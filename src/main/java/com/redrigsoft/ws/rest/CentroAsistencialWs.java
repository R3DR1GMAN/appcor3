package com.redrigsoft.ws.rest;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces; 
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.redrigsoft.dao.CentroAsistencialDao;
import com.redrigsoft.dao.UsuarioDao;
import com.redrigsoft.util.FormatoJson;
  
@Path("centroasistencial")
public class CentroAsistencialWs {
	
	private Gson gson= new Gson();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)  
	public String obtenerCentroAsistencial(@QueryParam("sisgestor") String v_sistGestor, @HeaderParam("authorization") String v_claveApi){
		
		String resultado=""; 
		
		int id_Usuario = UsuarioDao.autorizarUsuario(3, v_claveApi);
		
		if(id_Usuario!=0){
			String arrayJsonCentros = gson.toJson(CentroAsistencialDao.obtenerCentroAsistencial(v_sistGestor));
			resultado = FormatoJson.respuestaOk("centro_asistencial", true, arrayJsonCentros);
	
		}else{
		    resultado = FormatoJson.respuestaKo("centro_asistencial", false, "Se requiere una clave Api para autenticación");
		}
		
		return resultado;
	}
		
	
	
	
/*	   
	@POST
	@Path("/sync")
	@Consumes(MediaType.APPLICATION_JSON)  
	@Produces(MediaType.APPLICATION_JSON)
	public String sincronizarCentrosAsistencialesPOST(String v_todosCentrosAsistenciales, @HeaderParam("authorization") String v_claveApi){ 
		System.out.println("-> sincronizarCentrosAsistencialesPOST():"); 

		String resultado="";     
		 
		int id_Usuario = UsuarioDao.autorizarUsuario(v_claveApi);
		
		if(id_Usuario!=0){
			
			String[] partes = v_todosCentrosAsistenciales.split("\\*");
			String   todosCentrosAsistenciales = "";
			String   idDispositivo="";
			String   idToken="";           
			
			if(partes!=null && partes.length==1) {
				 todosCentrosAsistenciales = partes[0];

			}else if(partes!=null && partes.length==3){
			     todosCentrosAsistenciales = partes[0];
			     idDispositivo 			   = partes[1];
			     idToken 			       = partes[2];
			     CuentaEssaludDao.actualizarFechaUltimaSync(idDispositivo,idToken);
			}

			List<BCentroAsistencialEssalud> L1 = (Arrays.asList(gson.fromJson(todosCentrosAsistenciales, BCentroAsistencialEssalud[].class)));
			List<BCentroAsistencialEssalud> L2 = CentroAsistencialDao.obtenerCentroAsistencialActualizados();
			
			List<BCentroAsistencialEssalud> L3 = CentroAsistencialDao.obtenerNuevosCentrosAsistenciales(L1);
			List<BCentroAsistencialEssalud> L5 = CentroAsistencialDao.filtroCentrosAsistencialesActualizados(L1, L2);
			
			List<BCentroAsistencialEssalud> L6 = new ArrayList<BCentroAsistencialEssalud>();
		        L6.addAll(L3); 
				L6.addAll(L5);
			
			JSONArray arrayCentros = CentroAsistencialDao.obtenerCentrosAsistencialesEssaludJson(L6); 		
			resultado = FormatoJson.respuestaOk("centro_asistencial", true, arrayCentros);

		}else{
		    resultado = FormatoJson.respuestaKo("centro_asistencial", false, "Se requiere una clave Api para autenticación");
		}
		    System.out.println("-> Retornando resultado sync: "+resultado); 

		return resultado;
	}
*/
}
