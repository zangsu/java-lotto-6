package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.model.domain.result.LottoResult;

public interface LottoGameUI {
    int getMoney();
    List<Integer> getAnswerNumber();
    Integer getBonusNumber();
    void printResult(List<Map.Entry<LottoResult, Integer>> results);
}
