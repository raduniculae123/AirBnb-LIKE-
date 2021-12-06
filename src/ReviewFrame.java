
import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.DrbgParameters.Reseed;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReviewFrame {

	JFrame frmReview;
	private static final String SQL_INSERT = "INSERT INTO team002.reviews (propertyID, review, cleanliness, communication, checkin, accuracy, location, value, overall) VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String SQL_OVERALL = "SELECT overall from team002.reviews WHERE propertyID = ?";
	private static final String SQL_UPDATE = "UPDATE team002.properties SET avgScore = ? WHERE id = ?";
	private static final String SQL_AVG = "SELECT avgScore from team002.properties WHERE userID = ?";
	private static final String SQL_SH = "UPDATE team002.users SET superHost = ? WHERE id = ?";
	static final String DB_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/team002";
	static final String USER = "team002";
	static final String PASS = "38695e46";

	/**
	 * Create the application.
	 */
	public ReviewFrame(int propertyId, int userID) {
		initialize(propertyId, userID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int propertyId, int userID) {
		frmReview = new JFrame("Reviews");
		frmReview.getContentPane().setEnabled(false);
		frmReview.getContentPane().setBackground(Color.ORANGE);
		frmReview.getContentPane().setLayout(null);

		JLabel addReview_lbl = new JLabel("Review score");
		addReview_lbl.setBounds(467, 0, 232, 49);
		addReview_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		frmReview.getContentPane().add(addReview_lbl);

		JLabel description_lbl = new JLabel("Description");
		description_lbl.setBounds(314, 111, 124, 31);
		description_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frmReview.getContentPane().add(description_lbl);

		JLabel cleanliness_lbl = new JLabel("Cleanliess");
		cleanliness_lbl.setBounds(322, 237, 108, 31);
		cleanliness_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frmReview.getContentPane().add(cleanliness_lbl);

		JLabel communication_lbl = new JLabel("Communication");
		communication_lbl.setBounds(290, 279, 172, 31);
		communication_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frmReview.getContentPane().add(communication_lbl);

		JLabel checkin_lbl = new JLabel("Check-in");
		checkin_lbl.setBounds(328, 321, 95, 31);
		checkin_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frmReview.getContentPane().add(checkin_lbl);

		JLabel accuracy_lbl = new JLabel("Accuracy");
		accuracy_lbl.setBounds(319, 363, 113, 31);
		accuracy_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frmReview.getContentPane().add(accuracy_lbl);

		JLabel location_lbl = new JLabel("Location");
		location_lbl.setBounds(329, 405, 93, 31);
		location_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frmReview.getContentPane().add(location_lbl);

		JLabel value_lbl = new JLabel("Value");
		value_lbl.setBounds(345, 447, 61, 31);
		value_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frmReview.getContentPane().add(value_lbl);

		JComboBox isCleanliness_combobox = new JComboBox();
		isCleanliness_combobox.setBounds(634, 234, 205, 37);
		isCleanliness_combobox.addItem("--Select Score--");
		isCleanliness_combobox.addItem("          5");
		isCleanliness_combobox.addItem("          4");
		isCleanliness_combobox.addItem("          3");
		isCleanliness_combobox.addItem("          2");
		isCleanliness_combobox.addItem("          1");
		isCleanliness_combobox.setBackground(new Color(255, 200, 0));
		// isCleanliness_combobox.setHorizontalTextPosition(SwingConstants.LEFT);
		isCleanliness_combobox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frmReview.getContentPane().add(isCleanliness_combobox);

		JComboBox isCommunition_combobox = new JComboBox();
		isCommunition_combobox.setBounds(634, 276, 205, 37);
		isCommunition_combobox.addItem("--Select Score--");
		isCommunition_combobox.addItem("          5");
		isCommunition_combobox.addItem("          4");
		isCommunition_combobox.addItem("          3");
		isCommunition_combobox.addItem("          2");
		isCommunition_combobox.addItem("          1");
		isCommunition_combobox.setBackground(new Color(255, 200, 0));
		// isCleanliness_combobox.setHorizontalTextPosition(SwingConstants.LEFT);
		isCommunition_combobox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frmReview.getContentPane().add(isCommunition_combobox);

		JComboBox isCheck_in_combobox = new JComboBox();
		isCheck_in_combobox.setBounds(634, 318, 205, 37);
		isCheck_in_combobox.addItem("--Select Score--");
		isCheck_in_combobox.addItem("          5");
		isCheck_in_combobox.addItem("          4");
		isCheck_in_combobox.addItem("          3");
		isCheck_in_combobox.addItem("          2");
		isCheck_in_combobox.addItem("          1");
		isCheck_in_combobox.setBackground(new Color(255, 200, 0));
		// isCleanliness_combobox.setHorizontalTextPosition(SwingConstants.LEFT);
		isCheck_in_combobox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frmReview.getContentPane().add(isCheck_in_combobox);

		JComboBox isAccuracy_combobox = new JComboBox();
		isAccuracy_combobox.setBounds(634, 360, 205, 37);
		isAccuracy_combobox.addItem("--Select Score--");
		isAccuracy_combobox.addItem("          5");
		isAccuracy_combobox.addItem("          4");
		isAccuracy_combobox.addItem("          3");
		isAccuracy_combobox.addItem("          2");
		isAccuracy_combobox.addItem("          1");
		isAccuracy_combobox.setBackground(new Color(255, 200, 0));
		// isCleanliness_combobox.setHorizontalTextPosition(SwingConstants.LEFT);
		isAccuracy_combobox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frmReview.getContentPane().add(isAccuracy_combobox);

		JComboBox islocation_combobox = new JComboBox();
		islocation_combobox.setBounds(634, 402, 205, 37);
		islocation_combobox.addItem("--Select Score--");
		islocation_combobox.addItem("          5");
		islocation_combobox.addItem("          4");
		islocation_combobox.addItem("          3");
		islocation_combobox.addItem("          2");
		islocation_combobox.addItem("          1");
		islocation_combobox.setBackground(new Color(255, 200, 0));
		// isCleanliness_combobox.setHorizontalTextPosition(SwingConstants.LEFT);
		islocation_combobox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frmReview.getContentPane().add(islocation_combobox);

		JComboBox isvalue_combobox = new JComboBox();
		isvalue_combobox.setBounds(634, 444, 205, 37);
		isvalue_combobox.addItem("--Select Score--");
		isvalue_combobox.addItem("          5");
		isvalue_combobox.addItem("          4");
		isvalue_combobox.addItem("          3");
		isvalue_combobox.addItem("          2");
		isvalue_combobox.addItem("          1");
		isvalue_combobox.setBackground(new Color(255, 200, 0));
		// isCleanliness_combobox.setHorizontalTextPosition(SwingConstants.LEFT);
		isvalue_combobox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frmReview.getContentPane().add(isvalue_combobox);

		JButton submit_btn = new JButton("Submit");
		submit_btn.setBounds(661, 594, 140, 39);
		submit_btn.setFont(new Font("Tahoma", Font.BOLD, 25));
		frmReview.getContentPane().add(submit_btn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(563, 81, 341, 114);
		frmReview.getContentPane().add(scrollPane);

		JTextArea description_txtArea = new JTextArea();
		scrollPane.setViewportView(description_txtArea);
		description_txtArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		description_txtArea.setLineWrap(true);
		description_txtArea.setWrapStyleWord(true);
		frmReview.setBounds(100, 100, 1080, 720);
		frmReview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		submit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

				// PreparedStatement stmt = conn.prepareStatement(SQL_INSERT,
				// Statement.RETURN_GENERATED_KEYS);) {

				String s1 = (String) isCleanliness_combobox.getSelectedItem();
				String s11 = s1.trim();
				String s2 = (String) isCommunition_combobox.getSelectedItem();
				String s12 = s2.trim();
				String s3 = (String) isCheck_in_combobox.getSelectedItem();
				String s13 = s3.trim();
				String s4 = (String) isAccuracy_combobox.getSelectedItem();
				String s14 = s4.trim();
				String s5 = (String) islocation_combobox.getSelectedItem();
				String s15 = s5.trim();
				String s6 = (String) isvalue_combobox.getSelectedItem();
				String s16 = s6.trim();

				try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

						PreparedStatement stmt = conn.prepareStatement(SQL_INSERT);
						PreparedStatement stmtOVR = conn.prepareStatement(SQL_OVERALL);
						PreparedStatement stmtUP = conn.prepareStatement(SQL_UPDATE);
						PreparedStatement stmtAVG = conn.prepareStatement(SQL_AVG);
						PreparedStatement stmtSH = conn.prepareStatement(SQL_SH);) {

					int i1 = Integer.parseInt(s11);
					int i2 = Integer.parseInt(s12);
					int i3 = Integer.parseInt(s13);
					int i4 = Integer.parseInt(s14);
					int i5 = Integer.parseInt(s15);
					int i6 = Integer.parseInt(s16);
					int i7 = i1 + i2 + i3 + i4 + i5 + i6;
					double avg = i7 / 6.0;

					stmt.setString(1, String.valueOf(propertyId));
					stmt.setString(2, description_txtArea.getText());
					stmt.setString(3, s11);
					stmt.setString(4, s12);
					stmt.setString(5, s13);
					stmt.setString(6, s14);
					stmt.setString(7, s15);
					stmt.setString(8, s16);
					stmt.setString(9, String.valueOf(avg));
					stmt.executeUpdate();
					/*
					 * SQL_OVERALL = "SELECT overall from team002.reviews WHERE propertyID = ?";
					 * SQL_UPDATE ="UPDATE team002.properties SET avgScore = ? WHERE id = ?";
					 * SQL_AVG = "SELECT avgScore from team002.properties WHERE userID = ?"; SQL_SH
					 * = "UPDATE team002.users SET superHost = ? WHERE id = ?";
					 */
					stmtOVR.setInt(1, propertyId);
					ResultSet overAllRs = stmtOVR.executeQuery();
					double overAll = 0;
					int numOfRev = 0;
					while (overAllRs.next()) {
						overAll += overAllRs.getDouble("overall");
						numOfRev++;
					}
					overAll = overAll / numOfRev;
					System.out.println("overAll=" + overAll);

					stmtUP.setDouble(1, overAll);
					stmtUP.setInt(2, propertyId);
					stmtUP.executeUpdate();

					stmtAVG.setInt(1, userID);
					ResultSet avgRs = stmtAVG.executeQuery();
					double score = 0;
					int numOfProp = 0;
					while (avgRs.next()) {
						score += avgRs.getDouble("avgScore");
						numOfProp++;
					}
					score = score / numOfProp;

					if (score >= 4.7) {
						stmtSH.setInt(1, 1);
						stmtSH.setInt(2, userID);
					} else {
						stmtSH.setInt(1, 0);
						stmtSH.setInt(2, userID);
					}

					stmtSH.executeUpdate();

					ViewPropertyFrame window = new ViewPropertyFrame(propertyId, 2, userID, 1);
					window.frmViewProperty.setVisible(true);
					frmReview.dispose();

				} catch (NumberFormatException a) {
					JOptionPane.showMessageDialog(null, "Choose a right score ");

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

	}
}