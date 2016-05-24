package pl.jcommerce.moonshine.repository;

import org.springframework.data.repository.CrudRepository;

import pl.jcommerce.moonshine.model.measurement.Measurement;

public interface MeasurementRepository extends CrudRepository<Measurement, Long>{
	
}
