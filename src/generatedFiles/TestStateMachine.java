package generatedFiles;

public class TestStateMachine{
	public static void main(String args[]){
		ContextMachine context = new ContextMachine();
		context.pullChain();
		context.switchMode();
		context.pressButton();
		context.switchMode();
		context.pullChain();
		context.pressButton();
		context.pressButton();
		context.switchMode();
		context.pressButton();
	}
}
