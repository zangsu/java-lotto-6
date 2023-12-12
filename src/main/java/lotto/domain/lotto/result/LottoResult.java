package lotto.domain.lotto.result;

import static lotto.domain.lotto.result.BonusballMatchCondition.IGNORE;
import static lotto.domain.lotto.result.BonusballMatchCondition.MATCH;
import static lotto.domain.lotto.result.BonusballMatchCondition.NOT_MATCH;

import java.util.Arrays;
import java.util.function.BiPredicate;
import lotto.domain.lotto.AnswerLotto;
import lotto.domain.lotto.Lotto;
import lotto.domain.money.Money;

public enum LottoResult {
    NONE(0, 0, IGNORE),
    FIFTH(3, 5_000, IGNORE),
    FOURTH(4, 50_000, IGNORE),
    THIRD(5, 1_500_000, NOT_MATCH),
    SECOND(5, 30_000_000, MATCH),
    FIRST(6, 2_000_000_000, IGNORE);

    private final Money prize;
    private final int matchCount;
    private BonusballMatchCondition bonusballMatchCondition;


    LottoResult(int matchCount, int prize, BonusballMatchCondition bonusballMatchCondition) {
        this.matchCount = matchCount;
        this.prize = new Money(prize);
        this.bonusballMatchCondition = bonusballMatchCondition;
    }

    public static LottoResult of(AnswerLotto answerLotto, Lotto lotto) {
        return Arrays.stream(values())
                .filter(lottoResult -> lottoResult.matchCount == answerLotto.match(lotto))
                .filter(lottoResult -> lottoResult.bonusballMatchCondition.compareBonusNumber(answerLotto, lotto))
                .findFirst()
                .orElse(NONE);
    }

    public Money getPrize() {
        return prize;
    }

    public int getPrice() {
        return prize.getPrice();
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonusNeed() {
        return bonusballMatchCondition == MATCH;
    }
}
