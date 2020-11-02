package by.epamtc.shamuradova.ishop.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epamtc.shamuradova.ishop.controller.command.impl.AddToCartCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.AddUserInBlackListCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.AdminPageCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.AllModelsForAdminCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.AllUsersCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.BlackListCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.CartPageCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.DeleteUserFromBlackListCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.EnterPageCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.ErrorPageCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.FormEditModelCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.FormOrderCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.ImageByModelIdCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.IncreaseCountOfModelPerUnitCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.ModelsByCategoryCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.ModelsMoreCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.MoreOrdersCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.MyOrdersCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.OrderDetailesPageCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.ReduceCountOfModelPerUnitCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.RegistrationPageCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.SaveEditModelCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.SaveNewModelCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.SetLocaleCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.ShopperPageCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.SignInCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.SignOutCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.SignUpShopperCommand;
import by.epamtc.shamuradova.ishop.controller.command.impl.UsersByRoleCommand;

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
		commands.put(ParameterNameCommand.ALL_MODELS_OR_BY_CATEGORY, new ModelsByCategoryCommand());
		commands.put(ParameterNameCommand.ENTER_PAGE, new EnterPageCommand());
		commands.put(ParameterNameCommand.ADD_TO_CART, new AddToCartCommand());
		commands.put(ParameterNameCommand.CART_PAGE, new CartPageCommand());
		commands.put(ParameterNameCommand.SIGN_OUT, new SignOutCommand());
		commands.put(ParameterNameCommand.REDUCE_COUNT_OF_GOODS_PER_UNIT, new ReduceCountOfModelPerUnitCommand());
		commands.put(ParameterNameCommand.INCREASE_COUNT_OF_GOODS_PER_UNIT, new IncreaseCountOfModelPerUnitCommand());
		commands.put(ParameterNameCommand.FORM_ORDER, new FormOrderCommand());
		commands.put(ParameterNameCommand.CREATE_ORDER, new OrderDetailesPageCommand());
		commands.put(ParameterNameCommand.MY_ORDERS, new MyOrdersCommand());
		commands.put(ParameterNameCommand.LOAD_MORE_ORDERS, new MoreOrdersCommand());
		commands.put(ParameterNameCommand.GET_IMAGE_BY_MODEL_ID, new ImageByModelIdCommand());

		// admin
		commands.put(ParameterNameCommand.BLACK_LIST, new BlackListCommand());
		commands.put(ParameterNameCommand.DELETE_USER_FROM_BLACK_LIST, new DeleteUserFromBlackListCommand());
		commands.put(ParameterNameCommand.ALL_USERS, new AllUsersCommand());
		commands.put(ParameterNameCommand.ADD_TO_BLACK_LIST, new AddUserInBlackListCommand());
		commands.put(ParameterNameCommand.ALL_MODELS_FOR_ADMIN, new AllModelsForAdminCommand());
		commands.put(ParameterNameCommand.FORM_EDITING_MODEL, new FormEditModelCommand());
		commands.put(ParameterNameCommand.SAVE_EDIT_MODEL, new SaveEditModelCommand());
		commands.put(ParameterNameCommand.SAVE_NEW_MODEL, new SaveNewModelCommand());
		
		commands.put(ParameterNameCommand.USERS_BY_ROLE, new UsersByRoleCommand());
		
		
		
		commands.put(ParameterNameCommand.LOAD_MORE_MODELS, new ModelsMoreCommand());
	}

	public Command getCommand(String commandName) {
		commandName = commandName.toUpperCase();

		ParameterNameCommand valueName = ParameterNameCommand.valueOf(commandName);
		Command command = commands.get(valueName);
		return command;
	}
}
