package ui;

import modelos.Cliente;
import modelos.Mascota;
import servicios.ClienteServicio;
import servicios.MascotaServicio;

import javax.swing.*;
import java.awt.*;

public class MainGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Veterinaria 🏥");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        ClienteServicio clienteServicio = new ClienteServicio();
        MascotaServicio mascotaServicio = new MascotaServicio();

        JPanel panelCentro = new JPanel(new GridBagLayout());
        panelCentro.setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel nombreClienteLabel = new JLabel("Nombre Cliente:");
        JTextField nombreClienteField = new JTextField(15);
        JLabel telefonoLabel = new JLabel("Teléfono:");
        JTextField telefonoField = new JTextField(15);
        JLabel direccionLabel = new JLabel("Dirección:");
        JTextField direccionField = new JTextField(15);

        JLabel nombreMascotaLabel = new JLabel("Nombre Mascota:");
        JTextField nombreMascotaField = new JTextField(15);
        JLabel especieLabel = new JLabel("Especie:");
        JTextField especieField = new JTextField(15);
        JLabel edadLabel = new JLabel("Edad:");
        JTextField edadField = new JTextField(15);

        JButton agregarClienteButton = new JButton("Agregar Cliente");
        JButton agregarMascotaButton = new JButton("Agregar Mascota");
        JButton verClientesButton = new JButton("Ver Clientes");
        JButton verMascotasButton = new JButton("Ver Mascotas");
        JTextArea areaDeTexto = new JTextArea(10, 40);
        areaDeTexto.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaDeTexto);

        nombreClienteLabel.setFont(new Font("Arial", Font.BOLD, 12));
        nombreMascotaLabel.setFont(new Font("Arial", Font.BOLD, 12));
        agregarClienteButton.setBackground(new Color(144, 238, 144));
        agregarMascotaButton.setBackground(new Color(173, 216, 230));

        panelCentro.add(nombreClienteLabel, gbc);
        gbc.gridx = 1;
        panelCentro.add(nombreClienteField, gbc);
        gbc.gridx = 0; gbc.gridy++;
        panelCentro.add(telefonoLabel, gbc);
        gbc.gridx = 1;
        panelCentro.add(telefonoField, gbc);
        gbc.gridx = 0; gbc.gridy++;
        panelCentro.add(direccionLabel, gbc);
        gbc.gridx = 1;
        panelCentro.add(direccionField, gbc);
        gbc.gridx = 0; gbc.gridy++;
        panelCentro.add(nombreMascotaLabel, gbc);
        gbc.gridx = 1;
        panelCentro.add(nombreMascotaField, gbc);
        gbc.gridx = 0; gbc.gridy++;
        panelCentro.add(especieLabel, gbc);
        gbc.gridx = 1;
        panelCentro.add(especieField, gbc);
        gbc.gridx = 0; gbc.gridy++;
        panelCentro.add(edadLabel, gbc);
        gbc.gridx = 1;
        panelCentro.add(edadField, gbc);
        gbc.gridx = 0; gbc.gridy++;
        panelCentro.add(agregarClienteButton, gbc);
        gbc.gridx = 1;
        panelCentro.add(agregarMascotaButton, gbc);
        gbc.gridx = 0; gbc.gridy++;
        panelCentro.add(verClientesButton, gbc);
        gbc.gridx = 1;
        panelCentro.add(verMascotasButton, gbc);

        frame.add(panelCentro, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);

        // Acción para agregar un cliente
        agregarClienteButton.addActionListener(e -> {
            String nombreCliente = nombreClienteField.getText().trim();
            String telefono = telefonoField.getText().trim();
            String direccion = direccionField.getText().trim();

            if (nombreCliente.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Todos los campos del cliente son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Cliente cliente = new Cliente(nombreCliente, telefono, direccion);
            clienteServicio.agregarCliente(cliente);
            JOptionPane.showMessageDialog(frame, "Cliente agregado: " + nombreCliente);
            nombreClienteField.setText("");
            telefonoField.setText("");
            direccionField.setText("");
        });

        // Acción para agregar una mascota
        agregarMascotaButton.addActionListener(e -> {
            String nombreMascota = nombreMascotaField.getText().trim();
            String especie = especieField.getText().trim();
            String edadTexto = edadField.getText().trim();
            String duenio = nombreClienteField.getText().trim();

            if (nombreMascota.isEmpty() || especie.isEmpty() || edadTexto.isEmpty() || duenio.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Todos los campos de la mascota son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int edad = Integer.parseInt(edadTexto);
                Mascota mascota = new Mascota(nombreMascota, especie, edad, duenio);
                mascotaServicio.agregarMascota(mascota);
                JOptionPane.showMessageDialog(frame, "Mascota agregada: " + nombreMascota);
                nombreMascotaField.setText("");
                especieField.setText("");
                edadField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "La edad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción para ver clientes
        verClientesButton.addActionListener(e -> {
            areaDeTexto.setText("");
            areaDeTexto.append(clienteServicio.mostrarClientes());
        });

        // Acción para ver mascotas
        verMascotasButton.addActionListener(e -> {
            areaDeTexto.setText("");
            areaDeTexto.append(mascotaServicio.mostrarMascotas());
        });

        frame.setVisible(true);
    }
}