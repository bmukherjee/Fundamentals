package com.bigyan.foundation.simplejersey.servlet.modules;

import java.util.HashMap;
import java.util.Map;

import com.bigyan.foundation.simplejersey.servlet.config.MyInitConstants;
import com.bigyan.foundation.simplejersey.servlet.filters.EntityManagerFilter;
//import com.google.inject.persist.PersistFilter;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class MyServletModule extends ServletModule {
	
		private static final String propertyPackages = MyInitConstants.JERSEY_RESOURCE_PACKAGES;

		@Override
		protected void configureServlets() {
			super.configureServlets();

			// Install Configuration Properties
			install(new MyPropertiesModule());

			// Initialize Persistence JPA  
			install(new MyJpaPersistenceModule());
			
			
			// Initialize Apache Shiro if present
			// install(new YourShiroModule(getServletContext()));

			//Add Jersey Resource Classes to the context
			Map<String, String> params = new HashMap<String, String>();
			params.put(PackagesResourceConfig.PROPERTY_PACKAGES,
					propertyPackages);
			params.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
			
			
			// Add a PersistFilter
			// This allows EntityManagers to be injectible in Session Scope(open-session-in-view)
			 filter("/*").through(EntityManagerFilter.class);
			 
			// if you had a ShiroWebModule installed above you would need to
			// add this GuiceShiroFilter also.
			// filter("/*").through(GuiceShiroFilter.class);
			serve("/api/*").with(GuiceContainer.class, params);

		}
	}


