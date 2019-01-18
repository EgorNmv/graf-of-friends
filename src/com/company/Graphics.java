package com.company;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Graphics extends JFrame {
    public static String arg1;
    public static String arg2;
    private JTextField input = new JTextField("", 5);
    private JTextField input2 = new JTextField("", 5);
    private JLabel label = new JLabel("Имя + Пустая строка");
    private JLabel label6 = new JLabel("Пользователь + Имя");
    private JLabel label2 = new JLabel("Пользователь + Имя");
    private JLabel label3 = new JLabel("Пользователь + Пустая строка");
    private JLabel label4 = new JLabel("Пустая строка + Пустая строка");
    private JLabel label5 = new JLabel("Пользователь + Пустая строка");
    private JLabel label7 = new JLabel("Пользователь + Пустая строка");
    private JButton button = new JButton("Добавить человека");
    private JButton button6 = new JButton("Добавить друга");
    private JButton button2 = new JButton("Удалить друга");
    private JButton button3 = new JButton("Список друзей");
    private JButton button4 = new JButton("Топ");
    private JButton button5 = new JButton("Рекомендации");
    private JButton button7 = new JButton("Удаление пользователя");

    public Graphics() {

        super("Граф друзей");
        this.setBounds(750, 350, 400, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(0, 2));
        button.addActionListener(new ButtonEventListener());
        button2.addActionListener(new ButtonEventListener2());
        button3.addActionListener(new ButtonEventListener3());
        button4.addActionListener(new ButtonEventListener4());
        button5.addActionListener(new ButtonEventListener5());
        button6.addActionListener(new ButtonEventListener6());
        button7.addActionListener(new ButtonEventListener7());
        container.add(input);
        container.add(input2);
        container.add(button);
        container.add(label);
        container.add(button6);
        container.add(label6);
        container.add(button2);
        container.add(label2);
        container.add(button3);
        container.add(label3);
        container.add(button4);
        container.add(label4);
        container.add(button5);
        container.add(label5);
        container.add(button7);
        container.add(label7);

    }

    class ButtonEventListener7 implements ActionListener{
        public void actionPerformed (ActionEvent e)
        {
            arg1=input.getText();
            Main.deletePerson();
            JOptionPane.showMessageDialog(null,"Успешно", "Граф друзей", JOptionPane.PLAIN_MESSAGE);
        }
    }
    class ButtonEventListener implements ActionListener {
        public void actionPerformed (ActionEvent e)
        {
            arg1 = input.getText();
            Main.addG();
            JOptionPane.showMessageDialog(null,"Успешно", "Граф друзей", JOptionPane.PLAIN_MESSAGE);
        }
    }
    class ButtonEventListener6 implements ActionListener {
        public void actionPerformed (ActionEvent e)
        {
            arg1 = input.getText();
            arg2 = input2.getText();
            Main.addEdgeG();
            JOptionPane.showMessageDialog(null,"Успешно", "Граф друзей", JOptionPane.PLAIN_MESSAGE);
        }
    }
    class ButtonEventListener2 implements ActionListener {
        public void actionPerformed (ActionEvent e) {
           arg1 = input.getText();
           arg2 = input2.getText();
           Main.deleteG();
            JOptionPane.showMessageDialog(null,"Успешно", "Граф друзей", JOptionPane.PLAIN_MESSAGE);
        }
    }
    class ButtonEventListener3 implements ActionListener {
        public void actionPerformed (ActionEvent e)
        {
            arg1 = input.getText();
            Main.friendsUserG();
            JOptionPane.showMessageDialog(null,"Успешно", "Граф друзей", JOptionPane.PLAIN_MESSAGE);
        }
    }
    class ButtonEventListener4 implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            arg1 = input.getText();
            Main.topG();
        }
    }
    class ButtonEventListener5 implements ActionListener {
        public void actionPerformed (ActionEvent e)
        {
            arg1 = input.getText();
            Main.recommendG();
        }
    }

}



