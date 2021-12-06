import javax.swing.JFrame;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

/**
 *a class for adding the bedroom details
 */
public class AddBedroomFrame {
	private static int bedrooms = 0;
	private static int beds = 0;
	private static int sleepers = 0;
	protected JFrame frmAddBedroom;
	static final String DB_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/team002";
	static final String USER = "team002";
	static final String PASS = "38695e46";
	private static final String SQL_INSERT = "INSERT INTO team002.bedrooms (sleepingID, bed1Type, bed2Type) VALUES (?,?,?)";
	private static final String SQL_INSERT2 = "UPDATE team002.sleepings SET bedroomNr = ?,  bedsNr = ?, sleepers = ? WHERE id = (SELECT MAX(sleepingID) FROM team002.bedrooms)";
	private static final String SQL_UPDATE_GUESTS = "UPDATE team002.properties SET guests = ? WHERE id = (SELECT MAX(propertyID) FROM team002.facilities WHERE id = (SELECT MAX(facilityID) FROM team002.sleepings WHERE id = (SELECT MAX(sleepingID) FROM team002.bedrooms)))";

	/**
	 * Create the application.
	 */
	public AddBedroomFrame(int sleepingId) {
		initialize(sleepingId);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param sleepingId
	 */
	private void initialize(int sleepingId) {
		frmAddBedroom = new JFrame();
		frmAddBedroom.getContentPane().setBackground(Color.ORANGE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 149, 0, 51, 136, 0, 318, 0, 0, 111, 175, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 75, 0, 69, 0, 0, 0, 0, 101, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		frmAddBedroom.getContentPane().setLayout(gridBagLayout);

		/**
		 * adding the comboboxes and labels
		 */
		JLabel addBedroom_lbl = new JLabel("Add Bedroom");
		addBedroom_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		GridBagConstraints gbc_addBedroom_lbl = new GridBagConstraints();
		gbc_addBedroom_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_addBedroom_lbl.gridx = 7;
		gbc_addBedroom_lbl.gridy = 1;
		frmAddBedroom.getContentPane().add(addBedroom_lbl, gbc_addBedroom_lbl);

		JLabel bed1Type_lbl = new JLabel("Bed 1 type:");
		bed1Type_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_bed1Type_lbl = new GridBagConstraints();
		gbc_bed1Type_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_bed1Type_lbl.gridx = 5;
		gbc_bed1Type_lbl.gridy = 4;
		frmAddBedroom.getContentPane().add(bed1Type_lbl, gbc_bed1Type_lbl);

		JComboBox bed1Type_cmbx = new JComboBox();
		bed1Type_cmbx.setBackground(Color.WHITE);
		bed1Type_cmbx.setFont(new Font("Tahoma", Font.PLAIN, 25));
		bed1Type_cmbx.setModel(
				new DefaultComboBoxModel(new String[] { "Single bed", "Double bed", "Kingsize bed", "Bunk bed" }));
		bed1Type_cmbx.setToolTipText("");
		GridBagConstraints gbc_bed1Type_cmbx = new GridBagConstraints();
		gbc_bed1Type_cmbx.insets = new Insets(0, 0, 5, 5);
		gbc_bed1Type_cmbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_bed1Type_cmbx.gridx = 7;
		gbc_bed1Type_cmbx.gridy = 4;
		frmAddBedroom.getContentPane().add(bed1Type_cmbx, gbc_bed1Type_cmbx);

		JLabel bed2Type_lbl = new JLabel("Bed 2 type:");
		bed2Type_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_bed2Type_lbl = new GridBagConstraints();
		gbc_bed2Type_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_bed2Type_lbl.gridx = 5;
		gbc_bed2Type_lbl.gridy = 6;
		frmAddBedroom.getContentPane().add(bed2Type_lbl, gbc_bed2Type_lbl);

		JComboBox bed2Type_cmbx = new JComboBox();
		bed2Type_cmbx.setModel(new DefaultComboBoxModel(
				new String[] { "Empty", "Single bed", "Double bed", "Kingsize bed", "Bunk bed" }));
		bed2Type_cmbx.setToolTipText("");
		bed2Type_cmbx.setFont(new Font("Tahoma", Font.PLAIN, 25));
		bed2Type_cmbx.setBackground(Color.WHITE);
		GridBagConstraints gbc_bed2Type_cmbx = new GridBagConstraints();
		gbc_bed2Type_cmbx.insets = new Insets(0, 0, 5, 5);
		gbc_bed2Type_cmbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_bed2Type_cmbx.gridx = 7;
		gbc_bed2Type_cmbx.gridy = 6;
		frmAddBedroom.getContentPane().add(bed2Type_cmbx, gbc_bed2Type_cmbx);

		JButton addAnotherBedroom_btn = new JButton("Add another bedroom");
		addAnotherBedroom_btn.addActionListener(new ActionListener() {
			/**
			 * inserts the contents of the text fields in the database
			 */
			public void actionPerformed(ActionEvent e) {
				try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

						PreparedStatement stmt = conn.prepareStatement(SQL_INSERT);) {

					String bed1TypeString = bed1Type_cmbx.getSelectedItem().toString();
					String bed2TypeString = bed2Type_cmbx.getSelectedItem().toString();

					stmt.setString(1, String.valueOf(sleepingId));
					stmt.setString(2, bed1TypeString);
					stmt.setString(3, bed2TypeString);

					stmt.executeUpdate();
					System.out.println("1btn add " + bedrooms);
					bedrooms++;
					System.out.println("2btn add " + bedrooms);
					sleepers += sleepersNumber(bed1TypeString.trim());
					sleepers += sleepersNumber(bed2TypeString.trim());
					if (!bed2TypeString.trim().equals("Empty"))
						beds += 2;
					else
						beds += 1;

					PreparedStatement stmt2 = conn.prepareStatement(SQL_INSERT2, Statement.RETURN_GENERATED_KEYS);
					{

						stmt2.setInt(1, bedrooms);
						stmt2.setInt(2, beds);
						stmt2.setInt(3, sleepers);
						stmt2.executeUpdate();
					}

					PreparedStatement stmt3 = conn.prepareStatement(SQL_UPDATE_GUESTS, Statement.RETURN_GENERATED_KEYS);
					{

						stmt3.setInt(1, sleepers);
						stmt3.executeUpdate();
					}

					frmAddBedroom.dispose();

					AddBedroomFrame window = new AddBedroomFrame(sleepingId);
					window.frmAddBedroom.setVisible(true);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		addAnotherBedroom_btn.setFont(new Font("Tahoma", Font.BOLD, 25));
		GridBagConstraints gbc_addAnotherBedroom_btn = new GridBagConstraints();
		gbc_addAnotherBedroom_btn.insets = new Insets(0, 0, 5, 5);
		gbc_addAnotherBedroom_btn.gridx = 7;
		gbc_addAnotherBedroom_btn.gridy = 8;
		frmAddBedroom.getContentPane().add(addAnotherBedroom_btn, gbc_addAnotherBedroom_btn);

		JButton saveChanges_btn = new JButton("Save changes");
		saveChanges_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

						PreparedStatement stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);) {

					String bed1TypeString = bed1Type_cmbx.getSelectedItem().toString();
					String bed2TypeString = bed2Type_cmbx.getSelectedItem().toString();

					stmt.setString(1, String.valueOf(sleepingId));
					stmt.setString(2, bed1TypeString);
					stmt.setString(3, bed2TypeString);

					stmt.executeUpdate();

					bedrooms++;
					sleepers += sleepersNumber(bed1TypeString.trim());
					sleepers += sleepersNumber(bed2TypeString.trim());
					if (!bed2TypeString.trim().equals("Empty"))
						beds += 2;
					else
						beds += 1;

					PreparedStatement stmt2 = conn.prepareStatement(SQL_INSERT2, Statement.RETURN_GENERATED_KEYS);
					{

						stmt2.setInt(1, bedrooms);
						stmt2.setInt(2, beds);
						stmt2.setInt(3, sleepers);
						stmt2.executeUpdate();
					}
					
					PreparedStatement stmt3 = conn.prepareStatement(SQL_UPDATE_GUESTS, Statement.RETURN_GENERATED_KEYS);
					{

						stmt3.setInt(1, sleepers);
						stmt3.executeUpdate();
					}

					frmAddBedroom.dispose();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		saveChanges_btn.setFont(new Font("Tahoma", Font.BOLD, 25));
		GridBagConstraints gbc_saveChanges_btn = new GridBagConstraints();
		gbc_saveChanges_btn.insets = new Insets(0, 0, 5, 5);
		gbc_saveChanges_btn.gridx = 10;
		gbc_saveChanges_btn.gridy = 8;
		frmAddBedroom.getContentPane().add(saveChanges_btn, gbc_saveChanges_btn);
		frmAddBedroom.setBounds(100, 100, 1080, 720);
		frmAddBedroom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private int sleepersNumber(String bedType) {
		if (bedType.equals("Single bed"))
			return 1;
		if (bedType.equals("Empty"))
			return 0;

		return 2;
	}

}