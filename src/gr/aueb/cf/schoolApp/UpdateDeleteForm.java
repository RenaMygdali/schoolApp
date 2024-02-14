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
import javax.swing.border.BevelBorder;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateDeleteForm extends JFrame {

	private static final long serialVersionUID = 1484283L;
	private JTextField idTxt;
	private JTextField firstnameTxt;
	private JTextField lastnameTxt;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	/**
	 * Create the frame.
	 */
	public UpdateDeleteForm() {
		
		setTitle("Ενημέρωση / Διαγραφή Εκπαιδευτή");
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				String sql = "SELECT * FROM TEACHERS WHERE LASTNAME LIKE ?";
				Connection connection = Menu.getMyConnection();
				
				
				try {
					ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					ps.setString(1, Main.getTeachersSearchForm().getLastname() + "%");
					rs = ps.executeQuery();
					
					if (rs.next()) {
						idTxt.setText(rs.getString("ID"));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
					} else {
						JOptionPane.showMessageDialog(null,  "No Teachers found", "Teachers", JOptionPane.WARNING_MESSAGE);
						idTxt.setText("");
						firstnameTxt.setText("");
						lastnameTxt.setText("");
						Main.getTeachersUpdateDeleteForm().setVisible(false);
						Main.getTeachersSearchForm().setEnabled(true);
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
		updatePanel.setBounds(10, 11, 413, 140);
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
		
		JLabel firstnameLbl = new JLabel("Όνομα");
		firstnameLbl.setForeground(new Color(206, 83, 9));
		firstnameLbl.setFont(new Font("Tahoma", Font.BOLD, 17));
		firstnameLbl.setBounds(69, 53, 68, 23);
		updatePanel.add(firstnameLbl);
		
		JLabel lastnameLbl = new JLabel("Επώνυμο");
		lastnameLbl.setForeground(new Color(206, 83, 9));
		lastnameLbl.setFont(new Font("Tahoma", Font.BOLD, 17));
		lastnameLbl.setBounds(48, 90, 83, 23);
		updatePanel.add(lastnameLbl);
		
		firstnameTxt = new JTextField();
		firstnameTxt.setColumns(10);
		firstnameTxt.setBounds(147, 53, 177, 23);
		updatePanel.add(firstnameTxt);
		
		lastnameTxt = new JTextField();
		lastnameTxt.setColumns(10);
		lastnameTxt.setBounds(147, 90, 177, 23);
		updatePanel.add(lastnameTxt);
		
		
		// Update Button
		JButton udpateBtn = new JButton("Update");
		
		udpateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "UPDATE TEACHERS SET FIRSTNAME = ?, LASTNAME = ? WHERE ID = ?";
				try (PreparedStatement ps = Menu.getMyConnection().prepareStatement(query)) {
					ps.setString(1,  firstnameTxt.getText());
					ps.setString(2,  lastnameTxt.getText());
					ps.setInt(3,  Integer.parseInt(idTxt.getText()));
					
					int numberOfRowsAffected = ps.executeUpdate();
					
					JOptionPane.showMessageDialog(null,  numberOfRowsAffected + " rows affected", "UPDATE", JOptionPane.PLAIN_MESSAGE);
					ps.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		
		udpateBtn.setForeground(new Color(26, 37, 179));
		udpateBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		udpateBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		udpateBtn.setBounds(137, 261, 130, 54);
		getContentPane().add(udpateBtn);
		
		
		// Delete Button
		JButton deleteBtn = new JButton("Delete");
		
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "DELETE FROM TEACHERS WHERE ID = ?";
				int response;
				
				try (PreparedStatement preparedStmt = Menu.getMyConnection().prepareStatement(query)) {
					
					preparedStmt.setInt(1,  Integer.parseInt(idTxt.getText()));
					
					response = JOptionPane.showConfirmDialog(null, "Είστε σίγουρος;", "Warning", JOptionPane.YES_NO_OPTION);
					
					if (response == JOptionPane.YES_OPTION) {
						int numberOfRowsAffected = preparedStmt.executeUpdate();
						JOptionPane.showMessageDialog(null,  numberOfRowsAffected + " rows deleted successfully", "DELETE", JOptionPane.INFORMATION_MESSAGE);
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
		deleteBtn.setBounds(277, 261, 130, 54);
		getContentPane().add(deleteBtn);
		
		// Close Button
		JButton closeBtn = new JButton("Close");
		
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeachersUpdateDeleteForm().setVisible(false);
				Main.getTeachersSearchForm().setVisible(true);
				Main.getTeachersSearchForm().setEnabled(true);
			}
		});
		
		closeBtn.setForeground(new Color(26, 37, 179));
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		closeBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		closeBtn.setBounds(417, 261, 130, 54);
		getContentPane().add(closeBtn);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 233, 546, 2);
		getContentPane().add(separator);
		
		
		// First Record Button
		JButton firstRecordBtn = new JButton("");
		
		firstRecordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.first()) {
						idTxt.setText(Integer.toString(rs.getInt("ID")));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
					}
				} catch (SQLException e4) {
					e4.printStackTrace();
				}
			}
		});
		
		firstRecordBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		firstRecordBtn.setIcon(new ImageIcon(UpdateDeleteForm.class.getResource("/resources/First record.png")));
		firstRecordBtn.setBounds(72, 175, 69, 47);
		getContentPane().add(firstRecordBtn);
		
		
		// Previous Record Button
		JButton previousRecordBtn = new JButton("");
		
		previousRecordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.previous()) {
						idTxt.setText(Integer.toString(rs.getInt("ID")));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
					} else {
						rs.first();
					}
				} catch (SQLException e5) {
					e5.printStackTrace();
				}
			}
		});
		
		previousRecordBtn.setIcon(new ImageIcon(UpdateDeleteForm.class.getResource("/resources/Previous_record.png")));
		previousRecordBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		previousRecordBtn.setBounds(151, 175, 69, 47);
		getContentPane().add(previousRecordBtn);
		
		
		// Next record Button
		JButton nextRecordBtn = new JButton("");
		nextRecordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.next()) {
						idTxt.setText(Integer.toString(rs.getInt("ID")));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
					} else {
						rs.last();
					}
				} catch (SQLException e6) {
					e6.printStackTrace();
				}
			}
		});
		
		nextRecordBtn.setIcon(new ImageIcon(UpdateDeleteForm.class.getResource("/resources/Next_track.png")));
		nextRecordBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nextRecordBtn.setBounds(230, 175, 69, 47);
		getContentPane().add(nextRecordBtn);
		
		
		// Last record Button
		JButton lastRecordBtn = new JButton("");
		
		lastRecordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.next()) {
						idTxt.setText(Integer.toString(rs.getInt("ID")));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
					} else {
						rs.last();
					}
				} catch (SQLException e7) {
					e7.printStackTrace();
				}
			}
		});
		lastRecordBtn.setIcon(new ImageIcon(UpdateDeleteForm.class.getResource("/resources/Last_Record.png")));
		lastRecordBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lastRecordBtn.setBounds(309, 175, 69, 47);
		getContentPane().add(lastRecordBtn);
	}

}
