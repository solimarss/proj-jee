package br.com.solimar.service.rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class CarWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<Car> cars;

	
	public List<Car> getCars() {
		if(cars == null){
			cars = new ArrayList<Car>();
		}
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
	
	

}
