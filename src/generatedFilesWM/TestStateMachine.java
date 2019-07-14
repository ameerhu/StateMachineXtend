package generatedFilesWM;

public class TestStateMachine{
	public static void main(String args[]){
		ContextMachine context = new ContextMachine();
		context.changeState();
		context.startWash();
		context.changeState();
		context.changeState();
		context.changeState();
		
	}
}
