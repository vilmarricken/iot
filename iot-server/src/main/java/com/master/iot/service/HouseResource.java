package com.master.iot.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.master.iot.IOTCompenent;
import com.master.iot.IOTController;
import com.master.iot.IOTManager;
import com.master.iot.objects.Controller;
import com.master.iot.objects.Type;

@Path("house")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HouseResource implements Serializable {

	@GET
	@Path("controllers")
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<Controller> list() {
		IOTManager instance = IOTManager.getInstance();
		Collection<IOTController> controllers = instance.list();
		Collection<Controller> regions = new ArrayList<>();
		for (IOTController controller : controllers) {
			regions.add(new Controller(controller.getId(), controller.getName(), false, Type.REGION));
		}
		return regions;
	}

	@GET
	@Path("controllers/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<Controller> controllers(@PathParam("id") String id) {
		IOTManager instance = IOTManager.getInstance();
		IOTController controller = instance.get(id);
		IOTCompenent[] compenents = controller.getComponents();
		List<Controller> controllers = new ArrayList<>();
		for (IOTCompenent iotCompenent : compenents) {
			controllers.add(new Controller(String.valueOf(iotCompenent.getIndex()), iotCompenent.getName(),
					iotCompenent.isOn(), Type.CONTROLLER));
		}
		return controllers;
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
