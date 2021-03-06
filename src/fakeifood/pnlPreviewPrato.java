/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fakeifood;

import db.BuscaDados;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author arnor
 */
public class pnlPreviewPrato extends javax.swing.JPanel {

    private int quantidade;
    private int id_prato;
    private int id_restaurante;
    private String nome_prato;
    private String preco_prato;

    /**
     * Creates new form pnlPreviewPrato
     */
    public pnlPreviewPrato(String nome, String preco, String descricao, int id_prato, int id_restaurante) {
        initComponents();

        lblNome.setText(nome);
        lblPreco.setText("R$ " + preco);
        fldDescricao.setText(descricao);
        quantidade = 0;
        this.id_prato = id_prato;
        this.nome_prato = nome;
        this.preco_prato = preco;
        this.id_restaurante = id_restaurante;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNome = new javax.swing.JLabel();
        lblDescricao = new javax.swing.JLabel();
        lblPreco = new javax.swing.JLabel();
        btnAdicionar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        fldDescricao = new javax.swing.JTextArea();
        lblQuantidade = new javax.swing.JLabel();
        lblQ = new javax.swing.JLabel();
        btnMais = new javax.swing.JButton();
        btnMenos = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 204, 153));

        lblNome.setText("Nome");

        lblDescricao.setText("Descrição");

        lblPreco.setText("Preço");

        btnAdicionar.setBackground(new java.awt.Color(255, 255, 255));
        btnAdicionar.setText("Adicionar ao carrinho");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        fldDescricao.setEditable(false);
        fldDescricao.setColumns(20);
        fldDescricao.setRows(5);
        jScrollPane1.setViewportView(fldDescricao);

        lblQuantidade.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblQuantidade.setText("0");

        lblQ.setText("Quantidade");

        btnMais.setBackground(new java.awt.Color(255, 255, 255));
        btnMais.setText("+");
        btnMais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaisActionPerformed(evt);
            }
        });

        btnMenos.setBackground(new java.awt.Color(255, 255, 255));
        btnMenos.setText("-");
        btnMenos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDescricao)
                            .addComponent(lblNome)
                            .addComponent(lblPreco))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnMenos)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblQuantidade)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnMais, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(17, 17, 17))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                                .addComponent(lblQ)
                                .addContainerGap(100, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(lblQ))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnMenos)
                            .addComponent(lblQuantidade)
                            .addComponent(btnMais)
                            .addComponent(lblPreco))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAdicionar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(lblDescricao)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        try {
            // TODO add your handling code here:
            
            Carrinho c = FramePrincipalCliente.getCarrinho();
            BuscaDados BD = new BuscaDados();
            ResultSet rs = BD.selectRestaurante(id_restaurante);
            int aberto = rs.getInt("aberto");
            rs.close();
            
            if(aberto == 0){
                JOptionPane.showMessageDialog(null, "O restaurante está fechado.");
            }else{
                if (Integer.parseInt(lblQuantidade.getText()) == 0) {
                    JOptionPane.showMessageDialog(null, "Adicione pelo menos um item.");
                } else if (c.id_pratos.isEmpty() || (!c.id_pratos.isEmpty() && (c.getRestaurante() == id_restaurante))) {
                    
                    Object[] options = {"Ver carrinho", "Continuar comprando"};
                    
                    //criando popup com duas opções
                    //se for selecionado o da esquerda n = 0
                    //se for selecionado o da direita n = 1
                    //se o popup for fechado n = -1
                    int n = JOptionPane.showOptionDialog(this,
                            "Item adicionado! Deseja ver o carrinho?",
                            "",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null, //do not use a custom Icon
                            options, //the titles of buttons
                            options[0]); //default button title
                    
                    if (n == 0) {
                        FramePrincipalCliente.getCarrinho().setRestaurante(id_restaurante);
                        FramePrincipalCliente.getCarrinho().addItem(id_prato, nome_prato, preco_prato, quantidade);
                        FrameCarrinho carrinho = new FrameCarrinho();
                        carrinho.setVisible(true);
                    } else {
                        FramePrincipalCliente.getCarrinho().setRestaurante(id_restaurante);
                        FramePrincipalCliente.getCarrinho().addItem(id_prato, nome_prato, preco_prato, quantidade);
                    }
                    
                } else {
                    
                    Object[] options = {"Esvaziar carrinho e adicionar", "Cancelar"};
                    
                    int n = JOptionPane.showOptionDialog(this,
                            "Você só pode adiconar itens de um restaurante por vez. \n       Deseja esvaziar o carrinho e adicionar este item?",
                            "",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null, //do not use a custom Icon
                            options, //the titles of buttons
                            options[0]); //default button title
                    if (n == 0) {
                        FramePrincipalCliente.getCarrinho().setRestaurante(id_restaurante);
                        FramePrincipalCliente.getCarrinho().esvaziaCarrinho();
                        FramePrincipalCliente.getCarrinho().addItem(id_prato, nome_prato, preco_prato, quantidade);
                        FrameCarrinho carrinho = new FrameCarrinho();
                        carrinho.setVisible(true);
                    } else {
                        //cancela esvaziamento
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(pnlPreviewPrato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnMaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaisActionPerformed
        // TODO add your handling code here:
        quantidade++;
        lblQuantidade.setText("" + quantidade);
    }//GEN-LAST:event_btnMaisActionPerformed

    private void btnMenosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenosActionPerformed
        // TODO add your handling code here:
        if (quantidade == 0) {
            //nada
        } else {
            quantidade--;
            lblQuantidade.setText("" + quantidade);
        }
    }//GEN-LAST:event_btnMenosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnMais;
    private javax.swing.JButton btnMenos;
    private javax.swing.JTextArea fldDescricao;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblPreco;
    private javax.swing.JLabel lblQ;
    private javax.swing.JLabel lblQuantidade;
    // End of variables declaration//GEN-END:variables
}
