package pl.jcommerce.moonshine.service;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pl.jcommerce.moonshine.model.measurement.Measurement;
import pl.jcommerce.moonshine.model.thermometer.Thermometer;
import pl.jcommerce.moonshine.repository.MeasurementRepository;

@Component
public class ThermometerTemperatureSaver {

	@Autowired
	private MeasurementRepository measurementRepository;
	
	@Autowired
	private ThermometerService thermometerRepository;
	
	@Scheduled(fixedDelay=10000)
	public void saveTemperature() {
		Iterable<Thermometer>  thermometers = thermometerRepository.findAllNotDeleted();
		
		for(Thermometer termometer : thermometers) {
			measurementRepository.save(new Measurement(LocalTime.now(), termometer.getTemperature(), termometer));
		}
	}
}
