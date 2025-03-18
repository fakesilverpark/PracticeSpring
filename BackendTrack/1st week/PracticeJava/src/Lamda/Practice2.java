package Lamda;

// List<String> 에서 길이가 5 이상인 문자열만 필터링하는 코드 작성

import java.util.Arrays;
import java.util.List;

public class Practice2 {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("John", "Sally", "Class", "Tree", "Candy");

        List<String> FilteredStrings = strings.stream()
                .filter(string -> string.length() >= 5)
                .toList();

        System.out.println(FilteredStrings);
    }
}
