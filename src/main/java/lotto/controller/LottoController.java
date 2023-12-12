package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.lotto.AnswerLotto;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.generator.LottoGenerator;
import lotto.domain.lotto.result.LottoResult;
import lotto.domain.lotto.result.Results;
import lotto.domain.money.Cash;
import lotto.domain.money.LottoMoney;
import lotto.domain.money.Money;
import lotto.exception.handler.RetryHandler;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final LottoGenerator answerGenerator;

    public LottoController(LottoGenerator userLottoGenerator) {
        this.answerGenerator = userLottoGenerator;
    }

    public void run() {
        Money purchaseMoney = RetryHandler.getOrRetry(() -> getPurchaseMoney());
        Lottos lottos = purchaseLotto(purchaseMoney);
        AnswerLotto answer = getAnswerLotto();
        Results results = getResults(lottos, answer);
        outputView.printResults(results.getResultAndCount());
        outputView.printProfitRate(results.getProfitRate(purchaseMoney));
    }

    private Results getResults(Lottos lottos, AnswerLotto answer) {
        List<LottoResult> lottoResults = lottos.calcResult(answer);
        return new Results(lottoResults);
    }

    private AnswerLotto getAnswerLotto() {
        Lotto lotto = RetryHandler.getOrRetry(() -> new Lotto(inputView.getLottoNumbers()));
        return RetryHandler.getOrRetry(() -> {
            LottoNumber lottoNumber = new LottoNumber(inputView.getBonusNumber());
            return new AnswerLotto(lotto, lottoNumber);
        });
    }

    private Lottos purchaseLotto(Money purchaseMoney) {
        Cash remainCash = purchaseMoney.toCash();
        List<Lotto> lottos = new ArrayList<>();
        while (remainCash.canPurchase(Lotto.LOTTO_PRICE)) {
            remainCash.spend(Lotto.LOTTO_PRICE);
            lottos.add(answerGenerator.generate());
        }
        outputView.printLottos(lottos);
        return new Lottos(lottos);
    }

    private Money getPurchaseMoney() {
        int moneyAmount = inputView.getPurchaseMoney();
        return new LottoMoney(moneyAmount);
    }
}
