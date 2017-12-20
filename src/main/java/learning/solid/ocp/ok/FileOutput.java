package learning.solid.ocp.ok;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutput implements LogOutput {

	@Override
	public void emit(String message) {
		try (FileOutputStream fop = new FileOutputStream(new File("log.log"))) {
			fop.write(message.getBytes());
			fop.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
