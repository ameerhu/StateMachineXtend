package task5package;

import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import task5package.Action;
import task5package.Region;

@Accessors
@SuppressWarnings("all")
public class StateMachine extends Region {
  private String name;
  
  private Action[] actions;
  
  @Pure
  public String getName() {
    return this.name;
  }
  
  public void setName(final String name) {
    this.name = name;
  }
  
  @Pure
  public Action[] getActions() {
    return this.actions;
  }
  
  public void setActions(final Action[] actions) {
    this.actions = actions;
  }
}
