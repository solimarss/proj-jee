package br.com.solimar.service.rest;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ClienteRest implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String SERVER_URI = "http://localhost:8080/proj-jee/service";

	private static final String ENTRY_POINT = "/car";

	public List<Car> listAll() {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(SERVER_URI + ENTRY_POINT + "/listall");

		Response response = null;
		try {

			response = target.request(MediaType.APPLICATION_JSON).get(
					Response.class);

		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
			e.printStackTrace();
		}

		CarWrapper carWrapper = response.readEntity(CarWrapper.class);

		return carWrapper.getCars();

	}

	public List<Car> list() {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(SERVER_URI + ENTRY_POINT + "/list");

		Response response = null;
		try {

			response = target.request(MediaType.APPLICATION_JSON).get();

		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
			e.printStackTrace();
		}

		System.out.println("Status: " + response.getStatus());

		List<Car> lista = response.readEntity(new GenericType<List<Car>>() {
		});

		return lista;

	}

	public Car find(int id) {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(SERVER_URI + ENTRY_POINT + "/" + id);

		Response response = null;
		try {

			response = target.request(MediaType.APPLICATION_JSON).get(
					Response.class);

		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
			e.printStackTrace();
		}

		System.out.println("Status: " + response.getStatus());

		return response.readEntity(Car.class);

	}

	public int delete(int id) {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(SERVER_URI + ENTRY_POINT + "/" + id);

		Response response = null;
		try {

			response = target.request(MediaType.APPLICATION_JSON).delete(Response.class);

		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
			e.printStackTrace();
		}

		System.out.println("Status: " + response.getStatus());

		return response.getStatus();

	}

	
	public Car insert(Car car) {

		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(SERVER_URI + ENTRY_POINT + "/");

		Response response = null;
		try {

			response = target.request(MediaType.APPLICATION_JSON)
					.buildPost(Entity.json(car)).invoke();
			// response =
			// target.request().accept(MediaType.APPLICATION_JSON).post(Entity.entity(car,
			// MediaType.APPLICATION_JSON), Response.class);

		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
			e.printStackTrace();
		}

		System.out.println("Status: " + response.getStatus());

		return response.readEntity(Car.class);

	}
	
	
	public Car update(Car car) {


		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(SERVER_URI + ENTRY_POINT + "/");

		Response response = null;
		try {

			response = target.request(MediaType.APPLICATION_JSON)
					.buildPut(Entity.json(car)).invoke();


		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
			e.printStackTrace();
		}

		System.out.println("Status: " + response.getStatus());

		return response.readEntity(Car.class);

	}

	public static void main(String[] args) {

		ClienteRest cliente = new ClienteRest();


		System.out.println("******** Insert **********************");
		Car respInsert = cliente.insert(new Car("Ford", "Fiesta"));
		System.out.println("Resposta, id: " + respInsert.getId() + " - "
				+ respInsert.getModelo());

		System.out.println("******** List **********************");
		List<Car> cars = cliente.list();
		for (Car c : cars) {
			System.out
					.println("Resposta: " + c.getId() + " - " + c.getModelo());
		}

		System.out.println("******** Update **********************");
		Car upCar = cars.get(0);
		upCar.setMarca("Chevrolet");
		upCar.setModelo("Classic");
		Car respUpdate = cliente.update(upCar);
		System.out.println("Resposta, id: " + respUpdate.getId() + " - "
				+ respUpdate.getModelo());

		System.out.println("******** Find **********************");
		Car resp = cliente.find(cars.get(0).getId());
		System.out.println("Resposta, id: " + resp.getId() + " - "
				+ resp.getModelo());
		
		
		System.out.println("******** Delete **********************");
		Car carDelete = cars.get(1);
		cliente.delete(carDelete.getId());
		System.out.println("Resposta, id: " + carDelete.getId() + " - "
				+ carDelete.getModelo());
		

		
		System.out.println("******** List **********************");
		List<Car> cars2 = cliente.list();
		for (Car c : cars2) {
			System.out
					.println("Resposta: " + c.getId() + " - " + c.getModelo());
		}
	}

}
