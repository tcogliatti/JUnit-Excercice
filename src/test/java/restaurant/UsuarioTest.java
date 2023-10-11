package restaurant;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import restaurant.exceptions.EmailFormatException;
import restaurant.exceptions.SinSaldoException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {
    private Map<String, Usuario> usuarios = new HashMap<>();
    private Usuario [] users;

    public UsuarioTest() {
        users = new Usuario[4];
        try {
            users[0] = new Usuario("Homero Simpson", 0, 350, "homerojsimpson@springfield.com");
            users[1] = new Usuario("Barney Gomez", 0, 0, "el14gomez@springfield.com");
            users[2] = new Usuario("Edna Krabappel", 1, 0, "ednak@springfield.com");
            users[3] = new Usuario("Homero", 0, 0, "homerojsimpson@springfield.com");
        } catch (EmailFormatException e) {
            throw new RuntimeException(e);
        }

    }
//    @BeforeClass
    @Test
    @DisplayName("Verificar formato de correo")
    public void verificarMail() throws EmailFormatException {
        String email = "pepeasdsacarlitos.com";
        /*
            Patron de mail que contenga:
                1) cadena de caracteres aA-zZ, numeros 0-9 y caracteres especiales "_", "-" y "."
                2) luego de la cadena de caractreres que exista un caracter arroba"@"
                3) luego del caracter arroba, que exista al menos un punto "."
        */
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email); // verifica que el email coincida con el patron

        assertFalse(matcher.matches(), "Para este test el correo electrónico no debe cumplir con formato esperado");

//        try{
            Usuario u = new Usuario("Homero Simpson", 0, 350, email);
            fail("Se esperaba una falla de registro de usuario por error en el formato de correo electronico para el mail: "+email);
//        } catch (Exception e){
//            throw new  EmailFormatException("el formato del correo es incorrecto por eso sucedió un error al registrar el usuario");
//        }

    }

    public Usuario elegirUnoRandom() throws Exception {
        Random generadorAleatorios = new Random();
        int numeroAleatorio = generadorAleatorios.nextInt(this.users.length);
        return users[numeroAleatorio];
    }

    @Test
    @DisplayName("Verifica que un pedido descuenta el saldo del usuario")
    public void pedidoDesuentaSaldo(){
        Usuario u = null;
        try {
            u = new Usuario("Homero Simpson", 0, 350, "homerojsimpson@springfield.com");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Float saldoInicial = u.getSaldo();

        Ingrediente huevo = new Ingrediente("huevo", "unidades", 240, 100);
        Ingrediente papa = new Ingrediente("Papa", "gramos", 20000, 25);
        Ingrediente sal = new Ingrediente("Sal", "gramos", 1000, 50);
        Ingrediente carnePicada = new Ingrediente("Carne picada", "grmos", 10000, 250);
        Ingrediente panHamburguesa = new Ingrediente("Pan de Hamburguesa", "unidades", 100, 150);

        ItemReceta ir3 = new ItemReceta(sal, 20);
        ItemReceta ir4 = new ItemReceta(huevo, 4);
        ItemReceta ir5 = new ItemReceta(papa, 3);

        Receta tortilla = new Receta("Tortilla", 15, 1500);
        tortilla.addIngrediente(ir4);
        tortilla.addIngrediente(ir5);

        Receta papasFritas = new Receta("papas fritas", 7, 850);
        papasFritas.addIngrediente(ir5);
        papasFritas.addIngrediente(ir3);

        Producto p1 = new ProductoBasico("Lata Coca Cola", 10, 20);
        Producto p3 = new ProductoElaborado("Retorti", 120);
        ((ProductoElaborado) p3).setReceta(tortilla);
        ((ProductoElaborado) p3).setReceta(papasFritas);

        Pedido p = new Pedido();
        p.setUsuario(u);
        p.agregarItem(new ItemPedido(1, p1));
        p.agregarItem(new ItemPedido(1, p3));

        try {
            p.solicitarPedido();
        } catch (SinSaldoException e) {
            throw new RuntimeException(e);
        }
        assertTrue(saldoInicial > u.getSaldo() && (saldoInicial-u.getSaldo() == p.totalPedido()));
    }

}
