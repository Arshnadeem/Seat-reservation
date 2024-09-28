package com.arsh.helper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
//this will provide connection to the database
public class FactoryProvider {
	public static SessionFactory factory;

//	getting factory
	public static SessionFactory getFactory() {
		if(factory == null) {
			factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		}
		return factory;
	}
	
	public void closeFactory() {
		if(factory.isOpen()) {
			factory.close();
		}
	}
}
