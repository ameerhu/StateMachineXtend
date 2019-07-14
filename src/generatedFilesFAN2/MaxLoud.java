package generatedFilesFAN2;

public class MaxLoud extends Loud{
	public void loudMode (ContextMachine wrapper){
		wrapper.setState(new MinLoud());
		System.out.println("MinLoud");
	}
}
