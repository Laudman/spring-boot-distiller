package pl.jcommerce.moonshine.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.jcommerce.moonshine.model.Measurement;
import pl.jcommerce.moonshine.model.Thermometer;
import pl.jcommerce.moonshine.model.TwiAddress;
import pl.jcommerce.moonshine.repository.MeasurementRepository;
import pl.jcommerce.moonshine.repository.ThermometerRepository;

/**
 * Provides implementation for CRUD operations on logical thermometers and its
 * measurement.
 * 
 * @author wipo
 *
 */
@Service
public class ThermometerService {

	@Autowired
	private ThermometerRepository thermometerRepository;

	@Autowired
	private MeasurementRepository measurementRepository;

	@Autowired
	private TwiFacade facade;

	/**
	 * Attaches a thermometer to a physical device. then saves logical
	 * thermometer to database.
	 * 
	 * @param thermometer
	 * @return saved entity
	 */
	@Transactional
	public Thermometer save(Thermometer thermometer) {
		facade.attach(thermometer);
		return thermometerRepository.save(thermometer);
	}

	/**
	 * Retrieves a logical thermometer from database, then attaches to physical
	 * slot, and returns it.
	 * 
	 * @param id
	 * @return the entity with the given id or null if none found
	 */
	public Thermometer getById(Long id) {
		Thermometer thermometer = thermometerRepository.findById(id);
		facade.attach(thermometer);
		return thermometer;
	}

	/**
	 * This method disconnects logical thermometer from physical address bus
	 * making it unaccessible for <i>findAllThermometers</i> method, but still
	 * can be obtained by <i>getById</i> method
	 * 
	 * @param id
	 */
	@Transactional
	public void delete(Long id) {
		Thermometer thermometer = getById(id);
		thermometer.setDeleted(true);
		save(thermometer);
	}

	/**
	 * Returns all thermometer entities which <i>deleted</i> field is false,
	 * attaches them to physical device, and returns it.
	 * 
	 * @return list of attached, available thermometers
	 */
	public Iterable<Thermometer> findAllThermometers() {
		List<Thermometer> thermometers = new ArrayList<Thermometer>(thermometerRepository.findAllByDeletedFalse());
		facade.attachAll(thermometers);
		return thermometers;
	}

	/**
	 * Returns all available TwiAddresses which weren't already used, and are
	 * still available to connect them with logical thermometers.
	 * 
	 * @return list of physical thermometers addresses
	 */
	public List<TwiAddress> findAvailableTwiAddresses() {
		List<TwiAddress> availableAddresses = new ArrayList<>(facade.lookUp());
		Iterable<Thermometer> thermometers = findAllThermometers();
		List<TwiAddress> usedAddresses = (ArrayList<TwiAddress>) StreamSupport.stream(thermometers.spliterator(), false)
				.map(t -> t.getAddress()).collect(Collectors.toList());
		availableAddresses.removeAll(usedAddresses);
		return availableAddresses;
	}

	/**
	 * Collects measurements for attached thermometers
	 * 
	 * @return map with thermometer key and measurements as value
	 */
	public Map<String, List<Measurement>> getMeasurementsForAttachedThermometers() {

		Map<String, List<Measurement>> measurements = new HashMap<String, List<Measurement>>();

		for (Thermometer thermometer : findAllThermometers()) {
			measurements.put(thermometer.getName(), getMeasurementsFor(thermometer));
		}
		return measurements;
	}

	/**
	 * Returns measurements list for given thermometer
	 * 
	 * @param thermometer
	 * @return measurements list
	 */
	private List<Measurement> getMeasurementsFor(Thermometer thermometer) {
		return new ArrayList<>(measurementRepository.findByThermometer(thermometer));
	}


}
