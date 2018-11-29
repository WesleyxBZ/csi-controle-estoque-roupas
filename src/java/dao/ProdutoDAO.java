package dao;

import model.Produto;
import model.Estoque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.validation.ConstraintViolationException;
import model.Categoria;

public class ProdutoDAO {

    /**
     * Insere um novo produto e estoque.
     *
     * @param estoque Estoque
     * @return int - 0 == false e > 1 == true
     */
    public int insert(Estoque estoque) {
        try (Connection conn = ConexaoDB_Postgre.getconexao()) {

            conn.setAutoCommit(false);

            String sql = "INSERT INTO produto (id_produto, id_categoria, marca, cor, tamanho, descricao, "
                    + "codigoBarras, preco, caminhoImg, visivelCliente, criado_em) "
                    + "VALUES (DEFAULT,?,?,?,?,?,?,?,?,?,now()::timestamp(0) without time zone)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, estoque.getProduto().getCategoria().getId_categoria());
            ps.setString(2, estoque.getProduto().getMarca());
            ps.setString(3, estoque.getProduto().getCor());
            ps.setString(4, estoque.getProduto().getTamanho());
            ps.setString(5, estoque.getProduto().getDescricao());
            ps.setString(6, estoque.getProduto().getCodigoBarras());
            ps.setDouble(7, estoque.getProduto().getPreco());
            ps.setString(8, estoque.getProduto().getCaminhoImg());
            ps.setBoolean(9, estoque.getProduto().isVisivelCliente());

            if (ps.executeUpdate() > 0) { // Executa comando SQL de 'produto', se ok, executa SQL de 'estoque'

                ResultSet rs = ps.getGeneratedKeys(); // Pega chave SQL gerada por 'produto'     

                if (rs.next()) { // Se tiver um próximo

                    sql = "INSERT INTO estoque (id_estoque, id_produto, qtdprod) VALUES (DEFAULT,?,?)";
                    PreparedStatement ps1 = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps1.setInt(1, rs.getInt("id_produto"));
                    ps1.setInt(2, estoque.getQtdProd());

                    if (ps1.executeUpdate() > 0) {

                        ResultSet rs1 = ps1.getGeneratedKeys(); // Pega chave SQL gerada por 'estoque' 

                        if (rs1.next()) {
                            conn.commit();
                            return rs1.getInt("id_estoque");
                        }
                    }
                }
            }
            conn.rollback(); // Se não conseguir inserir estoque, desfaz inserção de 'produto' também

        } catch (ConstraintViolationException e) {
            throw new RuntimeException("codigo de barras já existe");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    /**
     * Busca informações de um produto.
     *
     * @param id_produto
     * @return produto Produto
     */
    public Produto read(int id_produto) {
        Produto p = null;

        try (Connection conn = ConexaoDB_Postgre.getconexao()) {
            String sql = "SELECT * FROM produto WHERE produto.id_produto = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_produto);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Categoria c = new Categoria();
                    c.setNome(new CategoriaDAO().getNome(rs.getInt("id_categoria")));

                    p = new Produto();
                    p.setId_produto(rs.getInt("id_produto"));
                    p.setCategoria(c);
                    p.setMarca(rs.getString("marca"));
                    p.setDescricao(rs.getString("descricao"));
                    p.setCor(rs.getString("cor"));
                    p.setTamanho(rs.getString("tamanho"));
                    p.setCodigoBarras(rs.getString("codigoBarras"));
                    p.setPreco(rs.getDouble("preco"));
                    p.setVisivelCliente(rs.getBoolean("visivelCliente"));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }

    /**
     * Atualiza informações do produto.
     *
     * @param produto Produto
     * @return Boolean
     */
    public boolean updateProduto(Produto produto) {
        try (Connection conn = ConexaoDB_Postgre.getconexao()) {

            conn.setAutoCommit(false);

            String sql = "UPDATE produto SET marca = ?, descricao = ?, preco = ?, caminhoImg = ?, visivelCliente = ?,"
                    + " atualizado_em = now()::timestamp(0) without time zone WHERE id_produto = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, produto.getMarca());
            ps.setString(2, produto.getDescricao());
            ps.setDouble(3, produto.getPreco());
            ps.setString(4, produto.getCaminhoImg());
            ps.setBoolean(5, produto.isVisivelCliente());
            ps.setInt(6, produto.getId_produto());

            if (ps.executeUpdate() > 0) {
                conn.commit();
                return true;
            }
            conn.rollback();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Atualiza estoque para uma quantidade maior.
     *
     * @param estoque Estoque
     * @return Boolean
     */
    public boolean updateEntradaEstoque(Estoque estoque) {
        try (Connection conn = ConexaoDB_Postgre.getconexao()) {
            String sql = "UPDATE estoque SET qtdProd = qtdProd + ?, atualizado_em = now()::timestamp(0) without time zone WHERE id_estoque = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, estoque.getQtdProd());
            ps.setInt(2, estoque.getId_estoque());

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    /**
     * Atualiza estoque para uma quantidade menor.
     *
     * @return int
     */
    public boolean updateDevolucaoEstoque(Estoque estoque) {

        try (Connection conn = ConexaoDB_Postgre.getconexao()) {

            String sql = "UPDATE estoque SET qtdProd = qtdProd - ?, atualizado_em = now()::timestamp(0) without time zone WHERE estoque.id_estoque = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, estoque.getQtdProd());
            ps.setInt(2, estoque.getId_estoque());

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Atualiza a quantidade de produtos presentes no estoque.
     *
     * @param estoque Estoque
     * @return boolean
     */
    public boolean updateAuditarEstoque(Estoque estoque) {

        try (Connection conn = ConexaoDB_Postgre.getconexao()) {

            String sql = "UPDATE estoque SET qtdProd = ?, atualizado_em = now()::timestamp(0) without time zone WHERE estoque.id_estoque = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, estoque.getQtdProd());
            ps.setInt(2, estoque.getId_estoque());

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Retorna produto.
     *
     * @return estoque Estoque
     */
    public ArrayList<Estoque> getProdutoEstoque() {

        ArrayList<Estoque> prodEst = new ArrayList<>();

        try (Connection conn = ConexaoDB_Postgre.getconexao()) {
            String sql = "SELECT * FROM produto, estoque WHERE produto.id_produto = estoque.id_produto";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String categ = new CategoriaDAO().getNome(rs.getInt("id_categoria"));
                Categoria c = new Categoria(rs.getInt("id_categoria"), categ);

                Produto p = new Produto(rs.getInt("id_produto"), c, rs.getString("marca"), rs.getString("cor"), rs.getString("tamanho"),
                        rs.getString("descricao"), rs.getString("codigoBarras"), rs.getDouble("preco"), rs.getString("caminhoImg"), rs.getBoolean("visivelCliente"));
                p.setCriado_em(rs.getString("criado_em"));

                Estoque pe = new Estoque(rs.getInt("id_estoque"), p, rs.getInt("qtdProd"));

                prodEst.add(pe);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return prodEst;
    }

    /**
     * Retorna produtos com estoque maior que 0.
     *
     * @return estoque Estoque
     */
    public ArrayList<Estoque> getProdutosComEstoque() {

        ArrayList<Estoque> prodEst = new ArrayList<>();

        try (Connection conn = ConexaoDB_Postgre.getconexao()) {
            String sql = "SELECT * FROM produto, estoque WHERE produto.id_produto = estoque.id_produto AND estoque.qtdprod > 0";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String categ = new CategoriaDAO().getNome(rs.getInt("id_categoria"));
                Categoria c = new Categoria(rs.getInt("id_categoria"), categ);

                Produto p = new Produto(rs.getInt("id_produto"), c, rs.getString("marca"), rs.getString("cor"), rs.getString("tamanho"),
                        rs.getString("descricao"), rs.getString("codigoBarras"), rs.getDouble("preco"), rs.getString("caminhoImg"), rs.getBoolean("visivelCliente"));
                p.setCriado_em(rs.getString("criado_em"));

                Estoque pe = new Estoque(rs.getInt("id_estoque"), p, rs.getInt("qtdProd"));

                prodEst.add(pe);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return prodEst;
    }

    /**
     * Retorna produtos com estoque maior que 0 e que não estejam na tabela
     * venda.
     *
     * @return estoque Estoque
     */
    public ArrayList<Estoque> getProdutosVenda() {

        ArrayList<Estoque> prodEst = new ArrayList<>();

        try (Connection conn = ConexaoDB_Postgre.getconexao()) {
            String sql = "SELECT * FROM categoria, produto, estoque WHERE categoria.id_categoria = produto.id_categoria AND "
                    + "produto.id_produto = estoque.id_produto AND estoque.qtdprod > 0 AND estoque.id_estoque "
                    + "NOT IN (SELECT produtoVenda.id_estoque FROM produtoVenda, venda WHERE estoque.id_estoque = produtoVenda.id_estoque "
                    + "AND produtoVenda.id_venda = venda.id_venda AND venda.valortotal IS NULL)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String categ = new CategoriaDAO().getNome(rs.getInt("id_categoria"));
                Categoria c = new Categoria(rs.getInt("id_categoria"), categ);

                Produto p = new Produto(rs.getInt("id_produto"), c, rs.getString("marca"), rs.getString("cor"), rs.getString("tamanho"),
                        rs.getString("descricao"), rs.getString("codigoBarras"), rs.getDouble("preco"), rs.getString("caminhoImg"), rs.getBoolean("visivelCliente"));
                p.setCriado_em(rs.getString("criado_em"));

                Estoque pe = new Estoque(rs.getInt("id_estoque"), p, rs.getInt("qtdProd"));

                prodEst.add(pe);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return prodEst;
    }

    /**
     * Retorna a quantidade em estoque de um produto.
     *
     * @param id_estoque
     * @return int
     */
    public int getQtdEstoque(int id_estoque) {
        try (Connection conn = ConexaoDB_Postgre.getconexao()) {

            String sql = "SELECT estoque.qtdprod FROM estoque WHERE estoque.id_estoque = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_estoque);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("qtdprod");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * Retorna a quantidade de produtos cadastrados.
     *
     * @return int
     */
    public int getQtdProdutos() {
        try (Connection conn = ConexaoDB_Postgre.getconexao()) {

            String sql = "SELECT COUNT(*) FROM estoque";
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
     * Retorna a quantidade de produtos cadastrados com estoque.
     *
     * @return int
     */
    public int getQtdProdutoComEstoque() {
        try (Connection conn = ConexaoDB_Postgre.getconexao()) {

            String sql = "SELECT COUNT(*) FROM estoque WHERE estoque.qtdprod > 0";
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
     * Retorna a quantidade de produtos cadastrados sem estoque.
     *
     * @return int
     */
    public int getQtdProdutoSemEstoque() {
        try (Connection conn = ConexaoDB_Postgre.getconexao()) {

            String sql = "SELECT COUNT(*) FROM estoque WHERE estoque.qtdprod = 0";
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

}
