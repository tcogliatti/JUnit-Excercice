package elecciones;


import java.util.Objects;

public class Persona {
    String dni;
    String nombre;
    String fechaNacimiento;
    int edad;
    boolean habilitadoParaVotar;

    public Persona(String nombre, String dni, String fechaNacimiento, int edad, boolean habilitadoParaVotar) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.habilitadoParaVotar = habilitadoParaVotar;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isHabilitadoParaVotar() {
        return habilitadoParaVotar;
    }

    public void setHabilitadoParaVotar(boolean habilitadoParaVotar) {
        this.habilitadoParaVotar = habilitadoParaVotar;
    }

    public String getDNI() {
        return dni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona persona)) return false;
        return getEdad() == persona.getEdad() && isHabilitadoParaVotar() == persona.isHabilitadoParaVotar() && Objects.equals(dni, persona.dni) && Objects.equals(getNombre(), persona.getNombre()) && Objects.equals(getFechaNacimiento(), persona.getFechaNacimiento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, getNombre(), getFechaNacimiento(), getEdad(), isHabilitadoParaVotar());
    }
}
