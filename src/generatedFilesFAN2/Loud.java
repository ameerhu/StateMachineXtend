package generatedFilesFAN2;

public abstract class Loud extends Low{
	public void switchMode (ContextMachine wrapper){
		wrapper.setState(new Silent());
		System.out.println("Silent");
	}
	public abstract void loudMode(ContextMachine wrapper);
}
