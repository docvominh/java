package com.vominh.example.java8.lambda;
import java.util.function.Consumer;

public class ConsumerTest {
    public static void main(String[] args) {
        var printSomething = new Consumer<String>() {
            @Override
            public void accept(String something) {
                System.out.println(something);
            }
        };

        // Basic syntax
        mainFunction(printSomething);

        // Lambda syntax
        mainFunction((something) -> System.out.println(something));
    }

    private static void mainFunction(Consumer<String> printSomething) {
        var name = "Minh";
        printSomething.accept(name);

        CallBack
    }
}
