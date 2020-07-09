package environmentutility.property;

import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {

	private static Properties environmentProperties = new Properties();
	
	static {
		try {
			environmentProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("environment.properties"));
        } catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
	
	public static Properties getEnvironmentProperties() {
        return environmentProperties;
	}
}
