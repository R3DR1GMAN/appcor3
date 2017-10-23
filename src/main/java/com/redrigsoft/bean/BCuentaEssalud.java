package com.redrigsoft.bean;

public class BCuentaEssalud {
	
	private int    idCuenta;
	private int    idUsuario;
	private String usuario;
	private String email;
	private String codDispositivo;
	private String codToken; 
	private String estado;
	private String fecRegistro;
	private String fecUltSync;
	
	public int getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCodDispositivo() {
		return codDispositivo;
	}
	public void setCodDispositivo(String codDispositivo) {
		this.codDispositivo = codDispositivo;
	}
	public String getCodToken() {
		return codToken;
	}
	public void setCodToken(String codToken) {
		this.codToken = codToken;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFecRegistro() {
		return fecRegistro;
	}
	public void setFecRegistro(String fecRegistro) {
		this.fecRegistro = fecRegistro;
	}
	public String getFecUltSync() {
		return fecUltSync;
	}
	public void setFecUltSync(String fecUltSync) {
		this.fecUltSync = fecUltSync;
	}
	
}
