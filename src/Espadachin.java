
public class Espadachin extends Unidad implements Atacante{
	
	Espadachin(Casilla unaCasilla){
		super(unaCasilla);
		vida = 100;
		costo = 50;
	}
	
	//distancia de ataque = 1
	
	public int atacar(Pieza unaPieza) {
		if(unaPieza instanceof Edificio) {
			return atacarEdificio();
		}
		return atacarUnidad();
	}
	
	private int atacarUnidad() {
		return 25;
	}
	
	private int atacarEdificio() {
		return 15;
	}
	
}
