package task5package;

import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import task5package.NamedState;
import task5package.StateMachine;

@Accessors
@SuppressWarnings("all")
public class ComplexState extends NamedState {
  private StateMachine stateMachine;
  
  @Pure
  public StateMachine getStateMachine() {
    return this.stateMachine;
  }
  
  public void setStateMachine(final StateMachine stateMachine) {
    this.stateMachine = stateMachine;
  }
}
