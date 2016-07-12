package br.com.solimar.service.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/car")
public class CarService {

	@Inject
	private CarRepository repository;

	@GET
	@Path("/listall")
	// @Produces("text/plain")
	// @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON })
	public Response gelist() {
		System.out.println("list");

		CarWrapper carWrapper = new CarWrapper();
		carWrapper.setCars(repository.list());

		return Response.status(200).entity(carWrapper).build();

	}

	@GET
	@Path("/list")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response gelistAll() {
		System.out.println("listAll");

		List<Car> list = repository.list();

		return Response.status(200).entity(list).build();

	}

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getUser(@PathParam("id") Integer id) {
		// curl -i -H "Accept: application/json"
		// http://localhost:8080/proj-jee/service/car/1

		System.out.println("find: " + id);

		Car resp = repository.find(id);

		return Response.status(200).entity(resp).build();

	}

	@POST
	@Path("/")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response insert(Car car) throws URISyntaxException {

		// curl -i -H "Content-type: application/json" -X POST -d
		// '{"marca":"Honda"}' http://localhost:8080/proj-jee/service/car/insert

		Car c = repository.insert(car);

		return Response.created(new URI("/" + car.getId())).status(200)
				.entity(c).build();
	}

	@PUT
	@Path("/")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response update(Car car) throws URISyntaxException {

		Car c = repository.update(car);

		return Response.created(new URI("/" + car.getId())).status(200)
				.entity(c).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response delete(@PathParam("id") Integer id) throws Exception {

		repository.delete(id);

		return Response.status(200).build();
	}

}
