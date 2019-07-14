package task5package

import org.eclipse.xtend.lib.annotations.Accessors

@Accessors
class NamedState implements State {
	String name
	Transition[] outgoings
	State zuper
	boolean isComplex
}