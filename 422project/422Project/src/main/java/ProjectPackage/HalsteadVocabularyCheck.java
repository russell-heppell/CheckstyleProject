package ProjectPackage;

import java.util.ArrayList;
import java.util.List;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class HalsteadVocabularyCheck extends AbstractCheck {
	
	public int halsteadVocabulary = 0;
	public List<Integer> visitedTokens = new ArrayList<Integer>();

	public int getHalsteadVocabulary() {
		return halsteadVocabulary;
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
		// TODO Auto-generated method stub
		return new int[0];
	}
	
	@Override
	public void visitToken(DetailAST aAST) {
		
		if(!visitedTokens.contains(aAST.getType())){
			visitedTokens.add(aAST.getType());
			halsteadVocabulary++;
		}
	}
	 
	@Override
	public void beginTree(DetailAST ast) {
		
		// reset variables
		halsteadVocabulary = 0;
		visitedTokens.clear();
	}
		
	@Override
	public void finishTree(DetailAST ast) {
		
		log(ast.getLineNo(), "Halstead Vocabulary: " + halsteadVocabulary + " - RH" );
	}
}
