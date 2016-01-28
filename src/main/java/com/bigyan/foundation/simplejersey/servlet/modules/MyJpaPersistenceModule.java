package com.bigyan.foundation.simplejersey.servlet.modules;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.servlet.RequestScoped;

public class MyJpaPersistenceModule extends AbstractModule{
	
	
	// This imposes the scopes for EntityManagerFactory and EntityManager.
	// Injecting EntityManagerFactory will give a singleton emf created at application startup
	// Injecting EntityManager will give a Request-scoped EntityManager. Doing this outside of a Request-scope will throw exception. To fix this, inject Provider<EntityManager> instead.	
	
	@Provides @Singleton
	public EntityManagerFactory provideEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("com.bigyan.foundation.persistenceUnit");
	}
	
	@Provides @RequestScoped
	public EntityManager provideEntityManager( EntityManagerFactory emf) {
	    return emf.createEntityManager();
	}
	
	@Override
	protected void configure() {
		
	}
	

}
