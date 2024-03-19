package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GrupoConstructorTest {

    @DisplayName("El constructor de Grupo debe lanzar una excepción si el numero de matriculados es mayor al de plazas")
    @Test
    void Grupo_MatriculadosMayorNumPlazas_ThrowsClubException() {

        assertThrows(ClubException.class, () -> {
            new Grupo("123A", "Pilates", 1, 2, 50.0);
        });
    }

    @DisplayName("El constructor de Grupo debe lanzar una excepción si el numero de plazas es menor o igual que 0")
    @Test
    public void Grupo_NumPlazasNegativo_ThrowsClubException() {
        assertThrows(ClubException.class, () -> new Grupo("123A", "Pilates", -5, 2, 50.0));
    }

    @DisplayName("El constructor de Grupo debe lanzar una excepción si el numero de matriculados es menor o igual que 0")
    @Test
    public void Grupo_NumMatriculadosInvalido_ThrowsClubException() {
        assertThrows(ClubException.class, () -> new Grupo("123A", "Pilates", 5, -2, 50.0));
    }

    @DisplayName("El constructor de Grupo debe lanzar una excepción si la tarifa es menor o igual que 0")
    @Test
    public void Grupo_TarifaInvalida_ThrowsClubException() {
        assertThrows(ClubException.class, () -> new Grupo("123A", "Pilates", 5, 2, -50.0));
    }

    @DisplayName("El constructor de Grupo debe lanzar una excepción si la tarifa es menor o igual que 0")
    @Test
    public void Grupo_ParametrosCorrectos_ReturnGrupo() throws ClubException {
        String codigo = "456B";
        String actividad = "Pilates";
        int nplazas = 8;
        int nmatriculados = 5;
        double tarifa = 50.0;

        assertDoesNotThrow(() -> new Grupo(codigo, actividad, nplazas, nmatriculados, tarifa));
    }
}