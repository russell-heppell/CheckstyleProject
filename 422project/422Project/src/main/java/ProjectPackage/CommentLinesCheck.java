package ProjectPackage;

import com.puppycrawl.tools.checkstyle.api.*;


public class CommentLinesCheck extends AbstractCheck {
	
	public int commentLinesCount = 0;
	public int blockBeginLineNum;

	@Override
	public int[] getDefaultTokens() {
		// TODO Auto-generated method stub
		return new int[] {TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.BLOCK_COMMENT_END, TokenTypes.SINGLE_LINE_COMMENT};
	}

	@Override
	public int[] getAcceptableTokens() {
		// TODO Auto-generated method stub
		return new int[] {TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.BLOCK_COMMENT_END, TokenTypes.SINGLE_LINE_COMMENT};
	}

	@Override
	public int[] getRequiredTokens() {
		// TODO Auto-generated method stub
		return new int[0];
	}
	
	@Override
	public void beginTree(DetailAST ast) {
		commentLinesCount = 0;
	}
	
	@Override
	public void finishTree(DetailAST ast) {
		
		log(ast.getLineNo(), "number of lines of comments: " + commentLinesCount + " - RH" );

	}
	
	@Override
	public void visitToken(DetailAST ast) {
		
		// single line comments count as 1, so just increment
		if(ast.getType() == TokenTypes.SINGLE_LINE_COMMENT) 
		{
			commentLinesCount++;
		}
		// if it is a beginning block comment, save the line number
		else if(ast.getType() == TokenTypes.BLOCK_COMMENT_BEGIN)
		{
			blockBeginLineNum = ast.getLineNo();
		}
		// if its an ending block comment, calculate the total lines of comments 
		// w/ the saved beginning line number, and increment the lines count
		else if(ast.getType() == TokenTypes.BLOCK_COMMENT_END)
		{
			int lineNum = ast.getLineNo() - blockBeginLineNum + 1;
			commentLinesCount += lineNum;
		}
	}
	
	@Override
	public boolean isCommentNodesRequired() {
		return true;
	}
}
