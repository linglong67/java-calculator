package study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SetTest {
    private Set<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }

    @Test
    @DisplayName("Set 사이즈")
    void getSize() {
        assertThat(numbers.size()).isEqualTo(3);
    }

    @DisplayName("Set 포함값 체크")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void contains(Integer number) {
        assertThat(numbers.contains(number)).isTrue();
    }

    @DisplayName("Set 포함여부 체크")
    @ParameterizedTest
    @CsvSource(value = {"1:true", "2:true", "3:true", "4:false", "5:false"}, delimiter = ':')
    void contains(int input, boolean expected) {
        assertEquals(expected, numbers.contains(input));
    }
}
