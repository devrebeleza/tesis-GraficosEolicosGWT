package com.paquete.graficos.eolicos.view;


import com.smartgwt.client.widgets.Canvas;


public interface ContextAreaFactory {
  
//  Canvas create();

  String getID();

  String getDescription();

  Canvas create(String name);
}
