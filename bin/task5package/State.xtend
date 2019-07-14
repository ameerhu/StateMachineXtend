package task5package

interface State {
	def String getName()
	def Transition[] getOutgoings()
	def State getZuper()
	def boolean isComplex()
}