package pl.jcommerce.moonshine.model;

import java.util.Random;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;

@Data
public class ThermometerDriverImpl implements ThermometerDriver {

	private TwiAddress address;
	private double temperature;
	@Transient
	@JsonIgnore
	private Random randomizer = new Random();

	public ThermometerDriverImpl(TwiAddress address) {
		this.address = address;
		this.temperature = randomizer.nextDouble()*20+25;
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
