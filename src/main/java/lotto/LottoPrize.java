package lotto;

import java.util.Arrays;
import java.util.Optional;

public enum LottoPrize {
    THREE_MATCHES(3, 5000),
    FOUR_MATCHES(4, 50000),
    FIVE_MATCHES(5, 1500000),
    FIVE_BONUS_MATCHES(5, 30000000),
    SIX_MATCHES(6, 2000000000);

    private final int matchCount;
    private final int money;

    LottoPrize(int matchCount, int money) {
        this.matchCount = matchCount;
        this.money = money;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getMoney() {
        return money;
    }

    public static Optional<LottoPrize> getLottoPrizeByMatchCount(int matchCount) {
        return Arrays.stream(LottoPrize.values())
                .filter(lottoPrize -> lottoPrize.getMatchCount() == matchCount)
                .findFirst();
    }
}