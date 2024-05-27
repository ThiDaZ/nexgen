/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Color;
import javax.swing.JOptionPane;
import model.MySQL;
import java.sql.ResultSet;
import javax.swing.UIManager;


/**
 *
 * @author thidas
 */
public class SignIn extends javax.swing.JFrame {

    /**
     * Creates new form SignIn
     */
    public SignIn() {
        initComponents();
      
    }
    
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new components.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        logoLabel = new javax.swing.JLabel();
        backgroundLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NexGen Login");
        setMaximumSize(new java.awt.Dimension(1248, 832));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setBackground(new java.awt.Color(9, 9, 11));
        panelRound1.setRoundBottomLeft(8);
        panelRound1.setRoundBottomRight(8);
        panelRound1.setRoundTopLeft(8);
        panelRound1.setRoundTopRight(8);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Sign in");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel3.setText("Sign in to continue NexGen");

        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });

        usernameLabel.setText("Username");

        passwordLabel.setText("Password");

        loginButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(passwordLabel)
                    .addComponent(usernameLabel)
                    .addComponent(passwordField)
                    .addComponent(usernameField)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(46, 46, 46)
                .addComponent(usernameLabel)
                .addGap(8, 8, 8)
                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(passwordLabel)
                .addGap(8, 8, 8)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        getContentPane().add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 279, 525, 424));

        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/logoWhite.png"))); // NOI18N
        getContentPane().add(logoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(525, 128, 230, 50));

        backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/loginBackground.png"))); // NOI18N
        backgroundLabel.setFocusable(false);
        backgroundLabel.setRequestFocusEnabled(false);
        getContentPane().add(backgroundLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        Home home = new Home();
        home.setVisible(true);
        this.dispose();

//        if (username.isEmpty()) {
//            jTextField1.putClientProperty("JComponent.outline", "error");
//        } else if (password.isEmpty()) {
//            jPasswordField1.putClientProperty("JComponent.outline", "error");
//        } else {
//            try {
//
//                ResultSet resultSet = MySQL.exucute("SELECT * FROM `user` WHERE `username` = '" + username + "' AND `password` = '" + password + "'");
//                if (resultSet.next()) {
//                    JOptionPane.showMessageDialog(this, "Success!");
//                    Home home = new Home();
//                    home.setVisible(true);
//                    this.dispose();
//                } else {
//                    JOptionPane.showMessageDialog(this, "Wrong credentioal", "Warning", JOptionPane.WARNING_MESSAGE);
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

    }//GEN-LAST:event_loginButtonActionPerformed

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldActionPerformed

  
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        Color myWhite = new Color(250, 250, 250);
        Color myBlack = new Color(9, 9, 11);

        FlatDarkLaf.registerCustomDefaultsSource("resources.theme");

        FlatDarkLaf.setup();
//        UIManager.put("TextComponent.arc", 8);
//        UIManager.put("TextField.foreground", myWhite);
//        UIManager.put("TextField.background", myBlack);

        UIManager.put("PasswordField.foreground", myWhite);
        UIManager.put("PasswordField.background", myBlack);

        UIManager.put("Button.arc", 8);
        UIManager.put("Button.background", myWhite);
        UIManager.put("Button.foreground", myBlack);
        UIManager.put("background", myBlack);

        UIManager.put("Button", "borderColor: #09090B; background: #09090B; foreground: #fff");


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignIn().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel logoLabel;
    private components.PanelRound panelRound1;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
