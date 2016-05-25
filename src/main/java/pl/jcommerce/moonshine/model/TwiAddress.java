package pl.jcommerce.moonshine.model;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.jcommerce.moonshine.controller.serializator.TwiAddressDeserializer;
import pl.jcommerce.moonshine.controller.serializator.TwiAddressSerializer;

/**
 * Contains physical thermometer address using I2C interface.
 * Serializable by JSON serializable classes:
 * @see TwiAddressSerializer
 * @see TwiAddressDeserializer
 * 
 * @author wipo
 *
 */
@Data
@Embeddable
@RequiredArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = TwiAddressDeserializer.class)
@JsonSerialize(using = TwiAddressSerializer.class)
public class TwiAddress {

	@NonNull
	private Byte[] physicalAddress;

}
