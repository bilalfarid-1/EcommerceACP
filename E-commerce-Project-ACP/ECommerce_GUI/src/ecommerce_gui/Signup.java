package ecommerce_gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Signup {
 public static void main(String[] args) {
     JFrame frame = new JFrame("Signup");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setSize(400, 300);

     JPanel panel = new JPanel();
     frame.add(panel);
     placeComponents(panel);

     frame.setVisible(true);
 }

 private static void placeComponents(JPanel panel) {
     panel.setLayout(null);

     JLabel userLabel = new JLabel("Username");
     userLabel.setBounds(10, 20, 80, 25);
     panel.add(userLabel);

     JTextField userText = new JTextField(20);
     userText.setBounds(100, 20, 165, 25);
     panel.add(userText);

     JLabel passwordLabel = new JLabel("Password");
     passwordLabel.setBounds(10, 50, 80, 25);
     panel.add(passwordLabel);

     JPasswordField passwordText = new JPasswordField(20);
     passwordText.setBounds(100, 50, 165, 25);
     panel.add(passwordText);

     JLabel emailLabel = new JLabel("Email");
     emailLabel.setBounds(10, 80, 80, 25);
     panel.add(emailLabel);

     JTextField emailText = new JTextField(20);
     emailText.setBounds(100, 80, 165, 25);
     panel.add(emailText);

     JLabel roleLabel = new JLabel("Role");
     roleLabel.setBounds(10, 110, 80, 25);
     panel.add(roleLabel);

     JComboBox<String> roleComboBox = new JComboBox<>(new String[]{"Customer", "Admin"});
     roleComboBox.setBounds(100, 110, 165, 25);
     panel.add(roleComboBox);

     JButton signupButton = new JButton("Signup");
     signupButton.setBounds(10, 140, 80, 25);
     panel.add(signupButton);

     signupButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             String username = userText.getText();
             String password = new String(passwordText.getPassword());
             String email = emailText.getText();
             String role = (String) roleComboBox.getSelectedItem();

             try {
                 Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mystore", "root", "acp1");
                 String query = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";
                 PreparedStatement preparedStatement = connection.prepareStatement(query);
                 preparedStatement.setString(1, username);
                 preparedStatement.setString(2, password);
                 preparedStatement.setString(3, email);
                 preparedStatement.setString(4, role);
                 preparedStatement.executeUpdate();
                 JOptionPane.showMessageDialog(null, "Signup Successful!");
             } catch (SQLException ex) {
                 ex.printStackTrace();
             }
         }
     });
 }
}

