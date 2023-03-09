package com.vominh.java.core.collections.list;

import com.vominh.java.core.collections.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ArrayListTest {


    //https://docs.oracle.com/javase/tutorial/collections/implementations/list.html

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(Person.builder().idNumber(11111).name("Minh").age(31).build());
        persons.add(Person.builder().idNumber(22222).name("Long").age(22).build());
        persons.add(Person.builder().idNumber(33333).name("Nam").age(45).build());


        System.out.println("----- Default print -----");
        persons.forEach(p -> {
            System.out.println(p.getName());
        });

        System.out.println("----- After sort -----");
        Collections.sort(persons);

        persons.forEach(p -> {
            System.out.println(p.getName());
        });


        System.out.println("----- Custom define sort -----");
        Comparator<Person> compareByAge = (p1, p2) -> p1.getAge() < p2.getAge() ? -1 : 1;

        Collections.sort(persons, compareByAge);

        persons.forEach(p -> {
            System.out.println(p.getName());
        });

        System.out.println("----- END -----");

    }
}
