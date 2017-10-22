package com.redrigsoft.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConexion {
	
	@SuppressWarnings("finally")
	public static Connection crearConexionDB(){
		Connection con = null;
		
		try {
			
			Class.forName(Credenciales.dbClass);
			con = DriverManager.getConnection(Credenciales.dbUrl, Credenciales.dbUser, Credenciales.dbPwd);
		
		} catch (Exception e) {
			System.out.println("*Error en crearConexionDB(): "+e);
			
		} finally {
		   return con;
		}
	}
	
	public static void cerrarConexionDB(Connection con, PreparedStatement pst, CallableStatement cst, ResultSet rs){
		try {
			
			if (con!= null) {con.close();}
			
			if (pst!=null) {pst.close();}
			
			if (cst!=null) {cst.close();}
			
			if (rs!=null) {rs.close();}
			
		} catch (Exception e) {
			System.out.println("*Error en cerrarConexionDB(): "+e);
		}
	}

}
