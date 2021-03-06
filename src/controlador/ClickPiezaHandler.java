package controlador;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import modelo.excepciones.PiezaNoEstaEnEquipoAliado;
import vista.JuegoVista;
import vista.PiezaVista;
import vista.TextoError;

public class ClickPiezaHandler implements EventHandler<MouseEvent> {
	
	private PiezaVista laPieza;
	private JuegoVista elJuego;
	
	public ClickPiezaHandler(JuegoVista elJuego, PiezaVista laPieza) {
		this.elJuego = elJuego;
		this.laPieza = laPieza;
	}
	
	@Override
	public void handle(MouseEvent event) {
		try {
			if(event.getButton() == MouseButton.PRIMARY ) {
				
				if(!elJuego.aliadoContieneA(laPieza.modelo())) {
					elJuego.playError();
					
					throw new PiezaNoEstaEnEquipoAliado();
				}
				
				seleccionarPieza();
			}
			if(event.getButton() == MouseButton.SECONDARY ) {
	
					laPieza.realizarAccionSobrePieza();				
	
			}
		}
		catch(Exception e) {
			
        	elJuego.playError();
            TextoError textoError = new TextoError("No puede interactuar con esa pieza");
            textoError.setOnMouseMoved(new TextoHandler(textoError));
            elJuego.getChildren().add(textoError);
		}
	}
	
	protected void seleccionarPieza() {
		//sacar efecto a casilla anterior
		PiezaVista piezaAnterior = elJuego.piezaSeleccionada();
		if(piezaAnterior != null) {
			elJuego.piezaSeleccionada().desSeleccionar();
		}
		//agregar efecto a casilla actual
		laPieza.playSeleccionar();
		
		laPieza.seleccionar();
		elJuego.seleccionarPieza(laPieza);
	}
	
}
