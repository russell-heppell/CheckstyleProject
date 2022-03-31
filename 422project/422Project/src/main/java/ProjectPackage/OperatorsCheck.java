package ProjectPackage;

import com.puppycrawl.tools.checkstyle.api.*;

public class OperatorsCheck extends AbstractCheck {
	
	public int operatorCount = 0;

	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.CTOR_CALL, TokenTypes.DO_WHILE, TokenTypes.LITERAL_WHILE,
				TokenTypes.FOR_INIT, TokenTypes.LITERAL_IF, TokenTypes.LITERAL_ELSE, TokenTypes.LITERAL_SWITCH,
				TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_RETURN, TokenTypes.LCURLY,
				TokenTypes.COMMA, TokenTypes.SEMI, TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.STAR, 
				TokenTypes.PLUS_ASSIGN, TokenTypes.MINUS_ASSIGN, TokenTypes.DIV, TokenTypes.INDEX_OP,
				TokenTypes.METHOD_CALL};
	}

	@Override
	public int[] getAcceptableTokens() {
		return new int[] {TokenTypes.CTOR_CALL, TokenTypes.DO_WHILE, TokenTypes.LITERAL_WHILE,
				TokenTypes.FOR_INIT, TokenTypes.LITERAL_IF, TokenTypes.LITERAL_ELSE, TokenTypes.LITERAL_SWITCH,
				TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_RETURN, TokenTypes.LCURLY,
				TokenTypes.COMMA, TokenTypes.SEMI, TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.STAR, 
				TokenTypes.PLUS_ASSIGN, TokenTypes.MINUS_ASSIGN, TokenTypes.DIV, TokenTypes.INDEX_OP,
				TokenTypes.METHOD_CALL};
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}
	
	@Override
	public void beginTree(DetailAST ast) {
		
		operatorCount = 0;
	}
	
	@Override
	public void finishTree(DetailAST ast) {
		
		log(ast.getLineNo(), "number of operators: " + operatorCount + " - RH" );
	}
	
	@Override
	public void visitToken(DetailAST ast) {
		operatorCount++;
	}
}
