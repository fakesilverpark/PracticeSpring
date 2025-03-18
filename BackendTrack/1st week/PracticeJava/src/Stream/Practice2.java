package Stream;

// List<String> 에서 문자열 길이으ㅢ 총 합을 구하는 코드

import java.util.Arrays;
import java.util.List;

public class Practice2 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Jane", "Doe", "Bob", "Alice", "Bobby");
        Integer reduce = names.stream()
                .map(String::length)
                .reduce(0, Integer::sum);

        System.out.println(reduce);
    }
}
