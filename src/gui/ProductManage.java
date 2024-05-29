package gui;

import com.formdev.flatlaf.FlatClientProperties;
import static gui.SignIn.logger;
import gui.popUps.brandManage;
import gui.popUps.categoryManage;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.MySQL;
import raven.popup.DefaultOption;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;

public class ProductManage extends javax.swing.JPanel {

    /**
     * Creates new form product
     */
    public ProductManage() {
        initComponents();
        LoadProducts();
        loadBrand();
        LoadCategory();
        init();
    }
    HashMap<String, String> brandMap = new HashMap<>();
    HashMap<String, String> categoryMap = new HashMap<>();

    private void init() {
        jPanel2.putClientProperty(FlatClientProperties.STYLE, "arc:25;");
    }

    private void loadBrand() {
        try {
            ResultSet rs = MySQL.execute("SELECT * FROM `brand`");
            Vector vector = new Vector();
            vector.add("Select Brand");
            while (rs.next()) {

                String name = rs.getString("brand");
                String id = String.valueOf(rs.getString("id"));
                brandMap.put(name, id);
                vector.add(name);

            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            DefaultComboBoxModel model1 = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);
            jComboBox4.setModel(model1);

        } catch (Exception e) {
            logger.log(Level.WARNING, "Somthing went wrong!", e);
        }
    }

    private void LoadCategory() {
        try {
            ResultSet rs = MySQL.execute("SELECT * FROM `category`");

            Vector vector = new Vector();
            vector.add("Select Category");
            while (rs.next()) {

                String name = rs.getString("category");
                String id = String.valueOf(rs.getString("id"));

                categoryMap.put(name, id);

                vector.add(name);

            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            DefaultComboBoxModel model1 = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model);
            catagoryComboBox.setModel(model1);

        } catch (Exception e) {
            logger.log(Level.WARNING, "Somthing went wrong!", e);
        }
    }

