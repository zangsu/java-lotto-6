package lotto.exception.handler;

import java.util.function.Supplier;
import lotto.view.OutputView;

/**
 * XXXException 을 전달받아 예상한 예외에 대해서만 재시도, 그 외의 예외는 throw
 */
public class RetryHandler {
    private static final OutputView outputView = new OutputView();

    public static <T> T getOrRetry(Supplier<T> supplier) {

        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printException(e);
            } finally {
                outputView.newLine();
            }
        }
    }
}
