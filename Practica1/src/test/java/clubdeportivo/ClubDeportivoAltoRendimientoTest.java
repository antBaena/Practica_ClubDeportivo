package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoAltoRendimientoTest {
    ClubDeportivoAltoRendimiento clubDeportivoAltoRendimiento;
    double incremento;
    int maximo = 10;

    @BeforeEach
    void setUp() throws ClubException {
        incremento = 100;
        maximo = 10;
        clubDeportivoAltoRendimiento = new ClubDeportivoAltoRendimiento("test", maximo, incremento);
    }

    @DisplayName("El constructor de la clase ClubDeportivoAltoRendimiento debe lanzar una excepción si el parametro 'maximo' es negativo")
    @Test
    void ClubDeportivo_ParamMaximoNegativo_ThrowsClubException() {
        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento("test", -1, 0);
        });
    }

    @DisplayName("El constructor de la clase ClubDeportivoAltoRendimiento debe lanzar una excepción si el parametro 'incremento' es negativo")
    @Test
    void ClubDeportivo_ParamIncrementoNegativo_ThrowsClubException() {
        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento("test", 0, -1);
        });
    }

    @DisplayName("El constructor de la clase ClubDeportivoAltoRendimiento debe lanzar una excepción si el parametro 'maximo' es negativo")
    @Test
    void ClubDeportivo_P1aramMaximoNegativo_ThrowsClubException() {
        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento("test", 0, -1, 0);
        });
    }

    @DisplayName("El constructor de la clase ClubDeportivoAltoRendimiento debe lanzar una excepción si el parametro 'incremento' es negativo")
    @Test
    void ClubDeportivo_P2aramIncrementoNegativo_ThrowsClubException() {
        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento("test", 0, 0, -1);
        });
    }

    // TODO Test si maximo es menor o igual a cero
    // TODO Test si incremento es menor o igual a cero
    @DisplayName("El constructor de la clase ClubDeportivoAltoRendimiento debe lanzar una excepción si el parametro 'incremento' es negativo")
    @Test
    void ingresos_ZeroActividad_DevuelveCero() {
        double ingresos = clubDeportivoAltoRendimiento.ingresos();
        assertEquals(ingresos, 0);
    }

    // TODO Test si maximo es menor o igual a cero
    // TODO Test si incremento es menor o igual a cero
    @DisplayName("El constructor de la clase ClubDeportivoAltoRendimiento debe lanzar una excepción si el parametro 'incremento' es negativo")
    @Test
    void ingresos_DevuelveIngresoConIncremento() throws ClubException {
        double tarifa = 25.0;
        int matriculados = 10;
        String[] datos = { "123A", "Kizomba", "10", String.valueOf(matriculados), String.valueOf(tarifa) };
        clubDeportivoAltoRendimiento.anyadirActividad(datos);

        double ingresos = clubDeportivoAltoRendimiento.ingresos();
        double expected = (tarifa * matriculados) * 2;
        assertEquals(ingresos, expected);
    }

}
