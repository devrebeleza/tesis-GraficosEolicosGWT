package com.paquete.graficos.eolicos.data;

import com.paquete.graficos.eolicos.view.AccountsView;
import com.paquete.graficos.eolicos.view.CalendarView;
import com.paquete.graficos.eolicos.view.TablasView;


public class TablasNavigationPaneSectionData {

  private static NavigationPaneRecord[] records;

  public static NavigationPaneRecord[] getRecords() {
  if (records == null) {
    records = getNewRecords();
  }
  return records;
  }
  
  public static NavigationPaneRecord[] getNewRecords() {
    return new NavigationPaneRecord[]{
//      new NavigationPaneRecord("activities", "Activities", new AccountsView.Factory(), null),
//      new NavigationPaneRecord("calendar", "Calendar", new CalendarView.Factory(), null),
//      new NavigationPaneRecord("leads", "Leads", new AccountsView.Factory(), null),
//      new NavigationPaneRecord("opportunities", "Opportunities", new AccountsView.Factory(), null),
//      new NavigationPaneRecord("accounts", "Accounts", new AccountsView.Factory(), null),

//    agregando tablasView
      new NavigationPaneRecord("archivo", "Datos del archivo", new TablasView.Factory(), null),      
      new NavigationPaneRecord("altura1", "Primer Altura", new TablasView.Factory(), null),
      new NavigationPaneRecord("altura2", "Segunda Altura", new TablasView.Factory(), null),
      new NavigationPaneRecord("altura3", "Tercer Altura", new TablasView.Factory(), null),
      new NavigationPaneRecord("perfiltemporariohora", "Perfil Temporario Hora", new TablasView.Factory(), null),
      new NavigationPaneRecord("perfiltemporariomes", "Perfil Temporario Mes", new TablasView.Factory(), null),
      new NavigationPaneRecord("perfilvertical", "Perfil Vertical", new TablasView.Factory(), null),      
      new NavigationPaneRecord("direccionvientorangos", "Dirección del Viento", new TablasView.Factory(), null),
      new NavigationPaneRecord("frecuenciavelocidad", "Frecuencia de Velocidades", new TablasView.Factory(), null)

      
//      new NavigationPaneRecord("contacts", "Contacts", new AccountsView.Factory(), null)
    };
    }
}

