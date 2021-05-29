package com.paquete.graficos.eolicos.funcionescarga;



import java.util.Iterator;
import com.paquete.graficos.hibernate.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.paquete.graficos.eolicos.basededatos.DatosPerfilVertical;



public class FuncionesDatosPerfilVertical {

	private static double k = 0.4;
	private static double gravedad = 9.8;
	private static double richardson;
	private static double longMoninObukhov;
	private static double uEstrella; // velocidad de fricción
	private static double z0; // longitud de rugosidad
	private static double p; 
	private static double u1; // velocidad en la altura z1
	private static double z1; // altura elegida para ley de potencia
	static boolean variablesIniciadasLogaritmicas = false;
	static boolean variablesIniciadasPotencia = false;
	
	public static String limpiarTablaDatosPerfilVertical(){
		
		Session session = null;
		Transaction tx = null;
		Logger log = Logger.getLogger("Eliminando datos de la tabla DatosPerfilVertical");
		log.info("Eliminando datos de la tabla DatosTodos");
		String terminoOk;
		try{ 
			session = HibernateUtil.getSessionFactory().getCurrentSession();			
			tx = session.beginTransaction();
			
			// necesito sql nativo por que hql no contiene truncate y debo reiniciar el campo clave que es auto_increment
		    Query query = session.createSQLQuery("TRUNCATE TABLE DatosPerfilVertical");
		    query.executeUpdate();

		    tx.commit();
		    terminoOk = "OK";
			// cerrar la sesion
			//session.close();
				
	   } catch (HibernateException e) {

		   	System.out.println(e.getMessage());
		   	log.warn("Ocurrio un error al intentar limpiar la tabla DatosPerfilVertical");
		   	terminoOk = "No se pudo vaciar la tabla de datos para el perfil vertical";
		   // cuando ocurre un error hace rollback
		   	if (tx != null)
		   		try {
		   			tx.rollback();
		   		} catch (HibernateException e1) {
		   			System.out.println("El rollback no fue exitoso");
		   			log.warn("El rollback no fue exitoso");
		   		}

         if (session != null)
         	try {
         		session.close();
         	} catch (HibernateException e2) {
         		System.out.println("El cierre de sesion no fue exitoso");
					log.warn("El cierre de sesion no fue exitoso");
         	}
		}	
	   return terminoOk;
	}
	
