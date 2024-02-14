package gr.aueb.cf.schoolApp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import org.mindrot.jbcrypt.BCrypt;

import gr.aueb.cf.schoolApp.util.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameTxt;
	private JPasswordField passwordField;
	private JSeparator separator;
//	private String hashedPassword;
	private String storedPassword;

	/**
	 * Create the frame.
	 */
	public LoginPage() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				usernameTxt.setText("");
				passwordField.setText("");
			}
		});
		setTitle("Login Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel usernameLbl = new JLabel("Username");
		usernameLbl.setForeground(new Color(0, 0, 255));
		usernameLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		usernameLbl.setBounds(59, 50, 87, 20);
		contentPane.add(usernameLbl);
		
		JLabel passwordLbl = new JLabel("Password");
		passwordLbl.setForeground(Color.BLUE);
		passwordLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		passwordLbl.setBounds(59, 104, 87, 20);
		contentPane.add(passwordLbl);
		
		
		// Username text field - action on ENTER key
		usernameTxt = new JTextField();
		
		usernameTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String inputUsername = usernameTxt.getText().trim();
					String inputPassword = String.valueOf(passwordField.getPassword()).trim();
					
					if (inputUsername.equals("") || inputPassword.equals("")) {
						JOptionPane.showMessageDialog(null, "Please enter username or/and password!", "USERNAME", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
			}
		});
		
		usernameTxt.setBounds(156, 43, 186, 35);
		contentPane.add(usernameTxt);
		usernameTxt.setColumns(10);
		
		
		// Password field - action on enter key 
		passwordField = new JPasswordField();
		
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					String inputUsername = usernameTxt.getText().trim();
					String inputPassword = String.valueOf(passwordField.getPassword()).trim();
					String password = System.getenv("schoolAppAdminPSWD");
					
					if (inputUsername.equals("") || inputPassword.equals("")) {
						JOptionPane.showMessageDialog(null, "Please enter username or/and password!", "USERNAME", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					if ((inputUsername.equals("admin")) && (inputPassword.equals(password))) {
						Main.getLoginPage().setVisible(false);
						Main.getSearchUserForm().setVisible(true);
						System.out.print("Successfull connection!");
					} else {
						String sql = "SELECT PASSWORD FROM USERS WHERE USERNAME = ?";
						
						try (Connection conn = DBUtil2.getConnection();
								PreparedStatement ps = conn.prepareStatement(sql)) {
							
							ps.setString(1, inputUsername);
							ResultSet rs = ps.executeQuery();
							if (rs.next()) {
								storedPassword = rs.getString("PASSWORD");
							} else {
								JOptionPane.showMessageDialog(null, "User not found", "Error", JOptionPane.WARNING_MESSAGE);
								return;
							}
							
							if (inputPassword.equals(storedPassword)) {
								Main.getMenu().setVisible(true);
								Main.getLoginPage().setVisible(false);
							} else {
								JOptionPane.showMessageDialog(null, "Error in password", "Error", JOptionPane.PLAIN_MESSAGE);
							}
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Something went wrong with insertion, Please try again", "Error", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
				
			}
		});
		passwordField.setBounds(156, 97, 186, 35);
		contentPane.add(passwordField);
		
		separator = new JSeparator();
		separator.setBounds(43, 167, 365, 2);
		contentPane.add(separator);
		
		
		// Login Button
		JButton loginBtn = new JButton("Login");
		
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputUsername = usernameTxt.getText().trim();
				String inputPassword = String.valueOf(passwordField.getPassword()).trim();
				String password = System.getenv("schoolAppAdminPSWD");
				
				if (inputUsername.equals("") || inputPassword.equals("")) {
					return;
				}
				
				if ((inputUsername.equals("admin")) && (inputPassword.equals(password))) {
					Main.getLoginPage().setVisible(false);
					Main.getSearchUserForm().setVisible(true);
					Main.getSearchUserForm().setEnabled(true);
					System.out.print("Successfull connection!");
				} else {
					String sql = "SELECT PASSWORD FROM USERS WHERE USERNAME = ?";
					
					try (Connection conn = DBUtil2.getConnection();
							PreparedStatement ps = conn.prepareStatement(sql)) {
						
						ps.setString(1, inputUsername);
						ResultSet rs = ps.executeQuery();
						if (rs.next()) {
							storedPassword = rs.getString("PASSWORD");
						} else {
							JOptionPane.showMessageDialog(null, "User not found", "Error", JOptionPane.WARNING_MESSAGE);
							return;
						}
						
						if (inputPassword.equals(storedPassword)) {
							Main.getMenu().setVisible(true);
							Main.getLoginPage().setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, "Error in password", "Error", JOptionPane.PLAIN_MESSAGE);
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Something went wrong with insertion, Please try again", "Error", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		loginBtn.setForeground(new Color(0, 0, 255));
		loginBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		loginBtn.setBounds(243, 194, 99, 35);
		contentPane.add(loginBtn);
	}
}
