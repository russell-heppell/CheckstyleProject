package ProjectPackage;

import com.puppycrawl.tools.checkstyle.api.*;


public class ExpressionsCheck extends AbstractCheck {
	
	public int expressionCount = 0;

	@Override
	public int[] getDefaultTokens() {

		return new int[] {TokenTypes.EXPR};
	}

	@Override
	public int[] getAcceptableTokens() {

		return new int[] {TokenTypes.EXPR};
	}

	@Override
	public int[] getRequiredTokens() {

		return new int[0];
	}
	
	@Override
	public void beginTree(DetailAST ast) {
		
		expressionCount = 0;
	}
	
	@Override
	public void finishTree(DetailAST ast) {
		
		log(ast.getLineNo(), "number of expressions: " + expressionCount + " - RH" );
	}
	
	@Override
	public void visitToken(DetailAST ast) {
		
		expressionCount++;
	}
}
