package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoAltoRendimientoTest {
    ClubDeportivoAltoRendimiento clubDeportivoAltoRendimiento;

    @BeforeEach
    void setUp() {
        try {
            clubDeportivoAltoRendimiento = new ClubDeportivoAltoRendimiento("test", 0, 0);
        } catch (ClubException e) {
            e.printStackTrace();
        }
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
}
