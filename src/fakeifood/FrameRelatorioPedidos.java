/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fakeifood;

import db.BuscaDados;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author danda
 */
public class FrameRelatorioPedidos extends javax.swing.JFrame {

    /**
     * Creates new form FrameRelatorioPedidos
     */
    private float destinadoFrete;
    private float recebidoFrete;
    private float valorTotal;
    private float valorCaixa;

    public FrameRelatorioPedidos(boolean dia, boolean semana, boolean mes) {
        initComponents();
        this.destinadoFrete = 0;
        this.recebidoFrete = 0;
        this.valorTotal = 0;
        this.valorCaixa = 0;
        if (dia) {
            listaDia();
        }
        if (semana) {
            listaSemana();
        }
        if (mes) {
            listaMes();
        }
    }

    public void listaDia() {
        try {
            BuscaDados BD = new BuscaDados();
            ResultSet rs = BD.selectRelatorioPedidosDia(FramePrincipalRestaurante.id_restaurante);
            DefaultListModel model = new DefaultListModel();
            
            while (rs.next()) {
                String dataInteira = rs.getString("data_pedido") + " ";
                String dataAno = dataInteira.substring(0, 4);
                String dataMes = dataInteira.substring(4, 6);
                String dataDia = dataInteira.substring(6, 8);
                dataInteira = dataDia + "/" + dataMes + "/" + dataAno;
                String tempoInteiro = rs.getString("hora_pedido") + " ";
                String tempoHora;
                String tempoMinuto;
                if(tempoInteiro.length() == 5){
                    tempoHora = tempoInteiro.substring(0, 2);
                    tempoMinuto = tempoInteiro.substring(2, 5);
                    tempoInteiro = tempoHora + "h" + tempoMinuto;
                }else{
                    tempoHora = tempoInteiro.substring(0, 1);
                    tempoMinuto = tempoInteiro.substring(1, 3);
                    tempoInteiro = tempoHora + "h" + tempoMinuto;
                } 
                model.addElement(dataInteira + " " + tempoInteiro + " Valor total " + rs.getString("valor_total") + " - ID: " + rs.getInt("id_pedido"));
                valorTotal  += Float.parseFloat(rs.getString("valor_total"));
                float valorFrete = Float.parseFloat(rs.getString("valor_frete"));
                if(valorFrete == 0){
                    destinadoFrete += 2;
                }else{
                    recebidoFrete += 2;
                }
            }
            lblValorPeriodo.setText("Valor em caixa do dia R$");
            valorCaixa = valorTotal - destinadoFrete - recebidoFrete;
            rs.close();
            DecimalFormat DF = new DecimalFormat("##.##");
            
            lblTotal.setText(DF.format(Double.parseDouble(valorTotal + "")));
            lblDestinado.setText(DF.format(Double.parseDouble(destinadoFrete + "")) + ",00");
            lblRecebido.setText(DF.format(Double.parseDouble(recebidoFrete + "")) + ",00");
            lblCaixa.setText(DF.format(Double.parseDouble(valorCaixa + "")));
            lblValorPeriodo.setText("Valor em caixa do dia R$ ");
            listPedidos.setModel(model);

            repaint();
            revalidate();
        } catch (SQLException ex) {
            Logger.getLogger(FrameRelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listaSemana() {
        try {
            BuscaDados BD = new BuscaDados();
            ResultSet rs = BD.selectRelatorioPedidosSemana(FramePrincipalRestaurante.id_restaurante);
            DefaultListModel model = new DefaultListModel();
            
            while (rs.next()) {
                String dataInteira = rs.getString("data_pedido") + " ";
                String dataAno = dataInteira.substring(0, 4);
                String dataMes = dataInteira.substring(4, 6);
                String dataDia = dataInteira.substring(6, 8);
                dataInteira = dataDia + "/" + dataMes + "/" + dataAno;
                String tempoInteiro = rs.getString("hora_pedido") + " ";
                String tempoHora;
                String tempoMinuto;
                if(tempoInteiro.length() == 5){
                    tempoHora = tempoInteiro.substring(0, 2);
                    tempoMinuto = tempoInteiro.substring(2, 5);
                    tempoInteiro = tempoHora + "h" + tempoMinuto;
                }else{
                    tempoHora = tempoInteiro.substring(0, 1);
                    tempoMinuto = tempoInteiro.substring(1, 3);
                    tempoInteiro = tempoHora + "h" + tempoMinuto;
                } 
                model.addElement(dataInteira + " " + tempoInteiro + " Valor total " + rs.getString("valor_total") + " - ID: " + rs.getInt("id_pedido"));
                
                float teste = Float.parseFloat(rs.getString("valor_total"));
                valorTotal  += teste;
                float valorFrete = Float.parseFloat(rs.getString("valor_frete"));
                if(valorFrete == 0){
                    destinadoFrete += 2;
                }else{
                    recebidoFrete += 2;
                }
            }
            lblValorPeriodo.setText("Valor em caixa da semana R$");
            valorCaixa = valorTotal - destinadoFrete - recebidoFrete;
            rs.close();
            DecimalFormat DF = new DecimalFormat("##.##");
            
            lblTotal.setText(DF.format(Double.parseDouble(valorTotal + "")));
            lblDestinado.setText(DF.format(Double.parseDouble(destinadoFrete + "")) + ",00");
            lblRecebido.setText(DF.format(Double.parseDouble(recebidoFrete + "")) + ",00");
            lblCaixa.setText(DF.format(Double.parseDouble(valorCaixa + "")));
            lblValorPeriodo.setText("Valor em caixa da semana R$ ");
            listPedidos.setModel(model);
            
            repaint();
            revalidate();
        } catch (SQLException ex) {
            Logger.getLogger(FrameRelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listaMes() {
        try {
            BuscaDados BD = new BuscaDados();
            ResultSet rs = BD.selectRelatorioPedidosMes(FramePrincipalRestaurante.id_restaurante);
            DefaultListModel model = new DefaultListModel();
            
            while (rs.next()) {
                String dataInteira = rs.getString("data_pedido") + " ";
                String dataAno = dataInteira.substring(0, 4);
                String dataMes = dataInteira.substring(4, 6);
                String dataDia = dataInteira.substring(6, 8);
                dataInteira = dataDia + "/" + dataMes + "/" + dataAno;
                String tempoInteiro = rs.getString("hora_pedido") + " ";
                String tempoHora;
                String tempoMinuto;
                if(tempoInteiro.length() == 5){
                    tempoHora = tempoInteiro.substring(0, 2);
                    tempoMinuto = tempoInteiro.substring(2, 5);
                    tempoInteiro = tempoHora + "h" + tempoMinuto;
                }else{
                    tempoHora = tempoInteiro.substring(0, 1);
                    tempoMinuto = tempoInteiro.substring(1, 3);
                    tempoInteiro = tempoHora + "h" + tempoMinuto;
                } 
                model.addElement(dataInteira + " " + tempoInteiro + " Valor total " + rs.getString("valor_total") + " - ID: " + rs.getInt("id_pedido"));
                valorTotal  += Float.parseFloat(rs.getString("valor_total"));
                float valorFrete = Float.parseFloat(rs.getString("valor_frete"));
                if(valorFrete == 0){
                    destinadoFrete += 2;
                }else{
                    recebidoFrete += 2;
                }
            }
            valorCaixa = valorTotal - destinadoFrete - recebidoFrete;
            rs.close();
            DecimalFormat DF = new DecimalFormat("##.##");
            
            lblTotal.setText(DF.format(Double.parseDouble(valorTotal + "")));
            lblDestinado.setText(DF.format(Double.parseDouble(destinadoFrete + "")) + ",00");
            lblRecebido.setText(DF.format(Double.parseDouble(recebidoFrete + "")) + ",00");
            lblCaixa.setText(DF.format(Double.parseDouble(valorCaixa + "")));
            lblValorPeriodo.setText("Valor em caixa do mês R$ ");
            listPedidos.setModel(model);

            repaint();
            revalidate();
        } catch (SQLException ex) {
            Logger.getLogger(FrameRelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listPedidos = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblValorPeriodo = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblDestinado = new javax.swing.JLabel();
        lblRecebido = new javax.swing.JLabel();
        lblCaixa = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 204, 153));
        jLabel1.setText("Histórico de Pedidos");

        jScrollPane1.setViewportView(listPedidos);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Total recebido R$ ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Valor destinado a frete R$");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Valor recebido por frete R$");

        lblValorPeriodo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblValorPeriodo.setText("Valor em caixa do período R$");

        lblTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTotal.setText("jLabel6");

        lblDestinado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDestinado.setText("jLabel7");

        lblRecebido.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblRecebido.setText("jLabel8");

        lblCaixa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCaixa.setText("jLabel9");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(149, 149, 149)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblTotal))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblDestinado)
                                            .addComponent(lblRecebido)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblValorPeriodo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblCaixa)))))
                        .addGap(144, 144, 144)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblTotal))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblDestinado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblRecebido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValorPeriodo)
                    .addComponent(lblCaixa))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(FrameRelatorioPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameRelatorioPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameRelatorioPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameRelatorioPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new FrameRelatorioPedidos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCaixa;
    private javax.swing.JLabel lblDestinado;
    private javax.swing.JLabel lblRecebido;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblValorPeriodo;
    private javax.swing.JList<String> listPedidos;
    // End of variables declaration//GEN-END:variables
}
