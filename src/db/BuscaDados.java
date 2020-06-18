/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BuscaDados {

    /**
     * Connect ao database
     *
     * @return o objeto de conexão
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

    public ResultSet selectClienteExiste(String email, String senha) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT *\n"
                + "FROM clientes\n"
                + "WHERE email_cliente = ? AND senha_cliente = ? \n";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, senha);
        rs = ps.executeQuery();

        return rs;

    }

    public ResultSet selectRestauranteExiste(String email, String senha) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT *\n"
                + "FROM restaurantes\n"
                + "WHERE email_restaurante = ? AND senha_restaurante = ? \n";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, senha);
        rs = ps.executeQuery();
        return rs;

    }

    public boolean selectEmailUsadoCliente(String email) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT *\n"
                + "FROM clientes\n"
                + "WHERE email_cliente = ? ";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        rs = ps.executeQuery();
        if (rs.isClosed()) {
            return false;
        }
        rs.close();
        return true;
    }

    public boolean selectEmailUsadoRestaurante(String email) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT *\n"
                + "FROM restaurantes\n"
                + "WHERE email_restaurante = ? ";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        rs = ps.executeQuery();
        if (rs.isClosed()) {
            return false;
        }
        rs.close();
        return true;
    }

    public ResultSet selectRestaurantes() throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT *\n"
                + "FROM restaurantes\n";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        return rs;

    }

    public ResultSet selectCliente(int id_cliente) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT *\n"
                + "FROM clientes \n"
                + "WHERE id_cliente = ?";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id_cliente);
        rs = ps.executeQuery();
        return rs;
    }

    public ResultSet selectRestaurante(int id_restaurante) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT *\n"
                + "FROM restaurantes \n"
                + "WHERE id_restaurante = ?";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id_restaurante);
        rs = ps.executeQuery();
        return rs;
    }

    public ResultSet selectClientePedidos(int id_cliente) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;

        String sql = "SELECT clientes.nome_cliente, pedidos.valor_total, restaurantes.nome_restaurante, pedidos.data_pedido, pedidos.hora_pedido, pedidos.id_pedido\n"
                + "FROM((clientes INNER JOIN pedidos ON clientes.id_cliente = pedidos.id_cliente)\n"
                + "INNER JOIN restaurantes ON pedidos.id_restaurante = restaurantes.id_restaurante)\n"
                + "WHERE clientes.id_cliente = ? \n"
                + "ORDER BY id_pedido DESC";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id_cliente);
        rs = ps.executeQuery();

        return rs;
    }

    public ResultSet selectRestaurantePratos(int id_restaurante) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT *\n"
                + "FROM pratos\n"
                + "WHERE id_restaurante = ?\n";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id_restaurante);
        rs = ps.executeQuery();
        return rs;

    }

    public ResultSet selectRestauranteSelecionado(int id_restaurante) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT *\n"
                + "FROM restaurantes\n"
                + "WHERE id_restaurante = ?\n";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id_restaurante);
        rs = ps.executeQuery();
        return rs;

    }

    public ResultSet selectPedidoPratos(int id_pedido) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;

        String sql = "SELECT pedidos.id_pedido, pratos.id_prato, pratos.nome_prato, carrinhos.quantidade, pedidos.valor_frete, pedidos.valor_total, pedidos.data_pedido, pedidos.hora_pedido\n"
                + "FROM((pedidos INNER JOIN carrinhos USING(id_pedido))\n"
                + "INNER JOIN pratos USING(id_prato))\n"
                + "WHERE carrinhos.id_pedido = ? \n";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id_pedido);
        rs = ps.executeQuery();
        return rs;
    }

    public ResultSet selectClienteEndereco(int id) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;

        String sql = "SELECT clientes.nome_cliente, enderecos.endereco\n"
                + "FROM clientes INNER JOIN enderecos USING(id_cliente)\n"
                + "WHERE id_cliente = " + id;

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        return rs;

    }

    public ResultSet selectRestauranteCategorias(int id) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT restaurantes.nome_restaurante, categorias.nome_categoria, categorias.id_categoria\n"
                + "FROM ((restaurantes INNER JOIN pertencimentos USING(id_restaurante)) INNER JOIN categorias USING(id_categoria))\n"
                + "WHERE id_restaurante = " + id;

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        return rs;

    }

    public ResultSet selectCategoriasExistentes() throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * \n"
                + "FROM categorias";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        return rs;
    }

    public int selectIDCategoria(String nome_categoria) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT id_categoria \n"
                + "FROM categorias WHERE nome_categoria = ?";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        ps.setString(1, nome_categoria);
        rs = ps.executeQuery();
        int id = rs.getInt("id_categoria");
        rs.close();
        return id;
    }

    public ResultSet selectCategoriasRestaurantes(int id_categoria) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * \n"
                + "FROM restaurantes INNER JOIN pertencimentos USING(id_restaurante) \n"
                + "WHERE id_categoria = ?";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id_categoria);
        rs = ps.executeQuery();
        return rs;
    }

    public ResultSet selectResultadoBusca(String busca) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * \n"
                + "FROM restaurantes INNER JOIN pratos USING(id_restaurante) \n"
                + "WHERE nome_restaurante LIKE '%" + busca + "%' OR nome_prato LIKE '%" + busca + "%' OR descricao LIKE '%" + busca + "%' "
                + "GROUP BY nome_restaurante";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        return rs;
    }

    public ResultSet selectPratoPrecos(int id) throws SQLException {
        // aqui nossa função acessa o preço pois o ID do PRECO começa sempre com um ID igual ao do PRATO
        // que o preço pertence so que o resto da string é que determina qual é o preço mais recente  
        // (o preço mais recente sendo sempre o maior valor do resto do ID )
        PreparedStatement ps;
        ResultSet rs;

        String sql = "SELECT pratos.nome_prato, precos.valor_preco, precos.hora_preco, precos.data_preco\n"
                + "FROM pratos INNER JOIN precos USING(id_prato)"
                + "WHERE id_prato = ?\n"
                + "ORDER BY id_preco DESC \n";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        return rs;
    }

    public int selectPratoID() throws SQLException {
        // aqui nossa função acessa o preço pois o ID do PRECO começa sempre com um ID igual ao do PRATO
        // que o preço pertence so que o resto da string é que determina qual é o preço mais recente  
        // (o preço mais recente sendo sempre o maior valor do resto do ID )
        PreparedStatement ps;
        ResultSet rs;

        String sql = "SELECT *\n"
                + "FROM pratos \n"
                + "ORDER BY id_prato DESC \n";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        int id = rs.getInt("id_prato");
        rs.close();
        return id;
    }

    public boolean selectRestauranteEntrega(int id) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT restaurantes.entrega_rapida\n"
                + "FROM restaurantes \n"
                + "WHERE id_restaurante = " + id;

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        boolean entrega = rs.getBoolean("entrega_rapida");

        rs.close();

        return entrega;
    }

    public int countClienteRestaurante(int id_cliente, int id_restaurante) throws SQLException {
        //Retorna o número de pedidos feitos entre um cliente e um restaurante (para gerar id)
        PreparedStatement ps;
        ResultSet rs;

        String sql = "SELECT COUNT(id_pedido)\n"
                + "FROM pedidos\n"
                + "WHERE id_pedido LIKE '" + id_cliente + id_restaurante + "%'\n";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        int count = rs.getInt("COUNT(id_pedido)");
        rs.close();

        return count;
    }

    public int selectTime() throws SQLException {
        //Retorna o número de pedidos feitos entre um cliente e um restaurante (para gerar id)
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT strftime('%H:%M ','now', 'localtime')";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        String dataPedido = rs.getString("Strftime('%H:%M ','now', 'localtime')");

        rs.close();
        String tempoHora = dataPedido.substring(0, 2);
        String tempoMinutos = dataPedido.substring(3, 5);
        String concatTime = tempoHora + tempoMinutos;

        return Integer.parseInt(concatTime);
    }

    public int selectDate() throws SQLException {
        //Retorna o número de pedidos feitos entre um cliente e um restaurante (para gerar id)
        PreparedStatement ps;
        ResultSet rs;

        String sql = "SELECT strftime('%d/%m/%Y ','now', 'localtime')";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        String dataPedido = rs.getString("Strftime('%d/%m/%Y ','now', 'localtime')");

        rs.close();
        String dataDia = dataPedido.substring(0, 2);
        String dataMes = dataPedido.substring(3, 5);
        String dataAno = dataPedido.substring(6, 10);
        String concatData = dataAno + dataMes + dataDia;

        return Integer.parseInt(concatData);
    }

    public float selectPrecoPratoDate(int dataPedido, int horaPedido, int id_pratoEscolhido) throws SQLException {
        float preco = 0;
        PreparedStatement ps1;
        ResultSet rs1;
        // caso no qual houve uma alteração no mesmo dia do pedido e essa alteração foi ANTES do pedido ser feito
        String sql1 = "SELECT *\n"
                + "FROM precos WHERE id_prato = ? AND data_preco = ? AND hora_preco <= ?\n"
                + "ORDER BY id_preco DESC";

        Connection conn = this.connect();
        ps1 = conn.prepareStatement(sql1);
        ps1.setInt(1, id_pratoEscolhido);
        ps1.setInt(2, dataPedido);
        ps1.setInt(3, horaPedido);
        rs1 = ps1.executeQuery();

        // caso no qual não houve uma atualização no mesmo dia que a pessoa fez o pedido, só anteriormente
        if (rs1.isClosed()) {
            PreparedStatement ps2;
            ResultSet rs2;

            String sql2 = "SELECT *\n"
                    + "FROM precos WHERE id_prato = ? AND data_preco < ?\n"
                    + "ORDER BY data_preco DESC";

            Connection conn2 = this.connect();
            ps2 = conn2.prepareStatement(sql2);
            ps2.setInt(1, id_pratoEscolhido);
            ps2.setInt(2, dataPedido);
            rs2 = ps2.executeQuery();

            preco = Float.parseFloat(rs2.getString("valor_preco"));
            rs2.close();
        } else {
            preco = Float.parseFloat(rs1.getString("valor_preco"));
        }

        rs1.close();

        return preco;
    }

    public int selectLastIdPedido() throws SQLException {
        //Retorna o número de pedidos feitos entre um cliente e um restaurante 
        PreparedStatement ps;
        ResultSet rs;

        String sql = "SELECT pedidos.id_pedido \n"
                + "FROM pedidos \n"
                + "ORDER BY id_pedido DESC";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        int id = rs.getInt("id_pedido");
        rs.close();

        return id;
    }

    public ResultSet selectPrato(int id_prato) throws SQLException {
        //Retorna o número de pedidos feitos entre um cliente e um restaurante
        PreparedStatement ps;
        ResultSet rs;

        String sql = "SELECT * \n"
                + "FROM pratos \n"
                + "WHERE id_prato = ?";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id_prato);
        rs = ps.executeQuery();

        return rs;
    }

    public ResultSet selectRelatorioPedidosDia(int id_restaurante) throws SQLException {
        int hoje = selectDate();
        PreparedStatement ps;
        ResultSet rs;

        String sql = "SELECT * \n"
                + "FROM pedidos \n"
                + "WHERE id_restaurante = ? AND data_pedido = ?"
                + "ORDER BY id_pedido DESC";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id_restaurante);
        ps.setInt(2, hoje);
        rs = ps.executeQuery();

        return rs;
    }

    public ResultSet selectRelatorioPedidosSemana(int id_restaurante) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;

        String sql = "SELECT strftime('%d/%m/%Y ','now', 'localtime', '-7 days')";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        String dataPedido = rs.getString("Strftime('%d/%m/%Y ','now', 'localtime', '-7 days')");

        rs.close();
        String dataDia = dataPedido.substring(0, 2);
        String dataMes = dataPedido.substring(3, 5);
        String dataAno = dataPedido.substring(6, 10);
        String concatData = dataAno + dataMes + dataDia;

        int dataSemana = Integer.parseInt(concatData);
        PreparedStatement ps2;
        ResultSet rs2;

        String sql2 = "SELECT * \n"
                + "FROM pedidos \n"
                + "WHERE id_restaurante = ? AND data_pedido >= ?"
                + "ORDER BY id_pedido DESC";

        Connection conn2 = this.connect();
        ps2 = conn2.prepareStatement(sql2);
        ps2.setInt(1, id_restaurante);
        ps2.setInt(2, dataSemana);
        rs2 = ps2.executeQuery();

        return rs2;
    }

    public ResultSet selectRelatorioPedidosMes(int id_restaurante) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;

        String sql = "SELECT strftime('%d/%m/%Y ','now', 'localtime', '-1 month')";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        String dataPedido = rs.getString("Strftime('%d/%m/%Y ','now', 'localtime', '-1 month')");

        rs.close();
        String dataDia = dataPedido.substring(0, 2);
        String dataMes = dataPedido.substring(3, 5);
        String dataAno = dataPedido.substring(6, 10);
        String concatData = dataAno + dataMes + dataDia;

        int dataMesAnterior = Integer.parseInt(concatData);
        PreparedStatement ps2;
        ResultSet rs2;

        String sql2 = "SELECT * \n"
                + "FROM pedidos \n"
                + "WHERE id_restaurante = ? AND data_pedido >= ?"
                + "ORDER BY id_pedido DESC";

        Connection conn2 = this.connect();
        ps2 = conn2.prepareStatement(sql2);
        ps2.setInt(1, id_restaurante);
        ps2.setInt(2, dataMesAnterior);
        rs2 = ps2.executeQuery();

        return rs2;
    }

    public ResultSet selectPrecoMedio(int id_restaurante) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;

        String sql = "SELECT strftime('%d/%m/%Y ','now', 'localtime', '-7 days')";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        String dataPedido = rs.getString("Strftime('%d/%m/%Y ','now', 'localtime', '-7 days')");

        rs.close();
        String dataDia = dataPedido.substring(0, 2);
        String dataMes = dataPedido.substring(3, 5);
        String dataAno = dataPedido.substring(6, 10);
        String concatData = dataAno + dataMes + dataDia;

        int dataSemana = Integer.parseInt(concatData);

        PreparedStatement ps2;
        ResultSet rs2;
        // retorna 
        String sql2 = "SELECT valor_preco, nome_prato \n"
                + "FROM precos INNER JOIN pratos USING(id_prato) \n"
                + "WHERE id_restaurante = ? AND data_preco >= ?"
                + "ORDER BY id_prato";

        Connection conn2 = this.connect();
        ps2 = conn2.prepareStatement(sql2);
        ps2.setInt(1, id_restaurante);
        ps2.setInt(2, dataSemana);
        rs2 = ps2.executeQuery();

        return rs2;
    }

    public ResultSet selectPrecoMedioAnterior(int id_restaurante) throws SQLException {
        // esse metodo vai calulcar o preço medio da semana sem considerar o dia de hoje
        PreparedStatement ps;
        ResultSet rs;

        String sql = "SELECT strftime('%d/%m/%Y ','now', 'localtime', '-7 days')";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        String dataPedido = rs.getString("Strftime('%d/%m/%Y ','now', 'localtime', '-7 days')");

        rs.close();
        String dataDia = dataPedido.substring(0, 2);
        String dataMes = dataPedido.substring(3, 5);
        String dataAno = dataPedido.substring(6, 10);
        String concatData = dataAno + dataMes + dataDia;

        int dataSemana = Integer.parseInt(concatData);

        PreparedStatement ps2;
        ResultSet rs2;
        // retorna 
        String sql2 = "SELECT valor_preco, nome_prato, id_prato\n"
                + "FROM precos INNER JOIN pratos USING(id_prato) \n"
                + "WHERE id_restaurante = ? AND data_preco >= ? AND data_preco < ?"
                + "ORDER BY id_prato";

        Connection conn2 = this.connect();
        ps2 = conn2.prepareStatement(sql2);
        ps2.setInt(1, id_restaurante);
        ps2.setInt(2, dataSemana);
        ps2.setInt(3, selectDate());
        rs2 = ps2.executeQuery();
        
        if(rs2.isClosed()){
            return selectPrecoMedio(id_restaurante);
        }

        return rs2;
    }

    public ResultSet selectPratoMaisVendidoMes(int id_restaurante) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;

        String sql = "SELECT strftime('%d/%m/%Y ','now','start of month')";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        String dataPedido = rs.getString("Strftime('%d/%m/%Y ','now','start of month')");

        rs.close();
        String dataDia = dataPedido.substring(0, 2);
        String dataMes = dataPedido.substring(3, 5);
        String dataAno = dataPedido.substring(6, 10);
        String concatData = dataAno + dataMes + dataDia;

        int dataSemana = Integer.parseInt(concatData);

        PreparedStatement ps2;
        ResultSet rs2;

        String sql2 = "SELECT SUM(quantidade), nome_prato \n"
                + "FROM ((carrinhos INNER JOIN pratos USING(id_prato)) INNER JOIN pedidos USING(id_pedido)) \n"
                + "WHERE pedidos.id_restaurante = ? AND data_pedido >= ? \n"
                + "GROUP BY id_prato \n"
                + "ORDER BY SUM(quantidade) DESC";

        Connection conn2 = this.connect();
        ps2 = conn2.prepareStatement(sql2);
        ps2.setInt(1, id_restaurante);
        ps2.setInt(2, dataSemana);
        rs2 = ps2.executeQuery();

        return rs2;
    }

    public ResultSet selectPratoMaisVendidoDia() throws SQLException {
        PreparedStatement ps;
        ResultSet rs;

        String sql = "SELECT strftime('%d/%m/%Y ','now','-1 day')";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        String dataPedido = rs.getString("Strftime('%d/%m/%Y ','now','-1 day')");

        rs.close();
        String dataDia = dataPedido.substring(0, 2);
        String dataMes = dataPedido.substring(3, 5);
        String dataAno = dataPedido.substring(6, 10);
        String concatData = dataAno + dataMes + dataDia;

        int dataSemana = Integer.parseInt(concatData);

        PreparedStatement ps2;
        ResultSet rs2;

        String sql2 = "SELECT SUM(quantidade), nome_prato, pratos.id_restaurante\n"
                + "FROM ((carrinhos INNER JOIN pratos USING(id_prato)) INNER JOIN pedidos USING(id_pedido)) \n"
                + "WHERE data_pedido = ? \n"
                + "GROUP BY id_prato \n"
                + "ORDER BY SUM(quantidade) DESC";

        Connection conn2 = this.connect();
        ps2 = conn2.prepareStatement(sql2);
        ps2.setInt(1, dataSemana);
        rs2 = ps2.executeQuery();

        return rs2;
    }

    public ResultSet selectRestaurantePratosPrecosAtuais(int id_restaurante) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        // retorna preço atual de cada prato de um restaurante específico
        String sql = "SELECT MAX(id_preco), valor_preco, id_prato \n"
                + "FROM ((restaurantes INNER JOIN pratos USING(id_restaurante)) INNER JOIN precos USING(id_prato))\n"
                + "WHERE id_restaurante = ?"
                + "GROUP BY id_prato";

        Connection conn = this.connect();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id_restaurante);
        rs = ps.executeQuery();

        return rs;
    }

    public void selectPragma() {
        String sql = "PRAGMA foreign_keys";

        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("foreign_keys"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main() throws SQLException {
        //
    }

}
