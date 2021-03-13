package Shalazary;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MathExpressionValidatorTest {
    @Test
    public void successfulTokensValidateTest() {
        String expression = "a + b - 18 * (_some / some_1) ^ some001";
        assertTrue(MathExpressionValidator.tokensValidate(expression));
    }

    @Test
    public void emptyExpressionTest(){
        String expression = "";
        assertTrue(MathExpressionValidator.tokensValidate(expression));
    }

    @Test
    public void wrongVariableNameTest() {
        String expression = "a + b - 18 * (01some / some_1) ^ some001";
        assertFalse(MathExpressionValidator.tokensValidate(expression));
    }

    @Test
    public void wrongOperatorTest() {
        String expression = "a + b - 18 ! (_some / some_1) ^ some001";
        assertFalse(MathExpressionValidator.tokensValidate(expression));
    }

    @Test
    public void successfulBrackedBalanceValidateTest() {
        String expression = "(a + b - c + (v / c + (8)) ^ 5) + (1 + 2) - 15 ";
        assertTrue(MathExpressionValidator.brackedBalanceValidate(expression));
    }

    @Test
    public void openBrackedDisbalanceTest() {
        String expression = "(a + b - c + (v / c + ((8)) ^ 5) + (1 + 2) - 15 ";
        assertFalse(MathExpressionValidator.brackedBalanceValidate(expression));
    }

    @Test
    public void closeBrackedDisbalanceTest() {
        String expression = "(a + b) - c + (v / c + (8)) ^ 5) + (1 + 2) - 15 ";
        assertFalse(MathExpressionValidator.brackedBalanceValidate(expression));
    }

    @Test
    public void wrongBrackedOrderTest() {
        String expression = "(a)) + ((b)";
        assertFalse(MathExpressionValidator.brackedBalanceValidate(expression));
    }
}
