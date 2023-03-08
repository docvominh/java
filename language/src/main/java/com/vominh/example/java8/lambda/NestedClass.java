package com.vominh.example.java8.lambda;

public class NestedClass {

    abstract class Service {
        abstract String getServiceName();
    }

    class Service1 extends Service {
        ServicePro1 servicePro = new ServicePro1();

        String getServiceName() {
            Service2 s2 = new Service2();
            return servicePro.getText() + "build";
        }
    }

    class Service2 extends Service {
        String getServiceName() {
            return "test";
        }
    }

    static class ServicePro1 {
        // Not valid
//        Service1 s1 = new Service1();
        ServicePro2 servicePro2 = new ServicePro2();

        String getText() {
            return "Service is: ";
        }
    }

    static class ServicePro2 {
        String getText() {
            return "Pro 2 Service is: ";
        }
    }


    interface ISimpleService {
        void print(String value);

        class SimpleService implements ISimpleService {

            @Override
            public void print(String value) {
                System.out.println(value);
            }
        }
    }

    public static void main(String[] args) {

        //There is a static nested class, this [static nested] class does not need an instance of the enclosing class in order to be instantiated itself.
        //These classes [static nested ones] can access only the static members of the enclosing class [since it does not have any reference to instances of the enclosing class...]
        NestedClass root = new NestedClass();
        NestedClass.Service service = root.new Service1();
        System.out.println(service.getServiceName());

        NestedClass.Service1 service1 = root.new Service1();
        System.out.println(service1.getServiceName());

        NestedClass.ServicePro1 servicePro = new ServicePro1();
        System.out.println(servicePro.getText());

        ISimpleService simpleService = new ISimpleService.SimpleService();
        simpleService.print("abc");

        AnonymousClass.Hello abc = new AnonymousClass.HelloInVietnam();
        abc.sayHello();
    }
}
