package trackwell.interview.test.model;

import javax.validation.Valid;

/**
 * @author María Valgeirsdóttir
 * 
 * Vessel input has the position latitude and longitude represented in degrees and speed is represented in knots 
 *
 */
public class VesselInput {
	
	@Valid
	Vessel vessel;
		
	@Valid 
	Position position;

	public Vessel getVessel() {
		return vessel;
	}

	public Position getPosition() {
		return position;
	}

	public void setVessel(Vessel vesselObject) {
		this.vessel = vesselObject;
	}

	public void setPosition(Position positionObject) {
		this.position = positionObject;
	}
}