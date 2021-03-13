package Shalazary;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class MathExpressionUtility {

    public static String[] tokenize(String expression) {
        if (expression.isEmpty())
            return new String[0];
    
        expression = expression.replace(" ", "");
        String[] tokenizedExpresion = expression.split("(?<=[-+*/^()])|(?=[-+*/^()])");

        return tokenizedExpresion;
    }

    public static Set<String> getVariables(String[] tokenizedExpression) {
        Set<String> arguments = new HashSet<String>();
        for (String token : tokenizedExpression)
            if (Pattern.matches("^[a-zA-Z_][a-zA-Z_0-9]*$", token))
                arguments.add(token);

        return arguments;
    }

    public static Set<String> getVariables(String expression) {
        return getVariables(tokenize(expression));
    }
}
