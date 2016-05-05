package pl.jcommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.jcommerce.database.ThermometerRepository;
import pl.jcommerce.moonshine.Thermometer;
import pl.jcommerce.moonshine.TwiAddress;
import pl.jcommerce.moonshine.TwiFacade;

@RestController
@RequestMapping("/thermometer")
public class ThermometerController {
	
	@Autowired
	private TwiFacade facade;
	
	@Autowired
	private ThermometerRepository repository;
	
	@RequestMapping("/addresses")
	public List<TwiAddress> getAddresses() {
		return facade.lookUp();
	}
	
	@RequestMapping("/add")
	public Thermometer save(@RequestBody Thermometer thermometer) {
		return repository.save(thermometer);
	}
	
}
