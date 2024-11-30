package ecommerce_gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {
 public static void main(String[] args) {
     JFrame frame = new JFrame("Login");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setSize(400, 300);

     JPanel panel = new JPanel();
     frame.add(panel);
     placeComponents(panel, frame);

     frame.setVisible(true);
 }

 private static void placeComponents(JPanel panel, JFrame frame) {
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

     JButton loginButton = new JButton("Login");
     loginButton.setBounds(10, 80, 80, 25);
     panel.add(loginButton);

     loginButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             String username = userText.getText();
             String password = new String(passwordText.getPassword());

             if (authenticateUser(username, password)) {
                 JOptionPane.showMessageDialog(null, "Login Successful!");
                 frame.dispose();
                 Menu menuPage = new Menu();
                 menuPage.setVisible(true);
             } else {
                 JOptionPane.showMessageDialog(null, "User does not exist!");
                 userText.setText("");
                 passwordText.setText("");
             }
         }
     });
 }

 private static boolean authenticateUser(String username, String password) {
     try {
         Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mystore", "root", "acp1");
         String query = "SELECT * FROM users WHERE username = ? AND password = ?";
         PreparedStatement preparedStatement = connection.prepareStatement(query);
         preparedStatement.setString(1, username);
         preparedStatement.setString(2, password);
         ResultSet resultSet = preparedStatement.executeQuery();

         if (resultSet.next()) {
             String role = resultSet.getString("role");
             // Store the role in a global variable or session
             UserSession.setRole(role);
             return true;
         }
         return false;
     } catch (Exception e) {
         e.printStackTrace();
         return false;
     }
 }
}
