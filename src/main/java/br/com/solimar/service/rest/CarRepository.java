package br.com.solimar.service.rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;

@SuppressWarnings("serial")
@ApplicationScoped
public class CarRepository implements Serializable {

	private List<Car> lista;

	public CarRepository() {
		lista = new ArrayList<Car>();

		insert(new Car("Volkswagem", "Gol"));
		insert(new Car("Honda", "Fit"));
		insert(new Car("Fiat", "Palio"));
		insert(new Car("Fiat", "Uno"));
		insert(new Car("Honda", "City"));
		insert(new Car("Toyota", "Etios"));
		insert(new Car("Toyota", "Corolla"));
		insert(new Car("Volkswagem", "Up"));
		insert(new Car("Volkswagem", "Fox"));
		insert(new Car("Nissan", "Frontier"));
	}

	public Car insert(Car c) {
		Random gerador = new Random();
		c.setId(gerador.nextInt(10000));
		lista.add(c);
		return c;
	}

	public Car update(Car c) {
		lista.remove(c);
		lista.add(c);
		return c;
	}

	public void update(Integer id) {
		Car c = new Car(id);
		lista.remove(c);
	}

	public List<Car> list() {
		return lista;
	}

	public Car find(Integer id) {

		Car c = new Car();
		for (Car car : lista) {
			System.out.println("id: " + car.getId());
			if (car.getId().equals(id)) {
				c = car;
			}
		}
		return c;
	}

	public void delete(Integer id) throws Exception {

		Car carRemove = null;
		
		for (Car car : lista) {
			System.out.println("id: " + car.getId());
			if (car.getId().equals(id)) {
				carRemove = car;
			}
		}
		
		if(carRemove == null){
			throw new Exception("Registro não existe");
		}
		lista.remove(carRemove);
		
	}

}
