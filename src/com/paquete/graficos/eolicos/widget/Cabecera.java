package com.paquete.graficos.eolicos.widget;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;

public class Cabecera extends HLayout {

  private static final int CABECERA_HEIGHT = 58;
    

  public Cabecera() {
    super();
    
    GWT.log("init Cabecera()...", null);
  
    // inicializao el plano contenedor
    this.setStyleName("crm-Cabecera");     
    this.setHeight(CABECERA_HEIGHT);
//    this.setBackgroundColor("#C3D9FF");  
  
 // initialise the Logo image
    Img logo = new Img("logo_eolica.png", 40, 40); 
    logo.setStyleName("crm-Cabecera-Logo"); 
    
    // inicializo el label cabecera 
    Label name = new Label();  
    name.setStyleName("crm-Cabecera-Name");  
    name.setContents("VientoOnline"); 
      
    // inicializo el contenedor del lado izquierdo
    HLayout westLayout = new HLayout();
    westLayout.setHeight(CABECERA_HEIGHT);  
    westLayout.setWidth("50%");
    westLayout.addMember(logo);
    westLayout.addMember(name);
    
    // inicializo la etiqueta de firmado de usuario
    Label signedInUser = new Label();  
    signedInUser.setStyleName("crm-MastHead-SignedInUser");  
    signedInUser.setContents("<b>Renzo Garcia</b><br />rgarcia.inf@gmail.com");   
    
    // inicializo el contenedor derecho
    HLayout eastLayout = new HLayout();
    eastLayout.setAlign(Alignment.RIGHT);  
    eastLayout.setHeight(CABECERA_HEIGHT);
    eastLayout.setWidth("50%");
    eastLayout.addMember(signedInUser); 
    
    // agrego el contenedor izquierdo y derecho al contenedor principal
    this.addMember(westLayout);   
    this.addMember(eastLayout); 
  } 
}