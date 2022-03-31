package ProjectPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HalsteadDifficultyCheck extends AbstractCheck {
	
	public List<Integer> operands = Arrays.asList(TokenTypes.VARIABLE_DEF, TokenTypes.ARRAY_DECLARATOR, TokenTypes.IDENT);
	
	public List<Integer> operators = Arrays.asList(TokenTypes.CTOR_CALL, TokenTypes.DO_WHILE, TokenTypes.LITERAL_WHILE,
			TokenTypes.FOR_INIT, TokenTypes.LITERAL_IF, TokenTypes.LITERAL_ELSE, TokenTypes.LITERAL_SWITCH,
			TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_RETURN, TokenTypes.LCURLY,
			TokenTypes.COMMA, TokenTypes.SEMI, TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.STAR, 
			TokenTypes.PLUS_ASSIGN, TokenTypes.MINUS_ASSIGN, TokenTypes.DIV, TokenTypes.INDEX_OP,
			TokenTypes.METHOD_CALL);
	
	
	public List<Integer> visitedOperand = new ArrayList<Integer>();

	public List<Integer> visitedOperator = new ArrayList<Integer>();
	
	public int uniqueOperators = 0;
	public int uniqueOperands = 0;
	public int totalOperands = 0;
	
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
				TokenTypes.IDENT, TokenTypes.DIV, TokenTypes.METHOD_CALL};
	}
	
	
	@Override
	public int[] getRequiredTokens() {
		// TODO Auto-generated method stub
		return new int[0];
	}
	
	@Override
	public void visitToken(DetailAST aAST) {
		// aAST is the token to be processed
		
		if(operands.contains(aAST.getType())) {
			totalOperands++;
			
			if(!visitedOperand.contains(aAST.getType()))
			{
				visitedOperand.add(aAST.getType());
				uniqueOperands++;
			}
		}
		else if(operators.contains(aAST.getType())) {
			
			if(!visitedOperator.contains(aAST.getType()))
			{
				visitedOperator.add(aAST.getType());
				uniqueOperators++;
			}
		}
	}
	 
	@Override
	public void beginTree(DetailAST ast) {
		
		// reinitialize all changed variables

		visitedOperand.clear();
		visitedOperator.clear();
		
		uniqueOperators = 0;
		uniqueOperands = 0;
		totalOperands = 0;
	}
		
	@Override
	public void finishTree(DetailAST ast) {
		
		int difficulty = 0;
		
		// check value of uniqueOperands to ensure no divide by 0
		if(uniqueOperands != 0) {
			difficulty = ((uniqueOperators / 2) * totalOperands) / uniqueOperands;
		}
		
		log(ast.getLineNo(), "Halstead Difficulty: " + difficulty + " - RH");
	}
}





