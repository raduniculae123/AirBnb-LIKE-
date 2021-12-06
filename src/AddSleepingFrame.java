


import javax.swing.JFrame;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

/**
 * a class for adding the outdoor details
 */
public class AddSleepingFrame {

	JFrame frmAddSleeping;
	static final String DB_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/team002";
	static final String USER = "team002";
	static final String PASS = "38695e46";
	private static final String SQL_INSERT = "INSERT INTO team002.sleepings (facilityID, bedlinen, towels) VALUES (?,?,?)";
	private static final String SQL_QUERY = "SELECT MAX(id) FROM team002.sleepings";

	/**
	 * Create the application.
	 */
	public AddSleepingFrame(int facilityId) {
		initialize(facilityId);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param propertyId
	 */
	private void initialize(int facilityId) {
		System.out.println("muie din sleeping");
		frmAddSleeping = new JFrame();
		frmAddSleeping.getContentPane().setEnabled(false);
		frmAddSleeping.getContentPane().setBackground(Color.ORANGE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 94, 148, 207, 0, 217, 0, 64, 0, 0, 0, 180, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 57, 111, 40, 0, 0, 0, 76, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		frmAddSleeping.getContentPane().setLayout(gridBagLayout);

		/**
		 * adding the checkboxes and labels
		 */
		JLabel addSleeping_lbl = new JLabel("Add Sleeping Facilities");
		addSleeping_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		GridBagConstraints gbc_addSleeping_lbl = new GridBagConstraints();
		gbc_addSleeping_lbl.fill = GridBagConstraints.HORIZONTAL;
		gbc_addSleeping_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_addSleeping_lbl.gridx = 6;
		gbc_addSleeping_lbl.gridy = 1;
		frmAddSleeping.getContentPane().add(addSleeping_lbl, gbc_addSleeping_lbl);

		JCheckBox isBedlinen_chkbox = new JCheckBox("Bedlinen ");
		isBedlinen_chkbox.setBackground(new Color(255, 200, 0));
		isBedlinen_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isBedlinen_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_isBedlinen_chkbox = new GridBagConstraints();
		gbc_isBedlinen_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isBedlinen_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isBedlinen_chkbox.gridx = 6;
		gbc_isBedlinen_chkbox.gridy = 3;
		frmAddSleeping.getContentPane().add(isBedlinen_chkbox, gbc_isBedlinen_chkbox);

		JCheckBox isTowels_chkbox = new JCheckBox("Towels ");
		isTowels_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isTowels_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isTowels_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isTowels_chkbox = new GridBagConstraints();
		gbc_isTowels_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isTowels_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isTowels_chkbox.gridx = 6;
		gbc_isTowels_chkbox.gridy = 4;
		frmAddSleeping.getContentPane().add(isTowels_chkbox, gbc_isTowels_chkbox);

		JButton toAddBedroom_btn = new JButton("Add bedroom");
		toAddBedroom_btn.addActionListener(new ActionListener() {
			/**
			 * inserts the contents of the checkboxes in the database
			 */
			public void actionPerformed(ActionEvent e) {
				try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

						PreparedStatement stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);) {

					String isBedlinenString = String.valueOf(isBedlinen_chkbox.isSelected() ? 1 : 0);
					String isTowelsString = String.valueOf(isTowels_chkbox.isSelected() ? 1 : 0);

					stmt.setString(1, String.valueOf(facilityId));
					stmt.setString(2, isBedlinenString);
					stmt.setString(3, isTowelsString);

					stmt.executeUpdate();
					frmAddSleeping.dispose();

					PreparedStatement stmt2 = conn.prepareStatement(SQL_QUERY);
					{

						ResultSet rs1 = stmt2.executeQuery();
						int sleepingId;
						if (rs1.next()) {
							if (rs1.getString(1) == null) {
								sleepingId = 1;
							} else {
								sleepingId = Integer.parseInt(rs1.getString(1));
							}
							AddBedroomFrame window = new AddBedroomFrame(sleepingId);
							window.frmAddBedroom.setVisible(true);
						}

					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		toAddBedroom_btn.setFont(new Font("Tahoma", Font.BOLD, 25));
		GridBagConstraints gbc_toAddBedroom_btn = new GridBagConstraints();
		gbc_toAddBedroom_btn.anchor = GridBagConstraints.EAST;
		gbc_toAddBedroom_btn.insets = new Insets(0, 0, 5, 5);
		gbc_toAddBedroom_btn.gridx = 6;
		gbc_toAddBedroom_btn.gridy = 7;
		frmAddSleeping.getContentPane().add(toAddBedroom_btn, gbc_toAddBedroom_btn);
		frmAddSleeping.setBounds(100, 100, 1080, 720);
		frmAddSleeping.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
