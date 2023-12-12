package lotto.domain.lotto;

import java.util.List;
import lotto.domain.lotto.result.LottoResult;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<LottoResult> calcResult(AnswerLotto answerLotto) {
        return lottos.stream()
                .map(lotto -> LottoResult.of(answerLotto, lotto))
                .toList();
    }
}
