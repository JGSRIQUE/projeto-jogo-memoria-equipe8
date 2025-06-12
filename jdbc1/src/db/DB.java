package db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            } catch (SQLException e) {
                throw new DbException("Erro ao conectar com o banco de dados: " + e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    private static Properties loadProperties() {
        try (InputStream input = DB.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new DbException("Arquivo db.properties não encontrado no classpath.");
            }
            Properties props = new Properties();
            props.load(input);
            return props;
        } catch (IOException e) {
            throw new DbException("Erro ao carregar db.properties: " + e.getMessage());
        }
    }
}
