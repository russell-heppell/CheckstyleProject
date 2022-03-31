package ProjectPackage;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.io.*;
import java.lang.*;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class HalsteadVolumeCheckTest {
	
	@Test
	public void defaultTokensTest() {
		HalsteadVolumeCheck check = new HalsteadVolumeCheck();
		int[] expected = {TokenTypes.ARRAY_DECLARATOR, TokenTypes.VARIABLE_DEF, TokenTypes.CTOR_CALL,
				TokenTypes.LITERAL_WHILE, TokenTypes.DO_WHILE, TokenTypes.FOR_INIT, 
				TokenTypes.LITERAL_IF, TokenTypes.LITERAL_ELSE, TokenTypes.LITERAL_SWITCH,
				TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_RETURN, 
				TokenTypes.LCURLY, TokenTypes.COMMA, TokenTypes.SEMI, TokenTypes.PLUS,
				TokenTypes.MINUS, TokenTypes.STAR, TokenTypes.PLUS_ASSIGN, 
				TokenTypes.MINUS_ASSIGN, TokenTypes.INDEX_OP, 
				TokenTypes.IDENT, TokenTypes.DIV, TokenTypes.METHOD_CALL};
		
		assertArrayEquals(expected, check.getDefaultTokens());
	}
	
	@Test
	public void acceptableTokensTest() {
		HalsteadVolumeCheck check = new HalsteadVolumeCheck();
		int[] expected = {TokenTypes.ARRAY_DECLARATOR, TokenTypes.VARIABLE_DEF, TokenTypes.CTOR_CALL,
				TokenTypes.LITERAL_WHILE, TokenTypes.DO_WHILE, TokenTypes.FOR_INIT, 
				TokenTypes.LITERAL_IF, TokenTypes.LITERAL_ELSE, TokenTypes.LITERAL_SWITCH,
				TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_RETURN, 
				TokenTypes.LCURLY, TokenTypes.COMMA, TokenTypes.SEMI, TokenTypes.PLUS,
				TokenTypes.MINUS, TokenTypes.STAR, TokenTypes.PLUS_ASSIGN, 
				TokenTypes.MINUS_ASSIGN, TokenTypes.INDEX_OP, 
				TokenTypes.IDENT, TokenTypes.DIV, TokenTypes.METHOD_CALL};
		
		assertArrayEquals(expected, check.getAcceptableTokens());
	}
	
	@Test
	public void requiredTokensTest() {
		HalsteadVolumeCheck check = new HalsteadVolumeCheck();
		assertArrayEquals(new int[0], check.getRequiredTokens());
	}
	
	
	@Test
	public void visitTokenTest() {
		HalsteadVolumeCheck check = new HalsteadVolumeCheck();
		DetailAST mockAST = mock(DetailAST.class);
		doReturn(TokenTypes.ARRAY_DECLARATOR).when(mockAST).getType();
		check.visitToken(mockAST);
		assertEquals(1, check.halsteadVocabulary);
		assertEquals(1, check.halsteadLength);
		
		// call visitToken again to go into false branch. halsteadLength should 
		// increment again, vocabulary should not
		check.visitToken(mockAST);
		assertEquals(2, check.halsteadLength);
		assertEquals(1, check.halsteadVocabulary);
	}
	
	@Test
	public void beginTreeTest() {
		HalsteadVolumeCheck check = new HalsteadVolumeCheck();
		DetailAST mockAST = mock(DetailAST.class);
		check.beginTree(mockAST);
		
		assertEquals(true, check.visitedTokensAST.isEmpty());
		assertEquals(0, check.halsteadLength);
		assertEquals(0, check.halsteadVocabulary);
	}
}
