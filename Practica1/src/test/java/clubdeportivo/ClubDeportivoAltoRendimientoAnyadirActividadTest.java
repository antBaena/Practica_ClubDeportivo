/*
 * @author Antonio Cañete Baena
 * 
 * @author Antonio Blas Moral Sánchez
 * 
 */
package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ClubDeportivoAltoRendimientoAnyadirActividadTest {
    ClubDeportivoAltoRendimiento clubDeportivoAltoRendimiento;
    double incremento;
    int maximo;
    String nombre;
    int numGrupos;

    @BeforeEach
    void setUp() throws ClubException {
        incremento = 100;
        maximo = 10;
        nombre = "test";
        numGrupos = 12;
        clubDeportivoAltoRendimiento = new ClubDeportivoAltoRendimiento(nombre, numGrupos, maximo, incremento);
    }

    @DisplayName("El metodo anyadirActividad añade una actividad si los parámetros son correctos")
    @Test
    void AnyadirActividad_ParametrosCorrectos_AnyadeActividad() throws ClubException {
        String[] datos = { "123A", "Kizomba", "10", "10", "25.0" };

        String expected = nombre + " --> [ (" + datos[0] + " - " + datos[1] + " - " + datos[4] + " euros " + "- P:"
                + datos[2] + " - M:" + datos[3] + ") ]";

        clubDeportivoAltoRendimiento.anyadirActividad(datos);

        assertEquals(clubDeportivoAltoRendimiento.toString(), expected);
    }

    @DisplayName("El método anyadirActividad debe lanzar una excepción si los parámetros no son válidos")
    @Test
    void AnyadirActividad_ParametrosInvalidos_ThrowsClubException() throws ClubException {
        String[] datosErroneos = { "a", "a", "a", "a", "a" };

        assertThrows(ClubException.class, () -> {
            clubDeportivoAltoRendimiento.anyadirActividad(datosErroneos);
        });
    }

    @DisplayName("El método anyadirActividad debe lanzar una excepción si el número de parámetros es inválido")
    @Test
    void AnyadirActividad_NumeroDeParametrosInvalido_ThrowsClubException() {
        String[] datosErroneos = { "a", "a", "a", "a" };

        assertThrows(ClubException.class, () -> {
            clubDeportivoAltoRendimiento.anyadirActividad(datosErroneos);
        });
    }

    @DisplayName("El método anyadirActividad debe añadir la actividad con el número de plazas maximo si el grupo tiene mas plazas que el maximo")
    @Test
    public void AnyadirActividad_MasPlazasQueMaximo_ModificaLasPlazasDelGrupo() throws ClubException {
        int plazas = maximo + 1;
        String[] datos = { "123A", "Kizomba", String.valueOf(plazas), "9", "25.0" };

        String expected = nombre + " --> [ (" + datos[0] + " - " + datos[1] + " - " + datos[4] + " euros " + "- P:"
                + maximo + " - M:" + datos[3] + ") ]";

        clubDeportivoAltoRendimiento.anyadirActividad(datos);

        assertEquals(clubDeportivoAltoRendimiento.toString(), expected);
    }

}
