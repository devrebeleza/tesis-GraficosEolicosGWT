package com.paquete.graficos.eolicos.data;



import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.*;


public class DatosArchivoSource extends DataSource {

  private static DatosArchivoSource instance = null;
  private String nombreTabla = "DatosArchivo";
  public static DatosArchivoSource getInstance(){
      if(instance == null){
        instance = new DatosArchivoSource("datosArchivo_XML");
      }
      return instance;
  }
  
  public DatosArchivoSource(String id){
    
    setID(id);
    setRecordXPath("/List/"+nombreTabla);
      
      DataSourceTextField  idDatosField = new DataSourceTextField("idDatosArchivo", "ID", 50,true);
      idDatosField.setPrimaryKey(true);
      idDatosField.setCanEdit(false);
           
      
      DataSourceTextField lugarAdquisicionField = new DataSourceTextField("lugarAdquisicion", "Lugar de Adquisición");  
      DataSourceTextField deadband1Field = new DataSourceTextField("deadband1", "Deadband 1", 100);
      DataSourceTextField deadband2Field = new DataSourceTextField("deadband2", "Deadband 2", 100);
      DataSourceTextField deadband3Field = new DataSourceTextField("deadband3", "Deadband 3", 100);
      DataSourceTextField sobreescrituraField = new DataSourceTextField("sobreescritura", "Sobreescritura", 100);
      DataSourceTextField altura1Field = new DataSourceTextField("altura1", "Altura Uno", 100);
      DataSourceTextField altura2Field = new DataSourceTextField("altura2", "Altura Dos", 100);
      DataSourceTextField altura3Field = new DataSourceTextField("altura3", "Altura Tres", 100);
      DataSourceTextField intervaloField = new DataSourceTextField("intervalo", "Intervalo", 100);
      DataSourceTextField cantPaquetesField = new DataSourceTextField("cantidadPaquetes", "Cantidad de Paquetes", 200);
      DataSourceTextField vacioField = new DataSourceTextField("vacioField", " "); 
      
      setFields(idDatosField,lugarAdquisicionField,deadband1Field,deadband2Field, deadband3Field,sobreescrituraField,altura1Field,altura2Field,altura3Field,
          intervaloField,cantPaquetesField, vacioField);
      
      setDataURL("docXML/"+nombreTabla+".ds.xml");
      			 
      setClientOnly(true);
      
    System.out.println("despues de llamar a tabla en DatosArchivoSource");

  }
  
}
