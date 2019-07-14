package generatedFilesWM;

public class Initial implements State{
	public void changeState (ContextMachine wrapper){
		wrapper.setState(new Locking());
		System.out.println("Locking");
	}
	public void startWash(ContextMachine wrapper){
		System.out.println("Can't Performed this action");
	}
} 
