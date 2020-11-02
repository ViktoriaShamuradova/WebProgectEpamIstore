package by.epamtc.shamuradova.ishop.constant;

import java.util.HashMap;
import java.util.Map;

public final class UserRole {

	private UserRole() {
	}

	public static final String ADMIN = "ADMIN";
	public static final String SHOPPER = "SHOPPER";

	public static final Map<String, Integer> rolesId = new HashMap<>();

	static {
		rolesId.put(ADMIN, 1);
		rolesId.put(SHOPPER, 2);
	}
}
