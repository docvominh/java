package com.vominh.example.java8.lambda.tutorial;

import com.vominh.example.java8.lambda.tutorial.criteria.CheckPerson;
import com.vominh.example.java8.lambda.tutorial.criteria.CheckPersonEligibleForSelectiveService;
import com.vominh.example.java8.lambda.tutorial.object.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Sample {

    // https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#use-case

    public static void main(String[] args) {
        List<Person> roster = new ArrayList<>();
        roster.add(new Person("Minh", LocalDate.of(1990, 3, 5), Person.Sex.MALE, "minh@gmail.com"));
        roster.add(new Person("Nguyen", LocalDate.of(1994, 8, 3), Person.Sex.FEMALE, "nguyen@gmail.com"));
        roster.add(new Person("Khang", LocalDate.of(2021, 4, 21), Person.Sex.MALE, "khang@gmail.com"));

        int x = 0;
        roster.forEach(p->{
            int y =1;
            y++;
            System.out.println(x);
            System.out.println(y);
        });

        Comparator<Person> comparator0 = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        Comparator<Person> comparator1 = (p1, p2) -> p1.getName().compareTo(p2.getName());
        Comparator<Person> comparator2 = Comparator.comparing(Person::getName);

        Collections.sort(roster, comparator1);
        Collections.sort(roster, comparator2);

        roster.forEach(p -> System.out.println(p.getName()));

        printPersons(roster, new CheckPersonEligibleForSelectiveService());

        printPersons(roster, new CheckPerson() {
            @Override
            public boolean test(Person p) {
                return p.getGender() == Person.Sex.FEMALE;
            }
        });


        // For single interface method only
        System.out.println("===========printPersons=============");
        printPersons(roster, p -> p.getGender() == Person.Sex.FEMALE);


        System.out.println("===========printPersonsWithPredicate=============");
        printPersonsWithPredicate(roster, p -> p.getAge() == 1 || p.getAge() == 2);

        System.out.println("============processPersons============");
        processPersons(roster, p -> 1 == 1, p -> p.printAgeOnly());

        System.out.println("============processPersonsWithFunction============");
        processPersonsWithFunction(
                roster,
                p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 40,
                p -> p.getEmailAddress(),
                email -> System.out.println(email)
        );

        System.out.println("============Stream============");

        roster.stream()
                .filter(p -> p.getAge() > 0)
                .map(p -> p.getAge())
                .forEach(age -> System.out.println(age));

    }

    public static void printPersonsOlderThan(List<Person> roster, int age) {
        for (Person p : roster) {
            if (p.getAge() >= age) {
                p.printPerson();
            }
        }
    }

    public static void printPersonsWithinAgeRange(List<Person> roster, int low, int high) {
        for (Person p : roster) {
            if (low <= p.getAge() && p.getAge() < high) {
                p.printPerson();
            }
        }
    }

    public static void printPersons(List<Person> roster, CheckPerson tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    public static void printPersonsWithPredicate(List<Person> roster, Predicate<Person> tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    public static void processPersons(List<Person> roster, Predicate<Person> tester, Consumer<Person> block) {
        for (Person p : roster) {
            if (tester.test(p)) {
                block.accept(p);
            }
        }
    }

    public static void processPersonsWithFunction(
            List<Person> roster,
            Predicate<Person> tester,
            Function<Person, String> mapper,
            Consumer<String> block) {
        for (Person p : roster) {
            if (tester.test(p)) {
                String data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

}
