package pl.jcommerce.moonshine.model.thermometer;

import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Thermometer extends BaseEntity {

	@NonNull
	private String name;
	@Getter(onMethod = @__({ @JsonIgnore }))
	@Setter(onMethod = @__({ @JsonProperty }))
	private TwiAddress address;

	@Transient
	private ThermometerDriver driver;

	public double getTemperature() {
		if (driver == null) {
			throw new IllegalStateException("Thermometer is not attached.");
		}
		return driver.getTemperature();
	}

}
