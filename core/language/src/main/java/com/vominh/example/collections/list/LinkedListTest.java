package com.vominh.example.collections.list;

import com.vominh.example.collections.Person;

import java.util.LinkedList;

public class LinkedListTest {

    public static void main(String[] args) {
        LinkedList<Person> persons = new LinkedList<>();
        persons.addLast(Person.builder().idNumber(11111).name("Minh").build());
        persons.addFirst(Person.builder().idNumber(22222).name("Long").build());

        persons.forEach(p -> System.out.println(p.getName()));

    }
}
