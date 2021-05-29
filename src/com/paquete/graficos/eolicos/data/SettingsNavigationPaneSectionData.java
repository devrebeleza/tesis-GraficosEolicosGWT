package com.paquete.graficos.eolicos.data;

import com.paquete.graficos.eolicos.view.AccountsView;


public class SettingsNavigationPaneSectionData {

  private static NavigationPaneRecord[] records;

  public static NavigationPaneRecord[] getRecords() {
  if (records == null) {
    records = getNewRecords();
  }
  return records;
  }
  
  public static NavigationPaneRecord[] getNewRecords() {
    return new NavigationPaneRecord[]{
      new NavigationPaneRecord("administration", "Administration", new AccountsView.Factory(), null),
      new NavigationPaneRecord("templates", "Templates", new AccountsView.Factory(), null),
      new NavigationPaneRecord("datamanagement", "Data Management", new AccountsView.Factory(), null)
    };
  }
}
