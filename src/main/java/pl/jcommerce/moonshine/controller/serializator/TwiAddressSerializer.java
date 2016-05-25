package pl.jcommerce.moonshine.controller.serializator;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import pl.jcommerce.moonshine.model.TwiAddress;

public class TwiAddressSerializer extends JsonSerializer<TwiAddress> {
	/**
	 * This method serializes {@code TwiAddress} to JSON data and
	 * 
	 * @param TwiAddress 
	 */
	@Override
	public void serialize(TwiAddress value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {

		gen.writeStartObject();
		gen.writeFieldName("physicalAddress");

		String serialized = Arrays.asList(value.getPhysicalAddress()).stream()
				.map(b -> String.format(Locale.US, "%02x", b))
				.collect(Collectors.joining(":"));

		gen.writeString(serialized);

		// gen.writeString

		// gen.writeString(Base64.encodeBase64String(value.getPhysicalAddress()));

		gen.writeEndObject();

	}

}
