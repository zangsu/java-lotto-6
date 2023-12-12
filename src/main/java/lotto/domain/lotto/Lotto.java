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
                .sorted()
                .toList();
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplication(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw LottoExceptionMaker.INVALID_LOTTO_SIZE.makeException();
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

    public boolean contains(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    public List<Integer> getNumbers() {
        return numbers.stream()
                .map(LottoNumber::getNumber)
                .toList();
    }

    public int matchCount(Lotto lotto) {
        return (int) numbers.stream()
                .filter(lotto::contains)
                .count();
    }

    @Override
    public String toString() {
        return "Lotto{" +
                "numbers=" + numbers +
                '}';
    }
}
