package com.servlet.rest;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.codec.binary.Base64;

import com.branchbit.ws.rest.vo.Archivo;

/**
 * Servlet implementation class GetArchivo
 */
@MultipartConfig
@WebServlet("/Hola")
public class GetArchivo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public GetArchivo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String nombre = request.getParameter("usuario");
		String password = request.getParameter("password");

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("tu nombre es: " + nombre);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String nombre = request.getParameter("usuario");
		String password = request.getParameter("password");
		Part archivo = request.getPart("archivo");
		int size = (int) archivo.getSize();

		// Declarar la dependencia en el pom.xml e importar la libreria
		Base64 base64 = new Base64();

		String fileBase64 = "";
		String fileName = archivo.getSubmittedFileName();

		String contentType = archivo.getContentType();

		if (size > 0) {
			byte[] fileArray = new byte[size];
			DataInputStream inputStream = new DataInputStream(archivo.getInputStream());
			try {
				inputStream.readFully(fileArray);
				fileBase64 = base64.encodeToString(fileArray);
				
				
				Archivo file=new Archivo();
				file.setFileName(fileName);
				file.setFileBase64(fileBase64);
				file.setContentType(contentType);
				
				PrintWriter out=response.getWriter();
				response.setContentType("text/html");
				//out.println(file.toString());	
				//out.println("Todo ok");
				/*
				 * Enviar paramtros POST
				 * */
				HttpURLConnection conn;
				URL url = new URL("http://localhost:8080/RestJR/rest/Home/saludar/adrian");
				conn=(HttpURLConnection)url.openConnection();
				
				conn.setRequestMethod("GET");
				out.println(conn.getResponseCode());
				InputStream is=conn.getInputStream();
				out.println(is);
				out.println(response.getWriter());
				/*
				 * Leer respuesta
				 * 
				BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
				String respuesta=null;
				StringBuilder strBlr=new StringBuilder();
				
				while ((respuesta=br.readLine())!=null) {
					strBlr.append(respuesta);
				}
				
				if(br!=null) {
					br.close();
					conn.disconnect();
				}
				out.println(strBlr);
				*/
				//conn.setDoOutput(true);
				conn.setRequestProperty("Accept", "text/html");
				conn.setRequestProperty("nombre","adrian");
				
				
				
			
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			
		}
	}

}
