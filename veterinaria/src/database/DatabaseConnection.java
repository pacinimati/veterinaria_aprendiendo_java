package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/veterinaria"; // Cambia "veterinaria" por el nombre de tu base de datos
    private static final String USER = "root"; // Cambia "root" si usas otro usuario
    private static final String PASSWORD = ""; // Cambia "tu_contraseña" por tu contraseña

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}