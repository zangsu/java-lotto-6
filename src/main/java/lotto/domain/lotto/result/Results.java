package lotto.domain.lotto.result;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.domain.money.Money;

public class Results {
    private final Map<LottoResult, Integer> results = new EnumMap<>(LottoResult.class);

    public Results(List<LottoResult> lottoResults) {
        Arrays.stream(LottoResult.values())
                .forEach(lottoResult -> results.put(lottoResult, 0));
        lottoResults.forEach(this::add);
    }

    private void add(LottoResult lottoResult) {
        if (lottoResult != LottoResult.NONE) {
            results.put(lottoResult, results.get(lottoResult) + 1);
        }
    }

    public List<ResultAndCount> getResultAndCount() {
        return Arrays.stream(LottoResult.values())
                .filter(lottoResult -> lottoResult != LottoResult.NONE)
                .sorted(Comparator.comparing(LottoResult::getPrice))
                .map(lottoResult -> new ResultAndCount(lottoResult, results.get(lottoResult)))
                .toList();
    }

    public double getProfitRate(Money purchaseMoney) {
        long totalPrize = results.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrice() * entry.getValue())
                .sum();

        return (double) (totalPrize * 100.0) / purchaseMoney.getPrice();
    }
}
