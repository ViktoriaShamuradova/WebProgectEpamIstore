package by.epamtc.shamuradova.ishop.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import by.epamtc.shamuradova.ishop.bean.entity.Model;
import by.epamtc.shamuradova.ishop.constant.ConstantsShopCart;
import by.epamtc.shamuradova.ishop.service.exception.ValidationException;

/**
 * Класс, представляющий корзину пользователя
 * 
 * Class representing the user's cart
 *
 * @author Victoria Shamuradova 2020
 */
public class ShopCart implements Serializable {

	private static final long serialVersionUID = 4905506574371844621L;
	/**
	 * shopCartItems are stored by ShopCartItem. Integer - model id ShopCartItem -
	 * object containing Model and its quantity
	 */
	private Map<Integer, ShopCartItem> shopCartItems;
	/**
	 * sum of cart
	 */
	private BigDecimal totalSum;

	/**
	 * total number of models
	 */
	private int totalCount;

	public ShopCart() {
		shopCartItems = new HashMap<>();
		totalSum = new BigDecimal(0);
	}

	public ShopCart(Map<Integer, ShopCartItem> shopCartItems, BigDecimal totalSum, int totalCount) {
		super();
		this.shopCartItems = shopCartItems;
		this.totalSum = totalSum;
		this.totalCount = totalCount;
	}

	public boolean containsIdModel(int idModel) {
		return shopCartItems.containsKey(idModel);
	}

	public ShopCartItem getShopCartItem(int idModel) {
		return shopCartItems.get(idModel);
	}

	public boolean isEmpty() {
		return shopCartItems.isEmpty();
	}

	public void removeModel(int idModel, int count) {
		ShopCartItem item = shopCartItems.get(idModel);
		if (item.getCount() > count) {
			item.setCount(item.getCount() - count);
		} else {
			shopCartItems.remove(idModel);
		}
		refreshData();

	}

	public Collection<ShopCartItem> getShopCartItems() {
		return shopCartItems.values();
	}

	public void setShopCartItems(Map<Integer, ShopCartItem> shopCartItems) {
		this.shopCartItems = shopCartItems;
	}

	public BigDecimal getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(BigDecimal totalSum) {
		this.totalSum = totalSum;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public void addShopCartItem(Model model, int count) {
		validateShoppingCartSize();
		ShopCartItem item = shopCartItems.get(model.getId());

		if (item == null) {
			validateModelsCount(count);
			item = new ShopCartItem(model, count);
			shopCartItems.put(model.getId(), item);
		} else {
			validateModelsCount(item.getCount() + count);
			item.setCount(item.getCount() + count);
		}
		refreshData();
	}

	private void validateModelsCount(int count) {
		if (count >= ConstantsShopCart.MAX_COUNT_OF_ONE_MODEL) {
			throw new ValidationException("Limit for model count reached");
		}

	}

	private void validateShoppingCartSize() {
		if (shopCartItems.size() >= ConstantsShopCart.MAX_DIFFERENT_MODELS_IN_SHOPPING_CART) {
			throw new ValidationException("Limit for Shopping cart size reached");
		}

	}

	public void addShopCartItem(ShopCartItem item) {

		if (shopCartItems.containsValue(item)) {
			ShopCartItem itemBeforeUpdate = shopCartItems.get(item.getModel().getId());
			itemBeforeUpdate.setCount(itemBeforeUpdate.getCount() + item.getCount());
			shopCartItems.put(item.getModel().getId(), itemBeforeUpdate);
		} else {
			shopCartItems.put(item.getModel().getId(), item);
		}
		refreshData();
	}

	private void refreshData() {
		totalCount = 0;
		totalSum = BigDecimal.ZERO;
		for (ShopCartItem item : getShopCartItems()) {
			totalCount = totalCount + item.getCount();
			totalSum = totalSum.add(item.getModel().getPrice().multiply(BigDecimal.valueOf(item.getCount())));

		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((shopCartItems == null) ? 0 : shopCartItems.hashCode());
		result = prime * result + totalCount;
		result = prime * result + ((totalSum == null) ? 0 : totalSum.hashCode());
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
		ShopCart other = (ShopCart) obj;
		if (shopCartItems == null) {
			if (other.shopCartItems != null)
				return false;
		} else if (!shopCartItems.equals(other.shopCartItems))
			return false;
		if (totalCount != other.totalCount)
			return false;
		if (totalSum == null) {
			if (other.totalSum != null)
				return false;
		} else if (!totalSum.equals(other.totalSum))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ShopCart [shopCartItems=" + shopCartItems + ", totalSum=" + totalSum + ", totalCount=" + totalCount
				+ "]";
	}
}
