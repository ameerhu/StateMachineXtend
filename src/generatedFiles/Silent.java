package generatedFiles;

public class Silent extends Low{
	public void switchMode (ContextMachine wrapper){
		wrapper.setState(new Loud());
		System.out.println("Loud");
	}
}
