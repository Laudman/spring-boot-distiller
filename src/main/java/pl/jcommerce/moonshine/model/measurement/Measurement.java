package pl.jcommerce.moonshine.model.measurement;

import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.jcommerce.moonshine.model.thermometer.BaseEntity;
import pl.jcommerce.moonshine.model.thermometer.Thermometer;

@Data
@AllArgsConstructor
@Entity
public class Measurement extends BaseEntity {

	private LocalTime time;
	private Double value;
	@ManyToOne
	@JoinColumn
	private Thermometer thermometer;

}
