package ProjectPackage;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.Test;
import com.puppycrawl.tools.checkstyle.api.*;

public class CommentLinesCheckTest{

	@Test
	public void getDefaultTokensTest() {
		CommentLinesCheck check = new CommentLinesCheck();
		int[] expected = {TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.BLOCK_COMMENT_END, TokenTypes.SINGLE_LINE_COMMENT};
		assertArrayEquals(expected, check.getDefaultTokens());
	}

	@Test
	public void getAcceptableTokensTest() {
		CommentLinesCheck check = new CommentLinesCheck();
		int[] expected = {TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.BLOCK_COMMENT_END, TokenTypes.SINGLE_LINE_COMMENT};
		assertArrayEquals(expected, check.getAcceptableTokens());
	}

	@Test
	public void getRequiredTokensTest() {
		CommentLinesCheck check = new CommentLinesCheck();
		assertArrayEquals(new int[0], check.getRequiredTokens());
	}

	@Test
	public void commentNodesCheck() {
		CommentLinesCheck check = new CommentLinesCheck();
		assertTrue(check.isCommentNodesRequired());
		
	}
	
	@Test
	public void visitTokenTest() {
		
		// branch 1 test
		CommentLinesCheck check = new CommentLinesCheck();
		DetailAST mockAST = mock(DetailAST.class);
		doReturn(TokenTypes.SINGLE_LINE_COMMENT).when(mockAST).getType();
		check.visitToken(mockAST);
		assertEquals(1, check.commentLinesCount);
		
		// branch 2 test
		doReturn(TokenTypes.BLOCK_COMMENT_BEGIN).when(mockAST).getType();
		doReturn(1).when(mockAST).getLineNo();
		check.visitToken(mockAST);
		assertEquals(1, check.blockBeginLineNum);
		assertEquals(1, check.commentLinesCount);

		// branch 3 test - block comment is of length 4, so commentLinesCount will be 5 after visitToken call.
		doReturn(TokenTypes.BLOCK_COMMENT_END).when(mockAST).getType();
		doReturn(4).when(mockAST).getLineNo();
		check.visitToken(mockAST);
		assertEquals(5, check.commentLinesCount);
		
		// branch 4 test. neither of the comment tokens. commentLinesCount should remain unchanged
		doReturn(TokenTypes.IDENT).when(mockAST).getType();
		check.visitToken(mockAST);
		assertEquals(5, check.commentLinesCount);

	}
	
	@Test
	public void beginTreeTest() {
		CommentLinesCheck check = new CommentLinesCheck();
		DetailAST mockAST = mock(DetailAST.class);
		check.beginTree(mockAST);
		assertEquals(0, check.commentLinesCount);
	}
}
