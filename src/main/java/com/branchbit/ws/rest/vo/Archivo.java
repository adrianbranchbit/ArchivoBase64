package com.branchbit.ws.rest.vo;

public class Archivo {
	private String folio;
	private String fileBase64;
	private String fileName;
	private String contentType;
	
	public Archivo() {
		// TODO Auto-generated constructor stub
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getFileBase64() {
		return fileBase64;
	}

	public void setFileBase64(String fileBase64) {
		this.fileBase64 = fileBase64;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	public String toString() {
		return "[folio=123,fileName= " + getFileName() + ",ContentType=" + getContentType() + ",Base64="
				+ getFileBase64() + "]";
	}

}
