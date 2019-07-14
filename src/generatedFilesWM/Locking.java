package generatedFilesWM;

public class Locking implements State{
	public void startWash (ContextMachine wrapper){
		wrapper.setState(new Washing());
		System.out.println("Washing");
	}
	public void changeState(ContextMachine wrapper){
		System.out.println("Can't Performed this action");
	}
} 
