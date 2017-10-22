package com.redrigsoft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.redrigsoft.bean.BUsuario;
import com.redrigsoft.db.DBConexion;

public class UsuarioDao {
	 
	
	public static BUsuario validarUsuarioContrasenia(String user, String pass){
		   System.out.println("-> DAO validarUsuarioContrasenia() con user:"+user+" y pass:"+pass);
		 
		   BUsuario bUsuario = null;
		   Connection        con = null;
		   PreparedStatement pst = null;
		   ResultSet         rs  = null;
		   
		   if(user!=null && !user.trim().isEmpty() && pass!=null && !pass.trim().isEmpty()){
			   try {
				   
				    con = DBConexion.crearConexionDB();
				    
				    pst = con.prepareStatement("SELECT *FROM usuario WHERE user=?  and  password=?");
					pst.setString(1, user);
					pst.setString(2, pass);
					
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
	
}
