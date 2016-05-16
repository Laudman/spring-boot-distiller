package pl.jcommerce.moonshine.model;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.jcommerce.moonshine.model.serializator.TwiAddressDeserializer;
import pl.jcommerce.moonshine.model.serializator.TwiAddressSerializer;

@Data
@Embeddable
@RequiredArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using=TwiAddressDeserializer.class)
@JsonSerialize(using=TwiAddressSerializer.class)
public class TwiAddress {

	@NonNull
	private Byte[] physicalAddress;
	
}