	public static String addDatosPerfilVerticalAltura1(){
		
		//DatosArchivo obj_datosArchivo = null;
		String[] strAlturas = null; //new String[3];
		strAlturas = FuncionesDatosArchivo.getAlturasDatosArchivo(); 
		
		String terminoOk;
		
		//chequeo que la tabla DatosArchivo tenga datos y recupero la primera altura
		if (strAlturas[0] != null) {
			
			int altura1 = Integer.parseInt(strAlturas[0]);
			System.out.println("cantidad de alturas  1 " + altura1);
			/*
			 *calculo los datos usando la vista de la altura 1 
			 */
			String[] strPerfilVertical = FuncionesViewDatosAltura1.getDatosPerfilVertical();
			int cantidadXRenglon = Integer.parseInt(strPerfilVertical[0].toString());
			double cantxRenglon = Double.parseDouble(strPerfilVertical[0]);
			
			Double totalVelocidadPromedio = new Double(Double.parseDouble(strPerfilVertical[1]));			   
			Double totalTempPromedio= new Double(Double.parseDouble(strPerfilVertical[2]));

			double promedioTempRenglon =  (totalTempPromedio / cantxRenglon);//totalTempPromedio.divide(cantxRenglon);
			double promedioVelocidadRenglon =  (totalVelocidadPromedio / cantxRenglon);
							
			Session session = null;
			Transaction tx = null;
			//frmAltaAplicacion.Cerrarse();
			Logger log = Logger.getLogger("Insertar los datos de la altura 1 en la tabla DatosPerfilVertical");
			log.info("Insertar los datos de la altura 1 en la tabla DatosPerfilVertical");			
				try {
									
					//iniciar la sesion con Hibernate
		            SessionFactory sess = HibernateUtil.getSessionFactory();
		            session = sess.getCurrentSession();
					//comenzar la transaccion
					tx = session.beginTransaction();

					/*
					 * creo el objeto para la altura 1 y lo guardo en la base
					 */
					DatosPerfilVertical obj_datosPerfil = new DatosPerfilVertical(altura1, cantidadXRenglon,totalVelocidadPromedio, totalTempPromedio, promedioVelocidadRenglon,promedioTempRenglon);					
					
					// Guardo en la BD el objeto
					session.save(obj_datosPerfil);				

					// cometer la transaccion o sino no se escribe nada en la BD
					tx.commit();
					
					terminoOk = "OK";

					// cerrar la sesion
					//session.close();
					
				} catch (HibernateException e) {
					
					System.out.println(e.getMessage());
					log.warn("Ocurrio un error al insertar el objeto");
					
					terminoOk = "Error en la transacción para guardar datos en la tabla de perfil vertical de la altura uno";
					// cuando ocurre un error hace rollback
					if (tx != null)
						try {
							tx.rollback();
						} catch (HibernateException e1) {
							System.out.println("El rollback no fue exitoso");
						    log.warn("El rollback no fue exitoso");						    
						}
					
					if (session != null)
						try {
							session.close();
						} catch (HibernateException e2) {
							System.out.println("Cerrar la sesion no fue exitoso");
							log.warn("Cerrar la sesion no fue exitoso");
						}
				}
						
		}else {
			terminoOk = "No hay datos para el perfil vertical de la altura uno";
		}
		
		return terminoOk;
		
	}
	
	
	public static String addDatosPerfilVerticalAltura2(){
		
		String[] strAlturas = null; //new String[3];
		strAlturas = FuncionesDatosArchivo.getAlturasDatosArchivo(); 
		
		String terminoOk;
		
		//chequeo que la tabla DatosArchivo tenga datos y recupero la segunda altura
		if (strAlturas[1] != null) {
			terminoOk = "No hay datos para el perfil vertical de la altura dos";
			int altura2 = Integer.parseInt(strAlturas[1]);
			if (altura2 > 0){

				/*
				 *calculo los datos usando la vista de la altura 2 
				 */
				String[] strPerfilVertical = FuncionesViewDatosAltura2.getDatosPerfilVertical();
				int cantidadXRenglon = Integer.parseInt(strPerfilVertical[0].toString());
				double cantxRenglon = Double.parseDouble(strPerfilVertical[0].toString());
				 
				Double totalVelocidadPromedio = new Double(Double.parseDouble(strPerfilVertical[1]));			   
				Double totalTempPromedio= new Double(Double.parseDouble(strPerfilVertical[2]));

				double promedioTempRenglon = (totalTempPromedio / cantxRenglon);
				double promedioVelocidadRenglon = (totalVelocidadPromedio / cantxRenglon);
								
				Session session = null;
				Transaction tx = null;
				//frmAltaAplicacion.Cerrarse();
				Logger log = Logger.getLogger("Insertar los datos de la altura 2 en la tabla DatosPerfilVertical");
				log.info("Insertar los datos de la altura 2 en la tabla DatosPerfilVertical");			
					try {
										
						//iniciar la sesion con Hibernate
			            SessionFactory sess = HibernateUtil.getSessionFactory();
			            session = sess.getCurrentSession();
						//comenzar la transaccion
						tx = session.beginTransaction();

						/*
						 * creo el objeto para la altura 1 y lo guardo en la base
						 */
						DatosPerfilVertical obj_datosPerfil = new DatosPerfilVertical(altura2, cantidadXRenglon,totalVelocidadPromedio, totalTempPromedio, promedioVelocidadRenglon,promedioTempRenglon);					
						
						// Guardo en la BD el objeto
						session.save(obj_datosPerfil);				

						// cometer la transaccion o sino no se escribe nada en la BD
						tx.commit();
						
						terminoOk = "OK";

						// cerrar la sesion
						//session.close();
						
					} catch (HibernateException e) {
						
						System.out.println(e.getMessage());
						log.warn("Ocurrio un error al insertar el objeto");
						
						terminoOk = "Error en la transacción para guardar datos en la tabla de perfil vertical de la altura dos";
						// cuando ocurre un error hace rollback
						if (tx != null)
							try {
								tx.rollback();
							} catch (HibernateException e1) {
								System.out.println("El rollback no fue exitoso");
							    log.warn("El rollback no fue exitoso");						    
							}
						
						if (session != null)
							try {
								session.close();
							} catch (HibernateException e2) {
								System.out.println("Cerrar la sesion no fue exitoso");
								log.warn("Cerrar la sesion no fue exitoso");
							}
					}				
			}
		}else terminoOk = "No hay datos para el perfil vertical de la altura dos";
		
		return terminoOk;
	}
	
	
	public static String addDatosPerfilVerticalAltura3(){
		
		String[] strAlturas = null; //new String[3];
		strAlturas = FuncionesDatosArchivo.getAlturasDatosArchivo(); 
		
		String terminoOk;
		
		//chequeo que la tabla DatosArchivo tenga datos y recupero la tercera altura
		if (strAlturas[2] != null) {
			terminoOk = "No hay datos para el perfil vertical de la altura tres";
			int altura3 = Integer.parseInt(strAlturas[2]);
			if (altura3 > 0){
				/*
				 *calculo los datos usando la vista de la altura 3 
				 */
				String[] strPerfilVertical = FuncionesViewDatosAltura3.getDatosPerfilVertical();
				int cantidadXRenglon = Integer.parseInt(strPerfilVertical[0].toString());
				double cantxRenglon = Double.parseDouble(strPerfilVertical[0].toString());
				 
				Double totalVelocidadPromedio = new Double(Double.parseDouble(strPerfilVertical[1]));			   
				Double totalTempPromedio= new Double(Double.parseDouble(strPerfilVertical[2]));

				double promedioTempRenglon = (totalTempPromedio / cantxRenglon);
				double promedioVelocidadRenglon = (totalVelocidadPromedio / cantxRenglon);
								
				Session session = null;
				Transaction tx = null;
				//frmAltaAplicacion.Cerrarse();
				Logger log = Logger.getLogger("Insertar los datos de la altura 3 en la tabla DatosPerfilVertical");
				log.info("Insertar los datos de la altura 3 en la tabla DatosPerfilVertical");			
					try {
										
						//iniciar la sesion con Hibernate
			            SessionFactory sess = HibernateUtil.getSessionFactory();
			            session = sess.getCurrentSession();
						//comenzar la transaccion
						tx = session.beginTransaction();

						/*
						 * creo el objeto para la altura 1 y lo guardo en la base
						 */
						DatosPerfilVertical obj_datosPerfil = new DatosPerfilVertical(altura3, cantidadXRenglon,totalVelocidadPromedio, totalTempPromedio, promedioVelocidadRenglon,promedioTempRenglon);					
						
						// Guardo en la BD el objeto
						session.save(obj_datosPerfil);				

						// cometer la transaccion o sino no se escribe nada en la BD
						tx.commit();
						
						terminoOk = "OK";

						// cerrar la sesion
						//session.close();
						
					} catch (HibernateException e) {
						
						System.out.println(e.getMessage());
						log.warn("Ocurrio un error al insertar el objeto");
						
						terminoOk = "Error en la transacción para guardar datos en la tabla de perfil vertical de la altura dos";
						// cuando ocurre un error hace rollback
						if (tx != null)
							try {
								tx.rollback();
							} catch (HibernateException e1) {
								System.out.println("El rollback no fue exitoso");
							    log.warn("El rollback no fue exitoso");						    
							}
						
						if (session != null)
							try {
								session.close();
							} catch (HibernateException e2) {
								System.out.println("Cerrar la sesion no fue exitoso");
								log.warn("Cerrar la sesion no fue exitoso");
							}
					}
			}
		}else terminoOk = "No hay datos para el perfil vertical de la altura tres";
		
	return terminoOk;		
	}


