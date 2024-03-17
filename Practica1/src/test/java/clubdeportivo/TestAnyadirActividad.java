package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class TestAnyadirActividad {
    private ClubDeportivo clubDeportivo;
    private String nombre;
    private int numGrupos;

    @BeforeEach
    void setUp() throws ClubException {
        nombre = "club";
        numGrupos = 12;
        clubDeportivo = new ClubDeportivo(nombre, numGrupos);
    }

    @DisplayName("El metodo anyadirActividad añade una actividad si los parámetros son correctos")
    @Test
    void AnyadirActividad_ParametrosCorrectos_AnyadeActividad() throws ClubException {
        String[] datos = { "123A", "Kizomba", "10", "10", "25.0" };
        String expected = nombre + " --> [ (" + datos[0] + " - " + datos[1] + " - " + datos[4] + " euros " + "- P:"
                + datos[2] + " - M:" + datos[3] + ") ]";

        clubDeportivo.anyadirActividad(datos);

        assertEquals(clubDeportivo.toString(), expected);
    }

    @DisplayName("El método anyadirActividad debe añadir un grupo al club si el parametro es un Grupo")
    @Test
    void AnyadirActividad_ParametrosCorrectos_ThrowsClubException() throws ClubException {
        String codigo = "456B";
        String actividad = "Pilates";
        int numGrupos = 8;
        int numMatriculados = 5;
        double tarifa = 50.0;

        Grupo g1 = new Grupo(codigo, actividad, numGrupos, numMatriculados, tarifa);
        clubDeportivo.anyadirActividad(g1);

        String expected = nombre + " --> [ " + g1.toString() + " ]";

        assertEquals(clubDeportivo.toString(), expected);
    }

    @DisplayName("El método anyadirActividad debe lanzar una excepción si los parámetros no son válidos")
    @Test
    void AnyadirActividad_ParametrosInvalidos_ThrowsClubException() throws ClubException {
        String[] datosErroneos = { "a", "a", "a", "a", "a" };

        assertThrows(ClubException.class, () -> {
            clubDeportivo.anyadirActividad(datosErroneos);
        });
    }

    @DisplayName("El método anyadirActividad debe lanzar una excepción si el número de parámetros es inválido")
    @Test
    void AnyadirActividad_NumeroDeParametrosInvalido_ThrowsClubException() {
        String[] datosErroneos = { "a", "a", "a", "a" };

        assertThrows(ClubException.class, () -> {
            clubDeportivo.anyadirActividad(datosErroneos);
        });
    }

    @DisplayName("El método anyadirActividad debe lanzar una excepción si el grupo es nulo")
    @Test
    void AnyadirActividad_GrupoNulo_ThrowsClubException() {
        Grupo g1 = null;

        assertThrows(ClubException.class, () -> {
            clubDeportivo.anyadirActividad(g1);
        });
    }

    @DisplayName("El método anyadirActividad debe actualizar el número de plazas si el grupo a añadir ya existe")
    @Test
    public void AnyadirActividad_GrupoExistente_ActualizaLasPlazasDelGrupo() throws ClubException {
        String codigo = "456B";
        String actividad = "Pilates";
        int numGrupos = 8;
        int numMatriculados = 5;
        double tarifa = 50.0;
        Grupo g1 = new Grupo(codigo, actividad, numGrupos, numMatriculados, tarifa);
        int numGruposNuevo = 8;

        clubDeportivo.anyadirActividad(g1);
        g1.actualizarPlazas(numGruposNuevo);
        clubDeportivo.anyadirActividad(g1);

        String expected = nombre + " --> [ " + g1.toString() + " ]";

        assertEquals(clubDeportivo.toString(), expected);
    }

    @DisplayName("El método anyadirActividad debe lanzar una excepción si el grupo es nulo")
    @Test
    void AnyadirActividad_MasGruposDeLosDisponibles_ThrowsClubException() throws ClubException {
        Grupo[] grupos = new Grupo[numGrupos + 1];
        int numPlazas = 10;
        int numMatriculados = 5;
        double tarifa = 25.0;

        for (int i = 0; i < numGrupos + 1; i++) {
            grupos[i] = new Grupo("123" + i, "actividad" + i, numPlazas, numMatriculados, tarifa);
        }

        for (int i = 0; i < numGrupos; i++) {
            clubDeportivo.anyadirActividad(grupos[i]);
        }

        assertThrows(ClubException.class, () -> clubDeportivo.anyadirActividad(grupos[numGrupos]));
    }
}
