package by.epamtc.shamuradova.ishop.bean;

public class OrderItem extends AbstractEntity<Integer>{

	private static final long serialVersionUID = -365373848626193474L;
	private int idOrder;
	private Model model;
	private int count;
	
	public OrderItem() {
		
	}

	public OrderItem(Model model, int count) {
		super();
		this.model = model;
		this.count = count;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + count;
		result = prime * result + idOrder;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
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
		OrderItem other = (OrderItem) obj;
		if (count != other.count)
			return false;
		if (idOrder != other.idOrder)
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderItem [idOrder=" + idOrder + ", model=" + model + ", count=" + count + "]";
	}
	

}
