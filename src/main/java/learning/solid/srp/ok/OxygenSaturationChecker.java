package learning.solid.srp.ok;

public class OxygenSaturationChecker {
	
	public boolean isOxygenLow(OxygenMeter oxygen) {
		return oxygen.getOxygenSaturation() <= 75;
	}
}
