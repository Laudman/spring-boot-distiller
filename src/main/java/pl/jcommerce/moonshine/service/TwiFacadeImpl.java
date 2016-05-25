package pl.jcommerce.moonshine.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import pl.jcommerce.moonshine.model.Thermometer;
import pl.jcommerce.moonshine.model.ThermometerDriverImpl;
import pl.jcommerce.moonshine.model.TwiAddress;

/**
 * Implementation of TwiFacade. Provides an implementation to obtain all
 * physical addresses. Also allows to attach logical thermometers to physical
 * slot.
 * 
 * @see TwiFacade
 * @author wipo
 *
 */
@Component
public class TwiFacadeImpl implements TwiFacade {

	@Override
	public List<TwiAddress> lookUp() {
		return Arrays.asList(
				new TwiAddress(new Byte[] { 65, 11, 64, 23, 54, 23, 88, 16 }),
				new TwiAddress(new Byte[] { 00, 33, 44, 10, 0, 1, 75, 73 }),
				new TwiAddress(new Byte[] { 78, 21, 64, 58, 85, 43, 63, 04 }),
				new TwiAddress(new Byte[] { 43, 33, 55, 24, 8, 23, 85, 02 }),
				new TwiAddress(new Byte[] { 01, 24, 64, 15, 54, 23, 54, 20 }));
	}

	@Override
	public void attach(Thermometer thermometer) {
		thermometer.setDriver(new ThermometerDriverImpl(thermometer.getAddress()));
	}

	@Override
	public void attachAll(List<Thermometer> thermometers) {
		for (Thermometer thermometer : thermometers) {
			thermometer.setDriver(new ThermometerDriverImpl(thermometer.getAddress()));
		}

	}

}
