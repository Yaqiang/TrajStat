/* Copyright 2014 - Yaqiang Wang,
 * yaqiang.wang@gmail.com
 * 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or (at
 * your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser
 * General Public License for more details.
 */
package org.meteothink.trajstat.forms;

import java.awt.Cursor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import org.meteoinfo.data.mapdata.Field;
import org.meteoinfo.geoprocess.GeoComputation;
import org.meteoinfo.common.MIMath;
import org.meteoinfo.common.PointD;
import org.meteoinfo.layer.VectorLayer;
import org.meteoinfo.legend.GroupNode;
import org.meteoinfo.legend.LayerNode;
import org.meteoinfo.plugin.IApplication;
import org.meteoinfo.shape.PolygonShape;
import org.meteoinfo.shape.PolylineZShape;
import org.meteoinfo.ui.CheckTreeManager;
import org.meteoinfo.ui.CheckTreeSelectionModel;

/**
 *
 * @author Yaqiang Wang
 */
public class FrmPSCF extends javax.swing.JDialog {

    private IApplication app;
    CheckTreeManager checkTreeManager;

    /**
     * Creates new form FrmPSCF
     * @param parent
     * @param modal
     */
    public FrmPSCF(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        app = (IApplication) parent;

        //Set trajectory layers
        DefaultMutableTreeNode layersTN = new DefaultMutableTreeNode("Trajectory", true);
        GroupNode gNode = app.getMapDocument().getActiveMapFrame().getGroupByName("Trajectory");
        for (LayerNode lNode : gNode.getLayers()) {
            layersTN.insert(new DefaultMutableTreeNode(lNode.getMapLayer()), 0);
        }
        DefaultTreeModel model = new DefaultTreeModel(layersTN);
        this.jTree_TrajLayers.setModel(model);
        checkTreeManager = new CheckTreeManager(this.jTree_TrajLayers);
        TreePath path = this.jTree_TrajLayers.getPathForRow(1);
        CheckTreeSelectionModel selModel = (CheckTreeSelectionModel) checkTreeManager.getSelectionModel();
        selModel.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                onSelectionValueChanged(e);
            }
        });
        selModel.addSelectionPath(path);
        this.getFileds();

        //Set PSCF layers
        GroupNode pNode = app.getMapDocument().getActiveMapFrame().getGroupByName("PSCF");
        this.jComboBox_PSCFLayer.removeAllItems();
        for (LayerNode lNode : pNode.getLayers()) {
            this.jComboBox_PSCFLayer.addItem(lNode.getMapLayer());
        }
    }

    private void getFileds() {
        // to get the paths that were checked
        TreePath checkedPaths[] = checkTreeManager.getSelectionModel().getSelectionPaths();
        if (checkedPaths == null) {
            return;
        }

        VectorLayer layer = null;
        for (TreePath path : checkedPaths) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
            if (node.getChildCount() > 0) {
                DefaultMutableTreeNode cnode = (DefaultMutableTreeNode) node.getChildAt(0);
                layer = (VectorLayer) cnode.getUserObject();
            } else {
                layer = (VectorLayer) node.getUserObject();
                break;
            }
        }

        if (layer != null) {
            this.jComboBox_Field.removeAllItems();
            for (Field field : layer.getFields()) {
                if (field.isNumeric()) {
                    this.jComboBox_Field.addItem(field.getColumnName());
                }
            }
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

        jLabel1 = new javax.swing.JLabel();
        jComboBox_PSCFLayer = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jComboBox_Field = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jTextField_Criterion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField_Missing = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton_GetNij = new javax.swing.JButton();
        jButton_GetMij = new javax.swing.JButton();
        jButton_CalPSCF = new javax.swing.JButton();
        jButton_WeightPSCF = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTree_TrajLayers = new javax.swing.JTree();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField_EndPoint = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField_ReduceRatio = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField_TrajNum = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField_ReduceRatio_Traj = new javax.swing.JTextField();
        jButton_Weight = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("PSCF Layer:");

        jComboBox_PSCFLayer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Field:");

        jComboBox_Field.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Criterion:");

        jTextField_Criterion.setText("0");

        jLabel4.setText("Missing:");

        jTextField_Missing.setText("0");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("PSCF Calculation"));

        jButton_GetNij.setText("Get Nij");
        jButton_GetNij.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_GetNijActionPerformed(evt);
            }
        });

        jButton_GetMij.setText("Get Mij");
        jButton_GetMij.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_GetMijActionPerformed(evt);
            }
        });

        jButton_CalPSCF.setText("Cal PSCF");
        jButton_CalPSCF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CalPSCFActionPerformed(evt);
            }
        });

        jButton_WeightPSCF.setText("Weight PSCF");
        jButton_WeightPSCF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_WeightPSCFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_GetNij)
                    .addComponent(jButton_CalPSCF))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_WeightPSCF)
                    .addComponent(jButton_GetMij))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_GetNij)
                    .addComponent(jButton_GetMij))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_CalPSCF)
                    .addComponent(jButton_WeightPSCF))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("colors");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("blue");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("violet");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("red");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("yellow");
        treeNode1.add(treeNode2);
        jTree_TrajLayers.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane2.setViewportView(jTree_TrajLayers);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Weight by endpoint number"));

        jLabel5.setText("End Point:");

        jTextField_EndPoint.setText("80;20;10");

        jLabel6.setText("Reduce Ratio:");

        jTextField_ReduceRatio.setText("0.7;0.42;0.05");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_EndPoint)
                    .addComponent(jTextField_ReduceRatio))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField_EndPoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField_ReduceRatio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Weight by trajectory number"));

        jLabel7.setText("Trajectory:");

        jTextField_TrajNum.setText("3;1");

        jLabel8.setText("Reduce Ratio:");

        jTextField_ReduceRatio_Traj.setText("0.5;0.2");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_TrajNum)
                    .addComponent(jTextField_ReduceRatio_Traj, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField_TrajNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField_ReduceRatio_Traj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton_Weight.setText("Weight");
        jButton_Weight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_WeightActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox_PSCFLayer, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox_Field, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_Criterion, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_Missing))
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton_Weight, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(86, 86, 86))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox_PSCFLayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField_Criterion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField_Missing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Weight)))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_GetNijActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GetNijActionPerformed
        // TODO add your handling code here:
        double missingValue = Double.parseDouble(this.jTextField_Missing.getText());
        this.getEndPointsInCell("Nij", 0, missingValue);
    }//GEN-LAST:event_jButton_GetNijActionPerformed

    private void jButton_GetMijActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GetMijActionPerformed
        // TODO add your handling code here:
        double criterion = Double.parseDouble(this.jTextField_Criterion.getText());
        double missingValue = Double.parseDouble(this.jTextField_Missing.getText());
        this.getEndPointsInCell("Mij", criterion, missingValue);
    }//GEN-LAST:event_jButton_GetMijActionPerformed

    private void jButton_CalPSCFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CalPSCFActionPerformed
        // TODO add your handling code here:
        VectorLayer aLayer = (VectorLayer) this.jComboBox_PSCFLayer.getSelectedItem();

        int i;
        int Nij;
        int Mij;
        double pscf;

        //---- Show progressbar
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        for (i = 0; i < aLayer.getShapeNum(); i++) {
            Nij = Integer.parseInt(aLayer.getCellValue("Nij", i).toString());
            if (Nij > 0) {
                Mij = Integer.parseInt(aLayer.getCellValue("Mij", i).toString());
                pscf = (double) Mij / Nij;
            } else {
                pscf = 0;
            }
            aLayer.editCellValue("PSCF", i, pscf);
        }
        aLayer.getAttributeTable().save();

        //---- Hide progressbar
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jButton_CalPSCFActionPerformed

    private void jButton_WeightPSCFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_WeightPSCFActionPerformed
        // TODO add your handling code here:
        VectorLayer aLayer = (VectorLayer) this.jComboBox_PSCFLayer.getSelectedItem();

        String[] endPoints = this.jTextField_EndPoint.getText().split(";");
        String[] reduceRatios = this.jTextField_ReduceRatio.getText().split(";");
        int epNum = endPoints.length;

        //---- Show progressbar
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        //---- Weighting PSCF
        int i;
        int Nij;
        int j;
        double pscf;
        double wpscf;
        for (i = 0; i < aLayer.getShapeNum(); i++) {
            Nij = Integer.parseInt(aLayer.getCellValue("Nij", i).toString());
            pscf = Double.parseDouble(aLayer.getCellValue("PSCF", i).toString());
            wpscf = pscf;
            for (j = 0; j <= epNum - 1; j++) {
                if (j == epNum - 1) {
                    if (Nij <= Integer.parseInt(endPoints[j])) {
                        wpscf = pscf * Double.parseDouble(reduceRatios[j]);
                    }
                } else {
                    if (Nij <= Integer.parseInt(endPoints[j]) && Nij > Integer.parseInt(endPoints[j + 1])) {
                        wpscf = pscf * Double.parseDouble(reduceRatios[j]);
                    }
                }
            }
            aLayer.editCellValue("WPSCF", i, wpscf);
        }
        aLayer.getAttributeTable().save();
        aLayer.updateLegendIndexes();
        this.app.getMapView().paintLayers();

        //---- Hide progressbar
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jButton_WeightPSCFActionPerformed

    private void jButton_WeightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_WeightActionPerformed
        // TODO add your handling code here:
        VectorLayer aLayer = (VectorLayer) this.jComboBox_PSCFLayer.getSelectedItem();

        String[] endPoints = this.jTextField_TrajNum.getText().split(";");
        String[] reduceRatios = this.jTextField_ReduceRatio_Traj.getText().split(";");
        int epNum = endPoints.length;

        //---- Show progressbar
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        //---- Weighting PSCF
        int i;
        int tNij;
        int j;
        double pscf;
        double wpscf;
        for (i = 0; i < aLayer.getShapeNum(); i++) {
            tNij = Integer.parseInt(aLayer.getCellValue("N_Traj", i).toString());
            pscf = Double.parseDouble(aLayer.getCellValue("WPSCF", i).toString());
            wpscf = pscf;
            for (j = 0; j <= epNum - 1; j++) {
                if (j == epNum - 1) {
                    if (tNij <= Integer.parseInt(endPoints[j])) {
                        wpscf = pscf * Double.parseDouble(reduceRatios[j]);
                    }
                } else {
                    if (tNij <= Integer.parseInt(endPoints[j]) && tNij > Integer.parseInt(endPoints[j + 1])) {
                        wpscf = pscf * Double.parseDouble(reduceRatios[j]);
                    }
                }
            }
            aLayer.editCellValue("WPSCF", i, wpscf);
        }
        aLayer.getAttributeTable().save();
        aLayer.updateLegendIndexes();
        this.app.getMapView().paintLayers();

        //---- Hide progressbar
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jButton_WeightActionPerformed

    private void getEndPointsInCell(final String aType, final double aCriterion, final double aNData) {
        SwingWorker worker = new SwingWorker<String, String>() {
            @Override
            protected String doInBackground() throws Exception {
                //---- Get PSCF Layer
                VectorLayer PSCFLayer = (VectorLayer) FrmPSCF.this.jComboBox_PSCFLayer.getSelectedItem();

                //---- Get selected layers
                //Get selected layers
                TreePath checkedPaths[] = checkTreeManager.getSelectionModel().getSelectionPaths();
                if (checkedPaths == null) {
                    JOptionPane.showMessageDialog(null, "There is no trajectory layer was selected!");
                    return "";
                }

                List<VectorLayer> layers = new ArrayList<>();
                for (TreePath path : checkedPaths) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                    if (node.getChildCount() > 0) {
                        for (int i = 0; i < node.getChildCount(); i++) {
                            DefaultMutableTreeNode cnode = (DefaultMutableTreeNode) node.getChildAt(i);
                            layers.add((VectorLayer) cnode.getUserObject());
                        }
                    } else {
                        layers.add((VectorLayer) node.getUserObject());
                        break;
                    }
                }

                //---- Show progressbar
                FrmPSCF.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                app.getProgressBar().setVisible(true);
                app.getProgressBar().setValue(0);
                app.getProgressBarLabel().setVisible(true);
                app.getProgressBarLabel().setText("...");

                //---- Get Nij     
                boolean usingCriterion = false;
                if (aType.equals("Mij")) {
                    usingCriterion = true;
                }
                int[] Nijs = new int[PSCFLayer.getShapeNum()];
                int[] TNijs = new int[PSCFLayer.getShapeNum()];
                int i;
                for (i = 0; i < Nijs.length; i++) {
                    Nijs[i] = 0;
                    TNijs[i] = 0;
                }
                int dataFldIdx;
                List<Integer> cellIdx = new ArrayList<>();
                String fieldName = FrmPSCF.this.jComboBox_Field.getSelectedItem().toString();
                for (VectorLayer tLayer : layers) {
                    app.getProgressBarLabel().setText(tLayer.getLayerName());
                    dataFldIdx = tLayer.getFieldIdxByName(fieldName);
                    double value;
                    for (i = 0; i < tLayer.getShapeNum(); i++) {                        
                        value = Double.parseDouble(tLayer.getCellValue(dataFldIdx, i).toString());
                        if (MIMath.doubleEquals(value, aNData)) {
                            continue;
                        }

                        if (usingCriterion) {
                            if (value < aCriterion) {
                                continue;
                            }
                        }

                        cellIdx.clear();
                        PolylineZShape aPLZ = (PolylineZShape) tLayer.getShapes().get(i);
                        for (int p = 0; p < aPLZ.getPointNum(); p++) {
                            PointD aPoint = new PointD(aPLZ.getPoints().get(p).X, aPLZ.getPoints().get(p).Y);                            
                            for (int s = 0; s < PSCFLayer.getShapeNum(); s++) {                                
                                PolygonShape aPGS = (PolygonShape) PSCFLayer.getShapes().get(s);
                                if (GeoComputation.pointInPolygon(aPGS.getPoints(), aPoint)) {
                                    Nijs[s] += 1;                                    
                                    if (!cellIdx.contains(s)){
                                        TNijs[s] += 1;
                                        cellIdx.add(s);
                                    }
                                    break;
                                }
                            }
                        }
                        app.getProgressBar().setValue((int) ((float) (i + 1) / tLayer.getShapeNum() * 100));
                    }
                }

                //---- Update PSCF layer                        
                for (i = 0; i < PSCFLayer.getShapeNum(); i++) {
                    PSCFLayer.editCellValue(aType, i, Nijs[i]);
                    if (!usingCriterion)
                        PSCFLayer.editCellValue("N_Traj", i, TNijs[i]);
                }
                PSCFLayer.getAttributeTable().save();

                return "";
            }

            @Override
            protected void done() {
                app.getProgressBar().setVisible(false);
                app.getProgressBarLabel().setVisible(false);
                FrmPSCF.this.setCursor(Cursor.getDefaultCursor());
            }
        };

        worker.execute();
    }

    private void onSelectionValueChanged(TreeSelectionEvent evt) {
        this.getFileds();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPSCF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPSCF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPSCF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPSCF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmPSCF dialog = new FrmPSCF(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_CalPSCF;
    private javax.swing.JButton jButton_GetMij;
    private javax.swing.JButton jButton_GetNij;
    private javax.swing.JButton jButton_Weight;
    private javax.swing.JButton jButton_WeightPSCF;
    private javax.swing.JComboBox jComboBox_Field;
    private javax.swing.JComboBox jComboBox_PSCFLayer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField_Criterion;
    private javax.swing.JTextField jTextField_EndPoint;
    private javax.swing.JTextField jTextField_Missing;
    private javax.swing.JTextField jTextField_ReduceRatio;
    private javax.swing.JTextField jTextField_ReduceRatio_Traj;
    private javax.swing.JTextField jTextField_TrajNum;
    private javax.swing.JTree jTree_TrajLayers;
    // End of variables declaration//GEN-END:variables
}
