package br.com.algoritmo.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static final SessionFactory sF = buildSessionFactory();
	
	public static SessionFactory buildSessionFactory() {
		try{
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			return cfg.buildSessionFactory();
			
			}catch (Throwable e){
				System.out.println("Erro ao criar session factory. \n Erro: " +e);
				e.printStackTrace();
				throw new ExceptionInInitializerError(e);
			}
			}
			public static SessionFactory getSf(){
				return sF;
			}
}
