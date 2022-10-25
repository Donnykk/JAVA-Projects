
package µÚ9ÕÂ×÷Òµ_11;

class Adapter implements Processor {
	public String name() { return "Adapter"; }
	StringOperator strOperator;
	public Adapter(StringOperator stringOperator) {
		this.strOperator = stringOperator;
	}
	public String process(Object input) {
		return strOperator.process((String)input);
	}	
}

public class Demo {
	public static void main(String[] args) {
		String s = new String();
		Apply.process(new Adapter(new StringOperator()), s);
	}
}
