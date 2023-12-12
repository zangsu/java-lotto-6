package lotto.domain.money;

import lotto.domain.lotto.Lotto;
import lotto.exception.LottoExceptionMaker;

public class LottoMoney extends Money {
    public LottoMoney(int money) {
        super(money);
        validateLottoMoney(money);
    }

    private void validateLottoMoney(int money) {
        if (money < 0) {
            throw LottoExceptionMaker.MONEY_MUST_NOT_NEGATIVE.makeException();
        }
        if (money == 0) {
            throw LottoExceptionMaker.PURCHASE_LOTTO_MUST_NOT_ZERO.makeException();
        }
        if (money % Lotto.LOTTO_PRICE.getPrice() != 0) {
            throw LottoExceptionMaker.INVALID_LOTTO_MONEY.makeException();
        }
    }
}
