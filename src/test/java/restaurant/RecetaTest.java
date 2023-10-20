package restaurant;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restaurant.exceptions.RecetaVaciaException;

import static org.junit.jupiter.api.Assertions.fail;

public class RecetaTest {

    @BeforeAll
    public static void arrancandoLaClase() {
    }
    @Test
    @DisplayName("Test de ingredientes nulos en receta")
    public void checkIngredientesReceta() throws RecetaVaciaException {
        Receta tortilla = new Receta("Tortilla", 15, 1500);
        ProductoElaborado pe = new ProductoElaborado("Retorti", 2500);
        pe.setReceta(tortilla);
        fail("La receta no debería poder setearse vacía");
    }
}
