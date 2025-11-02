package lotto;

import java.util.Arrays;
import java.util.List;

public class LottoParser {

    private static final String REGEXP_NUMBER = "^[1-9][0-9]*$";
    private static final String REGEXP_WINNING_NUMBER = "^([1-9][0-9]*)(,[1-9][0-9]*)*$";

    public static int parsePurchasePrice(String input) {
        if (isNullOrBlank(input)) {
            throw new IllegalArgumentException(ErrorMessage.NULL_OR_BLANK_INPUT.getMessage());
        }
        if (!isNumber(input)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_PRICE.getMessage());
        }
        int purchasePrice = Integer.parseInt(input);
        int validPrice = purchasePrice % LottoMachine.LOTTO_PRICE;
        if (validPrice != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_UNIT_PURCHASE_PRICE.getMessage());
        }
        return purchasePrice;
    }

    public static List<Integer> parseWinningNumber(String input) {
        if (isNullOrBlank(input)) {
            throw new IllegalArgumentException(ErrorMessage.NULL_OR_BLANK_INPUT.getMessage());
        }
        if (!input.matches(REGEXP_WINNING_NUMBER)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_WINNING_NUMBER.getMessage());
        }
        List<Integer> splitedNumbers = Arrays.stream(input.split(",")).map(Integer::parseInt).toList();
        if (splitedNumbers.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_SIZE_LOTTO.getMessage());
        }
        if (!isInRangeNumbers(splitedNumbers)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_RANGE_WINNING_NUMBER.getMessage());
        }
        return splitedNumbers;
    }

    public static int parseBonusNumber(String input) {
        if (isNullOrBlank(input)) {
            throw new IllegalArgumentException(ErrorMessage.NULL_OR_BLANK_INPUT.getMessage());
        }
        if (!isNumber(input)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_BONUS_NUMBER.getMessage());
        }
        int bonusNumber = Integer.parseInt(input);
        if (!isInRangeNumber(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_RANGE_BONUS_NUMBER.getMessage());
        }
        return bonusNumber;
    }

    private static boolean isNullOrBlank(String input) {
        return input == null || input.isBlank();
    }

    private static boolean isNumber(String input) {
        return input.matches(REGEXP_NUMBER);
    }

    private static boolean isInRangeNumbers(List<Integer> numbers) {
        return numbers.stream().allMatch(LottoParser::isInRangeNumber);
    }

    private static boolean isInRangeNumber(Integer number) {
        return number >= 1 && number <= 45;
    }
}
