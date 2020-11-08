package by.epamtc.shamuradova.ishop.bean.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import by.epamtc.shamuradova.ishop.bean.StatusOrder;

public class Order extends AbstractEntity<Integer> {

	private static final long serialVersionUID = 4034038614681077648L;
	
	private int idUser;
	private List<OrderItem> orderItems;
	private Date created;
	private StatusOrder status;

	public Order() {

	}

	public Order(int idUser, Date created) {
		super();
		this.idUser = idUser;
		this.created = created;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	public StatusOrder getStatus() {
		return status;
	}

	public void setStatus(StatusOrder status) {
		this.status = status;
	}

	public BigDecimal getTotalSum() {
		BigDecimal sum = BigDecimal.ZERO;
		if (orderItems != null || !orderItems.isEmpty()) {
			for (OrderItem item : orderItems) {
				sum = sum.add(item.getModel().getPrice().multiply(BigDecimal.valueOf(item.getCount())));

			}
		}
		return sum;
	}

	public int getTotalCount() {
		int count = 0;
		if (orderItems != null || !orderItems.isEmpty()) {
			for (OrderItem item : orderItems) {
				count = count + item.getCount();
			}
		}
		return count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + idUser;
		result = prime * result + ((orderItems == null) ? 0 : orderItems.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (idUser != other.idUser)
			return false;
		if (orderItems == null) {
			if (other.orderItems != null)
				return false;
		} else if (!orderItems.equals(other.orderItems))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [idUser=" + idUser + ", orderItems=" + orderItems + ", created=" + created + ", status=" + status
				+ "]";
	}

}
