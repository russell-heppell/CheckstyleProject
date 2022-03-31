package ProjectPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;

public class TestEngine {
	
	// Test Engine to automate black box testing 
	public TestEngine(String filePath, AbstractCheck check) throws IOException, CheckstyleException {
		beginTest(filePath, check);
		
	}
	
	public void beginTest(String filePath, AbstractCheck check) throws IOException, CheckstyleException {
		
		File file = new File(filePath);
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);
				
		// Fill AST with FileContents
		DetailAST root = JavaParser.parse(fc);
		
		// Add hidden comment nodes
		JavaParser.appendHiddenCommentNodes(root);
		
		// Configure Check
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		check.beginTree(root);
				
		// Visit Each Token in Tree
		helper(check,root);
				
		// Complete tree and display intended logs to user.
		check.finishTree(root);
	}
	
	public void helper(AbstractCheck b, DetailAST a) {
		// need to modify the while statement to check the AcceptableTokens in the AbstractCheck being passed
		
		while(a != null) {
			if(doesCheckContainToken(b.getAcceptableTokens(), a)) {
				b.visitToken(a);
			}
			
			helper(b,a.getFirstChild());
			a = a.getNextSibling();
		}
	}
	
	public boolean doesCheckContainToken(int[] acceptableTokens, DetailAST a) {
		
		boolean contains = false;
		
		for(var token : acceptableTokens) {
			if(token == a.getType()) {
				contains = true;
			}
		}
		
		return contains;
	}
}
