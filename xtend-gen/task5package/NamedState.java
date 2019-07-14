package task5package;

import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import task5package.State;
import task5package.Transition;

@Accessors
@SuppressWarnings("all")
public class NamedState implements State {
  private String name;
  
  private Transition[] outgoings;
  
  private State zuper;
  
  private boolean isComplex;
  
  @Pure
  public String getName() {
    return this.name;
  }
  
  public void setName(final String name) {
    this.name = name;
  }
  
  @Pure
  public Transition[] getOutgoings() {
    return this.outgoings;
  }
  
  public void setOutgoings(final Transition[] outgoings) {
    this.outgoings = outgoings;
  }
  
  @Pure
  public State getZuper() {
    return this.zuper;
  }
  
  public void setZuper(final State zuper) {
    this.zuper = zuper;
  }
  
  @Pure
  public boolean isComplex() {
    return this.isComplex;
  }
  
  public void setIsComplex(final boolean isComplex) {
    this.isComplex = isComplex;
  }
}
