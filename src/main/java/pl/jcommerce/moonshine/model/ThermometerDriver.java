package pl.jcommerce.moonshine.model;

public interface ThermometerDriver {

	TwiAddress getAddress();

	double getTemperature();
	
	double generateTemperature();

}
