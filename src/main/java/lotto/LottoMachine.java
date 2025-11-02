package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class LottoMachine {

    public static final int LOTTO_PRICE = 1000;

    public void play() {
        int purchasePrice = getPurchasePrice();
        int purchaseCount = purchasePrice / LOTTO_PRICE;

        List<Lotto> lottos = generateLottos(purchaseCount);

        List<Integer> winningNumber = getWinningNumber();
        Lotto winningLotto = new Lotto(winningNumber);
        int bonusNumber = getBonusNumber();

        Map<LottoPrize, Integer> statistic = getStatistic(lottos, winningLotto, bonusNumber);
        double percentage = getPercentage(statistic, purchasePrice);

        ConsoleIO.printResult(statistic, percentage);
    }

    private Map<LottoPrize, Integer> getStatistic(List<Lotto> lottos, Lotto winningLotto, int bonusNumber) {
        // 일치 개수 확인
        List<LottoPrize> matchedCount = lottos.stream()
                .filter(lotto -> winningLotto.countMatchedNumber(lotto) >= 3)
                .map(lotto -> {
                    int count = winningLotto.countMatchedNumber(lotto);
                    if (count == 5 && lotto.getNumbers().contains(bonusNumber)) {
                        return LottoPrize.FIVE_BONUS_MATCHES;
                    }
                    return LottoPrize.getLottoPrizeByMatchCount(count).get();
                })
                .toList();

        // 통계 내기
        return getMatchCountPerLottoPrize(matchedCount);
    }

    private Map<LottoPrize, Integer> getMatchCountPerLottoPrize(List<LottoPrize> matchedCount) {
        Map<LottoPrize, Integer> statistic = new LinkedHashMap<>(); // 순서 보장
        statistic.put(LottoPrize.THREE_MATCHES, 0);
        statistic.put(LottoPrize.FOUR_MATCHES, 0);
        statistic.put(LottoPrize.FIVE_MATCHES, 0);
        statistic.put(LottoPrize.FIVE_BONUS_MATCHES, 0);
        statistic.put(LottoPrize.SIX_MATCHES, 0);

        for (LottoPrize lottoPrize : matchedCount) {
            statistic.put(lottoPrize, statistic.get(lottoPrize) + 1);
        }
        return statistic;
    }

    private double getPercentage(Map<LottoPrize, Integer> statistic, int purchasePrice) {
        double sum = 0;
        for (Entry<LottoPrize, Integer> entry : statistic.entrySet()) {
            LottoPrize lottoPrize = entry.getKey();
            Integer count = entry.getValue();
            sum += lottoPrize.getMoney() * count;
        }
        return (sum + purchasePrice) / purchasePrice;
    }

    private List<Lotto> generateLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(generateLottoNumbers()));
        }
        ConsoleIO.printPurchaseInfo(lottos);
        return lottos;
    }

    private List<Integer> generateLottoNumbers() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }

    private int getPurchasePrice() {
        while (true) {
            try {
                String purchasePriceInput = ConsoleIO.inputPurchasePrice();
                return LottoParser.parsePurchasePrice(purchasePriceInput);
            } catch (Exception e) {
                ConsoleIO.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<Integer> getWinningNumber() {
        while (true) {
            try {
                String winningNumberInput = ConsoleIO.inputWinningNumber();
                return LottoParser.parseWinningNumber(winningNumberInput);
            } catch (Exception e) {
                ConsoleIO.printErrorMessage(e.getMessage());
            }
        }
    }

    private int getBonusNumber() {
        while (true) {
            try {
                String bonusNumberInput = ConsoleIO.inputBonusNumber();
                return LottoParser.parseBonusNumber(bonusNumberInput);
            } catch (Exception e) {
                ConsoleIO.printErrorMessage(e.getMessage());
            }
        }
    }

}
