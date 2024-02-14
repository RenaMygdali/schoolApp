package gr.aueb.cf.schoolApp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SearchForm extends JFrame {
	
	private static final long serialVersionUID = 9748923L;
	private JTextField lastnameTxt;
	private String lastname = "";

	/**
	 * Create the frame.
	 */
	public SearchForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				lastnameTxt.setText("");
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(SearchForm.class.getResource("/resources/eduv2.png")));
		getContentPane().setBackground(new Color(244, 255, 254));
		getContentPane().setLayout(null);
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		searchPanel.setBounds(95, 31, 319, 190);
		searchPanel.setBackground(new Color(238, 238, 238));
		getContentPane().add(searchPanel);
		searchPanel.setLayout(null);
		
		lastnameTxt = new JTextField();
		lastnameTxt.setBounds(50, 50, 230, 35);
		searchPanel.add(lastnameTxt);
		lastnameTxt.setColumns(10);
		
		JLabel lastnameLbl = new JLabel("Επώνυμο");
		lastnameLbl.setBounds(124, 16, 83, 23);
		lastnameLbl.setForeground(new Color(206, 83, 9));
		lastnameLbl.setFont(new Font("Tahoma", Font.BOLD, 17));
		searchPanel.add(lastnameLbl);
		
		JButton searchBtn = new JButton("Αναζήτηση");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// data binding
				lastname = lastnameTxt.getText();
				// validate
				Main.getTeachersUpdateDeleteForm().setVisible(true);
				Main.getTeachersSearchForm().setEnabled(false);
			}
		});
		searchBtn.setBounds(96, 120, 138, 40);
		searchBtn.setBackground(new Color(211, 213, 254));
		searchBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		searchBtn.setForeground(new Color(26, 37, 179));
		searchBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		searchPanel.add(searchBtn);
		
		JPanel insertPanel = new JPanel();
		insertPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		insertPanel.setBounds(95, 237, 319, 100);
		insertPanel.setBackground(new Color(238, 238, 238));
		getContentPane().add(insertPanel);
		insertPanel.setLayout(null);
		
		JButton inputBtn = new JButton("Εισαγωγή");
		inputBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeachersInsertForm().setVisible(true);
				Main.getTeachersSearchForm().setEnabled(false);
			}
		});
		inputBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		inputBtn.setBounds(100, 28, 130, 40);
		inputBtn.setForeground(new Color(26, 37, 179));
		inputBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		insertPanel.add(inputBtn);
		
		
		// Close Button
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getMenu().setVisible(true);
				Main.getMenu().setEnabled(true);
				Main.getTeachersSearchForm().setVisible(false);
			}
		});
		closeBtn.setBounds(314, 361, 100, 40);
		closeBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		closeBtn.setForeground(new Color(26, 37, 179));
		closeBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		getContentPane().add(closeBtn);
		setTitle("Εισαγωγή / Αναζήτηση Εκπαιδευτή");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 451);
		setLocationRelativeTo(null);

	}
	
	public String getLastname() {
		return lastname;
	}
}
