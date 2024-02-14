package gr.aueb.cf.schoolApp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;

import gr.aueb.cf.schoolApp.util.*;

import javax.swing.border.BevelBorder;
import javax.swing.JSeparator;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPasswordField;

public class UpdateDeleteUserForm extends JFrame {

	private static final long serialVersionUID = 1484283L;
	private JTextField idTxt;
	private JTextField usernameTxt;
	private JPasswordField newPassTxt;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private JPasswordField confirmPassTxt;
	private String inputNewPass;
	private String inputConfirmPass;

	/**
	 * Create the frame.
	 */
	public UpdateDeleteUserForm() {
		
		setTitle("Ενημέρωση / Διαγραφή Χρήστη");
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				String sql = "SELECT * FROM USERS WHERE USERNAME LIKE ?";
		
				try (Connection conn = DBUtil2.getConnection();
						PreparedStatement ps = conn.prepareStatement(sql)) {
					
					ps.setString(1, Main.getSearchUserForm().getUsername() + "%");
					rs = ps.executeQuery();
					
					if (rs.next()) {
						idTxt.setText(String.valueOf(rs.getInt("ID")));
						usernameTxt.setText(rs.getString("USERNAME"));
						
					} else {
						JOptionPane.showMessageDialog(null, "No Users found", "Users", JOptionPane.WARNING_MESSAGE);
						idTxt.setText("");
						usernameTxt.setText("");
						Main.getUpdateDeleteUserForm().setVisible(false);
						Main.getSearchUserForm().setVisible(true);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				try {
					if (rs != null) rs.close();
					if (ps != null) ps.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateDeleteForm.class.getResource("/resources/eduv2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 592, 365);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		
		JPanel updatePanel = new JPanel();
		updatePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		updatePanel.setLayout(null);
		updatePanel.setBackground(new Color(238, 238, 238));
		updatePanel.setBounds(10, 11, 413, 93);
		getContentPane().add(updatePanel);
		
		idTxt = new JTextField();
		idTxt.setBackground(new Color(252, 238, 163));
		idTxt.setEnabled(false);
		idTxt.setColumns(10);
		idTxt.setBounds(147, 15, 57, 23);
		updatePanel.add(idTxt);
		
		JLabel idLbl = new JLabel("ID");
		idLbl.setForeground(new Color(206, 83, 9));
		idLbl.setFont(new Font("Tahoma", Font.BOLD, 17));
		idLbl.setBounds(103, 15, 21, 23);
		updatePanel.add(idLbl);
		
		JLabel usernameLbl = new JLabel("username");
		usernameLbl.setForeground(new Color(206, 83, 9));
		usernameLbl.setFont(new Font("Tahoma", Font.BOLD, 17));
		usernameLbl.setBounds(48, 53, 89, 23);
		updatePanel.add(usernameLbl);
		
		usernameTxt = new JTextField();
		usernameTxt.setColumns(10);
		usernameTxt.setBounds(147, 53, 177, 23);
		updatePanel.add(usernameTxt);
		
		
		// Update User Button
		JButton udpateBtn = new JButton("Update");
		
		udpateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sqlSelect = "SELECT PASSWORD FROM USERS WHERE ID = ?";
				String sqlUpdate = "UPDATE USERS SET PASSWORD = ? WHERE ID = ?";
			
            	inputNewPass = String.valueOf(newPassTxt.getPassword()).trim();
				inputConfirmPass = String.valueOf(confirmPassTxt.getPassword()).trim();
				
				if (inputNewPass.equals("") || inputConfirmPass.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter a password!", "PASSWORD", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (!inputNewPass.equals(inputConfirmPass)) {
					JOptionPane.showMessageDialog(null,  "New and confirmation password are not the same. Please try again!", "PASSWORD", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				try (Connection conn = DBUtil2.getConnection();
						PreparedStatement psSelect = conn.prepareStatement(sqlSelect);
						PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {

					int userId = Integer.parseInt(idTxt.getText());
					
		            psSelect.setInt(1, userId);
		            ResultSet rs = psSelect.executeQuery();
		            
		            if (rs.next()) {
		            	String storedPassword = rs.getString("PASSWORD");
		            	
		            	if (storedPassword.equals(inputNewPass)) {
		            		JOptionPane.showMessageDialog(null,  "The new password you inserted is the same with your current password. Please insert a new one!", "PASSWORD", JOptionPane.WARNING_MESSAGE);
		            		newPassTxt.setText("");
							confirmPassTxt.setText("");
							return;
		            	}
				
						psUpdate.setString(1, String.valueOf(newPassTxt.getPassword()).trim());
						psUpdate.setInt(2, Integer.parseInt(idTxt.getText()));
							
						int numberOfRowsAffected = psUpdate.executeUpdate();
							
						JOptionPane.showMessageDialog(null, "Succesfull update\n" + numberOfRowsAffected + " rows affected", "UPDATE", JOptionPane.PLAIN_MESSAGE);
						newPassTxt.setText("");
						confirmPassTxt.setText("");
		            }
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		
		udpateBtn.setForeground(new Color(26, 37, 179));
		udpateBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		udpateBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		udpateBtn.setBounds(177, 271, 108, 44);
		getContentPane().add(udpateBtn);
		
		
		// Delete User Button
		JButton deleteBtn = new JButton("Delete");
		
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql = "DELETE FROM USERS WHERE ID = ?";
				int response;
				
				try (Connection conn = DBUtil2.getConnection();
						PreparedStatement ps = conn.prepareStatement(sql)) {
					
					ps.setInt(1, Integer.parseInt(idTxt.getText()));
					
					response = JOptionPane.showConfirmDialog(null, "Are you sure;", "Warning", JOptionPane.YES_NO_OPTION);
					
					if (response == JOptionPane.YES_OPTION) {
						int numberOfRowsAffected = ps.executeUpdate();
						JOptionPane.showMessageDialog(null, numberOfRowsAffected + " rows deleted successfully", "DELETE", JOptionPane.INFORMATION_MESSAGE);
						Main.getUpdateDeleteUserForm().setVisible(false);
						Main.getSearchUserForm().setVisible(true);
					}	
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} 
			}
		});
		deleteBtn.setForeground(new Color(26, 37, 179));
		deleteBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		deleteBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		deleteBtn.setBounds(299, 271, 108, 44);
		getContentPane().add(deleteBtn);
		
		// Close Button
		JButton closeBtn = new JButton("Close");
		
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs != null) rs.close();
					if (ps != null) ps.close();
					Main.getUpdateDeleteUserForm().setVisible(false);
					Main.getSearchUserForm().setVisible(true);
					Main.getSearchUserForm().setEnabled(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		closeBtn.setForeground(new Color(26, 37, 179));
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		closeBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		closeBtn.setBounds(417, 271, 108, 44);
		getContentPane().add(closeBtn);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 247, 546, 2);
		getContentPane().add(separator);
		
		JPanel changePassPanel = new JPanel();
		changePassPanel.setLayout(null);
		changePassPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		changePassPanel.setBackground(new Color(238, 238, 238));
		changePassPanel.setBounds(10, 115, 413, 121);
		getContentPane().add(changePassPanel);
		
		newPassTxt = new JPasswordField();
		newPassTxt.setBounds(162, 46, 217, 23);
		changePassPanel.add(newPassTxt);
		newPassTxt.setColumns(10);
		
		JLabel changePassLbl = new JLabel("Change Password");
		changePassLbl.setBounds(145, 11, 124, 23);
		changePassPanel.add(changePassLbl);
		changePassLbl.setForeground(new Color(0, 0, 0));
		changePassLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		confirmPassTxt = new JPasswordField();
		confirmPassTxt.setColumns(10);
		confirmPassTxt.setBounds(162, 80, 217, 23);
		changePassPanel.add(confirmPassTxt);
		
		JLabel newPassLbl = new JLabel("New password");
		newPassLbl.setForeground(new Color(222, 52, 33));
		newPassLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		newPassLbl.setBounds(58, 45, 94, 23);
		changePassPanel.add(newPassLbl);
		
		JLabel confirmPassLbl = new JLabel("Confirm Password");
		confirmPassLbl.setForeground(new Color(222, 52, 33));
		confirmPassLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		confirmPassLbl.setBounds(36, 79, 116, 23);
		changePassPanel.add(confirmPassLbl);
	}
}
