package Wildcard;

import java.util.Arrays;
import java.util.List;

public class BoundedWildcardExample {
    public static double getAverage(List<? extends Number> numbers) {
        double sum = 0.0;

        for (Number number : numbers) {
            sum += number.doubleValue();
        }

        return sum / numbers.size();
    }

    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> douList = Arrays.asList(1.5, 2.5, 3.5, 4.5, 5.5);

        System.out.println("정수 리스트 평균: " + getAverage(intList));
        System.out.println("실수 리스트 평균: " + getAverage(douList));
    }
}
