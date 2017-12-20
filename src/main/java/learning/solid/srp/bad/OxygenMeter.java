package learning.solid.srp.bad;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class OxygenMeter {
	private double oxygenSaturation;
	
	public double getOxygenSaturation() {
		return oxygenSaturation;
	}
	
	public void readOxygenLevel() throws IOException {
		try (InputStream oxygenStream = new FileInputStream("oxygen.level")) {
			int raw = oxygenStream.read();
			oxygenSaturation = (double) raw / 255 * 100;
		}
	}
	
	public boolean isOxygenLow() {
		return oxygenSaturation <= 75;
	}
	
	public void showLowOxygenAlert() {
		System.out.println(String.format("Oxygen is low %f", oxygenSaturation));
	}

}
