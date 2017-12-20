package learning.solid.ocp.ok;

public class Logger {
	private LogOutput output;

	public void configure(LogOutput output) {
		this.output = output;
	}
	
	public void log(String message) {
		output.emit(message);
	}
}
