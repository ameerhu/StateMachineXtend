package generatedFilesFAN2;

public abstract class Low extends SpeedControl{
	public void pullChain (ContextMachine wrapper){
		wrapper.setState(new Medium());
		System.out.println("Medium");
	}
	public abstract void switchMode(ContextMachine wrapper);
	public abstract void loudMode(ContextMachine wrapper);
}
