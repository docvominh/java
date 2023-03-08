package com.vominh.example.java8.lambda;

public class AnonymousClass {
    public interface Hello {
        void sayHello();

        void sayBye();
    }

    static class HelloInVietnam implements Hello {

        @Override
        public void sayHello() {
            System.out.println("Xin Chào");
        }

        @Override
        public void sayBye() {

        }
    }

    static class HelloInEnglish implements Hello {
        @Override
        public void sayHello() {
            System.out.println("Hello everybody");
        }

        @Override
        public void sayBye() {

        }
    }

    public static void main(String[] args) {
        var anonymousClass = new AnonymousClass();
        Hello vn = new HelloInVietnam();
        Hello en = new HelloInEnglish();

        Hello german = new Hello() {
            @Override
            public void sayHello() {
                System.out.println("Guten morgen");
            }

            @Override
            public void sayBye() {

            }
        };

        vn.sayHello();
        en.sayHello();
        german.sayHello();
    }


}
