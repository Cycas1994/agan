package com.cycas.design.singleton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 窗体类
 * @author xin.na
 * @since 2024/5/17 10:57
 */
public class SingletonWindow {

    public SingletonWindow() {
        JFrame frame = new JFrame("单例模式");
        frame.setSize(1024, 768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        JButton button = new JButton("打开工具箱");
        button.setBounds(10, 10, 120, 25);
        button.addActionListener(new ToolkitListener());
        panel.add(button);

        JButton button2 = new JButton("打开工具箱2");
        button2.setBounds(130, 10, 120, 25);
        button2.addActionListener(new ToolkitListener());
        panel.add(button2);

        frame.setVisible(true);
    }
}
