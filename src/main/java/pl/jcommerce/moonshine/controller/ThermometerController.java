package pl.jcommerce.moonshine.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.jcommerce.moonshine.model.measurement.Measurement;
import pl.jcommerce.moonshine.model.thermometer.Thermometer;
import pl.jcommerce.moonshine.model.thermometer.TwiAddress;
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

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void save(@PathVariable Long id) {
		service.delete(id);
	}

	@RequestMapping("/addresses")
	public List<TwiAddress> findAllTwiAddresses() {
		return service.getAllTwiAddresses();
	}
	@RequestMapping("/available")
	public List<TwiAddress> findAvailableTwiAddresses() {
		return service.getAvailableTwiAddresses();
	}

	@RequestMapping("/allthermometers")
	public Iterable<Thermometer> findAll() {
		return service.findAll();
	}
	@RequestMapping("/availablethermometers")
	public Iterable<Thermometer> findAllNotDeleted() {
		return service.findAllNotDeleted();
	}

//	@SendTo("/hello")
//	@RequestMapping("/temperature/{id}")
//	public void setIdForChart(@PathVariable Long id) {
//		service.setChoosenThermometer(id);
//	}
	//////////////////
//	@RequestMapping("/temperatures")
//	public Map<Thermometer, List<Measurement>> getAllThermometersTemperature() {
//		return service.getAllThermometersTemperature();
//	}
	//////////////////
	@RequestMapping("/aaa")
	public String aaa() {
		return "&label=02:15:59&value=55";
	}

}
