package com.bigyan.foundation.simplejersey.servlet.modules;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import com.bigyan.foundation.simplejersey.servlet.config.AppProperties;
import com.bigyan.foundation.simplejersey.servlet.config.MyInitConstants;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

/**
 * This Module is used to load the application properties from a java properties file
 * Other Modules that depend on application properties must be installed after installing this module.
 */
public class MyPropertiesModule extends AbstractModule {

	static Logger logger = Logger.getLogger(MyPropertiesModule.class.getName());


		@Override
		protected void configure() {
			Properties bootstrapProperties = new Properties();
			try {
				InputStream is = getClass()
						.getResourceAsStream(
								"/" 
						+ MyInitConstants.APPLICATION_PROPERTIES_FILE);
				if(is==null)
					throw new IOException("Can't Open Properties File");
				bootstrapProperties.load(is);
				
				Names.bindProperties(binder(), bootstrapProperties);
				
				bind(Properties.class).annotatedWith(AppProperties.class).toInstance(bootstrapProperties);
			}  catch (IOException e) {
				logger.warning("I/O Exception during loading configuration properties");
			}
		}


}
