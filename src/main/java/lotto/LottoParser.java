package lotto;

public class LottoParser {

    private static final String REGEXP_NUMBER = "^[1-9][0-9]*$";

    public static int parsePurchasePrice(String input) {
        if (isNullOrBlank(input)) {
            throw new IllegalArgumentException(ErrorMessage.NULL_OR_BLANK_INPUT.getMessage());
        }
        if (!isNumber(input)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_PRICE.getMessage());
        }
        int purchasePrice = Integer.parseInt(input);
        int validPrice = purchasePrice % 1000;
        if (validPrice == 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_UNIT_PURCHASE_PRICE.getMessage());
        }
        return purchasePrice;
    }

    private static boolean isNullOrBlank(String input) {
        return input == null || input.isBlank();
    }

    private static boolean isNumber(String input) {
        return input.matches(REGEXP_NUMBER);
    }

}
