/*
 * @author Antonio Cañete Baena
 * 
 * @author Antonio Blas Moral Sánchez
 * 
 */
package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class GrupoGettersTest {

    private String codigo;
    private String actividad;
    private int plazas;
    private int matriculados;
    private double tarifa;
    private Grupo grupo;

    @BeforeEach
    public void setUp() throws ClubException {
        codigo = "456B";
        actividad = "Pilates";
        plazas = 8;
        matriculados = 5;
        tarifa = 50.0;
        grupo = new Grupo(codigo, actividad, plazas, matriculados, tarifa);
    }

    @DisplayName("El método getCodigo debe devolver el código del grupo")
    @Test
    public void getCodigo__DevuelveCodigoCorrectamente() {
        assertEquals(grupo.getCodigo(), codigo);
    }

    @DisplayName("El método getActividad debe devolver la actividad del grupo")
    @Test
    public void getActividad__DevuelveActividadCorrectamente() {
        assertEquals(grupo.getActividad(), actividad);
    }

    @DisplayName("El método getPlazas debe devolver las plazas del grupo")
    @Test
    public void getPlazas__DevuelvePlazasCorrectamente() {
        assertEquals(grupo.getPlazas(), plazas);
    }

    @DisplayName("El método getMatriculados debe devolver los matriculados del grupo")
    @Test
    public void getMatriculados__DevuelveMatriculadosCorrectamente() {
        assertEquals(grupo.getMatriculados(), matriculados);
    }

    @DisplayName("El método getTarifa debe devolver la tarifa del grupo")
    @Test
    public void getTarifa__DevuelveTarifaCorrectamente() {
        assertEquals(grupo.getTarifa(), tarifa);
    }

}