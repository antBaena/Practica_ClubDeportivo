package clubdeportivo;

import java.util.StringJoiner;

public class ClubDeportivo {
	private String nombre;
	private int ngrupos;
	private Grupo[] grupos;
	private static final int TAM = 10;

	public ClubDeportivo(String nombre) throws ClubException {
		this(nombre, TAM);
	}

	public ClubDeportivo(String nombre, int n) throws ClubException {
		if (n <= 0) {
			throw new ClubException("ERROR: el club no puede crearse con un número de grupos 0 o negativo");
		}
		this.nombre = nombre;
		grupos = new Grupo[n];
	}

	private int buscar(Grupo g) {
		int i = 0;
		while (i < ngrupos && !g.equals(grupos[i])) {
			i++;
		}
		if (i == ngrupos) {
			i = -1;
		}
		return i;
	}

	public void anyadirActividad(String[] datos) throws ClubException {
		// ERROR encontrado: la longitud de los datos es incorrecta
		if (datos.length != 5)
			throw new ClubException("ERROR: El número de datos introducidos es incorrecto.");
		try {
			int plazas = Integer.parseInt(datos[2]);
			int matriculados = Integer.parseInt(datos[3]);
			double tarifa = Double.parseDouble(datos[4]);
			Grupo g = new Grupo(datos[0], datos[1], plazas, matriculados, tarifa);
			anyadirActividad(g);
		} catch (NumberFormatException e) {
			throw new ClubException("ERROR: formato de número incorrecto");
		}
	}

	public void anyadirActividad(Grupo g) throws ClubException {
		// ERROR encontrado: grupos.length no puede ser distinto this.ngrupos
		if (grupos.length == this.ngrupos)
			throw new ClubException("ERROR: En este club no se admiten más grupos.");
		if (g == null) { // ERROR encontrado: comprobar los grupos no son nulos
			throw new ClubException("ERROR: el grupo es nulo");
		}
		int pos = buscar(g);
		if (pos == -1) { // El grupo es nuevo
			grupos[ngrupos] = g;
			ngrupos++;
		} else { // El grupo ya existe --> modificamos las plazas
			grupos[pos].actualizarPlazas(g.getPlazas());
		}
	}

	public int plazasLibres(String actividad) {
		int p = 0;
		int i = 0;
		while (i < ngrupos) {
			if (grupos[i].getActividad().equals(actividad)) {
				p += grupos[i].plazasLibres();
			}
			i++;
		}
		return p;
	}

	public void matricular(String actividad, int npersonas) throws ClubException {
		// ERROR encontrado: comprobar que npersonas > 0
		if (npersonas <= 0)
			throw new ClubException("ERROR: el número de personas no es válido");

		int plazas = plazasLibres(actividad);
		if (plazas < npersonas) {
			throw new ClubException("ERROR: no hay suficientes plazas libres para esa actividad en el club.");
		}
		int i = 0;
		while (i < ngrupos && npersonas > 0) {
			if (actividad.equals(grupos[i].getActividad())) {
				int plazasGrupo = grupos[i].plazasLibres();
				if (plazasGrupo > 0) { // ERROR encontrado: si no hay plazas libres, no se puede matricul
					if (npersonas >= plazasGrupo) {
						grupos[i].matricular(plazasGrupo);
						npersonas -= plazasGrupo;
					} else {
						grupos[i].matricular(npersonas);
						npersonas = 0;
						// ERROR encontrado: npersonas =0 al matricular todas
					}
				}
			}
			i++;
		}
	}

	public double ingresos() {
		double cantidad = 0.0;
		int i = 0;
		while (i < ngrupos) {
			cantidad += grupos[i].getTarifa() * grupos[i].getMatriculados();
			i++;
		}
		return cantidad;
	}

	public String toString() {
		StringJoiner sj = new StringJoiner(", ", "[ ", " ]");
		int i = 0;
		while (i < ngrupos) {
			sj.add(grupos[i].toString());
			i++;
		}
		return nombre + " --> " + sj.toString();
	}
}
