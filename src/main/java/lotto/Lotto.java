package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_SIZE_LOTTO.getMessage());
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int countMatchedNumber(Lotto lotto) {
        Set<Integer> otherLotto = new HashSet<>(lotto.getNumbers());
        int count = 0;
        for (Integer number : numbers) {
            if (otherLotto.contains(number)) {
                count++;
            }
        }
        return count;
    }
}