	private static String[] getDatosAlturaMenor() {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
				
		// Iniciar la sesion con Hibernate
        SessionFactory sess = HibernateUtil.getSessionFactory();
        session = sess.getCurrentSession();
   
        // Comenzar la transaccion
        tx = session.beginTransaction();
        
        Logger log = Logger.getLogger("Obtener los datos de la mayor altura");
		log.info("Obtener los datos de la mayor altura");
		
		String[] strDevolucion = new String[3];
	    try {		
	    		//hago un query con todos los datos 
		        StringBuilder queryObjeto= new StringBuilder();
		        queryObjeto.append("select dp.altura, dp.promedioVelocidadRenglon, dp.promedioTempRenglon from DatosPerfilVertical as dp where dp.altura =  ");
		        queryObjeto.append("(select MIN(dp2.altura) from DatosPerfilVertical as dp2)");
		        
		        //lo ejecuto y lo guardo en un iterador.
		        Iterator listaDatosArchivo = session.createQuery(queryObjeto.toString()).list().iterator();

		        Object[] tupla = (Object[]) listaDatosArchivo.next();
		
		        strDevolucion[0] = tupla[0].toString();
			    strDevolucion[1] = tupla[1].toString();
			    strDevolucion[2] = tupla[2].toString();
		        //cometer la transaccion o sino no se escribe nada en la BD
				tx.commit();

				// cerrar la sesion
				//session.close();
				
	   } catch (HibernateException e) {

		   	System.out.println(e.getMessage());
		   	log.warn("Ocurrio un error al buscar los datos de la mayor Altura");

		   // cuando ocurre un error hace rollback
		   	if (tx != null)
		   		try {
		   			tx.rollback();
		   		} catch (HibernateException e1) {
		   			System.out.println("El rollback no fue exitoso");
		   			log.warn("El rollback no fue exitoso");
		   		}

            if (session != null)
            	try {
            		session.close();
            	} catch (HibernateException e2) {
            		System.out.println("El cierre de sesion no fue exitoso");
					log.warn("El cierre de sesion no fue exitoso");
            	}
	   }	
	
		return strDevolucion;
	}

