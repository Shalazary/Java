package Shalazary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class MathExpressionUtility {

    public static String[] tokenize(String expression, Collection<String> operators) {
        String op = "";
        for (String item : operators)
            op += item;

        expression = expression.replace(" ", "");
        String[] tokenizedExpresion = expression.split("(?<=[op()])|(?=[op()])".replace("op", op));

        return tokenizedExpresion;
    }

    public static String[] toPostfix(String[] tokenizedExpression, Map<String, Integer> operatorsPriority) {
        Stack<String> operatorsStack = new Stack<String>();
        List<String> result = new ArrayList<String>();

        operatorsPriority.put("(", 0);

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

    public static String[] toPostfix(String expression, Map<String, Integer> operatorsPriority) {
        Set<String> operators = operatorsPriority.keySet();
        return toPostfix(tokenize(expression, operators), operatorsPriority);
    }
}
