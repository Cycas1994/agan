package com.cycas.elasticsearch.pojo.dmo;

public class Employee {
    private String name;
    private String sex;
    private String occupation;
    private int salary;

    public Employee() {
    }

    public Employee(String name, String sex, String occupation, int salary) {
        this.name = name;
        this.sex = sex;
        this.occupation = occupation;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
