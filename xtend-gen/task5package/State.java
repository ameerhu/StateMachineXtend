package task5package;

import task5package.Transition;

@SuppressWarnings("all")
public interface State {
  public abstract String getName();
  
  public abstract Transition[] getOutgoings();
  
  public abstract State getZuper();
  
  public abstract boolean isComplex();
}
