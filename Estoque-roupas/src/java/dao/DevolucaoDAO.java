package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Categoria;
import model.Devolucao;
import model.Estoque;
import model.Produto;

public class DevolucaoDAO {

    /**
     * Insere uma nova devolução.
     *
     * @param devolucao Devolucao
     * @return Boolean
     */
    public boolean insert(Devolucao devolucao) {
        try (Connection conn = ConexaoDB_Postgre.getconexao()) {
            String sql = "INSERT INTO devolucao (id_devolucao, id_estoque, quantidade, criado_em) VALUES (DEFAULT, ?, ?, now()::timestamp(0) without time zone)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, devolucao.getEstoque().getId_estoque());
            ps.setInt(2, devolucao.getQuantidade());

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Retornará um objeto com informações das devoluções dos produtos.
     *
     * @return devolucoes ArrayList - objeto devolucao, estoque, produto e
     * categoria
     */
    public ArrayList<Devolucao> getDevolucoes() {

        ArrayList<Devolucao> devolucoes = new ArrayList<>();

        try (Connection conn = ConexaoDB_Postgre.getconexao()) {
            String sql = "SELECT * FROM devolucao, estoque, produto WHERE devolucao.id_estoque = estoque.id_estoque AND estoque.id_produto = produto.id_produto order by devolucao.criado_em desc";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String cat = new CategoriaDAO().getNome(rs.getInt("id_categoria"));
                Categoria c = new Categoria(rs.getInt("id_categoria"), cat);

                Produto p = new Produto();
                p.setId_produto(rs.getInt("id_produto"));
                p.setCategoria(c);
                p.setMarca(rs.getString("marca"));
                p.setCor(rs.getString("cor"));
                p.setDescricao(rs.getString("descricao"));
                p.setTamanho(rs.getString("tamanho"));
                p.setCodigoBarras(rs.getString("codigoBarras"));

                Estoque est = new Estoque();
                est.setProduto(p);

                Devolucao dev = new Devolucao(est, rs.getInt("quantidade"));
                dev.setCriado_em(rs.getString("criado_em"));

                devolucoes.add(dev);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return devolucoes;
    }

}
