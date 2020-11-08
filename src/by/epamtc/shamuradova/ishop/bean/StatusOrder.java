package by.epamtc.shamuradova.ishop.bean;

public enum StatusOrder {

	NEW,
	COMPLETED,
	DELIVERED,
	EXECUTED,
	CANCELED;
	
	public String getName() {
	    return name();
	}
}
