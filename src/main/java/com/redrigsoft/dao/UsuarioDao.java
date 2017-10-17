package com.redrigsoft.dao;


public class UsuarioDao {
	 
	public static boolean validarUsuarioContrasena(String usuario, String contrasena){
		   System.out.println("-> validarUsuarioContrasena() con user:"+usuario+" y pass:"+contrasena);
		 
		   boolean resultado     = false;
		   /*Connection        con = null;
		   PreparedStatement pst = null;
		   ResultSet         rs  = null;*/
		   
		   if(usuario!=null && !usuario.isEmpty() && contrasena!=null && !contrasena.isEmpty()){
			   
			   if(usuario.equals("rigman") && contrasena.equals("123456")) {
				   resultado=true;
			   }
			   
			   
			   /*try {
				   
				    con = DBConexion.crearConexionDB();
				    
				    pst = con.prepareStatement("SELECT *FROM usuario WHERE user=?  and  pass=?");
					pst.setString(1, usuario);
					pst.setString(2, contrasena);
					
					rs = pst.executeQuery();
					
					while(rs.next()){
					   resultado=true;
					}
				
			    } catch (Exception e) {
				    System.out.println("*Ocurrió un error en validarUsuarioContrasena(): "+e); 
			    } finally {
			    	DBConexion.cerrarConexionDB(con, pst, null, rs);		    	
				}*/			   
		   } 
		   
		   System.out.println("*Resultado: "+resultado);
		   return resultado;		
	}
	
