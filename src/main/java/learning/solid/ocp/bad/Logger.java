package learning.solid.ocp.bad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Logger {

    public void log(String message) {
        switch (output) {
            case CONSOLE : {
                System.out.println(message);
                break;
            }
            case FILE : {
                try (FileOutputStream fop = new FileOutputStream(new File("log.log"))) {
                    fop.write(message.getBytes());
                    fop.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public enum LogOutput {
		CONSOLE,
		FILE

	}

	private LogOutput output;

    public void configure(LogOutput output) {
        this.output = output;
    }
}
