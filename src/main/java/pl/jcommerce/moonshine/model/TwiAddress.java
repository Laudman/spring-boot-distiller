package pl.jcommerce.moonshine.model;

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
	private String physicalAddress;

}
