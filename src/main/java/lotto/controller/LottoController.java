package lotto.controller;

import lotto.exception.LottoExceptionMaker;
import lotto.money.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run(){
        Money purchaseMoney = getPurchaseMoney();
    }

    private Money getPurchaseMoney() {
        int moneyAmount = inputView.getPurchaseMoney();
        //todo 검증 로직 분리 시키면 좋을듯
        if (moneyAmount <= 0) {
            throw LottoExceptionMaker.MONEY_MUST_NOT_NEGATIVE.makeException();
        }

        //todo 예외 메시지 다른 걸로 바꾸기
        if(moneyAmount == 0){
            throw LottoExceptionMaker.INVALID_VALUE.makeException();
        }
        if(moneyAmount % 1000 != 0){
            throw LottoExceptionMaker.INVALID_VALUE.makeException();
        }

        return new Money(moneyAmount);
    }
}
