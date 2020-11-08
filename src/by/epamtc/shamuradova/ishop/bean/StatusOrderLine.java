package by.epamtc.shamuradova.ishop.bean;

import java.util.NavigableSet;
import java.util.TreeSet;

public class StatusOrderLine {

	private NavigableSet<StatusOrder> statuses = new TreeSet<>();

	public StatusOrderLine() {
		statuses.add(StatusOrder.NEW);
		statuses.add(StatusOrder.COMPLETED);
		statuses.add(StatusOrder.EXECUTED);
		statuses.add(StatusOrder.DELIVERED);
		statuses.add(StatusOrder.CANCELED);
		
	}

	public StatusOrder nextStatus(String status) {
		String st = status.toUpperCase();
		if (st.equals(StatusOrder.EXECUTED.toString()) || st.equals(StatusOrder.CANCELED.toString())) {
			return null;
		}
		return statuses.higher(StatusOrder.valueOf(st));
	}

	public StatusOrder nextStatus(StatusOrder status) {
		if (status == StatusOrder.EXECUTED || status == StatusOrder.CANCELED) {
			return null;
		}
		return statuses.higher(status);
	}

	public StatusOrder previousStatus(StatusOrder status) {
		if (status == StatusOrder.NEW || status == StatusOrder.CANCELED) {
			return null;
		}
		return statuses.lower(status);
	}


}
