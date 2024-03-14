package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestClubDeportivo {
    String nombre;
    ClubDeportivo clubDeportivo;

    @BeforeEach
    void setUp() throws ClubException {
        nombre = "test";
        clubDeportivo = new ClubDeportivo(nombre);
    }

    @DisplayName("El constructor de la clase Club Deportivo debe lanzar una excepción si los parámetros son negativos")
    @Test
    void ClubDeportivo_NegativeValue_ThrowsClubException() {
        assertThrows(ClubException.class, () -> {
            new ClubDeportivo(null, -1);
        });
    }

    @DisplayName("El metodo anyadirActividad no debe lanzar una excepción si los parámetros son correctos")
    @Test
    void AnyadirActividad_ParametrosCorrectos_AnyadeActividad() {
        String[] datos = { "123A", "Kizomba", "10", "10", "25.0" };

        assertAll(() -> {
            clubDeportivo.anyadirActividad(datos);
        });
    }

    @DisplayName("El método anyadirActividad debe lanzar una excepción si los parámetros no son válidos")
    @Test
    void AnyadirActividad_ParametrosInvalidos_ThrowsClubException() {
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

    @DisplayName("El método anyadirActividad debe añadir un grupo al club si el parametro es un Grupo")
    @Test
    void AnyadirActividad_ParametrosCorrectos_ThrowsClubException() {
        assertAll(() -> {
            clubDeportivo.anyadirActividad(new Grupo("123A", "Kizomba", 10, 10, 25.0));
        });
    }

    @DisplayName("El método anyadirActividad debe lanzar una excepción si el grrupo es nulo")
    @Test
    void plazasLibres__() {
        assertThrows(ClubException.class, () -> {
            clubDeportivo.anyadirActividad((Grupo) null);
        });
    }

    @DisplayName("El método anyadirActividad debe lanzar una exe un grupo al club si el parametro es un Grupo")
    @Test
    void PlazasLibres_ParametroCorrecto_ReturnPlazasLibres() throws ClubException {
        Grupo g;
        g = new Grupo("123A", "Kizomba", 10, 5, 25.0);
        clubDeportivo.anyadirActividad(g);

        int numPlazas = clubDeportivo.plazasLibres("Kizomba");

        assertEquals(numPlazas, 5);
    }

    @DisplayName("El método anyadirActividad debe lanzar una exe un grupo al club si el parametro es un Grupo")
    @Test
    void PlazasLibres_GrupoNoAnyadido_ReturnCero() {
        int numPlazas = clubDeportivo.plazasLibres("Kizomba");

        assertEquals(numPlazas, 0);
    }

    @DisplayName("El método matricular debe lanzar una exe un grupo al club si el parametro es un Grupo")
    @Test
    void Matricular_ParametrosCorrectos_NotThrowClubException() throws ClubException {
        Grupo g;
        g = new Grupo("123A", "Kizomba", 10, 5, 25.0);
        clubDeportivo.anyadirActividad(g);
        int numPersonas = 5;

        assertAll(() -> clubDeportivo.matricular("Kizomba", numPersonas));
    }

    @DisplayName("El método matricular debe lanzar una exe un grupo al club si el parametro es un Grupo")
    @Test
    void Matricular_NoExisteActividad_ThrowClubException() {
        assertThrows(ClubException.class, () -> clubDeportivo.matricular("Kizomba", 10));
    }

    @DisplayName("El método matricular debe lanzar una exe un grupo al club si el numero de personas es negativo")
    @Test
    void Matricular_NumPersonasNegativo_ThrowsException() throws ClubException {
        Grupo g;
        g = new Grupo("123A", "Kizomba", 1, 1, 25.0);
        clubDeportivo.anyadirActividad(g);
        int numPersonas = -3;

        assertThrows(ClubException.class, () -> {
            clubDeportivo.matricular("Kizomba", numPersonas);
        });
    }

    @DisplayName("El método matricular debe lanzar una exe un grupo al club si el parametro es un Grupo")
    @Test
    void Matricular_PlazasInsuficientes_ThrowsException() throws ClubException {
        Grupo g;
        g = new Grupo("123A", "Kizomba", 1, 1, 25.0);
        clubDeportivo.anyadirActividad(g);
        int numPersonas = 10;

        assertThrows(ClubException.class, () -> {
            clubDeportivo.matricular("Kizomba", numPersonas);
        });
    }

    @DisplayName("El método ingresos debe lanzar una exe un grupo al club si el parametro es un Grupo")
    @Test
    void ingresos_ZeroGrupos_DevulveCero() throws ClubException {

        double ingresos = clubDeportivo.ingresos();

        assertEquals(ingresos, 0);
    }

    @DisplayName("El método ingresos debe lanzar una exe un grupo al club si el parametro es un Grupo")
    @Test
    void ingresos_Grupos_DevulveTarifa() throws ClubException {
        double tarifa = 25.0;

        Grupo g1 = new Grupo("123A", "Kizomba", 1, 1, tarifa);
        Grupo g2 = new Grupo("123Ab", "Kizomba2", 1, 1, tarifa);

        clubDeportivo.anyadirActividad(g1);
        clubDeportivo.anyadirActividad(g2);

        double ingresos = clubDeportivo.ingresos();

        assertEquals(ingresos, (tarifa * 2));
    }

    @DisplayName("El método ingresos debe lanzar una exe un grupo al club si el parametro es un Grupo")
    @Test
    void toString_nodevulveString() throws ClubException {

        String text = clubDeportivo.toString();

        assertEquals(text, nombre + " --> [  ]");
    }

    @DisplayName("El método ingresos debe lanzar una exe un grupo al club si el parametro es un Grupo")
    @Test
    void toString_devulveString() throws ClubException {
        double tarifa = 25.0;

        Grupo g1 = new Grupo("A", "Kizomba", 1, 1, tarifa);
        Grupo g2 = new Grupo("B", "Kizomba2", 1, 1, tarifa);

        clubDeportivo.anyadirActividad(g1);
        clubDeportivo.anyadirActividad(g2);

        String text = clubDeportivo.toString();

        assertEquals(text, nombre + " --> [ " + g1.toString() + ", " + g2.toString() + " ]");
    }
}
