public class Aldeano extends Unidad {

    /*      -Aldeano-
     *
     *       Vida: 50
     *       Costo: 25
     */

	private Edificio edificioObjetivo;


	Aldeano(Area unEspacio) throws Excepcion{
		super(unEspacio);
		edificioObjetivo = null;
		vida = 50;
		costo = 25;
	}

	public void reparar(Edificio unEdificio) throws Excepcion {
		siEstaOcupadoDaError();
		siYaJugoElTurnoError();
		
		if(enRango(unEdificio,1) & unEdificio.necesitaReparacion()) {
			ocupado = true;
			turnoJugado = true;
			unEdificio.reparar();
			edificioObjectivo = unEdificio;
		}
	}
	
	private int generarOro() throws Excepcion {	//hago una funcion o multiplico por cantidad de aldeanos libres?
		siYaJugoElTurnoError();
		
		if(!ocupado) {
			jugoEnTurno = true;
			return 25;
		}
		else {
			return 0;
		}
	}
	
	public boolean estaOcupado() {
		return ocupado;
	}
	
	public Plaza crearPlaza(Area areaDeConstruccion) throws Excepcion {
		siYaJugoElTurnoError();
		
		//TODO no lo puedo declarar 1 vez por turno tengo  que 
		//guardarlo en algun lado y seguir el trabajo despues
		if(!ocupado & areaDeConstruccion.estaLibre() & distanciaMinimaA(areaDeConstruccion) == 1) {
			ocupado = true;
			turnoJugado = true;
			Plaza nuevaPlaza = new Plaza(areaDeConstruccion);
			edificioObjectivo = nuevaPlaza;
			edificioObjectivo.construir();
			
			return nuevaPlaza;
		}
		return null;
	}
	
	public Cuartel crearCuartel(Area areaDeConstruccion) throws Excepcion {
		siYaJugoElTurnoError();
		
		//TODO no lo puedo declarar 1 vez por turno tengo  que 
		//guardarlo en algun lado y seguir el trabajo despues
		if(!ocupado & areaDeConstruccion.estaLibre() & distanciaMinimaA(areaDeConstruccion) == 1) {
			ocupado = true;
			turnoJugado = true;
			Cuartel nuevoCuartel = new Cuartel(areaDeConstruccion);
			edificioObjectivo = nuevoCuartel;
			edificioObjectivo.construir();

			return nuevoCuartel;
		}
		return null;
	}

	public int realizarTrabajoDeTurno() throws Excepcion {
		siYaJugoElTurnoError();
		
		//el aldeano :
		// Genera oro, contruye o repara
		//TODO quedo un switch de mierda, ver si se puede cambiar
		if(edificioObjetivo != null) {
			if(edificioObjetivo.enConstruccion() | edificioObjetivo.necesitaReparacion()) {
				edificioObjetivo.construir();
				edificioObjetivo.reparar();
				jugoEnTurno = true;
				return 0;
			}
			else {
				ocupado = false;
				edificioObjetivo = null;
				return generarOro();
			}
		}
		else {
			return generarOro();
		}
	}	
	
}

