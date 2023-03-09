package com.vominh.java.core.collections;

public class Person implements Comparable<Person> {

    int idNumber;
    int age;
    String name;
    boolean vaccine;

    public int getIdNumber() {
        return idNumber;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public boolean isVaccine() {
        return vaccine;
    }

    public static PersonBuilder builder() {
        return new Person.PersonBuilder();
    }

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }

    public static final class PersonBuilder {
        private int idNumber;
        private int age;
        private String name;
        private boolean vaccine;

        private PersonBuilder() {
        }

        public static PersonBuilder builder() {
            return new PersonBuilder();
        }

        public PersonBuilder idNumber(int idNumber) {
            this.idNumber = idNumber;
            return this;
        }

        public PersonBuilder age(int age) {
            this.age = age;
            return this;
        }

        public PersonBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PersonBuilder vaccine(boolean vaccine) {
            this.vaccine = vaccine;
            return this;
        }

        public Person build() {
            Person person = new Person();
            person.idNumber = this.idNumber;
            person.age = this.age;
            person.name = this.name;
            person.vaccine = this.vaccine;
            return person;
        }
    }
}

