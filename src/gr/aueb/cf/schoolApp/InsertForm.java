package gr.aueb.cf.schoolApp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InsertForm extends JFrame {

	private static final long serialVersionUID = 7845930L;
	private JTextField lastnameTxt;
	private JTextField firstnameTxt;

	/**
	 * Create the frame.
	 */
	public InsertForm() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				firstnameTxt.setText("");
				lastnameTxt.setText("");
			}
		});
		
		setTitle("Εισαγωγή Εκπαιδευτή");
		setIconImage(Toolkit.getDefaultToolkit().getImage(InsertForm.class.getResource("/resources/eduv2.png")));
		getContentPane().setBackground(new Color(244, 255, 254));
		getContentPane().setLayout(null);
		
		JPanel insertPanel = new JPanel();
		insertPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(192, 192, 192), null, null));
		insertPanel.setBackground(new Color(238, 238, 238));
		insertPanel.setBounds(15, 11, 456, 164);
		setLocationRelativeTo(null);
		getContentPane().add(insertPanel);
		insertPanel.setLayout(null);
		
		JLabel lastnameLbl = new JLabel("Επίθετο");
		lastnameLbl.setForeground(new Color(206, 83, 9));
		lastnameLbl.setFont(new Font("Tahoma", Font.BOLD, 17));
		lastnameLbl.setBounds(29, 35, 72, 21);
		insertPanel.add(lastnameLbl);
		
		JLabel firstnameLbl = new JLabel("Όνομα");
		firstnameLbl.setForeground(new Color(206, 83, 9));
		firstnameLbl.setFont(new Font("Tahoma", Font.BOLD, 17));
		firstnameLbl.setBounds(29, 92, 72, 21);
		insertPanel.add(firstnameLbl);
		
		lastnameTxt = new JTextField();
		lastnameTxt.setBounds(113, 31, 230, 28);
		insertPanel.add(lastnameTxt);
		lastnameTxt.setColumns(10);
		
		firstnameTxt = new JTextField();
		firstnameTxt.setColumns(10);
		firstnameTxt.setBounds(111, 88, 230, 28);
		insertPanel.add(firstnameTxt);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(33, 204, 429, 1);
		getContentPane().add(separator);
		
		
		// Insert Button
		JButton insertBtn = new JButton("Insert");
		
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			    
			    try {
			    	String firstname = firstnameTxt.getText().trim();
			    	String lastname = lastnameTxt.getText().trim();
			    	
			    	if (firstname.equals("") || lastname.equals("")) {
			    		JOptionPane.showMessageDialog(null, "Empty firstname / lastname", "Error", JOptionPane.ERROR_MESSAGE);
			    		return;
			    	}
			    	
			    	String sql = "INSERT INTO TEACHERS (FIRSTNAME, LASTNAME) VALUES (?, ?)";	
			    	Connection connection = Menu.getMyConnection();
			    	PreparedStatement ps = connection.prepareStatement(sql);
			    	ps.setString(1, firstname);
			    	ps.setString(2, lastname);
			    	
			    	int n = ps.executeUpdate();
			    	JOptionPane.showMessageDialog(null,  n + " row affected", "Insert", JOptionPane.INFORMATION_MESSAGE);
			    } catch (SQLException e1) {
			    	e1.printStackTrace();
			    }
			}
		
		});
		
		insertBtn.setForeground(new Color(26, 37, 179));
		insertBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		insertBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		insertBtn.setBounds(253, 231, 100, 34);
		getContentPane().add(insertBtn);
		
		
		// Close Button
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeachersInsertForm().setVisible(false);
				Main.getTeachersSearchForm().setVisible(true);
				Main.getTeachersSearchForm().setEnabled(true);
			}
		});
		closeBtn.setForeground(new Color(26, 37, 179));
		closeBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		closeBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		closeBtn.setBounds(371, 231, 100, 34);
		getContentPane().add(closeBtn);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 353);
		setLocationRelativeTo(null);
	}
}
