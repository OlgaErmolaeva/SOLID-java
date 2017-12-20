package learning.solid.ocp.ok;

public class ConsoleOutput implements LogOutput {

	@Override
	public void emit(String message) {
		System.out.println(message);
	}

}
