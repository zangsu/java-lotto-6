package lotto.domain.lotto.result;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;
import lotto.domain.lotto.AnswerLotto;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoResultTest {
    private static final AnswerLotto answer = new AnswerLotto(
            new Lotto(List.of(1, 2, 3, 4, 5, 6)),
            new LottoNumber(7));

    @ParameterizedTest
    @MethodSource("ArgsLottoAndResult")
    @DisplayName("{0}의 등수는 {1}이다.")
    void 로또_등수_테스트 (Lotto lotto, LottoResult expected){
        Assertions.assertThat(LottoResult.of(answer, lotto))
                        .isEqualTo(expected);
    }

    static Stream<Arguments> ArgsLottoAndResult(){
        return Stream.of(
            Arguments.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                LottoResult.FIRST
            ),
            Arguments.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                LottoResult.SECOND
            ),
            Arguments.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 8)),
                LottoResult.THIRD
            ),
            Arguments.of(
                new Lotto(List.of(1, 2, 3, 4, 8, 9)),
                LottoResult.FOURTH
            ),
            Arguments.of(
                new Lotto(List.of(1, 2, 3, 8, 9, 10)),
                LottoResult.FIFTH
            ),
            Arguments.of(
                new Lotto(List.of(1, 2, 8, 9, 10, 11)),
                LottoResult.NONE
            )
        );
    }
}