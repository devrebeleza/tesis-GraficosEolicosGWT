package com.paquete.graficos.eolicos.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


import com.paquete.graficos.eolicos.client.AccesoDatosService;
import com.paquete.graficos.eolicos.funcionescarga.FuncionesDatosArchivo;
import com.paquete.graficos.eolicos.funcionescarga.FuncionesDatosFrecuenciaVelocidad;
import com.paquete.graficos.eolicos.funcionescarga.FuncionesDatosPerfilTemporarioHora;
import com.paquete.graficos.eolicos.funcionescarga.FuncionesDatosPerfilTemporarioMes;
import com.paquete.graficos.eolicos.funcionescarga.FuncionesDatosPerfilVertical;
import com.paquete.graficos.eolicos.funcionescarga.FuncionesDatosTodos;
import com.paquete.graficos.eolicos.funcionescarga.FuncionesDireccionVientoRangos;
import com.paquete.graficos.eolicos.funcionescarga.FuncionesLectura;

import com.paquete.graficos.eolicos.servXML.TablaEnXML;
import com.paquete.graficos.eolicos.shared.FieldVerifier;
import com.paquete.graficos.eolicos.shared.Mensaje;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class AccesoDatosServiceImpl extends RemoteServiceServlet implements
		AccesoDatosService {

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
	
	
	public void procesarArchivo(String archivo)throws IllegalArgumentException {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//	    session.beginTransaction();
		// Creo un archivo local para procesar los datos
	    File fl = new File("Documentos/ArchivoDatos.txt");
	    try{
	    	if(fl.createNewFile()){
//	    		System.out.println( archivo);
	    		System.out.println("El fichero se ha creado correctamente : ");
	    	}else{
//	    		System.out.println( archivo);	    		
	    		System.out.println("No ha podido ser creado el fichero : " + fl.length());
	    	}
	    }catch(IOException ioe){
	    	ioe.printStackTrace();
	    }
	    
	    try {
	    	// mediante este buffer creo un FileWriter para rellenar el archivo anterior
//			BufferedWriter bw = new BufferedWriter(new FileWriter(fl));
//			bw.write(archivo);
	    	
	    	FileWriter writer = new FileWriter(fl);
//	    	el String archivo trae info sobre el submit, debo eliminarlo, dependiendo del navegador es lo que debo buscar
	    	
	    	// esto se crea con el navegador firefox
	    	String archModif = archivo.replaceFirst("<pre>","");
	    	
	    	// esto se crea con el navegador chrome
	    	archModif = archivo.replaceFirst("<pre style=\"word-wrap: break-word; white-space: pre-wrap;\">", "");
	    	
	    	// esto es igual para todos los navegadores
	    	archModif = archModif.replaceFirst("</pre>", "");
	    	
	    	writer.write(archModif);
	    	writer.flush();
	    	writer.close();
			System.out.println( "tama??o archivo : " + archModif + " fin archivoooo");
			// Accedo al archivo para recuperar los datos mediante un buffer
			FileInputStream fis = new FileInputStream(fl.getAbsolutePath());
			InputStreamReader is = new InputStreamReader(fis,"ISO-8859-1");
			BufferedReader bf = new BufferedReader(is);
									
			// recorro el buffer linea por linea y escribo cada dato
//			String sCadena = bf.readLine();
//			int lineaCont = 0;
//			while ((sCadena != null) && (lineaCont < 10)){
//				String[] lista = null;
//				lista = sCadena.split("\t");
//				System.out.println("linea Contada = " + lineaCont);
//				System.out.println(sCadena);
//				int i =0; 
//				while (i< lista.length){
//					System.out.println("elemento " + i + ": "+ lista[i]);
//					i++;
//				}
//				sCadena = bf.readLine();
//				lineaCont++;
//			}
	    	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Mensaje.MensajeError("No se pudo generar el buffer de escritura");
//			Window.alert("No se pudo generar el buffer de escritura");
			e.printStackTrace();
		}		
	}
	
	public String setDatosTodos(Integer maxDivision)throws IllegalArgumentException{
		
//		borro todas los datos de las tablas que existen
		FuncionesDatosTodos.limpiarTablaDatosTodos();

		
		// uso el componente creado para la lectura y carga del archivo en la base de datos
		try {
			if(FuncionesLectura.leerArchivo("Documentos/ArchivoDatos.txt")){
			
			int cantLineasSinInsertar = 0;	
			int lineaContada = 0;
			String[] linea;
//		
			FuncionesDatosTodos insertDt = new FuncionesDatosTodos();
			String banderaOk = "OK";
			String error = null;
			//lee linea por linea y la envia para guardarla en la base de datos
			while((linea = FuncionesLectura.getNextLinea("\t"))!= null &&(banderaOk == "OK")){ 
				if (lineaContada ==1){
					banderaOk = insertDt.controlarColumnas(linea);
					System.out.println("mensaje bandera OK?: " + banderaOk);
					error = banderaOk;
				}
				else if (lineaContada > 1){
					if (insertDt.insertarLineaDatosTodos(linea) != "OK"){
//					if ("NOK" != "OK"){
						cantLineasSinInsertar++; 
						int i =0; 
						int lineaError = lineaContada - 1;
//						Mensaje.MensajeError("Linea " + lineaError + " no pudo ser insertada");
//					JOptionPane.showMessageDialog(jfchooser, "Linea " + lineaError + " no pudo ser insertada");
						System.out.println("Linea " + lineaError + " no pudo ser insertada");
						error = error + " Linea " + lineaError + " no pudo ser insertada /n"; 
						while (i< linea.length){
							System.out.println("elemento " + i + ": "+ linea[i]);
							i++;
						}	
					}				
				}
				lineaContada++;
			 }
			if ((banderaOk!="OK") || (cantLineasSinInsertar > 0)){
//				Mensaje.MensajeError(error);
				System.out.println(" Devolviendo Error");
				return error;
			} 
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			Mensaje.MensajeError("Excepci??n no soportada al leer el archivo interno " + e.getMessage());
			return "Excepci??n no soportada al leer el archivo interno " + e.getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			Mensaje.MensajeError("Excepci??n de entrada salida al leer el archivo interno " + e.getMessage());
			return "Excepci??n de entrada salida al leer el archivo interno " + e.getMessage();
		}
		
		//JOptionPane.showMessageDialog(jfchooser, "cantidad de lineas leidas =" + lineaContada + " bandera : " + bandera);
		//System.out.println("cantidad de elementos leidos =" + (lineaContada));
//	}
//	else{
//		JOptionPane.showMessageDialog(jfchooser, "Archivo Incorrecto");
//	}
		System.out.println("Limpiando e insertando datos Perfil Vertical Altura 1, 2 y 3");
		FuncionesDatosPerfilVertical.limpiarTablaDatosPerfilVertical();		
		FuncionesDatosPerfilVertical.addDatosPerfilVerticalAltura1();
		FuncionesDatosPerfilVertical.addDatosPerfilVerticalAltura2();
		FuncionesDatosPerfilVertical.addDatosPerfilVerticalAltura3();
		
		System.out.println("Limpiando e insertando datos Perfil Temporario Hora");
		FuncionesDatosPerfilTemporarioHora.limpiarTablaDatosPerfilTemporarioHora();
		FuncionesDatosPerfilTemporarioHora.addDatosPerfilTemporarioHora();
		
		System.out.println("Limpiando e insertando datos Perfil Temporario Mes");
		FuncionesDatosPerfilTemporarioMes.limpiarTablaDatosPerfilTemporarioMes();
		FuncionesDatosPerfilTemporarioMes.addDatosPerfilTemporarioMes();
		
		System.out.println("Limpiando datos Direcci??n Viento Rangos");
		FuncionesDireccionVientoRangos.limpiarTablaDireccionVientoRangos();
		try {
			FuncionesDireccionVientoRangos.addDatosDireccionVientoRangos(maxDivision);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Excepci??n no soportada al ingresar los datos de Direcci??n de Viento " + e.getMessage();
		}
		
		System.out.println("Limpiando datos Frecuencia Velocidad");
		FuncionesDatosFrecuenciaVelocidad.limpiarTablaDatosFrecuenciaVelocidad();
		try {
			FuncionesDatosFrecuenciaVelocidad.addDatosFrecuenciaVelocidad(24);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "Excepci??n no soportada al ingresar los datos de Frecuencia de Velocidad " + e1.getMessage();
		}
		
		
		
		
		return "OK";
	}

	@Override
	public void setDatosArchivo(String lugarAdquisicion, int deadband1, int deadband2, int deadband3,
			 String sobreescritura, int altura1, int altura2, int altura3, int intervalo, int cantidadPaquetes) throws IllegalArgumentException {
		String resultado = FuncionesDatosArchivo.limpiarTablaDatosArchivo();
		
		if (resultado.equalsIgnoreCase("OK")){
				resultado = FuncionesDatosArchivo.addDatosArchivo(lugarAdquisicion, deadband1, deadband2, deadband3, 
													sobreescritura, altura1, altura2, altura3, intervalo, cantidadPaquetes);
				if (!(resultado.equalsIgnoreCase("OK"))){
					
					Mensaje.MensajeError("Error: No se pudieron guardar los datos nuevos del archivo");
				}
		}
		else
			  Mensaje.MensajeError("Error: No se pudieron eliminar los datos antiguos del archivo");	
	}
	
	public void setTablaEnXML(String nombreTabla)throws IllegalArgumentException{
		TablaEnXML tablaXml = new TablaEnXML();
		try {
			tablaXml.setTablaEnXML(nombreTabla);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Mensaje.MensajeError("Error: al generar el archivo "+nombreTabla+".ds.xml - "+e.getMessage());
			e.printStackTrace();
		}
	}

	
	public void setTodasTablaEnXML() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		setTablaEnXML("DatosArchivo");
		setTablaEnXML("ViewDatosAltura1");
		setTablaEnXML("ViewDatosAltura2");
		setTablaEnXML("ViewDatosAltura3");
		setTablaEnXML("DatosPerfilTemporarioHora");
		setTablaEnXML("DatosPerfilTemporarioMes");
		setTablaEnXML("DatosPerfilVertical");
		setTablaEnXML("DireccionVientoRangos");
		setTablaEnXML("DatosFrecuenciaVelocidad");
		
	}
}
