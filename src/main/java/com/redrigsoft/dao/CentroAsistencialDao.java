package com.redrigsoft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.redrigsoft.bean.BCentroAsistencialEssalud;
import com.redrigsoft.db.DBConexion;

public class CentroAsistencialDao {

	
	public static List<BCentroAsistencialEssalud> obtenerCentroAsistencial(String v_sistGestor){
		
		List<BCentroAsistencialEssalud> listaCentros = new ArrayList<BCentroAsistencialEssalud>();
		
		   Connection        con = null; 
		   PreparedStatement pst = null;
		   ResultSet         rs  = null;	   
		   	
		   try {
				   
			    con = DBConexion.crearConexionDB();	 
			    
			    if(v_sistGestor!=null && !v_sistGestor.trim().equals("x")){ 
			    	pst = con.prepareStatement("SELECT *FROM centro_asistencial where sist_gestor_centro=? and habilitado='SI'");
			    	pst.setString(1, v_sistGestor);
			    }else{
				    pst = con.prepareStatement("SELECT *FROM centro_asistencial where habilitado='SI'");
				}

				rs = pst.executeQuery();			    
	  
				while(rs.next()){
					BCentroAsistencialEssalud c = new BCentroAsistencialEssalud();
				   	c.setCod_centro(rs.getString("cod_centro"));
				   	c.setRed_centro(rs.getString("red_centro")); 
				   	c.setTipo_centro(rs.getString("tipo_centro")); 
				   	c.setDes_centro(rs.getString("des_centro"));
				   	c.setDep_centro(rs.getString("dep_centro"));
				   	c.setProv_centro(rs.getString("prov_centro"));
				   	c.setDist_centro(rs.getString("dist_centro")); 
				   	c.setDir_centro(rs.getString("dir_centro")); 
				   	c.setLatitud_centro(rs.getString("latitud_centro"));
				   	c.setLongitud_centro(rs.getString("longitud_centro"));
				   	c.setVeloc_conx_centro(rs.getString("veloc_conx_centro"));
				   	c.setTip_conx_centro(rs.getString("tip_conx_centro"));
				   	c.setSist_gestor_centro(rs.getString("sist_gestor_centro"));
				   	c.setCod_renaes(rs.getString("cod_renaes"));
				   	c.setFec_registro(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("fec_registro")));
				   	c.setFec_actualiza(rs.getTimestamp("fec_actualiza")== null? "" : new SimpleDateFormat("dd/MM/YYYY hh:mm:ss").format(rs.getTimestamp("fec_actualiza")));
				   	listaCentros.add(c);
				}
			
		    } catch (Exception e) {
			    System.out.println("*Ocurrio un error en obtenerCentroAsistencial(): "+e); 
		    } finally {
		    	DBConexion.cerrarConexionDB(con, pst,null, rs);		    	
			}

		return listaCentros;		
	}	
	
	
	
