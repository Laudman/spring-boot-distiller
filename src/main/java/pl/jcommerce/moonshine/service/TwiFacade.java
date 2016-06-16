package pl.jcommerce.moonshine.service;

import pl.jcommerce.moonshine.model.Thermometer;
import pl.jcommerce.moonshine.model.TwiAddress;

import java.util.List;

/**
 * 
 * Provides methods that assigns physical addresses to logical thermometers. It
 * also allows to obtain all physical I2C addresses.
 * 
 * @author wipo
 *
 */
public interface TwiFacade {
	/**
	 * Returns all physical TwiAddresses
	 * 
	 * @return list of physical addresses
	 */
	List<TwiAddress> lookUp();

	/**
	 * Attaches single physical address to logical ThermometerDriver
	 * 
	 * @param thermometer
	 */
	void attach(Thermometer thermometer);

	/**
	 * Attaches all physical addresses to logical ThermometerDriver
	 * 
	 * @param thermometers
	 */
	void attachAll(List<Thermometer> thermometers);
}
