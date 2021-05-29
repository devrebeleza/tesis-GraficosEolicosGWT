package com.paquete.graficos.eolicos.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.layout.VLayout;

public class MainView  extends Composite {
	
	private HorizontalPanel container;
//	private LeftLayout leftLayout;
	private RightLayout rightLayout;
	
	public MainView() {
	
    container = new HorizontalPanel();
		this.initWidget(container);
	  		
		container.setHeight("100%");
		container.setWidth("100%");
		
//		leftLayout = new LeftLayout();
		rightLayout = new RightLayout();
		
//		container.add(leftLayout);
		container.add(rightLayout);
	}
	
	public Widget asWidget() {
		return this;
	}
}
