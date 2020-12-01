package by.epamtc.shamuradova.ishop.bean;

import java.io.Serializable;

import by.epamtc.shamuradova.ishop.bean.entity.Model;

/**
 * 
 * Объект этого класса представляет собой пару: модель и его количество. Объект
 * этого класса является частью корзины ShopCart
 * 
 * An object of this class represents a pair: a model and its quantity. An
 * object this class is part of the ShopCart
 * 
 * @author Victoria Shamuradova 2020
 */
public class ShopCartItem implements Serializable {

	private static final long serialVersionUID = 5720727113433815909L;

	private Model model;
	private int count;

	public ShopCartItem() {
	}

	public ShopCartItem(Model model, int count) {
		super();
		this.model = model;
		this.count = count;
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
		int result = 1;
		result = prime * result + count;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
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
		ShopCartItem other = (ShopCartItem) obj;
		if (count != other.count)
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
		return "ShopCartItem [model=" + model + ", count=" + count + "]";
	}
}
