package testLog;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Example {
	private static final Logger log = LogManager.getLogger(Example.class);

	public static void main(String[] args) {
		log.info("Test log message");
	}
}
