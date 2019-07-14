package generatedFilesFAN2;

public class High extends SpeedControl{
	public void pullChain (ContextMachine wrapper){
		wrapper.setState(new Silent());
		System.out.println("Silent");
	}
	public void switchMode(ContextMachine wrapper){
		System.out.println("Can't Performed this action");
	}
	public void loudMode(ContextMachine wrapper){
		System.out.println("Can't Performed this action");
	}
}
