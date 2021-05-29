package com.paquete.graficos.eolicos.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;


import com.paquete.graficos.eolicos.init.PaginaPrincipal;

public class MainGraficosEolicosGWT implements
    EntryPoint {
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
  private static final String  SERVER_ERROR = "An error occurred while "
                                                         + "attempting to contact the server. Please check your network "
                                                         + "connection and try again.";

  /**s 
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
//  private final RpcServiceAsync greetingService = GWT.create(RpcService.class);


  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    
//    FormularioSesion cu = new FormularioSesion();
//    System.out.println("antes de llamar a tabla en ModuleLoad");
//    TablaEnXML tablaXML = new TablaEnXML();

    PaginaPrincipal pagPrp = new PaginaPrincipal();
//    pagPrp.dibujar();
    
//    RootPanel.get().add((Widget) pagPrp.getSectionStack());
//    RootPanel.get().add(sectionStack);
//    RootPanel.get().add(button);
    
  }
}
