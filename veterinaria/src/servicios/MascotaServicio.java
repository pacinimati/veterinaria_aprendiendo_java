package servicios;

import database.DatabaseConnection;
import modelos.Mascota;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MascotaServicio {

    // Método para agregar una mascota a la base de datos
    public void agregarMascota(Mascota mascota) {
        String sql = "INSERT INTO mascotas (nombre, especie, edad, duenio) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, mascota.getNombre());
            pstmt.setString(2, mascota.getEspecie());
            pstmt.setInt(3, mascota.getEdad());
            pstmt.setString(4, mascota.getDuenio());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener todas las mascotas desde la base de datos
    public List<Mascota> obtenerMascotas() {
        List<Mascota> mascotas = new ArrayList<>();
        String sql = "SELECT * FROM mascotas";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Mascota mascota = new Mascota(
                    rs.getString("nombre"),
                    rs.getString("especie"),
                    rs.getInt("edad"),
                    rs.getString("duenio")
                );
                mascotas.add(mascota);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mascotas;
    }

    // Método para mostrar las mascotas en formato texto
    public String mostrarMascotas() {
        List<Mascota> mascotas = obtenerMascotas();
        if (mascotas.isEmpty()) {
            return "⚠️ No hay mascotas registradas.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n📋 Lista de Mascotas:\n");
        for (Mascota m : mascotas) {
            sb.append(m.getNombre()).append(" - ").append(m.getEspecie())
              .append(" - ").append(m.getEdad()).append(" años\n");
            sb.append("-------------------\n");
        }
        return sb.toString();
    }
}