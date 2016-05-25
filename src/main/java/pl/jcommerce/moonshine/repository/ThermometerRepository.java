package pl.jcommerce.moonshine.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

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

	/**
	 * Returns thermometer by the given id.
	 * 
	 * @param id
	 * @return
	 */
	Thermometer findById(Long id);

	/**
	 * Returns list of thermometers where field {@link Thermometer#deleted} is
	 * false
	 * 
	 * @return list of thermometers which aren't set as deleted.
	 */
	List<Thermometer> findAllByDeletedFalse();

}
