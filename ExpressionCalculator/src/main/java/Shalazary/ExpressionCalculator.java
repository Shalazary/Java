package Shalazary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * Class for calculating string expressions with standard math operations and
 * variables
 */
public class ExpressionCalculator {
    private String ExpressionString;
    private String[] TokenizedExpression;
    private String[] PostfixExpression;
    /** Table with names of variables and his values */
    private Map<String, Double> VariablesTable = new HashMap<String, Double>();

    private static final HashMap<String, Integer> OperatorsPriority = new HashMap<String, Integer>() {
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

    /**
     * Transforms tokenized expression to postfix form
     * 
     * @param tokenizedExpression - splited expression
     * @return returns tokenized postfix expression
     */
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
            } else if (!OperatorsPriority.containsKey(token)) {
                result.add(token);
            } else {
                while (!operatorsStack.isEmpty()
                        && OperatorsPriority.get(operatorsStack.peek()) >= OperatorsPriority.get(token))
                    result.add(operatorsStack.pop());

                operatorsStack.push(token);
            }
        }

        while (!operatorsStack.isEmpty())
            result.add(operatorsStack.pop());

        return result.toArray(new String[0]);
    }

    /**
     * Constructor - creating object to calculate an expression
     * 
     * @param expression - expression to calculate
     * @throws InvalidExpressionFormatException - throws if expression has invalid
     *                                          format
     */
    public ExpressionCalculator(String expression) throws InvalidExpressionFormatException {
        setExpression(expression);
    }

    /**
     * Constructor - creating object with default expression (f = 0)
     */
    public ExpressionCalculator() {
        ExpressionString = "0";
        TokenizedExpression = new String[] { "0" };
        PostfixExpression = new String[] { "0" };
        VariablesTable.clear();
    }

    /**
     * Set new expression for calculator
     * 
     * @param expression - string with expression
     * @throws InvalidExpressionFormatException - throws if expression has invalid
     *                                          format
     */
    public void setExpression(String expression) throws InvalidExpressionFormatException {
        if (!MathExpressionValidator.validate(expression))
            throw new InvalidExpressionFormatException();

        ExpressionString = expression;
        TokenizedExpression = MathExpressionUtility.tokenize(ExpressionString);
        PostfixExpression = toPostfix(TokenizedExpression);
        Set<String> vars = MathExpressionUtility.getVariables(TokenizedExpression);
        VariablesTable.clear();
        for (String var : vars)
            VariablesTable.put(var, 0.0);
    }

    /**
     * Returns table with names and values of variables of seated expression.
     * Strongly recommended not to add or remove values to table
     * 
     * @return returns map with variables
     */
    public Map<String, Double> getVariablesTable() {
        return VariablesTable;
    }

    /**
     * Computes value of expression with values of variables from Variable Table.
     * For not seated variables uses zero value.
     * 
     * @return returns expression value
     */
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
