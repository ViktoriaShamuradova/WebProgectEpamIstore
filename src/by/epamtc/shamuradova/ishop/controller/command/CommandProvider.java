package by.epamtc.shamuradova.ishop.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epamtc.shamuradova.ishop.controller.command.impl.AdminPageCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.ErrorPageCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.RegistrationPageCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.SetLocaleCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.ShopperPageCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.SignInCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.SignUpShopperCommand;

public class CommandProvider {

	private Map<ParameterName, Command> commands = new HashMap<>();

	
	
	public CommandProvider() {
		
		commands.put(ParameterName.SIGN_IN, new SignInCommand());
		commands.put(ParameterName.SET_LOCALE, new SetLocaleCommand());
		commands.put(ParameterName.REGISTRATION_PAGE, new RegistrationPageCommand());
		commands.put(ParameterName.SAVE_NEW_SHOPPER, new SignUpShopperCommand());
		commands.put(ParameterName.GET_SHOPPER_PAGE, new ShopperPageCommand());
		commands.put(ParameterName.GET_ERROR_PAGE, new ErrorPageCommand());
		commands.put(ParameterName.GET_ADMIN_PAGE, new AdminPageCommand());
	}

	
	
	public Command getCommand(String commandName) {
		commandName = commandName.toUpperCase();

		ParameterName valueName = ParameterName.valueOf(commandName);
		Command command = commands.get(valueName);
		return command;
	}

}
