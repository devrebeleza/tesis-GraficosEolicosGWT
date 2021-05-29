package com.paquete.graficos.eolicos.client;


import com.google.gwt.user.client.ui.PopupPanel;
import com.paquete.graficos.eolicos.shared.FuncionesUsuario;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;

import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;

import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.ToolbarItem;
import com.smartgwt.client.widgets.events.ClickEvent;


public class FormularioSesion extends Window {
  final Window login;
  
  public FormularioSesion(){
    
    login = new Window();  
    login.setWidth(360);  
    login.setHeight(115);  
    login.setTitle("Ingreso Usuario");  
    login.setShowMinimizeButton(false);
    login.setShowCloseButton(false);
    login.setIsModal(true);  
    login.setShowModalMask(true);  
    login.centerInPage();  
    
    final DynamicForm form = new DynamicForm();  
    
    final TextItem textItem = new TextItem();  
    textItem.setTitle("Usuario"); 
    textItem.setValue("Administrador");
    
    
    final PasswordItem passwordItem = new PasswordItem();  
    passwordItem.setTitle("Contraseña");  
    passwordItem.setValue("");
    
    IButton buttonIngresar = new IButton();  
    buttonIngresar.setTitle("Ingresar");  
    buttonIngresar.addClickHandler(new ClickHandler() {  
      public void onClick(ClickEvent event) {  
        
        String  us = textItem.getValueAsString();
        String pass = passwordItem.getValueAsString();
        
        boolean ingresoOk = FuncionesUsuario.controlarInicioSesion(us, pass);
        if (ingresoOk){
          login.destroy(); 
        }else{
          passwordItem.setValue("");
          form.focusInItem(0);
          PopupPanel ventanaError =  new PopupPanel(true, true);
          ventanaError.setTitle(" mensaje de error ");
          ventanaError.setWidget(new Label("Usuario o Contraseña Incorrecta"));
          ventanaError.setPixelSize(350,50);
          ventanaError.setAnimationEnabled(true);
          ventanaError.setGlassEnabled(true);
          ventanaError.showRelativeTo(login);
          
        }
      }  
  });       
    
    ToolbarItem toolbarItem = new ToolbarItem();
    toolbarItem.setButtons(buttonIngresar);
    toolbarItem.setWidth(190);    
    toolbarItem.setAlign(Alignment.RIGHT);

    form.setHeight100();  
    form.setWidth100();  
    form.setPadding(5);  
    form.setLayoutAlign(VerticalAlignment.BOTTOM);  
    form.setAutoFocus(true);
    form.setFields(textItem, passwordItem, toolbarItem); 

    login.addItem(form);  
    login.show();
   
  }
  
}
