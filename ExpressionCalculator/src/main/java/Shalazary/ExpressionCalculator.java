package Shalazary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Pattern;

public class ExpressionCalculator {
    private String ExpressionString;
    private String[] TokenizedExpression;
    private String[] PostfixExpression;
    private Map<String, Double> VariablesTable = new HashMap<String, Double>();

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

    private static String[] toPostfix(String[] tokenizedExpression) {
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

        return result.toArray(new String[0]);
    }

    public ExpressionCalculator(String expression) {
        setExpression(expression);
    }

    public ExpressionCalculator(){
        setExpression("0");
    }

    public void setExpression(String expression) {
        /*if (!MathExpressionValidator.validate(expression))
            throw new InvalidExpressionException(); */
        ExpressionString = expression;
        TokenizedExpression = MathExpressionUtility.tokenize(ExpressionString);
        PostfixExpression = toPostfix(TokenizedExpression);
        Set<String> vars = MathExpressionUtility.getVariables(TokenizedExpression);
        VariablesTable.clear();
        for (String var : vars)
            VariablesTable.put(var, 0.0);
    }

    public void setVariablesValue(Map<String, Double> variablesTable) {
        VariablesTable = variablesTable;
    }

    public Map<String, Double> getVariablesTable() {
        return VariablesTable;
    }

    public Double compute() {
        String[] postfixExpressionToCompute = new String[PostfixExpression.length];
        for (int i = 0; i < PostfixExpression.length; i++) {
            if (VariablesTable.containsKey(PostfixExpression[i]))
                postfixExpressionToCompute[i] = VariablesTable.get(PostfixExpression[i]).toString();
            else
                postfixExpressionToCompute[i] = PostfixExpression[i];
        }
        Stack<String> s = new Stack<String>();
        for (String token : postfixExpressionToCompute) {
            if (Pattern.matches("[-+*/^]", token)) {
                Double result = 0.0;
                Double a = Double.parseDouble(s.pop());
                if ((token.equals("-") || token.equals("+")) && (s.empty() || Pattern.matches("[-+*/^]", s.peek()))) {
                    if (token.equals("-"))
                        result = -a;
                    else if (token.equals("+"))
                        result = a;
                } else {
                    Double b = Double.parseDouble(s.pop());
                    if (token.equals("-"))
                        result = b - a;
                    else if (token.equals("+"))
                        result = b + a;
                    else if (token.equals("*"))
                        result = b * a;
                    else if (token.equals("/"))
                        result = b / a;
                    else if (token.equals("^"))
                        result = Math.pow(b, a);
                }
                s.push(result.toString());
            } else {
                s.push(token);
            }
        }
        return Double.parseDouble(s.pop());
    }
}
