package gui;

import com.github.weisj.jsvg.nodes.Title;
import gui.dialog.ReportPanel;
import gui.popUps.Profile;
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
import model.UserModel;
import raven.popup.DefaultOption;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;

/**
 *
 * @author thidas
 */
public class Home extends javax.swing.JFrame {

    private UserModel user;

    private int userid;
    private String username;
    private String userRole;

    public void setUser(UserModel user) {
        this.user = user;
        if (user != null) {
            userid = user.getId();
            username = user.getUsername();
            userRole = user.getRole();
        } else {
            System.out.println("User is null in setUser");
        }
    }

    private JButton activeButton;

    private Dashboard dashboard;
    private UserManage userManage;
    private ProductManage productManage;
    private SaleManage salaManage;
    private InventoryManage inventoryManage;
//    private Reporting reporting;
    private CustomerManage customerManage;
    private GRN grn;
    private GRNhistory grnHistory;
    private invoice invoice;

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

        if (user != null) {
            System.out.println("user not null");
        } else {
            System.out.println("user null");

        }

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
    private ImageIcon reportingWhite = new ImageIcon("C:/Users/thida/OneDrive/Documents/NetBeansProjects/Nexgen/src/resources/icons/chart-pie-white.png");
    private ImageIcon reportingDark = new ImageIcon("C:/Users/thida/OneDrive/Documents/NetBeansProjects/Nexgen/src/resources/icons/chart-pie-dark.png");
    private ImageIcon CustomerWhite = new ImageIcon("C:/Users/thida/OneDrive/Documents/NetBeansProjects/Nexgen/src/resources/icons/customer-white.png");
    private ImageIcon CustomerDark = new ImageIcon("C:/Users/thida/OneDrive/Documents/NetBeansProjects/Nexgen/src/resources/icons/customer-dark.png");
    private ImageIcon GrnWhite = new ImageIcon("C:/Users/thida/OneDrive/Documents/NetBeansProjects/Nexgen/src/resources/icons/grn-white.png");
    private ImageIcon GrnDark = new ImageIcon("C:/Users/thida/OneDrive/Documents/NetBeansProjects/Nexgen/src/resources/icons/grn-dark.png");
    private ImageIcon GrnHistoryWhite = new ImageIcon("C:/Users/thida/OneDrive/Documents/NetBeansProjects/Nexgen/src/resources/icons/grn-history-white.png");
    private ImageIcon GrnHistoryDark = new ImageIcon("C:/Users/thida/OneDrive/Documents/NetBeansProjects/Nexgen/src/resources/icons/grn-history-dark.png");

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
            case "Reporting & Analytics":
                activeButton.setIcon(reportingWhite);
                break;
            case "Customer Manage":
                activeButton.setIcon(CustomerWhite);
                break;
            case "GRN":
                activeButton.setIcon(GrnWhite);
                break;
            case "GRN History":
                activeButton.setIcon(GrnHistoryWhite);
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
        if (invoice == null) {
            invoice = new invoice(userid, username);
        }
        bodyPanel.add(invoice, BorderLayout.CENTER);
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

    public void addCustomerManage() {
        if (activeButton == customerManageButton) {
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

            activeButton = customerManageButton;
            activeButton.setBackground(myWhite);
            activeButton.setForeground(myBlack);
            activeButton.setIcon(CustomerDark);
        }
        if (customerManage == null) {
            customerManage = new CustomerManage();
        }
        bodyPanel.add(customerManage, BorderLayout.CENTER);
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

    }

