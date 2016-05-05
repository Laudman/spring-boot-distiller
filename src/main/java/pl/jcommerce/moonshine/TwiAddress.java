package pl.jcommerce.moonshine;

import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Embeddable
@RequiredArgsConstructor
@NoArgsConstructor
public class TwiAddress {

	@NonNull
	private byte[] physicalAddress;

}
