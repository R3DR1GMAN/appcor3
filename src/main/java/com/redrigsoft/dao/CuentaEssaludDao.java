package com.redrigsoft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.redrigsoft.bean.BCuentaEssalud;
import com.redrigsoft.db.DBConexion;

public class CuentaEssaludDao {
	 

	public static BCuentaEssalud obtenerCuentaPorIdDispositivo(int idCuenta, String cod_dispositivo){
		System.out.println("-> DAO obtenerCuentaPorIdDispositivo()::: idCuenta: "+idCuenta+" / cod_dispositivo: "+cod_dispositivo); 
		
		 BCuentaEssalud bCuenta = new BCuentaEssalud();
		 bCuenta.setEstado("-1"); //No existe por defecto
		
		 Connection        con = null;
		 PreparedStatement pst = null;
		 ResultSet         rs  = null;
		 
		 try {
			   
			    con = DBConexion.crearConexionDB();			    
			    pst = con.prepareStatement("SELECT *FROM cuenta_usuario cu INNER  JOIN  usuario us ON  us.idUsuario= cu.idUsuario WHERE  cu.idCuenta=? AND cu.cod_dispositivo=?");
			    pst.setInt(1, idCuenta);
			    pst.setString(2, cod_dispositivo);
				rs = pst.executeQuery();				

				while (rs.next()) {
					bCuenta = new BCuentaEssalud();
					bCuenta.setIdCuenta(rs.getInt("idCuenta"));
					bCuenta.setIdUsuario(rs.getInt("idUsuario")); 
					bCuenta.setUsuario(rs.getString("usuario")); 
					bCuenta.setEmail(rs.getString("email")); 
					bCuenta.setCodDispositivo(rs.getString("cod_dispositivo"));
					bCuenta.setCodToken(rs.getString("cod_token"));
					bCuenta.setEstado(rs.getString("estado")); 
					bCuenta.setFecRegistro(rs.getString("fec_registro")); 
					bCuenta.setFecUltSync(rs.getString("fec_ult_sync")); 				
				}

		    } catch (Exception e) {
			    System.out.println("*Ocurrió un error en obtenerCuentaPorIdDispositivo(): "+e); 
		    } finally {
		    	DBConexion.cerrarConexionDB(con, pst, null, rs);		    	
			}
			
		return bCuenta;		
	}
	
/*	
	public static JSONArray obtenerCuentaPorIdDispositivoJson(BCuentaEssalud cuenta){

		 JSONArray jArrayCuentas = new JSONArray();
		 
		 JSONObject jCuenta = new JSONObject();  
			  
		   try {

				  	jCuenta.put("idCuenta", cuenta.getIdCuenta());
				  	jCuenta.put("usuario",  cuenta.getUsuario());
				  	jCuenta.put("email", cuenta.getEmail());
				  	jCuenta.put("idDispositivo", cuenta.getIdDispositivo());
				  	jCuenta.put("idToken", cuenta.getIdToken());
				  	jCuenta.put("estado",   cuenta.getEstado());
				  	jCuenta.put("fec_registro", cuenta.getFec_registro());
				  	jCuenta.put("fec_ult_sync", cuenta.getFec_ult_sync());
				  	
				  	jArrayCuentas.put(jCuenta);
				
			} catch (JSONException e) {
				System.out.println("Error en obtenerCuentaPorIdDispositivoJson(): "+e);  
			}
	
		 
		return jArrayCuentas;
	}
	
	
	public static ArrayList<String> insertarCuentaEssalud(BCuentaEssalud bCuenta){
		   System.out.println("-> DAO insertarCuentaEssalud():");

		   boolean encontrado = false; 
		   ArrayList<String> resultadoArray = new ArrayList<String>();
		   
		   Connection        con = null;
		   PreparedStatement pst = null;
		   ResultSet         rs  = null;

		   
		   if(bCuenta!=null && !bCuenta.getUsuario().isEmpty() && !bCuenta.getEmail().isEmpty()){
			   try {
				   
				    con = DBConexion.crearConexionDB();				    
				    pst = con.prepareStatement("SELECT *FROM cuenta_essalud_maps WHERE usuario='"+bCuenta.getUsuario().trim()+"'");
					rs  = pst.executeQuery();
		
					while(rs.next()){
					   encontrado=true;
		
					}
										
					if(!encontrado){
						pst = con.prepareStatement("SELECT *FROM cuenta_essalud_maps WHERE email='"+bCuenta.getEmail().trim()+"'");
						rs = pst.executeQuery();
						
						while(rs.next()){
						   encontrado=true;
						}
						
						if(!encontrado){
							
							pst = con.prepareStatement("INSERT INTO cuenta_essalud_maps (usuario, email, idDispositivo, idToken, estado, fec_registro) VALUES (?,?,?,?,?,?)");
					   		pst.setString(1, bCuenta.getUsuario());
					   		pst.setString(2, bCuenta.getEmail());
					   		pst.setString(3, bCuenta.getIdDispositivo());
					   		pst.setString(4, bCuenta.getIdToken());
					   		pst.setString(5, "1");
					   		pst.setString(6, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					   		
					   		int records = pst.executeUpdate();
					   		
					   		if (records > 0) {
					   			resultadoArray.add("0");
								resultadoArray.add("La nueva cuenta se registro correctamente en el sistema");
							
					   		}else{
								resultadoArray.add("3");
								resultadoArray.add("No se pudo registrar la nueva cuenta");
							}
							
						}else{
							resultadoArray.add("2");
							resultadoArray.add("El email "+bCuenta.getEmail()+" ya se encuentra registrado en el sistema");
						}
						
					}else{
						resultadoArray.add("1");
						resultadoArray.add("El usuario "+bCuenta.getUsuario()+" ya se encuentra registrado en el sistema");
					} 										
				 
			    } catch (Exception e) {
			    	resultadoArray.add("4");
					resultadoArray.add("Ocurrio un error en insertarCuentaEssalud()");					
					System.out.println("Ocurrio un error en insertarCuentaEssalud(): "+e);
					
			    }finally {
			    	DBConexion.cerrarConexionDB(con, pst, null, rs);		    	
				}
		   }
		   
		return resultadoArray;	
	}
	
	
	public static int actualizarCuentaEssalud(BCuentaEssalud bCuentaEssalud){ 
		System.out.println("-> DAO actualizarCuentaEssalud()::: idDispositivo:"+bCuentaEssalud.getIdDispositivo()); 
		   int resultado         = 0;
		   Connection        con = null;
		   PreparedStatement pst = null;

		   try {
			   		con = DBConexion.crearConexionDB();
			   		pst = con.prepareStatement("UPDATE cuenta_essalud_maps SET idToken=? WHERE idDispositivo=?");
	   		 
			   		pst.setString(1, bCuentaEssalud.getIdToken());
			   		pst.setString(2, bCuentaEssalud.getIdDispositivo());

			   		if (pst.executeUpdate()> 0) { // Num de registros afectados
			   			resultado= 1;
					}
			
		   }catch (Exception e) {
			    System.out.println("*Ocurrio un error en actualizarCuentaEssalud(): "+e); 
		   } finally {
			   DBConexion.cerrarConexionDB(con, pst,null, null);			    	
		   }   
		     
		   System.out.println("*resultado: "+resultado); 
		   
		return resultado;	
	}
	
	
	public static int actualizarFechaUltimaSync(String idDispositivo, String idToken){
		System.out.println("-> DAO actualizarFechaUltimaSync()::: idDispositivo:"+idDispositivo+" idToken:"+idToken); 
		   int resultado         = 0;
		   Connection        con = null;
		   PreparedStatement pst = null; 

		   try {
			   		con = DBConexion.crearConexionDB();
			   		pst = con.prepareStatement("UPDATE cuenta_essalud_maps SET fec_ult_sync=?, idToken=? WHERE idDispositivo=?");
	   		 
			   		pst.setString(1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			   		pst.setString(2, idToken); 
			   		pst.setString(3, idDispositivo);

			   		if (pst.executeUpdate()> 0) { // Num de registros afectados
			   			resultado= 1;
					}
			
		   }catch (Exception e) {
			    System.out.println("*Ocurrio un error en actualizarFechaUltimaSync(): "+e); 
		   } finally {
			   DBConexion.cerrarConexionDB(con, pst,null, null);			    	
		   }
		     
		   System.out.println("*resultado: "+resultado); 
		   
		return resultado;	
	}
	
*/	
}
