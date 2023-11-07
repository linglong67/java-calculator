package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CalculatorTest {
    private static final String DELIMITER_REGEX = "//[^0-9]\n";
    List<String> delimiters = new ArrayList<>(Arrays.asList(",", ":"));

    String replaceStr(String string) {
        Matcher matcher = Pattern.compile(DELIMITER_REGEX).matcher(string);
        if (matcher.find()) {
            delimiters.add(string.substring(2, 3));
            string = string.replaceAll(DELIMITER_REGEX, "");
        }

        return string;
    }

    int sum(String string) {
        int sum = 0;
        for(String s : string.split(delimiters.toString())) {
            int num = s.isBlank() ? 0 : Integer.parseInt(s);
            sum += num;
        }

        return sum;
    }

    @DisplayName("성공 -> 입력값이 없는 경우 0")
    @ParameterizedTest
    @ValueSource(strings = {""})
    void calculatorNullSuccess(String string) {
        assertThat(sum(replaceStr(string))).isZero();
    }

    @DisplayName("성공 -> 정상적인 입력값을 넣은 경우")
    @ParameterizedTest
    @ValueSource(strings = {"1,2,3", "1,2,3", "1,2:3", "//;\n1;2;3", "//|\n1|2|3"})
    void calculatorSuccess(String string) {
        assertThat(sum(replaceStr(string))).isEqualTo(6);
    }

    @DisplayName("실패 예외처리 -> 비정상적인 입력값을 넣은 경우")
    @ParameterizedTest
    @ValueSource(strings = {"//'\n-1'1'd", "//0\n10203", "1,2,d"})
    void calculatorFail(String string) {
        assertThatThrownBy(() -> {
            sum(replaceStr(string));
        }).isInstanceOf(NumberFormatException.class)
          .hasMessageContaining("For input string");
    }
}
