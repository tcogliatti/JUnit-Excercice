package restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import restaurant.Almacen;
import restaurant.Ingrediente;

class AlmacenTest {
    Ingrediente defaultIngrediente;
    static List<Ingrediente> ingATestear;

    public AlmacenTest() {
    }
    @BeforeAll
    static void InitClass() {
        ingATestear = new ArrayList<Ingrediente>();
        ingATestear.add(new Ingrediente("harina", "kg", 10, 450));
        ingATestear.add(new Ingrediente("aceilte", "ltd", 15, 800));
        ingATestear.add(new Ingrediente("papa", "kg", 170, 500));
        ingATestear.add(new Ingrediente("huevo", "un", 150, 100));
        ingATestear.add(new Ingrediente("fideos", "kg", 10, 900));
    }

    @BeforeEach
    void setUp() throws Exception {
        int cualTomamos = (int) (Math.random() * ingATestear.size());
        defaultIngrediente = AlmacenTest.ingATestear.get(cualTomamos);
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @RepeatedTest(15)
    void testIngresarConsumibles() {

        System.out.println(defaultIngrediente.toString());
        int cantidadInicial = defaultIngrediente.getStock();
        int cantidadAgregada = (int) (Math.random() * 100);
        /** Me aseguro que el stock se esta actualizando bien*/
        Almacen.IngresarConsumibles(defaultIngrediente, cantidadAgregada);
        assertEquals(cantidadInicial + cantidadAgregada, defaultIngrediente.getStock());
    }

    @Test
    void testExtraerConsumibles() {
        fail("Not yet implemented");
    }

    @TestFactory
    Stream<DynamicTest> dynamicTestIngredientes() {
        return AlmacenTest.ingATestear.stream()
                .map(dom -> DynamicTest.dynamicTest("Testing: " + dom.getNombre(), () -> {
                    int cantidadInicial = dom.getStock();
                    int cantidadAgregada = (int) (Math.random() * 100);
                    Almacen.IngresarConsumibles(dom, cantidadAgregada);
                    assertEquals(cantidadAgregada + cantidadInicial, dom.getStock());
                }));
    }
}
