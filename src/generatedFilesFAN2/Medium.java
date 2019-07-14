package generatedFilesFAN2;

public class Medium extends SpeedControl{
	public void pullChain (ContextMachine wrapper){
		wrapper.setState(new High());
		System.out.println("High");
	}
	public void switchMode(ContextMachine wrapper){
		System.out.println("Can't Performed this action");
	}
	public void loudMode(ContextMachine wrapper){
		System.out.println("Can't Performed this action");
	}
}
