package com.redrigsoft.util;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


public class FormatoJson {
	
	public static String respuestaOk(String tag, boolean status, String msg){
		JSONObject obj = new JSONObject();
		try {
			obj.put("tag", tag);
			obj.put("status", new Boolean(status));
			obj.put("msg", msg);
		} catch (JSONException e) {
			System.out.println("Error en respuestaOk()");  
		}
		return obj.toString();
	}
	
	public static String respuestaJson(String tag, String status, String msg){
		JSONObject obj = new JSONObject();
		try {
			obj.put("tag", tag);
			obj.put("status", status);
			obj.put("msg", msg);
		} catch (JSONException e) {
			System.out.println("Error en respuestaJson()");  
		}
		return obj.toString();
	}
	
	
	public static String respuestaOk(String tag, boolean status, JSONArray jArray){
		
		JSONObject obj = new JSONObject();
		try {
			obj.put("tag", tag);
			obj.put("status", new Boolean(status));
			obj.put("contenido", jArray);
		} catch (JSONException e) { 
			System.out.println("Error en respuestaOk(): "+e);  
		} 
		return obj.toString(); 
	}
	
    public static String respuestaJson(String tag, boolean status, String jsonObjet){
		
		JSONObject obj = new JSONObject();
		try {
			obj.put("tag", tag);
			obj.put("status", status);
			obj.put("objeto", jsonObjet);
		} catch (JSONException e) { 
			System.out.println("Error en respuestaJson(): "+e);  
		} 
		return obj.toString(); 
	}
	
/*	
   public static String respuestaOk(List<BhRecurso> listaRecurso){
		
		JSONObject obj = new JSONObject();
		try {
			obj.put("estado", 100);
			obj.put("mensaje", "Sincronizacion completada");
			
			for (BhRecurso lista : listaRecurso) { 
				 obj.put(lista.getNombreTabla(), lista.getjArray());
			}

		} catch (JSONException e) { 
			System.out.println("Error en respuestaOk(): "+e);  
		} 
		return obj.toString(); 
	}
   
   public static String respuestaJson(String tag, String status, String msg, List<BhRecurso> listaRecurso){
		JSONObject obj = new JSONObject();
		try {
			    obj.put("tag", tag);
			    obj.put("status", status);
			    obj.put("msg", msg);
			
		  for (BhRecurso lista : listaRecurso) { 
				obj.put(lista.getNombreTabla(), lista.getjArray());
	      }
			
		} catch (JSONException e) {
			System.out.println("Error en respuestaJson()");  
		}
		return obj.toString();
	}
*/
	
	public static String respuestaKo(String tag, boolean status, String err_msg) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("tag", tag);
			obj.put("status", new Boolean(status));
			obj.put("error_msg", err_msg);
		} catch (JSONException e) {
			System.out.println("Error en respuestaKo()");  
		}
		return obj.toString(); 
	}
	
}
