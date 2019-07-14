package task5package;

import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import task5package.State;
import task5package.Transition;

@Accessors
@SuppressWarnings("all")
public class Region {
  private State[] states;
  
  private Transition[] transitions;
  
  @Pure
  public State[] getStates() {
    return this.states;
  }
  
  public void setStates(final State[] states) {
    this.states = states;
  }
  
  @Pure
  public Transition[] getTransitions() {
    return this.transitions;
  }
  
  public void setTransitions(final Transition[] transitions) {
    this.transitions = transitions;
  }
}
