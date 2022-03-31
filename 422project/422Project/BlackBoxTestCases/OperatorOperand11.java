package ProjectPackage;

// Black box test file for fault model 1.1. total number of operators should be 8,
// because of 4 semicolons and 2 brackets
public class OperatorOperand11 {
	private int i = 1;
	private int j = 2;
	
	public OperatorOperand11() {
		j -= i;		// first test with fault model 1.1. should only count it once
		i += j;		// second test with fault model 1.1. should only count it once. 
	}
}
