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
import com.master.iot.controller.HouseControllerController;
import com.master.iot.entity.HouseController;
import com.master.iot.entity.HouseControllerType;

@Path("house")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HouseResource implements Serializable {

	@GET
	@Path("controllers/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<HouseController> controllers(@PathParam("id") Long id) {
		IOTManager instance = IOTManager.getInstance();
		IOTController controller = instance.get(id);
		IOTCompenent[] compenents = controller.getComponents();
		List<Controller> controllers = new ArrayList<>();
		for (IOTCompenent iotCompenent : compenents) {
			controllers.add(new Controller(String.valueOf(iotCompenent.getIndex()), iotCompenent.getName(), iotCompenent.isOn(), HouseControllerType.CONTROLLER));
		}
		return controllers;
	}

	@GET
	@Path("controllers")
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<HouseController> list() {
		return new HouseControllerController().controllers();
	}

	@GET
	@Path("controllers/off/{id}/{component}")
	@Produces({ MediaType.APPLICATION_JSON })
	public void off(@PathParam("id") String id, @PathParam("component") String component) throws Exception {
		System.out.println("On - " + id + " - " + component);
		IOTManager instance = IOTManager.getInstance();
		IOTController controller = instance.get(id);
		controller.off((byte) Integer.valueOf(component).intValue());
	}

	@GET
	@Path("controllers/on/{id}/{component}")
	@Produces({ MediaType.APPLICATION_JSON })
	public void on(@PathParam("id") String id, @PathParam("component") String component) throws Exception {
		System.out.println("On - " + id + " - " + component);
		IOTManager instance = IOTManager.getInstance();
		IOTController controller = instance.get(id);
		controller.on(Integer.valueOf(component));
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
