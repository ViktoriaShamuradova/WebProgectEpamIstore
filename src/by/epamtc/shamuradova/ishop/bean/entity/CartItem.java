package by.epamtc.shamuradova.ishop.bean.entity;

import java.io.Serializable;

/**
 * Корзина, в которой хранится модель и ее кол-во  и id из таблицы всех корзин, представляет собой таблицу из бд
 * 
 * @author Шамурадова Виктория 2020
 */
//корзина же может содержать лист моделей. Думай!!!исправь
public class CartItem extends AbstractEntity<Integer> {

	private static final long serialVersionUID = 4871458939014195841L;
	
	
	private int count;
	private int modelId;
	private int cartId;
	
	public CartItem() {	}


	public CartItem(int count, int modelId, int cartId) {
		super();
		this.count = count;
		this.modelId = modelId;
		this.cartId = cartId;
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
		int result = super.hashCode();
		result = prime * result + cartId;
		result = prime * result + count;
		result = prime * result + modelId;
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
		CartItem other = (CartItem) obj;
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
		return "CartItem [count=" + count + ", modelId=" + modelId + ", cartId=" + cartId + "]";
	}

}
