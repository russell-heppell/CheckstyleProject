package ProjectPackage;

import com.puppycrawl.tools.checkstyle.api.*;


public class LoopingStatementCheck extends AbstractCheck {
	
	public int loopingStatementCount = 0;

	@Override
	public int[] getDefaultTokens() {
		// TODO Auto-generated method stub
		return new int[] {TokenTypes.FOR_INIT, TokenTypes.DO_WHILE, TokenTypes.LITERAL_WHILE};
	}

	@Override
	public int[] getAcceptableTokens() {
		// TODO Auto-generated method stub
		return new int[] {TokenTypes.FOR_INIT, TokenTypes.DO_WHILE, TokenTypes.LITERAL_WHILE};
	}

	@Override
	public int[] getRequiredTokens() {
		// TODO Auto-generated method stub
		return new int[0];
	}
	
	
	@Override
	public void beginTree(DetailAST ast) {
		loopingStatementCount = 0;
	}
	
	@Override
	public void finishTree(DetailAST ast) {
		
		log(ast.getLineNo(), "number of looping statements: " + loopingStatementCount + " - RH" );
	}
	
	@Override
	public void visitToken(DetailAST ast) {
		
		if(ast.getType() == TokenTypes.FOR_INIT || 
				ast.getType() == TokenTypes.DO_WHILE || 
				ast.getType() == TokenTypes.LITERAL_WHILE)
		{
			loopingStatementCount++;
		}
	}
}
