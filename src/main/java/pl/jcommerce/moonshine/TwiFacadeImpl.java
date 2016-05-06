package pl.jcommerce.moonshine;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TwiFacadeImpl implements TwiFacade {

	@Override
	public List<TwiAddress> lookUp() {
		return Arrays.asList(new TwiAddress(new byte[] { 54, 33, 64, 23, 54, 23, 54, 04 }),
				new TwiAddress(new byte[] { 77, 73, 74, 23, 54, 23, 54, 34 }),
				new TwiAddress(new byte[] { 54, 3, 68, 77, 87, 28, 54, 0 }),
				new TwiAddress(new byte[] { 21, 0, 84, 0, 54, 0, 44, 84 }),
				new TwiAddress(new byte[] { 85, 3, 74, 23, 54, 23, 84, 64 }));
	}

	@Override
	public void attach(Thermometer thermometer) {
		thermometer.setDriver(new ThermometerDriverImpl(thermometer.getAddress(), 36.6));
	}

}
