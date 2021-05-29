package com.paquete.graficos.eolicos.widget;


import com.google.gwt.core.client.GWT;
import com.paquete.graficos.eolicos.data.AccountData;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.types.ListGridFieldType;

public class ContextAreaListGrid extends ListGrid {

  public ContextAreaListGrid() {
  super();
       
  GWT.log("init ContextAreaListGrid()...", null);

    // incializo la cuadricula
  this.setShowAllRecords(true);   
  this.setSortField(1); 
         
    // inicializo los campos de las cuadricula   
  ListGridField iconField = new ListGridField("icon", "#", 27);
  iconField.setImageSize(16); 
  iconField.setAlign(Alignment.CENTER);
  // defino que va a contener una imagen
  iconField.setType(ListGridFieldType.IMAGE);  
  iconField.setImageURLPrefix("icons/16/");  
  iconField.setImageURLSuffix(".png");  
    
  ListGridField accountNameField = new ListGridField("accountName", "Account Name", 320);  
  ListGridField mainPhoneField = new ListGridField("mainPhone", "Main Phone", 100);  
  ListGridField locationField = new ListGridField("location", "Location", 100);  
  ListGridField primaryContactField = new ListGridField("primaryContact", "Primary Contact", 140); 
  // defino que va a contener un link
  primaryContactField.setType(ListGridFieldType.LINK);  
  ListGridField emailPrimaryContactField = new ListGridField("emailPrimaryContact", 
                                 "Email (Primary Contact)", 180);   
  ListGridField emptyField = new ListGridField("emptyField", " ");    
    
    // seteo los campos en la cuadricula  
  this.setFields(new ListGridField[] {iconField, accountNameField, mainPhoneField, locationField, 
           primaryContactField, emailPrimaryContactField, emptyField });  
  
  // poblar la cuadricula
  this.setData(AccountData.getRecords());  
  }
}