package pl.jcommerce.moonshine;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		TwiFacade facade = new TwiFacadeImpl();
		List<TwiAddress> addresses = facade.lookUp();
		Thermometer thermometer = new Thermometer("Thermometer 1");
		thermometer.setAddress(addresses.get(0));
		facade.attach(thermometer);
		thermometer.getTemperature();
	}

}
