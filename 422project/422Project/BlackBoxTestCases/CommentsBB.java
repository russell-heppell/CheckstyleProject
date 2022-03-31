// Java code sample for the Comments fault model. CommentLinesCount = 6. CommentCount = 4.
public class CommentsBB {
	
	public CommentsBB() {
		
		/*
		 * This is a block comment. // heres a single line comment operator that shouldnt be counted
		 */
		
		// This is a single line comment. // this is a line comment operator that shouldnt be counted. 
		
		// This is a single line comment. /* */ heres a block comment that shouldnt be counted. 

	}
 
}
