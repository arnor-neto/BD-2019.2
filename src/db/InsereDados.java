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
 * @author sqlitetutorial.net
 */
public class InsereDados {

    /**
     * Connect to the test.db database
     *
     * @return the Connection object
     */
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

    /**
     * Insert a new row into the warehouses table
     *
     * @param name
     * @param capacity
     */
    public void insereCliente(String nome, String email, String senha, String endereco) {
        String sql = "INSERT INTO clientes(nome_cliente, email_cliente, senha_cliente, endereco_cliente) VALUES(?,?,?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.setString(3, senha);
            pstmt.setString(4, endereco);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insereRestaurante(String nome, String email, String senha, int aberto, int entrega_rapida, String endereco) {
        String sql = "INSERT INTO restaurantes( nome_restaurante, email_restaurante, senha_restaurante, aberto, entrega_rapida, endereco_restaurante) VALUES(?,?,?,?,?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.setString(3, senha);
            pstmt.setInt(4, aberto);
            pstmt.setInt(5, entrega_rapida);
            pstmt.setString(6, endereco);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void inserePedido(int id_cliente, int id_restaurante, int data, int hora, String endereco_pedido, String valor_total, String valor_frete) {
        String sql = "INSERT INTO pedidos(id_cliente, id_restaurante, data_pedido, hora_pedido, endereco_pedido, valor_total, valor_frete) VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            //pstmt.setInt(1, id_pedido);
            pstmt.setInt(1, id_cliente);
            pstmt.setInt(2, id_restaurante);
            pstmt.setInt(3, data);
            pstmt.setInt(4, hora);
            pstmt.setString(5, endereco_pedido);
            pstmt.setString(6, valor_total);
            pstmt.setString(7, valor_frete);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void inserePrato(int id_restaurante, String nome, String descricao) {
        String sql = "INSERT INTO pratos(id_restaurante, nome_prato, descricao) VALUES(?,?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id_restaurante);
            pstmt.setString(2, nome);
            pstmt.setString(3, descricao);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insereCarrinho(int id_pedido, int id_prato, int quantidade) {
        String sql = "INSERT INTO carrinhos(id_pedido, id_prato, quantidade) VALUES(?,?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id_pedido);
            pstmt.setInt(2, id_prato);
            pstmt.setInt(3, quantidade);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void inserePreco(int id_prato, String valor_preco, int data_preco, int hora_preco) throws SQLException {

        PreparedStatement pstmt;
        String sql = "INSERT INTO precos(id_prato, valor_preco, data_preco, hora_preco) VALUES(?,?,?,?)";

        Connection conn = this.connect();
        pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id_prato);
        pstmt.setString(2, valor_preco);
        pstmt.setInt(3, data_preco);
        pstmt.setInt(4, hora_preco);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void insereCategoria(String nome_categoria) {
        String sql = "INSERT INTO categorias(nome_categoria) VALUES(?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome_categoria);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void inserePertencimento(int id_restaurante, int id_categoria) {
        String sql = "INSERT INTO pertencimentos(id_restaurante, id_categoria) VALUES(?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id_restaurante);
            pstmt.setInt(2, id_categoria);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insereEndereco(String endereco, int id_cliente) throws SQLException {
        PreparedStatement pstmt;
        String sql = "INSERT INTO enderecos(endereco, id_cliente) VALUES(?,?)";

        Connection conn = this.connect();
        pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, endereco);
        pstmt.setInt(2, id_cliente);

        pstmt.executeUpdate();

        pstmt.close();

    }

    /**
     * @param args the command line arguments
     */
    public static void main() throws SQLException {

        InsereDados app = new InsereDados();

        app.insereCategoria("Açaí");
        app.insereCategoria("Italiana");
        app.insereCategoria("Chinesa");
        app.insereCategoria("Japonesa");
        app.insereCategoria("Hamburguer");
        app.insereCategoria("Indiana");
        app.insereCategoria("Sorvete");
        app.insereCategoria("Nordestina");
        app.insereCategoria("Saudável");
        app.insereCategoria("Doces");
        app.insereCategoria("Pizzas");
        app.insereCategoria("Carnes");
        app.insereCategoria("Hamburguer");
        app.insereCategoria("Salgados");
        app.insereCategoria("Tapioca"); 
        app.insereCategoria("Crepes");
        app.insereCategoria("Frutos do Mar");
        app.insereCategoria("Portuguesa");
        app.insereCategoria("Marmita");
        app.insereCategoria("Mexicana");
        app.insereCategoria("Cachorro Quente");
        
        //Inserção indevida
        //app.inserePertencimento(34, 1);
        //Inserçao indevida
        //app.inserePreco(34, "12.50", 20200412, 1031);
    }

}
