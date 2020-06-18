/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author arnor
 */
public class RemoveDados {

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

    public void RemoveEndereco(String endereco, int id_cliente) {
        String sql = "DELETE FROM enderecos WHERE endereco = ? AND id_cliente = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, endereco);
            pstmt.setInt(2, id_cliente);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // nós nao removemos o prato do BD, colocamos ele num restaurante fantasma para que ele nao se perca
    // do histórico de pedidos do cliente
    public void RemovePrato(int id_prato) {
        String sql = "UPDATE pratos SET id_restaurante = ?"
                + "WHERE id_prato = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setNull(1, java.sql.Types.INTEGER);
            pstmt.setInt(2, id_prato);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removePertencimento(int id_restaurante, int id_categoria) {
        String sql = "DELETE FROM pertencimentos WHERE id_restaurante = ? AND id_categoria = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id_restaurante);
            pstmt.setInt(2, id_categoria);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
