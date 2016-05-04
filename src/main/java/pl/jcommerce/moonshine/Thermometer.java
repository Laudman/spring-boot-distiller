package pl.jcommerce.moonshine;

import lombok.Data;
import lombok.NonNull;

@Data
public class Thermometer {

	@NonNull
	private String name;
	private TwiAddress address;
	private ThermometerDriver driver;

	public double getTemperature() {
		if (driver == null) {
			throw new IllegalStateException("Thermometer is not attached.");
		}
		return driver.getTemperature();
	}
}
