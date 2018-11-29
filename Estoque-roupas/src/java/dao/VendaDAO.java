package dao;

import model.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Categoria;
import model.Estoque;
import model.Produto;
import model.ProdutoVenda;

public class VendaDAO {

    /**
     * Verifica se o produto a ser inserido já está no carinho e verifica se
     * existe uma tabela venda aberta, se sim, associa produtoVendido a ela e
     * insere produto na tabela produtoVendido.
     *
     * @param id_estoque
     * @param id_usuario
     * @return boolean
     */
    public boolean insert(int id_estoque, int id_usuario) {
        try (Connection conn = ConexaoDB_Postgre.getconexao()) {

            // Verificar se existe venda aberta, se sim, retorna id_venda
            int id_venda = new VendaDAO().verificarVendaAberta();

            if (id_venda == 0) { // Se não existir venda aberta

                // Cria uma venda
                String sql = "INSERT INTO venda(id_venda, id_usuario) VALUES (DEFAULT, ?)";
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, id_usuario);

                if (ps.executeUpdate() > 0) {

                    ResultSet rs = ps.getGeneratedKeys(); // Pega chave SQL gerada

                    if (rs.next()) {

                        // Relaciona o produtovenda a venda
                        sql = "INSERT INTO produtoVenda(id_produtoVenda, id_estoque, id_venda, quantidade) VALUES (DEFAULT, ?, ?, 1)";
                        PreparedStatement ps1 = conn.prepareStatement(sql);

                        ps1.setInt(1, id_estoque);
                        ps1.setInt(2, rs.getInt("id_venda"));

                        if (ps1.executeUpdate() > 0) {
                            return true;
                        }
                    }
                }

            } else { // Se existir venda aberta

                // Verifica se o produto não já está no carinho
                if (!new VendaDAO().verifProdEstaCarinho(id_estoque)) {

                    String sql = "INSERT INTO produtoVenda (id_produtoVenda, id_estoque, id_venda, quantidade) VALUES (DEFAULT, ?, ?, 1)";
                    PreparedStatement ps = conn.prepareStatement(sql);

                    ps.setInt(1, id_estoque);
                    ps.setInt(2, id_venda);

                    if (ps.executeUpdate() > 0) {
                        return true;
                    }
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Verifica se existe uma tabela venda sem estar finalizada, se sim, retona
     * o id desta tabela.
     *
     * @return int, 0 == false e > 1 == true
     */
    public int verificarVendaAberta() {
        try (Connection conn = ConexaoDB_Postgre.getconexao()) {

            String sql = "SELECT MAX(id_venda) FROM venda";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            // Se tiver resultado
            if (rs.next()) {

                int id = rs.getInt("max");

                if (id != 0) {

                    sql = "SELECT case when venda.valortotal IS NULL then true else false end FROM"
                            + " venda WHERE venda.id_venda = ?";
                    PreparedStatement ps1 = conn.prepareStatement(sql);
                    ps1.setInt(1, id);

                    ResultSet rs1 = ps1.executeQuery();

                    if (rs1.next()) {

                        if (rs1.getBoolean("case")) {
                            return id;
                        }
                    }
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * Verifica se o produto adicionado já existe na tabela produtoVenda com
     * venda aberta.
     *
     * @param id_estoque
     * @return boolean
     */
    public boolean verifProdEstaCarinho(int id_estoque) {

        try (Connection conn = ConexaoDB_Postgre.getconexao()) {

            // Verifica se o produto está no carinho, true == produto no carinho com venda aberta
            String sql = "SELECT case when venda.valortotal IS NULL then true else false end FROM produtovenda, venda WHERE "
                    + "produtovenda.id_estoque = ? AND produtovenda.id_venda = venda.id_venda AND venda.valortotal IS NULL;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_estoque);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getBoolean("case")) {
                    return true;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    /**
     * Verifica se o produto existe na tabela produtoVenda e se venda está
     * aberta.
     *
     * @param id_produtoVenda
     * @return boolean - true == produto está no carinho com venda aberta
     */
    public boolean VerifProdVenda(int id_produtoVenda) {

        try (Connection conn = ConexaoDB_Postgre.getconexao()) {

            // Verifica se o produto está no carinho com venda aberta, se retonar nada o produto não está, se retornar true o produto está
            String sql = "SELECT case when venda.valortotal IS NULL then true else false end FROM produtovenda, venda WHERE "
                    + "produtovenda.id_produtovenda = ? AND produtovenda.id_venda = venda.id_venda";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_produtoVenda);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getBoolean("case")) {
                    return true;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    /**
     * Deleta produto da tabela produtoVendido.
     *
     * @param id_produtoVenda
     * @return boolean
     */
    public boolean deletarProdCarinho(int id_produtoVenda) {

        try (Connection conn = ConexaoDB_Postgre.getconexao()) {
            String sql = "DELETE FROM produtoVenda WHERE id_produtoVenda = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_produtoVenda);

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    /**
     * Retorna os produtos que estao no carinho (tabela produtoVenda com valor
     * total em venda igual a null).
     *
     * @return ProdutoVenda - ArrayList
     */
    public ArrayList<ProdutoVenda> getProdCarinho() {

        ArrayList<ProdutoVenda> produtoVendas = new ArrayList<>();

        try (Connection conn = ConexaoDB_Postgre.getconexao()) {
            String sql = "SELECT * FROM categoria, produto, estoque, produtoVenda, venda WHERE venda.valortotal IS NULL AND "
                    + "venda.id_venda = produtoVenda.id_venda AND produtoVenda.id_estoque = estoque.id_estoque AND "
                    + "estoque.id_produto = produto.id_produto AND produto.id_categoria = categoria.id_categoria";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Categoria c = new Categoria(rs.getInt("id_categoria"), rs.getString("nome"));

                Produto p = new Produto();
                p.setCategoria(c);
                p.setMarca(rs.getString("marca"));
                p.setDescricao(rs.getString("descricao"));
                p.setCor(rs.getString("cor"));
                p.setTamanho(rs.getString("tamanho"));
                p.setCodigoBarras(rs.getString("codigoBarras"));
                p.setPreco(rs.getDouble("preco"));

                Estoque e = new Estoque(rs.getInt("id_estoque"), p, rs.getInt("qtdProd"));

                Venda v = new Venda();
                v.setId_venda(rs.getInt("id_venda"));

                ProdutoVenda pv = new ProdutoVenda(rs.getInt("id_produtoVenda"), e, v, 1);

                produtoVendas.add(pv);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return produtoVendas;
    }

    /**
     * Retorna a quantidade produtos que estao no carinho (tabela produtoVenda
     * com valor total em venda igual a null).
     *
     * @return int
     */
    public int getQtdProdCarinho() {

        int qtd = 0;

        try (Connection conn = ConexaoDB_Postgre.getconexao()) {
            String sql = "SELECT count(*) FROM categoria, produto, estoque, produtoVenda, venda WHERE venda.valortotal IS NULL AND "
                    + "venda.id_venda = produtoVenda.id_venda AND produtoVenda.id_estoque = estoque.id_estoque AND "
                    + "estoque.id_produto = produto.id_produto AND produto.id_categoria = categoria.id_categoria";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("count");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    /**
     * Retorna o valor total de produtos que estao no carinho (tabela
     * produtoVenda com valor total em venda igual a null).
     *
     * @return double
     */
    public double getValorTotalProdCarinho() {

        double valorTotal = 0;

        try (Connection conn = ConexaoDB_Postgre.getconexao()) {
            String sql = "SELECT produto.preco FROM categoria, produto, estoque, produtoVenda, venda WHERE venda.valortotal IS NULL AND "
                    + "venda.id_venda = produtoVenda.id_venda AND produtoVenda.id_estoque = estoque.id_estoque AND "
                    + "estoque.id_produto = produto.id_produto AND produto.id_categoria = categoria.id_categoria";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                valorTotal += rs.getInt("preco");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return valorTotal;
    }

    /**
     * Retorna os produtos vendidos (venda == finalizada).
     *
     * @return entrada - ArrayList objeto entrada, estoque, produto e categoria
     */
    public ArrayList<ProdutoVenda> getProdutosVendidos() {

        ArrayList<ProdutoVenda> produtosVendidos = new ArrayList<>();

        try (Connection conn = ConexaoDB_Postgre.getconexao()) {
            String sql = "SELECT * FROM categoria, produto, estoque, produtoVenda, venda WHERE venda.valortotal IS NOT NULL AND "
                    + "venda.id_venda = produtoVenda.id_venda AND produtoVenda.id_estoque = estoque.id_estoque AND "
                    + "estoque.id_produto = produto.id_produto AND produto.id_categoria = categoria.id_categoria";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Categoria c = new Categoria(rs.getInt("id_categoria"), rs.getString("nome"));

                Produto p = new Produto();
                p.setCategoria(c);
                p.setMarca(rs.getString("marca"));
                p.setDescricao(rs.getString("descricao"));
                p.setCor(rs.getString("cor"));
                p.setTamanho(rs.getString("tamanho"));
                p.setCodigoBarras(rs.getString("codigoBarras"));
                p.setPreco(rs.getDouble("preco"));

                Estoque e = new Estoque(rs.getInt("id_estoque"), p, rs.getInt("qtdProd"));

                Venda v = new Venda();
                v.setId_venda(rs.getInt("id_venda"));
                v.setCriado_em(rs.getString("criado_em"));
                v.setValorTotal(rs.getDouble("valortotal"));

                ProdutoVenda pv = new ProdutoVenda(rs.getInt("id_produtoVenda"), e, v, rs.getInt("quantidade"));

                produtosVendidos.add(pv);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return produtosVendidos;
    }

}
