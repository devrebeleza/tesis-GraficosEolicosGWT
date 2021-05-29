package com.paquete.graficos.eolicos.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface AccesoDatosServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)throws IllegalArgumentException;
	
	void procesarArchivo(String archivo, AsyncCallback<Void> callback)throws IllegalArgumentException;

	
	void setDatosTodos(Integer maxDivision, AsyncCallback<String> callback)throws IllegalArgumentException;
	
	void setDatosArchivo(String lugarAdquisicion, int deadband1, int deadband2, int deadband3,
			 String sobreescritura, int altura1, int altura2, int altura3, int intervalo, int cantidadPaquetes,AsyncCallback<Void> callback)
			 throws IllegalArgumentException;
	
	void setTablaEnXML(String nombreTabla,AsyncCallback<Void> callback)throws IllegalArgumentException;
	
	void setTodasTablaEnXML(AsyncCallback<Void> callback)throws IllegalArgumentException;
}
