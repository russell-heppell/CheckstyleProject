package ProjectPackage;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import org.mockito.MockedStatic;
import static org.mockito.Mockito.*;

public class OperatorsCheckTest {
	
	@Test
	public void getDefaultTokensTest() {
		OperatorsCheck check = new OperatorsCheck();
		int[] expected = {TokenTypes.CTOR_CALL, TokenTypes.DO_WHILE, TokenTypes.LITERAL_WHILE,
				TokenTypes.FOR_INIT, TokenTypes.LITERAL_IF, TokenTypes.LITERAL_ELSE, TokenTypes.LITERAL_SWITCH,
				TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_RETURN, TokenTypes.LCURLY,
				TokenTypes.COMMA, TokenTypes.SEMI, TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.STAR, 
				TokenTypes.PLUS_ASSIGN, TokenTypes.MINUS_ASSIGN, TokenTypes.DIV, TokenTypes.INDEX_OP,
				TokenTypes.METHOD_CALL};
		assertArrayEquals(expected, check.getDefaultTokens());
	}

	@Test
	public void getAcceptableTokensTest() {
		OperatorsCheck check = new OperatorsCheck();
		int[] expected = {TokenTypes.CTOR_CALL, TokenTypes.DO_WHILE, TokenTypes.LITERAL_WHILE,
				TokenTypes.FOR_INIT, TokenTypes.LITERAL_IF, TokenTypes.LITERAL_ELSE, TokenTypes.LITERAL_SWITCH,
				TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_RETURN, TokenTypes.LCURLY,
				TokenTypes.COMMA, TokenTypes.SEMI, TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.STAR, 
				TokenTypes.PLUS_ASSIGN, TokenTypes.MINUS_ASSIGN, TokenTypes.DIV, TokenTypes.INDEX_OP,
				TokenTypes.METHOD_CALL};
		assertArrayEquals(expected, check.getAcceptableTokens());
	}

	@Test
	public void getRequiredTokensTest() {
		OperatorsCheck check = new OperatorsCheck();
		assertArrayEquals(new int[0], check.getRequiredTokens());
	}
	
	@Test
	public void visitTokenTest() {
		
		OperatorsCheck check = new OperatorsCheck();
		DetailAST mockAST = mock(DetailAST.class);
		check.visitToken(mockAST);
		assertEquals(1, check.operatorCount);
	}
	
	@Test
	public void beginTreeTest() {
		OperatorsCheck check = new OperatorsCheck();
		DetailAST mockAST = mock(DetailAST.class);
		check.beginTree(mockAST);
		assertEquals(0, check.operatorCount);
	}
}
