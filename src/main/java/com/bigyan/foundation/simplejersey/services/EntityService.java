package com.bigyan.foundation.simplejersey.services;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.google.inject.Inject;

public class EntityService<T> {
	@Inject EntityManager entityManager;
	final Class<T> typeClass;
	
    public EntityService(Class<T> c) {
        this.typeClass = c;
    }
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public T get(@QueryParam("id") String id){	
        return entityManager.find(typeClass, id); 
	}
	
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public T create(T object) {
		return entityManager.merge(object);
		
	}
	
	@DELETE
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public T delete(@QueryParam("id") String id){	
        T res = get(id);
        if(res!=null)
        	entityManager.remove(res); 
        return res;
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<T> list() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(typeClass);
        Root<T> rootEntry = cq.from(typeClass);
        CriteriaQuery<T> all = cq.select(rootEntry); 
        return entityManager.createQuery(all).getResultList();
	}
	
	@GET
	@Path("/executesqlquery")
	@Produces(MediaType.APPLICATION_JSON)
	public List<T> executeQuery(String query) {
		return entityManager.createQuery(query, typeClass).getResultList();
	}

}
