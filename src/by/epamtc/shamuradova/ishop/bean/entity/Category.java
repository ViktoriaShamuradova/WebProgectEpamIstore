package by.epamtc.shamuradova.ishop.bean.entity;

public class Category extends AbstractEntity<Integer> {
	
	private static final long serialVersionUID = -6588428119548273094L;
	
	private String name;
	private int modelCount;
	private String url;

	public Category() {}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Category [name=" + name + ", modelCount=" + modelCount + ", url=" + url + "]";
	}

}