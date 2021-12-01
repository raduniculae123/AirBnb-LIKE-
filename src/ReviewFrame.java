package com2008project;

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
        gridBagLayout.columnWidths = new int[]{100, 100, 00, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{50, 150, 00, 0, 0, 0, 0, 0, 0, 0, 0, 0};
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

        JLabel Cleanliness_lbl = new JLabel("Cleanliess");
        Cleanliness_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
        GridBagConstraints gbc_Cleanliness_lbl = new GridBagConstraints();
        gbc_Cleanliness_lbl.fill = GridBagConstraints.WEST;
        gbc_Cleanliness_lbl.insets = new Insets(0, 0, 5, 5);
        gbc_Cleanliness_lbl.gridx = 5;
        gbc_Cleanliness_lbl.gridy = 3;
        frmReview.getContentPane().add(Cleanliness_lbl, gbc_Cleanliness_lbl);

        JLabel Com_lbl = new JLabel("Communication");
        Com_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
        GridBagConstraints gbc_Com_lbl = new GridBagConstraints();
        gbc_Com_lbl.fill = GridBagConstraints.WEST;
        gbc_Com_lbl.insets = new Insets(0, 0, 5, 5);
        gbc_Com_lbl.gridx = 5;
        gbc_Com_lbl.gridy = 4;
        frmReview.getContentPane().add(Com_lbl, gbc_Com_lbl);

        JLabel Checkin_lbl = new JLabel("Check-in");
        Checkin_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
        GridBagConstraints gbc_Checkin_lbl = new GridBagConstraints();
        gbc_Checkin_lbl.fill = GridBagConstraints.WEST;
        gbc_Checkin_lbl.insets = new Insets(0, 0, 5, 5);
        gbc_Checkin_lbl.gridx = 5;
        gbc_Checkin_lbl.gridy = 5;
        frmReview.getContentPane().add(Checkin_lbl, gbc_Checkin_lbl);

        JLabel Accuracy_lbl = new JLabel("Accurancy");
        Accuracy_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
        GridBagConstraints gbc_Accuracy_lbl = new GridBagConstraints();
        gbc_Accuracy_lbl.fill = GridBagConstraints.WEST;
        gbc_Accuracy_lbl.insets = new Insets(0, 0, 5, 5);
        gbc_Accuracy_lbl.gridx = 5;
        gbc_Accuracy_lbl.gridy = 6;
        frmReview.getContentPane().add(Accuracy_lbl, gbc_Accuracy_lbl);

        JLabel Location_lbl = new JLabel("Location");
        Location_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
        GridBagConstraints gbc_Location_lbl = new GridBagConstraints();
        gbc_Location_lbl.fill = GridBagConstraints.WEST;
        gbc_Location_lbl.insets = new Insets(0, 0, 5, 5);
        gbc_Location_lbl.gridx = 5;
        gbc_Location_lbl.gridy = 7;
        frmReview.getContentPane().add(Location_lbl, gbc_Location_lbl);

        JLabel Value_lbl = new JLabel("Value");
        Value_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
        GridBagConstraints gbc_Value_lbl = new GridBagConstraints();
        gbc_Value_lbl.fill = GridBagConstraints.WEST;
        gbc_Value_lbl.insets = new Insets(0, 0, 5, 5);
        gbc_Value_lbl.gridx = 5;
        gbc_Value_lbl.gridy = 8;
        frmReview.getContentPane().add(Value_lbl, gbc_Value_lbl);

        JComboBox isCleanliness_combobox = new JComboBox();
        isCleanliness_combobox.addItem("--Select Score--");
        isCleanliness_combobox.addItem("          5");
        isCleanliness_combobox.addItem("          4");
        isCleanliness_combobox.addItem("          3");
        isCleanliness_combobox.addItem("          2");
        isCleanliness_combobox.addItem("          1");
        isCleanliness_combobox.setBackground(new Color(255, 200, 0));
        //isCleanliness_combobox.setHorizontalTextPosition(SwingConstants.LEFT);
        isCleanliness_combobox.setFont(new Font("Tahoma", Font.PLAIN, 25));
        GridBagConstraints gbc_isCleanliness_combobox = new GridBagConstraints();
        gbc_isCleanliness_combobox.anchor = GridBagConstraints.WEST;
        gbc_isCleanliness_combobox.insets = new Insets(0, 0, 5, 5);
        gbc_isCleanliness_combobox.gridx = 7;
        gbc_isCleanliness_combobox.gridy = 3;
        frmReview.getContentPane().add(isCleanliness_combobox, gbc_isCleanliness_combobox);

        JComboBox isCommunition_combobox = new JComboBox();
        isCommunition_combobox.addItem("--Select Score--");
        isCommunition_combobox.addItem("          5");
        isCommunition_combobox.addItem("          4");
        isCommunition_combobox.addItem("          3");
        isCommunition_combobox.addItem("          2");
        isCommunition_combobox.addItem("          1");
        isCommunition_combobox.setBackground(new Color(255, 200, 0));
        //isCleanliness_combobox.setHorizontalTextPosition(SwingConstants.LEFT);
        isCommunition_combobox.setFont(new Font("Tahoma", Font.PLAIN, 25));
        GridBagConstraints gbc_isCommunition_combobox = new GridBagConstraints();
        gbc_isCommunition_combobox.anchor = GridBagConstraints.WEST;
        gbc_isCommunition_combobox.insets = new Insets(0, 0, 5, 5);
        gbc_isCommunition_combobox.gridx = 7;
        gbc_isCommunition_combobox.gridy = 4;
        frmReview.getContentPane().add(isCommunition_combobox, gbc_isCommunition_combobox);

        JComboBox isCheck_in_combobox = new JComboBox();
        isCheck_in_combobox.addItem("--Select Score--");
        isCheck_in_combobox.addItem("          5");
        isCheck_in_combobox.addItem("          4");
        isCheck_in_combobox.addItem("          3");
        isCheck_in_combobox.addItem("          2");
        isCheck_in_combobox.addItem("          1");
        isCheck_in_combobox.setBackground(new Color(255, 200, 0));
        //isCleanliness_combobox.setHorizontalTextPosition(SwingConstants.LEFT);
        isCheck_in_combobox.setFont(new Font("Tahoma", Font.PLAIN, 25));
        GridBagConstraints gbc_isCheck_in_combobox = new GridBagConstraints();
        gbc_isCheck_in_combobox.anchor = GridBagConstraints.WEST;
        gbc_isCheck_in_combobox.insets = new Insets(0, 0, 5, 5);
        gbc_isCheck_in_combobox.gridx = 7;
        gbc_isCheck_in_combobox.gridy = 5;
        frmReview.getContentPane().add(isCheck_in_combobox, gbc_isCheck_in_combobox);

        JComboBox isAccuracy_combobox = new JComboBox();
        isAccuracy_combobox.addItem("--Select Score--");
        isAccuracy_combobox.addItem("          5");
        isAccuracy_combobox.addItem("          4");
        isAccuracy_combobox.addItem("          3");
        isAccuracy_combobox.addItem("          2");
        isAccuracy_combobox.addItem("          1");
        isAccuracy_combobox.setBackground(new Color(255, 200, 0));
        //isCleanliness_combobox.setHorizontalTextPosition(SwingConstants.LEFT);
        isAccuracy_combobox.setFont(new Font("Tahoma", Font.PLAIN, 25));
        GridBagConstraints gbc_isAccuracy_combobox = new GridBagConstraints();
        gbc_isAccuracy_combobox.anchor = GridBagConstraints.WEST;
        gbc_isAccuracy_combobox.insets = new Insets(0, 0, 5, 5);
        gbc_isAccuracy_combobox.gridx = 7;
        gbc_isAccuracy_combobox.gridy = 6;
        frmReview.getContentPane().add(isAccuracy_combobox, gbc_isAccuracy_combobox);


        JComboBox islocation_combobox = new JComboBox();
        islocation_combobox.addItem("--Select Score--");
        islocation_combobox.addItem("          5");
        islocation_combobox.addItem("          4");
        islocation_combobox.addItem("          3");
        islocation_combobox.addItem("          2");
        islocation_combobox.addItem("          1");
        islocation_combobox.setBackground(new Color(255, 200, 0));
        //isCleanliness_combobox.setHorizontalTextPosition(SwingConstants.LEFT);
        islocation_combobox.setFont(new Font("Tahoma", Font.PLAIN, 25));
        GridBagConstraints gbc_location_combobox = new GridBagConstraints();
        gbc_location_combobox.anchor = GridBagConstraints.WEST;
        gbc_location_combobox.insets = new Insets(0, 0, 5, 5);
        gbc_location_combobox.gridx = 7;
        gbc_location_combobox.gridy = 7;
        frmReview.getContentPane().add(islocation_combobox, gbc_location_combobox);


        JComboBox isvalue_combobox = new JComboBox();
        isvalue_combobox.addItem("--Select Score--");
        isvalue_combobox.addItem("          5");
        isvalue_combobox.addItem("          4");
        isvalue_combobox.addItem("          3");
        isvalue_combobox.addItem("          2");
        isvalue_combobox.addItem("          1");
        isvalue_combobox.setBackground(new Color(255, 200, 0));
        //isCleanliness_combobox.setHorizontalTextPosition(SwingConstants.LEFT);
        isvalue_combobox.setFont(new Font("Tahoma", Font.PLAIN, 25));
        GridBagConstraints gbc_value_combobox = new GridBagConstraints();
        gbc_value_combobox.anchor = GridBagConstraints.WEST;
        gbc_value_combobox.insets = new Insets(0, 0, 5, 5);
        gbc_value_combobox.gridx = 7;
        gbc_value_combobox.gridy = 8;
        frmReview.getContentPane().add(isvalue_combobox, gbc_value_combobox);

        JButton submit_btn = new JButton("Submit");
        submit_btn.setFont(new Font("Tahoma", Font.BOLD, 25));
        GridBagConstraints gbc_submit_btn = new GridBagConstraints();
        gbc_submit_btn.anchor = GridBagConstraints.EAST;
        gbc_submit_btn.insets = new Insets(0, 0, 5, 5);
        gbc_submit_btn.gridx = 7;
        gbc_submit_btn.gridy = 11;
        frmReview.getContentPane().add(submit_btn, gbc_submit_btn);
        frmReview.setBounds(100, 100, 1080, 720);
        frmReview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        submit_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

                     //PreparedStatement stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);) {

                String s1=(String) isCleanliness_combobox.getSelectedItem();String s11 = s1.trim();
                String s2=(String) isCommunition_combobox.getSelectedItem();String s12 = s2.trim();
                String s3=(String) isCheck_in_combobox.getSelectedItem();String s13 = s3.trim();
                String s4=(String) isAccuracy_combobox.getSelectedItem();String s14 = s4.trim();
                String s5=(String) islocation_combobox.getSelectedItem();String s15 = s5.trim();
                String s6=(String) isvalue_combobox.getSelectedItem();String s16 = s6.trim();

                System.out.println(s11);
                System.out.println(s12);
                System.out.println(s13);
                try{
                    int i1 = Integer.parseInt(s11);
                    int i2 = Integer.parseInt(s12);
                    int i3 = Integer.parseInt(s13);
                    int i4 = Integer.parseInt(s14);
                    int i5 = Integer.parseInt(s15);
                    int i6 = Integer.parseInt(s16);
                    int i7 = i1+i2+i3+i4+i5+i6;
                    double avg = i7/6.0;
                    System.out.println(i7);
                    System.out.println("Average score is" + avg);
                }
                catch(NumberFormatException a){
                    JOptionPane.showMessageDialog(null, "Choose a right score ");

                }



                   /* stmt.setString(1, String.valueOf(facilityId));
                    stmt.setString(2, isHeatingString);
                    stmt.setString(3, isWashingMachineString);
                    stmt.setString(4, isDryingMachineString);
                    stmt.setString(5, isFireExtinguisherString);
                    stmt.setString(6, isSmokeAlarmString);
                    stmt.setString(7, isFirstAidKitString);

                    stmt.executeUpdate();
                    ReviewFrame.dispose();*/

                } //catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    //e1.printStackTrace();
                //}
            //}
        });

    }

}