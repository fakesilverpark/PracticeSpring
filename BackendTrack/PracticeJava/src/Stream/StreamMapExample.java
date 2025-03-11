package Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamMapExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Jane", "Doe", "Bob", "Alice", "Bobby");
        List<String> upperCaseNames = names.stream()
                // String 에서 기본 제공 메서드 = 내장함수 이렇게 깔끔하게 사용가능
                .map(String::toUpperCase) // = .map(s -> s.toUpperCase())
                .collect(Collectors.toList()); // = .toList

        System.out.println(upperCaseNames);
    }
}
