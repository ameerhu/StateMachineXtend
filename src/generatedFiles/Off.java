package generatedFiles;

public class Off implements State{
	public void pressButton (ContextMachine wrapper){
		wrapper.setState(new Silent());
		System.out.println("Silent");
	}
	public void pullChain(ContextMachine wrapper){
		System.out.println("Can't Performed this action");
	}
	public void switchMode(ContextMachine wrapper){
		System.out.println("Can't Performed this action");
	}
} 
