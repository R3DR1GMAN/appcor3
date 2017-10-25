package com.redrigsoft.mb;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.redrigsoft.dao.UsuarioDao;


@ManagedBean
public class MBLogin {
	
	private String user;
	private String pass;
	
	public MBLogin() {
			 
	}
	
	public String login() {
		System.out.println("-> login():");		

		String urlReturn="";

		if(user.isEmpty() || pass.isEmpty()){
			FacesContext context = FacesContext.getCurrentInstance();  
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Observación",  "Ingresar usuario y contraseña") );
	        			
		}else{
			   if(UsuarioDao.validarUsuarioContrasenia(1, user, pass)!=null){ 
			      urlReturn="pages/home";  
			   }else{
					 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Observación", "Usuario y/o contraseña incorrectos"));
			   }	     
		}
	
		return urlReturn;	
	} 
	
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
