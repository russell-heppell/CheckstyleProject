package ProjectPackage;

import com.puppycrawl.tools.checkstyle.api.*;

// Implements the number of comments check in Category B of the 422 project. 
// Checked on a java file 
public class CommentCheck extends AbstractCheck {

	private int commentCount = 0;
	
	public int getCommentCount() {
		return commentCount;
	}
	
	@Override
	public int[] getDefaultTokens() {
		// TODO Auto-generated method stub
		return new int[] {TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT};
	}

	@Override
	public int[] getAcceptableTokens() {
		// TODO Auto-generated method stub
		return new int[] {TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT};
	}

	@Override
	public int[] getRequiredTokens() {
		// TODO Auto-generated method stub
		return new int[0];
	}
	
	@Override
	public void beginTree(DetailAST ast) {
		commentCount = 0;
	}
	
	@Override
	public void finishTree(DetailAST ast) {
		
		log(ast.getLineNo(), "number of comments: " + commentCount + " - RH" );
	}
	
	@Override
	public void visitToken(DetailAST ast) {
		
		commentCount++;
	}
	
	@Override
	public boolean isCommentNodesRequired() {
		return true; 
	}
}
