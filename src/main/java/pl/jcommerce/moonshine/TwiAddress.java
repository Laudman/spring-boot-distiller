package pl.jcommerce.moonshine;

import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@RequiredArgsConstructor
public class TwiAddress {

	@NonNull
	private byte[] physicalAddress;

	public TwiAddress(String address) {
		physicalAddress = address.getBytes();
	}

}
