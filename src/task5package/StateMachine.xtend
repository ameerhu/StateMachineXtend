package task5package

import org.eclipse.xtend.lib.annotations.Accessors

@Accessors
class StateMachine extends Region{
	String name
	Action []actions
}