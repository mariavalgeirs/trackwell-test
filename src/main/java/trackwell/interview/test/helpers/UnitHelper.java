package trackwell.interview.test.helpers;

import trackwell.interview.test.model.VesselInput;
import trackwell.interview.test.model.VesselOutput;

public class UnitHelper {
	
	public static double degreeToRadians(double d) {
		
		return Math.toRadians(d);
		
	}

	// 1 knot is equal to 0.514 m/sek
	private static double knotsToMetersPerSecond(double speed) {
		return speed * 0.514;
	}	
	
	/**
	 * @author María Valgeirsdóttir
	 * @param externalVessel - Vessel information from input
	 * @return output - Vessel information from output with calculated position information and speed
	 *
	 */	
	public static VesselOutput createVesselOutput(VesselInput externalVessel) {
		
		VesselOutput output = new VesselOutput();
		
		output.setVessel(externalVessel.getVessel());
		output.setPosition(externalVessel.getPosition());		
		
		output.getPosition().setLatitude(degreeToRadians(output.getPosition().getLatitude()));
		output.getPosition().setLongitude(degreeToRadians(output.getPosition().getLongitude()));
		output.getPosition().setSpeed(knotsToMetersPerSecond(output.getPosition().getSpeed()));
		
		return output;
	}

}
