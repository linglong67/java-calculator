package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StringTest {

    @Test
    @DisplayName("split")
    void split() {
        String twoNumstr = "1,2";
        String oneNumstr = "1";

        assertThat(twoNumstr.split(",")).contains("1", "2");
        assertThat(oneNumstr.split(",")).containsExactly("1");
    }

    @Test
    @DisplayName("substring")
    void subString() {
        String str = "(1,2)";
        assertThat(str.substring(1, str.length() - 1)).isEqualTo("1,2");
    }

    @Test
    @DisplayName("charAt-Exception")
    void charAt() {
        String str = "abc";
        assertThatThrownBy(() -> {
            str.charAt(3);
        }).isInstanceOf(IndexOutOfBoundsException.class)
          .hasMessageContaining("String index out of range: 3");
    }
}
