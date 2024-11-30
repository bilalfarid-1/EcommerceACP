package ecommerce_gui;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderConfirmation extends JFrame {
    public OrderConfirmation(int orderId) {
        setTitle("Order Confirmation");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding
        add(panel);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mystore", "root", "acp1");
            String query = "SELECT * FROM orders WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String fullName = resultSet.getString("full_name");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                String paymentMethod = resultSet.getString("payment_method");
                double subtotal = resultSet.getDouble("subtotal");

                panel.add(createSection("Order #" + orderId));
                panel.add(createSection("Thank You " + fullName + "!"));
                panel.add(createSection("Your Order is Confirmed"));
                panel.add(createSection("We have accepted your order, and we're getting it ready. A confirmation mail has been sent to " + email));

                panel.add(createSection("Customer Details"));
                panel.add(createDetail("Email", email));
                panel.add(createDetail("Phone", "(999) 999-9999")); 
                panel.add(createDetail("Billing Address", fullName + "\n " + address + "\n " + city + ", " + state));
                panel.add(createDetail("Shipping Address", fullName + "\n " + address + "\n " + city + ", " + state));

                panel.add(createSection("Order Details"));
                panel.add(createDetail("Subtotal", "Rs" + subtotal));
                panel.add(createDetail("Shipping", "Rs50"));
                panel.add(createDetail("Payment Method", paymentMethod));
                panel.add(createDetail("Total", "Rs" + (subtotal + 50)));

                JButton continueShoppingButton = new JButton("Continue Shopping");
                continueShoppingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                continueShoppingButton.addActionListener(e -> dispose());
                panel.add(continueShoppingButton);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving order details: " + e.getMessage());
        }
    }

    private JPanel createSection(String text) {
        JPanel section = new JPanel();
        section.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        section.add(label);
        return section;
    }

    private JPanel createDetail(String label, String value) {
        JPanel detail = new JPanel();
        detail.setLayout(new FlowLayout(FlowLayout.LEFT));
        detail.add(new JLabel(label + ": " + value));
        return detail;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OrderConfirmation orderConfirmationPage = new OrderConfirmation(1); // Example order ID
            orderConfirmationPage.setVisible(true);
        });
    }
}

