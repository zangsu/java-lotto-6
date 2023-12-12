package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.generator.LottoGenerator;
import lotto.domain.money.Cash;
import lotto.exception.LottoExceptionMaker;
import lotto.exception.handler.RetryHandler;
import lotto.domain.money.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final LottoGenerator answerGenerator;
    public LottoController(LottoGenerator userLottoGenerator) {
        this.answerGenerator = userLottoGenerator;
    }
    public void run(){
        Money purchaseMoney = RetryHandler.getOrRetry(() -> getPurchaseMoney());
        Lottos lottos = purchaseLotto(purchaseMoney);

    }

    private Lottos purchaseLotto(Money purchaseMoney) {
        Cash remainCash = purchaseMoney.toCash();
        List<Lotto> lottos = new ArrayList<>();
        while(remainCash.canPurchase(Lotto.LOTTO_PRICE)){
            remainCash.spend(Lotto.LOTTO_PRICE);
            lottos.add(answerGenerator.generate());
        }
        //todo 출력 여기서 하나?
        return new Lottos(lottos);
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
