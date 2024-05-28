package com.cycas.design.singleton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 工具箱事件类
 * @author xin.na
 * @since 2024/5/17 11:05
 */
public class ToolkitListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Toolkit.getInstance();
    }
}
