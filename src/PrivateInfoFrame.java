
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrivateInfoFrame {

	JFrame privateFrame;
	static final String DB_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/team002";
	static final String USER = "team002";
	static final String PASS = "38695e46";
	private static final String SQL_QUERY= "SELECT shortName, house, postcode FROM team002.properties WHERE id=?";
	private static final String SQL_QUERY2= "SELECT email, mobilenumber FROM team002.users WHERE id=?";
	

	/**
	 * Create the application.
	 */
	public PrivateInfoFrame(int propertyID, int userID, int fromWhere, int isBooked) {
		initialize(propertyID, userID, fromWhere, isBooked);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int propertyID, int userID, int fromWhere, int isBooked) {
		privateFrame = new JFrame();
		privateFrame.getContentPane().setBackground(Color.ORANGE);
		privateFrame.getContentPane().setForeground(Color.WHITE);
		privateFrame.getContentPane().setLayout(null);
		
		JLabel name_lbl = new JLabel("");
		name_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		name_lbl.setBounds(10, 338, 976, 47);
		privateFrame.getContentPane().add(name_lbl);
		
		JLabel house_lbl = new JLabel("");
		house_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		house_lbl.setBounds(10, 513, 1044, 42);
		privateFrame.getContentPane().add(house_lbl);
		
		JLabel email_lbl = new JLabel("");
		email_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		email_lbl.setBounds(10, 97, 1044, 42);
		privateFrame.getContentPane().add(email_lbl);
		
		JLabel private_lbl = new JLabel("Private information");
		private_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		private_lbl.setBounds(359, 11, 334, 47);
		privateFrame.getContentPane().add(private_lbl);
		
		JLabel postcode_lbl = new JLabel("");
		postcode_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		postcode_lbl.setBounds(10, 441, 1044, 42);
		privateFrame.getContentPane().add(postcode_lbl);
		
		JLabel mobileNumber_lbl = new JLabel("");
		mobileNumber_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		mobileNumber_lbl.setBounds(10, 166, 1044, 42);
		privateFrame.getContentPane().add(mobileNumber_lbl);
		
		JButton back_btn = new JButton("Back");
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewPropertyFrame window = new ViewPropertyFrame(propertyID, 2, userID, isBooked);
				window.frmViewProperty.setVisible(true);
				privateFrame.dispose();
			}
		});
		back_btn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		back_btn.setBounds(905, 628, 149, 42);
		privateFrame.getContentPane().add(back_btn);
		privateFrame.setBounds(100, 100, 1080, 720);
		privateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//SQL_QUERY= "SELECT shortName, house, postcode FROM team002.bookings WHERE id=?";
		//SQL_QUERY2= "SELECT email, mobilenumber FROM team002.users WHERE id=?";
		
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				PreparedStatement stmt1 = conn.prepareStatement(SQL_QUERY);
				PreparedStatement stmt2 = conn.prepareStatement(SQL_QUERY2);) {
			
			stmt1.setString(1, String.valueOf(propertyID));
			stmt2.setString(1, String.valueOf(userID));
			ResultSet rs1 = stmt1.executeQuery();
			ResultSet rs2 = stmt2.executeQuery();
			
			if(rs1.next() && rs2.next()) {
				email_lbl.setText(rs2.getString("email"));
				mobileNumber_lbl.setText(rs2.getString("mobilenumber"));
				
				name_lbl.setText(rs1.getString("shortName"));
				house_lbl.setText(rs1.getString("house"));
				postcode_lbl.setText(rs1.getString("postcode"));
			}

		}

		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		
		
	}
}
