package ProjectPackage;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import org.mockito.MockedStatic;
import static org.mockito.Mockito.*;

public class HalsteadLengthCheckTest {
	
	@Test
	public void defaultTokensTest() {
		HalsteadLengthCheck check = new HalsteadLengthCheck();
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
		HalsteadLengthCheck check = new HalsteadLengthCheck();
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
		HalsteadLengthCheck check = new HalsteadLengthCheck();
		assertArrayEquals(new int[0], check.getRequiredTokens());
	}
	
	@Test
	public void visitTokenTest() {
		HalsteadLengthCheck check = new HalsteadLengthCheck();
		DetailAST mockAST = mock(DetailAST.class);
		check.visitToken(mockAST);
		assertEquals(1, check.getHalsteadLength());
	}
	
	
	@Test
	public void beginTreeTest() {
		HalsteadLengthCheck check = new HalsteadLengthCheck();
		DetailAST mockAST = mock(DetailAST.class);
		check.beginTree(mockAST);
		assertEquals(0, check.getHalsteadLength());
	}
}
