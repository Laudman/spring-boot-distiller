package pl.jcommerce.moonshine.model.thermometer;

import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ThermometerDriverImpl implements ThermometerDriver {

	private TwiAddress address;
	@JsonIgnore
	private Random randomizer = new Random();

	public ThermometerDriverImpl(TwiAddress address) {
		this.address = address;
	}

	@Override
	public TwiAddress getAddress() {
		return address;
	}

	@Override
	public double getTemperature() {
		return randomizer.nextDouble()*20+25;
	}

}
