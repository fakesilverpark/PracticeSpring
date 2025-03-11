package Wildcard;

import java.util.Arrays;
import java.util.List;

public class WildcardExample {

    public static void printList(List<?> list) {
        for (Object i : list) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        List<String> strList = Arrays.asList("a", "b", "c");
        List<Integer> intList = Arrays.asList(1, 2, 3);

        printList(strList);
        printList(intList);
    }
}
