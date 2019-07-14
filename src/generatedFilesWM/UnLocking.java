package generatedFilesWM;

public class UnLocking implements State{
	public void changeState (ContextMachine wrapper){
		wrapper.setState(new Final());
		System.out.println("Final");
	}
	public void startWash(ContextMachine wrapper){
		System.out.println("Can't Performed this action");
	}
} 
