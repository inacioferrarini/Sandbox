package sandbox.inacio.codevalidator;

import java.io.Console;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
	
 	static final Logger logger = LoggerFactory.getLogger(App.class);

	Vector<Character> leftSideTokens = new Vector<Character>();
	Vector<Character> rightSideTokens = new Vector<Character>();	

	protected boolean isInputOK(final String[] input) {
		logger.debug("validating input expression: {}", input);
		return (input != null && input.length == 1);
	}

	protected void addValidationTokens() {
		leftSideTokens.add('{');
		leftSideTokens.add('[');
		leftSideTokens.add('(');
		logger.debug("leftSideTokens: {}", leftSideTokens.toString());
		
		rightSideTokens.add('}');
		rightSideTokens.add(']');
		rightSideTokens.add(')');		
		logger.debug("rightSideTokens: {}", rightSideTokens.toString());
	}
	
	protected boolean isTokenARelatedToTokenB(final Character tokenA, final Character tokenB) {
		return (leftSideTokens.indexOf(tokenA) == rightSideTokens.indexOf(tokenB));
	}
	
	protected boolean isExpressionValid(final String expr) {
		
		Vector<Character> tokensInExpression = new Vector<Character>();
		
		for (char c : expr.toCharArray()) {
			logger.debug("character to evaluate: {}", c);
			if (leftSideTokens.contains(c)) {
				tokensInExpression.add(c);
				logger.debug("added token {}. tokensInExpression now = {}", c, tokensInExpression);
			} else if (rightSideTokens.contains(c)) {
				if (tokensInExpression.isEmpty()) {
					logger.debug("tokensInExpression empty. No tokens to remove. Returning false.");
					return false;
				} else if (isTokenARelatedToTokenB(tokensInExpression.lastElement(), c)) {
					tokensInExpression.remove(tokensInExpression.size()-1);
					logger.debug("removed token {}. tokensInExpression now = {}", c, tokensInExpression);
				}
			}
		}
		
		return (tokensInExpression.size() == 0);
	}
	
	public static void main(String[] args) {
		Console c = System.console();
		
		App app = new App();
		
		if (!app.isInputOK(args)) {
			logger.error("Expression not informed");
			c.printf("please inform the expression between \".%n");
			return;
		}

		app.addValidationTokens();
		
		if (!app.isExpressionValid(args[0])) {
			logger.error("The expression \"{}\" is not valid.");
			c.printf("Expression is not valid.%n");
		} else {
			c.printf("Expression is valid.%n");
		}
		
	}

	
}
