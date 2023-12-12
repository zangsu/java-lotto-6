package lotto.view;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.lotto.Lotto;
import lotto.view.io.Printer;

public class OutputView {
    public static final String EXCEPTION_PREFIX = "[ERROR] ";
    public static final String LOTTO_RESULT_DELIMITER = ", ";
    private final Printer printer = new Printer();

    public void printException(Exception e) {
        printer.printMessage(EXCEPTION_PREFIX + e.getMessage());
    }

    public void newLine() {
        printer.printMessage("");
    }

    private <T> void printListUsingFormat(List<T> list) {
        list.forEach(t -> printer.printMessageUsingFormat("FORMAT", 1, 2, 3));
    }

    public void printLottos(List<Lotto> lottos) {
        lottos.stream()
                .map(this::getLottoString)
                .forEach(lottoString ->
                        printer.printMessageUsingFormat("[%s]", lottoString, LOTTO_RESULT_DELIMITER));
    }

    private String getLottoString(Lotto lotto) {
        String lottoString = lotto.getNumbers().stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        return lottoString;
    }
}
