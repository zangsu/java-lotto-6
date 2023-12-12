package lotto.domain.lotto;

import lotto.exception.LottoExceptionMaker;

public class AnswerLotto {
    private final Lotto lottoNumber;
    private final LottoNumber bonusNumber;

    public AnswerLotto(Lotto lottoNumber, LottoNumber bonusNumber) {
        validateBonusDuplicate(lottoNumber, bonusNumber);
        this.lottoNumber = lottoNumber;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusDuplicate(Lotto lottoNumber, LottoNumber bonusNumber) {
        if(lottoNumber.contains(bonusNumber)) {
            throw LottoExceptionMaker.DUPLICATED_LOTTO_NUMBER.makeException();
        }
    }
}
