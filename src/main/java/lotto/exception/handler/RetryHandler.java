package lotto.exception.handler;

import java.util.Arrays;
import java.util.function.Supplier;
import lotto.exception.LottoExceptionMaker;
import lotto.view.OutputView;

/**
 * XXXException 을 전달받아 예상한 예외에 대해서만 재시도, 그 외의 예외는 throw
 */
public class RetryHandler{
    private static final OutputView outputView = new OutputView();

    public static <T> T getOrRetry(Supplier<T> supplier){

        while(true){
            try{
                return supplier.get();
            } catch (IllegalArgumentException e){
                outputView.printException(e);
            } finally {
                outputView.newLine();
            }
        }
    }
    public static <T> T getOrConditionalRetry(Supplier<T> supplier, LottoExceptionMaker... expectedExceptions){
        while(true){
            try{
                return supplier.get();
            } catch (IllegalArgumentException e){
                checkExpectedException(e, expectedExceptions);
            } finally {
                outputView.newLine();
            }
        }
    }
    public static void runOrRetry(Runnable runnable) {
        while(true){
            try {
                runnable.run();
                return;
            } catch (IllegalArgumentException e) {
                outputView.printException(e);
            } finally {
                outputView.newLine();
            }
        }
    }
    public static void runOrConditionalRetry(Runnable runnable, LottoExceptionMaker... expectedExceptions) {
        while(true){
            try {
                runnable.run();
                return;
            } catch (IllegalArgumentException e) {
                checkExpectedException(e, expectedExceptions);
            } finally {
                outputView.newLine();
            }
        }
    }

    private static void checkExpectedException(IllegalArgumentException e, LottoExceptionMaker[] expectedExceptions) {
        if(!isExpectedException(e, expectedExceptions)){
            throw e;
        }
        outputView.printException(e);
    }

    private static boolean isExpectedException(IllegalArgumentException e, LottoExceptionMaker[] exceptions) {
        return Arrays.stream(exceptions)
                .map(LottoExceptionMaker::makeException)
                .anyMatch(e::equals);
    }
}
