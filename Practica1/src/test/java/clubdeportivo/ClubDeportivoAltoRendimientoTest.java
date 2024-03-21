package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoAltoRendimientoTest {
    ClubDeportivoAltoRendimiento clubDeportivoAltoRendimiento;
    double incremento;
    int maximo;
    String nombre;

    @BeforeEach
    void setUp() throws ClubException {
        incremento = 100;
        maximo = 10;
        nombre = "test";
        clubDeportivoAltoRendimiento = new ClubDeportivoAltoRendimiento(nombre, maximo, incremento);
    }

    @DisplayName("El constructor de la clase ClubDeportivoAltoRendimiento debe lanzar una excepción si el parametro 'maximo' es negativo")
    @Test
    void ClubDeportivoAltoRendimientoC1_ParamMaximoNegativo_ThrowsClubException() {
        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento("test", -1, 1);
        });
    }

    @DisplayName("El constructor de la clase ClubDeportivoAltoRendimiento debe lanzar una excepción si el parametro 'incremento' es negativo")
    @Test
    void ClubDeportivoAltoRendimientoC1_ParamIncrementoNegativo_ThrowsClubException() {
        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento("test", 1, -1);
        });
    }

    @DisplayName("El constructor de la clase ClubDeportivoAltoRendimiento debe lanzar una excepción si el parametro 'maximo' es negativo")
    @Test
    void ClubDeportivoAltoRendimientoC2_ParamMaximoNegativo_ThrowsClubException() {
        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento("test", 1, -1, 1);
        });
    }

    @DisplayName("El constructor de la clase ClubDeportivoAltoRendimiento debe lanzar una excepción si el parametro 'incremento' es negativo")
    @Test
    void ClubDeportivoAltoRendimientoC2_ParamIncrementoNegativo_ThrowsClubException() {
        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento("test", 1, 1, -1);
        });
    }

    @DisplayName("El constructor de la clase ClubDeportivoAltoRendimiento debe lanzar una excepción si el parametro 'tamaño' es menor o igual a cero")
    @Test
    void ClubDeportivoAltoRendimientoC2_ParamTamanyoInvalido_ThrowsClubException() {
        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento("test", 0, 1, 1);
        });
    }

    @DisplayName("El metodo ingresos debe devolver cero si no hay grupos matriculados")
    @Test
    void ingresos_ZeroActividad_DevuelveCero() {
        double ingresos = clubDeportivoAltoRendimiento.ingresos();
        assertEquals(ingresos, 0);
    }

    @DisplayName("El metodo ingresos debe devolver las tarifas de los grupos mas un incremento si hay grupos matriculados")
    @Test
    void ingresos_DevuelveIngresoConIncremento() throws ClubException {
        double tarifa = 25.0;
        int matriculados = 10;
        String[] datos = { "123A", "Kizomba", "10", String.valueOf(matriculados), String.valueOf(tarifa) };
        clubDeportivoAltoRendimiento.anyadirActividad(datos);

        double ingresos = clubDeportivoAltoRendimiento.ingresos();
        double expected = ((tarifa * matriculados) + ((tarifa * matriculados) * (incremento / 100)));
        assertEquals(ingresos, expected);
    }

}
