package dao;

import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    /**
     * Insere um novo usuário.
     *
     * @param usuario Usuario
     * @return Boolean
     */
    public boolean insert(Usuario usuario) {
        try (Connection conn = ConexaoDB_Postgre.getconexao()) {
            String sql = "INSERT INTO usuario (id_usuario, nome, email, senha) VALUES (DEFAULT,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Retorna nome e id do usuário.
     *
     * @param email
     * @return usuario Usuario
     */
    public Usuario getUsuario(String email) {
        Usuario u = null;
        try (Connection conn = ConexaoDB_Postgre.getconexao()) {
            String sql = "SELECT id_usuario, usuario.nome FROM usuario WHERE usuario.email = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                u = new Usuario();
                u.setNome(rs.getString("nome"));
                u.setId_usuario(rs.getInt("id_usuario"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return u;
    }

    /**
     * Atualiza nome e senha do usuário
     *
     * @param usuario Usuario
     */
    public void update(Usuario usuario) {
        try (Connection conn = ConexaoDB_Postgre.getconexao()) {
            String sql = "UPDATE usuario SET nome = ?, senha = ? WHERE id_usuario = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getNome());;
            ps.setString(2, usuario.getSenha());
            ps.setInt(3, usuario.getId_usuario());

            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Deleta usuário.
     *
     * @param id_usuario
     * @return Boolean
     */
    public Boolean delete(int id_usuario) {
        try (Connection conn = ConexaoDB_Postgre.getconexao()) {
            String sql = "SELETE FROM usuario WHERE id_usuario = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_usuario);

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

}
