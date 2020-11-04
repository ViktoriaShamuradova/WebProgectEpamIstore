package by.epamtc.shamuradova.ishop.bean;

import java.io.Serializable;

public class CartContent implements Serializable{

	private int cartId;
	private int modelId;
	private int count;
	
	public CartContent() {	
	}
	

	public CartContent(int cartId, int modelId, int count) {
		super();
		this.cartId = cartId;
		this.modelId = modelId;
		this.count = count;
	}


	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
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
		int result = 1;
		result = prime * result + cartId;
		result = prime * result + count;
		result = prime * result + modelId;
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
		CartContent other = (CartContent) obj;
		if (cartId != other.cartId)
			return false;
		if (count != other.count)
			return false;
		if (modelId != other.modelId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CartContent [cartId=" + cartId + ", modelId=" + modelId + ", count=" + count + "]";
	}
	
}
