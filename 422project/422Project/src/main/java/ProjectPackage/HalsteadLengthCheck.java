package ProjectPackage;

import com.puppycrawl.tools.checkstyle.api.*;

// Implements the Halstead Length check in Category A of the 422 project.
// Checked on a java file
public class HalsteadLengthCheck extends AbstractCheck {

    private int halsteadLength = 0;
    
    public int getHalsteadLength() {
    	return halsteadLength;
    }
    
	@Override
    public int[] getDefaultTokens() {
        return new int[] {TokenTypes.ARRAY_DECLARATOR, TokenTypes.VARIABLE_DEF, TokenTypes.CTOR_CALL,
				TokenTypes.LITERAL_WHILE, TokenTypes.DO_WHILE, TokenTypes.FOR_INIT, 
				TokenTypes.LITERAL_IF, TokenTypes.LITERAL_ELSE, TokenTypes.LITERAL_SWITCH,
				TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_RETURN, 
				TokenTypes.LCURLY, TokenTypes.COMMA, TokenTypes.SEMI, TokenTypes.PLUS,
				TokenTypes.MINUS, TokenTypes.STAR, TokenTypes.PLUS_ASSIGN, 
				TokenTypes.MINUS_ASSIGN, TokenTypes.INDEX_OP, 
				TokenTypes.IDENT, TokenTypes.DIV, TokenTypes.METHOD_CALL};
    }
 
    @Override
    public void visitToken(DetailAST aAST) {
    	// increment the Halstead length variable on every token visit
    	halsteadLength++;
    }

	@Override
	public int[] getAcceptableTokens() {		
		// list of operators/ operands used in the Halstead Length.
		return new int[] {TokenTypes.ARRAY_DECLARATOR, TokenTypes.VARIABLE_DEF, TokenTypes.CTOR_CALL,
						TokenTypes.LITERAL_WHILE, TokenTypes.DO_WHILE, TokenTypes.FOR_INIT, 
						TokenTypes.LITERAL_IF, TokenTypes.LITERAL_ELSE, TokenTypes.LITERAL_SWITCH,
						TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_RETURN, 
						TokenTypes.LCURLY, TokenTypes.COMMA, TokenTypes.SEMI, TokenTypes.PLUS,
						TokenTypes.MINUS, TokenTypes.STAR, TokenTypes.PLUS_ASSIGN, 
						TokenTypes.MINUS_ASSIGN, TokenTypes.INDEX_OP, 
						TokenTypes.IDENT, TokenTypes.DIV, TokenTypes.METHOD_CALL }; 
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}
	
	@Override
	public void beginTree(DetailAST ast) {
		halsteadLength = 0;
	}
	
	@Override
	public void finishTree(DetailAST ast) {
		
		log(ast.getLineNo(), "Halstead Length: " + halsteadLength + " - RH" );
	}
}

