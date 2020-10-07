package by.epamtc.shamuradova.ishop.constant;

import java.util.regex.Pattern;

public final class PatternContainer {

	public static final Pattern PASSWORD_PATTERN= Pattern.compile("[0-9a-zA-Zа-яА-Я!@#$%^&*]{6,20}");
	public static final Pattern LOGIN_PATTERN = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]{1,25}$"); //^[a-zA-Zа-яА-ЯЁё][0-9a-zA-ZА-Яа-яЁё]{6,20}$
	
	private PatternContainer() {}

}
