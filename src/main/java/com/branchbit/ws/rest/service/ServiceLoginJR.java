package com.branchbit.ws.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.branchbit.ws.rest.vo.Archivo;
import com.branchbit.ws.rest.vo.Respuesta;
import com.branchbit.ws.rest.vo.VOUsuario;

@Path("/Home")
public class ServiceLoginJR {
	
	@GET
	@Path("/saludar/{nombre}")
	@Produces({MediaType.TEXT_PLAIN})
	public Response Saludar(@PathParam("nombre") String nombre) {
		return Response.status(200).entity("Tu nombre aaes "+nombre).build();
	}
	

	@POST
	@Path("/validarUsuario")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON})
	public VOUsuario validarUsuario(VOUsuario vo) {
		vo.setUserValido(false);
		if (vo.getUsuario().equals("ADRIAN") && vo.getPassword().equals("123")) {
			vo.setUserValido(true);
		}
		return vo;
	}
	
	@POST
	@Path("/recibirParametro")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Respuesta getServlet(Archivo archivo) {
		archivo.setFolio("1");
		if(archivo.getFolio().equals("1")) {
			System.out.println("Ok");
		}
		
		Respuesta respuesta=new Respuesta(false,"Archivo procesado correctamente");
		return respuesta;
	}
}
