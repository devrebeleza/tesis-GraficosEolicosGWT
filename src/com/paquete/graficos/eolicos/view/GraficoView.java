package com.paquete.graficos.eolicos.view;



import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class GraficoView extends VLayout {
  
  private static final String DESCRIPTION = "AccountsView";
 
  private HLayout container;
  
  public GraficoView(String parametro) {
  super();
   
  GWT.log("init AreaDemoView()...", null);
 
//    initialise the Account View layout container
//  container = new HLayout();
//  addMember(container);
//  container.setHeight("100%");//("100%");
//  container.setWidth("100%");//("100%");
  
    this.setStyleName("crm-ContextArea");
    this.setWidth("*"); 
    
    
    // add the List Grid to the Account View layout container
   
    
//    this.addMemberPreCreate(new AreaDemo());
//      container.addMember(new AreaDemo());
//      this.addMember(new AreaDemo());
    this.addMember(new HtmlView(parametro));

}
  

 public static class Factory implements ContextAreaFactory {
    
    private String id;
    
    public Canvas create(String parametro) {
      GraficoView view = new GraficoView(parametro);
      id = view.getID(); //"demoview";
     
      GWT.log("AreaDemoView.Factory.create()->view.getID() - " + id, null);
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