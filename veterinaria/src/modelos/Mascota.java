package modelos;

public class Mascota {
    private String nombre;
    private String especie;
    private int edad;
    private String duenio;

    public Mascota(String nombre, String especie, int edad, String duenio) {
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.duenio = duenio;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public int getEdad() {
        return edad;
    }

    public String getDuenio() {
        return duenio;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setDuenio(String duenio) {
        this.duenio = duenio;
    }

    public void mostrarInfo() {
        System.out.println("🐾 Mascota: " + nombre);
        System.out.println("Especie: " + especie);
        System.out.println("Edad: " + edad + " años");
        System.out.println("Dueño: " + duenio);
    }
}
