package guitest;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Register extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtField;
	private JTextField txtPassword;
	private JTextField txtRePassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 741, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Register");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(309, 70, 92, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(203, 142, 49, 14);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(274, 136, 229, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setBounds(181, 185, 71, 14);
		contentPane.add(lblUsername);
		
		txtField = new JTextField();
		txtField.setColumns(10);
		txtField.setBounds(274, 179, 229, 20);
		contentPane.add(txtField);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(181, 230, 71, 14);
		contentPane.add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(274, 224, 229, 20);
		contentPane.add(txtPassword);
		
		JLabel lblRePassword = new JLabel("Re-enter Pass");
		lblRePassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRePassword.setBounds(155, 276, 97, 14);
		contentPane.add(lblRePassword);
		
		txtRePassword = new JTextField();
		txtRePassword.setColumns(10);
		txtRePassword.setBounds(274, 270, 229, 20);
		contentPane.add(txtRePassword);
		
		JButton btnSignup = new JButton("Sign up");
		btnSignup.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSignup.setBounds(349, 312, 89, 23);
		contentPane.add(btnSignup);
	}

}
