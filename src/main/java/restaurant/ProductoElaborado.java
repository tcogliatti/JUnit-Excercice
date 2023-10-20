package restaurant;

import restaurant.exceptions.RecetaVaciaException;

import java.util.List;

public class ProductoElaborado extends Producto {
    String nombreComercial;
    int tiempoDeCoccion;
    Receta receta;

    public ProductoElaborado(String nombre, float precioUnitarioVenta) {
        super(nombre, 0, precioUnitarioVenta);
        // TODO Auto-generated constructor stub
    }
    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) throws RecetaVaciaException {
        if (!receta.getIngredientes().isEmpty())
            this.receta = receta;
        else
            throw new RecetaVaciaException("Error la receta no tiene ingredientes");
    }
    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public int getTiempoDeCoccion() {
        return tiempoDeCoccion;
    }

    public void setTiempoDeCoccion(int tiempoDeCoccion) {
        this.tiempoDeCoccion = tiempoDeCoccion;
    }

    @Override
    public void despachar(int cantidad) {
        Receta r = this.getReceta();
        for (int i = 0; i < r.getIngredientes().size(); i++) {
            ItemReceta ingredienteActual = r.getIngredientes().get(i);
            Almacen.ExtraerConsumibles(ingredienteActual.getIngrediente(), ingredienteActual.getCantidad() * cantidad);
        }

    }

    public String toString() {
        return nombre;
    }

}
