package Shalazary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

public class ExpressionCalculatorTest {
    ExpressionCalculator calculator = new ExpressionCalculator();

    @Test
    public void expressionCalculatorWithInteger() throws InvalidExpressionFormatException {
        String expression = "1 + (2 / 4) ^ 2 - 1 * 5";
        calculator.setExpression(expression);

        Double expected = -3.75;
        Double actual = calculator.compute();

        assertEquals(expected, actual, 0);
    }

    @Test
    public void expressionCalculatorWithDouble() throws InvalidExpressionFormatException {
        String expression = "1 + (0.5) ^ 2 - 5.0";
        calculator.setExpression(expression);

        Double expected = -3.75;
        Double actual = calculator.compute();

        assertEquals(expected, actual, 0);
    }

    @Test
    public void expressionCalculatorWithVariables() throws InvalidExpressionFormatException {
        String expression = "1 + a ^ 2 - b / some";
        calculator.setExpression(expression);
        Map<String, Double> vars = calculator.getVariablesTable();
        vars.replace("a", 0.5);
        vars.replace("b", 10.0);
        vars.replace("some", 2.0);

        Double expected = -3.75;
        Double actual = calculator.compute();

        assertEquals(expected, actual, 0);
    }

    @Test
    public void expressionCalculatorForEmptyExpressionTest() throws InvalidExpressionFormatException {
        calculator = new ExpressionCalculator();

        Double expected = 0.0;
        Double actual = calculator.compute();

        assertEquals(expected, actual, 0);
    }

    @Test
    public void expressionCalculatorWithPartOfVariables() throws InvalidExpressionFormatException {
        String expression = "1 + a ^ 2 - b / some";
        calculator.setExpression(expression);
        Map<String, Double> vars = calculator.getVariablesTable();
        vars.replace("some", 1.0);

        Double expected = 1.0;
        Double actual = calculator.compute();

        assertEquals(expected, actual, 0);
    }

    @Test
    public void expressionCalculatorWithInvalidExpression() throws InvalidExpressionFormatException {
        String expression = "1 + 0a ^ 2 - b / some"; // invalid var name 0a
        boolean exceptionFlag = false;

        try {
            calculator.setExpression(expression);
        } catch (InvalidExpressionFormatException e) {
            exceptionFlag = true;
        }

        assertTrue(exceptionFlag);
    }
}