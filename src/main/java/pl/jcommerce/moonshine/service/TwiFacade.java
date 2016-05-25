package pl.jcommerce.moonshine.service;

import java.util.List;

import pl.jcommerce.moonshine.model.Thermometer;
import pl.jcommerce.moonshine.model.TwiAddress;
/**
 * Provides methods that assigns physical addresses to logical thermometers.
 * It also allows to obtain all physical addresses.
 * @author wipo
 *
 */
public interface TwiFacade {

	List<TwiAddress> lookUp();

	void attach(Thermometer thermometer);
	
	void attachAll(List<Thermometer> thermometers);
}
