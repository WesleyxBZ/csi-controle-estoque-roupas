package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {

    /**
     * Verifica se o usuário está cadastrado no BD.
     *
     * @param email
     * @param senha
     * @return Boolean
     */
    public boolean autenticar(String email, String senha) {

        try (Connection conn = ConexaoDB_Postgre.getconexao()) {
            String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
