package learning.solid.srp.ok;

public class OxygenAlerter {
	
	public void showLowOxygenAlert(OxygenMeter oxygen) {
		System.out.println(String.format("Oxygen is low %f", oxygen.getOxygenSaturation()));
	}

}
