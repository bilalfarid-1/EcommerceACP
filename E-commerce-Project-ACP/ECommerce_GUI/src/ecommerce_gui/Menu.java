package ecommerce_gui;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Menu extends JFrame {
    private JPanel productsPanel;
    private JTextField searchField;
    private Cart cart;

    public Menu() {
        cart = new Cart();

        setTitle("E-commerce Shop");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the navigation bar
        JPanel navBar = new JPanel(new BorderLayout());
        navBar.setBackground(Color.LIGHT_GRAY);

        JLabel logoLabel = new JLabel("E-commerce Store");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        navBar.add(logoLabel, BorderLayout.WEST);

        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchProducts(searchField.getText()));

        JPanel searchPanel = new JPanel();
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        navBar.add(searchPanel, BorderLayout.CENTER);

        JButton cartButton = new JButton("Cart");
        cartButton.addActionListener(e -> {
            if (UserSession.getRole().equals("Customer")) {
                CartPage cartPage = new CartPage(cart);
                cartPage.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Access Denied! Admins cannot access the cart.");
            }
        });
        navBar.add(cartButton, BorderLayout.EAST);

        add(navBar, BorderLayout.NORTH);

        // Create the categories panel
        JPanel categoriesPanel = new JPanel();
        categoriesPanel.setLayout(new BoxLayout(categoriesPanel, BoxLayout.Y_AXIS));
        categoriesPanel.setPreferredSize(new Dimension(200, getHeight()));

        String[] categories = {"Electronics", "Clothing", "Books", "Home & Kitchen", "Sports"};
        for (String category : categories) {
            JButton categoryButton = new JButton(category);
            categoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            categoryButton.addActionListener(e -> loadProductsByCategory(category));
            categoriesPanel.add(categoryButton);
        }

        add(categoriesPanel, BorderLayout.WEST);

        // Create the products panel
        productsPanel = new JPanel();
        productsPanel.setLayout(new GridLayout(0, 3, 10, 10));
        JScrollPane scrollPane = new JScrollPane(productsPanel);
        add(scrollPane, BorderLayout.CENTER);

        loadProducts();

        // Add item button (visible only to admins)
        if (UserSession.getRole().equals("Admin")) {
            JButton addItemButton = new JButton("Add Item");
            addItemButton.addActionListener(e -> {
                Inventory inventory = new Inventory();
                inventory.setVisible(true);
            });
            add(addItemButton, BorderLayout.SOUTH);
        }
    }

    private void loadProducts() {
        loadProductsByCategory(null);
    }

    private void loadProductsByCategory(String category) {
        productsPanel.removeAll();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mystore", "root", "acp1");
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM products";
            if (category != null) {
                query += " WHERE category = '" + category + "'";
            }
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                String imagePath = resultSet.getString("image");
                int stock = resultSet.getInt("stock");

                Product product = new Product(id, name, description, price, imagePath, stock);

                // Create product panel
                JPanel productPanel = new JPanel();
                productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
                productPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                // Load the product image
                ImageIcon imageIcon = new ImageIcon(imagePath);
                Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                JLabel imageLabel = new JLabel(new ImageIcon(image));
                imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                productPanel.add(imageLabel);

                JLabel nameLabel = new JLabel(name);
                nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                productPanel.add(nameLabel);

                JLabel descriptionLabel = new JLabel(description);
                descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                productPanel.add(descriptionLabel);

                JLabel priceLabel = new JLabel("Price: Rs" + price);
                priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                productPanel.add(priceLabel);

                JButton addToCartButton = new JButton("Add to Cart");
                addToCartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                addToCartButton.addActionListener(e -> {
                    if (UserSession.getRole().equals("Customer")) {
                        cart.addItem(new CartItem(product, 1));
                        JOptionPane.showMessageDialog(null, "Item added to cart!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Access Denied! Admins cannot add items to the cart.");
                    }
                });
                productPanel.add(addToCartButton);

                productsPanel.add(productPanel);
            }

            productsPanel.revalidate();
            productsPanel.repaint();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading products: " + e.getMessage());
        }
    }

    private void searchProducts(String query) {
        // Implement search functionality
        // For now, just print the search query
        System.out.println("Searching for: " + query);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Menu menuPage = new Menu();
            menuPage.setVisible(true);
        });
    }
}
