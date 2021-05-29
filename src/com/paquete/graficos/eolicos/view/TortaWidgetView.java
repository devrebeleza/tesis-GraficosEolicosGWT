package com.paquete.graficos.eolicos.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class TortaWidgetView{
	
	private HorizontalPanel container;
//	private LeftLayout leftLayout;
	private RightLayout rightLayout;
	
	private VerticalPanel vpanel0;
	
	public Widget TortaWidgetView() {
	
	  DecoratedTabPanel tabPanelWidget = new DecoratedTabPanel();
	  tabPanelWidget.setWidth("100%");
	  tabPanelWidget.setHeight("100%");
	  tabPanelWidget.setAnimationEnabled(true);
	  
	  vpanel0 = new VerticalPanel();
	  vpanel0.setStyleName("vpDotted");
	  vpanel0.setHeight("100%");
	  vpanel0.setSpacing(15);
	  
	  HTML textoHtml = new HTML("widget html");
	  vpanel0.add(textoHtml);
	  
    container = new HorizontalPanel();
	  		
		container.setHeight("100%");
		container.setWidth("100%");
		
//		leftLayout = new LeftLayout();
		rightLayout = new RightLayout();
		
//		container.add(leftLayout);
		container.add(rightLayout);
		
//		vpanel0.add(rightLayout);
		
		tabPanelWidget.add(vpanel0, "grafico vertical");
//		tabPanelWidget.add(container, " grafico horizontal");
		tabPanelWidget.ensureDebugId("cwTabPanel");
		
		return tabPanelWidget;
	}
	
//	public Widget asWidget() {
//		return this;
//	}
}
