package Shalazary;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class MathExpressionUtilityTest {

    @Test
    public void tokenizeEmptyExpressionTest() {
        String expression = "";
        String[] expecteds = new String[0];
        String[] actuals = MathExpressionUtility.tokenize(expression);
        assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void tokenizeExpressionWithInteger() {
        String expression = "1 + 2 - (3 * 4) / (5 ^ 6)";
        String[] expecteds = { "1", "+", "2", "-", "(", "3", "*", "4", ")", "/", "(", "5", "^", "6", ")" };
        String[] actuals = MathExpressionUtility.tokenize(expression);
        assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void tokenizeExpressionWithDouble() {
        String expression = "1.1 + 2 - (3.3 * 4) / (5 ^ 6.6)";
        String[] expecteds = { "1.1", "+", "2", "-", "(", "3.3", "*", "4", ")", "/", "(", "5", "^", "6.6", ")" };
        String[] actuals = MathExpressionUtility.tokenize(expression);
        assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void tokenizeExpressionWithVariables() {
        String expression = "_some2 + 2 - (3.3 * _some_) / (_some1 ^ 6.6)";
        String[] expecteds = { "_some2", "+", "2", "-", "(", "3.3", "*", "_some_", ")", "/", "(", "_some1", "^", "6.6",
                ")" };
        String[] actuals = MathExpressionUtility.tokenize(expression);
        assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void getVariablesTest() {
        String expression = "_some2 + 2 - (3.3 * _some_) / (_some1 ^ 6.6)";
        Set<String> expecteds = new HashSet<>(Arrays.asList(new String[] { "_some2", "_some_", "_some1" }));
        Set<String> actuals = MathExpressionUtility.getVariables(expression);
        assertArrayEquals(expecteds.toArray(new String[0]), actuals.toArray(new String[0]));
    }

    @Test
    public void getVariablesWithNoVariables() {
        String expression = "1 + 2";
        Set<String> expecteds = new HashSet<>(Arrays.asList(new String[0]));
        Set<String> actuals = MathExpressionUtility.getVariables(expression);
        assertArrayEquals(expecteds.toArray(new String[0]), actuals.toArray(new String[0]));
    }

    @Test
    public void getVariablesFromEmptyExpression() {
        String expression = "";
        Set<String> expecteds = new HashSet<>(Arrays.asList(new String[0]));
        Set<String> actuals = MathExpressionUtility.getVariables(expression);
        assertArrayEquals(expecteds.toArray(new String[0]), actuals.toArray(new String[0]));
    }
}
