package dao;

import model.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaDAO {

    /**
     * Retorna nome da categoria.
     *
     * @param id_categoria
     * @return nome - String
     */
    public String getNome(int id_categoria) {
        try (Connection conn = ConexaoDB_Postgre.getconexao()) {
            String sql = "SELECT * FROM categoria WHERE id_categoria = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_categoria);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("nome");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return "erro";
    }

    /**
     * Retorna informações como nome e id das categorias.
     *
     * @return categorias - ArrayList
     */
    public ArrayList<Categoria> getCategorias() {

        ArrayList<Categoria> categorias = new ArrayList<>();

        try (Connection conn = ConexaoDB_Postgre.getconexao()) {
            String sql = "SELECT * FROM categoria";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId_categoria(rs.getInt("id_categoria"));
                c.setNome(rs.getString("nome"));
                categorias.add(c);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return categorias;
    }

}
