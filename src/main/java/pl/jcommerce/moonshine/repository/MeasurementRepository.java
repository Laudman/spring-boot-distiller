package pl.jcommerce.moonshine.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.jcommerce.moonshine.model.Measurement;
import pl.jcommerce.moonshine.model.Thermometer;

import java.util.List;

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

	/**
	 * This method returns last measurement for available thermometers. Used to real-time
	 * chart update
	 * 
	 * @param thermometer
	 * @return last measurement for given thermometer
	 */
	@Query("SELECT m FROM Measurement m WHERE m.time IN (SELECT MAX(m2.time) from Measurement m2 GROUP BY m2.thermometer HAVING m2.thermometer IN (SELECT t.id FROM Thermometer t WHERE t.deleted=false))")
	List<Measurement> findLatestMeasurementForAllThermometers();

}