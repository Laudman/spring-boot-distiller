package pl.jcommerce.moonshine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.ejb.Schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import pl.jcommerce.moonshine.model.thermometer.Thermometer;
import pl.jcommerce.moonshine.model.thermometer.TwiAddress;
import pl.jcommerce.moonshine.model.thermometer.TwiFacade;
import pl.jcommerce.moonshine.repository.MeasurementRepository;
import pl.jcommerce.moonshine.repository.ThermometerRepository;

@Service
public class ThermometerService {

	@Autowired
	private ThermometerRepository thermometerRepository;

	@Autowired
	private TwiFacade facade;

	@Autowired
	private MeasurementRepository measureRepository;

	private Thermometer choosen = new Thermometer();

	public Thermometer save(Thermometer thermometer) {
		facade.attach(thermometer);
		return thermometerRepository.save(thermometer);
	}

	// public void collectTemperature(Thermometer thermometer) {
	// measureRepository.save(entities);
	// }

	public void delete(Long id) {
//		deleteTemperatureFromDatabase(id);
		thermometerRepository.delete(id);		
	}
	
//	public void deleteTemperatureFromDatabase(Long id) {
//		
//	}

//	public Thermometer findById(Long id) {
//		return thermometerRepository.findById(id);
//	}

	public Iterable<Thermometer> findAll() {
		List<Thermometer> thermometers = new ArrayList<Thermometer>();
		for (Thermometer thermometer : thermometerRepository.findAll()) {
			facade.attach(thermometer);
			thermometers.add(thermometer);
		}
		return thermometers;
	}

	public Iterable<Thermometer> findAllNotDeleted() {
		List<Thermometer> thermometers = new ArrayList<Thermometer>();
		for (Thermometer thermometer : thermometerRepository.findAll()) {
			if (!thermometer.isDeleted()) {
				facade.attach(thermometer);
				thermometers.add(thermometer);
			}
		}
		return thermometers;
	}

//	public void setChoosenThermometer(Long id) {
//		Thermometer thermometer = thermometerRepository.findById(id);
//		facade.attach(thermometer);
//		choosen = thermometer;
//	}
//
//	public Thermometer getChoosenTermometer() {
//		facade.attach(choosen);
//		return choosen;
//	}

	public Iterable<Thermometer> getThermometers() {
		Iterable<Thermometer> thermometers = thermometerRepository.findAll();
		for (Thermometer thermometer : thermometers) {
			facade.attach(thermometer);
		}
		return thermometers;
	}

	public List<TwiAddress> getAvailableTwiAddresses() {
		List<TwiAddress> availableAddresses = new ArrayList<>(facade.lookUp());

		Iterable<Thermometer> thermometers = thermometerRepository.findAll();
		List<TwiAddress> usedAddresses = (ArrayList<TwiAddress>) StreamSupport.stream(thermometers.spliterator(), false)
				.map(t -> t.getAddress()).collect(Collectors.toList());

		availableAddresses.removeAll(usedAddresses);
		return availableAddresses;

	}

	// http://stackoverflow.com/questions/27952472/serialize-deserialize-java-8-java-time-with-jackson-json-mapper
	// http://stackoverflow.com/questions/27952472/serialize-deserialize-java-8-java-time-with-jackson-json-mapper

	// public Map<Thermometer, List<Measurement>>
	// getAllThermometersTemperature() {
	//
	// Map<Thermometer, List<Measurement>> map = new HashMap<Thermometer,
	// List<Measurement>>();
	//
	// for (Thermometer t : getThermometers()) {
	// List<Measurement> thermometerMeasurement = new ArrayList<Measurement>();
	// for (int i = 0; i <= 10; i++) {
	// thermometerMeasurement.add(new Measurement(LocalTime.now(),
	// t.getTemperature()));
	// }
	// map.put(t, thermometerMeasurement);
	// }
	//
	// return map;
	// }
}