/*	
	public static List<BCentroAsistencial> obtenerCentrosAsistenciales(String codCentro, int idUsuario){
		
		List<BCentroAsistencial> listaCentros = new ArrayList<BCentroAsistencial>();
		
		   Connection        con = null; 
		   PreparedStatement pst = null;
		   ResultSet         rs  = null;
			 
		if(codCentro!=null &&  codCentro.equals("0")){ //Obtenemos todos los centros con SISCAP
			
		   try {
			
			    con = DBConexion.crearConexionDB();			    
			    pst = con.prepareStatement("SELECT *FROM centro_asistencial WHERE sistema_centro='SISCAP'");			
				rs = pst.executeQuery(); 
				
				while(rs.next()){
					BCentroAsistencial c = new BCentroAsistencial();
				   	c.setCod_centro(rs.getString("cod_centro"));
				   	c.setCod_red_centro(rs.getString("cod_red_centro")); 
				   	c.setTip_centro(rs.getString("tip_centro")); 
				   	c.setDes_centro(rs.getString("des_centro"));
				   	c.setDep_centro(rs.getString("dep_centro"));
				   	c.setProv_centro(rs.getString("prov_centro"));
				   	c.setDist_centro(rs.getString("dist_centro")); 
				   	c.setDir_centro(rs.getString("dir_centro")); 
				   	c.setLatitud_centro(rs.getString("latitud_centro"));
				   	c.setLongitud_centro(rs.getString("longitud_centro"));
				   	c.setCod_renaes(rs.getString("cod_renaes"));
				   	listaCentros.add(c);
				}
			
		    } catch (Exception e) {
			    System.out.println("*Ocurrio un error en obtenerCentrosAsistenciales(): "+e); 
		    } finally {
		    	DBConexion.cerrarConexionDB(con, pst,null, rs);		    	
			}
			
		}
		return listaCentros;		
	}


public static JSONArray obtenerCentrosAsistencialesJson(List<BCentroAsistencial> listaCentros){
		
		 JSONArray jArrayContactos = new JSONArray();
		 
		 for (BCentroAsistencial centro : listaCentros) { 
			  JSONObject jContacto = new JSONObject();
			  
		   try {
				  	jContacto.put("cod_centro", centro.getCod_centro());
				  	jContacto.put("cod_red_centro", centro.getCod_red_centro());
				  	jContacto.put("tip_centro", centro.getTip_centro());
				  	jContacto.put("des_centro", centro.getDes_centro());
				  	jContacto.put("dep_centro", centro.getDep_centro());
				  	jContacto.put("prov_centro", centro.getProv_centro());
				  	jContacto.put("dist_centro", centro.getDist_centro());			  	
					jContacto.put("dir_centro", centro.getDir_centro());
				  	jContacto.put("latitud_centro", centro.getLatitud_centro());
				  	jContacto.put("longitud_centro", centro.getLongitud_centro());
				  	jContacto.put("cod_renaes", centro.getCod_renaes());

				  	
				    jArrayContactos.put(jContacto);
				
			} catch (JSONException e) {
				System.out.println("Error en obtenerCentrosAsistencialesJson(): "+e);  
			}
		}
		 
		return jArrayContactos;
	}

public static JSONArray obtenerCentrosAsistencialesEssaludJson(List<BCentroAsistencialEssalud> listaCentros){
	
	 JSONArray jArrayContactos = new JSONArray();
	 
	 for (BCentroAsistencialEssalud centro : listaCentros) { 
		  JSONObject jContacto = new JSONObject();
		  
	   try {
			  	jContacto.put("cod_centro", centro.getCod_centro());
			  	jContacto.put("red_centro", centro.getRed_centro());
			  	jContacto.put("tipo_centro", centro.getTipo_centro());
			  	jContacto.put("des_centro", centro.getDes_centro());
			  	jContacto.put("dep_centro", centro.getDep_centro());
			  	jContacto.put("prov_centro", centro.getProv_centro());
			  	jContacto.put("dist_centro", centro.getDist_centro());			  	
				jContacto.put("dir_centro", centro.getDir_centro());
			  	jContacto.put("latitud_centro", centro.getLatitud_centro());
			  	jContacto.put("longitud_centro", centro.getLongitud_centro());
			  	jContacto.put("tip_conx_centro", centro.getTip_conx_centro());
			  	jContacto.put("veloc_conx_centro", centro.getVeloc_conx_centro());	
			  	jContacto.put("sist_gestor_centro", centro.getSist_gestor_centro());
			  	jContacto.put("cod_renaes", centro.getCod_renaes());
			  	jContacto.put("fec_registro", centro.getFec_registro());
			  	jContacto.put("fec_actualiza", centro.getFec_actualiza());
			    jArrayContactos.put(jContacto);
			
		} catch (JSONException e) {
			System.out.println("Error en obtenerCentrosAsistencialesEssaludJson(): "+e);  
		}
	}
	 
	return jArrayContactos;
}


public static List<BCentroAsistencialEssalud> obtenerCentroAsistencialActualizados(){
	
	List<BCentroAsistencialEssalud> listaCentros = new ArrayList<BCentroAsistencialEssalud>();
	
	   Connection        con = null; 
	   PreparedStatement pst = null;
	   ResultSet         rs  = null;	   
	   	
	   try {
			   
		    con = DBConexion.crearConexionDB();	 
			pst = con.prepareStatement("SELECT *FROM appcore.centro_asistencial_essalud where fec_actualiza IS NOT NULL");
			rs = pst.executeQuery();			    
  
			while(rs.next()){
				BCentroAsistencialEssalud c = new BCentroAsistencialEssalud();
			   	c.setCod_centro(rs.getString("cod_centro"));
			   	c.setFec_actualiza(rs.getTimestamp("fec_actualiza")== null? "" : new SimpleDateFormat("dd/MM/YYYY hh:mm:ss").format(rs.getTimestamp("fec_actualiza")));
			   	listaCentros.add(c);
			}
		
	    } catch (Exception e) {
		    System.out.println("*Ocurrio un error en obtenerCentroAsistencialActualizados(): "+e); 
	    } finally {
	    	DBConexion.cerrarConexionDB(con, pst,null, rs);		    	
		}

	return listaCentros;		
}


public static List<BCentroAsistencialEssalud> obtenerNuevosCentrosAsistenciales(List<BCentroAsistencialEssalud> listaCentrosActuales){
	System.out.println("-> DAO obtenerNuevosCentrosAsistenciales():");
	List<BCentroAsistencialEssalud> listaNuevosCentros = new ArrayList<BCentroAsistencialEssalud>();
	 
	String cad_centros_actuales="";
	int    cont=0;
	   
	for (BCentroAsistencialEssalud centroActual : listaCentrosActuales) {
		 cont++;
		 
		 if(cont==1){
			 cad_centros_actuales=centroActual.getCod_centro();
		 }else{
			 cad_centros_actuales= cad_centros_actuales+","+centroActual.getCod_centro(); 
		 } 
	}
	
	Connection        con = null; 
	PreparedStatement pst = null;
	ResultSet         rs  = null;	    
	   	
	   try {
			   
		    con = DBConexion.crearConexionDB();	 
			pst = con.prepareStatement("SELECT *FROM appcore.centro_asistencial_essalud where habilitado='SI' and cod_centro NOT IN ("+cad_centros_actuales+")");	
			rs  = pst.executeQuery();			    
  
			while(rs.next()){
				BCentroAsistencialEssalud c = new BCentroAsistencialEssalud();
			   	c.setCod_centro(rs.getString("cod_centro"));
			   	c.setRed_centro(rs.getString("red_centro")); 
			   	c.setTipo_centro(rs.getString("tipo_centro")); 
			   	c.setDes_centro(rs.getString("des_centro"));
			   	c.setDep_centro(rs.getString("dep_centro"));
			   	c.setProv_centro(rs.getString("prov_centro"));
			   	c.setDist_centro(rs.getString("dist_centro")); 
			   	c.setDir_centro(rs.getString("dir_centro")); 
			   	c.setLatitud_centro(rs.getString("latitud_centro"));
			   	c.setLongitud_centro(rs.getString("longitud_centro"));
			   	c.setVeloc_conx_centro(rs.getString("veloc_conx_centro"));
			   	c.setTip_conx_centro(rs.getString("tip_conx_centro"));
			   	c.setSist_gestor_centro(rs.getString("sist_gestor_centro"));
			   	c.setCod_renaes(rs.getString("cod_renaes"));
			   	c.setFec_registro(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("fec_registro")));
			   	c.setFec_actualiza(rs.getTimestamp("fec_actualiza")== null? "" : new SimpleDateFormat("dd/MM/YYYY hh:mm:ss").format(rs.getTimestamp("fec_actualiza")));
			   	listaNuevosCentros.add(c);
			}
		
	    } catch (Exception e) {
		    System.out.println("*Ocurrio un error en obtenerNuevosCentrosAsistenciales(): "+e); 
	    } finally {
	    	DBConexion.cerrarConexionDB(con, pst,null, rs);		    	
		}
	   
	   System.out.println("*listaNuevosCentros.size() :"+listaNuevosCentros.size()); 

	return listaNuevosCentros;		
}


public static List<BCentroAsistencialEssalud> filtroCentrosAsistencialesActualizados(List<BCentroAsistencialEssalud> listaTodosLosCentros, List<BCentroAsistencialEssalud> listaCentrosActualizados){
	System.out.println("--> DAO filtroCentrosAsistencialesActualizados():");
	
	List<BCentroAsistencialEssalud> L4 = new ArrayList<BCentroAsistencialEssalud>();
	List<BCentroAsistencialEssalud> L5 = new ArrayList<BCentroAsistencialEssalud>();
	
    for (BCentroAsistencialEssalud centro : listaTodosLosCentros){	
		for (BCentroAsistencialEssalud centroActualizado : listaCentrosActualizados) {
			if(centro.getCod_centro().equals(centroActualizado.getCod_centro())){
				L4.add(centro);
				break;
			}
		}
     }  
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    
    for (BCentroAsistencialEssalud centro : L4){
		for (BCentroAsistencialEssalud centroActualizado : listaCentrosActualizados) {
			
			if(centro.getCod_centro().equals(centroActualizado.getCod_centro())){
				
				try {
					
					if(centro.getFec_actualiza().isEmpty()){
						L5.add(centro);
						
					}else if((sdf.parse(centroActualizado.getFec_actualiza())).after((sdf.parse(centro.getFec_actualiza())))){
						      L5.add(centroActualizado);
					}
				} catch (ParseException e) {
					System.out.println("Error al compara fechas actualizadas de centros: centro.getFec_actualiza():"+centro.getFec_actualiza()+" v/s centroActualizado.getFec_actualiza():"+centroActualizado.getFec_actualiza());
					System.out.println("Error: "+e);
					e.printStackTrace();
				}
				
				break;
			}
		}
     }
    
    
   if(L5.size()> 0){
    
    String cad_centros_actualizados="";
	int    cont=0;
 
	for (BCentroAsistencialEssalud centroActualizado : L5) {
		 cont++;
		 
		 if(cont==1){
			 cad_centros_actualizados=centroActualizado.getCod_centro();
		 }else{
			 cad_centros_actualizados= cad_centros_actualizados+","+centroActualizado.getCod_centro(); 
		 }
	}
    
	
    System.out.println("*Cadena centros actualizados: "+cad_centros_actualizados);
	
	Connection        con = null; 
	PreparedStatement pst = null;
	ResultSet         rs  = null;
	L5.clear();
	   	
	   try {
			   
		    con = DBConexion.crearConexionDB();	 
			pst = con.prepareStatement("SELECT *FROM appcore.centro_asistencial_essalud where cod_centro IN ("+cad_centros_actualizados+")");	
			rs  = pst.executeQuery();			    
  
			while(rs.next()){
				BCentroAsistencialEssalud c = new BCentroAsistencialEssalud();
			   	c.setCod_centro(rs.getString("cod_centro"));
			   	c.setRed_centro(rs.getString("red_centro")); 
			   	c.setTipo_centro(rs.getString("tipo_centro")); 
			   	c.setDes_centro(rs.getString("des_centro"));
			   	c.setDep_centro(rs.getString("dep_centro"));
			   	c.setProv_centro(rs.getString("prov_centro"));
			   	c.setDist_centro(rs.getString("dist_centro")); 
			   	c.setDir_centro(rs.getString("dir_centro")); 
			   	c.setLatitud_centro(rs.getString("latitud_centro"));
			   	c.setLongitud_centro(rs.getString("longitud_centro"));
			   	c.setVeloc_conx_centro(rs.getString("veloc_conx_centro"));
			   	c.setTip_conx_centro(rs.getString("tip_conx_centro"));
			   	c.setSist_gestor_centro(rs.getString("sist_gestor_centro"));
			   	c.setCod_renaes(rs.getString("cod_renaes"));
			   	c.setFec_registro(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("fec_registro")));
			   	c.setFec_actualiza(rs.getTimestamp("fec_actualiza")== null? "" : new SimpleDateFormat("dd/MM/YYYY hh:mm:ss").format(rs.getTimestamp("fec_actualiza")));
			   	L5.add(c);
			}
		
	    } catch (Exception e) {
		    System.out.println("*Ocurrio un error en filtroCentrosAsistencialesActualizados(): "+e); 
	    } finally {
	    	DBConexion.cerrarConexionDB(con, pst,null, rs);		    	
		}
	   
    }  
	   
	System.out.println("*listaCentrosActualizados.size() :"+L5.size()); 

	return L5;
}

*/

}
