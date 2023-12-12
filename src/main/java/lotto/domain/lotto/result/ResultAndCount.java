package lotto.domain.lotto.result;

public class ResultAndCount {
    private final LottoResult lottoResult;
    private final int count;

    public ResultAndCount(LottoResult lottoResult, int count) {
        this.lottoResult = lottoResult;
        this.count = count;
    }

    public LottoResult getLottoResult() {
        return lottoResult;
    }

    public int getMatchCount(){
        return lottoResult.getMatchCount();
    }

    public int getPrice(){
        return lottoResult.getPrice();
    }

    public int getCount() {
        return count;
    }

    public boolean isBonusNeed() {
        return lottoResult.isBonusNeed();
    }
}
