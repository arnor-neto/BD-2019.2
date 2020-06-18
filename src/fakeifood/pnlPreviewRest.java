/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fakeifood;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arnor
 */
public class pnlPreviewRest extends javax.swing.JPanel {

    
    int id_restaurante;
    /**
     * Creates new form pnlPreviewRest
     */
    public pnlPreviewRest(String nome, String categoria, int tipoEntrega, int id_restaurante) {
        initComponents();
        this.id_restaurante = id_restaurante;
        txtNomeRest.setText(nome);
        txtCategoria.setText(categoria);
        if(tipoEntrega == 0){
            txtTipoEntrega.setText("Entrega grátis");
        }else
            txtTipoEntrega.setText("Entrega rápida");
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtNomeRest = new javax.swing.JLabel();
        txtCategoria = new javax.swing.JLabel();
        txtTipoEntrega = new javax.swing.JLabel();
        btnAcesse = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(327, 97));
        setMinimumSize(new java.awt.Dimension(327, 97));
        setPreferredSize(new java.awt.Dimension(327, 97));

        jPanel1.setBackground(new java.awt.Color(255, 204, 153));
        jPanel1.setMaximumSize(new java.awt.Dimension(327, 100));

        txtNomeRest.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtNomeRest.setText("Nome");
        txtNomeRest.setAlignmentY(0.0F);

        txtCategoria.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtCategoria.setText("jLabel2");
        txtCategoria.setAlignmentY(0.0F);

        txtTipoEntrega.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtTipoEntrega.setText("jLabel3");
        txtTipoEntrega.setAlignmentY(0.0F);

        btnAcesse.setBackground(new java.awt.Color(255, 255, 255));
        btnAcesse.setText("Acesse");
        btnAcesse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcesseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNomeRest)
                    .addComponent(txtCategoria)
                    .addComponent(txtTipoEntrega))
                .addGap(189, 189, 189)
                .addComponent(btnAcesse)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNomeRest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCategoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTipoEntrega)
                    .addComponent(btnAcesse))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAcesseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcesseActionPerformed
        try {
            // TODO add your handling code here:
            FrameMenuRestaurante restauranteSelecionado = new FrameMenuRestaurante(id_restaurante);
            restauranteSelecionado.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(pnlPreviewRest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAcesseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcesse;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel txtCategoria;
    private javax.swing.JLabel txtNomeRest;
    private javax.swing.JLabel txtTipoEntrega;
    // End of variables declaration//GEN-END:variables
}