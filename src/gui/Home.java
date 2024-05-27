package gui;

import com.github.weisj.jsvg.nodes.Title;
import gui.popUps.brandManage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import raven.popup.DefaultOption;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;

/**
 *
 * @author thidas
 */
public class Home extends javax.swing.JFrame {

    private JButton activeButton;

    private Dashboard dashboard;
    private UserManage userManage;
    private ProductManage productManage;
    private SaleManage salaManage;
    private InventoryManage inventoryManage;
    private Warranty warranty;
    private Reporting reporting;

    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        init();
        addDashboard();
        time();
    }

    private void time() {
        final DateFormat timeFormat = new SimpleDateFormat("yyy MMMM dd    HH:mm aa");
        ActionListener timerListener = (ActionEvent e) -> {
            Date date = new Date();
            String time = timeFormat.format(date);
            timeLabel.setText(time);
        };
        Timer timer = new Timer(1000, timerListener);
        // to make sure it doesn't wait one second at the start
        timer.setInitialDelay(0);
        timer.start();
        
        
    }

    private void init() {
        GlassPanePopup.install(this);
    }

    public static void showPopUp() {
        brandManage brandManage = new gui.popUps.brandManage();
        DefaultOption option = new DefaultOption() {
            @Override
            public boolean closeWhenClickOutside() {
                return true;
            }
        };
        String actions[] = new String[]{"Cancel", "Save"};
        GlassPanePopup.showPopup(new SimplePopupBorder(brandManage, "Brand Manage", actions, (pc, i) -> {
            if (i == 1) {
                //save
                return;
            } else {
                pc.closePopup();
            }
        }), option);
    }

    private Color myWhite = new Color(250, 250, 250);
    private Color myBlack = new Color(9, 9, 11);

    // dashboard button icons
    private ImageIcon dashboadWhite = new ImageIcon("C:/Users/thida/OneDrive/Documents/NetBeansProjects/Nexgen/src/resources/icons/dashboard-white.png");
    private ImageIcon dashboardDark = new ImageIcon("C:/Users/thida/OneDrive/Documents/NetBeansProjects/Nexgen/src/resources/icons/dashboard-dark.png");
    private ImageIcon userWhite = new ImageIcon("C:/Users/thida/OneDrive/Documents/NetBeansProjects/Nexgen/src/resources/icons/user-white.png");
    private ImageIcon userDark = new ImageIcon("C:/Users/thida/OneDrive/Documents/NetBeansProjects/Nexgen/src/resources/icons/user-dark.png");
    private ImageIcon productWhite = new ImageIcon("C:/Users/thida/OneDrive/Documents/NetBeansProjects/Nexgen/src/resources/icons/box-white.png");
    private ImageIcon productDark = new ImageIcon("C:/Users/thida/OneDrive/Documents/NetBeansProjects/Nexgen/src/resources/icons/box-dark.png");
    private ImageIcon salesWhite = new ImageIcon("C:/Users/thida/OneDrive/Documents/NetBeansProjects/Nexgen/src/resources/icons/scale-balanced-white.png");
    private ImageIcon salesDark = new ImageIcon("C:/Users/thida/OneDrive/Documents/NetBeansProjects/Nexgen/src/resources/icons/scale-balanced-dark.png");
    private ImageIcon inventoryWhite = new ImageIcon("C:/Users/thida/OneDrive/Documents/NetBeansProjects/Nexgen/src/resources/icons/warehouse-white.png");
    private ImageIcon inventoryDark = new ImageIcon("C:/Users/thida/OneDrive/Documents/NetBeansProjects/Nexgen/src/resources/icons/warehouse-dark.png");
    private ImageIcon warrantyWhite = new ImageIcon("C:/Users/thida/OneDrive/Documents/NetBeansProjects/Nexgen/src/resources/icons/shield-white.png");
    private ImageIcon warrantyDark = new ImageIcon("C:/Users/thida/OneDrive/Documents/NetBeansProjects/Nexgen/src/resources/icons/shield-dark.png");
    private ImageIcon reportingWhite = new ImageIcon("C:/Users/thida/OneDrive/Documents/NetBeansProjects/Nexgen/src/resources/icons/chart-pie-white.png");
    private ImageIcon reportingDark = new ImageIcon("C:/Users/thida/OneDrive/Documents/NetBeansProjects/Nexgen/src/resources/icons/chart-pie-dark.png");

    // chanage icon color when inactive
    public void checkColor(String x) {
        switch (x) {
            case "Dashboard":
                activeButton.setIcon(dashboadWhite);
                break;
            case "User Management":
                activeButton.setIcon(userWhite);
                break;
            case "Product Management":
                activeButton.setIcon(productWhite);
                break;
            case "Sales Management":
                activeButton.setIcon(salesWhite);
                break;
            case "Inventory Management":
                activeButton.setIcon(inventoryWhite);
                break;
            case "Warranty":
                activeButton.setIcon(warrantyWhite);
                break;
            case "Reporting & Analytics":
                activeButton.setIcon(reportingWhite);
                break;
        }
    }

    public void addDashboard() {

        if (activeButton == dashboardButton) {
            String name = activeButton.getText();
            System.out.println(name + " Already active " + activeButton);
        } else {

            if (activeButton != null) {
                String name = activeButton.getText();
                activeButton.setBackground(myBlack);
                activeButton.setForeground(myWhite);
                checkColor(name);
                activeButton = null;
            }

            activeButton = dashboardButton;
            activeButton.setBackground(myWhite);
            activeButton.setForeground(myBlack);
            activeButton.setIcon(dashboardDark);
        }

        if (dashboard == null) {
            dashboard = new Dashboard();
        }
        bodyPanel.add(dashboard, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(bodyPanel);
    }

    public void addUserManage() {
        if (activeButton == userManageButton) {
            String name = activeButton.getText();
            System.out.println(name + " Already active " + activeButton);
        } else {

            if (activeButton != null) {
                String name = activeButton.getText();
                activeButton.setBackground(myBlack);
                activeButton.setForeground(myWhite);
                checkColor(name);
                activeButton = null;
            }

            activeButton = userManageButton;
            activeButton.setBackground(myWhite);
            activeButton.setForeground(myBlack);
            activeButton.setIcon(userDark);
        }
        if (userManage == null) {
            userManage = new UserManage();
        }
        bodyPanel.add(userManage, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(bodyPanel);
    }

    public void addProductManage() {
        if (activeButton == productManageButton) {
            String name = activeButton.getText();
            System.out.println(name + " Already active " + activeButton);
        } else {

            if (activeButton != null) {
                String name = activeButton.getText();
                activeButton.setBackground(myBlack);
                activeButton.setForeground(myWhite);
                checkColor(name);
                activeButton = null;
            }

            activeButton = productManageButton;
            activeButton.setBackground(myWhite);
            activeButton.setForeground(myBlack);
            activeButton.setIcon(productDark);
        }
        if (productManage == null) {
            productManage = new ProductManage();
        }
        bodyPanel.add(productManage, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(bodyPanel);
    }

    public void addSalesManage() {
        if (activeButton == salesManageButton) {
            String name = activeButton.getText();
            System.out.println(name + " Already active " + activeButton);
        } else {

            if (activeButton != null) {
                String name = activeButton.getText();
                activeButton.setBackground(myBlack);
                activeButton.setForeground(myWhite);
                checkColor(name);
                activeButton = null;
            }

            activeButton = salesManageButton;
            activeButton.setBackground(myWhite);
            activeButton.setForeground(myBlack);
            activeButton.setIcon(salesDark);
        }
        if (salaManage == null) {
            salaManage = new SaleManage();
        }
        bodyPanel.add(salaManage, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(bodyPanel);
    }

    public void addInventoryManage() {
        if (activeButton == inventoryManageButton) {
            String name = activeButton.getText();
            System.out.println(name + " Already active " + activeButton);
        } else {

            if (activeButton != null) {
                String name = activeButton.getText();
                activeButton.setBackground(myBlack);
                activeButton.setForeground(myWhite);
                checkColor(name);
                activeButton = null;
            }

            activeButton = inventoryManageButton;
            activeButton.setBackground(myWhite);
            activeButton.setForeground(myBlack);
            activeButton.setIcon(inventoryDark);
        }
        if (inventoryManage == null) {
            inventoryManage = new InventoryManage();
        }
        bodyPanel.add(inventoryManage, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(bodyPanel);
    }

    public void addWarranty() {
        if (activeButton == warrantyButton) {
            String name = activeButton.getText();
            System.out.println(name + " Already active " + activeButton);
        } else {

            if (activeButton != null) {
                String name = activeButton.getText();
                activeButton.setBackground(myBlack);
                activeButton.setForeground(myWhite);
                checkColor(name);
                activeButton = null;
            }

            activeButton = warrantyButton;
            activeButton.setBackground(myWhite);
            activeButton.setForeground(myBlack);
            activeButton.setIcon(warrantyDark);
        }
        if (warranty == null) {
            warranty = new Warranty();
        }
        bodyPanel.add(warranty, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(bodyPanel);
    }

    public void addReporting() {
        if (activeButton == repotingButton) {
            String name = activeButton.getText();
            System.out.println(name + " Already active " + activeButton);
        } else {

            if (activeButton != null) {
                String name = activeButton.getText();
                activeButton.setBackground(myBlack);
                activeButton.setForeground(myWhite);
                checkColor(name);
                activeButton = null;
            }

            activeButton = repotingButton;
            activeButton.setBackground(myWhite);
            activeButton.setForeground(myBlack);
            activeButton.setIcon(reportingDark);
        }
        if (reporting == null) {
            reporting = new Reporting();
        }
        bodyPanel.add(reporting, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(bodyPanel);
    }

    public void removeActivePanel() {
        Component[] components = bodyPanel.getComponents();
        for (Component component : components) {
            bodyPanel.remove(component);
        }
        SwingUtilities.updateComponentTreeUI(bodyPanel);
    }

    public void getActiveComponent() {
        System.out.println("-----");
        Component[] components = bodyPanel.getComponents();
        for (Component component : components) {
            System.out.println(component);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sideMenuPanel = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        dashboardButton = new javax.swing.JButton();
        userManageButton = new javax.swing.JButton();
        productManageButton = new javax.swing.JButton();
        salesManageButton = new javax.swing.JButton();
        inventoryManageButton = new javax.swing.JButton();
        warrantyButton = new javax.swing.JButton();
        repotingButton = new javax.swing.JButton();
        containerPanel = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        timeLabel = new javax.swing.JLabel();
        bodyPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(24, 24, 24));
        setMinimumSize(new java.awt.Dimension(1606, 900));

        sideMenuPanel.setBackground(new java.awt.Color(9, 9, 11));
        sideMenuPanel.setFocusable(false);
        sideMenuPanel.setPreferredSize(new java.awt.Dimension(250, 900));
        sideMenuPanel.setRequestFocusEnabled(false);

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/sideMenuLogo.png"))); // NOI18N

        dashboardButton.setBackground(new java.awt.Color(9, 9, 11));
        dashboardButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dashboardButton.setForeground(new java.awt.Color(250, 250, 250));
        dashboardButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/dashboard-white.png"))); // NOI18N
        dashboardButton.setText("Dashboard");
        dashboardButton.setBorderPainted(false);
        dashboardButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dashboardButton.setIconTextGap(14);
        dashboardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardButtonActionPerformed(evt);
            }
        });

        userManageButton.setBackground(new java.awt.Color(9, 9, 11));
        userManageButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        userManageButton.setForeground(new java.awt.Color(250, 250, 250));
        userManageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/user-white.png"))); // NOI18N
        userManageButton.setText("User Management");
        userManageButton.setBorderPainted(false);
        userManageButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userManageButton.setIconTextGap(16);
        userManageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userManageButtonActionPerformed(evt);
            }
        });

        productManageButton.setBackground(new java.awt.Color(9, 9, 11));
        productManageButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        productManageButton.setForeground(new java.awt.Color(250, 250, 250));
        productManageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/box-white.png"))); // NOI18N
        productManageButton.setText("Product Management");
        productManageButton.setBorderPainted(false);
        productManageButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        productManageButton.setIconTextGap(16);
        productManageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productManageButtonActionPerformed(evt);
            }
        });

        salesManageButton.setBackground(new java.awt.Color(9, 9, 11));
        salesManageButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        salesManageButton.setForeground(new java.awt.Color(250, 250, 250));
        salesManageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/scale-balanced-white.png"))); // NOI18N
        salesManageButton.setText("Sales Management");
        salesManageButton.setBorderPainted(false);
        salesManageButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        salesManageButton.setIconTextGap(16);
        salesManageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesManageButtonActionPerformed(evt);
            }
        });

        inventoryManageButton.setBackground(new java.awt.Color(9, 9, 11));
        inventoryManageButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        inventoryManageButton.setForeground(new java.awt.Color(250, 250, 250));
        inventoryManageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/warehouse-white.png"))); // NOI18N
        inventoryManageButton.setText("Inventory Management");
        inventoryManageButton.setBorderPainted(false);
        inventoryManageButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        inventoryManageButton.setIconTextGap(16);
        inventoryManageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventoryManageButtonActionPerformed(evt);
            }
        });

        warrantyButton.setBackground(new java.awt.Color(9, 9, 11));
        warrantyButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        warrantyButton.setForeground(new java.awt.Color(250, 250, 250));
        warrantyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/shield-white.png"))); // NOI18N
        warrantyButton.setText("Warranty");
        warrantyButton.setBorderPainted(false);
        warrantyButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        warrantyButton.setIconTextGap(16);
        warrantyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                warrantyButtonActionPerformed(evt);
            }
        });

        repotingButton.setBackground(new java.awt.Color(9, 9, 11));
        repotingButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        repotingButton.setForeground(new java.awt.Color(250, 250, 250));
        repotingButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/scale-balanced-white.png"))); // NOI18N
        repotingButton.setText("Reporting & Analytics");
        repotingButton.setBorderPainted(false);
        repotingButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        repotingButton.setIconTextGap(16);
        repotingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repotingButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sideMenuPanelLayout = new javax.swing.GroupLayout(sideMenuPanel);
        sideMenuPanel.setLayout(sideMenuPanelLayout);
        sideMenuPanelLayout.setHorizontalGroup(
            sideMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sideMenuPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(sideMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logo)
                    .addComponent(inventoryManageButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(salesManageButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(productManageButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userManageButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dashboardButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(warrantyButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(repotingButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        sideMenuPanelLayout.setVerticalGroup(
            sideMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sideMenuPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(logo)
                .addGap(98, 98, 98)
                .addComponent(dashboardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userManageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(productManageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salesManageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inventoryManageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warrantyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(repotingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(sideMenuPanel, java.awt.BorderLayout.LINE_START);

        containerPanel.setLayout(new java.awt.BorderLayout());

        header.setBackground(new java.awt.Color(24, 24, 24));
        header.setPreferredSize(new java.awt.Dimension(1356, 48));

        timeLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        timeLabel.setText("2024 March 25   00:46 am");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(timeLabel)
                .addContainerGap(1149, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(timeLabel)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        containerPanel.add(header, java.awt.BorderLayout.PAGE_START);

        bodyPanel.setLayout(new java.awt.BorderLayout());
        containerPanel.add(bodyPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(containerPanel, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dashboardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardButtonActionPerformed
        removeActivePanel();
        addDashboard();
    }//GEN-LAST:event_dashboardButtonActionPerformed

    private void userManageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userManageButtonActionPerformed
        removeActivePanel();
        addUserManage();
    }//GEN-LAST:event_userManageButtonActionPerformed

    private void productManageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productManageButtonActionPerformed
        removeActivePanel();
        addProductManage();
    }//GEN-LAST:event_productManageButtonActionPerformed

    private void repotingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repotingButtonActionPerformed
        removeActivePanel();
        addReporting();
    }//GEN-LAST:event_repotingButtonActionPerformed

    private void salesManageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesManageButtonActionPerformed
        removeActivePanel();
        addSalesManage();
    }//GEN-LAST:event_salesManageButtonActionPerformed

    private void inventoryManageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventoryManageButtonActionPerformed
        removeActivePanel();
        addInventoryManage();
    }//GEN-LAST:event_inventoryManageButtonActionPerformed

    private void warrantyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_warrantyButtonActionPerformed
        removeActivePanel();
        addWarranty();
    }//GEN-LAST:event_warrantyButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JPanel containerPanel;
    private javax.swing.JButton dashboardButton;
    private javax.swing.JPanel header;
    private javax.swing.JButton inventoryManageButton;
    private javax.swing.JLabel logo;
    private javax.swing.JButton productManageButton;
    private javax.swing.JButton repotingButton;
    private javax.swing.JButton salesManageButton;
    private javax.swing.JPanel sideMenuPanel;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JButton userManageButton;
    private javax.swing.JButton warrantyButton;
    // End of variables declaration//GEN-END:variables
}
