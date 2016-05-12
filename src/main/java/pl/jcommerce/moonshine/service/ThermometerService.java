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
	public double getTemperatureById(Long id) {
		Thermometer thermometer = repository.findById(id);
		facade.attach(thermometer);
		return thermometer.getTemperature();
	}

	public Iterable<Thermometer> getThermometers() {
		Iterable<Thermometer> thermometers = repository.findAll();
		for (Thermometer thermometer : thermometers) {
			facade.attach(thermometer);
		}
		return thermometers;
	}
	
	public List<String> getAvailableTwiAddresses() {
		List<TwiAddress> availableAddresses = new ArrayList<>(facade.lookUp());

		Iterable<Thermometer> thermometers = repository.findAll();
		List<TwiAddress> usedAddresses = (ArrayList<TwiAddress>) StreamSupport.stream(thermometers.spliterator(), false)
				.map(t -> t.getAddress()).collect(Collectors.toList());
		
		availableAddresses.removeAll(usedAddresses);

		List<String> list = availableAddresses.stream().map(TwiAddress:: getPhysicalAddressAsString).collect(Collectors.toList());
		return list;
		
	}
}
