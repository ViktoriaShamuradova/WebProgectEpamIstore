package by.epamtc.shamuradova.ishop.bean.entity;

/**
 * Класс-сущность, котоая представляет собой информацию из таблицы сategories(у каждой категории есть имя,
 * url, по которому можно получить список моделей данной категории) в базе данных
 * Параметризируется по типу Integer ключа id в базе данных
 * 
 * An entity class that represents information from the сategories(each category has a name,
  url, where you can get a list of models in this category) table in the database
 * Parameterized by type Integer of the id key in the database
 * 
 * @author Виктория Шамурадова 2020
 */


public class Category extends AbstractEntity<Integer> {
	
	private static final long serialVersionUID = -6588428119548273094L;
	
	private String name;
	private int modelCount;
	

	public Category() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getModeltCount() {
		return modelCount;
	}


	public void setModelCount(int modelCount) {
		this.modelCount = modelCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + modelCount;
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
		Category other = (Category) obj;
		if (modelCount != other.modelCount)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "Category [name=" + name + ", modelCount=" + modelCount + "]";
	}
}
