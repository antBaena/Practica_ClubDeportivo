package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ClubDeportivoMatricularTest {

    private ClubDeportivo clubDeportivo;
    private String nombre;
    private int numGrupos;

    @BeforeEach
    void setUp() throws ClubException {
        nombre = "club";
        numGrupos = 12;
        clubDeportivo = new ClubDeportivo(nombre, numGrupos);
    }

    @DisplayName("El método matricular debe matricular correctamente al número de personas solicitado")
    @Test
    void Matricular_NumPersonasSobraSitio_MatriculaCorrectamente() throws ClubException {
        Grupo g;
        String codigo = "123A";
        String nombreAct = "Kizomba";
        int numMatriculas = 10;
        int numPlazas = 10;
        double tarifa = 25.0;
        g = new Grupo("123A", nombreAct, numPlazas, 0, tarifa);
        clubDeportivo.anyadirActividad(g);

        clubDeportivo.matricular(nombreAct, numPlazas / 2);
        String expected = nombre + " --> [ (" + codigo + " - " + nombreAct + " - " + tarifa + " euros - P:" + numPlazas
                + " - M:" + numMatriculas / 2 + ") ]";

        assertEquals(clubDeportivo.toString(), expected);
    }

    @DisplayName("El método matricular debe lanzar una excepción cuando la actividad no existe")
    @Test
    void Matricular_NoExisteActividad_ThrowClubException() throws ClubException {
        assertThrows(ClubException.class, () -> clubDeportivo.matricular("Kizomba", 10));
    }

    @DisplayName("El método matricular debe lanzar una excepción cuando el numero de personas es negativo")
    @Test
    void Matricular_NumPersonasNegativo_ThrowsException() throws ClubException {
        int numPersonas = -3;
        Grupo g;
        g = new Grupo("123A", "Kizomba", 1, 1, 25.0);
        clubDeportivo.anyadirActividad(g);

        assertThrows(ClubException.class, () -> {
            clubDeportivo.matricular("Kizomba", numPersonas);
        });
    }

    @DisplayName("El método matricular debe lanzar una excepción si no hay suficientes plazas en la actividad solicitada")
    @Test
    void Matricular_PlazasInsuficientes_ThrowsException() throws ClubException {
        int numPersonas = 10;
        Grupo g;
        g = new Grupo("123A", "Kizomba", 1, 1, 25.0);
        clubDeportivo.anyadirActividad(g);

        assertThrows(ClubException.class, () -> {
            clubDeportivo.matricular("Kizomba", numPersonas);
        });
    }

    @DisplayName("El método matricular debe lanzar una excepción si el numero de personas a matricular es cero")
    @Test
    void Matricular_NumPersonasCero_ThrowsException() throws ClubException {
        int numPersonas = 0;
        Grupo g;
        g = new Grupo("123A", "Kizomba", 10, 1, 25.0);
        clubDeportivo.anyadirActividad(g);

        assertThrows(ClubException.class, () -> {
            clubDeportivo.matricular("Kizomba", numPersonas);
        });
    }

    @DisplayName("El método matricular debe matricular al número de personas repartido en varios grupos de la misma actividad")
    @Test
    void Matricular_NumPersonasFraccionado_MatriculaCorrectamente() throws ClubException {
        Grupo g;
        String codigo1 = "123A";
        String codigo2 = "123B";
        String codigo3 = "123C";
        String codigo4 = "123D";
        String codigo5 = "123E";
        String nombreAct = "Kizomba";
        String nombreAct2 = "Pilates";
        int numMatriculas = 10;
        int numPlazas = 10;

        double tarifa = 25.0;
        g = new Grupo(codigo1, nombreAct2, numPlazas, 10, tarifa);
        clubDeportivo.anyadirActividad(g);
        g = new Grupo(codigo2, nombreAct, numPlazas, 10, tarifa);
        clubDeportivo.anyadirActividad(g);
        g = new Grupo(codigo3, nombreAct, numPlazas, 8, tarifa);
        clubDeportivo.anyadirActividad(g);
        g = new Grupo(codigo4, nombreAct, numPlazas, 2, tarifa);
        clubDeportivo.anyadirActividad(g);
        g = new Grupo(codigo5, nombreAct, numPlazas, 10, tarifa);
        clubDeportivo.anyadirActividad(g);
        clubDeportivo.matricular(nombreAct, numMatriculas);
        String expected = nombre + " --> [ (" + codigo1 + " - " + nombreAct2 + " - " + tarifa + " euros - P:"
                + numPlazas
                + " - M:" + numMatriculas + "), " + "(" + codigo2 + " - " + nombreAct + " - " + tarifa + " euros - P:"
                + numPlazas + " - M:" + numMatriculas + "), " + "(" + codigo3 + " - " + nombreAct + " - " + tarifa
                + " euros - P:"
                + numPlazas + " - M:" + numMatriculas + "), " + "(" + codigo4 + " - " + nombreAct + " - " + tarifa
                + " euros - P:"
                + numPlazas + " - M:" + numMatriculas + "), " + "(" + codigo5 + " - " + nombreAct + " - " + tarifa
                + " euros - P:"
                + numPlazas + " - M:" + numMatriculas + ") ]";

        assertEquals(clubDeportivo.toString(), expected);
    }

}
