import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MainTest {

	@ParameterizedTest
	@CsvSource({"test,TEST", "tEst,TEST", "Java,JAVA"})
	void toUpperCase_ShouldGenerateTheExpectedUppercaseValue(String input, String expected) {
	    String actualValue = input.toUpperCase();
	    assertEquals(expected, actualValue);
	}
}
