package by.epamtc.shamuradova.ishop.dao.util;

import java.util.ResourceBundle;

public class ResourceProperty {

	private ResourceBundle resourceBundle;

	public ResourceProperty(String property) {
		resourceBundle = ResourceBundle.getBundle(property);
	}

	public String getValue(String key) {
		return resourceBundle.getString(key);
	}

}
