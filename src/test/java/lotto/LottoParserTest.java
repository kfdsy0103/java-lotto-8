package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
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

    @DisplayName("당첨 번호 입력이 null 또는 공백이면 예외가 발생한다.")
    @Test
    void 당첨번호_null_또는_공백_예외() {
        // given
        String nullInput = null;
        String blankInput = " ";

        // when & then
        assertThatThrownBy(() -> LottoParser.parseWinningNumber(nullInput))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> LottoParser.parseWinningNumber(blankInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호에 공백이나 문자가 포함되면 예외가 발생한다.")
    @Test
    void 당첨번호_형식_예외() {
        // given
        String inputWithSpace = "1, 2, 3, 4, 5, 6";
        String inputWithLetter = "1,2,3,4,5,a";

        // when & then
        assertThatThrownBy(() -> LottoParser.parseWinningNumber(inputWithSpace))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> LottoParser.parseWinningNumber(inputWithLetter))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호가 6개가 아니면 예외가 발생한다.")
    @Test
    void 당첨번호_개수_예외() {
        // given
        String lessNumbers = "1,2,3,4,5";
        String moreNumbers = "1,2,3,4,5,6,7";

        // when & then
        assertThatThrownBy(() -> LottoParser.parseWinningNumber(lessNumbers))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> LottoParser.parseWinningNumber(moreNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호가 1~45 범위를 벗어나면 예외가 발생한다.")
    @Test
    void 당첨번호_범위_예외() {
        // given
        String lowRange = "0,2,3,4,5,6";
        String highRange = "1,2,3,4,5,46";

        // when & then
        assertThatThrownBy(() -> LottoParser.parseWinningNumber(lowRange))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> LottoParser.parseWinningNumber(highRange))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("유효한 당첨 번호면 올바르게 파싱된다.")
    @Test
    void 당첨번호_정상_파싱() {
        // given
        String input = "1,2,3,4,5,6";

        // when
        List<Integer> result = LottoParser.parseWinningNumber(input);

        // then
        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("보너스 번호 입력이 null 또는 공백이면 예외가 발생한다.")
    @Test
    void 보너스번호_null_또는_공백_예외() {
        // given
        String nullInput = null;
        String blankInput = " ";

        // when & then
        assertThatThrownBy(() -> LottoParser.parseBonusNumber(nullInput))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> LottoParser.parseBonusNumber(blankInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 숫자가 아니면 예외가 발생한다.")
    @Test
    void 보너스번호_숫자아님_예외() {
        // given
        String input = "abc";

        // when & then
        assertThatThrownBy(() -> LottoParser.parseBonusNumber(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 1~45 범위를 벗어나면 예외가 발생한다.")
    @Test
    void 보너스번호_범위_예외() {
        // given
        String lowRange = "0";
        String highRange = "46";

        // when & then
        assertThatThrownBy(() -> LottoParser.parseBonusNumber(lowRange))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> LottoParser.parseBonusNumber(highRange))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("유효한 보너스 번호면 정상적으로 파싱된다.")
    @Test
    void 보너스번호_정상_파싱() {
        // given
        String input = "10";

        // when
        int result = LottoParser.parseBonusNumber(input);

        // then
        assertThat(result).isEqualTo(10);
    }
}
