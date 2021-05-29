package com.paquete.graficos.eolicos.init;




import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.paquete.graficos.eolicos.data.GraficosNavigationPaneSectionData;
import com.paquete.graficos.eolicos.data.NavigationPaneRecord;
import com.paquete.graficos.eolicos.data.TablasNavigationPaneSectionData;
import com.paquete.graficos.eolicos.shared.LecturaDatosWindow;
import com.paquete.graficos.eolicos.view.ContextAreaFactory;
import com.paquete.graficos.eolicos.widget.ApplicationMenu;
import com.paquete.graficos.eolicos.widget.Cabecera;
import com.paquete.graficos.eolicos.widget.NavigationPane;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class PaginaPrincipal extends SectionStack {

  static SectionStack sectionStack;
  
  private static final int NORTH_HEIGHT = 85; // MASTHEAD_HEIGHT + APPLICATION_MENU_HEIGHT
  private static final int DEFAULT_MENU_WIDTH = 70;
  
  private VLayout mainVLayout; 
  private HLayout northLayout;  
  private HLayout southLayout;
  private VLayout eastLayout;  
  private VLayout westLayout;
  private TabSet toptabSet;
  
  ApplicationMenu applicationMenu ;
  
  interface GlobalResources extends ClientBundle {
    @NotStrict
    @Source("GraficosEolicosGWT.css")
    CssResource css();
    }  
  
  public PaginaPrincipal(){

//    SectionStackSection seccionTitulo = new SectionStackSection("Tesis Eólica");  
//    seccionTitulo.setExpanded(true);  
//    seccionTitulo.setCanCollapse(false);  
    //section2.addItem(new Img("pieces/48/cube_green.png", 48, 48));  
      
//    sectionStack.draw(); 
   // sectionStack.show();
    GWT.log("init OnLoadModule()...", null);  
    
    // inject global styles
    GWT.<GlobalResources>create(GlobalResources.class).css().ensureInjected();
    
    // nos deshacemos de las barras de dezplazamiento y limpiamos el margen de las ventanas,
    // por que queremos aprovechar el area completa del cliente
    Window.enableScrolling(false);
    Window.setMargin("0px");
    
    // inicializamos el plano principal
    mainVLayout = new VLayout();
    mainVLayout.setWidth100();  
    mainVLayout.setHeight100();  
    
    // inicializo el plano contenedor norte
    northLayout = new HLayout();  
    northLayout.setHeight(NORTH_HEIGHT); 
    
    VLayout vLayout = new VLayout(); 
    // agrego la cabecera al contenedor plano anidado
    vLayout.addMember(new Cabecera());
    
    
    // Inicializo el menú de Aplicaciones
    applicationMenu = new ApplicationMenu();
    applicationMenu.addMenu("<u>A</u>rchivo", DEFAULT_MENU_WIDTH, 
            "Leer Archivo", new ApplicationMenuClickHandler());
//    applicationMenu.addMenu("<u>N</u>ew Activity", DEFAULT_MENU_WIDTH, 
//                "Task, Fax, Phone Call, Email, Letter, " + 
//                "Appointment", new ApplicationMenuClickHandler());
//    
//    applicationMenu.addMenu("New Re<u>c</u>ord", DEFAULT_MENU_WIDTH, 
//    "Account, Contact, separator, Lead, Opportunity", new ApplicationMenuClickHandler());
//    
//    Menu goToMenu = applicationMenu.addMenu("<u>G</u>o To", DEFAULT_MENU_WIDTH - 30);
//    applicationMenu.addSubMenu(goToMenu, "Sales", "Leads, Opportunities, Accounts, Contacts", new ApplicationMenuClickHandler());
//    applicationMenu.addSubMenu(goToMenu, "Settings", "Administration, Templates, Data Management", new ApplicationMenuClickHandler());
//    applicationMenu.addSubMenu(goToMenu, "Resource Centre", "Highlights, Sales, Settings", new ApplicationMenuClickHandler());
//    
//    applicationMenu.addMenu("<u>T</u>ools", DEFAULT_MENU_WIDTH - 30,
//                            "Import Data, Duplicate Detection, Advanced Find, Options", new ApplicationMenuClickHandler());
//    applicationMenu.addMenu("<u>H</u>elp", DEFAULT_MENU_WIDTH - 30, 
//                            "Help on this Page, Contents, myCRM Online, About myCRM", new ApplicationMenuClickHandler());
    
    
    
    //  agrego el menu de aplicación al plano contenedor anidado
    vLayout.addMember(applicationMenu);
//    vLayout.addMember(horLay);
    // agrego el plano contenedor anidado al plano contenedor norte
    northLayout.addMember(vLayout);
    
    // initialise the Navigation Pane
    NavigationPane navigationPane = new NavigationPane();
    navigationPane.add("Tablas", TablasNavigationPaneSectionData.getRecords(), 
              new NavigationPaneClickHandler());
//    navigationPane.add("Settings", SettingsNavigationPaneSectionData.getRecords(), 
//             new NavigationPaneClickHandler());
    navigationPane.add("Gráficos", GraficosNavigationPaneSectionData.getRecords(), 
               new NavigationPaneClickHandler());
    
 // select the first Navigation Pane section e.g Sales
    navigationPane.expandSection(0);
    
    // inicializo el plano contenedor oeste
    westLayout = navigationPane;
    
    // inicializo el plano contenedor este
    toptabSet = new TabSet();  
    toptabSet.setStyleName("crm-TabSet");
    toptabSet.setTabBarPosition(Side.TOP);  
    toptabSet.setWidth100();  
    toptabSet.setHeight100();  
       
    eastLayout = new VLayout();//  AccountsView();
//    probando cosas
    eastLayout.addMember(toptabSet);
    
//    Tab tTab1 = new Tab("Gráfico");
    //SC.say("titulo tab creado: " + tTab1.getTitle());
    HLayout layoutTab = new HLayout();
    layoutTab.setWidth100();
    layoutTab.setHeight100();
//    layoutTab.addMember(new MainView()); //anda
    layoutTab.draw();
    
//    tTab1.setPane(layoutTab);
//    tTab1.setCanClose(true);
//  
//    toptabSet.addTab(tTab1);
    
//    toptabSet.addChild( new MainView()); 
//    probando cosas
    
    
//  eastLayout.addMember(new MainView());  
    
    // inicializo el plano contenedor sur
    southLayout = new HLayout(); 
    
    // seteo el panel de navegacion (menu izquierdo) y el area de contexto () como miembros del 
    // plano contenedor sur 
    southLayout.setMembers(westLayout, eastLayout);  
    
    
    // agregamos los contenedores norte y sur al plano contenedor principal
    mainVLayout.addMember(northLayout);  
    mainVLayout.addMember(southLayout); 
    
    // agregamos el plano contenedor principal al panel raiz de GWT's
    RootLayoutPanel.get().add(mainVLayout);    
    
     
  }
  
  private class ApplicationMenuClickHandler implements ClickHandler {
    @Override
    public void onClick(MenuItemClickEvent event) {
      String applicationName = event.getItem().getTitle();
//      SC.say("You clicked: " + applicationName);
      if(applicationName.equalsIgnoreCase("Leer Archivo")){
      LecturaDatosWindow form = new LecturaDatosWindow();
      }
    }  
  }
  
  private class NavigationPaneClickHandler implements RecordClickHandler {
    @Override
    public void onRecordClick(RecordClickEvent event) {
      NavigationPaneRecord record = (NavigationPaneRecord) event.getRecord();
      setContextAreaView(record);
    }
  }
  
  private void setContextAreaView(NavigationPaneRecord record) {
    
    int cont = 0;
    boolean creoTab = true;
    Tab tabRecorre;
    
    while ((cont < toptabSet.getNumTabs()) && (creoTab)){
      tabRecorre = toptabSet.getTab(cont); 
     
      boolean comparo = tabRecorre.getTitle().equalsIgnoreCase(record.getName());
//      String say = "titulo tab seleccionado tabrecorre: " + comparo + " title " + tabRecorre.getTitle() + " name " + record.getName();
//      SC.say(say);
      if (comparo){
        
        creoTab = false;
        toptabSet.selectTab(tabRecorre);
      }     
      cont++;
    }
    
  
    if (creoTab) {
      Tab tTab1 = new Tab(" ");
      ContextAreaFactory factory = record.getFactory();
      String datoBuscado = getNombre(record.getName());
      Canvas view = factory.create(datoBuscado);
      HLayout layoutTab = new HLayout();
      layoutTab.setWidth100();
      layoutTab.setHeight100();
      layoutTab.addMember(view);
      layoutTab.draw();
    
//      Tab tTab1 = new Tab(record.getName());
      tTab1.setTitle(record.getName());
      //SC.say("titulo tab creado: " + tTab1.getTitle());
     
      tTab1.setPane(layoutTab);
      tTab1.setCanClose(true);
//      }
      toptabSet.addTab(tTab1);
      toptabSet.selectTab(tTab1);

      southLayout.setMembers(westLayout, toptabSet); 
    }  

  }

  private String getNombre(String nameBuscado) {

    String retorno = null;
    // nombre de las tablas
    if(nameBuscado.equalsIgnoreCase("Datos del archivo")){
      retorno = "DatosArchivo";
    }else if(nameBuscado.equalsIgnoreCase("Primer Altura")){
      retorno = "ViewDatosAltura1";
    }else if(nameBuscado.equalsIgnoreCase("Segunda Altura")){
      retorno = "ViewDatosAltura2";
    }else if(nameBuscado.equalsIgnoreCase("Tercer Altura")){
      retorno = "ViewDatosAltura3";
    }else if(nameBuscado.equalsIgnoreCase("Perfil Temporario Hora")){
      retorno = "DatosPerfilTemporarioHora";
    }else if(nameBuscado.equalsIgnoreCase("Perfil Temporario Mes")){
      retorno = "DatosPerfilTemporarioMes";
    }else if(nameBuscado.equalsIgnoreCase("Perfil Vertical")){
      retorno = "DatosPerfilVertical";
    }else if(nameBuscado.equalsIgnoreCase("Dirección del Viento")){
      retorno = "DireccionVientoRangos";
    }else if(nameBuscado.equalsIgnoreCase("Frecuencia de Velocidades")){
      retorno = "DatosFrecuenciaVelocidad";
    }
    // Nombre de los graficos VisualGWT 
    else if(nameBuscado.equalsIgnoreCase("G. Areademo")){
      retorno = "Areademo";
    }else if(nameBuscado.equalsIgnoreCase("G. Perfil Temporario Hora")){
      retorno = "PerfilHora";
    }else if(nameBuscado.equalsIgnoreCase("G. Perfil Temporario Mes")){
      retorno = "PerfilMes";
    }else if(nameBuscado.equalsIgnoreCase("G. Perfil Vertical")){
      retorno = "PerfilVertical";
    }else if(nameBuscado.equalsIgnoreCase("G. Rosa de Viento")){
      retorno = "RosaViento";
    }else if(nameBuscado.equalsIgnoreCase("G. Función Logarítmica")){
      retorno = "FuncLogaritmica";
    }else if(nameBuscado.equalsIgnoreCase("G. Función de Potencia")){
      retorno = "FuncPotencia";
    }
    else if(nameBuscado.equalsIgnoreCase("G. Frecuencia Velocidad ")){
        retorno = "Histograma";
      }
//      }else if(nameBuscado.equalsIgnoreCase("G. Funciones Perfil Vertical")){
//      retorno = "Funciones";
        
    
    return retorno;
  }    
  
  public static Object getSectionStack(){
    return sectionStack;
  }
  public static void dibujar(){
    sectionStack.draw(); 
    sectionStack.show();
 }
  
}