	public static String[] getDatosAlturaMayor() {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
				
		// Iniciar la sesion con Hibernate
        SessionFactory sess = HibernateUtil.getSessionFactory();
        session = sess.getCurrentSession();
   
        // Comenzar la transaccion
        tx = session.beginTransaction();
        
        Logger log = Logger.getLogger("Obtener los datos de la mayor altura");
		log.info("Obtener los datos de la mayor altura");
		
		String[] strDevolucion = new String[3];
	    try {		
	    		//hago un query con todos los datos 
		        StringBuilder queryObjeto= new StringBuilder();
		        queryObjeto.append("select dp.altura, dp.promedioVelocidadRenglon, dp.promedioTempRenglon from DatosPerfilVertical as dp where dp.altura =  ");
		        queryObjeto.append("(select MAX(dp2.altura) from DatosPerfilVertical as dp2)");
		        
		        //lo ejecuto y lo guardo en un iterador.
		        Iterator listaDatosArchivo = session.createQuery(queryObjeto.toString()).list().iterator();

		        Object[] tupla = (Object[]) listaDatosArchivo.next();
		
		        strDevolucion[0] = tupla[0].toString();
			    strDevolucion[1] = tupla[1].toString();
			    strDevolucion[2] = tupla[2].toString();
		        //cometer la transaccion o sino no se escribe nada en la BD
				tx.commit();

				// cerrar la sesion
				//session.close();
				
	   } catch (HibernateException e) {

		   	System.out.println(e.getMessage());
		   	log.warn("Ocurrio un error al buscar los datos de la mayor Altura");

		   // cuando ocurre un error hace rollback
		   	if (tx != null)
		   		try {
		   			tx.rollback();
		   		} catch (HibernateException e1) {
		   			System.out.println("El rollback no fue exitoso");
		   			log.warn("El rollback no fue exitoso");
		   		}

            if (session != null)
            	try {
            		session.close();
            	} catch (HibernateException e2) {
            		System.out.println("El cierre de sesion no fue exitoso");
					log.warn("El cierre de sesion no fue exitoso");
            	}
	   }	
	
		return strDevolucion;
	}
	
public static void setNumerodeRichardson(){
		
/*		Rib = [(g.(T2-T1)) / Tmed] * [(Z2-Z1) / (U2-U1)2] 
 * 		g = gravedad
 * 		T2 = promedio Temperatura altura 2
 * 		T1 = promedio Temperatura altura 1
 * 		Tmed = temperatura media de T1 y T2
 * 		z2 = altura 2
 * 		z1 = altura 1
 * 		u2 = velocidad promedio altura 2
 * 		u1 = velocidad promedio altura 1
 * */
		
		String[] datosAlturaMayor = getDatosAlturaMayor();
		
		int alturaMayor = Integer.parseInt(datosAlturaMayor[0].toString());
		double velocidadAlturaMayor = Double.parseDouble(datosAlturaMayor[1].toString());
		double temperaturaAlturaMayor = Double.parseDouble(datosAlturaMayor[2].toString());
		
//		System.out.println("alturaMayor : " + alturaMayor);
//		System.out.println("temperaturaAlturaMayor : " + temperaturaAlturaMayor);
//		System.out.println("velocidadAlturaMayor : " + velocidadAlturaMayor);
		
		String[] datosAlturaMenor = getDatosAlturaMenor();
		
		int alturaMenor = Integer.parseInt(datosAlturaMenor[0].toString());;		
		double velocidadAlturaMenor = Double.parseDouble(datosAlturaMenor[1].toString());
		double temperaturaAlturaMenor = Double.parseDouble(datosAlturaMenor[2].toString());
		
//		System.out.println("alturaMenor : " + alturaMenor);
//		System.out.println("temperaturaAlturaMenor : " + temperaturaAlturaMenor);
//		System.out.println("velocidadAlturaMenor : " + velocidadAlturaMenor);
		
		double mediaTemperatura = (temperaturaAlturaMayor + temperaturaAlturaMenor) /2;
		
//		System.out.println("mediaTemperatura : " + mediaTemperatura);				
		
		double velocidadesAlCuadrado = Math.pow((velocidadAlturaMayor - velocidadAlturaMenor),2);
//		System.out.println("velocidadesAlCuadrado : " + velocidadesAlCuadrado);
		
		double primerParametro = (gravedad * (temperaturaAlturaMayor - temperaturaAlturaMenor)) / mediaTemperatura;
//		System.out.println("primerParametro : " + primerParametro);
		
		double segundoParametro = (alturaMayor - alturaMenor) / velocidadesAlCuadrado;
//		System.out.println("segundoParametro : " + segundoParametro);
		
		richardson = primerParametro * segundoParametro;

	}
	
