/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author arnor
 */
public class AtualizaDados {

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C:/Users/arnor/Documents/NetBeansProjects/fakeIfood/db/fakeIfood.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            String sql = "PRAGMA foreign_keys = ON";
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // muda informações de prato
    public void AtualizaPrato(int id_prato, String novoNome, String novoPreco, String descricao) throws SQLException {

        String sql = "UPDATE pratos SET nome_prato = ? , "
                + "descricao = ? "
                + "WHERE id_prato = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, novoNome);
            pstmt.setString(2, descricao);
            pstmt.setInt(3, id_prato);
            // update 
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        BuscaDados BD = new BuscaDados();
        int data = BD.selectDate();
        int hora = BD.selectTime();

        InsereDados ID = new InsereDados();
        ID.inserePreco(id_prato, novoPreco, data, hora);
    }

    public void atualizaStatus(int id_restaurante, int aberto) {

        String sql = "UPDATE restaurantes SET aberto = ? "
                + "WHERE id_restaurante = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1, aberto);
            pstmt.setInt(2, id_restaurante);
            // update 
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void atualizaEntrega(int id_restaurante, int rapida) {

        String sql = "UPDATE restaurantes SET entrega_rapida = ? "
                + "WHERE id_restaurante = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1, rapida);
            pstmt.setInt(2, id_restaurante);
            // update 
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void atualizaCliente(int id_cliente, String nome, String email, String senha) {
        String sql = "UPDATE clientes SET nome_cliente = ? , email_cliente = ? , senha_cliente = ? "
                + "WHERE id_cliente = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.setString(3, senha);
            pstmt.setInt(4, id_cliente);
            // update 
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizaRestaurante(int id_cliente, String nome, String email, String senha) {
        String sql = "UPDATE restaurantes SET nome_restaurante = ? , email_restaurante = ? , senha_restaurante = ?"
                + "WHERE id_restaurante = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.setString(3, senha);
            pstmt.setInt(4, id_cliente);
            // update 
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
