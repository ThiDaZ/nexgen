package gui;

import static gui.SignIn.logger;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import model.MySQL;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.table.DefaultTableModel;
import raven.chart.ModelChart;
import model.ChartModel;

public class Dashboard extends javax.swing.JPanel {

    public Dashboard() {
        initComponents();

        chart.setTitle("");
        chart.addLegend("summary", Color.decode("#26a0da"), Color.decode("#314755"));
        chart.setForeground(Color.white);

        setData();
        lowStock();
        topCustomer();
    }

    private void topCustomer() {
        try {
            ResultSet rs = MySQL.execute("SELECT `customer`.`fname`, `customer`.`lname`, SUM(invoice.paid_amount) AS total_paid_amount "
                    + "FROM `customer` "
                    + "INNER JOIN `invoice` ON `invoice`.`customer_mobile` = `customer`.`mobile` "
                    + "GROUP BY `customer`.`mobile`, `customer`.`fname`, `customer`.`lname` "
                    + "ORDER BY total_paid_amount DESC "
                    + "LIMIT 5");

            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);

            int x = 0;

            while (rs.next()) {
                Vector v = new Vector();
                x = x + 1;
                v.add(String.valueOf(x));
                v.add(rs.getString("fname"));

                model.addRow(v);
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Somthing went worng!", e);
        }
    }

    private void lowStock() {
        try {
            ResultSet rs = MySQL.execute("SELECT product.id AS product_id, product.name AS product_name, brand.brand AS brand, MIN(stock.qty) AS min_qty FROM stock "
                    + "INNER JOIN product ON product.id = stock.product_id "
                    + "INNER JOIN brand ON brand.id = product.brand_id "
                    + "GROUP BY product.id, product.name, brand.brand "
                    + "ORDER BY min_qty ASC");
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(String.valueOf(rs.getInt("product_id")));
                v.add(rs.getString("product_name"));
                v.add(rs.getString("brand"));
                v.add(String.valueOf(rs.getInt("min_qty")));

                model.addRow(v);
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Somthing went worng!", e);

        }
    }

    private void setData() {
        try {
            List<ChartModel> list = new ArrayList<>();

            ResultSet rs = MySQL.execute("SELECT DATE_FORMAT(MAX(date), '%M') AS 'Month', SUM(paid_amount) AS Sales FROM `invoice` GROUP BY DATE_FORMAT(date, '%m%Y') ORDER BY DATE_FORMAT(MAX(date), '%m%Y');");
            while (rs.next()) {
                String month = rs.getString("Month");
                double sales = rs.getDouble("Sales");
                list.add(new ChartModel(month, sales));
            }

            for (int i = list.size() - 1; i >= 0; i--) {
                ChartModel model = list.get(i);
                chart.addData(new ModelChart(model.getMonth(), new double[]{model.getSales()}));
            }
            chart.start();
        } catch (Exception e) {
            logger.log(Level.WARNING, "somthing went wrong", e);
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

        summaryTitle = new javax.swing.JLabel();
        chartContainer = new javax.swing.JPanel();
        chart = new raven.chart.CurveLineChart();
        girdPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(1350, 852));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1350, 852));

        summaryTitle.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        summaryTitle.setText("Sales Summary");

        chartContainer.setLayout(new java.awt.BorderLayout());
        chartContainer.add(chart, java.awt.BorderLayout.CENTER);

        girdPanel.setLayout(new java.awt.GridLayout(1, 0));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        jLabel2.setText("Top Customers");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "#", "Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
        );

        girdPanel.add(jPanel2);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        jLabel1.setText("Low Stocks");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Product ID", "Name", "Brand", "QTY"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                .addContainerGap())
        );

        girdPanel.add(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(girdPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(summaryTitle)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(chartContainer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(summaryTitle)
                .addGap(18, 18, 18)
                .addComponent(chartContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(girdPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.chart.CurveLineChart chart;
    private javax.swing.JPanel chartContainer;
    private javax.swing.JPanel girdPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel summaryTitle;
    // End of variables declaration//GEN-END:variables

}
