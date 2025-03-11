package Async;

import java.util.concurrent.CompletableFuture;

public class AsyncExample {
    public static void main(String[] args) {

        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "비동기 작업이 오래시간 끝에 드디어!! 완료";
        }).thenAccept(System.out::println);

        System.out.println("메인 스레드가 실행중.....");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
