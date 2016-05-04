package pl.jcommerce.moonshine;

import java.util.Arrays;
import java.util.List;

public class TwiFacadeImpl implements TwiFacade {

	@Override
	public List<TwiAddress> lookUp() {
		return Arrays.asList(new TwiAddress(new byte[] { 21, 3, 64, 23, 54, 23, 54, 34 }));
	}

	@Override
	public void attach(Thermometer thermometer) {
		thermometer.setDriver(new ThermometerDriver() {

			@Override
			public double getTemperature() {
				return 36.6;
			}

			@Override
			public TwiAddress getAddress() {
				return thermometer.getAddress();
			}
		});
	}

}
