package com.paquete.graficos.eolicos.data;



import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.*;


public class DatosPerfilVerticalSource extends DataSource {

  private static DatosPerfilVerticalSource instance = null;
  private String nombreTabla = "DatosPerfilVertical";
  public static DatosPerfilVerticalSource getInstance(){
      if(instance == null){
        instance = new DatosPerfilVerticalSource("DatosPerfilVertical_XML");
      }
      return instance;
  }
  
  public DatosPerfilVerticalSource(String id){
    
    setID(id);
    setRecordXPath("/List/"+nombreTabla);
//      este id debería ocultarse
      DataSourceIntegerField  idDatosField = new DataSourceIntegerField("idDatosPerfilVertical", "ID", 50,true);
      idDatosField.setPrimaryKey(true);
      idDatosField.setCanEdit(false);
           
      
      DataSourceTextField alturaField = new DataSourceTextField("altura", "Altura");  
      
      DataSourceTextField cantidadxRenglonField = new DataSourceTextField("cantidadXrenglon", "Cantidad Datos Altura 1", 100);      
      
      DataSourceTextField totalVelPromField = new DataSourceTextField("totalVelocidadPromedio", "Velocidad Total Hora Altura Uno", 100);
      DataSourceTextField totalTempPromField = new DataSourceTextField("totalTempPromedio", "Velocidad Total Hora Altura Dos", 100);

      DataSourceTextField promVelRenglonField = new DataSourceTextField("promedioVelocidadRenglon", "Velocidad Promedio Hora Altura Uno", 100);
      DataSourceTextField promTempRenglonField = new DataSourceTextField("promedioTempRenglon", "Velocidad Promedio Hora Altura Dos", 100);
     
      DataSourceTextField vacioField = new DataSourceTextField("vacioField", " "); 
      
      setFields(idDatosField,alturaField,cantidadxRenglonField,totalVelPromField,totalTempPromField,promVelRenglonField,promTempRenglonField, vacioField);
      
      setDataURL("docXML/"+nombreTabla+".ds.xml");
      			 
      setClientOnly(true);
      
    System.out.println("despues de llamar a tabla en DatosPerfilVerticalSource");

  }
  
}