    public void addGrn() {
        if (activeButton == grnButton) {
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

            activeButton = grnButton;
            activeButton.setBackground(myWhite);
            activeButton.setForeground(myBlack);
            activeButton.setIcon(GrnDark);
        }
        if (grn == null) {
            grn = new GRN();
        }
        bodyPanel.add(grn, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(bodyPanel);
    }

    public void addGrnHistory() {
        if (activeButton == grnHistoryButton) {
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

            activeButton = grnHistoryButton;
            activeButton.setBackground(myWhite);
            activeButton.setForeground(myBlack);
            activeButton.setIcon(GrnHistoryDark);
        }
        if (grnHistory == null) {
            grnHistory = new GRNhistory();
        }
        bodyPanel.add(grnHistory, BorderLayout.CENTER);
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
        customerManageButton = new javax.swing.JButton();
        repotingButton = new javax.swing.JButton();
        grnButton = new javax.swing.JButton();
        grnHistoryButton = new javax.swing.JButton();
        containerPanel = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        timeLabel = new javax.swing.JLabel();
        profileImage = new main.ImageAvatar();
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

        customerManageButton.setBackground(new java.awt.Color(9, 9, 11));
        customerManageButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        customerManageButton.setForeground(new java.awt.Color(250, 250, 250));
        customerManageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/customer-white.png"))); // NOI18N
        customerManageButton.setText("Customer Manage");
        customerManageButton.setBorderPainted(false);
        customerManageButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        customerManageButton.setIconTextGap(16);
        customerManageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerManageButtonActionPerformed(evt);
            }
        });

        repotingButton.setBackground(new java.awt.Color(9, 9, 11));
        repotingButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        repotingButton.setForeground(new java.awt.Color(250, 250, 250));
        repotingButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/chart-pie-white.png"))); // NOI18N
        repotingButton.setText("Reporting & Analytics");
        repotingButton.setBorderPainted(false);
        repotingButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        repotingButton.setIconTextGap(16);
        repotingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repotingButtonActionPerformed(evt);
            }
        });

        grnButton.setBackground(new java.awt.Color(9, 9, 11));
        grnButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        grnButton.setForeground(new java.awt.Color(250, 250, 250));
        grnButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/grn-white.png"))); // NOI18N
        grnButton.setText("GRN");
        grnButton.setBorderPainted(false);
        grnButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        grnButton.setIconTextGap(16);
        grnButton.setMargin(new java.awt.Insets(2, 16, 3, 14));
        grnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grnButtonActionPerformed(evt);
            }
        });

        grnHistoryButton.setBackground(new java.awt.Color(9, 9, 11));
        grnHistoryButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        grnHistoryButton.setForeground(new java.awt.Color(250, 250, 250));
        grnHistoryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/grn-history-white.png"))); // NOI18N
        grnHistoryButton.setText("GRN History");
        grnHistoryButton.setBorderPainted(false);
        grnHistoryButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        grnHistoryButton.setIconTextGap(16);
        grnHistoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grnHistoryButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sideMenuPanelLayout = new javax.swing.GroupLayout(sideMenuPanel);
        sideMenuPanel.setLayout(sideMenuPanelLayout);
        sideMenuPanelLayout.setHorizontalGroup(
            sideMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sideMenuPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(sideMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(grnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(sideMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(logo)
                        .addComponent(inventoryManageButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(salesManageButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(productManageButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(userManageButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dashboardButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(customerManageButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(repotingButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(grnHistoryButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                .addComponent(customerManageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grnHistoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(repotingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(332, Short.MAX_VALUE))
        );

        getContentPane().add(sideMenuPanel, java.awt.BorderLayout.LINE_START);

        containerPanel.setLayout(new java.awt.BorderLayout());

        header.setBackground(new java.awt.Color(24, 24, 24));
        header.setPreferredSize(new java.awt.Dimension(1356, 48));

        timeLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        timeLabel.setText("2024 March 25   00:46 am");

        profileImage.setBorderSize(0);
        profileImage.setImage(new javax.swing.ImageIcon(getClass().getResource("/resources/profileImage/pic.jpg"))); // NOI18N
        profileImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileImageMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(timeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1107, Short.MAX_VALUE)
                .addComponent(profileImage, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(timeLabel))
                    .addGroup(headerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(profileImage, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
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
        addReporting();

        ReportPanel reportPanel = new gui.dialog.ReportPanel(this, true);
        reportPanel.setVisible(true);

    }//GEN-LAST:event_repotingButtonActionPerformed

    private void salesManageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesManageButtonActionPerformed
        removeActivePanel();
        addSalesManage();
    }//GEN-LAST:event_salesManageButtonActionPerformed

    private void inventoryManageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventoryManageButtonActionPerformed
        removeActivePanel();
        addInventoryManage();
    }//GEN-LAST:event_inventoryManageButtonActionPerformed

    private void customerManageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerManageButtonActionPerformed
        removeActivePanel();
        addCustomerManage();
    }//GEN-LAST:event_customerManageButtonActionPerformed

    private void profileImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileImageMouseClicked
        Profile profile = new gui.popUps.Profile(userid, username, userRole);
        DefaultOption option = new DefaultOption() {
            @Override
            public boolean closeWhenClickOutside() {
                return true;
            }
        };
        String actions[] = new String[]{};
        GlassPanePopup.showPopup(new SimplePopupBorder(profile, "Profile", actions, (pc, i) -> {
            if (i == 1) {
                //save
                return;
            } else {
                pc.closePopup();
            }
        }), option);

    }//GEN-LAST:event_profileImageMouseClicked

    private void grnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grnButtonActionPerformed
        removeActivePanel();
        addGrn();
    }//GEN-LAST:event_grnButtonActionPerformed

    private void grnHistoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grnHistoryButtonActionPerformed
        removeActivePanel();
        addGrnHistory();
    }//GEN-LAST:event_grnHistoryButtonActionPerformed

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
    private javax.swing.JButton customerManageButton;
    private javax.swing.JButton dashboardButton;
    private javax.swing.JButton grnButton;
    private javax.swing.JButton grnHistoryButton;
    private javax.swing.JPanel header;
    private javax.swing.JButton inventoryManageButton;
    private javax.swing.JLabel logo;
    private javax.swing.JButton productManageButton;
    private main.ImageAvatar profileImage;
    private javax.swing.JButton repotingButton;
    private javax.swing.JButton salesManageButton;
    private javax.swing.JPanel sideMenuPanel;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JButton userManageButton;
    // End of variables declaration//GEN-END:variables
}
