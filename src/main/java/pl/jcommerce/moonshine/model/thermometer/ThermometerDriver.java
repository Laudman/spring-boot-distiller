package pl.jcommerce.moonshine.model.thermometer;

public interface ThermometerDriver {

	TwiAddress getAddress();

	double getTemperature();

}
