package com.vominh.example.log;

import java.util.logging.Logger;

public class LogTest {
    private static final String className = "LogTest";

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(className);
        logger.info("This is an info message");
        logger.severe("This is an error message"); // == ERROR
        logger.fine("Here is a debug` message"); // == DEBUG

//        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MyClass.getClass().getName());
//        logger.info("This is an info message");
//        logger.error("This is an error message");
//        logger.debug("Here is a debug message");

    }
}
