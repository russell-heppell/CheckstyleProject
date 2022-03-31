package ProjectPackage;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

// Test class to perform black box testing from Fault Models.
public class BlackBoxTest {
		
	@Test
	void operatorOperand11Test() throws IOException, CheckstyleException {
		String path = System.getProperty("user.dir") +
				"\\BlackBoxTestCases\\OperatorOperand11.java";
		OperatorsCheck check = new OperatorsCheck();
		TestEngine engine = new TestEngine(path, check);
		assertEquals(8, check.operatorCount);
	}
	
	@Test
	void operatorOperand12Test() throws IOException, CheckstyleException {
		String path = System.getProperty("user.dir") +
				"\\BlackBoxTestCases\\OperatorOperand12.java";
		LoopingStatementCheck check = new LoopingStatementCheck();
		TestEngine engine = new TestEngine(path, check);
		assertEquals(1, check.loopingStatementCount);
	}
	
	@Test
	void operatorOperand13Test()throws IOException, CheckstyleException {
		String path = System.getProperty("user.dir") +
				"\\BlackBoxTestCases\\OperatorOperand13.java";
		OperatorsCheck check = new OperatorsCheck();
		TestEngine engine = new TestEngine(path, check);
		assertEquals(4, check.operatorCount);
	}
	
	@Test
	void operatorOperand14Test()throws IOException, CheckstyleException {
		String path = System.getProperty("user.dir") +
				"\\BlackBoxTestCases\\OperatorOperand14.java";
		HalsteadLengthCheck check = new HalsteadLengthCheck();
		TestEngine engine = new TestEngine(path, check);
		assertEquals(9, check.getHalsteadLength());
	}
	
	@Test
	void ExpressionBBTest()throws IOException, CheckstyleException {
		String path = System.getProperty("user.dir") +
				"\\BlackBoxTestCases\\ExpressionBB.java";
		ExpressionsCheck check = new ExpressionsCheck();
		TestEngine engine = new TestEngine(path, check);
		assertEquals(5, check.expressionCount);
	}
	
	@Test
	void CommentBBTest()throws IOException, CheckstyleException {
		String path = System.getProperty("user.dir") +
				"\\BlackBoxTestCases\\CommentsBB.java";
		CommentCheck commentCheck = new CommentCheck();
		CommentLinesCheck commentLineCheck = new CommentLinesCheck();
		TestEngine engine1 = new TestEngine(path, commentCheck);
		TestEngine engine2 = new TestEngine(path, commentLineCheck);
		assertEquals(6, commentLineCheck.commentLinesCount);
		assertEquals(4, commentCheck.getCommentCount());
	}
}
