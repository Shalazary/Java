package Shalazary;

import java.util.regex.Pattern;

/**
 * Class with validators for math expressions
 */
public class MathExpressionValidator {

    /**
     * Validator for checking tokens in expression Token should be: operation,
     * bracket, name of variable, integer or double number
     * 
     * @param tokenizedExpression - splited expression
     * @return returns result of validation
     */
    public static boolean tokensValidate(String[] tokenizedExpression) {
        for (String token : tokenizedExpression)
            if (!Pattern.matches("([a-zA-Z_][a-zA-Z_0-9]*)|([0-9]*)|([0-9]+\\.[0-9]+)|([-+*/^()])", token))
                return false;

        return true;
    }

    /**
     * Validator for checking tokens in expression. Token should be: operation,
     * bracket, name of variable, integer or double number
     * 
     * @param expression - string with expression
     * @return returns result of validation
     */
    public static boolean tokensValidate(String expression) {
        return tokensValidate(MathExpressionUtility.tokenize(expression));
    }

    /**
     * Validator for checking the correct placement of brackets
     * 
     * @param tokenizedExpression - splited expression
     * @return returns result of validation
     */
    public static boolean bracketBalanceValidate(String[] tokenizedExpression) {
        int balance = 0;
        for (String token : tokenizedExpression) {
            if (token.equals("("))
                balance++;
            else if (token.equals(")"))
                balance--;
            if (balance < 0)
                return false;
        }
        return balance == 0;
    }

    /**
     * Validator for checking the correct placement of brackets
     * 
     * @param expression - string with expression
     * @return returns result of validation
     */
    public static boolean bracketBalanceValidate(String expression) {
        return bracketBalanceValidate(MathExpressionUtility.tokenize(expression));
    }

    // TODO add code for operationBalanceValidate

    /**
     * Validator for checking the correct placement of operators
     * 
     * @param tokenizedExpression - splited expression
     * @return returns result of validation
     */
    public static boolean operationBalanceValidate(String[] tokenizedExpression) {
        return true;
    }

    /**
     * Validator for checking the correct placement of operators
     * 
     * @param expression - string with expression
     * @return returns result of validation
     */
    public static boolean operationBalanceValidate(String expression) {
        return operationBalanceValidate(MathExpressionUtility.tokenize(expression));
    }

    /**
     * Full expression validator
     * 
     * @param tokenizedExpression - splited expression
     * @return returns result of validation
     */
    public static boolean validate(String[] tokenizedExpression) {
        return tokensValidate(tokenizedExpression) && bracketBalanceValidate(tokenizedExpression)
                && operationBalanceValidate(tokenizedExpression);
    }

    /**
     * Full expression validator
     * 
     * @param expression - string with expression
     * @returnr eturns result of validation
     */
    public static boolean validate(String expression) {
        return validate(MathExpressionUtility.tokenize(expression));
    }
}
