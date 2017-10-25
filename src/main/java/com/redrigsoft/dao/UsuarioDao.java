package com.redrigsoft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.redrigsoft.bean.BUsuario;
import com.redrigsoft.db.DBConexion;

public class UsuarioDao {
	
	public static BUsuario validarUsuarioContrasenia(int idCuenta, String user, String pass){
		   System.out.println("-> DAO validarUsuarioContrasenia() con user:"+user+" y pass:"+pass);
		 
		   BUsuario bUsuario = null;
		   Connection        con = null;
		   PreparedStatement pst = null;
		   ResultSet         rs  = null;
		   
		   if(user!=null && !user.trim().isEmpty() && pass!=null && !pass.trim().isEmpty()){
			   try {
				   
				    con = DBConexion.crearConexionDB();
				    
				    pst = con.prepareStatement("SELECT *FROM cuenta_usuario cu INNER JOIN  usuario us ON  us.idUsuario= cu.idUsuario  WHERE cu.idCuenta=? AND us.user=? AND us.password=?");
					pst.setInt   (1, idCuenta);
				    pst.setString(2, user);
					pst.setString(3, pass);
					
					rs = pst.executeQuery();
					
					while(rs.next()){
						bUsuario = new BUsuario();
						bUsuario.setIdUsuario(rs.getInt("idUsuario"));  
						bUsuario.setNombre(rs.getString("nombre"));
					}
				
			    } catch (Exception e) {
				    System.out.println("*Ocurrió un error en validarUsuarioContrasenia(): "+e); 
			    } finally {
			    	DBConexion.cerrarConexionDB(con, pst, null, rs);		    	
				}			   
		   } 
		   
		   return bUsuario;		
	}
	
	
	
	public static int autorizarUsuario(int idCuenta, String claveApi){
		   int idUsuario=0;
		   
		   Connection        con = null;
		   PreparedStatement pst = null;
		   ResultSet         rs  = null;
			 
		   try {		   
				    con = DBConexion.crearConexionDB();
				    pst = con.prepareStatement("SELECT us.idUsuario FROM cuenta_usuario cu INNER JOIN  usuario us ON  us.idUsuario= cu.idUsuario  WHERE cu.idCuenta=? AND cu.claveApi= ?");
					pst.setInt   (1, idCuenta);
				    pst.setString(2, claveApi); 
				    
					rs = pst.executeQuery();			

					while (rs.next()) {	
						idUsuario = rs.getInt("idUsuario"); 
					}

			  } catch (Exception e) {
				    System.out.println("*Ocurrió un error en autorizarUsuario(): "+e); 
			  } finally {
			    	DBConexion.cerrarConexionDB(con, pst, null, rs);		    	
		      }
		   	   
		   return idUsuario;
	}
	
}
