package Stream;

// ListL<Integer> 에서 짝수만 필터링
// 제곱한 값의 합을 구하는 코드
// filter, map, reduce

import java.util.Arrays;
import java.util.List;

public class Practice1 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Integer reduce = numbers.stream()
                // 일부만 걸러내려고 할 때
                .filter(n -> n % 2 == 0)
                // 모든거에 적용하고 싶을 때
                .map(n -> n*n)
                .reduce(0, Integer::sum);

        System.out.println(reduce);
    }
}