    private void LoadProducts() {

        String query = "SELECT * FROM `product` INNER JOIN `brand` ON `brand`.`id` =`product`.`brand_id` INNER JOIN `category` ON `category`.`id` =`product`.`category_id`";

        if (!idField.getText().isEmpty()) {
            if (query.contains("WHERE")) {
                query += " AND `product`.`id` ='" + idField.getText() + "'";
            } else {
                query += " WHERE `product`. `id`='" + idField.getText() + "'";
            }
        }

        if (!SearchProduct.getText().isEmpty()) {
            if (query.contains("WHERE")) {
                query += " AND `product`.`name` LIKE('" + SearchProduct.getText() + "%') OR product.id LIKE('" + SearchProduct.getText() + "%') ";
            } else {
                query += " WHERE `product`. `name` LIKE('" + SearchProduct.getText() + "%') OR product.id LIKE('" + SearchProduct.getText() + "%')";
            }
        }

        if (jComboBox4.getSelectedIndex() != 0) {
            if (query.contains("WHERE")) {
                query += " AND `product`.`brand_id` ='" + brandMap.get(String.valueOf(jComboBox4.getSelectedItem())) + "'";
            } else {
                query += " WHERE `product`.`brand_id` ='" + brandMap.get(String.valueOf(jComboBox4.getSelectedItem())) + "'";
            }
        }

        if (catagoryComboBox.getSelectedIndex() != 0) {
            if (query.contains("WHERE")) {
                query += " AND `product`.`category_id` ='" + categoryMap.get(String.valueOf(catagoryComboBox.getSelectedItem())) + "'";
            } else {
                query += " WHERE `product`.`category_id` ='" + categoryMap.get(String.valueOf(catagoryComboBox.getSelectedItem())) + "'";
            }
        }

        String sort = String.valueOf(jComboBox3.getSelectedItem());
        query += " ORDER BY ";
        if (sort.equals("Product ID ASC")) {
            query += "`product`.`id` ASC";
        } else if (sort.equals("Product ID DESC")) {
            query += "`product`.`id` DESC";
        } else if (sort.equals("Brand ASC")) {
            query += "`brand`.`brand` ASC";
        } else if (sort.equals("Brand DESC")) {
            query += "`brand`.`brand` DESC";
        }

        try {

            ResultSet rs = MySQL.execute(query);

            DefaultTableModel model = (DefaultTableModel) productTable.getModel();
            model.setRowCount(0);
            while (rs.next()) {

                String id = String.valueOf(rs.getInt("id"));
                String name = rs.getString("name");
                String Brand = rs.getString("brand");
                String Category = rs.getString("category");
                String warranty = String.valueOf(rs.getInt("warranty_period"));
                Vector vector = new Vector();
                vector.add(id);
                vector.add(name);
                vector.add(Brand);
                vector.add(Category);
                vector.add(warranty);

                model.addRow(vector);
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Somthing went wrong!", e);

        }
    }

    //validation pass varible
    boolean x = false;

    private void validation() {
        String id = idField.getText();
        String name = nameField.getText();
        String brand = brandMap.get(jComboBox1.getSelectedItem());
        String category = categoryMap.get(jComboBox2.getSelectedItem());

        x = false;
        if (id.isEmpty()) {
            JOptionPane.showConfirmDialog(this, "Please Enter Product Id", "Alert", JOptionPane.WARNING_MESSAGE);
        } else if (name.isEmpty()) {
            JOptionPane.showConfirmDialog(this, "Please Enter Product Name", "Alert", JOptionPane.OK_CANCEL_OPTION);
        } else if (brand == null) {
            JOptionPane.showConfirmDialog(this, "Please Select Brand", "Alert", JOptionPane.OK_CANCEL_OPTION);
        } else if (category == null) {
            JOptionPane.showConfirmDialog(this, "Please Select Category", "Alert", JOptionPane.OK_CANCEL_OPTION);
        } else {
            x = true;

        }

    }

    private void reset() {
        idField.setText(null);
        nameField.setText(null);
        jTextField3.setText(null);
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        idField.setEditable(true);
        LoadProducts();
        loadBrand();
        LoadCategory();
        productTable.clearSelection();
        jComboBox3.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        brandManageButton = new javax.swing.JButton();
        categoryManageButton = new javax.swing.JButton();
        productListWrapper = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();
        productListHeader = new javax.swing.JPanel();
        jComboBox3 = new javax.swing.JComboBox<>();
        SearchProduct = new javax.swing.JTextField();
        jComboBox4 = new javax.swing.JComboBox<>();
        catagoryComboBox = new javax.swing.JComboBox<>();
        addProductButton = new javax.swing.JButton();
        updateProductButton = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        idField = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        jButton4.setText("Search");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        setMinimumSize(new java.awt.Dimension(1350, 852));
        setPreferredSize(new java.awt.Dimension(1350, 852));

        brandManageButton.setText("Brand Manage");
        brandManageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brandManageButtonActionPerformed(evt);
            }
        });

        categoryManageButton.setText("Category Manage");
        categoryManageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryManageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(brandManageButton)
                .addGap(18, 18, 18)
                .addComponent(categoryManageButton)
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(brandManageButton)
                    .addComponent(categoryManageButton))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        productListWrapper.setLayout(new java.awt.BorderLayout());

        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Manufacturer", "Category ", "Warranty"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(productTable);

        productListWrapper.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Product ID ASC", "Product ID DESC", "Brand ASC", "Brand DESC" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });

        SearchProduct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchProductKeyReleased(evt);
            }
        });

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });

        catagoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        catagoryComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                catagoryComboBoxItemStateChanged(evt);
            }
        });

        addProductButton.setText("Add");
        addProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductButtonActionPerformed(evt);
            }
        });

        updateProductButton.setText("Update");
        updateProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateProductButtonActionPerformed(evt);
            }
        });

        jButton3.setText("Reset");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout productListHeaderLayout = new javax.swing.GroupLayout(productListHeader);
        productListHeader.setLayout(productListHeaderLayout);
        productListHeaderLayout.setHorizontalGroup(
            productListHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productListHeaderLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(SearchProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(catagoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 305, Short.MAX_VALUE)
                .addComponent(addProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        productListHeaderLayout.setVerticalGroup(
            productListHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productListHeaderLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(productListHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SearchProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(catagoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addProductButton)
                    .addComponent(updateProductButton)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        SearchProduct.putClientProperty("JTextField.placeholderText", "Search");

        productListWrapper.add(productListHeader, java.awt.BorderLayout.PAGE_START);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        jLabel10.setText("Product Management");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setText("Product Details");

        jLabel1.setText("ID :");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Brand :");

        jLabel2.setText("Name :");

        jLabel4.setText("Category :");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Warranty  :");

        jLabel7.setText("Months");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(31, 31, 31)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)))
                        .addGap(179, 179, 179)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(productListWrapper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(28, 28, 28))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(productListWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void productTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productTableMouseClicked
        // TODO add your handling code here:
        idField.setEditable(false);
        int row = productTable.getSelectedRow();

        idField.setText(String.valueOf(productTable.getValueAt(row, 0)));
        nameField.setText(String.valueOf(productTable.getValueAt(row, 1)));
        jTextField3.setText(String.valueOf(productTable.getValueAt(row, 4)));
        jComboBox1.setSelectedItem(String.valueOf(productTable.getValueAt(row, 2)));
        jComboBox2.setSelectedItem(String.valueOf(productTable.getValueAt(row, 3)));
        System.out.println(row);

    }//GEN-LAST:event_productTableMouseClicked

    private void addProductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductButtonActionPerformed

        validation();

        try {

            System.out.println(x);
            if (x == true) {

                ResultSet rs = MySQL.execute("SELECT * FROM `product` WHERE `id` = '" + idField.getText() + "' OR (`name` ='" + nameField.getText() + "' AND `brand_id`='" + brandMap.get(String.valueOf(jComboBox1.getSelectedItem())) + "')");

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Product Already Added", "Alertl", JOptionPane.PLAIN_MESSAGE);
                } else {
                    MySQL.execute("INSERT INTO `product`(`id`,`name`,`warranty_period`,`brand_id`,`category_id`) VALUES ('" + idField.getText() + "','" + nameField.getText() + "','" + jTextField3.getText() + "','" + brandMap.get(String.valueOf(jComboBox1.getSelectedItem())) + "','" + categoryMap.get(String.valueOf(jComboBox2.getSelectedItem())) + "')");
                    JOptionPane.showMessageDialog(this, "Product Added", "Succesfull", JOptionPane.PLAIN_MESSAGE);
                    reset();
                }
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Somthing went wrong!", e);
        }
        LoadProducts();


    }//GEN-LAST:event_addProductButtonActionPerformed

    private void updateProductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateProductButtonActionPerformed

        if (productTable.getSelectedRow() == -1) {

            JOptionPane.showMessageDialog(this, "Please Select Row To Update", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                validation();
                System.out.println(x);
                if (x == true) {
                    MySQL.execute("UPDATE `product` SET  `name` ='" + nameField.getText() + "',`warranty_period`='" + jTextField3.getText() + "',`brand_id`='" + brandMap.get(String.valueOf(jComboBox1.getSelectedItem())) + "',`category_id`='" + categoryMap.get(String.valueOf(jComboBox2.getSelectedItem())) + "' WHERE `id`='" + idField.getText() + "'");
                    JOptionPane.showMessageDialog(this, "Product Updated", "Succesfull", JOptionPane.PLAIN_MESSAGE);
                    reset();
                }
            } catch (Exception e) {
                logger.log(Level.WARNING, "Somthing went wrong!", e);
            }
        }

    }//GEN-LAST:event_updateProductButtonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        reset();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        LoadProducts();
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void SearchProductKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchProductKeyReleased
        LoadProducts();
    }//GEN-LAST:event_SearchProductKeyReleased

    private void catagoryComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_catagoryComboBoxItemStateChanged
        LoadProducts();
    }//GEN-LAST:event_catagoryComboBoxItemStateChanged

    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
        LoadProducts();
    }//GEN-LAST:event_jComboBox4ItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        LoadProducts();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void brandManageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brandManageButtonActionPerformed
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
    }//GEN-LAST:event_brandManageButtonActionPerformed

    private void categoryManageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryManageButtonActionPerformed
        categoryManage categoryManage = new gui.popUps.categoryManage();
        DefaultOption option = new DefaultOption() {
            @Override
            public boolean closeWhenClickOutside() {
                return true;
            }
        };
        String actions[] = new String[]{"Cancel", "Save"};
        GlassPanePopup.showPopup(new SimplePopupBorder(categoryManage, "Category Manage", actions, (pc, i) -> {
            if (i == 1) {
                //save
                return;
            } else {
                pc.closePopup();
            }
        }), option);
    }//GEN-LAST:event_categoryManageButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField SearchProduct;
    private javax.swing.JButton addProductButton;
    private javax.swing.JButton brandManageButton;
    private javax.swing.JComboBox<String> catagoryComboBox;
    private javax.swing.JButton categoryManageButton;
    private javax.swing.JTextField idField;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField nameField;
    private javax.swing.JPanel productListHeader;
    private javax.swing.JPanel productListWrapper;
    private javax.swing.JTable productTable;
    private javax.swing.JButton updateProductButton;
    // End of variables declaration//GEN-END:variables
}
