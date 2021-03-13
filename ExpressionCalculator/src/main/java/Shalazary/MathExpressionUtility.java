package Shalazary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class MathExpressionUtility {

    private static final HashMap<String, Integer> operatorsPriority = new HashMap<String, Integer>() {
        {
            put("(", 0);
            put(")", 1);
            put("+", 2);
            put("-", 2);
            put("*", 3);
            put("/", 3);
            put("^", 4);
        }
    };

    public static String[] tokenize(String expression) {
        expression = expression.replace(" ", "");
        String[] tokenizedExpresion = expression.split("(?<=[-+*/^()])|(?=[-+*/^()])");

        return tokenizedExpresion;
    }

    public static String[] toPostfix(String[] tokenizedExpression) {
        Stack<String> operatorsStack = new Stack<String>();
        List<String> result = new ArrayList<String>();

        for (String token : tokenizedExpression) {
            if (token.equals("(")) {
                operatorsStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorsStack.peek().equals("("))
                    result.add(operatorsStack.pop());
                operatorsStack.pop();
            } else if (!operatorsPriority.containsKey(token)) {
                result.add(token);
            } else {
                while (!operatorsStack.isEmpty()
                        && operatorsPriority.get(operatorsStack.peek()) >= operatorsPriority.get(token))
                    result.add(operatorsStack.pop());

                operatorsStack.push(token);
            }
        }

        while (!operatorsStack.isEmpty())
            result.add(operatorsStack.pop());

        String[] resultArray = new String[result.size()];
        resultArray = result.toArray(resultArray);
        return resultArray;
    }

    public static String[] toPostfix(String expression) {
        return toPostfix(tokenize(expression));
    }
}
