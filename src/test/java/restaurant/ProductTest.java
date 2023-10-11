package restaurant;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import restaurant.exceptions.EmailFormatException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductTest {
    private final Double MARGIN = 0.2;
    public ProductTest() {
    }

    @Test
    @DisplayName("Verificar Porcentaje de ganancia para productos basicos")
    public void marginSimpleProduct() {
        Producto p1 = new ProductoBasico("Lata Coca Cola", 10, 20);
        Float margenGanancia = p1.getPrecioUnitarioVenta() - p1.getPrecioUnitarioCompra();
        Float margenMinimo = p1.getPrecioUnitarioCompra() * MARGIN.floatValue();
        assertTrue(margenGanancia>=margenMinimo);
    }
    @Test
    @DisplayName("Verificar Porcentaje de ganancia para productos elaborado")
    public void marginManufacturedProduct() {
        Ingrediente huevo = new Ingrediente("huevo", "unidades", 240, 100);
        Ingrediente papa = new Ingrediente("Papa", "gramos", 20000, 25);
        Ingrediente sal = new Ingrediente("Sal", "gramos", 1000, 50);

        ItemReceta ir4 = new ItemReceta(huevo, 4);
        ItemReceta ir5 = new ItemReceta(papa, 3);
        ItemReceta ir6 = new ItemReceta(sal, 1);


        Receta tortilla = new Receta("Tortilla", 15, 1500);
        tortilla.addIngrediente(ir4);
        tortilla.addIngrediente(ir5);
        tortilla.addIngrediente(ir6);

        Float margenGanancia = tortilla.getPrecioVenta() - tortilla.calcularCosto();
        Float margenMinimo = tortilla.calcularCosto() * MARGIN.floatValue();
        assertTrue(margenGanancia>=margenMinimo);
    }
}
