package pl.jcommerce.moonshine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.jcommerce.moonshine.dao.ThermometerRepository;
import pl.jcommerce.moonshine.model.Thermometer;
import pl.jcommerce.moonshine.model.TwiAddress;
import pl.jcommerce.moonshine.model.TwiFacade;

@Service
public class ThermometerService {

	@Autowired
	private ThermometerRepository repository;

	@Autowired
	private TwiFacade facade;

	private Thermometer choosen = new Thermometer();

	public Thermometer save(Thermometer thermometer) {
		facade.attach(thermometer);
		return repository.save(thermometer);
	}
	
	public void delete(Long id) {
		repository.delete(id);
	}
	public Thermometer findById(Long id) {
		return repository.findById(id);
	}
	
	public void setChoosenThermometer(Long id) {
		Thermometer thermometer = repository.findById(id);
		facade.attach(thermometer);
	}
	public Thermometer getChoosenTermometer() {
		facade.attach(choosen);
		return choosen;
	}

	public Iterable<Thermometer> getThermometers() {
		Iterable<Thermometer> thermometers = repository.findAll();
		for (Thermometer thermometer : thermometers) {
			facade.attach(thermometer);
		}
		return thermometers;
	}
	
	public List<TwiAddress> getAvailableTwiAddresses() {
		List<TwiAddress> availableAddresses = new ArrayList<>(facade.lookUp());

		Iterable<Thermometer> thermometers = repository.findAll();
		List<TwiAddress> usedAddresses = (ArrayList<TwiAddress>) StreamSupport.stream(thermometers.spliterator(), false)
				.map(t -> t.getAddress()).collect(Collectors.toList());
		
		availableAddresses.removeAll(usedAddresses);

		return availableAddresses;
		
	}	
}
		


	
	
	

