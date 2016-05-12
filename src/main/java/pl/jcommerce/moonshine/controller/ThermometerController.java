package pl.jcommerce.moonshine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.jcommerce.moonshine.model.Thermometer;
import pl.jcommerce.moonshine.service.ThermometerService;

@RestController
@RequestMapping("/thermometer")
public class ThermometerController {

	@Autowired
	private ThermometerService service;

	@RequestMapping("/add")
	public Thermometer save(@RequestBody Thermometer thermometer) {
		return service.save(thermometer);
	}
	
	@RequestMapping(value= "/delete/{id}", method=RequestMethod.DELETE)
	public void save(@PathVariable Long id) {
		service.delete(id);
	}
	
	@RequestMapping("/addresses")
	public List<String> getTwiAddresses() {
		return service.getAvailableTwiAddresses();
	}

	@RequestMapping("/thermometers")
	public Iterable<Thermometer> all() {
		return service.getThermometers();
	}
	
	@RequestMapping("/temperature/{id}")
	public double getTemperature(@PathVariable Long id) {
		return service.getTemperatureById(id);
	}

}
