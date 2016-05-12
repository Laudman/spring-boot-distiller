package pl.jcommerce.moonshine.model;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Data
@Embeddable
public class TwiAddress {

	@NonNull
	@Lob
	@Getter(onMethod = @__({ @JsonIgnore }))
	@Setter(onMethod = @__({ @JsonProperty }))
	private byte[] physicalAddress;
	
	public TwiAddress() {
	}
	
	public TwiAddress(String address) {
		physicalAddress=address.getBytes();
	}
	
	public String getPhysicalAddressAsString() {
		return new String(physicalAddress);
	}
	

	
}
