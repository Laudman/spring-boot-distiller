package pl.jcommerce.moonshine.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.jcommerce.moonshine.model.Measurement;
import pl.jcommerce.moonshine.model.Thermometer;
import pl.jcommerce.moonshine.model.TwiAddress;
import pl.jcommerce.moonshine.service.ThermometerService;

/**
 * Controller designed for thermometers CRUD operations
 * 
 * @author wipo
 *
 */
@RestController
@RequestMapping("thermometer")
public class ThermometerController {

	@Autowired
	private ThermometerService service;

	/**
	 * Saves given thermometer to database
	 * 
	 * @param Thermometer
	 *            to save
	 * @return Thermometer saved
	 */
	@RequestMapping("/add")
	public Thermometer save(@RequestBody Thermometer thermometer) {
		return service.save(thermometer);
	}

	/**
	 * Delete the specified thermometer
	 * 
	 * @param id
	 *            of thermometer
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void save(@PathVariable Long id) {
		service.delete(id);
	}

	/**
	 * Returns available logical thermometers
	 * 
	 * @return Thermometer iterable collection
	 */
	@RequestMapping("/thermometers")
	public Iterable<Thermometer> findAvailableThermometers() {
		return service.findAllThermometers();
	}

	/**
	 * Returns available TwiAddresses
	 * 
	 * @return TwiAddress available list
	 */
	@RequestMapping("/addresses")
	public List<TwiAddress> findAvailableTwiAddresses() {
		return service.findAvailableTwiAddresses();
	}

	/**
	 * Returns measurements for attached thermometers
	 * 
	 * @return
	 */
	@RequestMapping("measurements")
	public Map<String, List<Measurement>> getMeasurementsForAttachedThermometers() {
		return service.getMeasurementsForAttachedThermometers();
	}
}
