package Lamda;

@FunctionalInterface
interface Calculator {
    int operate(int a, int b);
}

public class LambdaBasicExample {
    public static void main(String[] args) {

        Calculator add = Integer::sum;
        Calculator mul = (a, b) -> a * b;

        System.out.println("10 + 5 = " + add.operate(10, 5));
        System.out.println("10 + 5 = " + mul.operate(10, 5));
    }
}
