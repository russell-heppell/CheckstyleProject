package ProjectPackage;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import org.mockito.MockedStatic;
import static org.mockito.Mockito.*;

public class HalsteadVocabularyCheckTest {
	
	@Test
	public void defaultTokensTest() {
		HalsteadVocabularyCheck check = new HalsteadVocabularyCheck();
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
		HalsteadVocabularyCheck check = new HalsteadVocabularyCheck();
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
		HalsteadVocabularyCheck check = new HalsteadVocabularyCheck();
		assertArrayEquals(new int[0], check.getRequiredTokens());
	}
	
	
	@Test
	public void visitTokenTest() {
		HalsteadVocabularyCheck check = new HalsteadVocabularyCheck();
		DetailAST mockAST = mock(DetailAST.class);
		doReturn(TokenTypes.ARRAY_DECLARATOR).when(mockAST).getType();
		check.visitToken(mockAST);
		assertEquals(1, check.getHalsteadVocabulary());
		
		// call visitToken again to go into false branch
		check.visitToken(mockAST);
		assertEquals(1, check.getHalsteadVocabulary());
	}
	
	@Test
	public void beginTreeTest() {
		HalsteadVocabularyCheck check = new HalsteadVocabularyCheck();
		DetailAST mockAST = mock(DetailAST.class);
		check.beginTree(mockAST);
		assertEquals(0, check.getHalsteadVocabulary());
		assertEquals(true, check.visitedTokens.isEmpty());
	}
}
