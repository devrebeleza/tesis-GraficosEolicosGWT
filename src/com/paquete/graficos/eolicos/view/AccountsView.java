package com.paquete.graficos.eolicos.view;


import com.google.gwt.core.client.GWT;
import com.paquete.graficos.eolicos.widget.ContextAreaListGrid;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.VLayout;

public class AccountsView extends VLayout {
  
  private static final String DESCRIPTION = "AccountsView";

  public AccountsView() {
  super();
    
  GWT.log("init AccountView()...", null);
    
  // initialise the Account View layout container
    this.setStyleName("crm-ContextArea");
    this.setWidth("*"); 
    
    // add the List Grid to the Account View layout container
    this.addMember(new ContextAreaListGrid());
  }
  
 public static class Factory implements ContextAreaFactory {
    
    private String id;
    
    public Canvas create(String parametro) {
      AccountsView view = new AccountsView();
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