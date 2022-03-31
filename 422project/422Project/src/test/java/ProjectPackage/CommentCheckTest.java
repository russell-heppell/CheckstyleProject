package ProjectPackage;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import org.mockito.MockedStatic;
import static org.mockito.Mockito.*;

public class CommentCheckTest {
	
	@Test
	public void defaultTokensTest() {
		CommentCheck check = new CommentCheck();
		int[] expected = {TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT};
		assertArrayEquals(expected, check.getDefaultTokens());
	}
	
	@Test
	public void requiredTokensTest() {
		CommentCheck check = new CommentCheck();
		assertArrayEquals(new int[0], check.getRequiredTokens());
	}
	
	@Test
	public void acceptableTokensTest() {
		CommentCheck check = new CommentCheck();
		int[] expected = {TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT};
		assertArrayEquals(expected, check.getAcceptableTokens());
	}
	
	@Test
	public void commentNodesCheck() {
		CommentCheck check = new CommentCheck();
		assertTrue(check.isCommentNodesRequired());
		
	}
	
	@Test
	public void visitTokenTest() {
		CommentCheck check = new CommentCheck();
		DetailAST mockAST = mock(DetailAST.class);
		check.visitToken(mockAST);
		assertEquals(1, check.getCommentCount());
	}
	
	@Test
	public void beginTreeTest() {
		CommentCheck check = new CommentCheck();
		DetailAST mockAST = mock(DetailAST.class);
		check.beginTree(mockAST);
		assertEquals(0, check.getCommentCount());
	}
}
