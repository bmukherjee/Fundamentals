package com.bigyan.foundation.simplejersey.servlet.config;

import com.bigyan.foundation.simplejersey.servlet.modules.MyServletModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * This class goes mapped in web.xml and is used to inject Google Guice's
 * Injector into the Web Application Context.
 * 
 */
public class MyServletContextListenerConfig extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new MyServletModule() );
	}

}
