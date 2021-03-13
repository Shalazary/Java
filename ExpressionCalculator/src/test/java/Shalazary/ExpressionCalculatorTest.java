package Shalazary;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

public class ExpressionCalculatorTest {
    ExpressionCalculator calculator = new ExpressionCalculator();

    @Test
    public void expressionCalculatorWithInteger() {
        String expression = "1 + (2 / 4) ^ 2 - 1 * 5";
        calculator.setExpression(expression);

        Double expected = -3.75;
        Double actual = calculator.compute();

        assertEquals(expected, actual, 0);
    }

    @Test
    public void expressionCalculatorWithDouble() {
        String expression = "1 + (0.5) ^ 2 - 5.0";
        calculator.setExpression(expression);

        Double expected = -3.75;
        Double actual = calculator.compute();

        assertEquals(expected, actual, 0);
    }

    @Test
    public void expressionCalculatorWithVariables() {
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
}