package vista;

import controlador.EdificioRealizarAccion;
import javafx.scene.image.ImageView;
import modelo.Edificio;
import modelo.Pieza;

public abstract class EdificioVista extends PiezaVista{
	
	protected ImageView enConstruccionView;
	protected ImageView construidoView;
	protected Edificio modelo;

	public EdificioVista(int x, int y, Edificio unModelo, MapaVista unMapa) {
		super(x, y, unModelo, unMapa);
		modelo = unModelo;
		crearRepresentacion();
	}

	@Override
	public void realizarAccionSobrePieza() {
		new EdificioRealizarAccion(elMapa,this);
	}
	
	public Edificio modelo() {
		System.out.println("Edificio getModelo : " + (modelo == null)); //TODO BORRAR

		return modelo;
	}
	
	protected abstract void prepararBotones();

	protected abstract void crearRepresentacion();
	
}