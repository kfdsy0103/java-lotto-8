package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoParserTest {

    @DisplayName("구입 금액이 null 또는 공백이면 예외가 발생한다.")
    @Test
    void 구입금액_null_또는_공백_예외() {
        // given
        String nullInput = null;
        String blankInput = " ";

        // when & then
        assertThatThrownBy(() -> LottoParser.parsePurchasePrice(nullInput))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> LottoParser.parsePurchasePrice(blankInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 숫자가 아니면 예외가 발생한다.")
    @Test
    void 구입금액_숫자아님_예외() {
        // given
        String input = "abc";

        // when & then
        assertThatThrownBy(() -> LottoParser.parsePurchasePrice(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 1000원 단위가 아니면 예외가 발생한다.")
    @Test
    void 구입금액_단위_예외() {
        // given
        String input = "1500";

        // when & then
        assertThatThrownBy(() -> LottoParser.parsePurchasePrice(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("유효한 구입 금액이면 정상적으로 파싱된다.")
    @Test
    void 구입금액_정상_파싱() {
        // given
        String input = "3000";

        // when
        int result = LottoParser.parsePurchasePrice(input);

        // then
        assertThat(result).isEqualTo(3000);
    }
}
