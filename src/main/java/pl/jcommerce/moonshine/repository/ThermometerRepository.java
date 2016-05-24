package pl.jcommerce.moonshine.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pl.jcommerce.moonshine.model.thermometer.Thermometer;

public interface ThermometerRepository extends CrudRepository<Thermometer, Long> {

	List<Thermometer> findByName(String name);
	Thermometer findById(Long id);
}
