package lotto;

public enum ErrorMessage {
    NULL_OR_BLANK_INPUT("입력 값이 공백일 수는 없습니다."),
    INVALID_PURCHASE_PRICE("구입금액은 양수인 정수만 입력할 수 있습니다."),
    INVALID_UNIT_PURCHASE_PRICE("구입금액은 1000원 단위로 입력해야 합니다."),
    INVALID_WINNING_NUMBER("당첨 번호는 (,)로 구분된 1~45 사이의 수를 입력해야 합니다."),
    INVALID_RANGE_WINNING_NUMBER("당첨 번호는 1부터 45 사이의 숫자여야 합니다."),
    INVALID_BONUS_NUMBER("보너스 번호는 양수인 정수만 입력할 수 있습니다."),
    INVALID_RANGE_BONUS_NUMBER("보너스 번호는 1부터 45 사이의 숫자여야 합니다."),
    INVALID_SIZE_LOTTO("로또 번호는 6개여야 합니다."),
    NO_UNIQUE_NUMBER_LOTTO("각 로또 번호는 유일해야 합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
