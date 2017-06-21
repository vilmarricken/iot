package com.master.iot.server;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

	@POST
	public void create(String product) {
	}

	@PUT
	public void edit(String product) {
	}

	@GET
	public void list(@QueryParam("search") String search) {
	}

	@GET
	@Path("{id}")
	public void find(@PathParam("id") Long id) {
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Long id)  {
	}

}