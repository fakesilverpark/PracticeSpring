package Stream;

import java.util.Arrays;
import java.util.List;

public class StreamReduceExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // reduce() 는 리스트의 모든 값을 하나로 합칠 때 사용
        Integer reduce = numbers.stream()
                // n, F n = 초기값, F
                // 내장 함수가 있으면 내장함수를 쓰는게 좋음
                .reduce(0, Integer::sum); //.reduce(0, (a, b) -> a + b);

        System.out.println(reduce);
    }
}
