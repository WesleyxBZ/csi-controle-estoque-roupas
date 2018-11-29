package dao;

import model.Entrada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Categoria;
import model.Estoque;
import model.Produto;

public class EntradaDAO {

    /**
     * Insere uma nova entrada.
     *
     * @param entrada Entrada
     * @return boolean
     */
    public boolean insert(Entrada entrada) {
        try (Connection conn = ConexaoDB_Postgre.getconexao()) {
            String sql = "INSERT INTO entrada (id_entrada, id_estoque, quantidade, criado_em) "
                    + "VALUES (DEFAULT, ?, ?, now()::timestamp(0) without time zone)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, entrada.getEstoque().getId_estoque());
            ps.setInt(2, entrada.getQuantidade());

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    /**
     * Retorna informações das entreadas dos produtos.
     *
     * @return entrada ArrayList - objeto entrada, estoque, produto e categoria
     */
    public ArrayList<Entrada> getEntradas() {

        ArrayList<Entrada> entradas = new ArrayList<>();

        try (Connection conn = ConexaoDB_Postgre.getconexao()) {
            String sql = "SELECT * FROM entrada, estoque, produto WHERE entrada.id_estoque = estoque.id_estoque AND estoque.id_produto = produto.id_produto order by entrada.criado_em desc";
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

                Entrada ent = new Entrada(est, rs.getInt("quantidade"));
                ent.setCriado_em(rs.getString("criado_em"));

                entradas.add(ent);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return entradas;
    }

}
