package pl.jcommerce.moonshine.repository;

import org.springframework.data.repository.CrudRepository;
import pl.jcommerce.moonshine.model.Thermometer;

import java.util.List;

/**
 * Provides operations for CRUD operations of thermometers. Extends
 * CrudRepository
 *
 * @author wipo
 * @see Thermometer
 * @see CrudRepository
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
