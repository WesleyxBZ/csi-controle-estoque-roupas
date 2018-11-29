package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB_Postgre {

    public static void main(String[] args) {
        new ConexaoDB_Postgre().getconexao();
    }

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String HOST = "localhost";
    private static final String PORT = "5432";
    private static final String DB = "estoque";
    private static final String SGBD = "postgresql";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";

    private static final String URL = "jdbc" + ":" + SGBD + "://" + HOST + ":" + PORT + "/" + DB;

    public static Connection getconexao() {
        Connection conn = null;

        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

}
