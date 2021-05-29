package com.paquete.graficos.eolicos.data;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class DatosArchivoRecord extends ListGridRecord {
  
  public DatosArchivoRecord() {}
  
  public DatosArchivoRecord(Integer idDatosArchivo, 
                            String lugarAdquisicion, 
                            int deadband1, 
                            Integer deadband2,
                            Integer deadband3, 
                            String sobreescritura, 
                            int altura1, 
                            Integer altura2, 
                            Integer altura3,
                            int intervalo, 
                            Integer cantidadPaquetes){
    
    setIdDatosArchivo(idDatosArchivo); 
    setLugarAdquisicion(lugarAdquisicion); 
    setDeadband1(deadband1); 
    setDeadband2(deadband2);
    setDeadband3(deadband3);
    setSobreescritura(sobreescritura); 
    setAltura1(altura1);
    setAltura2(altura2); 
    setAltura3(altura3);
    setIntervalo(intervalo); 
    setCantidadPaquetes(cantidadPaquetes);
    
  }
  
  private void setCantidadPaquetes(
      Integer cantidadPaquetes) {
    // TODO Auto-generated method stub
    
  }

  private void setIntervalo(int intervalo) {
    // TODO Auto-generated method stub
    
  }

  private void setAltura3(Integer altura3) {
    // TODO Auto-generated method stub
    
  }

  private void setAltura2(Integer altura2) {
    // TODO Auto-generated method stub
    
  }

  private void setAltura1(int altura1) {
    // TODO Auto-generated method stub
    
  }

  private void setSobreescritura(
      String sobreescritura) {
    // TODO Auto-generated method stub
    
  }

  private void setDeadband3(Integer deadband3) {
    // TODO Auto-generated method stub
    
  }

  private void setDeadband2(Integer deadband2) {
    // TODO Auto-generated method stub
    
  }

  private void setDeadband1(int deadband1) {
    // TODO Auto-generated method stub
    
  }

  private void setLugarAdquisicion(
      String lugarAdquisicion) {
    // TODO Auto-generated method stub
    
  }

  private void setIdDatosArchivo(
      Integer idDatosArchivo) {
    // TODO Auto-generated method stub
    
  }

  public DatosArchivoRecord(String icon, 
               String accountName,
             String mainPhone,
               String location,
               String primaryContact,
               String emailPrimaryContact) {
  setIcon(icon);
  setAccountName(accountName);
  setMainPhone(mainPhone);
  setLocation(location);
  setPrimaryContact(primaryContact);
  setEmailPrimaryContact(emailPrimaryContact);  
  } 
    
  public void setIcon(String icon) {
   setAttribute("icon", icon);
  }       
    
  public void setAccountName(String accountName) {
    setAttribute("accountName", accountName);
  }

  public void setMainPhone(String mainPhone) {
    setAttribute("mainPhone", mainPhone);
  }
    
  public void setLocation(String location) {
    setAttribute("location", location);
  }    
    
  public void setPrimaryContact(String primaryContact) {
    setAttribute("primaryContact", primaryContact);
  }   
    
  public void setEmailPrimaryContact(String emailPrimaryContact) {
    setAttribute("emailPrimaryContact", emailPrimaryContact);
  }        
    
  public String getIcon() {
    return getAttributeAsString("icon");
  }    

  public String getAccountName() {
    return getAttributeAsString("accountName");
  }
    
  public String getMainPhone() {
    return getAttributeAsString("mainPhone");
  }
  
  public String getLocation() {
    return getAttributeAsString("location");
  }    

  public String getPrimaryContact() {
    return getAttributeAsString("primaryContact");
  }
    
  public String getEmailPrimaryContact() {
    return getAttributeAsString("emailPrimaryContact");
  }    
}