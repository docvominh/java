package com.vominh.design.pattern.observer;

import com.vominh.design.pattern.observer.event.EmailNotificationListener;
import com.vominh.design.pattern.observer.event.LogOpenListener;

public class Test {
    public static void main(String[] args) {
        Editor editor = new Editor();
        editor.events.subscribe("open", new LogOpenListener("D:\\file.txt"));
        editor.events.subscribe("save", new EmailNotificationListener("admin@example.com"));

        try {
            editor.openFile("test.txt");
            editor.saveFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
