import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class CalculatorTest {

    @Test
    public void test_add() {
        Calculator calculator = new Calculator();
        int num1 = 3;
        int num2 = 5;
        int sum1 = calculator.add(num1, num2);
        System.out.println("sum1: " + sum1);

        int num5 = calculator.getTemp(getText());

        calculator.setTemp(13);
        int temp = calculator.getTemp();
        System.out.println("[calculator]temp: " + temp);

        int num3 = 1;
        int num4 = 2;
        int sum2 = calculator.add(num3, num4);
        System.out.println("sum2: " + sum2);

        assertThat(sum1, is(8));
    }

    private String getText() {
        return "good";
    }


}