	/*
	public static BUsuario validarUsuarioContrasenia(String usuario, String contrasena){
		   System.out.println("-> DAO validarUsuarioContrasenia() con user:"+usuario+" y pass:"+contrasena);
		 
		   BUsuario bUsuario = null;
		   Connection        con = null;
		   PreparedStatement pst = null;
		   ResultSet         rs  = null;
		   
		   if(usuario!=null && !usuario.trim().equals("") && contrasena!=null && !contrasena.trim().equals("")){
			   try {
				   
				    con = DBConexion.crearConexionDB();
				    
				    pst = con.prepareStatement("SELECT *FROM usuario WHERE user=?  and  pass=?");
					pst.setString(1, usuario);
					pst.setString(2, contrasena);
					
					rs = pst.executeQuery();
					
					while(rs.next()){
						bUsuario = new BUsuario();
						bUsuario.setIdUsuario(rs.getInt("idUsuario"));  
						bUsuario.setNombre(rs.getString("nombre"));
						bUsuario.setApellidos(rs.getString("apellidos"));
						bUsuario.setEmail(rs.getString("email"));
						bUsuario.setCelular(rs.getString("celular"));
						bUsuario.setUser(rs.getString("user")); 
						
						Blob blobIcon = (Blob) rs.getBlob ("icon"); 
						if(blobIcon!=null){
						   bUsuario.setIcon(new String(blobIcon.getBytes(1, (int) blobIcon.length()), StandardCharsets.UTF_8)); 
						}else{
						   bUsuario.setIcon(""); 
						}
						
						bUsuario.setVisible(rs.getString("visible"));
						bUsuario.setClaveApi(rs.getString("claveApi"));
						bUsuario.setMac(rs.getString("mac"));
					}
				
			    } catch (Exception e) {
				    System.out.println("*Ocurrió un error en validarUsuarioContrasenia(): "+e); 
			    } finally {
			    	DBConexion.cerrarConexionDB(con, pst, null, rs);		    	
				}			   
		   } 
		   
		   return bUsuario;		
	}
	
	public static List<BUsuario> buscarUsuario(BUsuario inUsuario){
		   System.out.println("-> DAO buscarUsuario()");
		 
		   List<BUsuario> listaUsuariosResult = new ArrayList<BUsuario>();

		   BUsuario bUsuario = null;
		   Connection        con = null;
		   PreparedStatement pst = null;
		   ResultSet         rs  = null;
		   
		   if(inUsuario!=null && !inUsuario.getCelular().trim().equals("")){
			   try {
				   
				    con = DBConexion.crearConexionDB();
				    
				    pst = con.prepareStatement("SELECT *FROM usuario WHERE celular=?");
					pst.setString(1, inUsuario.getCelular());
					
					rs = pst.executeQuery();
					
					while(rs.next()){
						bUsuario = new BUsuario();
						bUsuario.setIdUsuario(rs.getInt("idUsuario"));  
						bUsuario.setNombre(rs.getString("nombre"));
						bUsuario.setApellidos(rs.getString("apellidos"));
						bUsuario.setEmail(rs.getString("email"));
						bUsuario.setCelular(rs.getString("celular"));
						bUsuario.setUser(rs.getString("user")); 
						bUsuario.setPass("");
						bUsuario.setVisible(rs.getString("visible"));
						bUsuario.setClaveApi("");
						bUsuario.setMac("");
						
						Blob blobIcon = (Blob) rs.getBlob ("icon"); 
						if(blobIcon!=null){
						   bUsuario.setIcon(new String(blobIcon.getBytes(1, (int) blobIcon.length()), StandardCharsets.UTF_8)); 
						}else{
						   bUsuario.setIcon(""); 
						}

						listaUsuariosResult.add(bUsuario);
					}
				
			    } catch (Exception e) {
				    System.out.println("*Ocurrió un error en buscarUsuario(): "+e); 
			    } finally {
			    	DBConexion.cerrarConexionDB(con, pst, null, rs);		    	
				}			   
		   } 
		   
		   return listaUsuariosResult;		
	}
	
	
	
	
	
	
	public static BUsuario obtenerDatosUsuarioPorUser(String user){
		
		 BUsuario usuario = null;
		
		 Connection        con = null;
		 PreparedStatement pst = null;
		 ResultSet         rs  = null;
		 
		 try {
			   
			    con = DBConexion.crearConexionDB();
			    
			    pst = con.prepareStatement("SELECT nombre, apellidos, claveApi, email FROM usuario WHERE user= ?");
				pst.setString(1, user);
				
				rs = pst.executeQuery();
				

				while (rs.next()) {	
					usuario = new BUsuario();
					usuario.setNombre(rs.getString("nombre"));
					usuario.setApellidos(rs.getString("apellidos"));
					usuario.setClaveApi(rs.getString("claveApi"));
					usuario.setEmail(rs.getString("email"));
				}

		    } catch (Exception e) {
			    System.out.println("*Ocurrió un error en obtenerDatosUsuarioPorUser(): "+e); 
		    } finally {
		    	DBConexion.cerrarConexionDB(con, pst, null, rs);		    	
			}
			
		return usuario;		
	}
	
	public static BUsuario obtenerDatosUsuarioPorClaveApi(String claveApi){
		
		 BUsuario usuario = null;
		
		 Connection        con = null;
		 PreparedStatement pst = null;
		 ResultSet         rs  = null;
		 
		 try {
			   
			    con = DBConexion.crearConexionDB();
			    
			    pst = con.prepareStatement("SELECT *FROM usuario WHERE claveApi= ?");
				pst.setString(1, claveApi);
				rs = pst.executeQuery();
				

				while (rs.next()) {	
					usuario = new BUsuario();
					usuario.setIdUsuario(rs.getInt("idUsuario"));
					usuario.setNombre(rs.getString("nombre"));
					usuario.setApellidos(rs.getString("apellidos"));
					usuario.setClaveApi(rs.getString("claveApi"));
					usuario.setEmail(rs.getString("email"));
					usuario.setCelular(rs.getString("celular"));
				}

		    } catch (Exception e) {
			    System.out.println("*Ocurrió un error en obtenerDatosUsuarioPorClaveApi(): "+e); 
		    } finally {
		    	DBConexion.cerrarConexionDB(con, pst, null, rs);		    	
			}
			
		return usuario;		
	}
	
	
	public static int autorizarUsuario(String claveApi){
		   int idUsuario=0;
		   
		   Connection        con = null;
		   PreparedStatement pst = null;
		   ResultSet         rs  = null;
			 
		   try {		   
				    con = DBConexion.crearConexionDB();
				    
				    pst = con.prepareStatement("SELECT idUsuario FROM usuario WHERE claveApi= ?");
					pst.setString(1, claveApi);
					
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
	
	
	public static int insertarUsuario(String nombre, String apellidos, String usuario, String contrasena, String claveApi, String email){
		   int resultado         = 0;
		   Connection        con = null;
		   PreparedStatement pst = null;
		   
		   try {
			   		con = DBConexion.crearConexionDB();
			   		
			   		pst = con.prepareStatement("INSERT INTO usuario (nombre, apellidos, user, pass, claveApi, email) VALUES (?,?,?,?,?,?)");
			   		pst.setString(1, nombre);
			   		pst.setString(2, apellidos);
			   		pst.setString(3, usuario);
			   		pst.setString(4, contrasena);
			   		pst.setString(5, claveApi);
			   		pst.setString(6, email);
			   		
			   		int records = pst.executeUpdate();
			   		
			   		if (records > 0) { // Num registros afectados
			   			resultado = 1;
					}
			
		   }catch(SQLException sqle){
				if(sqle.getErrorCode() == 1062){ //When UNIQUE KEY violation
					resultado = -1;
				}
		   }catch (Exception e) {
			    System.out.println("*Ocurrió un error en insertarUsuario(): "+e); 
		   } finally {
		    	DBConexion.cerrarConexionDB(con, pst, null, null);		    	
		   }
		   
		return resultado;	
	}
	
	public static ArrayList<String> insertarUsuario(BUsuario bUsuario){
		System.out.println("-> DAO insertarUsuario():");
		System.out.println(bUsuario.getIdUsuario());
		System.out.println(bUsuario.getNombre());
		System.out.println(bUsuario.getApellidos());
		System.out.println(bUsuario.getEmail());   		
		System.out.println(bUsuario.getCelular());
		System.out.println(bUsuario.getUser());
		System.out.println(bUsuario.getPass());
		//System.out.println(bUsuario.getIcon());
		System.out.println(bUsuario.getVisible());
		System.out.println(bUsuario.getClaveApi());
		System.out.println(bUsuario.getMac());
		
		   String codResul = "0"; 
		   String desResul = "";
		   ArrayList<String> resultado = new ArrayList<String>();
		   
		   Connection        con = null;
		   CallableStatement cst = null;
		   
		   try {
		   		con = DBConexion.crearConexionDB();
		   		
		   		cst = con.prepareCall("{call mantenimientoUsuario(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		   		cst.setInt(1, bUsuario.getIdUsuario());
		   		cst.setString(2, bUsuario.getNombre());
		   		cst.setString(3, bUsuario.getApellidos());
		   		cst.setString(4, bUsuario.getEmail());   		
		   		cst.setString(5, bUsuario.getCelular());
		   		cst.setString(6, bUsuario.getUser());
		   		cst.setString(7, bUsuario.getPass());
		   		
		   		byte[] byteData = bUsuario.getIcon().getBytes("UTF-8");//Better to specify encoding
		   		  Blob blobData = (Blob) con.createBlob();
		   		       blobData.setBytes(1, byteData);

		   		cst.setBlob  (8, blobData);
		   		cst.setString(9, bUsuario.getVisible());
		   		cst.setString(10, bUsuario.getClaveApi());
		   		cst.setString(11, bUsuario.getMac());
		   		cst.setString(12, "1"); //Opcion Insertar
		   		cst.registerOutParameter(13, java.sql.Types.VARCHAR); 
		   		cst.registerOutParameter(14, java.sql.Types.VARCHAR); 		   		
		   		cst.execute();
		   		
		   		codResul = cst.getString(13);
		   		desResul = cst.getString(14);
		   		
		   		System.out.println("*COD Resultado: "+codResul);
		   		System.out.println("*DES Resultado: "+desResul);
		   		
		   		resultado.add(codResul);
		   		resultado.add(desResul);
		   			
		   }catch (Exception e) {
			    System.out.println("*Ocurrió un error en insertarUsuario(): "+e); 
			    resultado.add("0");
		   		resultado.add("Error al insertar Usuario");
		   } finally {		    
		    	DBConexion.cerrarConexionDB(con, null,cst, null);	
		   }
		   
		return resultado;	
	}
	
	
	public static ArrayList<String> actualizarUsuario(BUsuario bUsuario){
		System.out.println("-> DAO actualizarUsuario():"); 
		
		   String codResul = "0"; 
		   String desResul = "";
		   ArrayList<String> resultado = new ArrayList<String>();
		   
		   Connection        con = null;
		   CallableStatement cst = null;
		   
		   try {
		   		con = DBConexion.crearConexionDB();
		   		
		   		cst = con.prepareCall("{call mantenimientoUsuario(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		   		cst.setInt(1, bUsuario.getIdUsuario());
		   		cst.setString(2, bUsuario.getNombre());
		   		cst.setString(3, bUsuario.getApellidos());
		   		cst.setString(4, bUsuario.getEmail());   		
		   		cst.setString(5, bUsuario.getCelular());
		   		cst.setString(6, bUsuario.getUser());
		   		cst.setString(7, bUsuario.getPass());
		   		
		   		byte[] byteData = bUsuario.getIcon().getBytes("UTF-8");//Better to specify encoding
		   		  Blob blobData = (Blob) con.createBlob();
		   		       blobData.setBytes(1, byteData);

		   		cst.setBlob  (8, blobData);
		   		cst.setString(9, bUsuario.getVisible());
		   		cst.setString(10, bUsuario.getClaveApi());
		   		cst.setString(11, bUsuario.getMac());
		   		cst.setString(12, "2"); //Opcion Actualizar
		   		cst.registerOutParameter(13, java.sql.Types.VARCHAR); 
		   		cst.registerOutParameter(14, java.sql.Types.VARCHAR); 		   		
		   		cst.execute(); 
		   		
		   		codResul = cst.getString(13);
		   		desResul = cst.getString(14);
		   		
		   		System.out.println("*COD Resultado: "+codResul);
		   		System.out.println("*DES Resultado: "+desResul);
		   		
		   		resultado.add(codResul);
		   		resultado.add(desResul);
		   			
		   }catch (Exception e) {
			    System.out.println("*Ocurrió un error en actualizarUsuario(): "+e); 
			    resultado.add("0");
		   		resultado.add("Error al actualizar Usuario");
		   } finally {		    
		    	DBConexion.cerrarConexionDB(con, null,cst, null);	
		   }
		   
		return resultado;	
	}
	*/
}
