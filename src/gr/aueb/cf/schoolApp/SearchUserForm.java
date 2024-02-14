package gr.aueb.cf.schoolApp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SearchUserForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userNameTxt;
	private String username;

	/**
	 * Create the frame.
	 */
	public SearchUserForm() {
		
		setTitle("Αναζήτηση / Εισαγωγή Χρήστη");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 363);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(223, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		searchPanel.setBounds(89, 11, 268, 133);
		contentPane.add(searchPanel);
		searchPanel.setLayout(null);
		
		
		// Username text field - action on enter key
		userNameTxt = new JTextField();
		
		userNameTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					username = userNameTxt.getText().trim();		// Data binding
					
					if (username.equals("")) {
						JOptionPane.showMessageDialog(null,  "Please enter a username", "USERNAME", JOptionPane.WARNING_MESSAGE);
						return;
					}
					Main.getUpdateDeleteUserForm().setVisible(true);
					Main.getSearchUserForm().setVisible(false);
				}
			}
		});
		
		userNameTxt.setBounds(60, 45, 155, 23);
		searchPanel.add(userNameTxt);
		userNameTxt.setColumns(10);
		
		JLabel userNameLbl = new JLabel("Username");
		userNameLbl.setBounds(95, 11, 84, 23);
		userNameLbl.setForeground(new Color(205, 48, 20));
		userNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 19));
		searchPanel.add(userNameLbl);
		
		
		// Search User Button
		JButton searchUserBtn = new JButton("Αναζήτηση");
		
		searchUserBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					username = userNameTxt.getText().trim();		// Data binding
					
					if (username.equals("")) {
						JOptionPane.showMessageDialog(null,  "Please enter a username", "USERNAME", JOptionPane.WARNING_MESSAGE);
						return;
					}
					Main.getUpdateDeleteUserForm().setVisible(true);
					Main.getSearchUserForm().setVisible(false);
				}
		});
		
		searchUserBtn.setForeground(Color.BLUE);
		searchUserBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		searchUserBtn.setBounds(87, 89, 100, 33);
		searchPanel.add(searchUserBtn);
		
		JPanel insertPanel = new JPanel();
		insertPanel.setLayout(null);
		insertPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		insertPanel.setBounds(89, 164, 268, 98);
		contentPane.add(insertPanel);
		
		
		// Insert Button
		JButton insertUserBtn = new JButton("Εισαγωγή");
		
		insertUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getSearchUserForm().setVisible(false);
				Main.getInsertUserForm().setVisible(true);
			}
		});
		
		insertUserBtn.setForeground(Color.BLUE);
		insertUserBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		insertUserBtn.setBounds(87, 34, 100, 33);
		insertPanel.add(insertUserBtn);
		
		
		// Close Button
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getSearchUserForm().setVisible(false);
				Main.getLoginPage().setVisible(true);
			}
		});
		closeBtn.setForeground(Color.BLUE);
		closeBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		closeBtn.setBounds(257, 280, 100, 25);
		contentPane.add(closeBtn);
	}
	
	public String getUsername() {
		return username;
	}
}
