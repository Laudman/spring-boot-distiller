package pl.jcommerce.moonshine.model;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TwiFacadeImpl implements TwiFacade {

	@Override
	public List<TwiAddress> lookUp() {
		return Arrays.asList(
				new TwiAddress("5433642354235404"),
				new TwiAddress("8798456498749849"),
				new TwiAddress("5646874894981456"),
				new TwiAddress("7781050591980561"),
				new TwiAddress("6547894156815616"));
	}

	@Override
	public void attach(Thermometer thermometer) {
		thermometer.setDriver(new ThermometerDriverImpl(thermometer.getAddress()));
	}

}
