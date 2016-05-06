package pl.jcommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.jcommerce.moonshine.Thermometer;
import pl.jcommerce.moonshine.TwiAddress;
import pl.jcommerce.moonshine.service.ThermometerService;

@RestController
@RequestMapping("/thermometer")
public class ThermometerController {

	@Autowired
	private ThermometerService service;

	@RequestMapping("/addresses")
	public List<TwiAddress> getTwiAddresses() {
		return service.getTwiAddresses();
	}

	@RequestMapping("/add")
	public Thermometer save(@RequestBody Thermometer thermometer) {
		return service.save(thermometer);
	}

	@RequestMapping("/all")
	public Iterable<Thermometer> all() {
		return service.getThermometers();
	}

}
