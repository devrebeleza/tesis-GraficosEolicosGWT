package com.paquete.graficos.eolicos.data;




import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.*;
import com.smartgwt.client.types.FieldType;


public class DatosVistaAlturaDosSource extends DataSource {

  private static DatosVistaAlturaDosSource instance = null;
  private String nombreTabla = "ViewDatosAltura2";
  public static DatosVistaAlturaDosSource getInstance(){
      if(instance == null){
        instance = new DatosVistaAlturaDosSource("DatosVistaAlturaDos_XML");
      }
      return instance;
  }
  
  public DatosVistaAlturaDosSource(String id){
    
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
      
      DataSourceTextField promVelAltura2Field = new DataSourceTextField("velPromAlt2", "Velocidad Promedio Altura Dos", 100);
      DataSourceTextField promDirAltura2Field = new DataSourceTextField("dirPromAlt2", "Dirección Promedio Altura Dos", 100);
      DataSourceField promTempAltura2Field = new DataSourceField("tempPromAlt2",FieldType.FLOAT, "Temperatura Promedio Altura Dos", 100);      
      
      DataSourceTextField desvioAltura2Field = new DataSourceTextField("desvioAlt2", "Desvio Altura Dos", 100);
      DataSourceTextField velMaxAltura2Field = new DataSourceTextField("velMaxAlt2", "Velocidad Máxima Altura Dos", 100);
      DataSourceTextField dirMaxAltura2Field = new DataSourceTextField("dirMaxAlt2", "Dirección Máxima Altura Dos", 100);
      DataSourceField tempMaxAltura2Field = new DataSourceField("tempMaxAlt2",FieldType.FLOAT, "Temperatura Máxima Altura Dos", 100);
            
      DataSourceTextField velMinAltura2Field = new DataSourceTextField("velMinAlt2", "Velocidad Minima Altura Dos", 100);
      DataSourceTextField dirMinAltura2Field = new DataSourceTextField("dirMinAlt2", "Dirección Minima Altura Dos", 100);
      DataSourceField tempMinAltura2Field = new DataSourceField("tempMinAlt2", FieldType.FLOAT,"Temperatura Minima Altura Dos", 100);
      
            
     
      DataSourceTextField vacioField = new DataSourceTextField("vacioField", " "); 
      
      setFields(idDatosField,fechaField,horaField,promVelAltura2Field,promDirAltura2Field,promTempAltura2Field,desvioAltura2Field,velMaxAltura2Field,
    		  dirMaxAltura2Field,tempMaxAltura2Field,velMinAltura2Field,dirMinAltura2Field,tempMinAltura2Field,vacioField);
      
      setDataURL("docXML/"+nombreTabla+".ds.xml");
      			 
      setClientOnly(true);
      
    System.out.println("despues de llamar a tabla en DatosVistaAlturaUnoSource");

  }
  
}
