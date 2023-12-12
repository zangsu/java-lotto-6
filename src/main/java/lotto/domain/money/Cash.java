package lotto.domain.money;


import lotto.exception.LottoExceptionMaker;

public class Cash implements Monetary {
    private int cash;

    public Cash(int cash) {
        validateCash(cash);
        this.cash = cash;
    }

    private void validateCash(int cash) {
        if (cash < 0) {
            throw LottoExceptionMaker.MONEY_MUST_NOT_NEGATIVE.makeException();
        }
    }

    public boolean canPurchase(Monetary price) {
        return compareTo(price) >= 0;
    }

    public void spend(Monetary price) {
        if (compareTo(price) < 0) {
            throw LottoExceptionMaker.NOT_ENOUGH_MONEY.makeException();
        }
        this.cash -= price.getPrice();
    }

    public Money toMoney() {
        return new Money(this.cash);
    }

    @Override
    public int getPrice() {
        return this.cash;
    }
}
