package lotto.domain.lotto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import lotto.exception.LottoExceptionMaker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnswerLottoTest {

    @DisplayName("보너스 번호가 당첨 번호에 포함되면 예외가 발생한다.")
    @Test
    void validateBonusDuplicate() {
        // given
        Lotto lotto = new Lotto(List.of(1,2,3,4,5,6));
        LottoNumber bonusNumber = new LottoNumber(6);

        // when & then
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> new AnswerLotto(lotto, bonusNumber))
                .withMessage(LottoExceptionMaker.DUPLICATED_LOTTO_NUMBER.getMessage());

    }
}