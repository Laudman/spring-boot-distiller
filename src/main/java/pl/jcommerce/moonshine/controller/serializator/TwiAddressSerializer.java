package pl.jcommerce.moonshine.controller.serializator;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import pl.jcommerce.moonshine.model.TwiAddress;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Used for serialization {@code TwiAddress} when obtained from view.
 * 
 * @return TwiAddress
 */
public class TwiAddressSerializer extends JsonSerializer<TwiAddress> {

	public void serialize(TwiAddress value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {

		gen.writeStartObject();
		gen.writeFieldName("physicalAddress");

		String serialized = Arrays.asList(value.getPhysicalAddress()).stream()
				.map(b -> String.format(Locale.US, "%02x", b))
				.collect(Collectors.joining(":"));

		gen.writeString(serialized);
		gen.writeEndObject();

	}

}
