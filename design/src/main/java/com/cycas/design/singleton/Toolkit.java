package com.cycas.design.singleton;

import javax.swing.*;

/**
 * 工具箱类
 * @author xin.na
 * @since 2024/5/17 11:13
 */
public class Toolkit extends JFrame {

    private static Toolkit toolkit;

    private Toolkit(String name) {
        super(name);
    }

    public static Toolkit getInstance() {
        if (toolkit == null || !toolkit.isVisible()) {
            toolkit = new Toolkit("工具箱");
            toolkit.setSize(150, 300);
            toolkit.setLocation(100, 1000);
            toolkit.setResizable(false);
            toolkit.setAlwaysOnTop(true);
            toolkit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            toolkit.setVisible(true);
        }
        return toolkit;
    }
}
