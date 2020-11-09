package by.epamtc.shamuradova.ishop.bean.entity;

import java.sql.Date;

/**
 * Класс-сущность, котоая представляет собой информацию из таблицы сarts(записи о наличии корзин у пользователей) в базе данных
 * Параметризируется по типу Integer ключа id в базе данных
 * 
 * An entity class that represents information from the сarts table in the database
 * Parameterized by type Integer of the id key in the database
 * @author Виктория Шамурадова 2020
 */

public class Cart extends AbstractEntity<Integer> {

	private static final long serialVersionUID = 3951881799324241955L;
	
	int userId;
	private Date created;
	
	public Cart() {}

	public Cart(int userId, Date created) {
		super();
		this.userId = userId;
		this.created = created;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + userId;
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
		Cart other = (Cart) obj;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cart [userId=" + userId + ", created=" + created + "]";
	}
}
