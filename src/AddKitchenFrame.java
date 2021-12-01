

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class AddKitchenFrame {

	protected JFrame frmAddKitchen;
	static final String DB_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/team002";
	static final String USER = "team002";
	static final String PASS = "38695e46";
	private static final String SQL_INSERT = "INSERT INTO team002.kitchens (facilityID, refrigerator, microwave, stove, oven, dishwasher, pan, pot, plates, cutlery, oil, salt, pepper, sugar) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	

	/**
	 * Create the application.
	 */
	public AddKitchenFrame(int facilityId) {
		initialize(facilityId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int facilityId) {
		frmAddKitchen = new JFrame();
		frmAddKitchen.getContentPane().setEnabled(false);
		frmAddKitchen.getContentPane().setBackground(Color.ORANGE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 94, 148, 207, 0, 217, 0, 64, 0, 0, 0, 180, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{57, 111, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmAddKitchen.getContentPane().setLayout(gridBagLayout);
		
		JLabel addKitchen_lbl = new JLabel("Add Kitchen Facilities");
		addKitchen_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		GridBagConstraints gbc_addKitchen_lbl = new GridBagConstraints();
		gbc_addKitchen_lbl.fill = GridBagConstraints.HORIZONTAL;
		gbc_addKitchen_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_addKitchen_lbl.gridx = 6;
		gbc_addKitchen_lbl.gridy = 1;
		frmAddKitchen.getContentPane().add(addKitchen_lbl, gbc_addKitchen_lbl);
		
		JCheckBox isRefrigerator_chkbox = new JCheckBox("Refrigerator ");
		isRefrigerator_chkbox.setBackground(new Color(255, 200, 0));
		isRefrigerator_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isRefrigerator_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_isRefrigerator_chkbox = new GridBagConstraints();
		gbc_isRefrigerator_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isRefrigerator_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isRefrigerator_chkbox.gridx = 5;
		gbc_isRefrigerator_chkbox.gridy = 2;
		frmAddKitchen.getContentPane().add(isRefrigerator_chkbox, gbc_isRefrigerator_chkbox);
		
		JCheckBox isPlates_chkbox = new JCheckBox("Plates ");
		isPlates_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isPlates_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isPlates_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isPlates_chkbox = new GridBagConstraints();
		gbc_isPlates_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isPlates_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isPlates_chkbox.gridx = 7;
		gbc_isPlates_chkbox.gridy = 2;
		frmAddKitchen.getContentPane().add(isPlates_chkbox, gbc_isPlates_chkbox);
		
		JCheckBox isMicrowave_chkbox = new JCheckBox("Microwave ");
		isMicrowave_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isMicrowave_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isMicrowave_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isMicrowave_chkbox = new GridBagConstraints();
		gbc_isMicrowave_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isMicrowave_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isMicrowave_chkbox.gridx = 5;
		gbc_isMicrowave_chkbox.gridy = 3;
		frmAddKitchen.getContentPane().add(isMicrowave_chkbox, gbc_isMicrowave_chkbox);
		
		JCheckBox isCutlery_chkbox = new JCheckBox("Cutlery ");
		isCutlery_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isCutlery_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isCutlery_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isCutlery_chkbox = new GridBagConstraints();
		gbc_isCutlery_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isCutlery_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isCutlery_chkbox.gridx = 7;
		gbc_isCutlery_chkbox.gridy = 3;
		frmAddKitchen.getContentPane().add(isCutlery_chkbox, gbc_isCutlery_chkbox);
		
		JCheckBox isStove_chkbox = new JCheckBox("Stove ");
		isStove_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isStove_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isStove_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isStove_chkbox = new GridBagConstraints();
		gbc_isStove_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isStove_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isStove_chkbox.gridx = 5;
		gbc_isStove_chkbox.gridy = 4;
		frmAddKitchen.getContentPane().add(isStove_chkbox, gbc_isStove_chkbox);
		
		JCheckBox isOil_chkbox = new JCheckBox("Oil ");
		isOil_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isOil_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isOil_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isOil_chkbox = new GridBagConstraints();
		gbc_isOil_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isOil_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isOil_chkbox.gridx = 7;
		gbc_isOil_chkbox.gridy = 4;
		frmAddKitchen.getContentPane().add(isOil_chkbox, gbc_isOil_chkbox);
		
		JCheckBox isOven_chkbox = new JCheckBox("Oven ");
		isOven_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isOven_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isOven_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isOven_chkbox = new GridBagConstraints();
		gbc_isOven_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isOven_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isOven_chkbox.gridx = 5;
		gbc_isOven_chkbox.gridy = 5;
		frmAddKitchen.getContentPane().add(isOven_chkbox, gbc_isOven_chkbox);
		
		JCheckBox isSalt_chkbox = new JCheckBox("Salt ");
		isSalt_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isSalt_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isSalt_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isSalt_chkbox = new GridBagConstraints();
		gbc_isSalt_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isSalt_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isSalt_chkbox.gridx = 7;
		gbc_isSalt_chkbox.gridy = 5;
		frmAddKitchen.getContentPane().add(isSalt_chkbox, gbc_isSalt_chkbox);
		
		JCheckBox isDishwasher_chkbox = new JCheckBox("Dishwasher ");
		isDishwasher_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isDishwasher_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isDishwasher_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isDishwasher_chkbox = new GridBagConstraints();
		gbc_isDishwasher_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isDishwasher_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isDishwasher_chkbox.gridx = 5;
		gbc_isDishwasher_chkbox.gridy = 6;
		frmAddKitchen.getContentPane().add(isDishwasher_chkbox, gbc_isDishwasher_chkbox);
		
		JCheckBox isPepper_chkbox = new JCheckBox("Pepper ");
		isPepper_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isPepper_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isPepper_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isPepper_chkbox = new GridBagConstraints();
		gbc_isPepper_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isPepper_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isPepper_chkbox.gridx = 7;
		gbc_isPepper_chkbox.gridy = 6;
		frmAddKitchen.getContentPane().add(isPepper_chkbox, gbc_isPepper_chkbox);
		
		JCheckBox isPan_chkbox = new JCheckBox("Pan ");
		isPan_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isPan_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isPan_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isPan_chkbox = new GridBagConstraints();
		gbc_isPan_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isPan_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isPan_chkbox.gridx = 5;
		gbc_isPan_chkbox.gridy = 7;
		frmAddKitchen.getContentPane().add(isPan_chkbox, gbc_isPan_chkbox);
		
		JCheckBox isSugar_chkbox = new JCheckBox("Sugar ");
		isSugar_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isSugar_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isSugar_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isSugar_chkbox = new GridBagConstraints();
		gbc_isSugar_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isSugar_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isSugar_chkbox.gridx = 7;
		gbc_isSugar_chkbox.gridy = 7;
		frmAddKitchen.getContentPane().add(isSugar_chkbox, gbc_isSugar_chkbox);
		
		JCheckBox isPot_chkbox = new JCheckBox("Pot ");
		isPot_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isPot_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isPot_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isPot_chkbox = new GridBagConstraints();
		gbc_isPot_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isPot_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isPot_chkbox.gridx = 5;
		gbc_isPot_chkbox.gridy = 8;
		frmAddKitchen.getContentPane().add(isPot_chkbox, gbc_isPot_chkbox);
		
		JButton submit_btn = new JButton("Submit");
		submit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

						PreparedStatement stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);) {

					String isRefrigeratorString = String.valueOf(isRefrigerator_chkbox.isSelected() ? 1 : 0);
					String isMicrowaveString = String.valueOf(isMicrowave_chkbox.isSelected() ? 1 : 0);
					String isStoveString = String.valueOf(isStove_chkbox.isSelected() ? 1 : 0);
					String isOvenString = String.valueOf(isOven_chkbox.isSelected() ? 1 : 0);
					String isDishwasherString = String.valueOf(isDishwasher_chkbox.isSelected() ? 1 : 0);
					String isPanString = String.valueOf(isPan_chkbox.isSelected() ? 1 : 0);
					String isPotString = String.valueOf(isPot_chkbox.isSelected() ? 1 : 0);
					String isPlatesString = String.valueOf(isPlates_chkbox.isSelected() ? 1 : 0);
					String isCutleryString = String.valueOf(isCutlery_chkbox.isSelected() ? 1 : 0);
					String isOilString = String.valueOf(isOil_chkbox.isSelected() ? 1 : 0);
					String isSaltString = String.valueOf(isSalt_chkbox.isSelected() ? 1 : 0);
					String isPepperString = String.valueOf(isPepper_chkbox.isSelected() ? 1 : 0);
					String isSugarString = String.valueOf(isSugar_chkbox.isSelected() ? 1 : 0);
					
					stmt.setString(1, String.valueOf(facilityId));
					stmt.setString(2, isRefrigeratorString);
					stmt.setString(3, isMicrowaveString);
					stmt.setString(4, isStoveString);
					stmt.setString(5, isOvenString);
					stmt.setString(6, isDishwasherString);
					stmt.setString(7, isPanString);
					stmt.setString(8, isPotString);
					stmt.setString(9, isCutleryString);
					stmt.setString(10, isPlatesString);
					stmt.setString(11, isOilString);
					stmt.setString(12, isSaltString);
					stmt.setString(13, isPepperString);
					stmt.setString(14, isSugarString);
					
					stmt.executeUpdate();
					frmAddKitchen.dispose();
					
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
		gbc_submit_btn.gridx = 7;
		gbc_submit_btn.gridy = 9;
		frmAddKitchen.getContentPane().add(submit_btn, gbc_submit_btn);
		frmAddKitchen.setBounds(100, 100, 1080, 720);
		frmAddKitchen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
