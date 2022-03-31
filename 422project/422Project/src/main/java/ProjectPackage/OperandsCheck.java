package ProjectPackage;

import com.puppycrawl.tools.checkstyle.api.*;

public class OperandsCheck extends AbstractCheck {
	
	public int operandCount = 0;

	@Override
	public int[] getDefaultTokens() {

		return new int[] {TokenTypes.VARIABLE_DEF, TokenTypes.ARRAY_DECLARATOR, TokenTypes.IDENT};
	}

	@Override
	public int[] getAcceptableTokens() {

		return new int[] {TokenTypes.VARIABLE_DEF, TokenTypes.ARRAY_DECLARATOR, TokenTypes.IDENT};
	}

	@Override
	public int[] getRequiredTokens() {

		 return new int[0];
	}

	@Override
	public void beginTree(DetailAST ast) {
		
		operandCount = 0;
	}
	
	@Override
	public void finishTree(DetailAST ast) {
		
		log(ast.getLineNo(), "number of operands: " + operandCount + " - RH" );
	}
	
	@Override
	public void visitToken(DetailAST ast) {
		operandCount++;
	}
}
