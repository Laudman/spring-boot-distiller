package pl.jcommerce.moonshine.model;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity which contains time of measurement and its value. In
 * relation with Thermometer class.
 * 
 * @author wipo
 * @see Thermometer
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Measurement extends BaseEntity {

	private LocalTime time;
	private Double value;
	@ManyToOne
	@JoinColumn
	private Thermometer thermometer;

}
