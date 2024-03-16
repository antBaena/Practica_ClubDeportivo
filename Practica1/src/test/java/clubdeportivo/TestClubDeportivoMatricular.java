package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class TestClubDeportivoMatricular {

    private ClubDeportivo clubDeportivo;
    private String nombre;
    private int numGrupos;

    @BeforeEach
    void setUp() throws ClubException {
        nombre = "club";
        numGrupos = 12;
        clubDeportivo = new ClubDeportivo(nombre, numGrupos);
    }

    @DisplayName("El método matricular debe lanzar una exe un grupo al club si el parametro es un Grupo")
    @Test
    void Matricular_ParametrosCorrectos_NotThrowClubException() throws ClubException {
        int numPersonas = 5;
        Grupo g;
        g = new Grupo("123A", "Kizomba", 10, 5, 25.0);
        clubDeportivo.anyadirActividad(g);

        assertAll(() -> clubDeportivo.matricular("Kizomba", numPersonas));
    }

    @DisplayName("El método matricular debe lanzar una exe un grupo al club si el parametro es un Grupo")
    @Test
    void Matricular_NoExisteActividad_ThrowClubException() throws ClubException {
        assertThrows(ClubException.class, () -> clubDeportivo.matricular("Kizomba", 10));
    }

    @DisplayName("El método matricular debe lanzar una exe un grupo al club si el numero de personas es negativo")
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

    @DisplayName("El método matricular debe lanzar una exe un grupo al club si el parametro es un Grupo")
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

    // TODO test donde npersonas = 0
    @DisplayName("El método matricular debe lanzar una exe un grupo al club si el numero de personas es negativo")
    @Test
    void Matricular_NumPersonasCero_ThrowsException() throws ClubException {
        int numPersonas = 30;
        Grupo g;
        g = new Grupo("123A", "Kizomba", 1, 1, 25.0);
        clubDeportivo.anyadirActividad(g);

        assertThrows(ClubException.class, () -> {
            clubDeportivo.matricular("Kizomba", numPersonas);
        });
    }

    @DisplayName("El método matricular debe lanzar una exe un grupo al club si el parametro es un Grupo")
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

    @DisplayName("El método matricular debe lanzar una exe un grupo al club si el parametro es un Grupo")
    @Test
    void Matricular_NumPersonasFraccionado_MatriculaCorrectamente() throws ClubException {
        Grupo g;
        String codigo1 = "123A";
        String codigo2 = "123B";
        String nombreAct = "Kizomba";

        int numMatriculas = 10;
        int numPlazas = 10;

        double tarifa = 25.0;
        g = new Grupo(codigo1, nombreAct, numPlazas, 8, tarifa);
        clubDeportivo.anyadirActividad(g);
        g = new Grupo(codigo2, nombreAct, numPlazas, 2, tarifa);
        clubDeportivo.anyadirActividad(g);
        clubDeportivo.matricular(nombreAct, numMatriculas);
        String expected = nombre + " --> [ (" + codigo1 + " - " + nombreAct + " - " + tarifa + " euros - P:" + numPlazas
                + " - M:" + numMatriculas + "), " + "(" + codigo2 + " - " + nombreAct + " - " + tarifa + " euros - P:"
                + numPlazas + " - M:" + numMatriculas + ") ]";

        assertEquals(clubDeportivo.toString(), expected);
    }

    @DisplayName("El método matricular debe lanzar una exe un grupo al club si el parametro es un Grupo")
    @Test
    void Matricular_a_MatriculaCorrectamente() throws ClubException {
        Grupo g;
        String codigo1 = "123A";
        String codigo2 = "123B";
        String nombreAct1 = "Kizomba";
        String nombreAct2 = "Pilates";
        int numMatriculas = 10;
        int numPlazas = 10;

        double tarifa = 25.0;
        g = new Grupo(codigo1, nombreAct1, numPlazas, 0, tarifa);
        clubDeportivo.anyadirActividad(g);
        g = new Grupo(codigo2, nombreAct2, numPlazas, 0, tarifa);
        clubDeportivo.anyadirActividad(g);
        clubDeportivo.matricular(nombreAct2, numMatriculas);
        String expected = nombre + " --> [ (" + codigo1 + " - " + nombreAct1 + " - " + tarifa + " euros - P:"
                + numPlazas
                + " - M:" + 0 + "), " + "(" + codigo2 + " - " + nombreAct2 + " - " + tarifa + " euros - P:"
                + numPlazas + " - M:" + numMatriculas + ") ]";

        assertEquals(clubDeportivo.toString(), expected);
    }

    // TODO varias actividades, minimo una llena
    @DisplayName("El método anyadirActividad debe lanzar una excepción si el grupo es nulo")
    @Test
    void Matricular_VariosGruposConAlgunoLleno_MatriculaCorrectamente() throws ClubException {
        Grupo g1;
        Grupo g2;
        String codigo1 = "123A";
        String codigo2 = "123B";
        String nombreAct1 = "Kizomba";
        String nombreAct2 = "Kizomba";
        int numMatriculas = 10;
        int numPlazas = 10;

        double tarifa = 25.0;
        g1 = new Grupo(codigo1, nombreAct1, numPlazas, 10, tarifa);
        clubDeportivo.anyadirActividad(g1);
        g2 = new Grupo(codigo2, nombreAct2, numPlazas, 0, tarifa);
        clubDeportivo.anyadirActividad(g2);
        clubDeportivo.matricular(nombreAct2, 1);
        String expected = nombre + " --> [ (" + codigo1 + " - " + nombreAct1 + " - " + tarifa + " euros - P:"
                + numPlazas
                + " - M:" + numMatriculas + "), " + "(" + codigo2 + " - " + nombreAct2 + " - " + tarifa + " euros - P:"
                + numPlazas + " - M:" + numMatriculas + ") ]";

        assertEquals(clubDeportivo.toString(), expected);
    }
}
