package Lamda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LambdaFilterExample {
    public static void main(String[] args) {

        List<String> names = new ArrayList<>();
        names.add("John");
        names.add("Jane");
        names.add("Bob");

        List<String> names1 = Arrays.asList("John", "Jane", "Bob");

        List<String> filteredNames = names.stream()
                .filter(name -> name.startsWith("J"))
                .toList();

        System.out.println(filteredNames);
    }
}