	public static void setLongitudMoninObukov(){
		
/*		Caso estable o neutro z/L= (1-13.8*Ri +- sqrt(1+9.2*Ri)) / (95.22 * Ri -18.4)
		Caso inestable z/L = Ri
		z es a elección, nosotros vamos a utilizar la media entre la altura mayor y la menor 
 */
	
		//double ri = getFormuladeRichardson();
		
		String[] datosAlturaMayor = getDatosAlturaMayor();
		int alturaMayor = Integer.parseInt(datosAlturaMayor[0].toString());
		
		String[] datosAlturaMenor = getDatosAlturaMenor();		
		int alturaMenor = Integer.parseInt(datosAlturaMenor[0].toString());
		
		double z = Math.sqrt(alturaMenor * alturaMayor); 
//		System.out.println("z  : " + z);

		if (richardson >= -0.01 ){ //caso estable o neutro
			
			System.out.println("richardson: " + richardson);
			double primerParametroNumerador = 1-(13.8 * richardson);
			System.out.println("primerParametroNumerador: " + primerParametroNumerador);
			
			double parametroRaiz = 1+(9.2*richardson);
			double segundoParametroNumerador = Math.sqrt(parametroRaiz);
			System.out.println("segundoParametroNumerador: " + segundoParametroNumerador);
			
			double parametroDenominador = (95.22*richardson) - 18.4;
			System.out.println("parametroDenominador: " + parametroDenominador);					
			
			double parametroNumeradorSuma = primerParametroNumerador + segundoParametroNumerador;		
			double parametroNumeradorResta = primerParametroNumerador - segundoParametroNumerador;
			System.out.println("parametroNumeradorSuma: " + parametroNumeradorSuma);
			System.out.println("parametroNumeradorResta : " + parametroNumeradorResta);
			
			double resultadoConSuma = parametroNumeradorSuma  / parametroDenominador;
			double resultadoConResta = parametroNumeradorResta / parametroDenominador;
			System.out.println("resultadoConSuma: " + resultadoConSuma);
			System.out.println("resultadoConResta: " + resultadoConResta);
			
			longMoninObukhov = z / resultadoConSuma; // asignando resultado a l tomando en cuenta la suma
			System.out.println("resultado suma longMoninObukhov : " + longMoninObukhov);
			double signo = longMoninObukhov * richardson;
			
			if ( (signo < 0) || (longMoninObukhov == 0 && richardson < 0) || (longMoninObukhov < 0 && richardson == 0) ){ // verificando que longMoninObukhov calculado por la suma tenga un signo distinto que el de richardson
				longMoninObukhov = z / resultadoConResta; // asignando resultado a l tomando en cuenta la resta
//				System.out.println("resultado resta longMoninObukhov : " + longMoninObukhov);
			}
		}else{ // caso inestable
			longMoninObukhov =  z / richardson;			
			System.out.println("resultado inestable : " + longMoninObukhov);
//			System.out.println("resultado resta l : " + lresta);
		}

	}
	
