package pl.jcommerce.moonshine;

import lombok.Data;
import lombok.NonNull;

@Data
public class TwiAddress {

	@NonNull
	private byte[] physicalAddress;

}
