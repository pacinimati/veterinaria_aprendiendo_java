package servicios;

import database.DatabaseConnection;
import modelos.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteServicio {

    // Método para agregar un cliente a la base de datos
    public void agregarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre, telefono, direccion) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getTelefono());
            pstmt.setString(3, cliente.getDireccion());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener todos los clientes desde la base de datos
    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("direccion")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    // Método para mostrar los clientes en formato texto
    public String mostrarClientes() {
        List<Cliente> clientes = obtenerClientes();
        if (clientes.isEmpty()) {
            return "⚠️ No hay clientes registrados.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n📋 Lista de Clientes:\n");
        for (Cliente c : clientes) {
            sb.append(c.getNombre()).append(" - ").append(c.getTelefono()).append("\n");
            sb.append("-------------------\n");
        }
        return sb.toString();
    }
}