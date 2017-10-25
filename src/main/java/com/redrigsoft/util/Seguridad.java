package com.redrigsoft.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Seguridad {
	
	
	public static String generarClaveApi(){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("*Error al obtener instancia de MessageDigest: "+e);
		}
		
		StringBuffer hexString = new StringBuffer();
		byte[] data = md.digest(textoAleatorio(10).getBytes());
		for (int i=0; i<data.length; i++){
			hexString.append(Integer.toHexString( (data[i]>> 4) & 0x0F ) );
			hexString.append(Integer.toHexString( data[i] & 0x0F ) );
		}
		return hexString.toString();
	}
	
	public static String textoAleatorio(int numLetras) {
		   Random random = new Random();
		   StringBuilder sb = new StringBuilder(numLetras);
		   for (int i = 0; i <numLetras; i++)
		      sb.append((char)(random.nextInt(26) + 65));
		   return sb.toString();            
	}
	
	 
	/*public static void main(String args[]){
		System.out.println("generarClaveApi: "+generarClaveApi());
	}*/

}
