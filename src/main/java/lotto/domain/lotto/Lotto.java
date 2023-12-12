package lotto.domain.lotto;

import java.util.List;
import lotto.domain.money.Money;
import lotto.exception.LottoExceptionMaker;

public class Lotto {
    public static final int LOTTO_SIZE = 6;
    public static final Money LOTTO_PRICE = new Money(1000);
    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream()
                .map(LottoNumber::new)
                .toList();
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplication(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplication(List<Integer> numbers) {
        int distinctCount = (int) numbers.stream()
                .distinct()
                .count();
        if (distinctCount != LOTTO_SIZE) {
            throw LottoExceptionMaker.DUPLICATED_LOTTO_NUMBER.makeException();
        }
    }

    // TODO: 추가 기능 구현
}
