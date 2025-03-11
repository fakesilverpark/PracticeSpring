package Optional;

import java.util.Optional;

public class NullPointerExample {
    public static void main(String[] args) {
        Optional<String> value = getFromServer();
        int length = value.map(String::length).orElse(0);
        System.out.println(length);
    }

    public static Optional<String> getFromServer(){
        return Optional.ofNullable(null);
    }
}
