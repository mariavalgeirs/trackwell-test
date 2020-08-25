package trackwell.interview.test.model;

import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class Position {
	
	@NotNull(message = "Date is mandatory")
	private Date date;	 

	@JsonProperty(access = Access.READ_ONLY)
	private Date recievedDate;

	@NotNull(message = "Latitude is mandatory")
	private double latitude;

	@NotNull(message = "Longitude is mandatory")
	private double longitude;

	@NotNull(message = "Speed is mandatory")
	@DecimalMin("0.0") 
	private double speed;


	public Position() {
		this.recievedDate = new Date();	 
	}	

	public Date getDate() {
		return date;
	}

	public Date getRecievedDate() {
		return recievedDate;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getSpeed() {
		return speed;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setRecievedDate(Date date) {
		this.recievedDate = date;
	}

	public void setLatitude(double d) {
		this.latitude = d;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
}
