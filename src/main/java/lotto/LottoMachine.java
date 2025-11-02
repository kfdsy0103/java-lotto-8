package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class LottoMachine {

    public static final int LOTTO_PRICE = 1000;

    public void play() {
        int purchasePrice = getPurchasePrice();
        int purchaseCount = purchasePrice / LOTTO_PRICE;

        List<Lotto> lottos = generateLottos(purchaseCount);

        List<Integer> winningNumber = getWinningNumber();
        Lotto winningLotto = new Lotto(winningNumber);
        int bonusNumber = getBonusNumber();
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
