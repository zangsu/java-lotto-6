package lotto.money;


import lotto.exception.LottoExceptionMaker;

public class Money implements Monetary {
    private final int money;

    public Money(int money) {
        validateMoney(money);
        this.money = money;
    }

    private void validateMoney(int money) {
        if (money < 0) {
            throw LottoExceptionMaker.MENU_AMOUNT_MUST_POSITIVE.makeException();
        }
    }

    public Cash toCash(){
        return new Cash(this.money);
    }

    @Override
    public int getPrice() {
        return this.money;
    }
}
