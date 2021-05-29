package com.paquete.graficos.eolicos.data;




import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.*;
import com.smartgwt.client.types.FieldType;


public class DatosVistaAlturaUnoSource extends DataSource {

  private static DatosVistaAlturaUnoSource instance = null;
  private String nombreTabla = "ViewDatosAltura1";
  public static DatosVistaAlturaUnoSource getInstance(){
      if(instance == null){
        instance = new DatosVistaAlturaUnoSource("DatosVistaAlturaUno_XML");
      }
      return instance;
  }
  
  public DatosVistaAlturaUnoSource(String id){
    
    setID(id);
//    debo agregarle id por que me genera el xml con ese tag agregado
    setRecordXPath("/List/"+nombreTabla+"/id");
//      este id debería ocultarse
      DataSourceIntegerField  idDatosField = new DataSourceIntegerField("idDatosTodos", "ID", 50,true);
      idDatosField.setPrimaryKey(true);
      idDatosField.setCanEdit(false);
           
      
//      DataSourceTextField fechaField = new DataSourceTextField("fecha", "Fecha");
      DataSourceDateField fechaField = new DataSourceDateField("fecha", "Fecha");
      DataSourceDateTimeField horaField = new DataSourceDateTimeField("hora", "Hora");      
      
      DataSourceTextField promVelAltura1Field = new DataSourceTextField("velPromAlt1", "Velocidad Promedio Altura Uno", 100);
      DataSourceTextField promDirAltura1Field = new DataSourceTextField("dirPromAlt1", "Dirección Promedio Altura Uno", 100);
      DataSourceField promTempAltura1Field = new DataSourceField("tempPromAlt1",FieldType.FLOAT, "Temperatura Promedio Altura Uno", 100);
      
      DataSourceTextField desvioAltura1Field = new DataSourceTextField("desvioAlt1", "Desvio Altura Uno", 100);
      DataSourceTextField velMaxAltura1Field = new DataSourceTextField("velMaxAlt1", "Velocidad Máxima Altura Uno", 100);
      DataSourceTextField dirMaxAltura1Field = new DataSourceTextField("dirMaxAlt1", "Dirección Máxima Altura Uno", 100);
      DataSourceField tempMaxAltura1Field = new DataSourceField("tempMaxAlt1",FieldType.FLOAT, "Temperatura Máxima Altura Uno", 100);   
      
      DataSourceTextField velMinAltura1Field = new DataSourceTextField("velMinAlt1", "Velocidad Minima Altura Uno", 100);
      DataSourceTextField dirMinAltura1Field = new DataSourceTextField("dirMinAlt1", "Dirección Minima Altura Uno", 100);
      DataSourceField tempMinAltura1Field = new DataSourceField("tempMinAlt1", FieldType.FLOAT,"Temperatura Minima Altura Uno", 100);
            
     
      DataSourceTextField vacioField = new DataSourceTextField("vacioField", " "); 
      
      setFields(idDatosField,fechaField,horaField,promVelAltura1Field,promDirAltura1Field,promTempAltura1Field,desvioAltura1Field,velMaxAltura1Field,
    		  dirMaxAltura1Field,tempMaxAltura1Field,velMinAltura1Field,dirMinAltura1Field,tempMinAltura1Field,vacioField);
      
      setDataURL("docXML/"+nombreTabla+".ds.xml");
      			 
      setClientOnly(true);
      
    System.out.println("despues de llamar a tabla en DatosVistaAlturaUnoSource");

  }
  
}
