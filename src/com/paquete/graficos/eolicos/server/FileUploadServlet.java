package com.paquete.graficos.eolicos.server;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
	
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



public class FileUploadServlet extends HttpServlet {
	     
     
         @Override
         protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                 throws ServletException, IOException {
             super.doGet(req, resp);
         }
     
		@Override
         protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                 throws ServletException, IOException {
             
             //procesa peticiones multiples
             if (ServletFileUpload.isMultipartContent(req)) {
     
                 // Crear una fábrica de elementos de archivo
                 // basados en disco
                 FileItemFactory factory = new DiskFileItemFactory();
     
     
                 // Se parsea la solicitud
                 try {
                     // Crear un archivo nuevo controlador de carga
                     ServletFileUpload upload = new ServletFileUpload(factory);
         
                     resp.setContentType("text/plain");
                     
                     FileItemIterator iterator = upload.getItemIterator(req);
                     while (iterator.hasNext()) {
                       FileItemStream item = iterator.next();
                       InputStream stream = item.openStream();

                       if (item.isFormField()) {
                    	   System.out.println("Got a form field: " + item.getFieldName());
//                         log.warning("Got a form field: " + item.getFieldName());
                       } else {
                    	   System.out.println("Got an uploaded file: " + item.getFieldName() +
                                   ", name = " + item.getName());        
                    	  
//                         log.warning("Got an uploaded file: " + item.getFieldName() +
//                                     ", name = " + item.getName());

                         // You now have the filename (item.getName() and the
                         // contents (which you can read from stream).  Here we just
                         // print them back out to the servlet output stream, but you
                         // will probably want to do something more interesting (for
                         // example, wrap them in a Blob and commit them to the
                         // datastore).
                    	   ByteArrayOutputStream out = new ByteArrayOutputStream();
                         int len;
                         byte[] buffer = new byte[18192];
                         
                         int cont= 0;
                         while ((len = stream.read(buffer, 0, buffer.length)) != -1) {
                        	 out.write(buffer, 0, len);
                        	 
                           cont ++;
                           resp.getOutputStream().write(buffer, 0, len);
//                           System.out.println("cont " + cont +"  out : " + resp.toString());
                         }
                         System.out.println("salida correcta FileUploadServlet");
                         // controla el tamaño del archivo subido
//                         int maxFileSize = 10*(1024*1024); //10 megs max 
//                         if (out.size() > maxFileSize) { 
//                             throw new RuntimeException("File is > than " + maxFileSize);
//                         }
                       }
                     }
                 } catch (Exception e) {
                     resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                             "Ocurrió un error mientras se subía el documento : " + e.getMessage());
                 }
     
             } else {
                 resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE,
                                 "El tipo de contenido solicitado no es compatible con el servlet");
             }
         }
}
