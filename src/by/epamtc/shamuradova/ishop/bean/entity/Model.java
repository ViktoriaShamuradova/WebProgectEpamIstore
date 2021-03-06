package by.epamtc.shamuradova.ishop.bean.entity;

import java.math.BigDecimal;

/**
 * Класс-сущность, котоая представляет собой информацию из таблицы models(представляет сосбой модель, у которой есть название,
 * описание, цена, изображение, категория, производитель, количество) в базе данных
 * Параметризируется по типу Integer ключа id в базе данных
 * 
 * An entity class that represents information from the models(represents a model that has a name,
 * description, price, image, category, producer, count) table in the database
 * Parameterized by type Integer of the id key in the database
 * 
 * @author Виктория Шамурадова 2020
 */

public class Model extends AbstractEntity<Integer> {

	private static final long serialVersionUID = 8928391057379672914L;

	private String name;
	private String description;
	private BigDecimal price;
	private String imageLink;
	private String category;
	private String producer;
	private int count;

	public Model() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + count;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + getId();
		result = prime * result + ((imageLink == null) ? 0 : imageLink.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((producer == null) ? 0 : producer.hashCode());
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
		Model other = (Model) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (count != other.count)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (imageLink == null) {
			if (other.imageLink != null)
				return false;
		} else if (!imageLink.equals(other.imageLink))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (producer == null) {
			if (other.producer != null)
				return false;
		} else if (!producer.equals(other.producer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Model [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", imageLink=" + imageLink + ", category=" + category + ", producer=" + producer + ", count=" + count
				+ "]";
	}

}
