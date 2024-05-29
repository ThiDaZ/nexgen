package gui;

import gui.popUps.customerAdd;
import gui.popUps.productSelect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.CustomerBean;
import model.MySQL;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import raven.popup.DefaultOption;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;

public class SaleManage extends javax.swing.JPanel {

    /**
     * @return the tableProductId
     */
    /**
     * @param tableProductId the tableProductId to set
     */
    public void setTableProductId(String tableProductId) {
        this.tableProductId = tableProductId;
    }

    /**
     * @param tableStockId the tableStockId to set
     */
    public void setTableStockId(String tableStockId) {
        this.tableStockId = tableStockId;
    }

    private CustomerBean customerBean = new CustomerBean();

    private double total;
    private double paidAmount;
    private double balance;

    // HashMap<String, String> pm_map = new HashMap<>();
    public void processInvoice() {

        long id = System.currentTimeMillis();
        long in_id = id;

        String invoiceId = String.valueOf(System.currentTimeMillis());
        System.out.println(invoiceId);

        Date dateTime = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            MySQL.execute("INSERT INTO `invoice` VALUES('" + invoiceId + "','" + format.format(dateTime) + "','" + paymentField.getText() + "','" + customerBean.getMobile() + "',1 ,"
                    + "'" + pm_map.get(paymentComboBox.getSelectedItem().toString()) + "')");

            int rowCount = salesTable.getRowCount();

            for (int i = 0; i < rowCount; i++) {
                String stockId = String.valueOf(salesTable.getValueAt(i, 0));
                int qty = Integer.parseInt(String.valueOf(salesTable.getValueAt(i, 6)));

                //Invoice Item Insert
                MySQL.execute("INSERT INTO `invoice_item`(`qty`,`stock_id`,`invoice_id`,warranty_id) VALUES ('" + qty + "','" + stockId + "','" + invoiceId + "',1)");

                //Stock Update
                MySQL.execute("UPDATE `stock` SET `qty` = `qty`-'" + qty + "' WHERE `id` = '" + stockId + "'");
//                MySQL.iud("UPDATE `stock` SET `qty` = `qty`-'" + qty + "' WHERE `id` = '" + stockId + "'");
            }

            double newPoints = 0;

            if (!customerBean.getMobile().equals("None")) {

                double paidAmount = Double.parseDouble(String.valueOf(paymentField.getText()));
                newPoints = paidAmount / 1000;

                //Customer Update
                MySQL.execute("UPDATE `customer` SET `points` = `points`+'" + newPoints + "' WHERE `mobile` = '" + customerBean.getMobile() + "'");
//                MySQL.iud("UPDATE `customer` SET `points` = `points`+'" + newPoints + "' WHERE `mobile` = '" + customerBean.getMobile() + "'");
            }
            String reportPath = "/report//NexGen.jasper";

            HashMap<String, Object> map = new HashMap<>();
            map.put("Parameter1", "Danushka");
            map.put("Parameter2", customerBean.getFname() + " " + customerBean.getLname());
            map.put("Parameter10", in_id);
            map.put("Parameter4", format.format(dateTime));
            map.put("Parameter5", totalPayField.getText());
            map.put("Parameter6", paymentComboBox.getSelectedItem().toString());
            map.put("Parameter7", paymentField.getText());

            if (reedemCheckbox.isSelected()) {
                map.put("Parameter8", jLabel5.getText());
            } else {
                map.put("Parameter8", "0");
            }

            map.put("Parameter9", balanceField.getText());

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nexgen", "root", "oni-chan99");
//            JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, map, connection);
            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/report/NexGen.jasper"), map);

//       JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, map, connection);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void calculate() {
//        totalQuantity = 0;
        total = 0;

        int rowCount = salesTable.getRowCount();

        for (int i = 0; i < rowCount; i++) {

            String tableQty = String.valueOf(salesTable.getValueAt(i, 4));
            String tablePrize = String.valueOf(salesTable.getValueAt(i, 5));

//            totalQuantity += Integer.parseInt(tableQty);
            total += (Double.parseDouble(tablePrize) * Integer.parseInt(tableQty));

        }

//        qty.setText(String.valueOf(totalQuantity)); total qty lable
        totalPayField.setText(String.valueOf(total));
        calculatePayment();
    }

    HashMap<String, String> pm_map = new HashMap<>();

