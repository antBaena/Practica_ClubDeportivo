package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class TestClubDeportivo {

    @DisplayName("El segundo constructor de la clase Club Deportivo debe lanzar una excepción si los parámetros son negativos")
    @Test
    void ClubDeportivo_NombreYnumPlazas_CreateObject() throws ClubException {
        String nombre = "club";
        int numPlazas = 12;
        String expected = nombre + " --> [  ]";

        ClubDeportivo clubDeportivo = new ClubDeportivo(nombre, numPlazas);

        assertEquals(clubDeportivo.toString(), expected);
    }

    @DisplayName("El primer constructor de la clase Club Deportivo debe lanzar una excepción si los parámetros son negativos")
    @Test
    void ClubDeportivo_Nombre_CreateObject() throws ClubException {
        String nombre = "club";

        String expected = nombre + " --> [  ]";

        ClubDeportivo clubDeportivo = new ClubDeportivo(nombre);

        assertEquals(clubDeportivo.toString(), expected);
    }

    @DisplayName("El constructor de la clase Club Deportivo debe lanzar una excepción si los parámetros son negativos")
    @Test
    void ClubDeportivo_NegativeValue_ThrowsClubException() {
        assertThrows(ClubException.class, () -> {
            new ClubDeportivo(null, -1);
        });
    }

    @DisplayName("El método plazasLibres debe devolver el numero de plazas libres del grupo de la actividad seleccionada si el parametro es un Grupo")
    @Test
    void PlazasLibres_GrupoExiste_ReturnPlazasLibres() throws ClubException {
        String nombre = "club";
        int numGrupos = 12;
        ClubDeportivo clubDeportivo = new ClubDeportivo(nombre, numGrupos);
        int numMatriculados = 2;
        int numPlazasTotal = 10;

        Grupo g1 = new Grupo("123A", "Kizomba", numPlazasTotal, numMatriculados + 2, 25.0);
        Grupo g2 = new Grupo("234B", "Pilates", numPlazasTotal, numMatriculados, 25.0);

        clubDeportivo.anyadirActividad(g1);
        clubDeportivo.anyadirActividad(g2);

        int numPlazas = clubDeportivo.plazasLibres("Pilates");

        assertEquals(numPlazas, numPlazasTotal - numMatriculados);
    }

    @DisplayName("El método plazasLibres debe devolver cero si no hay grupos con esa actividad en el club")
    @Test
    void PlazasLibres_GrupoNoAnyadido_ReturnCero() throws ClubException {
        String nombre = "club";
        int numGrupos = 12;
        ClubDeportivo clubDeportivo = new ClubDeportivo(nombre, numGrupos);

        int numPlazas = clubDeportivo.plazasLibres("Kizomba");

        assertEquals(numPlazas, 0);
    }

    @DisplayName("El método plazasLibres debe devolver cero si el grupo no tiene plazas libres")
    @Test
    void PlazasLibres_GrupoExiste_ReturnCero() throws ClubException {
        String nombre = "club";
        int numGrupos = 12;
        Grupo g1 = new Grupo("123A", "Kizomba", 10, 10, 25.0);
        ClubDeportivo clubDeportivo = new ClubDeportivo(nombre, numGrupos);
        int numPlazas = clubDeportivo.plazasLibres("Kizomba");

        assertEquals(numPlazas, 0);
    }

    @DisplayName("El método ingresos debe lanzar una exe un grupo al club si el parametro es un Grupo")
    @Test
    void ingresos_ZeroGrupos_DevulveCero() throws ClubException {
        String nombre = "club";
        int numGrupos = 12;
        ClubDeportivo clubDeportivo = new ClubDeportivo(nombre, numGrupos);

        double ingresos = clubDeportivo.ingresos();

        assertEquals(ingresos, 0);
    }

    @DisplayName("El método ingresos debe lanzar una exe un grupo al club si el parametro es un Grupo")
    @Test
    void ingresos_Grupos_DevulveTarifa() throws ClubException {
        String nombre = "club";
        int numGrupos = 12;
        ClubDeportivo clubDeportivo = new ClubDeportivo(nombre, numGrupos);
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
        String nombre = "club";
        int numGrupos = 12;
        ClubDeportivo clubDeportivo = new ClubDeportivo(nombre, numGrupos);

        String text = clubDeportivo.toString();

        assertEquals(text, "club --> [  ]");
    }

    @DisplayName("El método ingresos debe lanzar una exe un grupo al club si el parametro es un Grupo")
    @Test
    void toString_devulveString() throws ClubException {
        String nombre = "club";
        int numGrupos = 12;
        ClubDeportivo clubDeportivo = new ClubDeportivo(nombre, numGrupos);
        double tarifa = 25.0;
        Grupo g1 = new Grupo("A", "Kizomba", 1, 1, tarifa);
        Grupo g2 = new Grupo("B", "Kizomba2", 1, 1, tarifa);

        clubDeportivo.anyadirActividad(g1);
        clubDeportivo.anyadirActividad(g2);

        String text = clubDeportivo.toString();

        assertEquals(text, "club --> [ " + g1.toString() + ", " + g2.toString() + " ]");
    }

}
