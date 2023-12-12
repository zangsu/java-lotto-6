package lotto.view;

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
}
