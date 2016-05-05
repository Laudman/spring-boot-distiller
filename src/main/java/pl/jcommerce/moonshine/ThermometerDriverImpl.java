package pl.jcommerce.moonshine;

public class ThermometerDriverImpl implements ThermometerDriver {
	
	TwiAddress address;
	double temp;

	public ThermometerDriverImpl(TwiAddress address, double temp) {
		this.address = address;
		this.temp = temp;
	}

	@Override
	public TwiAddress getAddress() {
		return address;
	}

	@Override
	public double getTemperature() {
		return temp;
	}

}
