package by.epamtc.shamuradova.ishop.constant;

import java.util.HashMap;
import java.util.Map;

public final class UserStatus {

	public static final String NEW = "NEW";
	public static final String NOT_ACTIVE = "NOT ACTIVE";
	public static final String REGULAR = "REGULAR";

	public static final Map<String, Integer> statusesId = new HashMap<>();
	
	static {
		statusesId.put(NEW, 1);
		statusesId.put(NOT_ACTIVE, 2);
		statusesId.put(REGULAR, 3);
	}

	private UserStatus() {

	}

}
