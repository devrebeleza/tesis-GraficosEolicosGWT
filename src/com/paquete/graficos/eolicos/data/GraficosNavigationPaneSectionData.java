package com.paquete.graficos.eolicos.data;

import com.paquete.graficos.eolicos.view.GraficoView;


public class GraficosNavigationPaneSectionData {

  private static NavigationPaneRecord[] records;

  public static NavigationPaneRecord[] getRecords() {
    if (records == null) {
      records = getNewRecords();
    }
    return records;
  }
  
  public static NavigationPaneRecord[] getNewRecords() {
    return new NavigationPaneRecord[]{
      new NavigationPaneRecord("gtodos", "G. Todos", new GraficoView.Factory(), null),
      new NavigationPaneRecord("gperfiltemporariohora", "G. Perfil Temporario Hora", new GraficoView.Factory(), null),
      new NavigationPaneRecord("gperfiltemporariomes", "G. Perfil Temp Mes", new GraficoView.Factory(), null),
      new NavigationPaneRecord("gperfilvertical", "G. Perfil Vertical", new GraficoView.Factory(), null),
      new NavigationPaneRecord("rosadeviento", "G. Rosa de Viento", new GraficoView.Factory(), null),
      new NavigationPaneRecord("histograma", "G. Frecuencia Velocidad", new GraficoView.Factory(), null),
      new NavigationPaneRecord("logaritmica", "G. Función Logarítmica", new GraficoView.Factory(), null),
      new NavigationPaneRecord("potencia", "G. Función de Potencia", new GraficoView.Factory(), null)
      
    };
  }
}
