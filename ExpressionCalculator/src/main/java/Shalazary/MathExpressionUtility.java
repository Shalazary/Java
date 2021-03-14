package Shalazary;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Class with utility functions for mathematical expressions
 */
public class MathExpressionUtility {

    /**
     * Function for splitting a math expression into tokens by standard operations
     * and brackets
     * 
     * @param expression - string with math expression
     * @return returns array of tokens of the input expression
     */
    public static String[] tokenize(String expression) {
        if (expression.isEmpty())
            return new String[0];

        expression = expression.replace(" ", "");
        String[] tokenizedExpresion = expression.split("(?<=[-+*/^()])|(?=[-+*/^()])");

        return tokenizedExpresion;
    }

    /**
     * Function for extracting names of variables from tokenized math expression
     * 
     * @param tokenizedExpression - math expression splitted into tokens
     * @return returns set of names of variables
     */
    public static Set<String> getVariables(String[] tokenizedExpression) {
        Set<String> arguments = new HashSet<String>();
        for (String token : tokenizedExpression)
            if (Pattern.matches("^[a-zA-Z_][a-zA-Z_0-9]*$", token))
                arguments.add(token);

        return arguments;
    }

    /**
     * Function for extracting names of variables from string with math expression
     * 
     * @param expression - string with math expression
     * @return returns set of names of variables
     */
    public static Set<String> getVariables(String expression) {
        return getVariables(tokenize(expression));
    }
}
