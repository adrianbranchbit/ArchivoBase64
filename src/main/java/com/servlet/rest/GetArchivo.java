package com.servlet.rest;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
import org.json.JSONObject;

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

		/*
		 * String nombre = request.getParameter("usuario"); String password =
		 * request.getParameter("password");
		 */
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

				Archivo file = new Archivo();
				file.setFolio("123");
				file.setFileName(fileName);
				file.setFileBase64(fileBase64);
				file.setContentType(contentType);
				
				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				
				
				HttpURLConnection conn;
				URL url = new URL("http://localhost:8080/RestJR/rest/Home/recibirParametro");
				conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setDoInput(true);
				
				//Asi error 415 application/json COnsumir json en el REST poner json aqui..aun no imprime
				conn.setRequestProperty("Content-Type", "application/json");
				
				conn.setRequestProperty("Accept", "application/json");
				conn.setRequestMethod("POST");

				JSONObject json = new JSONObject();
				json.put("folio",file.getFolio());
				json.put("fileName", file.getFileName());
				json.put("fileBase64", file.getFileBase64());
				json.put("contentType", file.getContentType());
				
				OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
				wr.write(json.toString());
				wr.flush();
				
				StringBuilder sb =new StringBuilder();
				int HttpResult = conn.getResponseCode();
				System.out.println(HttpResult);
				if (HttpResult == HttpURLConnection.HTTP_OK) {
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
					String line = null;
					while ((line = br.readLine()) != null) {
						sb.append(line + "\n");
					}
					out.println("["+sb.toString()+"]");
					br.close();
				} else {
					System.out.println(conn.getResponseMessage());
					out.println(conn.getResponseMessage());
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void Procesar(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out;
		try {
			out = response.getWriter();

			response.setContentType("application/json");

			HttpURLConnection conn;
			URL url = new URL("http://localhost:8080/RestJR/rest/Home/recibirParametro");
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("archivo", "Cruz islas");
			out.println(conn.getResponseCode());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
