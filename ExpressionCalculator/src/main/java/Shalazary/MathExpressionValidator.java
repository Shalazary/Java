package Shalazary;

import java.util.regex.Pattern;

public class MathExpressionValidator {
    public static boolean tokensValidate(String[] tokenizedExpression) {
        for (String token : tokenizedExpression)
            if (!Pattern.matches("([a-zA-Z_][a-zA-Z_0-9]*)|([0-9]*)|([0-9]+\\.[0-9]+)|([-+*/^()])", token))
                return false;

        return true;
    }

    public static boolean tokensValidate(String expression) {
        return tokensValidate(MathExpressionUtility.tokenize(expression));
    }

    public static boolean brackedBalanceValidate(String[] tokenizedExpression) {
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

    public static boolean brackedBalanceValidate(String expression) {
        return brackedBalanceValidate(MathExpressionUtility.tokenize(expression));
    }

    public static boolean validate(String[] tokenizedExpression){
        return true;
    }

    public static boolean validate(String expression){
        return true;
    }
}
