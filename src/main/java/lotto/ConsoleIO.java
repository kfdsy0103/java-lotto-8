package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ConsoleIO {

    public static String inputPurchasePrice() {
        System.out.println("구입금액을 입력해 주세요.");
        return Console.readLine();
    }

    public static void printPurchaseInfo(List<Lotto> lottos) {
        System.out.println();
        int size = lottos.size();
        System.out.println(size + "개를 구매했습니다.");
        lottos.forEach(ConsoleIO::printLottoInfo);
    }

    public static String inputWinningNumber() {
        System.out.println();
        System.out.println("당첨 번호를 입력해 주세요.");
        return Console.readLine();
    }

    public static String inputBonusNumber() {
        System.out.println();
        System.out.println("보너스 번호를 입력해 주세요.");
        return Console.readLine();
    }

    public static void printResult(Map<LottoPrize, Integer> result, Double percentage) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        result.entrySet().forEach(ConsoleIO::printStatistic);
        System.out.printf("총 수익률은 %.2f%%입니다.\n", percentage);
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }

    private static void printLottoInfo(Lotto lotto) {
        System.out.println(lotto.toString());
    }

    private static void printStatistic(Entry<LottoPrize, Integer> statistics) {
        LottoPrize lottoPrize = statistics.getKey();
        Integer matchCountResult = statistics.getValue();

        if (lottoPrize.equals(LottoPrize.FIVE_BONUS_MATCHES)) {
            System.out.printf("%d개 일치, 보너스 볼 일치 (%,d원) - %d개\n", lottoPrize.getMatchCount(), lottoPrize.getMoney(),
                    matchCountResult);
            return;
        }
        System.out.printf("%d개 일치 (%,d원) - %d개\n", lottoPrize.getMatchCount(), lottoPrize.getMoney(),
                matchCountResult);
    }
}
