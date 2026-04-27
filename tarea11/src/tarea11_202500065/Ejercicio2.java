package tarea11_202500065;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio2 {

    // Texto base dado en el enunciado.
    private static final String TEXTO_BASE =
            "UNIVERSIDAD DE SAN CARLOS DE GUATEMALA\n"
            + "Facultad de Ingenieria - Boletin Informativo 2024-04-25\n"
            + "\n"
            + "Actividades programadas:\n"
            + "- Conferencia el 2024-05-01 a las 10:30 en el Edificio T3\n"
            + "- Examen parcial el 2024-05-15 a las 08:00 en el Edificio S11\n"
            + "- Defensa de proyecto el 2024-06-10 a las 14:30 en el Edificio T7\n"
            + "\n"
            + "Contactos del departamento:\n"
            + "- Coordinador:  coord.ipc1@ingenieria.usac.edu.gt   Tel: 2418-8000\n"
            + "- Auxiliar 1:   aux01_ipc1@ingenieria.usac.edu.gt   Tel: 5555-1234\n"
            + "- Auxiliar 2:   aux02.ipc1@gmail.com                Tel: 4321-9876";

    // Captura anio, mes y dia por separado para imprimirlos como pide el enunciado.
    private static final Pattern PATRON_FECHA = Pattern.compile("\\b(\\d{4})-(\\d{2})-(\\d{2})\\b");
    // Encuentra correos con formatos comunes dentro del texto.
    private static final Pattern PATRON_CORREO = Pattern.compile(
            "\\b[A-Za-z0-9][A-Za-z0-9._-]*@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b"
    );
    // Solo censura telefonos con guion porque asi lo pide el ejercicio.
    private static final Pattern PATRON_TELEFONO_CON_GUION = Pattern.compile("\\b\\d{4}-\\d{4}\\b");

    public static void extraerFechas(String texto) {
        // Busca todas las fechas presentes en el texto.
        Matcher matcher = PATRON_FECHA.matcher(texto);
        boolean encontro = false;

        while (matcher.find()) {
            encontro = true;
            System.out.println(
                    "Anio: " + matcher.group(1)
                    + " | Mes: " + matcher.group(2)
                    + " | Dia: " + matcher.group(3)
            );
        }

        if (!encontro) {
            System.out.println("No se encontraron fechas.");
        }
    }

    public static void extraerCorreos(String texto) {
        // Recorre cada coincidencia de correo encontrada.
        Matcher matcher = PATRON_CORREO.matcher(texto);
        boolean encontro = false;

        while (matcher.find()) {
            encontro = true;
            System.out.println(matcher.group());
        }

        if (!encontro) {
            System.out.println("No se encontraron correos.");
        }
    }

    public static String censurarDatosSensibles(String texto) {
        // Primero reemplaza correos y luego telefonos.
        Matcher matcherCorreos = PATRON_CORREO.matcher(texto);
        String textoSinCorreos = matcherCorreos.replaceAll("[CORREO]");

        Matcher matcherTelefonos = PATRON_TELEFONO_CON_GUION.matcher(textoSinCorreos);
        return matcherTelefonos.replaceAll("[TEL]");
    }

    public static void main(String[] args) {
        // Pruebas para extraer fechas.
        System.out.println("Pruebas de extraer fechas:");
        System.out.println("Caso 1:");
        extraerFechas(TEXTO_BASE);
        System.out.println();
        System.out.println("Caso 2:");
        extraerFechas("La reunion sera el 2025-01-30 y la entrega final el 2025-02-05.");
        System.out.println();
        System.out.println("Caso 3:");
        extraerFechas("Este texto no tiene fechas validas.");

        System.out.println();
        // Pruebas para extraer correos.
        System.out.println("Pruebas de extraer correos:");
        System.out.println("Caso 1:");
        extraerCorreos(TEXTO_BASE);
        System.out.println();
        System.out.println("Caso 2:");
        extraerCorreos("Escriba a pruebas@correo.com o soporte_01@usac.edu.gt.");
        System.out.println();
        System.out.println("Caso 3:");
        extraerCorreos("No hay correos en esta linea.");

        System.out.println();
        // Pruebas para censurar datos sensibles.
        System.out.println("Pruebas de censurar datos sensibles:");
        probarCensura("Llama a 5555-1234 o escribe a juan@usac.edu.gt para mas info.");
        probarCensura(TEXTO_BASE);
        probarCensura("Contacto alterno: maria.lopez@gmail.com y 2418-8000.");
    }

    private static void probarCensura(String texto) {
        // Imprime entrada y salida para comparar el reemplazo.
        System.out.println("Entrada: " + texto);
        System.out.println("Salida:  " + censurarDatosSensibles(texto));
        System.out.println();
    }
}
