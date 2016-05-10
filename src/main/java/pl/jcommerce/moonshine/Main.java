package pl.jcommerce.moonshine;

import java.util.List;

import pl.jcommerce.moonshine.model.Thermometer;
import pl.jcommerce.moonshine.model.TwiAddress;
import pl.jcommerce.moonshine.model.TwiFacade;
import pl.jcommerce.moonshine.model.TwiFacadeImpl;

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