	public static double getFideZdeL(int z){
/*		Caso estable fi(z/l) = 6.9 * (z/l)
		caso inestable fi(z/l) = (-2 * ln ( (1 + A) /2 ) - ln ( (1 + A(al cuadrado)) / 2 ) + 2 tg(a la menos 1 )(A - (pi / 2))
		caso neutro fi(z/l) = 0
		A = (1 - 22*(z/l))(a la 0.25)
*/
		
		double fi = 0; //por defecto coloco 0 a fi, este es el resultado para neutralidad
		
	//double l = getLongitudMoninObukov();
		
		if (richardson < -0.01){ // en inestabilidad
			
			double paramA = Math.pow((1- (22* z/longMoninObukhov)), 0.25);
			
			double primerParametro = -2 * Math.log((1+paramA)/2);
			
			double segundoParametro = Math.log((1+ Math.pow(paramA, 2)) / 2);
			
			double tercerParametro =  2 * Math.atan( paramA - (Math.PI / 2));  // calculo tangente a la menos 1
			
			fi = primerParametro - segundoParametro + tercerParametro;
			
		}else if(richardson > 0.01){  // en estabilidad
				
				fi = 6.9 * (z / longMoninObukhov);
			
		}
		
		return fi;
	}
	
	public static void setUestrella(){  // velocidad de friccion
//		u* = (( u1 - u2 ) * k) / ( ln (z1/z2) - fi(z1/l) + fi (z2/l) )
//		u1 = velocida promedio para altura 1
//		u1 = velocida promedio para altura 2
//		z1 = altura 1
//		z1 = altura 2
//		l = longitud de Monin-Obukhov
		
		String[] datosAlturaMenor = getDatosAlturaMenor();
		
		int alturaMenor = Integer.parseInt(datosAlturaMenor[0].toString());;		
		double velocidadAlturaMenor = Double.parseDouble(datosAlturaMenor[1].toString());
		
		String[] datosAlturaMayor = getDatosAlturaMayor();
		
		int alturaMayor = Integer.parseInt(datosAlturaMayor[0].toString());
		double velocidadAlturaMayor = Double.parseDouble(datosAlturaMayor[1].toString());
		
		double parametroNumerador = (velocidadAlturaMenor - velocidadAlturaMayor) * k;
		
		double primerParamDenominador = Math.log(alturaMenor / alturaMayor);
		double segundoParamDenominador = getFideZdeL(alturaMenor);
		double tercerParamDenominador = getFideZdeL(alturaMayor);
		
		double parametroDenominador = primerParamDenominador - segundoParamDenominador + tercerParamDenominador;
		
		uEstrella = parametroNumerador / parametroDenominador;
	}
	
