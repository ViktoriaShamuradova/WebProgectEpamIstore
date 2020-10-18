package by.epamtc.shamuradova.ishop.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epamtc.shamuradova.ishop.controller.command.impl.AddToCartCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.AdminPageCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.AllModelsAllCategoriesCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.AllModelsMoreCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.CartPageCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.EnterPageCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.ErrorPageCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.ModalPageModalCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.ModelsByCategoryCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.RegistrationPageCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.SetLocaleCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.ShopperPageCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.SignInCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.SignUpShopperCommand;

public class CommandProvider {

	private Map<ParameterNameCommand, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(ParameterNameCommand.SIGN_IN, new SignInCommand());
		commands.put(ParameterNameCommand.SET_LOCALE, new SetLocaleCommand());
		commands.put(ParameterNameCommand.REGISTRATION_PAGE, new RegistrationPageCommand());
		commands.put(ParameterNameCommand.SAVE_NEW_SHOPPER, new SignUpShopperCommand());
		commands.put(ParameterNameCommand.GET_SHOPPER_PAGE, new ShopperPageCommand());
		commands.put(ParameterNameCommand.GET_ERROR_PAGE, new ErrorPageCommand());
		commands.put(ParameterNameCommand.GET_ADMIN_PAGE, new AdminPageCommand());
		commands.put(ParameterNameCommand.GET_MAIN_ALL_MODELS_PAGE, new AllModelsAllCategoriesCommand());
		commands.put(ParameterNameCommand.LOAD_MORE_MODELS, new AllModelsMoreCommand());
		commands.put(ParameterNameCommand.MODELS_BY_CATEGORY, new ModelsByCategoryCommand());
		commands.put(ParameterNameCommand.ENTER_PAGE, new EnterPageCommand());
		commands.put(ParameterNameCommand.MODEL_PAGE_MODAL, new ModalPageModalCommand());
		commands.put(ParameterNameCommand.ADD_TO_CART, new AddToCartCommand());
		commands.put(ParameterNameCommand.CART_PAGE, new CartPageCommand());
	}	
	
	public Command getCommand(String commandName) {
		commandName = commandName.toUpperCase();

		ParameterNameCommand valueName = ParameterNameCommand.valueOf(commandName);
		Command command = commands.get(valueName);
		return command;
	}
}
