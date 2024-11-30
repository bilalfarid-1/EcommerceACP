package ecommerce_gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CartPage extends JFrame {
    private Cart cart;
    private JPanel cartPanel;
    private JLabel totalLabel;
    private JLabel taxLabel;
    private JLabel deliveryLabel;
    private JLabel subtotalLabel;

    public CartPage(Cart cart) {
        if (!UserSession.getRole().equals("Customer")) {
            JOptionPane.showMessageDialog(null, "Access Denied! Only customers can access this page.");
            dispose();
            return;
        }

        this.cart = cart;

        setTitle("Shopping Cart");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(cartPanel);
        add(scrollPane, BorderLayout.CENTER);

        loadCartItems();

        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));

        totalLabel = new JLabel();
        taxLabel = new JLabel();
        deliveryLabel = new JLabel("Delivery: Rs50");
        subtotalLabel = new JLabel();

        summaryPanel.add(totalLabel);
        summaryPanel.add(taxLabel);
        summaryPanel.add(deliveryLabel);
        summaryPanel.add(subtotalLabel);

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> {
            Checkout checkoutPage = new Checkout(Double.parseDouble(subtotalLabel.getText().replace("Subtotal: Rs", "")));
            checkoutPage.setVisible(true);
            dispose();
        });
        summaryPanel.add(checkoutButton);

        add(summaryPanel, BorderLayout.SOUTH);

        updateSummary();
    }

    private void loadCartItems() {
        cartPanel.removeAll();

        for (CartItem item : cart.getItems()) {
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
            itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Load the product image
            ImageIcon imageIcon = new ImageIcon(item.getProduct().getImagePath());
            Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            itemPanel.add(imageLabel);

            JLabel nameLabel = new JLabel(item.getProduct().getName());
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            itemPanel.add(nameLabel);

            JLabel descriptionLabel = new JLabel(item.getProduct().getDescription());
            descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            itemPanel.add(descriptionLabel);

            JPanel quantityPanel = new JPanel();
            quantityPanel.setLayout(new FlowLayout());

            JButton decreaseButton = new JButton("-");
            JLabel quantityLabel = new JLabel(String.valueOf(item.getQuantity()));
            JButton increaseButton = new JButton("+");

            decreaseButton.addActionListener(e -> {
                if (item.getQuantity() > 1) {
                    item.setQuantity(item.getQuantity() - 1);
                    quantityLabel.setText(String.valueOf(item.getQuantity()));
                    updateSummary();
                }
            });

            increaseButton.addActionListener(e -> {
                if (item.getQuantity() < item.getProduct().getStock()) {
                    item.setQuantity(item.getQuantity() + 1);
                    quantityLabel.setText(String.valueOf(item.getQuantity()));
                    updateSummary();
                } else {
                    JOptionPane.showMessageDialog(null, "Cannot add more than available stock!");
                }
            });

            quantityPanel.add(decreaseButton);
            quantityPanel.add(quantityLabel);
            quantityPanel.add(increaseButton);

            itemPanel.add(quantityPanel);

            JButton removeButton = new JButton("Remove");
            removeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            removeButton.addActionListener(e -> {
                cart.removeItem(item);
                loadCartItems();
                updateSummary();
            });
            itemPanel.add(removeButton);

            JLabel priceLabel = new JLabel("Price: Rs" + item.getProduct().getPrice() * item.getQuantity());
            priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            itemPanel.add(priceLabel);

            cartPanel.add(itemPanel);
        }

        cartPanel.revalidate();
        cartPanel.repaint();
    }

    private void updateSummary() {
        double total = cart.getTotal();
        double tax = total * 0.10;
        double delivery = 50;
        double subtotal = total + tax + delivery;

        totalLabel.setText("Total: Rs" + total);
        taxLabel.setText("Tax (10%): Rs" + tax);
        subtotalLabel.setText("Subtotal: Rs" + subtotal);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Cart cart = new Cart();
            CartPage cartPage = new CartPage(cart);
            cartPage.setVisible(true);
        });
    }
}

