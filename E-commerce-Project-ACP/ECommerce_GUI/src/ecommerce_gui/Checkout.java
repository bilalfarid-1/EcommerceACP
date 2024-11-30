package ecommerce_gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Checkout extends JFrame {
    private double subtotal;
    private JTextField emailField;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField cityField;
    private JComboBox<String> stateComboBox;
    private JTextField ccNumberField;
    private JTextField ccExpirationField;
    private JTextField ccCvvField;
    private JLabel subtotalLabel;
    private JPanel creditCardPanel;

    public Checkout(double subtotal) {
        this.subtotal = subtotal;

        setTitle("Checkout");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        add(panel);

        subtotalLabel = new JLabel("Total: Rs" + subtotal);
        subtotalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(subtotalLabel);

        panel.add(createFormGroup("Email Address:", emailField = new JTextField(20)));
        panel.add(createFormGroup("Full Name:", nameField = new JTextField(20)));
        panel.add(createFormGroup("Address:", addressField = new JTextField(20)));
        panel.add(createFormGroup("City:", cityField = new JTextField(20)));

        panel.add(createFormGroup("State:", stateComboBox = new JComboBox<>(new String[]{"KPK", "Punjab", "Sindh", "Balochistan", "Kashmir", "Gilgit", "Islamabad"})));

        JPanel paymentMethodPanel = new JPanel();
        paymentMethodPanel.setLayout(new FlowLayout());

        JButton cardButton = new JButton("Card");
        JButton codButton = new JButton("Cash on Delivery");

        cardButton.addActionListener(e -> showCreditCardFields(true));
        codButton.addActionListener(e -> showCreditCardFields(false));

        paymentMethodPanel.add(cardButton);
        paymentMethodPanel.add(codButton);

        panel.add(paymentMethodPanel);

        creditCardPanel = new JPanel();
        creditCardPanel.setLayout(new BoxLayout(creditCardPanel, BoxLayout.Y_AXIS));
        creditCardPanel.add(createFormGroup("CC Number:", ccNumberField = new JTextField(20)));
        creditCardPanel.add(createFormGroup("CC Expiration:", ccExpirationField = new JTextField(20)));
        creditCardPanel.add(createFormGroup("CVV:", ccCvvField = new JTextField(20)));
        creditCardPanel.setVisible(false);

        panel.add(creditCardPanel);

        JButton payButton = new JButton("Pay Rs" + subtotal);
        payButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        payButton.addActionListener(e -> processPayment());
        panel.add(payButton);
    }

    private JPanel createFormGroup(String label, JComponent field) {
        JPanel formGroup = new JPanel();
        formGroup.setLayout(new FlowLayout());
        formGroup.add(new JLabel(label));
        formGroup.add(field);
        return formGroup;
    }

    private void showCreditCardFields(boolean show) {
        creditCardPanel.setVisible(show);
        pack();
    }

    private void processPayment() {
        String email = emailField.getText();
        String fullName = nameField.getText();
        String address = addressField.getText();
        String city = cityField.getText();
        String state = (String) stateComboBox.getSelectedItem();
        String paymentMethod = creditCardPanel.isVisible() ? "Card" : "Cash on Delivery";
        String ccNumber = ccNumberField.getText();
        String ccExpiration = ccExpirationField.getText();
        String ccCvv = ccCvvField.getText();

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mystore", "root", "acp1");
            String query = "INSERT INTO orders (email, full_name, address, city, state, payment_method, cc_number, cc_expiration, cc_cvv, subtotal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, fullName);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, city);
            preparedStatement.setString(5, state);
            preparedStatement.setString(6, paymentMethod);
            preparedStatement.setString(7, ccNumber);
            preparedStatement.setString(8, ccExpiration);
            preparedStatement.setString(9, ccCvv);
            preparedStatement.setDouble(10, subtotal);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int orderId = generatedKeys.getInt(1);
                OrderConfirmation orderConfirmation = new OrderConfirmation(orderId);
                orderConfirmation.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Error: Order ID not generated.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error processing payment: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Checkout checkoutPage = new Checkout(100.0); // Example subtotal
            checkoutPage.setVisible(true);
        });
    }
}
