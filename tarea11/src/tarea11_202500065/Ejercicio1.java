package tarea11_202500065;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio1 {

    // El carnet debe tener exactamente 9 digitos.
    private static final Pattern PATRON_CARNET = Pattern.compile("^\\d{4}\\d{5}$");
    // El correo debe terminar en @usac.edu.gt y no puede iniciar con punto o guion bajo.
    private static final Pattern PATRON_CORREO_USAC = Pattern.compile(
            "^[A-Za-z0-9][A-Za-z0-9._]*@usac\\.edu\\.gt$"
    );
    // Acepta 8 digitos con guion opcional justo al centro y primer digito de celular valido.
    private static final Pattern PATRON_TELEFONO = Pattern.compile("^[3-6]\\d{3}-?\\d{4}$");

    public static boolean validarCarnet(String carnet) {
        // Verifica si todo el texto cumple el patron.
        Matcher matcher = PATRON_CARNET.matcher(carnet);
        return matcher.matches();
    }

    public static boolean validarCorreoInstitucional(String correo) {
        // Valida estructura del correo institucional.
        Matcher matcher = PATRON_CORREO_USAC.matcher(correo);
        return matcher.matches();
    }

    public static boolean validarTelefonoGuatemalteco(String telefono) {
        // Acepta formato XXXXXXXX o XXXX-XXXX.
        Matcher matcher = PATRON_TELEFONO.matcher(telefono);
        return matcher.matches();
    }

    public static void main(String[] args) {
        // Casos de prueba solicitados para cada metodo.
        System.out.println("Pruebas de carnet universitario:");
        probarValidacion("202300123", validarCarnet("202300123"));
        probarValidacion("202512345", validarCarnet("202512345"));
        probarValidacion("20230012", validarCarnet("20230012"));
        probarValidacion("2023ABC12", validarCarnet("2023ABC12"));

        System.out.println();
        System.out.println("Pruebas de correo institucional:");
        probarValidacion("juan.perez@usac.edu.gt", validarCorreoInstitucional("juan.perez@usac.edu.gt"));
        probarValidacion("carla_001@usac.edu.gt", validarCorreoInstitucional("carla_001@usac.edu.gt"));
        probarValidacion("juan@gmail.com", validarCorreoInstitucional("juan@gmail.com"));
        probarValidacion(".juan@usac.edu.gt", validarCorreoInstitucional(".juan@usac.edu.gt"));

        System.out.println();
        System.out.println("Pruebas de telefono guatemalteco:");
        probarValidacion("5555-1234", validarTelefonoGuatemalteco("5555-1234"));
        probarValidacion("30001234", validarTelefonoGuatemalteco("30001234"));
        probarValidacion("1234-5678", validarTelefonoGuatemalteco("1234-5678"));
        probarValidacion("555-1234", validarTelefonoGuatemalteco("555-1234"));
    }

    private static void probarValidacion(String valor, boolean esValido) {
        // Muestra el resultado de cada prueba.
        System.out.println(valor + " -> " + (esValido ? "Valido" : "Invalido"));
    }
}
