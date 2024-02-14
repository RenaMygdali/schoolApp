package gr.aueb.cf.schoolApp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import org.mindrot.jbcrypt.BCrypt;

import gr.aueb.cf.schoolApp.util.*;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InsertUserForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameTxt;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public InsertUserForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				usernameTxt.setText("");
				passwordField.setText("");
			}
		});
		setTitle("Εισαγωγή Χρήστη");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
	
		contentPane.setBackground(new Color(236, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel userNameLbl = new JLabel("Username");
		userNameLbl.setForeground(new Color(180, 16, 12));
		userNameLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		userNameLbl.setBounds(45, 39, 86, 19);
		contentPane.add(userNameLbl);
		
		JLabel passwordLbl = new JLabel("Password");
		passwordLbl.setForeground(new Color(180, 16, 12));
		passwordLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		passwordLbl.setBounds(45, 88, 86, 19);
		contentPane.add(passwordLbl);
		
		usernameTxt = new JTextField();
		usernameTxt.setBounds(141, 36, 255, 25);
		contentPane.add(usernameTxt);
		usernameTxt.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(141, 85, 255, 25);
		contentPane.add(passwordField);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(44, 165, 362, 2);
		contentPane.add(separator);
		
		
		// Insert Button
		JButton insertBtn = new JButton("Insert");
		
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "INSERT INTO USERS (USERNAME, PASSWORD) VALUES (?, ?)";
				int n;
				
				try (Connection conn = DBUtil2.getConnection();
						PreparedStatement ps = conn.prepareStatement(sql)) {
					
					String inputUsername = usernameTxt.getText().trim();
					String inputPassword = String.valueOf(passwordField.getPassword()).trim();
					
					if (inputUsername.equals("") || (inputPassword.equals(""))) {
						return;
					}
					
//					int workload = 12;
//					String salt = BCrypt.gensalt(workload);
//					String hashedPassword = BCrypt.hashpw(inputPassword, salt);
//					System.out.println("Password Length: " + hashedPassword.length());
//					System.out.println(hashedPassword);
					
					ps.setString(1, inputUsername);
					ps.setString(2, inputPassword);
					
					n = ps.executeUpdate();
					
					JOptionPane.showMessageDialog(null, n + " records inserted", "INSERT", JOptionPane.PLAIN_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Something went wrong with insertion", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		insertBtn.setForeground(Color.BLUE);
		insertBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		insertBtn.setBounds(190, 198, 100, 25);
		contentPane.add(insertBtn);
		
		// Close Button
		JButton closeBtn = new JButton("Close");
		
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getInsertUserForm().setVisible(false);
				Main.getSearchUserForm().setVisible(true);
			}
		});
		
		closeBtn.setForeground(Color.BLUE);
		closeBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		closeBtn.setBounds(300, 198, 100, 25);
		contentPane.add(closeBtn);
	}
}
