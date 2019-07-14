package generatedFilesWM;

class ContextMachine {
    private State currentState;

    public ContextMachine() {
        currentState = new Initial();
        System.out.println("Initial");
    }

    public void setState(State s) {
        currentState = s;
    }
    
    public void startWash(){
    	currentState.startWash(this);
    }
    public void changeState(){
    	currentState.changeState(this);
    }
}
