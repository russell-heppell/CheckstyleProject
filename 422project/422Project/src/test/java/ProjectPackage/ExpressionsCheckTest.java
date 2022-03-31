package ProjectPackage;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class ExpressionsCheckTest {
	
	@Test
	public void getDefaultTokensTest() {
		ExpressionsCheck check = new ExpressionsCheck();
		int[] expected = {TokenTypes.EXPR};
		assertArrayEquals(expected, check.getDefaultTokens());
	}

	@Test
	public void getAcceptableTokensTest() {
		ExpressionsCheck check = new ExpressionsCheck();
		int[] expected = {TokenTypes.EXPR};
		assertArrayEquals(expected, check.getAcceptableTokens());
	}

	@Test
	public void getRequiredTokensTest() {
		ExpressionsCheck check = new ExpressionsCheck();
		assertArrayEquals(new int[0], check.getRequiredTokens());
	}
	
	@Test
	public void visitTokenTest() {
		
		ExpressionsCheck check = new ExpressionsCheck();
		DetailAST mockAST = mock(DetailAST.class);
		check.visitToken(mockAST);
		assertEquals(1, check.expressionCount);
	}
	
	@Test
	public void beginTreeTest() {
		ExpressionsCheck check = new ExpressionsCheck();
		DetailAST mockAST = mock(DetailAST.class);
		check.beginTree(mockAST);
		assertEquals(0, check.expressionCount);
	}
}
