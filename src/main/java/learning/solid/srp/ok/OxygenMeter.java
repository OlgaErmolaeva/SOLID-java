package learning.solid.srp.ok;

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
}
