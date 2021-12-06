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
 *a class for adding the bathing details
 */
public class AddBathingFrame {

	protected JFrame frmAddBathing;
	static final String DB_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/team002";
	static final String USER = "team002";
	static final String PASS = "38695e46";
	private static final String SQL_INSERT = "INSERT INTO team002.bathings (facilityID, hairdryer, toiletpaper, shampoo) VALUES (?,?,?,?)";
	private static final String SQL_QUERY = "SELECT MAX(id) FROM team002.bathings";

	/**
	 * Create the application.
	 */
	public AddBathingFrame(int facilityId) {
		initialize(facilityId);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param facilityId
	 */
	private void initialize(int facilityId) {
		frmAddBathing = new JFrame();
		frmAddBathing.getContentPane().setEnabled(false);
		frmAddBathing.getContentPane().setBackground(Color.ORANGE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 94, 148, 207, 0, 217, 0, 64, 0, 0, 0, 180, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 57, 111, 40, 0, 0, 0, 69, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		frmAddBathing.getContentPane().setLayout(gridBagLayout);

		/**
		 * adding the checkboxes and labels
		 */
		JLabel addBathing_lbl = new JLabel("Add Bathing Facilities");
		addBathing_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		GridBagConstraints gbc_addBathing_lbl = new GridBagConstraints();
		gbc_addBathing_lbl.fill = GridBagConstraints.HORIZONTAL;
		gbc_addBathing_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_addBathing_lbl.gridx = 6;
		gbc_addBathing_lbl.gridy = 1;
		frmAddBathing.getContentPane().add(addBathing_lbl, gbc_addBathing_lbl);

		JCheckBox isHairDryer_chkbox = new JCheckBox("Hair dryer ");
		isHairDryer_chkbox.setBackground(new Color(255, 200, 0));
		isHairDryer_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isHairDryer_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_isHairDryer_chkbox = new GridBagConstraints();
		gbc_isHairDryer_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isHairDryer_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isHairDryer_chkbox.gridx = 6;
		gbc_isHairDryer_chkbox.gridy = 3;
		frmAddBathing.getContentPane().add(isHairDryer_chkbox, gbc_isHairDryer_chkbox);

		JCheckBox isToiletPaper_chkbox = new JCheckBox("Toilet paper ");
		isToiletPaper_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isToiletPaper_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isToiletPaper_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isToiletPaper_chkbox = new GridBagConstraints();
		gbc_isToiletPaper_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isToiletPaper_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isToiletPaper_chkbox.gridx = 6;
		gbc_isToiletPaper_chkbox.gridy = 4;
		frmAddBathing.getContentPane().add(isToiletPaper_chkbox, gbc_isToiletPaper_chkbox);

		JCheckBox isShampoo_chkbox = new JCheckBox("Shampoo ");
		isShampoo_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isShampoo_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isShampoo_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isShampoo_chkbox = new GridBagConstraints();
		gbc_isShampoo_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isShampoo_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isShampoo_chkbox.gridx = 6;
		gbc_isShampoo_chkbox.gridy = 5;
		frmAddBathing.getContentPane().add(isShampoo_chkbox, gbc_isShampoo_chkbox);

		JButton toAddBathroom_btn = new JButton("Add bathroom");
		toAddBathroom_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * inserts the contents of the text fields in the database
				 */
				try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

						PreparedStatement stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);) {

					String isHairDryerString = String.valueOf(isHairDryer_chkbox.isSelected() ? 1 : 0);
					String isToiletPaperString = String.valueOf(isToiletPaper_chkbox.isSelected() ? 1 : 0);
					String isShampooString = String.valueOf(isShampoo_chkbox.isSelected() ? 1 : 0);

					stmt.setString(1, String.valueOf(facilityId));
					stmt.setString(2, isHairDryerString);
					stmt.setString(3, isToiletPaperString);
					stmt.setString(4, isShampooString);
					stmt.executeUpdate();
					frmAddBathing.dispose();

					PreparedStatement stmt2 = conn.prepareStatement(SQL_QUERY);
					{

						ResultSet rs1 = stmt2.executeQuery();
						int bathingId;
						if (rs1.next()) {
							if (rs1.getString(1) == null) {
								bathingId = 1;
							} else {
								bathingId = Integer.parseInt(rs1.getString(1));
							}
							AddBathroomFrame window = new AddBathroomFrame(bathingId);
							window.frmAddBathroom.setVisible(true);
						}

					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		toAddBathroom_btn.setFont(new Font("Tahoma", Font.BOLD, 25));
		GridBagConstraints gbc_toAddBathroom_btn = new GridBagConstraints();
		gbc_toAddBathroom_btn.anchor = GridBagConstraints.EAST;
		gbc_toAddBathroom_btn.insets = new Insets(0, 0, 5, 5);
		gbc_toAddBathroom_btn.gridx = 6;
		gbc_toAddBathroom_btn.gridy = 7;
		frmAddBathing.getContentPane().add(toAddBathroom_btn, gbc_toAddBathroom_btn);
		frmAddBathing.setBounds(100, 100, 1080, 720);
		frmAddBathing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}