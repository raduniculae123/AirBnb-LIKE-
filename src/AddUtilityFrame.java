
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
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

/**
 * a class for adding the utilities details
 */
public class AddUtilityFrame {

	protected JFrame frmAddUtility;
	static final String DB_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/team002";
	static final String USER = "team002";
	static final String PASS = "38695e46";
	private static final String SQL_INSERT = "INSERT INTO team002.utilities (facilityID, heating, washingMachine, dryingMachine, fireExtinguisher, smokeAlarm, firstAidKit) VALUES (?,?,?,?,?,?,?)";

	/**
	 * Create the application.
	 */
	public AddUtilityFrame(int facilityId) {
		initialize(facilityId);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param facilityId
	 */
	private void initialize(int facilityId) {
		frmAddUtility = new JFrame();
		frmAddUtility.getContentPane().setEnabled(false);
		frmAddUtility.getContentPane().setBackground(Color.ORANGE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 94, 148, 207, 0, 217, 0, 64, 0, 0, 0, 180, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 57, 111, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		frmAddUtility.getContentPane().setLayout(gridBagLayout);

		/**
		 * adding the checkboxes and labels
		 */
		JLabel addUtilities_lbl = new JLabel("Add Utilities Facilities");
		addUtilities_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		GridBagConstraints gbc_addUtility_lbl = new GridBagConstraints();
		gbc_addUtility_lbl.fill = GridBagConstraints.HORIZONTAL;
		gbc_addUtility_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_addUtility_lbl.gridx = 5;
		gbc_addUtility_lbl.gridy = 1;
		frmAddUtility.getContentPane().add(addUtilities_lbl, gbc_addUtility_lbl);

		JCheckBox isHeating_chkbox = new JCheckBox("Heating ");
		isHeating_chkbox.setBackground(Color.ORANGE);
		// isHeating_chkbox.setBackground(new Color(0, 255, 225));
		isHeating_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isHeating_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_isHeating_chkbox = new GridBagConstraints();
		gbc_isHeating_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isHeating_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isHeating_chkbox.gridx = 5;
		gbc_isHeating_chkbox.gridy = 2;
		frmAddUtility.getContentPane().add(isHeating_chkbox, gbc_isHeating_chkbox);

		JCheckBox isWashingMachine_chkbox = new JCheckBox("Washing Machine ");
		isWashingMachine_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isWashingMachine_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isWashingMachine_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isWashingMachine_chkbox = new GridBagConstraints();
		gbc_isWashingMachine_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isWashingMachine_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isWashingMachine_chkbox.gridx = 5;
		gbc_isWashingMachine_chkbox.gridy = 3;
		frmAddUtility.getContentPane().add(isWashingMachine_chkbox, gbc_isWashingMachine_chkbox);

		JCheckBox isDryingMachine_chkbox = new JCheckBox("Drying Machine ");
		isDryingMachine_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isDryingMachine_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isDryingMachine_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isDryingMachine_chkbox = new GridBagConstraints();
		gbc_isDryingMachine_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isDryingMachine_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isDryingMachine_chkbox.gridx = 5;
		gbc_isDryingMachine_chkbox.gridy = 4;
		frmAddUtility.getContentPane().add(isDryingMachine_chkbox, gbc_isDryingMachine_chkbox);

		JCheckBox isFireExtinguisher_chkbox = new JCheckBox("Fire Extinguisher ");
		isFireExtinguisher_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isFireExtinguisher_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isFireExtinguisher_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isFireExtinguisher_chkbox = new GridBagConstraints();
		gbc_isFireExtinguisher_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isFireExtinguisher_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isFireExtinguisher_chkbox.gridx = 5;
		gbc_isFireExtinguisher_chkbox.gridy = 5;
		frmAddUtility.getContentPane().add(isFireExtinguisher_chkbox, gbc_isFireExtinguisher_chkbox);

		JCheckBox isSmokeAlarm_chkbox = new JCheckBox("Smoke Alarm ");
		isSmokeAlarm_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isSmokeAlarm_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isSmokeAlarm_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isSmokeAlarm_chkbox = new GridBagConstraints();
		gbc_isSmokeAlarm_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isSmokeAlarm_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isSmokeAlarm_chkbox.gridx = 5;
		gbc_isSmokeAlarm_chkbox.gridy = 6;
		frmAddUtility.getContentPane().add(isSmokeAlarm_chkbox, gbc_isSmokeAlarm_chkbox);

		JCheckBox isFirstAidKit_chkbox = new JCheckBox("First Aid Kit ");
		isFirstAidKit_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isFirstAidKit_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isFirstAidKit_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isFirstAidKit_chkbox = new GridBagConstraints();
		gbc_isFirstAidKit_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isFirstAidKit_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isFirstAidKit_chkbox.gridx = 5;
		gbc_isFirstAidKit_chkbox.gridy = 8;
		frmAddUtility.getContentPane().add(isFirstAidKit_chkbox, gbc_isFirstAidKit_chkbox);

		JButton submit_btn = new JButton("Submit");
		submit_btn.addActionListener(new ActionListener() {
			/**
			 * inserts the contents of the checkboxes in the database
			 */
			public void actionPerformed(ActionEvent e) {
				try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

						PreparedStatement stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);) {

					String isHeatingString = String.valueOf(isHeating_chkbox.isSelected() ? 1 : 0);
					String isWashingMachineString = String.valueOf(isWashingMachine_chkbox.isSelected() ? 1 : 0);
					String isDryingMachineString = String.valueOf(isDryingMachine_chkbox.isSelected() ? 1 : 0);
					String isFireExtinguisherString = String.valueOf(isFireExtinguisher_chkbox.isSelected() ? 1 : 0);
					String isSmokeAlarmString = String.valueOf(isSmokeAlarm_chkbox.isSelected() ? 1 : 0);
					String isFirstAidKitString = String.valueOf(isFirstAidKit_chkbox.isSelected() ? 1 : 0);

					stmt.setString(1, String.valueOf(facilityId));
					stmt.setString(2, isHeatingString);
					stmt.setString(3, isWashingMachineString);
					stmt.setString(4, isDryingMachineString);
					stmt.setString(5, isFireExtinguisherString);
					stmt.setString(6, isSmokeAlarmString);
					stmt.setString(7, isFirstAidKitString);

					stmt.executeUpdate();
					frmAddUtility.dispose();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		submit_btn.setFont(new Font("Tahoma", Font.BOLD, 25));
		GridBagConstraints gbc_submit_btn = new GridBagConstraints();
		gbc_submit_btn.anchor = GridBagConstraints.WEST;
		gbc_submit_btn.insets = new Insets(0, 0, 5, 5);
		gbc_submit_btn.gridx = 10;
		gbc_submit_btn.gridy = 11;
		frmAddUtility.getContentPane().add(submit_btn, gbc_submit_btn);
		frmAddUtility.setBounds(100, 100, 1080, 720);
		frmAddUtility.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
