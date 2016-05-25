package pl.jcommerce.moonshine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Provides a method to save measurements from thermometers to database
 * 
 * @author wipo
 *
 */
@Component
public class ThermometerDataGenerator {

	@Autowired
	ThermometerService service;

	/**
	 * Records measurements from thermometers within the specified time(10s)
	 * 
	 * @see @Scheluded
	 */
	@Scheduled(fixedDelay = 10000)
	public void saveTemperature() {
		service.collectMeasurements();
	}

}