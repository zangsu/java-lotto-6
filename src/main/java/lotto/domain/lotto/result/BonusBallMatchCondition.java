package lotto.domain.lotto.result;

import java.util.function.BiPredicate;
import lotto.domain.lotto.AnswerLotto;
import lotto.domain.lotto.Lotto;

public enum BonusBallMatchCondition {

    IGNORE((answerLotto, lotto) -> true),
    MATCH(AnswerLotto::matchBonusNumber),
    NOT_MATCH(MATCH.condition.negate());

    private final BiPredicate<AnswerLotto, Lotto> condition;

    BonusBallMatchCondition(BiPredicate<AnswerLotto, Lotto> condition) {
        this.condition = condition;
    }

    public boolean compareBonusNumber(AnswerLotto answerLotto, Lotto lotto) {
        return condition.test(answerLotto, lotto);
    }
}
