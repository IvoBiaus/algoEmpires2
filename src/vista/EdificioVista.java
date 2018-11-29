package vista;

import javafx.scene.image.ImageView;
import modelo.Aldeano;
import modelo.Atacante;
import modelo.Edificio;

public abstract class EdificioVista extends PiezaVista{
	
	protected ImageView enConstruccionView;
	protected ImageView construidoView;

	
	public EdificioVista(int x, int y, Edificio unModelo, MapaVista unMapa) {
		super(x, y, unModelo, unMapa);
	}

	@Override
	protected void realizarAccionSobrePieza() {
		PiezaVista piezaSeleccionada = elMapa.piezaSeleccionada();
		if(piezaSeleccionada.modelo() instanceof Atacante) {
			//Edificio esta siendo atacado
			((Atacante)(piezaSeleccionada).modelo()).atacar(modelo);
			if(modelo.estaDestruida()) {
				elMapa.removerPieza(this);
			}
		}
		
		if(piezaSeleccionada.modelo() instanceof Aldeano) {
			//Edificio esta siendo reparado
			((Aldeano)(piezaSeleccionada).modelo()).reparar((Edificio)modelo);
		}
		actualizarVisualizacon();
	}
	
	protected abstract void prepararBotones();

	protected abstract void crearRepresentacion();
	
}
