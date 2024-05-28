package com.cycas.design.builder;

/**
 * 指挥者
 * @author xin.na
 * @since 2024/5/10 16:38
 */
public class PersonDirector {

    private PersonBuilder pb;

    public PersonDirector(PersonBuilder pb) {
        this.pb = pb;
    }

    public void createPerson() {
        pb.buildHead();
        pb.buildBody();
        pb.buildArmLeft();
        pb.buildArmRight();
        pb.buildLegLeft();
        pb.buildLegRight();
    }
}
