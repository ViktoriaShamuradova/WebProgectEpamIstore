package by.epamtc.shamuradova.ishop.bean.entity;

/**
 * Класс-сущность, котоая представляет собой информацию из таблицы producers(представляет собой назвоние производителей) в базе данных
 * Параметризируется по типу Integer ключа id в базе данных
 * 
 * An entity class that represents information from the producers table in the database
 * Parameterized by type Integer of the id key in the database
 * 
 * @author Виктория Шамурадова 2020
 */


public class Producer extends AbstractEntity<Integer> {

	private static final long serialVersionUID = 502280979690366206L;
	
	private String name;

	public Producer() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Producer other = (Producer) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Producer [name=" + name + "]";
	}

}
