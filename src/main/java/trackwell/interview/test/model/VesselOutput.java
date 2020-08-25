package trackwell.interview.test.model;


/**
 * @author María Valgeirsdóttir
 * 
 * Vessel output has the position latitude and longitude represented in radians and speed is represented in meters per second 
 *
 */
public class VesselOutput {
	
	Vessel VesselObject;
	
	Position PositionObject;

	public Vessel getVessel() {
		return VesselObject;
	}

	public Position getPosition() {
		return PositionObject;
	} 

	public void setVessel(Vessel vesselObject) {
		this.VesselObject = vesselObject;
	}

	public void setPosition(Position positionObject) {
		this.PositionObject = positionObject;
	}
}
