package com.paquete.graficos.eolicos.data;

import com.paquete.graficos.eolicos.view.AccountsView;
import com.paquete.graficos.eolicos.view.CalendarView;


public class SalesNavigationPaneSectionData {

  private static NavigationPaneRecord[] records;

  public static NavigationPaneRecord[] getRecords() {
  if (records == null) {
    records = getNewRecords();
  }
  return records;
  }
  
  public static NavigationPaneRecord[] getNewRecords() {
    return new NavigationPaneRecord[]{
      new NavigationPaneRecord("activities", "Activities", new AccountsView.Factory(), null),
      new NavigationPaneRecord("calendar", "Calendar", new CalendarView.Factory(), null),
      new NavigationPaneRecord("leads", "Leads", new AccountsView.Factory(), null),
      new NavigationPaneRecord("opportunities", "Opportunities", new AccountsView.Factory(), null),
      new NavigationPaneRecord("accounts", "Accounts", new AccountsView.Factory(), null),
      new NavigationPaneRecord("contacts", "Contacts", new AccountsView.Factory(), null)
    };
    }
}

