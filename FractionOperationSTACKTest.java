import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FractionOperationStackTest {
    //простые тесты для каждого знака
    @Test
    void sumTest() throws InvalidExpression{

        FractionOperationStack expression = new FractionOperationStack(
                FractionOperationStack.convertToReversePolishNotation("1/2 + 1/3"));
        expression.solve();
        assertEquals(expression.getAnswer().toString(), "5/6");
    }

    @Test
    void subtractTest() throws InvalidExpression{

        FractionOperationStack expression = new FractionOperationStack(
                FractionOperationStack.convertToReversePolishNotation("1/2 - 1/3"));
        expression.solve();
        assertEquals(expression.getAnswer().toString(), "1/6");
    }

    @Test()
    void multiplyTest() throws InvalidExpression{

        FractionOperationStack expression = new FractionOperationStack(
                FractionOperationStack.convertToReversePolishNotation("1/2 * 1/3"));
        expression.solve();
        assertEquals(expression.getAnswer().toString(), "1/6");
    }

    @Test
    void divTest() throws InvalidExpression{

        FractionOperationStack expression = new FractionOperationStack(
                FractionOperationStack.convertToReversePolishNotation("1/2 : 1/2"));
        expression.solve();
        assertEquals(expression.getAnswer().toString(), "1/1");
    }

    //проверка на приоритетность
    @Test
    void equalPriorityTest1() throws InvalidExpression{

        FractionOperationStack expression = new FractionOperationStack(
                FractionOperationStack.convertToReversePolishNotation("1/2 + 1/2 + 1/2"));
        expression.solve();
        assertEquals(expression.getAnswer().toString(), "3/2");
    }

    @Test
    void equalPriorityTest2() throws InvalidExpression{

        FractionOperationStack expression = new FractionOperationStack(
                FractionOperationStack.convertToReversePolishNotation("1/2 * 1/2 : 1/2"));
        expression.solve();
        assertEquals(expression.getAnswer().toString(), "1/2");
    }

    @Test
    void unequalPriorityTest1() throws InvalidExpression{

        FractionOperationStack expression = new FractionOperationStack(
                FractionOperationStack.convertToReversePolishNotation("1/2 + 1/2 * 1/2"));
        expression.solve();
        assertEquals(expression.getAnswer().toString(), "3/4");
    }

    @Test()
    void unequalPriorityTest2() throws InvalidExpression{

        FractionOperationStack expression = new FractionOperationStack(
                FractionOperationStack.convertToReversePolishNotation("( 1/2 + 1/2 ) * 1/2"));
        expression.solve();
        assertEquals(expression.getAnswer().toString(), "1/2");
    }
    //проверка на неисправности пользовательского ввода
    @Test
    void invalidExpressionExceptionTest1(){
        FractionOperationStack expression = new FractionOperationStack(
                FractionOperationStack.convertToReversePolishNotation("asd + 1/2 + 1/2"));
        assertThrows(InvalidExpression.class, () -> expression.solve());
    }

    @Test
    void invalidExpressionExceptionTest2(){
        FractionOperationStack expression = new FractionOperationStack(
                FractionOperationStack.convertToReversePolishNotation("123/123/123 + 1/2 + 1/2"));
        assertThrows(InvalidExpression.class, () -> expression.solve());
    }

    @Test
    void invalidExpressionExceptionTest3(){

        assertThrows(InvalidExpression.class, () ->{
            FractionOperationStack expression = new FractionOperationStack(
                    FractionOperationStack.convertToReversePolishNotation("1/2 + 1/2 * 1/0"));
            expression.solve();
        });
    }
}