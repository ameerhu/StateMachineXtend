package generatedFilesWM;

public class Washing implements State{
	public void changeState (ContextMachine wrapper){
		wrapper.setState(new UnLocking());
		System.out.println("UnLocking");
	}
	public void startWash(ContextMachine wrapper){
		System.out.println("Can't Performed this action");
	}
} 