    public void loadPaymentMethods() {

        try {

            ResultSet resultSet = MySQL.execute("SELECT * FROM `payment_method` ORDER BY `method` ASC");

            DefaultComboBoxModel model = (DefaultComboBoxModel) paymentComboBox.getModel();
            model.removeAllElements();

            Vector v = new Vector();
            v.add("Select");

            while (resultSet.next()) {
                v.add(resultSet.getString("method"));
                pm_map.put(resultSet.getString("method"), resultSet.getString("id"));
            }

            model.addAll(v);
            paymentComboBox.setSelectedIndex(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void calculatePayment() {

        paymentField.setEnabled(true);
        String paymentMethod = String.valueOf(paymentComboBox.getSelectedItem());

        if (reedemCheckbox.isSelected()) {
            totalPayField.setText(String.valueOf(total - customerBean.getPoint()));
        } else {
            totalPayField.setText(String.valueOf(total));
        }

        if (paymentMethod.equals("Select")) {

            paidAmount = 0;
            balance = 0;
            paymentField.setText(String.valueOf(paidAmount));

        } else if (paymentMethod.equals("Cash")) {

            paidAmount = Double.parseDouble(paymentField.getText());
            balance = paidAmount - Double.parseDouble(paymentField.getText());

        } else {

            paidAmount = total;
            balance = 0;
            paymentField.setText(totalPayField.getText());
            paymentField.setEnabled(false);
        }
        balanceField.setText(String.valueOf(balance));

    }

    private String tableProductId;
    private String tableStockId;

    public void addTable(String tableProductId, String tableStockId, int qty) {

        try {
            System.out.println(tableProductId + " " + tableStockId);

            System.out.println("tess1");
            ResultSet rs = MySQL.execute("SELECT * FROM `product` "
                    + "INNER JOIN `stock` ON `product`.`id` = `stock`.`product_id` "
                    + "INNER JOIN `brand` ON `brand`.`id` = `product`.`brand_id` "
                    + "INNER JOIN `category` ON `category`.`id` = `product`.`category_id` "
                    + "WHERE `product_id`= " + tableProductId + " AND `stock`.`id` = " + tableStockId + "");

            DefaultTableModel model = (DefaultTableModel) salesTable.getModel();
//            model.setRowCount(0);

            while (rs.next()) {
                System.out.println(tableProductId + " " + tableStockId);
                Vector v = new Vector();
                v.add(String.valueOf(rs.getInt("product_id")));
                v.add(rs.getString("name"));
                v.add(rs.getString("brand"));
                v.add(rs.getString("category"));
                v.add(qty);
                v.add(rs.getString("selling_price"));
                v.add(String.valueOf(rs.getInt("stock.id")));

                model.addRow(v);

            }
            calculate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getCustomer(String mobile, String name, String points) {
        System.out.println(mobile);
        System.out.println(name);
        System.out.println(points);

        customerLabel.setText("Customer : " + name);
        mobileField.setText(mobile);
        pointField.setText(points);

        int intPoints = Integer.parseInt(points);

        customerBean.setMobile(mobile);
        customerBean.setPoint(intPoints);
    }

    /**
     * Creates new form SaleManage
     */
    public SaleManage() {
        initComponents();
        loadPaymentMethods();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        salesTable = new javax.swing.JTable();
        printInvoiceButton = new javax.swing.JButton();
        balanceField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        paymentField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        totalPayField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        paymentComboBox = new javax.swing.JComboBox<>();
        addProductButton = new javax.swing.JButton();
        addCustomerButton = new javax.swing.JButton();
        customerLabel = new javax.swing.JLabel();
        reedemCheckbox = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        mobileField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        pointField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        dailyInvoiceLabel = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(1350, 852));
        setPreferredSize(new java.awt.Dimension(1350, 852));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        jLabel1.setText("Sale Manage");

        salesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Name", "Brand", "Category", "QTY", "Selling Price", "Stock ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        salesTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                salesTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(salesTable);

        printInvoiceButton.setText("Print Invoice");
        printInvoiceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printInvoiceButtonActionPerformed(evt);
            }
        });

        balanceField.setEditable(false);
        balanceField.setPreferredSize(new java.awt.Dimension(68, 27));

        jLabel2.setText("Balance");

        paymentField.setMinimumSize(new java.awt.Dimension(68, 27));
        paymentField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentFieldActionPerformed(evt);
            }
        });
        paymentField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paymentFieldKeyReleased(evt);
            }
        });

        jLabel3.setText("Payment");

        totalPayField.setEditable(false);
        totalPayField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalPayFieldActionPerformed(evt);
            }
        });

        jLabel4.setText("Total Payment");

        jLabel5.setText("Payment Method");

        paymentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        paymentComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentComboBoxActionPerformed(evt);
            }
        });

        addProductButton.setText("Add Product");
        addProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductButtonActionPerformed(evt);
            }
        });

        addCustomerButton.setText("Add Customer");
        addCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCustomerButtonActionPerformed(evt);
            }
        });

        customerLabel.setText("Not Selected Customer");

        reedemCheckbox.setText("Reedem Points");
        reedemCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reedemCheckboxActionPerformed(evt);
            }
        });

        jLabel6.setText("Mobile ");

        mobileField.setEditable(false);

        jLabel8.setText("Points");

        pointField.setEditable(false);

        jButton1.setText("Remove");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(addCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(paymentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(totalPayField, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(99, 99, 99)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(mobileField, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                    .addComponent(pointField))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(reedemCheckbox)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(printInvoiceButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                            .addComponent(paymentField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(balanceField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(addProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(customerLabel)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerLabel)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paymentField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(totalPayField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(paymentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(balanceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(printInvoiceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(reedemCheckbox)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(mobileField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pointField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addContainerGap())
        );

        dailyInvoiceLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        dailyInvoiceLabel.setText("Daily Invoice #00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dailyInvoiceLabel))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(dailyInvoiceLabel))
                .addGap(35, 35, 35)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void totalPayFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalPayFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalPayFieldActionPerformed

    private void paymentFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentFieldActionPerformed

    private void addCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCustomerButtonActionPerformed
        customerAdd customeradd = new gui.popUps.customerAdd();
        customeradd.setSalesmange(this);

        DefaultOption option = new DefaultOption() {
            @Override
            public boolean closeWhenClickOutside() {
                return true;
            }
        };
        String actions[] = new String[]{};
        GlassPanePopup.showPopup(new SimplePopupBorder(customeradd, "Customer Select", actions, (pc, i) -> {
            if (i == 1) {
                //save
                return;
            } else {
                pc.closePopup();
            }
        }), option);
        System.out.println(tableProductId + " " + tableStockId);
    }//GEN-LAST:event_addCustomerButtonActionPerformed

    private void reedemCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reedemCheckboxActionPerformed
        calculate();
        calculatePayment();
    }//GEN-LAST:event_reedemCheckboxActionPerformed

    private void printInvoiceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printInvoiceButtonActionPerformed
        if (customerBean.getMobile().equals("None")) {

            int option = JOptionPane.showConfirmDialog(this, "Do you want to register customer?", "Message", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                mobileField.requestFocus();
            } else {
//              customer = none
                processInvoice();

            }

        } else {

            //     customer != none
            processInvoice();

        }
    }//GEN-LAST:event_printInvoiceButtonActionPerformed

    private void addProductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductButtonActionPerformed
        productSelect productselect = new gui.popUps.productSelect();
        productselect.setSalesmange(this);
        DefaultOption option = new DefaultOption() {
            @Override
            public boolean closeWhenClickOutside() {
                return true;
            }
        };
        String actions[] = new String[]{"save", "close"};
        GlassPanePopup.showPopup(new SimplePopupBorder(productselect, "Product Select", actions, (pc, i) -> {
            if (i == 1) {
                //save
                return;
            } else {
                pc.closePopup();
            }
        }), option);
    }//GEN-LAST:event_addProductButtonActionPerformed

    private void salesTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_salesTableKeyReleased
        calculate();
    }//GEN-LAST:event_salesTableKeyReleased

    private void paymentComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentComboBoxActionPerformed
        calculate();

    }//GEN-LAST:event_paymentComboBoxActionPerformed

    private void paymentFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paymentFieldKeyReleased
        calculate();

    }//GEN-LAST:event_paymentFieldKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCustomerButton;
    private javax.swing.JButton addProductButton;
    private javax.swing.JTextField balanceField;
    private javax.swing.JLabel customerLabel;
    private javax.swing.JLabel dailyInvoiceLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField mobileField;
    private javax.swing.JComboBox<String> paymentComboBox;
    private javax.swing.JTextField paymentField;
    private javax.swing.JTextField pointField;
    private javax.swing.JButton printInvoiceButton;
    private javax.swing.JCheckBox reedemCheckbox;
    private javax.swing.JTable salesTable;
    private javax.swing.JTextField totalPayField;
    // End of variables declaration//GEN-END:variables
}
