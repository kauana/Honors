//********************************************************************************
//  Honors2.java   Author: Kauana dos Santos
//  Class: CS 111B - Section: 002 - Second Honors Project
//  Due Date: 12/10/15
//  Demonstrates the design and implementation of an application that helps a café take orders.
//  Algorithm:
//  1. Create components and get them to display 
//  2. Create a special Panel class that can handle our menu order panels
//  3. Create a class that can handle rows in our panel, which are always similar (item, price, size, qty)
//  4. Create listener class that handle combobox events
//********************************************************************************

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Honors2 {

    public static void main(String[] args) {
        final String[] SIZES = {"Regular", "Large"}; // to be used for Size combobox
        final Integer[] QUANTITIES = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // to be used for quantity combobox

        // Creates frame
        JFrame frame = new JFrame("Café Orders");
        frame.setPreferredSize(new Dimension(600, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creates tabbed panes with different icons
        JTabbedPane tabbedPane = new JTabbedPane();
        ImageIcon icon1 = new ImageIcon("soup.png");
        ImageIcon icon2 = new ImageIcon("pizza.png");
        ImageIcon icon3 = new ImageIcon("dessert.jpg");
        ImageIcon icon4 = new ImageIcon("coffee.gif");

        // Creates 4 MenuContainer Panels for the tabbed panes
        MenuContainer appetizersPanel = new MenuContainer();
        MenuContainer entreesPanel = new MenuContainer();
        MenuContainer dessertsPanel = new MenuContainer();
        MenuContainer beveragesPanel = new MenuContainer();

        // Add the 4 Panels to the TabbedPanes
        tabbedPane.addTab("Appetizers", icon1, appetizersPanel);
        tabbedPane.addTab("Pizzas", icon2, entreesPanel);
        tabbedPane.addTab("Desserts", icon3, dessertsPanel);
        tabbedPane.addTab("Beverages", icon4, beveragesPanel);

        // RowItem class creates every row in the MenuContainer panel
        // It creates 4 columns: Food Item, price, combobox with size options and
        // a combobox with quantity options. The parameters are given to the RowItem constructor
        // and later the method createRow from the MenuContainer class is called to create the
        // rows of the program.
        RowItem appetizer1 = new RowItem("Chicken Wings", 6.50, SIZES, QUANTITIES);
        RowItem appetizer2 = new RowItem("Tomato Soup", 4.00, SIZES, QUANTITIES);
        RowItem appetizer3 = new RowItem("Caesar Salad", 5.50, SIZES, QUANTITIES);
        appetizersPanel.createRow(appetizer1);
        appetizersPanel.createRow(appetizer2);
        appetizersPanel.createRow(appetizer3);

        RowItem entree1 = new RowItem("Pepperoni and Cheese", 14.00, SIZES, QUANTITIES);
        RowItem entree2 = new RowItem("Spinach and Ricotta", 13.00, SIZES, QUANTITIES);
        RowItem entree3 = new RowItem("Chicken Alfredo", 13.75, SIZES, QUANTITIES);
        entreesPanel.createRow(entree1);
        entreesPanel.createRow(entree2);
        entreesPanel.createRow(entree3);

        RowItem dessert1 = new RowItem("Cheesecake", 5.00, SIZES, QUANTITIES);
        RowItem dessert2 = new RowItem("Chocolate Mousse", 5.75, SIZES, QUANTITIES);
        RowItem dessert3 = new RowItem("Raspberry Sorbet", 4.50, SIZES, QUANTITIES);
        dessertsPanel.createRow(dessert1);
        dessertsPanel.createRow(dessert2);
        dessertsPanel.createRow(dessert3);

        RowItem beverage1 = new RowItem("Coffee", 2.75, SIZES, QUANTITIES);
        RowItem beverage2 = new RowItem("Hot Chocolate", 2.00, SIZES, QUANTITIES);
        RowItem beverage3 = new RowItem("Lemonade", 1.50, SIZES, QUANTITIES);
        beveragesPanel.createRow(beverage1);
        beveragesPanel.createRow(beverage2);
        beveragesPanel.createRow(beverage3);

        // createFooter method from the MenuContainer class adds footer components in all panels
        appetizersPanel.createFooter();
        entreesPanel.createFooter();
        dessertsPanel.createFooter();
        beveragesPanel.createFooter();

        // Add tabbedPane to frame
        frame.getContentPane().add(tabbedPane);
        frame.pack();
        frame.setVisible(true);
    }
}

// Class RowItem has methods that create the rows for our program, it also has useful acessors and listeners for the comboboxes
class RowItem {
    private NumberFormat fmt = NumberFormat.getCurrencyInstance();
    private String item;
    private double price;
    private JComboBox<String> sizeOption;
    private JComboBox<Integer> quantityOption;

    public RowItem(String item, double price, String[] sizes, Integer[] quantities) {
        this.item = item;
        this.price = price;
        this.sizeOption = new JComboBox<>(sizes);
        this.quantityOption = new JComboBox<>(quantities);
    }
    // Add row components to the given panel and listens to combobox events
    public void addToPanel(MenuContainer panel) {
        panel.add(new JLabel(item));
        panel.add(new JLabel(fmt.format(price)));
        panel.add(sizeOption);
        panel.add(quantityOption);
        // both comboboxes share the same interface
        sizeOption.addActionListener(new ComboBoxListener(panel));
        quantityOption.addActionListener(new ComboBoxListener(panel));
    }

    // returns selected item on the quantity combobox, object is casted as an Integer
    public Integer getSelectedQuantity() {
        return (Integer) quantityOption.getSelectedItem();
    }
    public double getPrice() {
        return this.price;
    }
    // retuns selected size on the combobox, object is casted as a String
    public String getSelectedSize() {
        return (String) sizeOption.getSelectedItem();
    }
}

// MenuContainer inherits characteristics from the JPanel class and has some methods that benefit us in creating the panels for our program
@SuppressWarnings("serial")
class MenuContainer extends JPanel {
    private JLabel item  = new JLabel("Items:");
    private JLabel price = new JLabel("Price:");
    private JLabel size = new JLabel("Size:");
    private JLabel quantity = new JLabel("Quantity:");
    private JLabel priceInfo = new JLabel("*Large Size Add $2.00");
    private JLabel empty = new JLabel(); // empty panel to add space between components
    private NumberFormat fmt = NumberFormat.getCurrencyInstance();
    private ArrayList<RowItem> arrayRows;
    private JLabel subtotal;
    private GridLayout layout;
    private double totalCost = 0.0;

    public MenuContainer() {
        subtotal = new JLabel("Subtotal: " + fmt.format(totalCost)); // formats totalcost so that it is displayed as a currency $0.00
        layout = new GridLayout(2, 4); // initializer a layout with 2 rows and 4 columns

        // Add first row of labels for the panel which is the same for all panels in this program
        this.setLayout(layout);
        this.add(item);
        this.add(price);
        this.add(size);
        this.add(quantity);

        this.arrayRows = new ArrayList<RowItem>(); // arrayRows will store RowItem objects
    }

    public void createRow(RowItem item) {
        item.addToPanel(this); // calls addToPanel from RowItem class to add argument to panel 
        arrayRows.add(item); // every RowItem object is stored in arrayRows for later use
        layout.setRows(layout.getRows() + 1); // every time a row is added, gridlayout increases number of rows, column is always the same
    }

    // create footer components for panels
    public void createFooter() {
        this.add(priceInfo);
        this.add(empty);
        this.add(subtotal);
    }

    // Calculates subtotal as info is being gathered, if a large item is added, it adds $2 to every item marked as large
    public double calculateSubtotal() {
        RowItem[] items = arrayRows.toArray(new RowItem[arrayRows.size()]); // change arraylist to array so we can access obj's price, size and qty
        double total = 0.0;
        int priceSize = 0;
        // items is not an array of RowItems so we can access every objects price, size and quantity
        for (int i = 0; i < items.length; i++) {
            if (items[i].getSelectedSize().equals("Large")) {
                priceSize = 2;
            }
            total = total + ((items[i].getPrice() + priceSize) * items[i].getSelectedQuantity());
        }
        return total;
    }
    // cost mutator, updates cost
    public void setTotalCost(double newCost) {
        totalCost = newCost;
        subtotal.setText("Subtotal: " + fmt.format(totalCost));
    }
}

// Listener class for combobox events, when a person chooses a quantity or a size, info is gathered and used to display a new cost in our application, every panel has its own subtotal accumulator
class ComboBoxListener implements ActionListener {
    MenuContainer currentPanel;

    public ComboBoxListener(MenuContainer panel) {
        currentPanel = panel;
    }

    public void actionPerformed(ActionEvent event) {
        currentPanel.setTotalCost(currentPanel.calculateSubtotal());
    }
}
