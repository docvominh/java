package com.vominh.java.core.thread.parallel;

public class Person {
    private int id;
    private int age;
    private String name;

    public Person() {
    }

    public Person(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Id: %s, %s, %s", this.id, this.name, this.age);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
