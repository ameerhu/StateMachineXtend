package generatedFiles;

public class Loud extends Low{
	public void switchMode (ContextMachine wrapper){
		wrapper.setState(new Silent());
		System.out.println("Silent");
	}
}
