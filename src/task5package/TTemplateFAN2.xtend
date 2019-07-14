package task5package

import java.io.BufferedWriter
import java.io.FileWriter

class TTemplateFAN2 {
	String pkgName = "generatedFilesFAN2"
	String path = "src/"+pkgName+"/";
	BufferedWriter writer
	String data;
	
	def writeFiles(StateMachine main){
		fileWrite("State",genInterface(main.actions)+"")
		
		for(s:main.states){
			genStateClass(s,main.actions)
		}
		
		fileWrite("ContextMachine",genContext(main.states.get(0),main.actions)+"")
		
		fileWrite("TestStateMachine",genTestFile()+"")
	}
	
	def fileWrite(String name,String data){
		writer = new BufferedWriter(new FileWriter(path+name+".java"))
		writer.write(data)
		writer.close()
		println(name+" written.")
	}
	
	def genInterface(Action[] actions)'''
	package «pkgName»;
	
	interface State{
		«FOR a:actions»
		public void «a.name»(ContextMachine context);
		«ENDFOR»
	}
	'''
	
	def resolveState(State s){
		if(s.isComplex)
			resolveState((s as ComplexState).stateMachine.states.get(0))
		else
			s.name
	}
	
	def genStateClass(State s,Action []actions){
	data = '''
	package «pkgName»;
	
	«IF s.zuper == null && !s.isComplex»
	public class «s.name» implements State{
		«IF s.outgoings !== null»
			«FOR st:s.outgoings»
				«IF st.action !== null»
				public void «st.action.name» (ContextMachine wrapper){
					wrapper.setState(new «resolveState(st.target)»());
					System.out.println("«resolveState(st.target)»");
				}
				«ENDIF»
			«ENDFOR»
		«ENDIF»
		«IF actions !== null»
			«FOR a:actions»
				«IF s.outgoings!==null»
					«FOR o : s.outgoings»
					«IF o.action !== null»
						«IF o.action.name !== a.name»
							public void «a.name»(ContextMachine wrapper){
								System.out.println("Can't Performed this action");
							}
						«ENDIF»
						«ENDIF»
					«ENDFOR»
				«ELSE»
					public void «a.name»(ContextMachine wrapper){
						System.out.println("Can't Performed this action");
					}
				«ENDIF»
			«ENDFOR»
		«ENDIF»
	} 
	«ELSEIF s.isComplex && s.zuper == null»
	public abstract class «s.name» implements State{
		«IF s.outgoings !== null»
			«FOR st:s.outgoings»
				public void «st.action.name» (ContextMachine wrapper){
					wrapper.setState(new «st.target.name»());
					System.out.println("«st.target.name»");
				}
			«ENDFOR»
		«ENDIF»
		«IF actions !== null»
			«FOR a:actions»
				«IF s.outgoings!==null»
					«FOR o : s.outgoings»
						«IF o.action.name !== a.name»
						public abstract void «a.name»(ContextMachine wrapper);
						«ENDIF»
					«ENDFOR»
				«ENDIF»
			«ENDFOR»
		«ENDIF»
	}
	«ELSEIF s.isComplex && s.zuper !== null»
	public abstract class «s.name» extends «s.zuper.name»{
		«IF s.outgoings !== null»
			«FOR st:s.outgoings»
				public void «st.action.name» (ContextMachine wrapper){
					wrapper.setState(new «st.target.name»());
					System.out.println("«st.target.name»");
				}
			«ENDFOR»
		«ENDIF»
		«IF actions !== null»
		«FOR a:actions»
			«IF s.outgoings!==null»
				«FOR o : s.outgoings»
					«IF o.action.name !== a.name»
						public abstract void «a.name»(ContextMachine wrapper);
					«ENDIF»
				«ENDFOR»
			«ENDIF»
		«ENDFOR»
		«ENDIF»
	}
	«ELSE»
	public class «s.name» extends «s.zuper.name»{
		«IF s.outgoings !== null»
			«FOR st:s.outgoings»
				public void «st.action.name» (ContextMachine wrapper){
					wrapper.setState(new «resolveState(st.target)»());
					System.out.println("«resolveState(st.target)»");
				}
			«ENDFOR»
		«ENDIF»
		«IF actions !== null»
			«FOR a:actions»
				«IF s.outgoings!==null»
					«FOR o : s.outgoings»
						«IF o.action.name !== a.name»
							public void «a.name»(ContextMachine wrapper){
								System.out.println("Can't Performed this action");
							}
						«ENDIF»
					«ENDFOR»
				«ENDIF»
			«ENDFOR»
		«ENDIF»
	}
	«ENDIF»
	'''
	fileWrite(s.name,data);
	
	if(s.complex)
		for(st:(s as ComplexState).stateMachine.states){
			genStateClass(st,(s as ComplexState).stateMachine.actions)
		}
	}
	
	def genContext(State state, Action []actions)'''
	package «pkgName»;
	
	class ContextMachine {
	    private State currentState;
	
	    public ContextMachine() {
	        currentState = new «state.name»();
	        System.out.println("«state.name»");
	    }
	
	    public void setState(State s) {
	        currentState = s;
	    }
	    
	    «FOR a:actions»
	    public void «a.name»(){
	    	currentState.«a.name»(this);
	    }
	    «ENDFOR»
	}
	'''
	
	def genTestFile()'''
	package «pkgName»;
	
	public class TestStateMachine{
		public static void main(String args[]){
			ContextMachine context = new ContextMachine();
		}
	}
	'''
}