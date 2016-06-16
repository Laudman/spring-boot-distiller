package pl.jcommerce.moonshine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Entity which is a representation of logical thermometer. In relation with
 * Measurement class.
 *
 * @author wipo
 * @see Measurement
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Thermometer extends BaseEntity {

    @NonNull
    private String name;
    @Getter(onMethod = @__({@JsonIgnore}))
    @Setter(onMethod = @__({@JsonProperty}))
    private TwiAddress address;
    private String description;
    @Transient
    private ThermometerDriver driver;

    /**
     * Returns temperature
     *
     * @return double temperature
     */
    public double getTemperature() {
        if (driver == null) {
            throw new IllegalStateException("Thermometer is not attached.");
        }
        return driver.getTemperature();
    }

}
