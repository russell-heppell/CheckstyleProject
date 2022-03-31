package ProjectPackage;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.lang.*;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;


public class HalsteadVolumeCheck extends AbstractCheck {
	
	public int halsteadVocabulary = 0;
	public int halsteadLength = 0;
	
	public List<Integer> visitedTokensAST = new ArrayList<Integer>();

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
		// aAST is the token to be processed
		
		// increment the Halstead length no matter what
		halsteadLength++;
		
		// if the token is not in the list, then it hasn't been accounted for so add it 
		// and increment our vocabulary. 
		if(!visitedTokensAST.contains(aAST.getType())) {
			visitedTokensAST.add(aAST.getType());
			halsteadVocabulary++;
		}
	}
	 
	@Override
	public void beginTree(DetailAST ast) {
		// reinitialize all changed variables
		
		visitedTokensAST.clear();
		halsteadVocabulary = 0;
		halsteadLength = 0;
	}
		
	@Override
	public void finishTree(DetailAST ast) {
		// halstead Volume = halsteadLength * log2(halsteadVocabulary)
		
		int volume = halsteadLength * (int)(Math.log(halsteadVocabulary) / Math.log(2));
		
		log(ast.getLineNo(), "Halstead Volume: " + volume + " - RH" );

	}
}
