package lotto;

import lotto.controller.LottoController;
import lotto.domain.lotto.generator.RandomLottoGenerator;

public class Application {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController(new RandomLottoGenerator());
        lottoController.run();
    }
}
