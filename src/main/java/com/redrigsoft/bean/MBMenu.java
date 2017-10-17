package com.redrigsoft.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class MBMenu {
	
	private int activeindex;
	
	public MBMenu() {
			
	}
	
	public void save() {
		System.out.println("-> save():");
		addMessage("Success", "Data Guardada");
	} 

	public void update() {
		System.out.println("-> update():");
		addMessage("Success", "Data Actualizada");
	}

	public void delete() {
		System.out.println("-> delete():");
		addMessage("Success", "Data Eliminada");
	}
	

	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext .getCurrentInstance().addMessage(null, message);
	}

	public int getActiveindex() {
		return activeindex;
	}

	public void setActiveindex(int activeindex) {
		this.activeindex = activeindex;
	}

}
