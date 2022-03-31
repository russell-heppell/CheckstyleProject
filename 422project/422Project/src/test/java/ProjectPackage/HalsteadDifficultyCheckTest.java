package ProjectPackage;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import org.mockito.MockedStatic;
import static org.mockito.Mockito.*;

public class HalsteadDifficultyCheckTest {
	
	@Test
	public void defaultTokensTest() {
		HalsteadDifficultyCheck check = new HalsteadDifficultyCheck();
		int[] expected = {TokenTypes.ARRAY_DECLARATOR, TokenTypes.VARIABLE_DEF, TokenTypes.CTOR_CALL,
				TokenTypes.LITERAL_WHILE, TokenTypes.DO_WHILE, TokenTypes.FOR_INIT, 
				TokenTypes.LITERAL_IF, TokenTypes.LITERAL_ELSE, TokenTypes.LITERAL_SWITCH,
				TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_RETURN, 
				TokenTypes.LCURLY, TokenTypes.COMMA, TokenTypes.SEMI, TokenTypes.PLUS,
				TokenTypes.MINUS, TokenTypes.STAR, TokenTypes.PLUS_ASSIGN, 
				TokenTypes.MINUS_ASSIGN, TokenTypes.INDEX_OP, 
				TokenTypes.IDENT, TokenTypes.DIV, TokenTypes.METHOD_CALL};
		
		assertArrayEquals(expected, check.getDefaultTokens());
	}
	
	@Test
	public void acceptableTokensTest() {
		HalsteadDifficultyCheck check = new HalsteadDifficultyCheck();
		int[] expected = {TokenTypes.ARRAY_DECLARATOR, TokenTypes.VARIABLE_DEF, TokenTypes.CTOR_CALL,
				TokenTypes.LITERAL_WHILE, TokenTypes.DO_WHILE, TokenTypes.FOR_INIT, 
				TokenTypes.LITERAL_IF, TokenTypes.LITERAL_ELSE, TokenTypes.LITERAL_SWITCH,
				TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_RETURN, 
				TokenTypes.LCURLY, TokenTypes.COMMA, TokenTypes.SEMI, TokenTypes.PLUS,
				TokenTypes.MINUS, TokenTypes.STAR, TokenTypes.PLUS_ASSIGN, 
				TokenTypes.MINUS_ASSIGN, TokenTypes.INDEX_OP, 
				TokenTypes.IDENT, TokenTypes.DIV, TokenTypes.METHOD_CALL};
		
		assertArrayEquals(expected, check.getAcceptableTokens());
	}
	
	@Test
	public void requiredTokensTest() {
		HalsteadDifficultyCheck check = new HalsteadDifficultyCheck();
		assertArrayEquals(new int[0], check.getRequiredTokens());
	}
	
	
	@Test
	public void visitTokenTest() {
		HalsteadDifficultyCheck check = new HalsteadDifficultyCheck();
		DetailAST mockAST = mock(DetailAST.class);
		
		// Testing the operands case w/ variable def token
		doReturn(TokenTypes.VARIABLE_DEF).when(mockAST).getType();
		check.visitToken(mockAST);
		assertEquals(1, check.totalOperands);
		assertEquals(1, check.uniqueOperands);
		assertEquals(1, check.visitedOperand.size());
		
		// call visitToken again. totalOperands should increment,
		// but uniqueOperands should not since we already have variableDef in the list.
		check.visitToken(mockAST);
		assertEquals(2, check.totalOperands);
		assertEquals(1, check.uniqueOperands);
		assertEquals(1, check.visitedOperand.size());
		
		// now test the operators case w/ constructor call token
		doReturn(TokenTypes.CTOR_CALL).when(mockAST).getType();
		check.visitToken(mockAST);
		assertEquals(1, check.visitedOperator.size());
		assertEquals(1, check.uniqueOperators);
		
		// call visitToken again, nothing should change since we already have CTOR_CALL in the list.
		check.visitToken(mockAST);
		assertEquals(1, check.visitedOperator.size());
		assertEquals(1, check.uniqueOperators);
		
		// Now, test the false branch by using a token that isnt used at all, 
		// and check that nothing is changed 
		doReturn(TokenTypes.DOUBLE_COLON).when(mockAST).getType();
		check.visitToken(mockAST);
		assertEquals(1, check.visitedOperator.size());
		assertEquals(1, check.uniqueOperators);
		assertEquals(2, check.totalOperands);
		assertEquals(1, check.uniqueOperands);
		assertEquals(1, check.visitedOperand.size());

	}
	
	@Test
	public void beginTreeTest() {
		HalsteadDifficultyCheck check = new HalsteadDifficultyCheck();
		DetailAST mockAST = mock(DetailAST.class);
		check.beginTree(mockAST);
		
		// test that all variables reset
		assertEquals(true, check.visitedOperand.isEmpty());
		assertEquals(true, check.visitedOperator.isEmpty());

		assertEquals(0, check.uniqueOperators);
		assertEquals(0, check.uniqueOperands);
		assertEquals(0, check.totalOperands);

	}
}
