package com.hqy.concurrency05;

        import java.util.stream.IntStream;

public class ImmutableClient {
    public static void main(String[] args) {
        //共享数据
        Person person = new Person("Young", "Fuzhou");
        IntStream.range(0, 5).forEach(i ->
                new UserPersonThread(person).start()
        );




    }
}
