package com.clients;

import java.util.Arrays;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import metier.entites.Compte;
import metier.sessions.BanqueRemote;

public class RemoteClient {

	public static void main(String[] args) {
		try {
			BanqueRemote banque = getBanqueRemote();
			Compte[] comptes = banque.consulterComptes();
			
			if (comptes.length > 0) {
				System.out.println(comptes.length);
				Arrays.stream(comptes).forEach(c -> System.out.println(c.getCode()));
			}
		} catch (NamingException e) {
			System.out.println("Can't resolve BanqueRemote!");
			System.out.println(e);
		}
	}
	
	private static BanqueRemote getBanqueRemote() throws NamingException {
		Context context = getContext();
		String appName = "";
		String moduleName = "Banque.EJB";
		String distinctName = "Banque";
		String viewClassName = BanqueRemote.class.getName();
		
		StringBuilder banqueRemoteEjbName = new StringBuilder();
		banqueRemoteEjbName.append("ejb:").append(appName).append("/").append(moduleName).append("/")
							.append(distinctName).append("!").append(viewClassName);
		
		return (BanqueRemote)context.lookup(banqueRemoteEjbName.toString());
	}
	
	private static Context getContext() throws NamingException {
		Properties properties = new Properties();
		properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.HttpNamingContextFactory");
		properties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		properties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		return new InitialContext(properties);
	}

}
