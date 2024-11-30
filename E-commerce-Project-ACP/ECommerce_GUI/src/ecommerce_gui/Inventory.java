package ecommerce_gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.*;

public class Inventory extends JFrame {
    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField priceField;
    private JTextField stockField;
    private JTextField imageField;
    private JComboBox<String> categoryComboBox;

    public Inventory() {
        if (!UserSession.getRole().equals("Admin")) {
            JOptionPane.showMessageDialog(null, "Access Denied! Only admins can access this page.");
            dispose();
            return;
        }

        setTitle("Add Item");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 20, 80, 25);
        panel.add(nameLabel);

        nameField = new JTextField(20);
        nameField.setBounds(100, 20, 165, 25);
        panel.add(nameField);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(10, 50, 80, 25);
        panel.add(descriptionLabel);

        descriptionField = new JTextField(20);
        descriptionField.setBounds(100, 50, 165, 25);
        panel.add(descriptionField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(10, 80, 80, 25);
        panel.add(priceLabel);

        priceField = new JTextField(20);
        priceField.setBounds(100, 80, 165, 25);
        panel.add(priceField);

        JLabel stockLabel = new JLabel("Stock:");
        stockLabel.setBounds(10, 110, 80, 25);
        panel.add(stockLabel);

        stockField = new JTextField(20);
        stockField.setBounds(100, 110, 165, 25);
        panel.add(stockField);

        JLabel imageLabel = new JLabel("Image Path:");
        imageLabel.setBounds(10, 140, 80, 25);
        panel.add(imageLabel);

        imageField = new JTextField(20);
        imageField.setBounds(100, 140, 165, 25);
        panel.add(imageField);

        JButton browseButton = new JButton("Browse");
        browseButton.setBounds(270, 140, 80, 25);
        panel.add(browseButton);

        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "/Images"));
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    imageField.setText(selectedFile.getPath());
                }
            }
        });

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(10, 170, 80, 25);
        panel.add(categoryLabel);

        categoryComboBox = new JComboBox<>(new String[]{"Electronics", "Clothing", "Books", "Home & Kitchen", "Sports"});
        categoryComboBox.setBounds(100, 170, 165, 25);
        panel.add(categoryComboBox);

        JButton addButton = new JButton("Add Item");
        addButton.setBounds(10, 200, 100, 25);
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });
    }

    private void addItem() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        double price = Double.parseDouble(priceField.getText());
        int stock = Integer.parseInt(stockField.getText());
        String imagePath = imageField.getText();
        String category = (String) categoryComboBox.getSelectedItem();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mystore", "root", "acp1");
            String query = "INSERT INTO products (name, description, price, stock, image, category) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setDouble(3, price);
            preparedStatement.setInt(4, stock);
            preparedStatement.setString(5, imagePath);
            preparedStatement.setString(6, category);
            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Item added successfully!");
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding item: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Inventory inventory = new Inventory();
            inventory.setVisible(true);
        });
    }
}
