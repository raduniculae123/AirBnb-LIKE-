

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;

public class ReviewFrame {

    private JFrame frmReview;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReviewFrame window = new ReviewFrame();
                    window.frmReview.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public ReviewFrame() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmReview = new JFrame("Reviews");
        frmReview.getContentPane().setEnabled(false);
        frmReview.getContentPane().setBackground(Color.ORANGE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 94, 148, 207, 0, 217, 0, 64, 0, 0, 0, 180, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{57, 111, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        frmReview.getContentPane().setLayout(gridBagLayout);

        JLabel addReview_lbl = new JLabel("Review score");
        addReview_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
        GridBagConstraints gbc_addReview_lbl = new GridBagConstraints();
        gbc_addReview_lbl.fill = GridBagConstraints.HORIZONTAL;
        gbc_addReview_lbl.insets = new Insets(0, 0, 5, 5);
        gbc_addReview_lbl.gridx = 6;
        gbc_addReview_lbl.gridy = 1;
        frmReview.getContentPane().add(addReview_lbl, gbc_addReview_lbl);

        JLabel Cleanliness_lbl = new JLabel("Clean");
        addReview_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
        GridBagConstraints gbc_Cleanliness_lbl = new GridBagConstraints();
        gbc_Cleanliness_lbl.fill = GridBagConstraints.HORIZONTAL;
        gbc_Cleanliness_lbl.insets = new Insets(0, 0, 5, 5);
        gbc_Cleanliness_lbl.gridx = 5;
        gbc_Cleanliness_lbl.gridy = 2;
        frmReview.getContentPane().add(Cleanliness_lbl, gbc_Cleanliness_lbl);

        JComboBox isCleanliness_combobox = new JComboBox();
        isCleanliness_combobox.addItem("--Select Cleanliness Score--");
        isCleanliness_combobox.addItem("5");
        isCleanliness_combobox.addItem("4");
        isCleanliness_combobox.addItem("3");
        isCleanliness_combobox.addItem("2");
        isCleanliness_combobox.addItem("1");
        isCleanliness_combobox.setBackground(new Color(255, 200, 0));
        //isCleanliness_combobox.setHorizontalTextPosition(SwingConstants.LEFT);
        isCleanliness_combobox.setFont(new Font("Tahoma", Font.PLAIN, 25));
        GridBagConstraints gbc_isCleanliness_combobox = new GridBagConstraints();
        gbc_isCleanliness_combobox.anchor = GridBagConstraints.WEST;
        gbc_isCleanliness_combobox.insets = new Insets(0, 0, 5, 5);
        gbc_isCleanliness_combobox.gridx = 6;
        gbc_isCleanliness_combobox.gridy = 3;
        frmReview.getContentPane().add(isCleanliness_combobox, gbc_isCleanliness_combobox);

        JComboBox isCommunition_combobox = new JComboBox();
        isCommunition_combobox.addItem("--Select Communition Score--");
        isCommunition_combobox.addItem("5");
        isCommunition_combobox.addItem("4");
        isCommunition_combobox.addItem("3");
        isCommunition_combobox.addItem("2");
        isCommunition_combobox.addItem("1");
        isCommunition_combobox.setBackground(new Color(255, 200, 0));
        //isCleanliness_combobox.setHorizontalTextPosition(SwingConstants.LEFT);
        isCommunition_combobox.setFont(new Font("Tahoma", Font.PLAIN, 25));
        GridBagConstraints gbc_isCommunition_combobox = new GridBagConstraints();
        gbc_isCommunition_combobox.anchor = GridBagConstraints.WEST;
        gbc_isCommunition_combobox.insets = new Insets(0, 0, 5, 5);
        gbc_isCommunition_combobox.gridx = 6;
        gbc_isCommunition_combobox.gridy = 4;
        frmReview.getContentPane().add(isCommunition_combobox, gbc_isCommunition_combobox);

        JComboBox isCheck_in_combobox = new JComboBox();
        isCheck_in_combobox.addItem("--Select Check-in Score--");
        isCheck_in_combobox.addItem("5");
        isCheck_in_combobox.addItem("4");
        isCheck_in_combobox.addItem("3");
        isCheck_in_combobox.addItem("2");
        isCheck_in_combobox.addItem("1");
        isCheck_in_combobox.setBackground(new Color(255, 200, 0));
        //isCleanliness_combobox.setHorizontalTextPosition(SwingConstants.LEFT);
        isCommunition_combobox.setFont(new Font("Tahoma", Font.PLAIN, 25));
        GridBagConstraints gbc_isCheck_in_combobox = new GridBagConstraints();
        gbc_isCheck_in_combobox.anchor = GridBagConstraints.WEST;
        gbc_isCheck_in_combobox.insets = new Insets(0, 0, 5, 5);
        gbc_isCheck_in_combobox.gridx = 6;
        gbc_isCheck_in_combobox.gridy = 5;
        frmReview.getContentPane().add(isCheck_in_combobox, gbc_isCheck_in_combobox);

        JComboBox isAccuracy_combobox = new JComboBox();
        isAccuracy_combobox.addItem("--Select Accuracy Score--");
        isAccuracy_combobox.addItem("5");
        isAccuracy_combobox.addItem("4");
        isAccuracy_combobox.addItem("3");
        isAccuracy_combobox.addItem("2");
        isAccuracy_combobox.addItem("1");
        isAccuracy_combobox.setBackground(new Color(255, 200, 0));
        //isCleanliness_combobox.setHorizontalTextPosition(SwingConstants.LEFT);
        isAccuracy_combobox.setFont(new Font("Tahoma", Font.PLAIN, 25));
        GridBagConstraints gbc_isAccuracy_combobox = new GridBagConstraints();
        gbc_isAccuracy_combobox.anchor = GridBagConstraints.WEST;
        gbc_isAccuracy_combobox.insets = new Insets(0, 0, 5, 5);
        gbc_isAccuracy_combobox.gridx = 6;
        gbc_isAccuracy_combobox.gridy = 6;
        frmReview.getContentPane().add(isAccuracy_combobox, gbc_isAccuracy_combobox);

        /*JCheckBox isOnRoadParking_chkbox = new JCheckBox("On-road parking");
        isOnRoadParking_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
        isOnRoadParking_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
        isOnRoadParking_chkbox.setBackground(Color.ORANGE);
        GridBagConstraints gbc_isOnRoadParking_chkbox = new GridBagConstraints();
        gbc_isOnRoadParking_chkbox.anchor = GridBagConstraints.WEST;
        gbc_isOnRoadParking_chkbox.insets = new Insets(0, 0, 5, 5);
        gbc_isOnRoadParking_chkbox.gridx = 6;
        gbc_isOnRoadParking_chkbox.gridy = 4;
        frmReview.getContentPane().add(isOnRoadParking_chkbox, gbc_isOnRoadParking_chkbox);

        JCheckBox isPaidParking_chkbox = new JCheckBox("Paid parking ");
        isPaidParking_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
        isPaidParking_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
        isPaidParking_chkbox.setBackground(Color.ORANGE);
        GridBagConstraints gbc_isPaidParking_chkbox = new GridBagConstraints();
        gbc_isPaidParking_chkbox.anchor = GridBagConstraints.WEST;
        gbc_isPaidParking_chkbox.insets = new Insets(0, 0, 5, 5);
        gbc_isPaidParking_chkbox.gridx = 6;
        gbc_isPaidParking_chkbox.gridy = 5;
        frmReview.getContentPane().add(isPaidParking_chkbox, gbc_isPaidParking_chkbox);

        JCheckBox isPatio_chkbox = new JCheckBox("Patio ");
        isPatio_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
        isPatio_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
        isPatio_chkbox.setBackground(Color.ORANGE);
        GridBagConstraints gbc_isPatio_chkbox = new GridBagConstraints();
        gbc_isPatio_chkbox.anchor = GridBagConstraints.WEST;
        gbc_isPatio_chkbox.insets = new Insets(0, 0, 5, 5);
        gbc_isPatio_chkbox.gridx = 6;
        gbc_isPatio_chkbox.gridy = 6;
        frmReview.getContentPane().add(isPatio_chkbox, gbc_isPatio_chkbox);

        JCheckBox isBarbeque_chkbox = new JCheckBox("Barbeque ");
        isBarbeque_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
        isBarbeque_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
        isBarbeque_chkbox.setBackground(Color.ORANGE);
        GridBagConstraints gbc_isBarbeque_chkbox = new GridBagConstraints();
        gbc_isBarbeque_chkbox.anchor = GridBagConstraints.WEST;
        gbc_isBarbeque_chkbox.insets = new Insets(0, 0, 5, 5);
        gbc_isBarbeque_chkbox.gridx = 6;
        gbc_isBarbeque_chkbox.gridy = 7;
        frmReview.getContentPane().add(isBarbeque_chkbox, gbc_isBarbeque_chkbox);*/

        JButton submit_btn = new JButton("Submit");
        submit_btn.setFont(new Font("Tahoma", Font.BOLD, 25));
        GridBagConstraints gbc_submit_btn = new GridBagConstraints();
        gbc_submit_btn.anchor = GridBagConstraints.EAST;
        gbc_submit_btn.insets = new Insets(0, 0, 5, 5);
        gbc_submit_btn.gridx = 6;
        gbc_submit_btn.gridy = 9;
        frmReview.getContentPane().add(submit_btn, gbc_submit_btn);
        frmReview.setBounds(100, 100, 1080, 720);
        frmReview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
