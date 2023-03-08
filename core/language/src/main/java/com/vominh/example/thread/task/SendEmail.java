package com.vominh.example.thread.task;

public class SendEmail implements Runnable {
    private String email;
    private String subject;

    public SendEmail(String email, String subject) {
        this.email = email;
        this.subject = subject;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("Send Email to: %s", this.email));
        System.out.println(String.format("Subject: %s", this.subject));
    }
}
