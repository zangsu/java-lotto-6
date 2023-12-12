package lotto.view;

import java.util.List;
import lotto.domain.lotto.Lotto;
import lotto.view.io.Printer;
import lotto.view.io.Reader;

public class InputView {
    

    public static final String DELIMITER = ",";
    private final Reader reader = new Reader();
    private final Printer printer = new Printer();

    public int getPurchaseMoney(){
        printer.printMessage("구입금액을 입력해 주세요.");
        return reader.getInteger();
    }

    public List<Integer> getLottoNumbers() {
        printer.printMessage("당첨 번호를 입력해 주세요.");
        return reader.getObjectsUsingDelimiter(Integer::parseInt, DELIMITER);
    }

    public int getBonusNumber() {
        printer.printMessage("보너스 번호를 입력해 주세요.");
        return reader.getInteger();
    }
}
