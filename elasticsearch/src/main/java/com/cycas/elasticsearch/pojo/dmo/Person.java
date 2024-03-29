package com.cycas.elasticsearch.pojo.dmo;

public class Person {

    private String personId;
    private String name;
    private String number;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("Person{personId='%s', name='%s', number='%s}", personId, name, number);
    }
}
