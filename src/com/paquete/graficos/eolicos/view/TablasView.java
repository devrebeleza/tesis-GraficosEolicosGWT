package com.paquete.graficos.eolicos.view;



import com.google.gwt.core.client.GWT;
import com.paquete.graficos.eolicos.widget.TablaAreaListGrid;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.VLayout;

public class TablasView extends VLayout {
  
  private static final String DESCRIPTION = "AccountsView";

  public TablasView(String parametro) {
  super();
    
  GWT.log("init AccountView()...", null);
    
  // inicializo el Tablas View layout container
    this.setStyleName("crm-ContextArea");
    this.setWidth("*"); 
    
    System.out.println("tabla view: " + parametro);
    // agrego el List Grid al Tablas View layout contenedor
    this.addMember(new TablaAreaListGrid(parametro));
  }
  
 public static class Factory implements ContextAreaFactory {
    
    private String id;
    
    public Canvas create(String parametro) {
      TablasView view = new TablasView(parametro);
      id = view.getID();
        
      GWT.log("AccountsView.Factory.create()->view.getID() - " + id, null);
      return view;
    }

    public String getID() {
      return id;
    }

    public String getDescription() {
      return DESCRIPTION;
    }
    
  }   
}