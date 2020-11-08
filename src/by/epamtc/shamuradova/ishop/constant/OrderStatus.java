package by.epamtc.shamuradova.ishop.constant;

import java.util.HashMap;
import java.util.Map;

public final class OrderStatus {

	private OrderStatus() {}

	public static final String NEW = "NEW";
	public static final String AGREED = "AGREED";
	public static final String COMPLETED_WITH = "COMPLETED WITH";
	public static final String COMPLETED = "COMPLETED";
	public static final String SENT_FOR_DELIVERY = "SENT FOR DELIVERY";
	public static final String IMPLEMENTED = "IMPLEMENTED";
	public static final String CANCELED = "CANCELED";
	
	public static final Map<String, Integer> statusId = new HashMap<>();

	static {
		statusId.put(NEW, 6);
		statusId.put(AGREED, 7);
		statusId.put(COMPLETED_WITH, 8);
		statusId.put(COMPLETED, 9);
		statusId.put(SENT_FOR_DELIVERY, 10);
		statusId.put(IMPLEMENTED, 11);
		statusId.put(CANCELED, 12);
	}
	
}
