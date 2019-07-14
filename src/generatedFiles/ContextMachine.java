package generatedFiles;

class ContextMachine {
    private State currentState;

    public ContextMachine() {
        currentState = new Off();
        System.out.println("Off");
    }

    public void setState(State s) {
        currentState = s;
    }
    
    public void pressButton(){
    	currentState.pressButton(this);
    }
    public void pullChain(){
    	currentState.pullChain(this);
    }
    public void switchMode(){
    	currentState.switchMode(this);
    }
}
