package generatedFilesFAN2;

public class MinLoud extends Loud{
	public void loudMode (ContextMachine wrapper){
		wrapper.setState(new MaxLoud());
		System.out.println("MaxLoud");
	}
}
