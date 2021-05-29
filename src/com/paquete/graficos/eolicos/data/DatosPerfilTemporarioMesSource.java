package com.paquete.graficos.eolicos.data;



import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.*;


public class DatosPerfilTemporarioMesSource extends DataSource {

  private static DatosPerfilTemporarioMesSource instance = null;
  private String nombreTabla = "DatosPerfilTemporarioMes";
  public static DatosPerfilTemporarioMesSource getInstance(){
      if(instance == null){
        instance = new DatosPerfilTemporarioMesSource("DatosPerfilTemporarioMes_XML");
      }
      return instance;
  }
  
  public DatosPerfilTemporarioMesSource(String id){
    
    setID(id);
    setRecordXPath("/List/"+nombreTabla);
//      este id debería ocultarse
      DataSourceIntegerField  idDatosField = new DataSourceIntegerField("idDatosPerfilTemporarioMes", "ID", 50,true);
      idDatosField.setPrimaryKey(true);
      idDatosField.setCanEdit(false);
           
      
      DataSourceTextField horaField = new DataSourceTextField("mes", "Mes");  
      
      DataSourceTextField cantidadAlt1Field = new DataSourceTextField("cantidadAlt1", "Cantidad Datos Altura 1", 100);
      DataSourceTextField cantidadAlt2Field = new DataSourceTextField("cantidadAlt2", "Cantidad Datos Altura 2", 100);
      DataSourceTextField cantidadAlt3Field = new DataSourceTextField("cantidadAlt3", "Cantidad Datos Altura 3", 100);      
      
      DataSourceTextField velHoraAltura1Field = new DataSourceTextField("totalVelocidadPromedioAlt1", "Velocidad Total Hora Altura Uno", 100);
      DataSourceTextField velHoraAltura2Field = new DataSourceTextField("totalVelocidadPromedioAlt2", "Velocidad Total Hora Altura Dos", 100);
      DataSourceTextField velHoraAltura3Field = new DataSourceTextField("totalVelocidadPromedioAlt3", "Velocidad Total Hora Altura Tres", 100);
      
      DataSourceTextField promVelHoraAltura1Field = new DataSourceTextField("promedioVelocidadAlt1", "Velocidad Promedio Hora Altura Uno", 100);
      DataSourceTextField promVelHoraAltura2Field = new DataSourceTextField("promedioVelocidadAlt2", "Velocidad Promedio Hora Altura Dos", 100);
      DataSourceTextField promVelHoraAltura3Field = new DataSourceTextField("promedioVelocidadAlt3", "Velocidad Promedio Hora Altura Tres", 100);
      
      DataSourceTextField desvioAltura1Field = new DataSourceTextField("totalDesvioAlt1", "Desvio Total Hora Altura Uno", 100);
      DataSourceTextField desvioAltura2Field = new DataSourceTextField("totalDesvioAlt2", "Desvio Total Hora Altura Dos", 100);
      DataSourceTextField desvioAltura3Field = new DataSourceTextField("totalDesvioAlt3", "Desvio Total Hora Altura Tres", 100);

      DataSourceTextField promDesvioAltura1Field = new DataSourceTextField("promedioDesvioAlt1", "Desvio Promedio Hora Altura Uno", 100);
      DataSourceTextField promDesvioAltura2Field = new DataSourceTextField("promedioDesvioAlt2", "Desvio Promedio Hora Altura Dos", 100);
      DataSourceTextField promDesvioAltura3Field = new DataSourceTextField("promedioDesvioAlt3", "Desvio Promedio Hora Altura Tres", 100);
      
     
      DataSourceTextField vacioField = new DataSourceTextField("vacioField", " "); 
      
      setFields(idDatosField,horaField,cantidadAlt1Field,cantidadAlt2Field,cantidadAlt3Field,velHoraAltura1Field,velHoraAltura2Field,velHoraAltura3Field,promVelHoraAltura1Field,
    		  promVelHoraAltura2Field,promVelHoraAltura3Field, desvioAltura1Field,desvioAltura2Field,desvioAltura3Field,promDesvioAltura1Field,promDesvioAltura2Field,promDesvioAltura3Field,
    		  vacioField);
      
      setDataURL("docXML/"+nombreTabla+".ds.xml");
      			 
      setClientOnly(true);
      
    System.out.println("despues de llamar a tabla en DatosPerfilTemporarioMesSource");

  }
  
}
