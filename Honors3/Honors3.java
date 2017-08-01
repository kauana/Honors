//********************************************************************************
//  Honors3.java   Author: Kauana dos Santos
//  Class: CS 111B - Section: 002 - Third Honors Project
//  Due Date: 12/10/15
//  Demonstrates the use of slider components for graphing a quadratic equation.
//  Algorithm:
//  1. Create driver program
//  2. Create SliderPanel and its components
//  3. Create inner class Graphing Panel
//  4. Add GraphingPanel to SliderPanel
//  5. Add SliderPanel to frame
//  6. Create Listener class for SliderPanel
//********************************************************************************

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;


public class Honors3 {

    // Presents a frame with a SliderPanel containing sliders and a smaller panel that graphs
    // a quadratic equation based on the slider values
    public static void main(String []args) {

        JFrame frame = new JFrame("Graphing Quadratic Equations");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new SliderPanel());
        frame.pack();
        frame.setVisible(true);

    }
}

class SliderPanel extends JPanel {

    private final int VALUE_MIN, VALUE_MAX, VALUE_INIT;
    private JSlider sliderA, sliderB, sliderC;
    private JLabel aLabel, bLabel, cLabel;
    private int aValue, bValue, cValue;
    private BoxLayout layout;

    //Sets up the sliders and their labels, aligning them using a box layout.
    public SliderPanel() {
        VALUE_MIN = -50;
        VALUE_MAX = 50;
        VALUE_INIT = 0;

        sliderA = new JSlider(JSlider.HORIZONTAL, VALUE_MIN, VALUE_MAX, VALUE_INIT);
        sliderA.setMajorTickSpacing(5);
        sliderA.setMinorTickSpacing(1);
        sliderA.setPaintTicks(true);
        sliderA.setPaintLabels(true);
        sliderB = new JSlider(JSlider.HORIZONTAL, VALUE_MIN, VALUE_MAX, VALUE_INIT);
        sliderB.setMajorTickSpacing(5);
        sliderB.setMinorTickSpacing(1);
        sliderB.setPaintTicks(true);
        sliderB.setPaintLabels(true);
        sliderC = new JSlider(JSlider.HORIZONTAL, VALUE_MIN, VALUE_MAX, VALUE_INIT);
        sliderC.setMajorTickSpacing(5);
        sliderC.setMinorTickSpacing(1);
        sliderC.setPaintTicks(true);
        sliderC.setPaintLabels(true);

        SliderListener listener = new SliderListener();
        sliderA.addChangeListener(listener);
        sliderB.addChangeListener(listener);
        sliderC.addChangeListener(listener);

        aLabel = new JLabel("Choose a value for a:");
        bLabel = new JLabel("Choose a value for b:");
        cLabel = new JLabel("Choose a value for c:");

        layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);
        this.setPreferredSize(new Dimension(500, 700));
        this.add(aLabel);
        this.add(sliderA);
        this.add (Box.createRigidArea(new Dimension(0, 20)));
        this.add(bLabel);
        this.add(sliderB);
        this.add (Box.createRigidArea (new Dimension(0, 20)));
        this.add(cLabel);
        this.add(sliderC);
        this.add(new GraphingPanel()); //adds the Inner panel to the SliderPanel

    }

    //Inner panel, responsible for drawing the parabola based on the sliders values.
    private class GraphingPanel extends JPanel {
        private final int SIZE = 500;
        private final int SCALE = 5;

        public GraphingPanel() {
            this.setPreferredSize(new Dimension(SIZE, SIZE));
            this.setBackground(Color.white);
        }

        @Override
        public void paintComponent(Graphics page) {
            super.paintComponent(page);
            double y;
            
            // draws guide lines, scale = 5
            Color color = new Color(235, 235, 235);
            page.setColor(color);
            for (int x = 0; x <= SIZE; x+= 5) {
                page.drawLine(x, 0, x, SIZE);
                page.drawLine(0, x, SIZE, x); 
            }

            page.setColor(Color.black);
            // draws x coordinate line
            page.drawLine(0, SIZE / 2, SIZE, SIZE / 2);
            // draws y coordinate line
            page.drawLine(SIZE / 2, 0, SIZE / 2, SIZE);

            // draws the parabola
            page.setColor(Color.red);
            for (double x = -SIZE/SCALE; x <= SIZE/SCALE; x += 0.125) { // graph goes from -50 to 50
                y = (aValue * x * x + bValue * x + cValue);
                // increases the parabola's scale
                int scaleX = (int) (x * SCALE) + SIZE / 2;
                int scaleY = (int) (-y * SCALE) + SIZE / 2;
                page.fillOval(scaleX, scaleY, 3, 3);
            }
        }
    }
    
    //Represents the listener for all three sliders
    private class SliderListener implements ChangeListener {
        //Gets the value of each slider, then updates the labels
        public void stateChanged(ChangeEvent event) {
            aValue = sliderA.getValue();
            bValue = sliderB.getValue();
            cValue = sliderC.getValue();

            aLabel.setText("Choose a value for a: " + aValue);
            bLabel.setText("Choose a value for b: " + bValue);
            cLabel.setText("Choose a value for c: " + cValue);
            //As sliders change, parabola is redrawn
            repaint();
        }
    }
}



