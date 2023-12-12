package lotto.view;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.result.ResultAndCount;
import lotto.view.io.Printer;

public class OutputView {
    public static final String EXCEPTION_PREFIX = "[ERROR] ";
    public static final String LOTTO_RESULT_DELIMITER = ", ";
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,##0");
    private static final DecimalFormat PERCENT_FORMAT = new DecimalFormat("###,##0.0");
    public static final String NO_BONUS_FORMAT = "%d개 일치 (%s원) - %d개";
    public static final String BONUS_FORMAT = "%d개 일치, 보너스 볼 일치 (%s원) - %d개";

    private final Printer printer = new Printer();

    public void printException(Exception e) {
        printer.printMessage(EXCEPTION_PREFIX + e.getMessage());
    }

    public void newLine() {
        printer.printMessage("");
    }


    public void printLottos(List<Lotto> lottos) {
        printer.printMessageUsingFormat("%d개를 구매했습니다.", lottos.size());
        lottos.stream()
                .map(this::getLottoString)
                .forEach(lottoString ->
                        printer.printMessageUsingFormat("[%s]", lottoString, LOTTO_RESULT_DELIMITER));
        newLine();
    }

    private String getLottoString(Lotto lotto) {
        String lottoString = lotto.getNumbers().stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        return lottoString;
    }

    public void printResults(List<ResultAndCount> resultAndCount) {
        printer.printMessage("""
                당첨 통계
                ---""");
        resultAndCount.forEach(result -> {
            String format = NO_BONUS_FORMAT;
            if(result.isBonusNeed()) {
                format = BONUS_FORMAT;
            }
            printer.printMessageUsingFormat(format,
                    result.getMatchCount(),
                    DECIMAL_FORMAT.format(result.getPrice()),
                    result.getCount());
        });
    }

    public void printProfitRate(double profitRate) {
        printer.printMessageUsingFormat("총 수익률은 %s%%입니다.", PERCENT_FORMAT.format(profitRate));
    }
}
