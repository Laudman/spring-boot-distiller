package pl.jcommerce.moonshine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.jcommerce.dao.ThermometerRepository;
import pl.jcommerce.moonshine.Thermometer;
import pl.jcommerce.moonshine.TwiAddress;
import pl.jcommerce.moonshine.TwiFacade;

@Service
public class ThermometerService {

	@Autowired
	private ThermometerRepository repository;

	@Autowired
	private TwiFacade facade;

	public Iterable<Thermometer> getThermometers() {
		Iterable<Thermometer> thermometers = repository.findAll();
		for (Thermometer thermometer : thermometers) {
			facade.attach(thermometer);
		}
		return thermometers;
	}

	public Thermometer save(Thermometer thermometer) {
		facade.attach(thermometer);
		return repository.save(thermometer);
	}

	public List<TwiAddress> getTwiAddresses() {
		return facade.lookUp();
	}

}
