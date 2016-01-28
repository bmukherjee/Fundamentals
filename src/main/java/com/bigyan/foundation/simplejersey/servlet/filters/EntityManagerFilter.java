package com.bigyan.foundation.simplejersey.servlet.filters;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.google.inject.Provider;
import com.google.inject.Singleton;

/**
 * This Filter is used to Wrap each request in a Transaction, which is safely committed or rollbacked at the end.
 *
 */
@Singleton
public class EntityManagerFilter implements Filter {
	
	@Inject
	Provider<EntityManager> entityManagerProvider;
	
	
	static Logger logger = Logger.getLogger(EntityManagerFilter.class.getName());

	@Override
	public void doFilter( ServletRequest req, ServletResponse res,
			FilterChain chain ) throws IOException, ServletException {
		
		EntityTransaction tr = null;
		EntityManager entityManager = entityManagerProvider.get();

		try{
			if( entityManager == null || !entityManager.isOpen() ) 
				logger.warning("EntityManager is null or Closed");
			else {
				tr = entityManager.getTransaction();
				tr.begin();
			}
			
			chain.doFilter( req, res );
			
			if( tr != null && tr.isActive() )
				tr.commit();
			
		} finally {
			if( entityManager == null || !entityManager.isOpen() ) 
				logger.warning( "EntityManager is null or Closed" );
			else {
				if( tr != null && tr.isActive() )
					tr.rollback();
				entityManager.close();
			}
		}

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}
	
	@Override
	public void destroy() {
	}

}
