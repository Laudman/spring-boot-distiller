package pl.jcommerce.moonshine.model;

/**
 * This interface provides methods to obtain actual temperature and thermometer
 * physical address
 * 
 * @author wipo
 *
 */
public interface ThermometerDriver {
	/**
	 * Returns physical address
	 * 
	 * @return TwiAddress thermometer physical address
	 */
	TwiAddress getAddress();

	/**
	 * Returns thermometer's temperature
	 * 
	 * @return double Thermometer's temperature
	 */
	double getTemperature();

}
