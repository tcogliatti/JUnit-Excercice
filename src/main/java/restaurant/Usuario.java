package restaurant;

import restaurant.exceptions.EmailFormatException;
import restaurant.exceptions.SinSaldoException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario {
    public static ArrayList<Usuario> users = new ArrayList<>();
    static int nextID;
    int id;
    String nombreYApellido;
    int tipoUsuario;
    float saldoCuenta;
    Date ultimaRecarga;
    String email;

    public Usuario(String nombreYApellido, int tipoUsuario, float saldoCuenta, String email) throws EmailFormatException {
        super();
        if(!checkMailFormat(email))
            throw new EmailFormatException("Error de formato de correo electrónico");
        this.email = email;
        this.nombreYApellido = nombreYApellido;
        this.tipoUsuario = tipoUsuario;
        this.saldoCuenta = saldoCuenta;
    }

    public void cargarCredito(float cuanto) {
        saldoCuenta += cuanto;

    }

    float descontarSaldo(float cuanto) throws SinSaldoException {
        if ((saldoCuenta - cuanto) < 0) throw new SinSaldoException();
        else
            saldoCuenta -= cuanto;
        return saldoCuenta;
    }
    float getSaldo() {
        return saldoCuenta;
    }

    public String getEmail() {
        return email;
    }
    private boolean checkMailFormat(String mail){
         /*
            Patron de mail que contenga:
                1) cadena de caracteres aA-zZ, numeros 0-9 y caracteres especiales "_", "-" y "."
                2) luego de la cadena de caractreres que exista un caracter arroba"@"
                3) luego del caracter arroba, que exista al menos un punto "."
        */
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mail); // verifica que el email coincida con el patron

        return matcher.matches();
    }
}
