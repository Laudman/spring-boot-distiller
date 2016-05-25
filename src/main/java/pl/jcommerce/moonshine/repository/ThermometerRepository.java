package pl.jcommerce.moonshine.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pl.jcommerce.moonshine.model.Measurement;
import pl.jcommerce.moonshine.model.Thermometer;

/**
 * Provides operations for CRUD operations of thermometers. Extends
 * CrudRepository
 * 
 * @see Thermometer
 * @see CrudRepository
 * @author wipo
 *
 */
public interface ThermometerRepository extends CrudRepository<Thermometer, Long> {

	List<Thermometer> findByName(String name);

	Thermometer findById(Long id);

	List<Thermometer> findAllByDeletedFalse();

}