	public static void setZetaCero(){  // longitud de rugosidad 
//		ln z0 = ( (u1 *ln z2) - (u2 * ln z1) ) / (u1 - u2)
//		z0 = e (elevado a ( (u1 *ln z2) - (u2 * ln z1) ) / (u1 - u2) )
//		u1 = velocida promedio para altura 1
//		u1 = velocida promedio para altura 2
//		z1 = altura 1
//		z1 = altura 2

		String[] datosAlturaMenor = getDatosAlturaMenor();
		
		int alturaMenor = Integer.parseInt(datosAlturaMenor[0].toString());;		
		double velocidadAlturaMenor = Double.parseDouble(datosAlturaMenor[1].toString());
		
		String[] datosAlturaMayor = getDatosAlturaMayor();
		
		int alturaMayor = Integer.parseInt(datosAlturaMayor[0].toString());
		double velocidadAlturaMayor = Double.parseDouble(datosAlturaMayor[1].toString());
		
		double parametroNumerador = (velocidadAlturaMenor * Math.log(alturaMayor)) - (velocidadAlturaMayor * Math.log(alturaMenor));
		
		double parametroDenominador = velocidadAlturaMenor - velocidadAlturaMayor;
		
		double resultado = parametroNumerador / parametroDenominador;
		
		z0 = Math.exp(resultado);

	}
	
	public static double getValorFuncionLogaritmica(double altura){
//		U(z) = ( u* / k ) * ln( z / zo )
		
		double resultado;
		double z = altura;
		
		iniciarVariablesLogaritmica(); // inicializo las variables globales si aun no han sido seteadas
		
		double parametroNumerador = uEstrella / k;
		double parametroDenominador = Math.log(z / z0);

		resultado = parametroNumerador / parametroDenominador;
		
		return resultado;
		
	}
	
	private static void iniciarVariablesLogaritmica(){
		
		if (!variablesIniciadasLogaritmicas){
			setNumerodeRichardson();
			setLongitudMoninObukov();
			setUestrella();
			setZetaCero();
			variablesIniciadasLogaritmicas = true;
		}		
	}
	
	// controlo que tengo dos alturas para poder calcular la función logaritmica
	public static boolean tengoDatosdeDosAlturas(){
		
		boolean retorno = false;
		
		String[] datosAlturaMenor = getDatosAlturaMenor();
		
		int alturaMenor = Integer.parseInt(datosAlturaMenor[0].toString());;		
		double velocidadAlturaMenor = Double.parseDouble(datosAlturaMenor[1].toString());
		double temperaturaAlturaMenor = Double.parseDouble(datosAlturaMenor[2].toString());
		
		String[] datosAlturaMayor = getDatosAlturaMayor();
		
		int alturaMayor = Integer.parseInt(datosAlturaMayor[0].toString());
		double velocidadAlturaMayor = Double.parseDouble(datosAlturaMayor[1].toString());
		double temperaturaAlturaMayor = Double.parseDouble(datosAlturaMayor[2].toString());
		
		
		if ((alturaMenor != alturaMayor) && (alturaMenor > 0) && (alturaMayor > 0)){
			if ((velocidadAlturaMenor) > 0 && (velocidadAlturaMayor > 0)){
				if ((temperaturaAlturaMenor > 0) && (temperaturaAlturaMayor > 0)){
					retorno = true;
				}
			}	
		}
		return retorno;
	}
	
