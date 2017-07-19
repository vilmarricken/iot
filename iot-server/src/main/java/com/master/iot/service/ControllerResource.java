package com.master.iot.service;

import java.io.Serializable;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.master.iot.IOTController;
import com.master.iot.IOTManager;

@Path("controllers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ControllerResource implements Serializable {

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<IOTController> list() {
		IOTManager instance = IOTManager.getInstance();
		return instance.list();
	}
	/*
	 * @GET public Collection<IOTController> list() { return
	 * IOTManager.getInstance().list(); }
	 * 
	 * @PUT public Product edit(@NotNull Product product) { return
	 * service.edit(product); }
	 * 
	 * @GET public List<Product> list(@QueryParam("search") String search) {
	 * return service.list(search); }
	 * 
	 * @GET
	 * 
	 * @Path("{id}") public Product find(@NotNull @PathParam("id") Long id)
	 * throws AppException { return service.find(id); }
	 * 
	 * @DELETE
	 * 
	 * @Path("{id}") public void delete(@NotNull @PathParam("id") Long id)
	 * throws AppException { service.delete(id); }
	 */

}
