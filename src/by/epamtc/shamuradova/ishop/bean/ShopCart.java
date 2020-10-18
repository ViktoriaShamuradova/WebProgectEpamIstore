package by.epamtc.shamuradova.ishop.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShopCart implements Serializable {

	private Map<Integer, ShopCartItem> shopCartItems;
	private int totalSum;
	private int totalCount;

	public ShopCart() {
		shopCartItems = new HashMap();
	}

	public ShopCart(Map<Integer, ShopCartItem> shopCartItems, int totalSum, int totalCount) {
		super();
		this.shopCartItems = shopCartItems;
		this.totalSum = totalSum;
		this.totalCount = totalCount;
	}

	public Collection<ShopCartItem> getShopCartItems() {
		return shopCartItems.values();
	}

	public void setShopCartItems(Map<Integer, ShopCartItem> shopCartItems) {
		this.shopCartItems = shopCartItems;
	}

	public int getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(int totalSum) {
		this.totalSum = totalSum;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public void addShopCartItem(Model model, int count) {
	}
	
	public void addShopCartItem(ShopCartItem item) {
		shopCartItems.put(item.getModel().getId(), item);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((shopCartItems == null) ? 0 : shopCartItems.hashCode());
		result = prime * result + totalCount;
		result = prime * result + totalSum;
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
		if (totalSum != other.totalSum)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ShopCart [shopCartItems=" + shopCartItems + ", totalSum=" + totalSum + ", totalCount=" + totalCount
				+ "]";
	}

}
