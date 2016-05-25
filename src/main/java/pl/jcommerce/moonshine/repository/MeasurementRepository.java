package pl.jcommerce.moonshine.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pl.jcommerce.moonshine.model.Measurement;
import pl.jcommerce.moonshine.model.Thermometer;

/**
 * Provides operations for CRUD operations of thermometers measurement. Extends
 * CrudRepository
 * 
 * @see Measurement
 * @see CrudRepository
 * @author wipo
 *
 */
public interface MeasurementRepository extends CrudRepository<Measurement, Long> {
	/**
	 * Return list of measurements for given thermometer
	 * 
	 * @param thermometer
	 * @return list of measurements for given thermometer
	 */
	List<Measurement> findByThermometer(Thermometer thermometer);
}
