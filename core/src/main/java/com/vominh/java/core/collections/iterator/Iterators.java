package com.vominh.java.core.collections.iterator;

import com.vominh.java.core.collections.Person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Iterators {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(Person.builder().age(20).name("Minh").build());
        persons.add(Person.builder().age(22).name("Obama").build());
        persons.add(Person.builder().age(34).name("Bill Gate").build());

        DataPerson data = new DataPerson();
        data.setPersons(persons);
        Iterator<Person> iterator = data.iterator();
        while (iterator.hasNext()) {
            Person p = iterator.next();
            System.out.println(p.getName());
        }
    }
}

class DataPerson implements Iterable<Person> {
    List<Person> persons;

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public Iterator<Person> iterator() {
        return this.persons.iterator();
    }
}

