package pl.jcommerce.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pl.jcommerce.moonshine.Thermometer;

public interface ThermometerRepository extends CrudRepository<Thermometer, Long> {

	List<Thermometer> findByName(String name);

}
