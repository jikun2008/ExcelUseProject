package com.test;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MidaPlayerKnoledge extends JFrame {
    public MidaPlayerKnoledge() {
         super("TestJScrollPane");
         this.setLayout(new GridLayout(2,1));
         this.setBounds(200, 200, 300, 300);
         JPanel panel = new JPanel(new GridLayout(20,5));
          
         //加一些标签  就能显示了
         JLabel[] num = new JLabel[100];
         for (int i = 0; i < num.length; i++) {
                    num[i] = new JLabel(""+(i+1));
                    panel.add(num[i]);
              }
              //panel.setPreferredSize(new Dimension(200,100));
         //这段代码去掉  否则会乱码  在GridLayout下不能设置组件的大小
          
         JScrollPane scrollPane = new JScrollPane(panel);
         scrollPane.setBounds(10, 10, 175, 70);
         JPanel panel1= new JPanel();
         this.getContentPane().add(scrollPane);
         this.getContentPane().add(panel1);
         this.setVisible(true);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        }
        public static void main(String[] args) {
         new MidaPlayerKnoledge();
        }
 
}