package Wildcard;

// List<?> 를 써서 리스트 요소 개수 반환하는 메서드 작성

import java.util.Arrays;
import java.util.List;

public class Practice2 {

    public static int getCountList(List<?> list) {
        return list.size();
    }

    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        List<String> strList = Arrays.asList("a", "b", "c");
        List<Double> douList = Arrays.asList(1.0, 2.0, 3.0, 4.0);

        System.out.println(getCountList(strList));
        System.out.println(getCountList(intList));
        System.out.println(getCountList(douList));
    }
}
