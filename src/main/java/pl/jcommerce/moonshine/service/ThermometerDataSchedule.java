package pl.jcommerce.moonshine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.jcommerce.moonshine.model.Measurement;
import pl.jcommerce.moonshine.model.Thermometer;
import pl.jcommerce.moonshine.repository.MeasurementRepository;

import java.time.LocalDateTime;

/**
 * Provides scheluded method used to save thermometers measurement to database
 *
 * @author wipo
 */
@Component
@EnableScheduling
public class ThermometerDataSchedule {

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private ThermometerService service;

    /**
     * Repeats each 10s ThermometerService's method called
     * generateMeasurementsThenSave();
     *
     * @see @Scheluded
     */
    @Scheduled(fixedDelay = 10000)
    public void repeatGenerationForMeasurement() {
        Iterable<Thermometer> thermometers = service.findAllThermometers();

        for (Thermometer thermometer : thermometers) {
            measurementRepository.save(new Measurement(LocalDateTime.now(), thermometer.getTemperature(), thermometer));
        }
    }
}