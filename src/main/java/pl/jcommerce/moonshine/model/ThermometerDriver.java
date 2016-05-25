package pl.jcommerce.moonshine.model;

/**
 * This interface provides methods to obtain actual temperature and thermometer
 * physisal address
 * 
 * @author wipo
 *
 */
public interface ThermometerDriver {

	TwiAddress getAddress();

	double getTemperature();

}
