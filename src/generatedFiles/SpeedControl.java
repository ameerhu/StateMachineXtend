package generatedFiles;

public abstract class SpeedControl implements State{
	public void pressButton (ContextMachine wrapper){
		wrapper.setState(new Off());
		System.out.println("Off");
	}
	public abstract void pullChain(ContextMachine wrapper);
	public abstract void switchMode(ContextMachine wrapper);
}
