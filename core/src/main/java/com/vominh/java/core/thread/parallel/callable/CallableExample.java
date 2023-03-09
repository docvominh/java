package com.vominh.java.core.thread.parallel.callable;

import java.util.concurrent.Callable;

public class CallableExample {
    public static void main(String[] args) throws Exception {
        var name = "Minh";
        PrintMachine printMachine = new PrintMachine(name);
        mainFunction(printMachine);
    }

    private static void mainFunction(Callable<Void> printMachine) throws Exception {
        printMachine.call();
    }
}

class PrintMachine implements Callable<Void> {
    private final String input;

    public PrintMachine(String input) {
        this.input = input;
    }

    @Override
    public Void call() {
        System.out.println(input);
        return null;
    }
}
