package com.branchbit.ws.rest.vo;

public class Respuesta {
	private boolean status;
	private String message;
	
	public Respuesta() {
		// TODO Auto-generated constructor stub
	}

	public Respuesta(boolean status, String message) {
		this.status = status;
		this.message = message;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "[status="+getStatus()+",message="+getMessage()+"]";
	}

}
