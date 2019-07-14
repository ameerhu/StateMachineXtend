package task5package

import org.eclipse.xtend.lib.annotations.Accessors

@Accessors
class Transition {
	int id
	String trigger
	Action action
	State source
	State target
}