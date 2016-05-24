package pl.jcommerce.moonshine.model.thermometer;

import java.util.List;

public interface TwiFacade {

	public List<TwiAddress> lookUp();

	public void attach(Thermometer thermometer);

}
