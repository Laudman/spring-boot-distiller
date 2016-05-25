package pl.jcommerce.moonshine.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
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
	 * Attaches a thermometer to a physical slot. then saves logical thermometer
	 * to database.
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
	 * This method changes {@link Thermometer#setDeleted(true)} field to
	 * <i>true</i> , next saves it back to database.
	 * 
	 * @param id
	 */
	@Transactional
	public void delete(Long id) {
		Thermometer thermometer = getById(id);
		thermometer.setDeleted(true);
		setDeletedForMeasurement(thermometer);
		save(thermometer);
	}

	/**
	 * Returns all thermometer entities which {@link Thermometer#isDeleted()}
	 * field is false, attaches them to physical slot, and returns it.
	 * 
	 * @return list of attached, available thermometers
	 */
	public Iterable<Thermometer> findAllThermometers() {
		List<Thermometer> thermometers = new ArrayList<Thermometer>(thermometerRepository.findAllByDeletedFalse());
		facade.attachAll(thermometers);
		return thermometers;
	}

	/**
	 * Returns all available TwiAddress objects which weren't already used to
	 * attach for logical thermometers.
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
	 * Collects measurements of all available thermometers, then saves them to
	 * database
	 */
	public void collectMeasurements() {
		Iterable<Thermometer> thermometers = findAllThermometers();

		for (Thermometer termometer : thermometers) {
			measurementRepository.save(new Measurement(LocalTime.now(), termometer.getTemperature(), termometer));
		}
	}

	/**
	 * Disables temperature measurement for given thermometer. This
	 * method is invoked when {@link #delete(Long)} method is used.
	 * 
	 * @param thermometer
	 */
	@Transactional
	public void setDeletedForMeasurement(Thermometer thermometer) {
		List<Measurement> measurements = measurementRepository.findByThermometer(thermometer);
		measurements.stream().forEach(m -> m.setDeleted(true));
		measurementRepository.save(measurements);
	}
}
