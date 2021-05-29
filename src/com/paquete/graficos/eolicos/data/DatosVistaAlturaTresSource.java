package com.paquete.graficos.eolicos.data;




import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.*;
import com.smartgwt.client.types.FieldType;


public class DatosVistaAlturaTresSource extends DataSource {

  private static DatosVistaAlturaTresSource instance = null;
  private String nombreTabla = "ViewDatosAltura3";
  public static DatosVistaAlturaTresSource getInstance(){
      if(instance == null){
        instance = new DatosVistaAlturaTresSource("DatosVistaAlturaTres_XML");
      }
      return instance;
  }
  
  public DatosVistaAlturaTresSource(String id){
    
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
      
      DataSourceTextField promVelAltura3Field = new DataSourceTextField("velPromAlt3", "Velocidad Promedio Altura Tres", 100);
      DataSourceTextField promDirAltura3Field = new DataSourceTextField("dirPromAlt3", "Dirección Promedio Altura Tres", 100);
      DataSourceField promTempAltura3Field = new DataSourceField("tempPromAlt3", FieldType.FLOAT,"Temperatura Promedio Altura Tres", 100);
 
      DataSourceTextField desvioAltura3Field = new DataSourceTextField("desvioAlt3", "Desvio Altura Tres", 100);
      DataSourceTextField velMaxAltura3Field = new DataSourceTextField("velMaxAlt3", "Velocidad Máxima Altura Tres", 100);
      DataSourceTextField dirMaxAltura3Field = new DataSourceTextField("dirMaxAlt3", "Dirección Máxima Altura Tres", 100);
      DataSourceField tempMaxAltura3Field = new DataSourceField("tempMaxAlt3", FieldType.FLOAT,"Temperatura Máxima Altura Tres", 100);
      
      DataSourceTextField velMinAltura3Field = new DataSourceTextField("velMinAlt3", "Velocidad Minima Altura Tres", 100);
      DataSourceTextField dirMinAltura3Field = new DataSourceTextField("dirMinAlt3", "Dirección Minima Altura Tres", 100);
      DataSourceField tempMinAltura3Field = new DataSourceField("tempMinAlt3",FieldType.FLOAT, "Temperatura Minima Altura Tres", 100);
                  
     
      DataSourceTextField vacioField = new DataSourceTextField("vacioField", " "); 
      
      setFields(idDatosField,fechaField,horaField,promVelAltura3Field,promDirAltura3Field,promTempAltura3Field,desvioAltura3Field,velMaxAltura3Field,
    		  dirMaxAltura3Field,tempMaxAltura3Field,velMinAltura3Field,dirMinAltura3Field,tempMinAltura3Field,vacioField);
      
      setDataURL("docXML/"+nombreTabla+".ds.xml");
      			 
      setClientOnly(true);
      
    System.out.println("despues de llamar a tabla en DatosVistaAlturaTresSource");

  }
  
}
