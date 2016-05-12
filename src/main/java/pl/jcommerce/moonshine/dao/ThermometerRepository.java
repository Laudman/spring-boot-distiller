package pl.jcommerce.moonshine.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pl.jcommerce.moonshine.model.Thermometer;

public interface ThermometerRepository extends CrudRepository<Thermometer, Long> {

	List<Thermometer> findByName(String name);
	Thermometer findById(Long id);
}
