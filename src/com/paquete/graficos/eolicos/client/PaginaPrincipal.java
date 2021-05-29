package com.paquete.graficos.eolicos.client;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class PaginaPrincipal extends SectionStack {

  static SectionStack sectionStack;
  
  public PaginaPrincipal(){

    SectionStackSection seccionTitulo = new SectionStackSection("Tesis Eólica");  
    seccionTitulo.setExpanded(true);  
    seccionTitulo.setCanCollapse(false);  
    //section2.addItem(new Img("pieces/48/cube_green.png", 48, 48));  
      
    HLayout mainLayout = new HLayout();  
    mainLayout.setWidth100();  
    mainLayout.setHeight100();  

    
    Label navigationLabel = new Label();  
    navigationLabel.setContents("Navigation");  
    navigationLabel.setAlign(Alignment.CENTER);  
    navigationLabel.setOverflow(Overflow.HIDDEN);  
    navigationLabel.setWidth("15%");  
    navigationLabel.setShowResizeBar(true);  
    
    mainLayout.addMember(navigationLabel);  

    VLayout vLayout = new VLayout();  
    vLayout.setWidth("70%");  

    Label listingLabel = new Label();  
    listingLabel.setContents("Listing");  
    listingLabel.setAlign(Alignment.CENTER);  
    listingLabel.setOverflow(Overflow.HIDDEN);  
    listingLabel.setHeight("50%");  
    listingLabel.setShowResizeBar(true);  

    Label detailsLabel = new Label();  
    detailsLabel.setContents("Details");  
    detailsLabel.setAlign(Alignment.CENTER);  
    detailsLabel.setOverflow(Overflow.HIDDEN);  
    detailsLabel.setHeight("50%");  

    vLayout.addMember(listingLabel);  
    vLayout.addMember(detailsLabel);    
    
    mainLayout.addMember(vLayout); 
    
    sectionStack = new SectionStack();  
    sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);  
    sectionStack.setWidth100();  
    sectionStack.setHeight100();  
    
    sectionStack.addSection(seccionTitulo);
    sectionStack.addMember(mainLayout);
    
//    sectionStack.draw(); 
   // sectionStack.show();
     
  }
  
  public static Object getSectionStack(){
    return sectionStack;
  }
  public static void dibujar(){
    sectionStack.draw(); 
    sectionStack.show();
 }
  
}
