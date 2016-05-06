package pl.jcommerce.moonshine;

import lombok.Data;

@Data
public class ThermometerDriverImpl implements ThermometerDriver {

	private TwiAddress address;
	private double temperature;

	public ThermometerDriverImpl(TwiAddress address, double temperature) {
		this.address = address;
		this.temperature = temperature;
	}

	@Override
	public TwiAddress getAddress() {
		return address;
	}

	@Override
	public double getTemperature() {
		return temperature;
	}

}
