package com.paquete.graficos.eolicos.widget;



import com.google.gwt.core.client.GWT;
import com.paquete.graficos.eolicos.data.NavigationPaneRecord;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.VLayout;



public class NavigationPane extends VLayout {
  
  private static final int WEST_WIDTH = 200;
 
  private SectionStack sectionStack ;

      
  public NavigationPane() {
    super();
        
    GWT.log("init Navigation Pane()...", null);
      
    // inicializo el section stack
    this.setStyleName("crm-NavigationPane");  
    this.setWidth(WEST_WIDTH);
    this.setShowResizeBar(true);    
    
    sectionStack = new SectionStack();
    sectionStack.setWidth(WEST_WIDTH);
    sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
    sectionStack.setShowExpandControls(true);
    sectionStack.setAnimateSections(true);  

    // agrego el section stack al contenedor del panel de navegacion
    this.addMember(sectionStack);
   
  } 
  public void add(String sectionName, NavigationPaneRecord[] sectionData,RecordClickHandler clickHandler) {
      sectionStack.addSection(new NavigationPaneSection(sectionName, sectionData,clickHandler));
  } 

  public void expandSection(int section) {
      sectionStack.expandSection(section);
  } 
}