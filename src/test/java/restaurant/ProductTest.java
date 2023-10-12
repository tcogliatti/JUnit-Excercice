package restaurant;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductTest {
    private final Double MARGIN = 0.2;
    public static List<Producto> productos = new ArrayList<>();;

    public ProductTest() {

    }

    @BeforeAll
    static void InitClass() {
        Ingrediente huevo = new Ingrediente("huevo", "unidades", 240, 100);
        Ingrediente harina = new Ingrediente("harina", "gramos", 10000, 40);
        Ingrediente papa = new Ingrediente("Papa", "gramos", 20000, 25);
        Ingrediente sal = new Ingrediente("Sal", "gramos", 1000, 50);
        Ingrediente carnePicada = new Ingrediente("Carne picada", "grmos", 10000, 250);
        Ingrediente panHamburguesa = new Ingrediente("Pan de Hamburguesa", "unidades", 100, 150);
        Producto gaseosaCola = new ProductoBasico("Coca Cola", 10, 20);
        Producto agua = new ProductoBasico("Agua", 10, 22);
        Producto gaseosaSprite = new ProductoBasico("Lata Sprite", 10, 22);

        ItemReceta ir1 = new ItemReceta(huevo, 1);
        ItemReceta ir2 = new ItemReceta(carnePicada, 100);
        ItemReceta ir3 = new ItemReceta(sal, 20);
        ItemReceta ir4 = new ItemReceta(huevo, 4);
        ItemReceta ir5 = new ItemReceta(papa, 3);
        ItemReceta ir6 = new ItemReceta(panHamburguesa, 1);

        Receta tortilla = new Receta("Tortilla", 15, 1500);
        tortilla.addIngrediente(ir4);
        tortilla.addIngrediente(ir5);

        Receta hamburguesa = new Receta("hamburguesa grande", 5, 1500);
        hamburguesa.addIngrediente(ir2);
        hamburguesa.addIngrediente(ir1);
        hamburguesa.addIngrediente(ir3);
        hamburguesa.addIngrediente(ir6);

        Receta papasFritas = new Receta("papas fritas", 7, 850);
        papasFritas.addIngrediente(ir5);
        papasFritas.addIngrediente(ir3);

        // BÁSICOS
        ProductTest.productos.add(gaseosaCola);
        ProductTest.productos.add(gaseosaSprite);
        ProductTest.productos.add(agua);

        // ELAVORADOS
        Producto p3 = new ProductoElaborado("Retorti", 2500);
        p3.setPrecioUnitarioCompra(tortilla.calcularCosto());
        ((ProductoElaborado) p3).setReceta(tortilla);
        ProductTest.productos.add(p3);

        Producto p4 = new ProductoElaborado("BIG O", 50000);
        p4.setPrecioUnitarioCompra(hamburguesa.calcularCosto());
        ((ProductoElaborado) p4).setReceta(hamburguesa);
        ProductTest.productos.add(p4);

        Producto p5 = new ProductoElaborado("PATATAS", 2000);
        p5.setPrecioUnitarioCompra(papasFritas.calcularCosto());
        ((ProductoElaborado) p5).setReceta(papasFritas);
        ProductTest.productos.add(p5);
    }

    @Test
    @DisplayName("Verificar Porcentaje de ganancia para productos basicos")
    public void marginSimpleProduct() {
        Producto p1 = new ProductoBasico("Lata Coca Cola", 10, 20);
        Float margenGanancia = p1.getPrecioUnitarioVenta() - p1.getPrecioUnitarioCompra();
        Float margenMinimo = p1.getPrecioUnitarioCompra() * MARGIN.floatValue();
        assertTrue(margenGanancia >= margenMinimo);
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

        Producto p = new ProductoElaborado("Tortilla", 1500);
        ((ProductoElaborado) p).setReceta(tortilla);

        Float margenGanancia = p.getPrecioUnitarioVenta() - tortilla.calcularCosto();
        Float margenMinimo = tortilla.calcularCosto() * MARGIN.floatValue();
        p.setPrecioUnitarioCompra(tortilla.calcularCosto());
        System.out.println("prec venta: " + p.getPrecioUnitarioVenta() + " - prec costo" + tortilla.calcularCosto());
        System.out.println("prec venta: " + p.getPrecioUnitarioVenta() + " - prec costo" + p.getPrecioUnitarioCompra());
        assertTrue(margenGanancia >= margenMinimo);
    }

    @TestFactory
    Stream<DynamicTest> dynamicTestMargenGanancia() {
        return ProductTest.productos.stream()
                .map(prod -> DynamicTest.dynamicTest("Testing producto: " + prod.getNombre(), () -> {
                    Float margenGanancia = prod.getPrecioUnitarioVenta() - prod.getPrecioUnitarioCompra();
                    Float margenMinimo = prod.getPrecioUnitarioCompra() * MARGIN.floatValue();
                    assertTrue(margenGanancia >= margenMinimo);

                }));
    }
}
