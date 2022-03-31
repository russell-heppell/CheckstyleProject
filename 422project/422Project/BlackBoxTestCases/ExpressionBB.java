

// Java code sample for expression Fault model. Since the expression counting only deals with 
// one token type, its a simpler count, so all models 3.1 - 3.4 tested in the same file. 
// expression count should return 5.
public class ExpressionBB {
	
	private int var1;
	
	public ExpressionBB() {
		var1 = 50;							// Assignment.
		var1++;								// Increment.
		var1--;								// Decrement.
		int num = var1 + 2;					// Definition.
		String s = "Hello " + "World!";		//String concatenation.
	}
}
