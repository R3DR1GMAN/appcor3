package com.redrigsoft.dao;

import java.sql.Blob;
import java.sql.CallableStatement;
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
import com.redrigsoft.util.Seguridad;

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
			    pst = con.prepareStatement("SELECT *FROM cuenta_usuario cu INNER JOIN  usuario us ON  us.idUsuario= cu.idUsuario WHERE  cu.idCuenta=? AND cu.cod_dispositivo=?");
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
	

	public static ArrayList<String> insertarCuentaEssalud(int idCuenta, BCuentaEssalud bCuenta){
		   System.out.println("-> DAO insertarCuentaEssalud():");

		   ArrayList<String> resultadoArray = new ArrayList<String>();		   
		   Connection        con = null;
		   CallableStatement cst = null;

		   if(bCuenta!=null && !bCuenta.getUsuario().isEmpty() && !bCuenta.getEmail().isEmpty()){
			   try {
				    con = DBConexion.crearConexionDB();				    
				    cst = con.prepareCall("{call mantenimientoUsuario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			   		cst.setInt   (1, 0);
			   		cst.setString(2, bCuenta.getUsuario());
			   		cst.setString(3, "");
			   		cst.setString(4, "");   		
			   		cst.setString(5, "");
			   		cst.setString(6, "");
			   		cst.setString(7, "");
			   		cst.setString(8, "");
			   		cst.setString(9, "");
			   		cst.setString(10,bCuenta.getEmail());		   		
			   		cst.setString(11,"");
			   		
			   		cst.setInt   (12, 3); //Cuenta EsSalud
			   		cst.setInt   (13, 0);
			   		cst.setString(14,Seguridad.generarClaveApi());
			   		cst.setString(15,bCuenta.getCodDispositivo());
			   		cst.setString(16,bCuenta.getCodToken());
			   		cst.setString(17,"");
			   		cst.setBlob  (18,(Blob) con.createBlob()); 
			   		cst.setString(19,""); 
			   		cst.setString(20,"1");
			   		cst.setDate  (21, null);
			   		cst.setDate  (22, new java.sql.Date(0)); 
			   		cst.setDate  (23, null);
			   		cst.setString(24,"I");
			   		cst.registerOutParameter(25, java.sql.Types.VARCHAR); 
			   		cst.registerOutParameter(26, java.sql.Types.VARCHAR); 		   		
			   		cst.execute();
			   		
			   		resultadoArray.add(cst.getString(25));
			   		resultadoArray.add(cst.getString(26));
			
				   }catch (Exception e) {
					    System.out.println("*Ocurrió un error en insertarCuentaEssalud(): "+e); 
					    resultadoArray.add("1");
					    resultadoArray.add("Error al insertar Usuario");
				   } finally {		    
				    	DBConexion.cerrarConexionDB(con, null,cst, null);	
				   }
		   }
		   
		return resultadoArray;
	}
	
	
	public static int actualizarCuentaEssalud(int idCuenta, int idUsuario, BCuentaEssalud bCuentaEssalud){ 
		System.out.println("-> DAO actualizarCuentaEssalud()::: idDispositivo:"+bCuentaEssalud.getCodDispositivo()); 
		   int resultado         = 0;
		   Connection        con = null;
		   PreparedStatement pst = null;

		   try {
			   		con = DBConexion.crearConexionDB();
			   		pst = con.prepareStatement("UPDATE cuenta_usuario SET cod_token=? WHERE idCuenta=? AND idUsuario=? AND idDispositivo=?");
	   		 
			   		pst.setString(1, bCuentaEssalud.getCodToken());
			   		pst.setInt   (2, idCuenta);
			   		pst.setInt   (3, idUsuario);
			   		pst.setString(4, bCuentaEssalud.getCodDispositivo());

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
	
	
/*	
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
