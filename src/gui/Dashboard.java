/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

/**
 *
 * @author thida
 */
public class Dashboard extends javax.swing.JPanel {

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
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

        summaryTitle = new javax.swing.JLabel();
        chartContainer = new javax.swing.JPanel();
        chart = new raven.chart.CurveLineChart();

        setMinimumSize(new java.awt.Dimension(1350, 852));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1350, 852));

        summaryTitle.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        summaryTitle.setText("Sales Summary");

        chartContainer.setLayout(new java.awt.BorderLayout());
        chartContainer.add(chart, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(summaryTitle)
                    .addComponent(chartContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 1294, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(summaryTitle)
                .addGap(18, 18, 18)
                .addComponent(chartContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(380, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.chart.CurveLineChart chart;
    private javax.swing.JPanel chartContainer;
    private javax.swing.JLabel summaryTitle;
    // End of variables declaration//GEN-END:variables
}
