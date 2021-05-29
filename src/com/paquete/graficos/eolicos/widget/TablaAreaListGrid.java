package com.paquete.graficos.eolicos.widget;

import java.sql.Time;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.paquete.graficos.eolicos.basededatos.DatosPerfilVertical;
import com.paquete.graficos.eolicos.basededatos.DireccionVientoRangos;
import com.paquete.graficos.eolicos.client.AccesoDatosService;
import com.paquete.graficos.eolicos.client.AccesoDatosServiceAsync;
import com.paquete.graficos.eolicos.data.DatosArchivoSource;
import com.paquete.graficos.eolicos.data.DatosDireccionVientoRangosSource;
import com.paquete.graficos.eolicos.data.DatosFrecuenciaVelocidadSource;
import com.paquete.graficos.eolicos.data.DatosPerfilTemporarioHoraSource;
import com.paquete.graficos.eolicos.data.DatosPerfilTemporarioMesSource;
import com.paquete.graficos.eolicos.data.DatosPerfilVerticalSource;
import com.paquete.graficos.eolicos.data.DatosVistaAlturaDosSource;
import com.paquete.graficos.eolicos.data.DatosVistaAlturaTresSource;
import com.paquete.graficos.eolicos.data.DatosVistaAlturaUnoSource;
import com.paquete.graficos.eolicos.shared.Mensaje;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.TimeDisplayFormat;
import com.smartgwt.client.widgets.grid.*;

public class TablaAreaListGrid extends ListGrid {

