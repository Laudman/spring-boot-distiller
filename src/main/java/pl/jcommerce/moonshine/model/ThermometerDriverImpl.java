package pl.jcommerce.moonshine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Random;

/**
 * Driver for logical thermometer, its an implementation of
 * <i>ThermometerDriver</i> interface, contains own TwiAddress, returns
 * randomized thermometer temperature.
 * 
 * @author wipo
 * @see ThermometerDriver
 */
@Data
public class ThermometerDriverImpl implements ThermometerDriver {

	private TwiAddress address;
	/**
	 * When invoked by controller, randomizer is being ignored by Json
	 * serializer
	 */
	@JsonIgnore
	private Random randomizer;

	/**
	 * Constructs driver implementation according given TwiAddress
	 * 
	 * @param TwiAddress
	 */
	public ThermometerDriverImpl(TwiAddress address) {
		this.address = address;
		randomizer = new Random();
	}

	@Override
	public TwiAddress getAddress() {
		return address;
	}

	@Override
	public double getTemperature() {
		return randomizer.nextDouble() * 20 + 25;
	}

}
