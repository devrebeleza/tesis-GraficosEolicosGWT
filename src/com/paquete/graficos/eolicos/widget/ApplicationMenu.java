package com.paquete.graficos.eolicos.widget;


import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuButton;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.MenuItemSeparator;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.google.gwt.core.client.GWT;

public class ApplicationMenu extends HLayout {
  
  private static final int APPLICATION_MENU_HEIGHT = 27;
  private static final int DEFAULT_SHADOW_DEPTH = 10;
  
  private static final String SEPARATOR = "separator";
  private static final String ICON_PREFIX = "icons/16/";
  private static final String ICON_SUFFIX = ".png";
  
  //private MenuBar menuBar ;
  private int menuPosition = 0;    
    
  public ApplicationMenu() {
  super();
      
  GWT.log("init ApplicationMenu()...", null);
    
    // initialise the Application Menu layout container
    this.setStyleName("crm-ApplicationMenu"); 
    this.setHeight(APPLICATION_MENU_HEIGHT);
//    this.setBackgroundColor("#48D1CC");  

    // initialise the Menu Bar
//  menuBar = new MenuBar();
  
  // add the Menu Bar to the Application Menu layout container
//  this.addMember(menuBar);        
  }
  
  public Menu addMenu(String menuName, int width, String menuItemNames, ClickHandler clickHandler) {
  // initialise the new menu
  Menu menu = new Menu(); 
  menu.setTitle(menuName);
  menu.setShowShadow(true);  
  menu.setShadowDepth(DEFAULT_SHADOW_DEPTH); 
  menu.setWidth(width);
  
  // create an array of menu item names 
  String[] menuItems = process(menuItemNames);
  
  for (int i = 0; i < menuItems.length; i++) {
    // remove any whitespace
    String menuItemName = menuItems[i].replaceAll("\\W", "");

    if (menuItemName.contentEquals(SEPARATOR)) {
      MenuItemSeparator separator = new MenuItemSeparator();
      menu.addItem(separator);  
      continue;
    }
      
    MenuItem menuItem = new MenuItem(menuItems[i], getIcon(menuItems[i]));
    menuItem.addClickHandler(clickHandler);
    menu.addItem(menuItem);    
  }
    
  Menu[] menus = new Menu[1]; 
  menus[0] = menu;
//  menuBar.addMenus(menus, menuPosition);
//  MenuButton botonMenu = new MenuButton(menuName, menu);
  this.addMember(new MenuButton(menuName, menu));
  menuPosition++ ;
  
  return menus[0];
  }
    
  public Menu addMenu(String menuName, int width) {
  // initialise the new menu
  Menu menu = new Menu(); 
//  menu.setTitle(menuName);
  menu.setShowShadow(true);  
  menu.setShadowDepth(DEFAULT_SHADOW_DEPTH); 
  menu.setWidth(width);
    
  Menu[] menus = new Menu[1]; 
  menus[0] = menu;
//menuBar.addMenus(menus, menuPosition);
  this.addMember(new MenuButton(  menuName, menu));
  menuPosition++ ;
    
  return menu;
  }
    
  public Menu addSubMenu(Menu parentMenu, String subMenuName, String menuItemNames, ClickHandler clickHandler) {
  // initialise the new sub menu
  Menu menu = new Menu(); 
  menu.setShowShadow(true);  
  menu.setShadowDepth(DEFAULT_SHADOW_DEPTH); 
      
  MenuItem menuItem = new MenuItem(subMenuName);
  
  // create an array of menu item names 
  String[] menuItems = process(menuItemNames);

  for (int i = 0; i < menuItems.length; i++) {
    // remove any whitespace
    String menuItemName = menuItems[i].replaceAll("\\W", "");
        
    if (menuItemName.contentEquals(SEPARATOR)) {
      MenuItemSeparator separator = new MenuItemSeparator();
      menu.addItem(separator);  
      continue;
    }
    
  menuItem = new MenuItem(menuItems[i], getIcon(menuItems[i]));
  menuItem.addClickHandler(clickHandler);
  menu.addItem(menuItem);    
  }
      
  // add the sub menu to the menu item
  menuItem = new MenuItem(subMenuName);
  parentMenu.addItem(menuItem); 
  menuItem.setSubmenu(menu);
      
  return menu;
  }  
    
  public final static String DELIMITER = ",";
    
  public static String[] process(String line) {
    return line.split(DELIMITER);
  }
    
  private String getIcon(String applicationName) {
  // remove any whitespace
  String name = applicationName.replaceAll("\\W", "");
  // e.g. "icons/16/" + "activities" + ".png" 
  String icon = ICON_PREFIX + name.toLowerCase() + ICON_SUFFIX ; 
  return icon ;
  }
}