import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

/**
 *a class for adding the bathroom details
 */
public class AddBathroomFrame {
	private static int bathrooms;
	protected JFrame frmAddBathroom;
	static final String DB_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/team002";
	static final String USER = "team002";
	static final String PASS = "38695e46";
	private static final String SQL_INSERT = "INSERT INTO team002.bathrooms (bathingID, toilet, shower, bath, isShared) VALUES (?,?,?,?,?)";
	private static final String SQL_INSERT2 = "UPDATE team002.bathings SET bathroomNr = ? WHERE id = (SELECT MAX(bathingID) FROM team002.bathrooms)";
	/**
	 * Create the application.
	 */
	public AddBathroomFrame(int bathingId) {
		initialize(bathingId);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param bathingId
	 */
	private void initialize(int bathingId) {
		frmAddBathroom = new JFrame();
		frmAddBathroom.getContentPane().setEnabled(false);
		frmAddBathroom.getContentPane().setBackground(Color.ORANGE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 94, 148, 207, 0, 217, 0, 64, 0, 0, 0, 180, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{57, 111, 40, 0, 0, 0, 0, 59, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmAddBathroom.getContentPane().setLayout(gridBagLayout);
		
		/**
		 * adding the checkboxes and labels
		 */
		JLabel addOutdoor_lbl = new JLabel("Add Bathroom");
		addOutdoor_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		GridBagConstraints gbc_addOutdoor_lbl = new GridBagConstraints();
		gbc_addOutdoor_lbl.fill = GridBagConstraints.HORIZONTAL;
		gbc_addOutdoor_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_addOutdoor_lbl.gridx = 6;
		gbc_addOutdoor_lbl.gridy = 1;
		frmAddBathroom.getContentPane().add(addOutdoor_lbl, gbc_addOutdoor_lbl);
		
		JCheckBox isToilet_chkbox = new JCheckBox("Toilet ");
		isToilet_chkbox.setVerticalAlignment(SwingConstants.BOTTOM);
		isToilet_chkbox.setBackground(new Color(255, 200, 0));
		isToilet_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isToilet_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_isToilet_chkbox = new GridBagConstraints();
		gbc_isToilet_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isToilet_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isToilet_chkbox.gridx = 6;
		gbc_isToilet_chkbox.gridy = 3;
		frmAddBathroom.getContentPane().add(isToilet_chkbox, gbc_isToilet_chkbox);
		
		JCheckBox isBath_chkbox = new JCheckBox("Bath ");
		isBath_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isBath_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isBath_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isBath_chkbox = new GridBagConstraints();
		gbc_isBath_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isBath_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isBath_chkbox.gridx = 6;
		gbc_isBath_chkbox.gridy = 4;
		frmAddBathroom.getContentPane().add(isBath_chkbox, gbc_isBath_chkbox);
		
		JCheckBox isShower_chkbox = new JCheckBox("Shower ");
		isShower_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isShower_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isShower_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isShower_chkbox = new GridBagConstraints();
		gbc_isShower_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isShower_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isShower_chkbox.gridx = 6;
		gbc_isShower_chkbox.gridy = 5;
		frmAddBathroom.getContentPane().add(isShower_chkbox, gbc_isShower_chkbox);
		
		JCheckBox isShared_chkbox = new JCheckBox("Is it shared? ");
		isShared_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isShared_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isShared_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isShared_chkbox = new GridBagConstraints();
		gbc_isShared_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isShared_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isShared_chkbox.gridx = 6;
		gbc_isShared_chkbox.gridy = 6;
		frmAddBathroom.getContentPane().add(isShared_chkbox, gbc_isShared_chkbox);
		
		JButton addAnotherBathroom_btn = new JButton("Add another bathroom");
		addAnotherBathroom_btn.addActionListener(new ActionListener() {
			/**
			 * inserts the contents of the text fields in the database
			 */
			public void actionPerformed(ActionEvent e) {
				try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

					PreparedStatement stmt = conn.prepareStatement(SQL_INSERT);) {

					String isToiletString = String.valueOf(isToilet_chkbox.isSelected() ? 1 : 0);
					String isShowerString = String.valueOf(isShower_chkbox.isSelected() ? 1 : 0);
					String isBathString = String.valueOf(isBath_chkbox.isSelected() ? 1 : 0);
					String isSharedString = String.valueOf(isShared_chkbox.isSelected() ? 1 : 0);
					
					
					stmt.setString(1, String.valueOf(bathingId));
					stmt.setString(2, isToiletString);
					stmt.setString(3, isShowerString);
					stmt.setString(4,isBathString);
					stmt.setString(5, isSharedString);
							
					stmt.executeUpdate();
					
					bathrooms++;
					
					
					PreparedStatement stmt2 = conn.prepareStatement(SQL_INSERT2, Statement.RETURN_GENERATED_KEYS); {

						stmt2.setInt(1, bathrooms);
						stmt2.executeUpdate();
					}
					
					frmAddBathroom.dispose();
					
					AddBathroomFrame window = new AddBathroomFrame(bathingId);
					window.frmAddBathroom.setVisible(true);
					
							
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		addAnotherBathroom_btn.setFont(new Font("Tahoma", Font.BOLD, 25));
		GridBagConstraints gbc_addAnotherBathroom_btn = new GridBagConstraints();
		gbc_addAnotherBathroom_btn.anchor = GridBagConstraints.EAST;
		gbc_addAnotherBathroom_btn.insets = new Insets(0, 0, 5, 5);
		gbc_addAnotherBathroom_btn.gridx = 6;
		gbc_addAnotherBathroom_btn.gridy = 9;
		frmAddBathroom.getContentPane().add(addAnotherBathroom_btn, gbc_addAnotherBathroom_btn);
		
		JButton saveChanges_btn = new JButton("Save changes");
		saveChanges_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

					PreparedStatement stmt = conn.prepareStatement(SQL_INSERT);) {

					String isToiletString = String.valueOf(isToilet_chkbox.isSelected() ? 1 : 0);
					String isShowerString = String.valueOf(isShower_chkbox.isSelected() ? 1 : 0);
					String isBathString = String.valueOf(isBath_chkbox.isSelected() ? 1 : 0);
					String isSharedString = String.valueOf(isShared_chkbox.isSelected() ? 1 : 0);
					
					
					stmt.setString(1, String.valueOf(bathingId));
					stmt.setString(2, isToiletString);
					stmt.setString(3, isShowerString);
					stmt.setString(4,isBathString);
					stmt.setString(5, isSharedString);
							
					stmt.executeUpdate();
					
					bathrooms++;
					
					
					PreparedStatement stmt2 = conn.prepareStatement(SQL_INSERT2, Statement.RETURN_GENERATED_KEYS); {

						stmt2.setInt(1, bathrooms);
						stmt2.executeUpdate();
					}
					
					frmAddBathroom.dispose();
					
							
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		saveChanges_btn.setFont(new Font("Tahoma", Font.BOLD, 25));
		GridBagConstraints gbc_saveChanges_btn = new GridBagConstraints();
		gbc_saveChanges_btn.insets = new Insets(0, 0, 5, 5);
		gbc_saveChanges_btn.gridx = 7;
		gbc_saveChanges_btn.gridy = 9;
		frmAddBathroom.getContentPane().add(saveChanges_btn, gbc_saveChanges_btn);
		frmAddBathroom.setBounds(100, 100, 1080, 720);
		frmAddBathroom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}