  public TablaAreaListGrid(final String parametro) {
  super();
       
  GWT.log("init TablaAreaListGrid()...", null);

    // incializo la cuadricula
//  this.setShowAllRecords(false);   
  this.setSortField(1); 
  

  if (parametro.equalsIgnoreCase("DatosArchivo")){
     
//	// Creo la interfaz del servicio RPC de acceso a datos para ser usado en la inserción en la base
//	   	final AccesoDatosServiceAsync accesServiceXML = GWT.create(AccesoDatosService.class);
//	   	
//	   	accesServiceXML.setTablaEnXML(parametro, new AsyncCallback<Void>() {
//
//	 	      @Override
//	 	      public void onFailure(Throwable caught) {
//	 	        Mensaje.MensajeError("No se pudo generar el archivo .data.xml para la tabla Datos Archivo " + caught.getMessage());
////	 	        com.google.gwt.user.client.Window.alert("No se pudieron guardar los datos en la Base de Datos" + caught.getMessage());
//	 	      }
//
//	 	      @Override
//	 	      public void onSuccess(Void result) { 	    	  
////	 	    		  Mensaje.MensajeConfirmacion("El archivo " +parametro+".xml se generó con exito");      	               	    		        	               
////	 	    	 com.google.gwt.user.client.Window.alert("Los datos se han guardado con exito");      	
//	 	      	} 
//	 	      });
    // inicializo los campos de la cuadricula    
      
    ListGridField idDatosField = new ListGridField("idDatosArchivo", "id", 50);  
    idDatosField.setHidden(true);
    ListGridField lugarAdquisicionField = new ListGridField("lugarAdquisicion", "Lugar de Adquisición", 130);  
    ListGridField altura1Field = new ListGridField("altura1", "Altura Uno", 100);
    altura1Field.setAlign(getHoverAlign().CENTER);
    ListGridField altura2Field = new ListGridField("altura2", "Altura Dos", 100);
    altura2Field.setAlign(getHoverAlign().CENTER);
    ListGridField altura3Field = new ListGridField("altura3", "Altura Tres", 100);
    altura3Field.setAlign(getHoverAlign().CENTER);
    ListGridField deadband1Field = new ListGridField("deadband1", "Deadband 1 (°)", 100);
    deadband1Field.setAlign(getHoverAlign().CENTER);
    ListGridField deadband2Field = new ListGridField("deadband2", "Deadband 2 (°)", 100);
    deadband2Field.setAlign(getHoverAlign().CENTER);
    ListGridField deadband3Field = new ListGridField("deadband3", "Deadband 3 (°)", 100);
    deadband3Field.setAlign(getHoverAlign().CENTER);
    ListGridField sobreescrituraField = new ListGridField("sobreescritura", "Sobreescritura", 100);
    sobreescrituraField.setAlign(getHoverAlign().CENTER);
    
    ListGridField intervaloField = new ListGridField("intervalo", "Intervalo (min)", 100);
    intervaloField.setAlign(getHoverAlign().CENTER);
    ListGridField cantPaquetesField = new ListGridField("cantidadPaquetes", "Cantidad de Paquetes", 150);
    cantPaquetesField.setAlign(getHoverAlign().CENTER);
    ListGridField vacioField = new ListGridField("vacioField", " "); 

     
    
    // seteo los campos en la cuadricula  
    this.setFields(idDatosField, lugarAdquisicionField, altura1Field, altura2Field, altura3Field, deadband1Field, deadband2Field,deadband3Field, sobreescrituraField,
        intervaloField,cantPaquetesField,vacioField);  
    
    // poblar la cuadricula
    this.setDataSource(DatosArchivoSource.getInstance());  
    this.setAutoFetchData(true); 
  
    this.draw();
    this.markForRedraw();
    
  }else if (parametro.equalsIgnoreCase("ViewDatosAltura1")){
	  
//	  Mensaje.MensajeError("Primer Altura " + parametro );
	  ListGridField  idDatosField = new ListGridField("idDatosTodos", "ID", 50);
	  idDatosField.setHidden(true);
           
      
      ListGridField fechaField = new ListGridField("fecha", "Fecha",80);
      fechaField.setAlign(getHoverAlign().CENTER);
      ListGridField horaField = new ListGridField("hora", "Hora",70);
      horaField.setAlign(getHoverAlign().CENTER);
      
// 		agrego este set para que solo recupere el tiempo y no la fecha de la fuente: http://forums.smartclient.com/showthread.php?t=22206
      horaField.setTimeFormatter(TimeDisplayFormat.TOPADDED24HOURTIME);
      
      ListGridField promVelAltura1Field = new ListGridField("velPromAlt1", "Vel. Prom. Altura Uno", 120);
      promVelAltura1Field.setAlign(getHoverAlign().CENTER);
      ListGridField promDirAltura1Field = new ListGridField("dirPromAlt1", "Dir. Prom. Altura Uno", 120);
      promDirAltura1Field.setAlign(getHoverAlign().CENTER);
      ListGridField promTempAltura1Field = new ListGridField("tempPromAlt1", "Temp. Prom. Altura Uno", 130);
      promTempAltura1Field.setAlign(getHoverAlign().CENTER);
	      // para mostrar los grados centigrados  
      promTempAltura1Field.setType(ListGridFieldType.FLOAT);  
      promTempAltura1Field.setCellFormatter(new CellFormatter() {  
          public String format(Object value, ListGridRecord record, int rowNum, int colNum) {  
        	  NumberFormat nf = NumberFormat.getFormat("0.00");                                              	
              try {  
                  return nf.format(((Number)value).floatValue()) + " °C";  
              } catch (Exception e) {                                                	 
                  return null;
              }  
           }  
    	  });
      ListGridField desvioAltura1Field = new ListGridField("desvioAlt1", "Desvio Altura Uno", 100);
      desvioAltura1Field.setAlign(getHoverAlign().CENTER);
      ListGridField velMaxAltura1Field = new ListGridField("velMaxAlt1", "Vel. Máxima Altura Uno", 130);
      velMaxAltura1Field.setAlign(getHoverAlign().CENTER);
      ListGridField dirMaxAltura1Field = new ListGridField("dirMaxAlt1", "Dir. Máxima Altura Uno", 130);
      dirMaxAltura1Field.setAlign(getHoverAlign().CENTER);
      ListGridField tempMaxAltura1Field = new ListGridField("tempMaxAlt1", "Temp. Máxima Altura Uno", 140);
      tempMaxAltura1Field.setAlign(getHoverAlign().CENTER);
	      // para mostrar los grados centigrados  
      tempMaxAltura1Field.setType(ListGridFieldType.FLOAT);  
      tempMaxAltura1Field.setCellFormatter(new CellFormatter() {  
          public String format(Object value, ListGridRecord record, int rowNum, int colNum) {  
        	  NumberFormat nf = NumberFormat.getFormat("0.00");                                              	
              try {  
                  return nf.format(((Number)value).floatValue()) + " °C";  
              } catch (Exception e) {                                                	 
                  return null;
              }  
           }  
    	  });
      ListGridField velMinAltura1Field = new ListGridField("velMinAlt1", "Vel. Minima Altura Uno", 120);
      velMinAltura1Field.setAlign(getHoverAlign().CENTER);
      ListGridField dirMinAltura1Field = new ListGridField("dirMinAlt1", "Dir. Minima Altura Uno", 120);
      dirMinAltura1Field.setAlign(getHoverAlign().CENTER);
      ListGridField tempMinAltura1Field = new ListGridField("tempMinAlt1", "Temp. Minima Altura Uno", 130);
      tempMinAltura1Field.setAlign(getHoverAlign().CENTER);
	      // para mostrar los grados centigrados  
      tempMinAltura1Field.setType(ListGridFieldType.FLOAT);  
      tempMinAltura1Field.setCellFormatter(new CellFormatter() {  
        public String format(Object value, ListGridRecord record, int rowNum, int colNum) {  
      	  NumberFormat nf = NumberFormat.getFormat("0.00");                                              	
            try {  
                return nf.format(((Number)value).floatValue()) + " °C";  
            } catch (Exception e) {                                                	 
                return null;
            }  
         }  
  	  });
      ListGridField vacioField = new ListGridField("vacioField", " "); 
      
      setFields(idDatosField,fechaField,horaField,promVelAltura1Field,promDirAltura1Field,promTempAltura1Field,desvioAltura1Field,velMaxAltura1Field,
    		  dirMaxAltura1Field,tempMaxAltura1Field,velMinAltura1Field,dirMinAltura1Field,tempMinAltura1Field,vacioField);
	  
	  // poblar la cuadricula
	    this.setDataSource(DatosVistaAlturaUnoSource.getInstance());  
	    this.setAutoFetchData(true); 
//	    this.setSortField(1);
	    this.sort();
	    this.draw();
	    this.markForRedraw();
	  
        }else  if (parametro.equalsIgnoreCase("ViewDatosAltura2")){
//        	Mensaje.MensajeError("Segunda Altura " + parametro );
        	ListGridField  idDatosField = new ListGridField("idDatosTodos", "ID", 50);
      	  idDatosField.setHidden(true);
                 
            
            ListGridField fechaField = new ListGridField("fecha", "Fecha",80);
            fechaField.setAlign(getHoverAlign().CENTER);
            ListGridField horaField = new ListGridField("hora", "Hora",70);
            horaField.setAlign(getHoverAlign().CENTER);
            
//       		agrego este set para que solo recupere el tiempo y no la fecha de la fuente: http://forums.smartclient.com/showthread.php?t=22206
            horaField.setTimeFormatter(TimeDisplayFormat.TOPADDED24HOURTIME);
            
            ListGridField promVelAltura2Field = new ListGridField("velPromAlt2", "Vel. Prom. Altura Dos", 120);
            promVelAltura2Field.setAlign(getHoverAlign().CENTER);
            ListGridField promDirAltura2Field = new ListGridField("dirPromAlt2", "Dir. Prom. Altura Dos", 120);
            promDirAltura2Field.setAlign(getHoverAlign().CENTER);
            ListGridField promTempAltura2Field = new ListGridField("tempPromAlt2", "Temp. Prom. Altura Dos", 130);
            promTempAltura2Field.setAlign(getHoverAlign().CENTER);
  	      // para mostrar los grados centigrados  
            promTempAltura2Field.setType(ListGridFieldType.FLOAT);  
  	      	promTempAltura2Field.setCellFormatter(new CellFormatter() {  
              public String format(Object value, ListGridRecord record, int rowNum, int colNum) {  
            	  NumberFormat nf = NumberFormat.getFormat("0.00");                                              	
                  try {  
                      return nf.format(((Number)value).floatValue()) + " °C";  
                  } catch (Exception e) {                                                	 
                      return null;
                  }  
               }  
        	  });
            
            ListGridField desvioAltura2Field = new ListGridField("desvioAlt2", "Desvio Altura Dos", 100);
            desvioAltura2Field.setAlign(getHoverAlign().CENTER);
            ListGridField velMaxAltura2Field = new ListGridField("velMaxAlt2", "Vel. Máxima Altura Dos", 130);
            velMaxAltura2Field.setAlign(getHoverAlign().CENTER);
            ListGridField dirMaxAltura2Field = new ListGridField("dirMaxAlt2", "Dir. Máxima Altura Dos", 130);
            dirMaxAltura2Field.setAlign(getHoverAlign().CENTER);
            ListGridField tempMaxAltura2Field = new ListGridField("tempMaxAlt2", "Temp. Máxima Altura Dos", 140);
            tempMaxAltura2Field.setAlign(getHoverAlign().CENTER);
  	      // para mostrar los grados centigrados  
            tempMaxAltura2Field.setType(ListGridFieldType.FLOAT);  
            tempMaxAltura2Field.setCellFormatter(new CellFormatter() {  
              public String format(Object value, ListGridRecord record, int rowNum, int colNum) {  
            	  NumberFormat nf = NumberFormat.getFormat("0.00");                                              	
                  try {  
                      return nf.format(((Number)value).floatValue()) + " °C";  
                  } catch (Exception e) {                                                	 
                      return null;
                  }  
               }  
        	  });            
            ListGridField velMinAltura2Field = new ListGridField("velMinAlt2", "Vel. Minima Altura Dos", 120);
            velMinAltura2Field.setAlign(getHoverAlign().CENTER);
            ListGridField dirMinAltura2Field = new ListGridField("dirMinAlt2", "Dir. Minima Altura Dos", 120);
            dirMinAltura2Field.setAlign(getHoverAlign().CENTER);
            ListGridField tempMinAltura2Field = new ListGridField("tempMinAlt2", "Temp. Minima Altura Dos", 130);
            tempMinAltura2Field.setAlign(getHoverAlign().CENTER);
  	      // para mostrar los grados centigrados  
            tempMinAltura2Field.setType(ListGridFieldType.FLOAT);  
            tempMinAltura2Field.setCellFormatter(new CellFormatter() {  
              public String format(Object value, ListGridRecord record, int rowNum, int colNum) {  
            	  NumberFormat nf = NumberFormat.getFormat("0.00");                                              	
                  try {  
                      return nf.format(((Number)value).floatValue()) + " °C";  
                  } catch (Exception e) {                                                	 
                      return null;
                  }  
               }  
        	  });
            ListGridField vacioField = new ListGridField("vacioField", " "); 
            
            setFields(idDatosField,fechaField,horaField,promVelAltura2Field,promDirAltura2Field,promTempAltura2Field,desvioAltura2Field,velMaxAltura2Field,
          		  dirMaxAltura2Field,tempMaxAltura2Field,velMinAltura2Field,dirMinAltura2Field,tempMinAltura2Field,vacioField);
      	  
      	  // poblar la cuadricula
      	    this.setDataSource(DatosVistaAlturaDosSource.getInstance());  
      	    this.setAutoFetchData(true); 
//      	    this.setSortField(1);
      	    this.sort();
      	    this.draw();
      	    this.markForRedraw();
               }else if (parametro.equalsIgnoreCase("ViewDatosAltura3")){
//            	   Mensaje.MensajeError("Tercer Altura " + parametro );
            	   ListGridField  idDatosField = new ListGridField("idDatosTodos", "ID", 50);
            		  idDatosField.setHidden(true);
            	           
            	      
            	      ListGridField fechaField = new ListGridField("fecha", "Fecha",80);
            	      fechaField.setAlign(getHoverAlign().CENTER);
            	      ListGridField horaField = new ListGridField("hora", "Hora",70);
            	      horaField.setAlign(getHoverAlign().CENTER);
            	      
//            	 		agrego este set para que solo recupere el tiempo y no la fecha de la fuente: http://forums.smartclient.com/showthread.php?t=22206
            	      horaField.setTimeFormatter(TimeDisplayFormat.TOPADDED24HOURTIME);
            	      
            	      ListGridField promVelAltura3Field = new ListGridField("velPromAlt3", "Vel. Prom. Altura Tres", 120);
            	      promVelAltura3Field.setAlign(getHoverAlign().CENTER);
            	      ListGridField promDirAltura3Field = new ListGridField("dirPromAlt3", "Dir. Prom. Altura Tres", 120);
            	      promDirAltura3Field.setAlign(getHoverAlign().CENTER);
            	      ListGridField promTempAltura3Field = new ListGridField("tempPromAlt3", "Temp. Prom. Altura Tres", 130);            	      
            	      promTempAltura3Field.setAlign(getHoverAlign().CENTER);
            	      // para mostrar los grados centigrados  
            	      promTempAltura3Field.setType(ListGridFieldType.FLOAT);  
            	      promTempAltura3Field.setCellFormatter(new CellFormatter() {  
                        public String format(Object value, ListGridRecord record, int rowNum, int colNum) {  
                      	  NumberFormat nf = NumberFormat.getFormat("0.00");                                              	
                            try {  
                                return nf.format(((Number)value).floatValue()) + " °C";  
                            } catch (Exception e) {                                                	 
                                return null;
                            }  
                         }  
                  	  });
            	      ListGridField desvioAltura3Field = new ListGridField("desvioAlt3", "Desvio Altura Tres", 100);
            	      desvioAltura3Field.setAlign(getHoverAlign().CENTER);
            	      ListGridField velMaxAltura3Field = new ListGridField("velMaxAlt3", "Vel. Máxima Altura Tres", 130);
            	      velMaxAltura3Field.setAlign(getHoverAlign().CENTER);
            	      ListGridField dirMaxAltura3Field = new ListGridField("dirMaxAlt3", "Dir. Máxima Altura Tres", 130);
            	      dirMaxAltura3Field.setAlign(getHoverAlign().CENTER);
            	      ListGridField tempMaxAltura3Field = new ListGridField("tempMaxAlt3", "Temp. Máxima Altura Tres", 140);            	      
            	      tempMaxAltura3Field.setAlign(getHoverAlign().CENTER);
            	      // para mostrar los grados centigrados  
            	      tempMaxAltura3Field.setType(ListGridFieldType.FLOAT);  
            	      tempMaxAltura3Field.setCellFormatter(new CellFormatter() {  
                        public String format(Object value, ListGridRecord record, int rowNum, int colNum) {  
                      	  NumberFormat nf = NumberFormat.getFormat("0.00");                                              	
                            try {  
                                return nf.format(((Number)value).floatValue()) + " °C";  
                            } catch (Exception e) {                                                	 
                                return null;
                            }  
                         }  
                  	  });
            	      ListGridField velMinAltura3Field = new ListGridField("velMinAlt3", "Vel. Minima Altura Tres", 120);
            	      velMinAltura3Field.setAlign(getHoverAlign().CENTER);
            	      ListGridField dirMinAltura3Field = new ListGridField("dirMinAlt3", "Dir. Minima Altura Tres", 120);
            	      dirMinAltura3Field.setAlign(getHoverAlign().CENTER);
            	      ListGridField tempMinAltura3Field = new ListGridField("tempMinAlt3", "Temp. Minima Altura Tres", 130);
            	      tempMinAltura3Field.setAlign(getHoverAlign().CENTER);
            	      // para mostrar los grados centigrados  
            	      tempMinAltura3Field.setType(ListGridFieldType.FLOAT);  
            	      tempMinAltura3Field.setCellFormatter(new CellFormatter() {  
                        public String format(Object value, ListGridRecord record, int rowNum, int colNum) {  
                      	  NumberFormat nf = NumberFormat.getFormat("0.00");                                              	
                            try {  
                                return nf.format(((Number)value).floatValue()) + " °C";  
                            } catch (Exception e) {                                                	 
                                return null;
                            }  
                         }  
                  	  });  
            	      
            	      
            	      ListGridField vacioField = new ListGridField("vacioField", " "); 
            	      
            	      setFields(idDatosField,fechaField,horaField,promVelAltura3Field,promDirAltura3Field,promTempAltura3Field,desvioAltura3Field,velMaxAltura3Field,
            	    		  dirMaxAltura3Field,tempMaxAltura3Field,velMinAltura3Field,dirMinAltura3Field,tempMinAltura3Field,vacioField);
            		  
            		  // poblar la cuadricula
            		    this.setDataSource(DatosVistaAlturaTresSource.getInstance());  
            		    this.setAutoFetchData(true); 
//            		    this.setSortField(1);
            		    this.sort();
            		    this.draw();
            		    this.markForRedraw();
                     }else if (parametro.equalsIgnoreCase("DatosPerfilTemporarioHora")){
//                    	 Mensaje.MensajeError("Perfil Temporario Hora " + parametro );
                    	 
                    	 	ListGridField  idDatosField = new ListGridField("idDatosPerfilTemporarioHora", "ID", 50);
                    	    idDatosField.setHidden(true);                	                 
                    	     //----------------------
                    	    ListGridField horaField = new ListGridField("hora", "Hora",50);  
                    	      horaField.getAutoFitWidth();
                    	      horaField.setAlign(getHoverAlign().CENTER);
                    	    ListGridField cantidadAlt1Field = new ListGridField("cantidadAlt1", "Cant. Datos Altura Uno", 130);
                    	    cantidadAlt1Field.setAlign(getHoverAlign().CENTER);
                    	    ListGridField cantidadAlt2Field = new ListGridField("cantidadAlt2", "Cant. Datos Altura Dos", 130);
                    	    cantidadAlt2Field.setAlign(getHoverAlign().CENTER);
                    	    ListGridField cantidadAlt3Field = new ListGridField("cantidadAlt3", "Cant. Datos Altura Tres", 130);
                    	    cantidadAlt3Field.setAlign(getHoverAlign().CENTER);
                    	      
                    	    ListGridField velHoraAltura1Field = new ListGridField("totalVelocidadPromedioAlt1", "Vel. Total Hora Altura Uno", 150);
                    	    velHoraAltura1Field.setAlign(getHoverAlign().CENTER);
                    	    ListGridField velHoraAltura2Field = new ListGridField("totalVelocidadPromedioAlt2", "Vel. Total Hora Altura Dos", 150);
                    	    velHoraAltura2Field.setAlign(getHoverAlign().CENTER);
                    	    ListGridField velHoraAltura3Field = new ListGridField("totalVelocidadPromedioAlt3", "Vel. Total Hora Altura Tres", 150);
                    	    velHoraAltura3Field.setAlign(getHoverAlign().CENTER);
                    	    																					 
                    	    ListGridField promVelHoraAltura1Field = new ListGridField("promedioVelocidadAlt1", "Vel. Prom. Hora Altura Uno", 150);
                    	    promVelHoraAltura1Field.setAlign(getHoverAlign().RIGHT);                    	   
                    	    ListGridField promVelHoraAltura2Field = new ListGridField("promedioVelocidadAlt2", "Vel. Prom. Hora Altura Dos", 150);
                    	    promVelHoraAltura2Field.setAlign(getHoverAlign().RIGHT);
                    	    ListGridField promVelHoraAltura3Field = new ListGridField("promedioVelocidadAlt3", "Vel. Prom. Hora Altura Tres", 150);
                    	    promVelHoraAltura3Field.setAlign(getHoverAlign().RIGHT);
                    	    
                    	    ListGridField desvioAltura1Field = new ListGridField("totalDesvioAlt1", "Desvio Total Hora Altura Uno", 160);
                    	    desvioAltura1Field.setAlign(getHoverAlign().CENTER);
                    	    ListGridField desvioAltura2Field = new ListGridField("totalDesvioAlt2", "Desvio Total Hora Altura Dos", 160);
                    	    desvioAltura2Field.setAlign(getHoverAlign().CENTER);
                    	    ListGridField desvioAltura3Field = new ListGridField("totalDesvioAlt3", "Desvio Total Hora Altura Tres", 160);
                    	    desvioAltura3Field.setAlign(getHoverAlign().CENTER);

                    	    ListGridField promDesvioAltura1Field = new ListGridField("promedioDesvioAlt1", "Desvio Prom. Hora Altura Uno", 160);
                    	    promDesvioAltura1Field.setAlign(getHoverAlign().RIGHT);
                    	    ListGridField promDesvioAltura2Field = new ListGridField("promedioDesvioAlt2", "Desvio Prom. Hora Altura Dos", 160);
                    	    promDesvioAltura2Field.setAlign(getHoverAlign().RIGHT);
                    	    ListGridField promDesvioAltura3Field = new ListGridField("promedioDesvioAlt3", "Desvio Prom. Hora Altura Tres", 160);
                    	    promDesvioAltura3Field.setAlign(getHoverAlign().RIGHT);  
                    	     
                    	    ListGridField vacioField = new ListGridField("vacioField", " "); 

                    	    // seteo los campos en la cuadricula 
                    	    setFields(idDatosField,horaField,cantidadAlt1Field,cantidadAlt2Field,cantidadAlt3Field,velHoraAltura1Field,velHoraAltura2Field,velHoraAltura3Field,promVelHoraAltura1Field,
                    	    		  promVelHoraAltura2Field,promVelHoraAltura3Field, desvioAltura1Field,desvioAltura2Field,desvioAltura3Field,promDesvioAltura1Field,promDesvioAltura2Field,promDesvioAltura3Field,
                    	    		  vacioField);                    	                        	    
                    	    //----------------------                    	    
                    	    
                    	    // poblar la cuadricula
                    	    this.setDataSource(DatosPerfilTemporarioHoraSource.getInstance());  
                    	    this.setAutoFetchData(true); 
//                    	    this.setSortField(1);
                    	    this.sort();
                    	    this.draw();
                    	    this.markForRedraw();
                    	 
                           }else if (parametro.equalsIgnoreCase("DatosPerfilTemporarioMes")){
//                        	   Mensaje.MensajeError("Perfil Temporario Mes: " + parametro );
                        	   ListGridField  idDatosField = new ListGridField("idDatosPerfilTemporarioMes", "ID", 50);
                       	    idDatosField.setHidden(true);
                       	                 

                       	     //----------------------
                       	    ListGridField horaField = new ListGridField("mes", "Mes",50);  
                       	      horaField.getAutoFitWidth();
                       	      horaField.setAlign(getHoverAlign().CENTER);
                       	    ListGridField cantidadAlt1Field = new ListGridField("cantidadAlt1", "Cant. Datos Altura Uno", 130);
                       	    cantidadAlt1Field.setAlign(getHoverAlign().CENTER);
                       	    ListGridField cantidadAlt2Field = new ListGridField("cantidadAlt2", "Cant. Datos Altura Dos", 130);
                       	    cantidadAlt2Field.setAlign(getHoverAlign().CENTER);
                       	    ListGridField cantidadAlt3Field = new ListGridField("cantidadAlt3", "Cant. Datos Altura Tres", 130);
                       	    cantidadAlt3Field.setAlign(getHoverAlign().CENTER);
                       	      
                       	    ListGridField velHoraAltura1Field = new ListGridField("totalVelocidadPromedioAlt1", "Vel. Total Mes Altura Uno", 150);
                       	    velHoraAltura1Field.setAlign(getHoverAlign().CENTER);
                       	    ListGridField velHoraAltura2Field = new ListGridField("totalVelocidadPromedioAlt2", "Vel. Total Mes Altura Dos", 150);
                       	    velHoraAltura2Field.setAlign(getHoverAlign().CENTER);
                       	    ListGridField velHoraAltura3Field = new ListGridField("totalVelocidadPromedioAlt3", "Vel. Total Mes Altura Tres", 150);
                       	    velHoraAltura3Field.setAlign(getHoverAlign().CENTER);
                       	    																					 
                       	    ListGridField promVelHoraAltura1Field = new ListGridField("promedioVelocidadAlt1", "Vel. Prom. Mes Altura Uno", 150);
                       	    ListGridField promVelHoraAltura2Field = new ListGridField("promedioVelocidadAlt2", "Vel. Prom. Mes Altura Dos", 150);
                       	    ListGridField promVelHoraAltura3Field = new ListGridField("promedioVelocidadAlt3", "Vel. Prom. Mes Altura Tres", 150);
                       	    
                       	    ListGridField desvioAltura1Field = new ListGridField("totalDesvioAlt1", "Desvio Total Mes Altura Uno", 160);
                       	    desvioAltura1Field.setAlign(getHoverAlign().CENTER);
                       	    ListGridField desvioAltura2Field = new ListGridField("totalDesvioAlt2", "Desvio Total Mes Altura Dos", 160);
                       	    desvioAltura2Field.setAlign(getHoverAlign().CENTER);
                       	    ListGridField desvioAltura3Field = new ListGridField("totalDesvioAlt3", "Desvio Total Mes Altura Tres", 160);
                       	    desvioAltura3Field.setAlign(getHoverAlign().CENTER);

                       	    ListGridField promDesvioAltura1Field = new ListGridField("promedioDesvioAlt1", "Desvio Prom. Mes Altura Uno", 160);
                       	    promDesvioAltura1Field.setAlign(getHoverAlign().RIGHT);
                       	    ListGridField promDesvioAltura2Field = new ListGridField("promedioDesvioAlt2", "Desvio Prom. Mes Altura Dos", 160);
                       	 	promDesvioAltura2Field.setAlign(getHoverAlign().RIGHT);
                       	 	ListGridField promDesvioAltura3Field = new ListGridField("promedioDesvioAlt3", "Desvio Prom. Mes Altura Tres", 160);
                       		promDesvioAltura3Field.setAlign(getHoverAlign().RIGHT);
                       	     
                       	    ListGridField vacioField = new ListGridField("vacioField", " "); 

                       	    // seteo los campos en la cuadricula 
                       	    setFields(idDatosField,horaField,cantidadAlt1Field,cantidadAlt2Field,cantidadAlt3Field,velHoraAltura1Field,velHoraAltura2Field,velHoraAltura3Field,promVelHoraAltura1Field,
                       	    		  promVelHoraAltura2Field,promVelHoraAltura3Field, desvioAltura1Field,desvioAltura2Field,desvioAltura3Field,promDesvioAltura1Field,promDesvioAltura2Field,promDesvioAltura3Field,
                       	    		  vacioField);
                       	    
                       	    
                       	    //----------------------                    	    
                       	    
                       	    // poblar la cuadricula
                       	    this.setDataSource(DatosPerfilTemporarioMesSource.getInstance());  
                       	    this.setAutoFetchData(true); 
                       	    this.setSortField(0);
                       	    this.draw();
                       	    this.markForRedraw();
                                 } else if (parametro.equalsIgnoreCase("DatosPerfilVertical")){
//                                	 Mensaje.MensajeError("Perfil Vertical " + parametro );
                                	 ListGridField  idDatosField = new ListGridField("idDatosPerfilVertical", "ID");
                                	 idDatosField.setHidden(true);
                                	 
                                	//----------------------        
                                     ListGridField alturaField = new ListGridField("altura", "Altura", 50);  
                                     alturaField.setAlign(getHoverAlign().CENTER);
                                     ListGridField cantidadxRenglonField = new ListGridField("cantidadXrenglon", "Cantidad de Datos",100);     //100 
                                     cantidadxRenglonField.setAlign(getHoverAlign().CENTER);
                                     ListGridField totalVelPromField = new ListGridField("totalVelocidadPromedio", "Total Velocidad Promedio",150); //150
                                     totalVelPromField.setAlign(getHoverAlign().CENTER);
                                     ListGridField totalTempPromField = new ListGridField("totalTempPromedio", "Total Temperatura Promedio",150); //150
                                     totalTempPromField.setAlign(getHoverAlign().CENTER);
                                     ListGridField promVelRenglonField = new ListGridField("promedioVelocidadRenglon", "Velocidad Promedio x Renglon",180); //150                                   
                                     promVelRenglonField.setAlign(getHoverAlign().CENTER);
                                     ListGridField promTempRenglonField = new ListGridField("promedioTempRenglon", "Temperatura Promedio x Renglon",180); //150
                                     promTempRenglonField.setAlign(getHoverAlign().CENTER);
                                     ListGridField vacioField = new ListGridField("vacioField", " "); 
                                     
                                     setFields(idDatosField,alturaField,cantidadxRenglonField,totalVelPromField,totalTempPromField,promVelRenglonField,promTempRenglonField, vacioField);
                                   //----------------------        
                                  // poblar la cuadricula
                               	     this.setDataSource(DatosPerfilVerticalSource.getInstance());  
                               	     this.setAutoFetchData(true); 
//                               	    this.setSortField(1);
                               	     this.sort();
                               	     this.draw();
                               	     this.markForRedraw();
                                	 
                                        }else if (parametro.equalsIgnoreCase("DireccionVientoRangos")){
//                                        	Mensaje.MensajeError("Diercción del Viento: " + parametro );
                                        	
                                        	 ListGridField  idDatosField = new ListGridField("idDireccionVientoRangos", "ID", 50);
                                      	    idDatosField.setHidden(true);                	                 
                                      	     //----------------------                             	   
                                      	    ListGridField divisionField = new ListGridField("division", "Division", 70);
                                      	    divisionField.setAlign(getHoverAlign().CENTER);
                                      	    ListGridField inicioRangoField = new ListGridField("inicioRango", "Inicio del Rango", 100);
                                      	    inicioRangoField.setAlign(getHoverAlign().CENTER);
                                      	    ListGridField finRangoField = new ListGridField("finRango", "Fin del Rango", 100);
                                      	    finRangoField.setAlign(getHoverAlign().CENTER);
                                      	    ListGridField cantDatosAltura1Field = new ListGridField("cantDatosAlt1", "Cant. Datos Altura Uno", 130);                                      	    																		  
                                      	    cantDatosAltura1Field.setAlign(getHoverAlign().CENTER);
                                      	    ListGridField cantDatosAltura2Field = new ListGridField("cantDatosAlt2", "Cant. Datos Altura Dos", 130);
                                      	    cantDatosAltura2Field.setAlign(getHoverAlign().CENTER);                             	    
                                      	    ListGridField cantDatosAltura3Field = new ListGridField("cantDatosAlt3", "Cant. Datos Altura Tres", 130);
                                      	    cantDatosAltura3Field.setAlign(getHoverAlign().CENTER);
                                      	    ListGridField porcentajeAltura1Field = new ListGridField("porcentajeAlt1", "Porcentaje Altura Uno", 130);
                                      	    porcentajeAltura1Field.setAlign(getHoverAlign().RIGHT);
                                      	// para mostrar el porcentaje  
                                        	porcentajeAltura1Field.setType(ListGridFieldType.FLOAT);  
                                        	porcentajeAltura1Field.setCellFormatter(new CellFormatter() {  
                                              public String format(Object value, ListGridRecord record, int rowNum, int colNum) {  
                                            	  NumberFormat nf = NumberFormat.getFormat("0.00");                                              	
                                                  try {  
                                                      return nf.format(((Number)value).floatValue()) + " %";  
                                                  } catch (Exception e) {                                                	 
                                                      return null;
                                                  }  
                                              }  
                                        	});  
                                      	    
                                      	    ListGridField porcentajeAltura2Field = new ListGridField("porcentajeAlt2", "Porcentaje Altura Dos", 130);
                                      	    porcentajeAltura2Field.setAlign(getHoverAlign().RIGHT);
                                      	  // para mostrar el porcentaje  
                                        	porcentajeAltura2Field.setType(ListGridFieldType.FLOAT);  
                                        	porcentajeAltura2Field.setCellFormatter(new CellFormatter() {  
                                              public String format(Object value, ListGridRecord record, int rowNum, int colNum) {  
                                            	  NumberFormat nf = NumberFormat.getFormat("0.00");                                              	
                                                  try {  
                                                      return nf.format(((Number)value).floatValue()) + " %";  
                                                  } catch (Exception e) {                                                	 
                                                      return null;
                                                  }  
                                              }  
                                        	});  
                                      	    ListGridField porcentajeAltura3Field = new ListGridField("porcentajeAlt3", "Porcentaje Altura Tres", 130);                  
                                      	    porcentajeAltura3Field.setAlign(getHoverAlign().RIGHT);
                                      	    //para mostrar el porcentaje
                                      	    porcentajeAltura3Field.setType(ListGridFieldType.FLOAT);  
                                      	    porcentajeAltura3Field.setCellFormatter(new CellFormatter() {  
                                             public String format(Object value, ListGridRecord record, int rowNum, int colNum) {  
                                          	  NumberFormat nf = NumberFormat.getFormat("0,00");                                            	 
                                                try {  
                                                    return nf.format(((Number)value).floatValue()) + " %";  
                                                } catch (Exception e) {  
                                                    return null;
                                                }  
                                              }  
                                      	    });  
                                      	    
                                      	    
                                      	    ListGridField vacioField = new ListGridField("vacioField", " "); 
                                                                                                            	                                 	   
                                      	    // seteo los campos en la cuadricula 
                                      	   setFields(idDatosField,divisionField,inicioRangoField,finRangoField,cantDatosAltura1Field,cantDatosAltura2Field,cantDatosAltura3Field,porcentajeAltura1Field,
                                           		  porcentajeAltura2Field,porcentajeAltura3Field,vacioField);
                                       	                        	                        	    
                                      	    //----------------------                    	    
                                      	    
                                      	    // poblar la cuadricula
                                      	    this.setDataSource(DatosDireccionVientoRangosSource.getInstance());  
                                      	    this.setAutoFetchData(true); 
                                      	    this.setSortField(0);
//                                      	    this.sort();                                  	                                        	  
                                      	    this.draw();
                                      	    this.markForRedraw();
                                              }else if (parametro.equalsIgnoreCase("DatosFrecuenciaVelocidad")){
//                                              	Mensaje.MensajeError("Diercción del Viento: " + parametro );
                                              	
                                             	 ListGridField  idDatosField = new ListGridField("idDatosFrecuenciaVelocidad", "ID", 50);
                                           	    idDatosField.setHidden(true);                	                 
                                           	     //----------------------                             	   
                                           	    ListGridField velocidadField = new ListGridField("velocidad", "Velocidad", 70);
                                           	    velocidadField.setAlign(getHoverAlign().CENTER);
                                           	    ListGridField cantDatosAltura1Field = new ListGridField("cantDatosAlt1", "Cant. Datos Altura Uno", 130);                                      	    																		  
                                           	    cantDatosAltura1Field.setAlign(getHoverAlign().CENTER);
                                           	    ListGridField cantDatosAltura2Field = new ListGridField("cantDatosAlt2", "Cant. Datos Altura Dos", 130);
                                           	    cantDatosAltura2Field.setAlign(getHoverAlign().CENTER);                             	    
                                           	    ListGridField cantDatosAltura3Field = new ListGridField("cantDatosAlt3", "Cant. Datos Altura Tres", 130);
                                           	    cantDatosAltura3Field.setAlign(getHoverAlign().CENTER);
                                           	    ListGridField porcentajeAltura1Field = new ListGridField("porcentajeAlt1", "Porcentaje Altura Uno", 130);
                                           	    porcentajeAltura1Field.setAlign(getHoverAlign().RIGHT);
                                           	// para mostrar el porcentaje  
                                             	porcentajeAltura1Field.setType(ListGridFieldType.FLOAT);  
                                             	porcentajeAltura1Field.setCellFormatter(new CellFormatter() {  
                                                   public String format(Object value, ListGridRecord record, int rowNum, int colNum) {  
                                                 	  NumberFormat nf = NumberFormat.getFormat("0.00");                                              	
                                                       try {  
                                                           return nf.format(((Number)value).floatValue()) + " %";  
                                                       } catch (Exception e) {                                                	 
                                                           return null;
                                                       }  
                                                   }  
                                             	});  
                                           	    
                                           	    ListGridField porcentajeAltura2Field = new ListGridField("porcentajeAlt2", "Porcentaje Altura Dos", 130);
                                           	    porcentajeAltura2Field.setAlign(getHoverAlign().RIGHT);
                                           	  // para mostrar el porcentaje  
                                             	porcentajeAltura2Field.setType(ListGridFieldType.FLOAT);  
                                             	porcentajeAltura2Field.setCellFormatter(new CellFormatter() {  
                                                   public String format(Object value, ListGridRecord record, int rowNum, int colNum) {  
                                                 	  NumberFormat nf = NumberFormat.getFormat("0.00");                                              	
                                                       try {  
                                                           return nf.format(((Number)value).floatValue()) + " %";  
                                                       } catch (Exception e) {                                                	 
                                                           return null;
                                                       }  
                                                   }  
                                             	});  
                                           	    ListGridField porcentajeAltura3Field = new ListGridField("porcentajeAlt3", "Porcentaje Altura Tres", 130);                  
                                           	    porcentajeAltura3Field.setAlign(getHoverAlign().RIGHT);
                                           	    //para mostrar el porcentaje
                                           	    porcentajeAltura3Field.setType(ListGridFieldType.FLOAT);  
                                           	    porcentajeAltura3Field.setCellFormatter(new CellFormatter() {  
                                                  public String format(Object value, ListGridRecord record, int rowNum, int colNum) {  
                                               	  NumberFormat nf = NumberFormat.getFormat("0,00");                                            	 
                                                     try {  
                                                         return nf.format(((Number)value).floatValue()) + " %";  
                                                     } catch (Exception e) {  
                                                         return null;
                                                     }  
                                                   }  
                                           	    });  
                                           	    
                                           	    
                                           	    ListGridField vacioField = new ListGridField("vacioField", " "); 
                                                                                                                 	                                 	   
                                           	    // seteo los campos en la cuadricula 
                                           	   setFields(idDatosField,velocidadField,cantDatosAltura1Field,cantDatosAltura2Field,cantDatosAltura3Field,porcentajeAltura1Field,
                                                		  porcentajeAltura2Field,porcentajeAltura3Field,vacioField);
                                            	                        	                        	    
                                           	    //----------------------                    	    
                                           	    
                                           	    // poblar la cuadricula
                                           	    this.setDataSource(DatosFrecuenciaVelocidadSource.getInstance());  
                                           	    this.setAutoFetchData(true); 
                                           	    this.setSortField(0);
//                                           	    this.sort();                                  	                                        	  
                                           	    this.draw();
                                           	    this.markForRedraw();
                                                   }
  
//    // inicializo los campos de las cuadricula   
//  ListGridField iconField = new ListGridField("icon", "#", 27);
//  iconField.setImageSize(16); 
//  iconField.setAlign(Alignment.CENTER);
//  // defino que va a contener una imagen
//  iconField.setType(ListGridFieldType.IMAGE);  
//  iconField.setImageURLPrefix("icons/16/");  
//  iconField.setImageURLSuffix(".png");  
//    
//  ListGridField accountNameField = new ListGridField("accountName", "Account Name", 320);  
//  ListGridField mainPhoneField = new ListGridField("mainPhone", "Main Phone", 100);  
//  ListGridField locationField = new ListGridField("location", "Location", 100);  
//  ListGridField primaryContactField = new ListGridField("primaryContact", "Primary Contact", 140); 
//  // defino que va a contener un link
//  primaryContactField.setType(ListGridFieldType.LINK);  
//  ListGridField emailPrimaryContactField = new ListGridField("emailPrimaryContact", 
//                                 "Email (Primary Contact)", 180);   
//  ListGridField emptyField = new ListGridField("emptyField", " ");    
//    
//    // seteo los campos en la cuadricula  
//  this.setFields(new ListGridField[] {iconField, accountNameField, mainPhoneField, locationField, 
//           primaryContactField, emailPrimaryContactField, emptyField });  
//  
//  // poblar la cuadricula
//  this.setData(AccountData.getRecords());  
  }
}