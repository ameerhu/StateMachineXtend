package task5package;

public class TestingFanMore {

	public static void main(String[] args) {
		StateMachine stateMachine = new StateMachine();
		stateMachine.setName("MainRegion");
		
		PseudoState initial = new PseudoState();
		initial.setName("Initial");
		
		NamedState off = new NamedState();
		off.setName("Off");
		
		ComplexState speedControl = new ComplexState();
		speedControl.setName("SpeedControl");
		StateMachine subSM = new StateMachine();
		subSM.setName("speedControl");
		speedControl.setStateMachine(subSM);
		
		PseudoState initial2 = new PseudoState();
		initial2.setName("Initial2");
		
		ComplexState low = new ComplexState();
		low.setName("Low");
		StateMachine subSubSM = new StateMachine();
		subSubSM.setName("switchMode");
		low.setStateMachine(subSubSM);
		
		PseudoState initial3 = new PseudoState();
		initial3.setName("Initial3");
		
		NamedState silent = new NamedState();
		silent.setName("Silent");
		
		ComplexState loud = new ComplexState();
		loud.setName("Loud");
		StateMachine subsubsubSM = new StateMachine();
		subsubsubSM.setName("loudMode");
		loud.setStateMachine(subsubsubSM);
		
		NamedState medium = new NamedState();
		medium.setName("Medium");
		
		NamedState high = new NamedState();
		high.setName("High");
		
		NamedState minLoud = new NamedState();
		minLoud.setName("MinLoud");
		
		NamedState maxLoud = new NamedState();
		maxLoud.setName("MaxLoud");
		
		
		//Setting the super States & complex property
		off.setIsComplex(false);
		speedControl.setIsComplex(true);
		low.setZuper(speedControl);
		low.setIsComplex(true);
		medium.setZuper(speedControl);
		medium.setIsComplex(false);
		high.setZuper(speedControl);
		high.setIsComplex(false);
		silent.setZuper(low);
		silent.setIsComplex(false);
		loud.setZuper(low);
		loud.setIsComplex(true);
		minLoud.setZuper(loud);
		minLoud.setIsComplex(false);
		maxLoud.setZuper(loud);
		maxLoud.setIsComplex(false);
		
		
		//Actions
		Action press = new Action();
		press.setName("pressButton");
		
		Action sMode = new Action();
		sMode.setName("switchMode");
		
		Action pull = new Action();
		pull.setName("pullChain");
		
		Action loudMode = new Action();
		loudMode.setName("loudMode");
		//Transitions
		Transition i2o = new Transition();
		i2o.setId(1);
		i2o.setSource(initial);
		i2o.setTarget(off);
		
		Transition o2speed = new Transition();
		o2speed.setId(2);
		o2speed.setSource(off);
		o2speed.setTarget(speedControl);
		o2speed.setAction(press);
		
		Transition speed2o = new Transition();
		speed2o.setId(3);
		speed2o.setSource(speedControl);
		speed2o.setTarget(off);
		speed2o.setAction(press);
		
		Transition l2m = new Transition();
		l2m.setId(4);
		l2m.setSource(low);
		l2m.setTarget(medium);
		l2m.setAction(pull);
		
		Transition m2h = new Transition();
		m2h.setId(5);
		m2h.setSource(medium);
		m2h.setTarget(high);
		m2h.setAction(pull);
		
		Transition h2l = new Transition();
		h2l.setId(6);
		h2l.setSource(high);
		h2l.setTarget(low);
		h2l.setAction(pull);
				
		Transition s2l = new Transition();
		s2l.setId(9);
		s2l.setSource(silent);
		s2l.setTarget(loud);
		s2l.setAction(sMode);
		
		Transition l2s = new Transition();
		l2s.setId(10);
		l2s.setSource(loud);
		l2s.setTarget(silent);
		l2s.setAction(sMode);
		
		Transition min2ml = new Transition();
		min2ml.setId(11);
		min2ml.setSource(minLoud);
		min2ml.setTarget(maxLoud);
		min2ml.setAction(loudMode);
		
		Transition max2ml = new Transition();
		max2ml.setId(11);
		max2ml.setSource(maxLoud);
		max2ml.setTarget(minLoud);
		max2ml.setAction(loudMode);
		
		//setting outgoings os states
		off.setOutgoings(new Transition[] {o2speed});
		speedControl.setOutgoings(new Transition[] {speed2o});
		low.setOutgoings(new Transition[] {l2m});
		silent.setOutgoings(new Transition[] {s2l});
		loud.setOutgoings(new Transition[] {l2s});
		medium.setOutgoings(new Transition[] {m2h});
		high.setOutgoings(new Transition[] {h2l});
		minLoud.setOutgoings(new Transition[] {min2ml});
		maxLoud.setOutgoings(new Transition[] {max2ml});
		
		//setting the Loud control mode
		subsubsubSM.setStates(new State[] {minLoud,maxLoud});
		subsubsubSM.setTransitions(new Transition[] {min2ml,max2ml});
		subsubsubSM.setActions(new Action[] {loudMode});
		
		//Setting the Mode control Machine
		subSubSM.setStates(new State[] {silent,loud});
		subSubSM.setTransitions(new Transition[] {s2l,l2s});
		subSubSM.setActions(new Action[] {sMode,loudMode});
	
		//Setting the Speed Control Machine
		subSM.setStates(new State[] {low,medium,high});
		subSM.setTransitions(new Transition[] {l2m,m2h,h2l});
		subSM.setActions(new Action[] {pull,sMode,loudMode});
		
		//Setting the Main Machine
		stateMachine.setStates(new State[] {off,speedControl});
		stateMachine.setTransitions(new Transition[] {o2speed,speed2o});
		stateMachine.setActions(new Action[] {press,pull,sMode,loudMode});
		
		TTemplateFAN2 temp = new TTemplateFAN2();
		temp.writeFiles(stateMachine);
	}

}
