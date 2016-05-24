package pl.jcommerce.moonshine.model.serializator;

import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import pl.jcommerce.moonshine.model.thermometer.TwiAddress;

public class TwiAddressDeserializer extends JsonDeserializer<TwiAddress> {

	@Override
	public TwiAddress deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectCodec oc = p.getCodec();
		JsonNode node = oc.readTree(p);
		
		Byte[] aaa = Arrays.stream(node.asText().split(":"))
				.map(var -> Byte.parseByte(var, 16))
				.toArray(size -> new Byte[size]);

		return new TwiAddress(aaa);
	}

}
