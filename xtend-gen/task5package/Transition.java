package task5package;

import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import task5package.Action;
import task5package.State;

@Accessors
@SuppressWarnings("all")
public class Transition {
  private int id;
  
  private String trigger;
  
  private Action action;
  
  private State source;
  
  private State target;
  
  @Pure
  public int getId() {
    return this.id;
  }
  
  public void setId(final int id) {
    this.id = id;
  }
  
  @Pure
  public String getTrigger() {
    return this.trigger;
  }
  
  public void setTrigger(final String trigger) {
    this.trigger = trigger;
  }
  
  @Pure
  public Action getAction() {
    return this.action;
  }
  
  public void setAction(final Action action) {
    this.action = action;
  }
  
  @Pure
  public State getSource() {
    return this.source;
  }
  
  public void setSource(final State source) {
    this.source = source;
  }
  
  @Pure
  public State getTarget() {
    return this.target;
  }
  
  public void setTarget(final State target) {
    this.target = target;
  }
}
