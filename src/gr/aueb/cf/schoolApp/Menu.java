package gr.aueb.cf.schoolApp;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Menu extends JFrame {

	private static final long serialVersionUID = 674893409230L;
	private JPanel contentPane;
	private static Connection connection;

	/**
	 * Create the frame.
	 */
	public Menu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/resources/eduv2.png")));
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				String url = "jdbc:mysql://localhost:3306/SchoolApp?serverTimezone=UTC";
				String username = "schoolAppAdmin";
//				String password = "12345";
				String password = System.getenv("schoolAppAdminPSWD");
				
				try {
					//Class.forName("com.mysql.cj.jdbc.Driver");		// δεν είναι υποχρεωτικό αν ο Driver φορτώνεται αυτόματα.
					connection = DriverManager.getConnection(url, username, password);
					System.out.println("Connection succeed!");
				} catch (SQLException e1) {
					//TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				} catch (ClassNotFoundException e2) {
//					// TODO Auto-generated catch block
//					e2.printStackTrace();
//				}
			}
		});
		setTitle("Διαχείριση Εκπαιδευτικού Συστήματος");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		this.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel qualityEduLbl2 = new JLabel("Ποιότητα στην Εκπαίδευση");
		qualityEduLbl2.setForeground(new Color(0, 128, 0));
		qualityEduLbl2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		qualityEduLbl2.setBounds(69, 11, 363, 66);
		contentPane.add(qualityEduLbl2);
		
		JLabel qualityEduLbl1 = new JLabel("Ποιότητα στην Εκπαίδευση");
		qualityEduLbl1.setForeground(new Color(0, 0, 0));
		qualityEduLbl1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		qualityEduLbl1.setBounds(71, 13, 363, 66);
		contentPane.add(qualityEduLbl1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(47, 76, 400, 1);
		contentPane.add(separator);
		
		JButton teachersBtn = new JButton("");
		teachersBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeachersSearchForm().setVisible(true);
				Main.getMenu().setEnabled(false);
			
			}
		});
		teachersBtn.setBounds(64, 98, 50, 40);
		contentPane.add(teachersBtn);
		
		JLabel teachersLbl = new JLabel("Εκπαιδευτές");
		teachersLbl.setFont(new Font("Tahoma", Font.BOLD, 17));
		teachersLbl.setForeground(new Color(128, 0, 0));
		teachersLbl.setBounds(124, 104, 118, 29);
		contentPane.add(teachersLbl);
		
		JButton studentsBtn = new JButton("");
		studentsBtn.setBounds(64, 156, 50, 40);
		contentPane.add(studentsBtn);
		
		JLabel studentsLbl = new JLabel("Εκπαιδευόμενοι");
		studentsLbl.setForeground(new Color(128, 0, 0));
		studentsLbl.setFont(new Font("Tahoma", Font.BOLD, 17));
		studentsLbl.setBounds(124, 162, 149, 29);
		contentPane.add(studentsLbl);
	}
	
	public static Connection getMyConnection() {
		return connection;
	}
}
