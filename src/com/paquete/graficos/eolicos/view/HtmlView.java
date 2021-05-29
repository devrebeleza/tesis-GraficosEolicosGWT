package com.paquete.graficos.eolicos.view;

import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.ContentsType;
import com.smartgwt.client.widgets.HTMLPane;


public class HtmlView  extends HTMLPane {
	
	public HtmlView(String parametro) {
	  super();

    this.setShowEdges(true);  
    this.setContentsURL("http://localhost:8080/VisualGWT/VisualGWT.html?grafico="+parametro);
    this.setContentsType(ContentsType.PAGE); 
	  this.setHeight100();
	  this.setWidth100();  

	}
	
	public Widget asWidget() {
	  
		return this;
	}
}
