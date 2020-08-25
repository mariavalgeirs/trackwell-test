package trackwell.interview.test.model;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class Vessel {
	
	@NotBlank(message = "Vessel name is mandatory")
	private String name;
	
	@NotBlank(message = "Country is mandatory")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String country;

	public String getName() {
		return name;
	}

	public String getCountry() {
		return country;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
