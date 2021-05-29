package com.paquete.graficos.eolicos.client;


  package ar.uba.fi.ombpm.adminconsole.client.panel.widgets.menu;

  import ar.uba.fi.ombpm.adminconsole.client.controller.mediator.IMediator;
  import ar.uba.fi.ombpm.adminconsole.client.panel.ParentPanel.WidgetsParentPanelEnum;

  import com.smartgwt.client.types.Alignment;
  import com.smartgwt.client.types.Overflow;
  import com.smartgwt.client.types.VerticalAlignment;
  import com.smartgwt.client.types.VisibilityMode;
  import com.smartgwt.client.widgets.Label;
  import com.smartgwt.client.widgets.events.MouseDownEvent;
  import com.smartgwt.client.widgets.events.MouseDownHandler;
  import com.smartgwt.client.widgets.layout.HLayout;
  import com.smartgwt.client.widgets.layout.SectionStack;
  import com.smartgwt.client.widgets.layout.SectionStackSection;
  import com.smartgwt.client.widgets.layout.VLayout;

  /**
   * Menu izquierdo con el acceso a las funcionalidades de la consola
   * 
   * @author gdebenedetti
   * 
   */
  public class MenuIzquierdo extends HLayout {

          /**
           * Acordion con las opciones de menu
           */
          private SectionStack sectionStack;

          /***
           * Determina el estado del menu, significa que accion fue seleccionada
           * permite hacer que los clicks sobre las opciones tengan distinto
           * comportamiento segun el estado
           */
          private MenuStateEnum menuState;

          /**
           * Mediador para la llamada de los eventos
           */
          private IMediator mediator;

          /**
           * Singleton
           */
          protected static LeftMenu instance;

          public static LeftMenu getInstance() {
                  if (instance == null)
                          instance = new LeftMenu();
                  return instance;
          }

          private LeftMenu() {
                  sectionStack = new SectionStack();
                  menuState = MenuStateEnum.NONE;
                  cargarMenu();
          }

          /**
           * Se setea el mediador de los eventos
           */
          public void setMediator(IMediator mediator) {
                  this.mediator = mediator;
          }

          /**
           * Este metodo popula al acorion
           */
          private void cargarMenu() {
                  cargarGestionProcesos();

          }

          /***
           * Limpia el estado del menu;
           */
          public void cleanState() {
                  menuState = MenuStateEnum.NONE;
          }

          private void cargarGestionProcesos() {
                  SectionStackSection sss = new SectionStackSection(
                                  "Gesti&oacute;n Usuarios");

                  sectionStack.setAnimateSections(true);
                  sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
                  sectionStack.setOverflow(Overflow.AUTO);

                  sss.setExpanded(true);
                  sss.setCanCollapse(true);

                  VLayout vl = new VLayout();
                  /**
                   * Boton agregar usuario
                   */
                  Label lblAgregarUsuario = getBotonAgregar();
                  vl.addMember(lblAgregarUsuario);

                  /**
                   * Boton usuario rol
                   */
                  Label lblUsuarioRol = getBotonUsuarioRol();
                  vl.addMember(lblUsuarioRol);
                  sss.addItem(vl);

                  sectionStack.addSection(sss);
                  this.addMember(sectionStack);

          }
          
          /**
           * Boton usuario rol
           */
          private Label getBotonUsuarioRol() {
                  Label lblUsuarioRol = new Label("Asignar Usuario a Rol");
                  lblUsuarioRol.setAlign(Alignment.CENTER);
                  lblUsuarioRol.setValign(VerticalAlignment.CENTER);
                  lblUsuarioRol.setWidth100();
                  lblUsuarioRol.setHeight(20);
                  lblUsuarioRol.setMargin(5);
                  lblUsuarioRol.setBorder("1px solid #808080");
                  lblUsuarioRol.setBackgroundColor("#C3D9FF");
                  lblUsuarioRol.addMouseDownHandler(getAsignarRolUsuarioClickHandler());
                  lblUsuarioRol.setCursor(com.smartgwt.client.types.Cursor.HAND);
                  return lblUsuarioRol;
          }

          /**
           * Boton agregar usuario
           */
          private Label getBotonAgregar() {
                  Label lblAgregarUsuario = new Label("Agregar Nuevo Usuario");
                  lblAgregarUsuario.setAlign(Alignment.CENTER);
                  lblAgregarUsuario.setValign(VerticalAlignment.CENTER);
                  lblAgregarUsuario.setWidth100();
                  lblAgregarUsuario.setMargin(5);
                  lblAgregarUsuario.setHeight(20);
                  lblAgregarUsuario.setBorder("1px solid #808080");
                  lblAgregarUsuario.setBackgroundColor("#C3D9FF");
                  lblAgregarUsuario.addMouseDownHandler(getAgregarUsuarioClickHandler());
                  lblAgregarUsuario.setCursor(com.smartgwt.client.types.Cursor.HAND);
                  return lblAgregarUsuario;
          }

          /**
           * Handler del click para agregar rol a usuario
           * 
           * @return Handler del click
           */
          private MouseDownHandler getAsignarRolUsuarioClickHandler() {
                  return new MouseDownHandler() {
                          @Override
                          public void onMouseDown(MouseDownEvent event) {
                                  if (menuState != MenuStateEnum.ASIGNARUSUARIOROL) {
                                          // Ejecuto la accion
                                          mediator.executeAction(WidgetsParentPanelEnum.MENU
                                                          .getValue(), ActionsEnum.AGREGARUSUARIOROL
                                                          .getValue());
                                          // Le agrego el estado al menu
                                          menuState = MenuStateEnum.ASIGNARUSUARIOROL;
                                  }

                          }
                  };

          }

          /***
           * Handler del click de agregar usuario
           * 
           * @return Handler del Click
           */
          private MouseDownHandler getAgregarUsuarioClickHandler() {

                  return new MouseDownHandler() {
                          @Override
                          public void onMouseDown(MouseDownEvent event) {
                                  if (menuState != MenuStateEnum.AGREGARUSUARIO) {
                                          // Ejecuto la accion del menú
                                          mediator.executeAction(WidgetsParentPanelEnum.MENU
                                                          .getValue(), ActionsEnum.AGREGARUSUARIO.getValue());
                                          // le agrego el estado al menu
                                          menuState = MenuStateEnum.AGREGARUSUARIO;
                                  }

                          }
                  };

          }

          /**
           * Enumerador de Estador
           * 
           * @author aivaldi
           * 
           */
          public enum MenuStateEnum {
                  NONE, AGREGARUSUARIO, ASIGNARUSUARIOROL
          }

          public enum ActionsEnum {
                  AGREGARUSUARIO("ALTAUSUARIO"), AGREGARUSUARIOROL("USUARIOROL");

                  private String type;

                  private ActionsEnum(String type) {
                          this.type = type;
                  }

                  public String getValue() {
                          return this.type;
                  }
          }

  }

