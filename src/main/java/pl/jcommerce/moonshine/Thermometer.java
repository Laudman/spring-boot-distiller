package pl.jcommerce.moonshine;

import javax.persistence.Entity;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NonNull;

@Data
//@EqualsAndHashCode(callSuper=false)
@Entity
public class Thermometer extends BaseEntity {

	@NonNull
	private String name;
	private TwiAddress address;
	
	@Transient
	private ThermometerDriver driver;

	public double getTemperature() {
		if (driver == null) {
			throw new IllegalStateException("Thermometer is not attached.");
		}
		return driver.getTemperature();
	}
}
