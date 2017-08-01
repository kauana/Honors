//********************************************************************************
//  Honors1.java   Author: Kauana dos Santos
//  Class: CS 111B - Section: 002 - First Honors Project
//  Due Date: 12/10/15
//  Demonstrates the use of nested panels e image icons.
//  Algorithm:
//  I. Create a Frame
//  II. Create Components
//  III. Create subPanels and Panel
//  IV. Add SubPanel to Panel
//  V. Add Panel to Frame
//********************************************************************************

import java.awt.*;
import javax.swing.*;

public class Honors1 {

    public static void main(String []args) {

        // Create a Frame
        JFrame frame = new JFrame("Halloween Themed Panels");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500, 100));

        // Create Components
        ImageIcon image1 = new ImageIcon("BlightWalker.gif");
        ImageIcon image2 = new ImageIcon("Werewolf.gif");
        ImageIcon image3 = new ImageIcon("Pumpkin.gif");
        ImageIcon image4 = new ImageIcon("Phantasm.gif");

        JLabel label1, label2, label3, label4;
        label1 = new JLabel("BlightWalker Right", image1, SwingConstants.LEFT);
        label1.setHorizontalTextPosition(SwingConstants.LEFT);
        label2 = new JLabel("Werewolf Right", image2, SwingConstants.RIGHT);
        label2.setHorizontalTextPosition(SwingConstants.LEFT);
        label3 = new JLabel("Pumpkin Left", image3, SwingConstants.LEFT);
        label3.setForeground(Color.white);
        label4 = new JLabel("Phantasm Left", image4, SwingConstants.RIGHT);
        label4.setForeground(Color.white);

        // Create subPanels
        JPanel subPanel1 = new JPanel();
        subPanel1.setPreferredSize(new Dimension(500, 100));
        Color customColor = new Color(95, 52, 117); // Creates a Purple color
        subPanel1.setBackground(customColor);

        JPanel subPanel2 = new JPanel();
        subPanel2.setPreferredSize(new Dimension(500, 100));
        subPanel2.setBackground(Color.black);

        // Add Components to SubPanels
        subPanel1.add(label1);
        subPanel1.add(label2);
        subPanel2.add(label3);
        subPanel2.add(label4);

        frame.getContentPane().add(subPanel1, BorderLayout.WEST);
        frame.getContentPane().add(subPanel2, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

    }
}
