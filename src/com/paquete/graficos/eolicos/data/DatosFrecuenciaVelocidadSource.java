package com.paquete.graficos.eolicos.data;




import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.*;
import com.smartgwt.client.types.FieldType;


public class DatosFrecuenciaVelocidadSource extends DataSource {

  private static DatosFrecuenciaVelocidadSource instance = null;
  private String nombreTabla = "DatosFrecuenciaVelocidad";
  public static DatosFrecuenciaVelocidadSource getInstance(){
      if(instance == null){
        instance = new DatosFrecuenciaVelocidadSource("DatosFrecuenciaVelocidad_XML");
      }
      return instance;
  }
  
  public DatosFrecuenciaVelocidadSource(String id){
    
    setID(id);

    setRecordXPath("/List/"+nombreTabla);
//      este id debería ocultarse
      DataSourceIntegerField  idDatosField = new DataSourceIntegerField("idDatosFrecuenciaVelocidad", "ID", 50,true);
      idDatosField.setPrimaryKey(true);
      idDatosField.setCanEdit(false);
           
      
//      DataSourceTextField fechaField = new DataSourceTextField("fecha", "Fecha");
//      DataSourceDateField fechaField = new DataSourceDateField("fecha", "Fecha");
//      DataSourceDateTimeField horaField = new DataSourceDateTimeField("hora", "Hora");      

      DataSourceTextField velocidadField = new DataSourceTextField("velocidad", "Velocidad", 100);
//      Porcentaje con 2 decimales, agregar "%" si se puede
//      DataSourceFloatField floatField = new DataSourceFloatField("porcentajeAlt1", "Porcentaje Altura Uno", 100);
      
      
      
     // DataSourceTextField inicioRangoField = new DataSourceTextField("inicioRango", "Inicio del Rango", 100);
     // DataSourceTextField finRangoField = new DataSourceTextField("finRango", "Fin del Rango", 100);
      DataSourceTextField cantDatosAltura1Field = new DataSourceTextField("cantDatosAlt1", "Cant. Datos Altura Uno", 100);
      DataSourceTextField cantDatosAltura2Field = new DataSourceTextField("cantDatosAlt2", "Cant. Datos Altura Dos", 100);
      DataSourceTextField cantDatosAltura3Field = new DataSourceTextField("cantDatosAlt3", "Cant. Datos Altura Tres", 100);
//      definimos DataSourceField para poder tomar el valor como un float y posteriormente trabajarlo en el ListGrid
      DataSourceField porcentajeAltura1Field = new DataSourceField("porcentajeAlt1", FieldType.FLOAT,"Porcentaje Altura Uno", 100);
      DataSourceField porcentajeAltura2Field = new DataSourceField("porcentajeAlt2",FieldType.FLOAT, "Porcentaje Altura Dos", 100);
      DataSourceField porcentajeAltura3Field = new DataSourceField("porcentajeAlt3",FieldType.FLOAT, "Porcentaje Altura Tres", 100);                  
     
      DataSourceTextField vacioField = new DataSourceTextField("vacioField", " "); 
      
      setFields(idDatosField,velocidadField,cantDatosAltura1Field,cantDatosAltura2Field,cantDatosAltura3Field,porcentajeAltura1Field,
    		  porcentajeAltura2Field,porcentajeAltura3Field,vacioField);
      
      setDataURL("docXML/"+nombreTabla+".ds.xml");
      			 
      setClientOnly(true);
      
    System.out.println("despues de llamar a tabla en DatosFrecuenciaVelocidadSource");

  }
  
}
