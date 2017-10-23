package com.redrigsoft.ws.rest;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.redrigsoft.bean.BCuentaEssalud;
import com.redrigsoft.dao.CuentaEssaludDao;
import com.redrigsoft.util.FormatoJson;
  
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
			resultadoWs = FormatoJson.respuestaJson("cuenta_essalud", false, gson.toJson(new BCuentaEssalud()));
		}
		
		System.out.println("*resultadoWs: "+resultadoWs); 
		return resultadoWs;
	}
	
/*	
	
	@POST
	@Path("/registrar") 
	@Consumes(MediaType.APPLICATION_JSON)  
	@Produces(MediaType.APPLICATION_JSON)
	public String registrarCuentaEssalud(String v_cadena, @HeaderParam("authorization") String v_claveApi){
		System.out.println("-> registrarCuentaEssalud()::: v_cadena: "+v_cadena);   
		String resultadoWs="";	
		
		int id_Usuario = UsuarioDao.autorizarUsuario(v_claveApi);
		
		if(id_Usuario!=0){
			BCuentaEssalud[] bCuenta = gson.fromJson(v_cadena, BCuentaEssalud[].class);		
						         
			ArrayList<String> resultadoDao = CuentaEsSaludDao.insertarCuentaEssalud(bCuenta[0]); 
			
			if(resultadoDao.get(0).equals("0")){ 
				   resultadoWs = FormatoJson.respuestaJson("Registra cuenta", "true",  "Se registro correctamente la cuenta del usuario "+bCuenta[0].getUsuario());
			}else if(resultadoDao.get(0).equals("1")){ 
				   resultadoWs = FormatoJson.respuestaJson("Registra cuenta", "false", "El usuario "+bCuenta[0].getUsuario()+" ya se encuentra registrado en el sistema");
			}else if(resultadoDao.get(0).equals("2")){ 
				   resultadoWs = FormatoJson.respuestaJson("Registra cuenta", "false", "El email "+bCuenta[0].getEmail()+" ya se encuentra registrado en el sistema");
			}else{
				   resultadoWs = FormatoJson.respuestaJson("Registra cuenta", "false", "No se pudo registrar al "+bCuenta[0].getUsuario()+" en el sistema");
			}
				
		}else{
			resultadoWs = FormatoJson.respuestaKo("centro_asistencial", false, "Se requiere una clave Api para autenticación");
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
		
		int id_Usuario = UsuarioDao.autorizarUsuario(v_claveApi);
		
		if(id_Usuario!=0){
			BCuentaEssalud[] bCuenta = gson.fromJson(v_cadena, BCuentaEssalud[].class);		
						         
			int resultadoDao = CuentaEsSaludDao.actualizarCuentaEssalud(bCuenta[0]); 
			
			if(resultadoDao==1){ 
				   resultadoWs = FormatoJson.respuestaJson("Actualiza cuenta", "true",  "Se actualizo correctamente la cuenta del usuario "+bCuenta[0].getUsuario());
			}else if(resultadoDao==0){
				   resultadoWs = FormatoJson.respuestaJson("Actualiza cuenta", "false", "No se pudo actualizar al "+bCuenta[0].getUsuario()+" en el sistema");
			}
				
		}else{
			resultadoWs = FormatoJson.respuestaKo("Actualiza cuenta", false, "Se requiere una clave Api para autenticación");
		}
		
		System.out.println("*resultadoWs: "+resultadoWs); 
		
		return resultadoWs;
	}
*/

}
