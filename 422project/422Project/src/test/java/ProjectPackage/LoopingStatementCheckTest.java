package ProjectPackage;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import org.mockito.MockedStatic;
import static org.mockito.Mockito.*;


public class LoopingStatementCheckTest {
	
	@Test
	public void getDefaultTokensTest() {
		LoopingStatementCheck check = new LoopingStatementCheck();
		int[] expected = {TokenTypes.FOR_INIT, TokenTypes.DO_WHILE, TokenTypes.LITERAL_WHILE};
		assertArrayEquals(expected, check.getDefaultTokens());
	}

	@Test
	public void getAcceptableTokensTest() {
		LoopingStatementCheck check = new LoopingStatementCheck();
		int[] expected = {TokenTypes.FOR_INIT, TokenTypes.DO_WHILE, TokenTypes.LITERAL_WHILE};
		assertArrayEquals(expected, check.getAcceptableTokens());
	}

	@Test
	public void getRequiredTokensTest() {
		LoopingStatementCheck check = new LoopingStatementCheck();
		assertArrayEquals(new int[0], check.getRequiredTokens());
	}
	
	@Test
	public void visitTokenTest() {
		
		// branch 1 tests: FOR_INIT token test
		LoopingStatementCheck check = new LoopingStatementCheck();
		DetailAST mockAST = mock(DetailAST.class);
		doReturn(TokenTypes.FOR_INIT).when(mockAST).getType();
		check.visitToken(mockAST);
		assertEquals(1, check.loopingStatementCount);
		
		// DO_WHILE token test
		doReturn(TokenTypes.DO_WHILE).when(mockAST).getType();
		check.visitToken(mockAST);
		assertEquals(2, check.loopingStatementCount);

		// LITERAL_WHILE token test
		doReturn(TokenTypes.LITERAL_WHILE).when(mockAST).getType();
		check.visitToken(mockAST);
		assertEquals(3, check.loopingStatementCount);
		
		// branch 2 test. neither of the looping tokens.
		doReturn(TokenTypes.IDENT).when(mockAST).getType();
		check.visitToken(mockAST);
		assertEquals(3, check.loopingStatementCount);
	}
	
	@Test
	public void beginTreeTest() {
		LoopingStatementCheck check = new LoopingStatementCheck();
		DetailAST mockAST = mock(DetailAST.class);
		check.beginTree(mockAST);
		assertEquals(0, check.loopingStatementCount);
	}
}
