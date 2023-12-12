package lotto.domain.lotto;

import lotto.exception.LottoExceptionMaker;

public class LottoNumber {
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    private final int number;

    public LottoNumber(int number) {
        validate(number);
        this.number = number;
    }

    private void validate(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw LottoExceptionMaker.INVALID_LOTTO_NUMBER.makeException();
        }
    }
}
