package lotto.exception;

public enum LottoExceptionMaker {
    INVALID_NUMBER_FORMAT("숫자를 입력해 주세요"),
    INVALID_INPUT_FORMAT("입력 형식이 잘못되었습니다."),

    BLANK_INPUT("입력값이 비어 있습니다."),
    MONEY_MUST_NOT_NEGATIVE("금액은 0 이상이어야 합니다."),
    NOT_ENOUGH_MONEY("잔액이 부족합니다."),

    INVALID_LOTTO_NUMBER("로또 번호는 1~45 사이의 숫자여야 합니다."),
    DUPLICATED_LOTTO_NUMBER("로또 번호는 중복될 수 없습니다."),
    INVALID_LOTTO_SIZE("로또 번호는 6개여야 합니다."),
    INVALID_LOTTO_MONEY("로또 구입 금액은 1000원 단위여야 합니다."),
    PURCHASE_LOTTO_MUST_NOT_ZERO("로또 구입 금액은 0원 이상이어야 합니다."),
    ;

    private final String message;
    private final IllegalArgumentException exception;

    LottoExceptionMaker(String message) {
        this.message = message;
        this.exception = new IllegalArgumentException(this.message);
    }

    public IllegalArgumentException makeException() {
        return exception;
    }

    public String getMessage() {
        return message;
    }
}
