package task5package;

public class TestW {
	public static void main(String args[]) {
		
		PseudoState initial = new PseudoState();
		initial.setName("Initial");
		NamedState locking = new NamedState();
		locking.setName("Locking");
		NamedState waiting = new NamedState();
		waiting.setName("Waiting");
		NamedState washing = new NamedState();
		washing.setName("Washing");
		NamedState unlocking = new NamedState();
		unlocking.setName("UnLocking");	
		PseudoState end = new PseudoState();
		end.setName("Final");
		
		Action startWash = new Action();
		startWash.setName("startWash");
		Action changeState = new Action();
		changeState.setName("changeState");
		
		
		//Initial to locking
				Transition initialTolocking = new Transition();
				initialTolocking.setId(1);
				initialTolocking.setSource(initial);
				initialTolocking.setTarget(locking);
				initialTolocking.setAction(changeState);
						
						
			//Locking to waiting first transition	
				Transition lockingToWaiting = new Transition();
				lockingToWaiting.setId(2);
				lockingToWaiting.setSource(locking);
				lockingToWaiting.setTarget(waiting);
				lockingToWaiting.setTrigger("PRESS_DELAYED_START");
				
			//Locking to washing 
				Transition lockingToWashing = new Transition();
				lockingToWashing.setId(3);
				lockingToWashing.setSource(locking);
				lockingToWashing.setTarget(washing);
				lockingToWashing.setTrigger("PRESS_START");
				lockingToWashing.setAction(startWash);
				
			//Waiting to washing
				Transition waitingToWashing = new Transition();
				waitingToWashing.setId(4);
				waitingToWashing.setSource(waiting);
				waitingToWashing.setTarget(washing);
				waitingToWashing.setAction(startWash);
				
			//Washing to Unlocking
				Transition washingToUnlocking = new Transition();
				washingToUnlocking.setSource(washing);
				washingToUnlocking.setTarget(unlocking);
				washingToUnlocking.setAction(changeState);
				
			//Unlocking to End
				Transition UnlockingToEnd = new Transition();
				UnlockingToEnd.setSource(unlocking);
				UnlockingToEnd.setTarget(end);
				UnlockingToEnd.setAction(changeState);
		
		//Setting outgoings
		initial.setOutgoings(new Transition[] {initialTolocking});
		locking.setOutgoings(new Transition[] {lockingToWaiting,lockingToWashing});
		waiting.setOutgoings(new Transition[] {waitingToWashing});
		washing.setOutgoings(new Transition[] {washingToUnlocking});
		unlocking.setOutgoings(new Transition[] {UnlockingToEnd});
		
		//setting state as not complex
		initial.setIsComplex(false);
		locking.setIsComplex(false);
		waiting.setIsComplex(false);
		washing.setIsComplex(false);
		unlocking.setIsComplex(false);
		end.setIsComplex(false);
		
		//Creating the main region which will hold the complete Machine
		StateMachine washingMachine = new StateMachine();
		washingMachine.setName("Main");
		washingMachine.setActions(new Action[] {startWash,changeState});
		washingMachine.setStates(new State[] {initial,locking,waiting,washing,unlocking,end});
		washingMachine.setTransitions(new Transition[] {initialTolocking,lockingToWaiting,lockingToWashing,waitingToWashing,washingToUnlocking,UnlockingToEnd});
		
		TTemplateWM tmp = new TTemplateWM();
		tmp.writeFiles(washingMachine);
				
	}

}
