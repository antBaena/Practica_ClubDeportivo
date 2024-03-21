package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class GrupoTest {

    private Grupo g1;
    private String codigo;
    private String actividad;
    private int nplazas;
    private int nmatriculados;
    private double tarifa;

    @BeforeEach
    void setUp() throws ClubException {
        codigo = "456B";
        actividad = "Pilates";
        nplazas = 8;
        nmatriculados = 5;
        tarifa = 50.0;

        g1 = new Grupo(codigo, actividad, nplazas, nmatriculados, tarifa);
    }

    @DisplayName("El metodo plazasLibres debe devolver las plazas totales menos el número de matriculados")
    @Test
    public void PlazasLibres_ReturnPlazasLibres() {
        int plazasLibres = nplazas - nmatriculados;
        assertEquals(g1.plazasLibres(), plazasLibres);
    }

    @DisplayName("El metodo actualizarPlazas debe actualizar el numero de plazas si el valor introducido es corecto")
    @Test
    public void ActualizarPlazas_NumPlazasValido_ActualizaElNumPlazas() throws ClubException {

        int numPlazas = 10;

        g1.actualizarPlazas(numPlazas);

        assertEquals(g1.getPlazas(), numPlazas);
    }

    @DisplayName("El metodo actualizar plazas debe lanzar una excepción si el número de plazas a actualizar es menor que el número de matriculados")
    @Test
    public void ActualizarPlazas_NumPlazasMenorANumMatriculados_ThrowsClubException() {

        int numPlazas = nmatriculados - 1;

        assertThrows(ClubException.class, () -> g1.actualizarPlazas(numPlazas));
    }

    @DisplayName("El metodo actualizarPlazas debe lanzar una excepcion si el numero de plazas a actualizar es negativo")
    @Test
    public void ActualizarPlazas_ValorNegativo_ThrowsException() {

        int numPlazas = -1;

        assertThrows(ClubException.class, () -> {
            g1.actualizarPlazas(numPlazas);
        });

    }

    @DisplayName("El metodo matricular debe actualizar el numero de matriculados  si los parametros son correctos")
    @Test
    public void Matricular_NumPlazasValido_ActualizaNumMatriculados() throws ClubException {

        int numPersonas = 3;
        int numMatriculados = g1.getMatriculados();

        g1.matricular(numPersonas);

        assertEquals(g1.getMatriculados(), numMatriculados + numPersonas);
    }

    @DisplayName("El metodo matricular debe actualizar el numero de plazas libres si los parametros son correctos")
    @Test
    public void Matricular_NumPlazasValido_ActualizaNumPlazasLibres() throws ClubException {

        int numPersonas = 3;
        int plazasLibres = g1.plazasLibres();

        g1.matricular(numPersonas);

        assertEquals(g1.plazasLibres(), plazasLibres - numPersonas);
    }

    @DisplayName("El metodo matricular debe lanzar una excepcion si el numero de personas es negativo")
    @Test
    public void matricular_NumPersonasNegativo_ThrowsException() {

        int numPersonas = -1;

        assertThrows(ClubException.class, () -> g1.matricular(numPersonas));
    }

    @DisplayName("El metodo matricular debe lanzar una excepcion si el numero de personas es menor a el numero de plazas libres")
    @Test
    public void matricular_NumPersonasMenorAPlazasLibres_ThrowsException() {

        int numPersonas = g1.getPlazas() + 1;

        assertThrows(ClubException.class, () -> g1.matricular(numPersonas));
    }

    @DisplayName("El metodo toString debe devolver un string con todos los datos del grupo")
    @Test
    public void ToString__ReturnValorCorrecto() {
        String expected = "(" + codigo + " - " + actividad + " - " + tarifa + " euros " + "- P:" + nplazas + " - M:"
                + nmatriculados + ")";

        assertEquals(g1.toString(), expected);
    }

    @DisplayName("El metodo equals debe devolver un boolean true cuando las instancias son iguales")
    @Test
    public void Equals_ParametroEsIgual_ReturnTrue() throws ClubException {
        Grupo g2 = new Grupo(codigo, actividad, nplazas, nmatriculados, tarifa);

        assertTrue(g1.equals(g2));
    }

    @DisplayName("El metodo equals debe devolver un boolean false cuando los codigos de los grupos son distintos")
    @Test
    public void Equals_CodigoEsDistinto_ReturnFalse() throws ClubException {
        Grupo g2 = new Grupo("123A", actividad, nplazas, nmatriculados, tarifa);

        assertNotEquals(g1, g2);
    }

    @DisplayName("El metodo equals debe devolver un boolean false cuando las actividades de los grupos son distintas")
    @Test
    public void Equals_ActividadEsDistinta_ReturnFalse() throws ClubException {
        Grupo g2 = new Grupo(codigo, "actividad", nplazas, nmatriculados, tarifa);

        assertNotEquals(g1, g2);
    }

    @DisplayName("El metodo equals debe devolver un boolean false cuando las instancias no son del mismo tipo")
    @Test
    public void Equals_ParametroEsDistintoTipo_ReturnFalse() throws ClubException {
        String g2 = "Pilates";

        assertNotEquals(g1, g2);
    }

    @DisplayName("El metodo hashCode debe devolver dos enteros iguales si las instancias son iguales")
    @Test
    public void HashCode__ReturnValorCorrecto() throws ClubException {

        Grupo g2 = new Grupo(codigo, actividad, nplazas, nmatriculados, tarifa);

        int hash1 = g1.hashCode();
        int hash2 = g2.hashCode();

        assertEquals(hash1, hash2);
    }
}