package by.epamtc.shamuradova.ishop.bean.entity;

import java.io.Serializable;

/**
 * Абстрактный класс, родительский класс для всех классов сущностей, которые представляют сосбой таблицу из базы данных
 * У каждого дочернего класса есть поле id, которое является ключом в базе данных, но так как тип ключа может быть разный, то нужно параметризировать
 * по Т
 * 
 * Abstract class, the parent class for all entity classes that represent a table from the database
 * Each child class has an id field, which is a key in the database, but since the key type can be different, you need to parameterize
 *  
 * @author Виктория Шамурадова 2020
 */
public abstract class AbstractEntity<T> implements Serializable {
	
	private static final long serialVersionUID = -2576618638870850455L;
	
	T id;
	
	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEntity other = (AbstractEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)   )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s [id=%s]", getClass().getSimpleName(), id);
	}
}