	private static void setPe(double z, double z0){
//	  p = 1 / ln( z / z0 ) "con datos de una altura" longitud de rugosidad z0 es elegido por el usuario
//    z = altura a la que se quiere conocer la velocidad
//	  p = ln( u2 / u1 ) / ln( z2 / z1 )
//    u2, u1= velocidad en altura 2 y altura 1 respectivamente
//	  z2, z1= altura 2 y altura 1
//	  cuando tenemos datos a una sola altura el valor de p es 1/7. en el futuro obtendremos el valor de p pidiendole al usuario el valor a utilizar de p
//	  o podriamos pedirle el valor de z0 para poder calcularlo con la primera función
	  
		if (tengoDatosdeDosAlturas()){
			String[] datosAlturaMenor = getDatosAlturaMenor();
			
			int alturaMenor = Integer.parseInt(datosAlturaMenor[0].toString());;		
			double velocidadAlturaMenor = Double.parseDouble(datosAlturaMenor[1].toString());
			
			String[] datosAlturaMayor = getDatosAlturaMayor();
			
			int alturaMayor = Integer.parseInt(datosAlturaMayor[0].toString());
			double velocidadAlturaMayor = Double.parseDouble(datosAlturaMayor[1].toString());
			
			double parametroNumerador = Math.log(velocidadAlturaMayor / velocidadAlturaMenor);
			
			double parametroDenominador = Math.log(alturaMayor / alturaMenor);
			
			p = parametroNumerador / parametroDenominador;
		} else{
//			
//			double parametroDenominador = Math.log( z / z0);  // que es z y que es z0 -- z es la altura a la que queremos conocer la velocidad. z0 es la long de 
//			                                                     rugosidad ( como en este caso no se puede calcular por que tengo datos a una sola altura
//		                                                       este parametro podria ser ingresado por el usuario
//			p = 1 / parametroDenominador;
		  
		  p = 1/7; // este número es muy utilizado cuando solo existen datos a una sola altura. 
		}
	}
	
	private static void setDatosAlturaParaPotencia(){
		
		String[] datosAlturaMenor = getDatosAlturaMenor();
		
		z1 = Integer.parseInt(datosAlturaMenor[0].toString()); // altura 		
		u1 = Double.parseDouble(datosAlturaMenor[1].toString()); // velocidad promedio
	}
	
	private static void iniciarVariablesPotencia(double altura, double longitudRugosidad){
		
		if (!variablesIniciadasPotencia){	
		  setPe(altura, longitudRugosidad);
			setDatosAlturaParaPotencia();
			variablesIniciadasPotencia = true;		
		}		
	}
	
	public static double getValorFuncionPotencia(double altura){  // Funcion de Potencia (Sutton, 1953)
//		U(z) = u1 * (z / z1) elevado a la p
//		u1 = velocidad de la altura que tomo como referencia
//		z1 = altura que tomo como referencia
//	  z = altura a la que quiero obtener el calculo
		
		double resultado;
    double z = altura;
//    double z0 = longitudRugosidad;
//  iniciarVariablesPotencia(z, z0);
		iniciarVariablesPotencia(z, 0);

		double parametroPotencia = z / z1;
				
		resultado = u1 * Math.pow(parametroPotencia, p);
		
		return resultado;	
	}
}



















