package generatedFilesFAN2;

public class Silent extends Low{
	public void switchMode (ContextMachine wrapper){
		wrapper.setState(new MinLoud());
		System.out.println("MinLoud");
	}
	public void loudMode(ContextMachine wrapper){
		System.out.println("Can't Performed this action");
	}
}
