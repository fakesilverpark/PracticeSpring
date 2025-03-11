package Wildcard;

// <? extends Number> 를 써서 숫자 리스트의 최대값을 구하는 코드 작성
// max() 횔용

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Practice1 {
    public static double getMaxValue(List<? extends Number> numbers){
        return numbers.stream()
                .max(Comparator.comparingDouble((Number num) -> num.doubleValue()))
                .get()
                .doubleValue();
    }

    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(getMaxValue(intList));
    }
}
