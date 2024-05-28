package com.cycas.design.memento;

import com.cycas.design.memento.Memento;

/**
 * 管理者
 * @author xin.na
 * @since 2024/5/14 15:50
 */
public class CareTaker {

    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
