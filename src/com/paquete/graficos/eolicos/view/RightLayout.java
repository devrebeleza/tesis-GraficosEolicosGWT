package com.paquete.graficos.eolicos.view;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.visualizations.PieChart;

public class RightLayout extends VerticalPanel {
	
	public RightLayout() {
		setBorderWidth(1);
		add(new Label("Some Widget"));
		add(new Graphic());
		setWidth("100%");
		setHeight("100%");
	}
	
	public class Graphic extends HorizontalPanel {
		public Graphic() {
			Runnable onLoadCallback = new Runnable() {
				public void run() {
				  PieChart pie = new PieChart(createTablePie(), createOptionsPie());
//				  pie.setWidth("100%");
//				  pie.setHeight("100%");
					add(pie);
				}
			};
			VisualizationUtils.loadVisualizationApi(onLoadCallback, PieChart.PACKAGE);
		}

		private PieChart.Options createOptionsPie() {
			PieChart.Options options = PieChart.Options.create();
			options.setWidth(800);
			options.setHeight(520);
			options.setTitle("GWT Chart test.");
			return options;
		}
		private AbstractDataTable createTablePie() {
			DataTable data = DataTable.create();
			data.addColumn(ColumnType.STRING, "Data set");
			data.addColumn(ColumnType.NUMBER, "Value");
			data.addRows(2);
			data.setValue(0, 0, "Data set 1");
			data.setValue(0, 1, 100);
			data.setValue(1, 0, "Data set 2");
			data.setValue(1, 1, 10);
			return data;
		}
	}
}
