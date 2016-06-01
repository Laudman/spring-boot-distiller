package pl.jcommerce.moonshine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Provides scheluded method used to save thermometers measurement to database
 * 
 * @author wipo
 *
 */
@Component
@EnableScheduling
public class ThermometerDataSchedule {

	@Autowired
	ThermometerService service;

	/**
	 * Repeats each 10s ThermometerService's method called
	 * generateMeasurementsThenSave();
	 * 
	 * @see @Scheluded
	 */
//	@Scheduled(fixedDelay = 1000)
	public void repeatGenerationAndSavingForMeasurement() {
		service.generateMeasurementsThenSave();
	}

}