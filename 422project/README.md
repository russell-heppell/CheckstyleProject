# 422Project

code and test explanation. state assumptions about ambiguous metrics, explain how tokens were defined,
any known inaccuracies, etc.

- CommentCheck.java – Counts every multi-line and every block comment begin token. 
	Test: Verifies the tokens arrays are returning as expected, calls visitToken with a mockedAST and 	
	verifies our commentCount is 1. Also calls beginTree to verify our variables are reset accordingly.
 
- CommentLinesCheck.java – Counts every single line comment token and if it sees a multi-line begin token,
saves the line number and waits for the next multi-line end token and calculates the number of lines in 
the block and adds to the total count.
	Test: Verifies the tokens arrays are returning as expected. For testing every branch of visitToken,
 	initially mock the AST to return a Single line comment token when getType is called, and verify
 	the count is correct. Then mock it to return a block-comment begin token, as well as a line
 	number of 1 when getLineNo is called. Then mock it to return a block comment end token with a
 	line number of 4, so we can check our comment count with both single line and multi-line
 	comments. We then assert that our comment count is 4, and then call visitToken with an
 	unrelated token, so that the 4th branch can be executed. Then verify our count is unchanged.
 	Also calls beginTree to verify our variables are reset accordingly.

- ExpressionsCheck.java – Counts every EXPR token. 
	Test: Verifies the tokens arrays are returning as expected and calls visitToken with a mockedAST
 	and verifies that our expression count is 1. Also calls beginTree to verify our variable is reset
 	accordingly.

- HalsteadDifficultyCheck.java – Finds the count of unique operators, unique operands, and total operands and
calculates Difficulty = ((uniqueOperators / 2) * totalOperands) / uniqueOperands. Operators and operands 
were determined with the list found on two GeeksForGeeks articles provided by Professor Zeng. To find these
3 counts, two token arrays - Operators and Operands were used that held the list of the two types, so inside 
visitToken we knew which token type we were looking at. Another two arrays were used to hold tokens that were 
already visited so I could calculate the unique operators and operands. 
	Test: Verifies the tokens arrays are returning as expected. To enter every branch of visitToken, I
 	mocked the AST to return certain types of tokens (operator, operand, repeating
 	operators/operands) so that all the cases would be executed. To test the final branch, an
 	unrelated token was returned, and all variables were verified to be unchanged. BeginTree was
 	also tested to verify that all changed variables were reset accordingly. 

- HalsteadEffortCheck.java – Calculates the Halstead Difficulty, Halstead Volume, and outputs Halstead 
Effort = Difficulty * Volume. Volume is calculated as HalsteadLength * LOG(HalsteadVocab)2, where 
HalsteadLength is total count of operators and operands, and halsteadVocab is the sum of unique operators
 and unique operands. The HalsteadDifficulty is calculated in visitToken the same as mentioned before, and
 the HalsteadVocabulary uses an array to store the visited tokens, so it’s only incremented when the token 
is unique. HaslteadLength is incremented always. 
	Test: Verifies the tokens arrays are returning as expected. To test every branch of visitToken,
 	again we had to mock the return tokenType to go into the different cases, whether it’s a
 	repeating token, new token, or unrelated token. BeginTree was also tested to verify that all
 	changed variables were reset accordingly. 

- HalsteadLengthCheck.java – Returns the total sum of operators and operands. Operators and operands were 
determined with the lists found on two GeeksForGeeks articles provided by Professor Zeng.
	Test: Verifies the tokens arrays are returning as expected. On every visitToken call, 
	a variable halsteadLength is incremented to count the total number of operators and operands, 
	so the test mocks a detailAST and calls visitToken, expecting the halsteadLength variable to be 1. 
	BeginTree is also tested to verify that the variable is reset correctly. 

- HalsteadVocabularyCheck.java – Returns the total sum of unique operators and unique operands. A list of 
tokens is used to keep track of tokens that have already been seen to ensure that were only counting unique 
ones. In visitToken, it checks the list and if the list does not contain the token, it increments the 
halsteadVocabulary variable and adds the token to our list. 
	Test: Verifies the tokens arrays are returning as expected. To make sure only unique tokens are 
	counted, a mockAST is created and a call to visitToken is made, making sure a valid token is returned 
	when a call to getType is made. Then we assert that the count is 1, then call visitToken again with 
	the same token, asserting that the count is unchanged because the token is no longer unique. 
	beginTree is also tested, verifying that our variables are properly reset. 

- HalsteadVolumeCheck.java – finds the Halstead vocabulary and the Halstead length, then calculates the Halstead
Volume = Halstead length * log2(Halstead vocabulary) inside of the finishTree function. Halstead length and 
vocabulary are determined the same as their individual checks. 
	Test: Verifies the tokens arrays are returning as expected. VisitToken is tested by mocking a detailAST 
	as usual and having the return of getType be a valid token. The initial call to visitToken causes both 
	variables (vocabulary and length) to both increment, while the second call with the same token only 
	increments the length, and vocabulary stays the same (no longer a unique token). BeginTree is also 
	tested by verifying that our variables are properly reset. 

- LoopingStatementCheck.java - Counts the number of looping statements (for loop, while, do-while). 
	Test: Verifies the tokens arrays are returning as expected. Since the visitToken implementation 
	used a 3 statement OR condition (one for each token), the test for visitToken ran a call to visitToken
	with all 3 token types, to go through every branch of the if. For the last branch, an irrelevant 
	token was used to ensure that the count wasnt changed. BeginTree is also tested by verifying that
	our variables are properly reset. 

- OperandsCheck.java - Counts the number of operands in a java file. Again, the chosen tokens for operands were
determined based on two GeeksForGeeks articles provided by Professor Zeng.
	Test: Verifies the tokens arrays are returning as expected. For testing VisitToken, a mockAST is created
	like usual, and then a call to visitToken is made, verifying that the operand count was incremented 
	accordingly. BeginTree is also tested by verifying that our variables are properly reset. 

- OperatorsCheck.java - Counts the number of operators in a java file. Again, the chosen tokens for operators were
determined based on two GeeksForGeeks articles provided by Professor Zeng.
	Test: Verifies the tokens arrays are returning as expected. For testing VisitToken, a mockAST is created
	like usual, and then a call to visitToken is made, verifying that the operator count was incremented 
	accordingly. BeginTree is also tested by verifying that our variables are properly reset. 

