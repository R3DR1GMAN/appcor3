package com.redrigsoft.ws.rest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.redrigsoft.bean.BCuentaEssalud;
import com.redrigsoft.dao.CuentaEssaludDao;
import com.redrigsoft.dao.UsuarioDao;
import com.redrigsoft.util.FormatoJson;
import com.redrigsoft.util.Seguridad;
  
@Path("cuentaessalud")
public class CuentaEssaludWs {
	 
	private Gson gson= new Gson();
	
	
	@GET
	@Path("/consultar")
	@Produces(MediaType.APPLICATION_JSON)  
	public String consultarCuentaPorCodDispositivo(@QueryParam("cod_dispositivo") String cod_dispositivo, @HeaderParam("authorization") String v_claveApi){
		System.out.println("-> consultarCuentaPorIdDispositivo(): "+cod_dispositivo); 
		String resultadoWs ="";
		
		try {		
		    BCuentaEssalud bCuenta = CuentaEssaludDao.obtenerCuentaPorIdDispositivo(3, cod_dispositivo);			
			String jsonCuenta  = gson.toJson(bCuenta);				
			resultadoWs = FormatoJson.respuestaJson("cuenta_essalud", true, jsonCuenta);
		} catch (Exception e) {
			System.out.println("*Ocurrio un error en consultarCuentaPorCodDispositivo(): "+e); 
			resultadoWs = FormatoJson.respuestaJson("cuenta_essalud", false, "");
		}
		
		System.out.println("*resultadoWs: "+resultadoWs); 
		return resultadoWs;
	}
	
	
	@POST
	@Path("/registrar") 
	@Consumes(MediaType.APPLICATION_JSON)  
	@Produces(MediaType.APPLICATION_JSON)
	public String registrarCuentaEssalud(String v_cadena, @HeaderParam("authorization") String v_claveApi){
		System.out.println("-> registrarCuentaEssalud()::: v_cadena: "+v_cadena);   
		String resultadoWs="";	
		
		BCuentaEssalud bCuenta = gson.fromJson(v_cadena, BCuentaEssalud.class);
		bCuenta.setClaveApi(Seguridad.generarClaveApi());
		ArrayList<String> resultadoDao = CuentaEssaludDao.insertarCuentaEssalud(3, bCuenta); 
					
		if(resultadoDao.get(0).equals("0")){
		   resultadoWs = FormatoJson.respuestaJson("Registro usuario", "true", gson.toJson(bCuenta));
		}else{
		   resultadoWs = FormatoJson.respuestaJson("Registro usuario", "false", "Lo sentimos, ocurrió un error al tratar de crear al usuario");
		}

		System.out.println("*resultadoWs: "+resultadoWs); 		
		return resultadoWs;
	}

		
	@POST
	@Path("/actualizar")
	@Consumes(MediaType.APPLICATION_JSON)  
	@Produces(MediaType.APPLICATION_JSON)
	public String actualizarCuentaEssalud(String v_cadena, @HeaderParam("authorization") String v_claveApi){
		System.out.println("-> actualizarCuentaEssalud()::: v_cadena: "+v_cadena);   
		String resultadoWs="";	
		int id_Usuario = UsuarioDao.autorizarUsuario(3, v_claveApi);
		
		if(id_Usuario!=0){
			
			BCuentaEssalud bCuenta = gson.fromJson(v_cadena, BCuentaEssalud.class);			         
			int resultadoDao = CuentaEssaludDao.actualizarCuentaEssalud(3, id_Usuario, bCuenta); 
			
			if(resultadoDao==1){ 
				   resultadoWs = FormatoJson.respuestaJson("Actualiza cuenta", "true",  "Se actualizo correctamente la cuenta del usuario "+bCuenta.getUsuario());
			}else if(resultadoDao==0){
				   resultadoWs = FormatoJson.respuestaJson("Actualiza cuenta", "false", "No se pudo actualizar al "+bCuenta.getUsuario()+" en el sistema");
			}
				
		}else{
			resultadoWs = FormatoJson.respuestaKo("Actualiza cuenta", false, "Se requiere una clave Api para autenticación");
		}
		
		System.out.println("*resultadoWs: "+resultadoWs); 
		
		return resultadoWs;
	}
}
