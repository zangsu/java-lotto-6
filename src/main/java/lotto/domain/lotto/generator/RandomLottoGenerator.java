package lotto.domain.lotto.generator;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;

public class RandomLottoGenerator implements LottoGenerator {
    @Override
    public Lotto generate() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(LottoNumber.MIN_LOTTO_NUMBER,
                LottoNumber.MAX_LOTTO_NUMBER,
                Lotto.LOTTO_SIZE);

        return new Lotto(numbers);
    }
}
