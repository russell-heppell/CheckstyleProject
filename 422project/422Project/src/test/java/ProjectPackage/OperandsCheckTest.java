package ProjectPackage;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import org.mockito.MockedStatic;
import static org.mockito.Mockito.*;

public class OperandsCheckTest {
	
	@Test
	public void getDefaultTokensTest() {
		OperandsCheck check = new OperandsCheck();
		int[] expected = {TokenTypes.VARIABLE_DEF, TokenTypes.ARRAY_DECLARATOR, TokenTypes.IDENT};
		assertArrayEquals(expected, check.getDefaultTokens());
	}

	@Test
	public void getAcceptableTokensTest() {
		OperandsCheck check = new OperandsCheck();
		int[] expected = {TokenTypes.VARIABLE_DEF, TokenTypes.ARRAY_DECLARATOR, TokenTypes.IDENT};
		assertArrayEquals(expected, check.getAcceptableTokens());
	}

	@Test
	public void getRequiredTokensTest() {
		OperandsCheck check = new OperandsCheck();
		assertArrayEquals(new int[0], check.getRequiredTokens());
	}
	
	@Test
	public void visitTokenTest() {
		
		OperandsCheck check = new OperandsCheck();
		DetailAST mockAST = mock(DetailAST.class);
		check.visitToken(mockAST);
		assertEquals(1, check.operandCount);
	}
	
	@Test
	public void beginTreeTest() {
		OperandsCheck check = new OperandsCheck();
		DetailAST mockAST = mock(DetailAST.class);
		check.beginTree(mockAST);
		assertEquals(0, check.operandCount);
	}
}
