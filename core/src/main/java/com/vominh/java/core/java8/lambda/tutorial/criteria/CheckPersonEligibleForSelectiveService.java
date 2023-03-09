package com.vominh.java.core.java8.lambda.tutorial.criteria;

import com.vominh.java.core.java8.lambda.tutorial.object.Person;

public class CheckPersonEligibleForSelectiveService implements CheckPerson {
    public boolean test(Person p) {
        return p.getGender() == Person.Sex.MALE &&
                p.getAge() >= 18 &&
                p.getAge() <= 40;
    }
}