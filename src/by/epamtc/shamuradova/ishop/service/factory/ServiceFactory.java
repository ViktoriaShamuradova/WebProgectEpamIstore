package by.epamtc.shamuradova.ishop.service.factory;

import by.epamtc.shamuradova.ishop.service.CartService;
import by.epamtc.shamuradova.ishop.service.ModelService;
import by.epamtc.shamuradova.ishop.service.SignInService;
import by.epamtc.shamuradova.ishop.service.SignUpService;
import by.epamtc.shamuradova.ishop.service.impl.CartServiceImpl;
import by.epamtc.shamuradova.ishop.service.impl.ModelServiceImpl;
import by.epamtc.shamuradova.ishop.service.impl.SignInServiceImpl;
import by.epamtc.shamuradova.ishop.service.impl.SignUpServiceImpl;

public class ServiceFactory {

	private static final ServiceFactory INSTANCE = new ServiceFactory();

	private ModelService modelService = null;
	private SignInService signInService = null;
	private SignUpService signUpService = null;
	private CartService cartService = null;

	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return INSTANCE;
	}

	public ModelService getModelService() {

		if (modelService == null) {
			synchronized (INSTANCE) {
				if (modelService == null) {
					modelService = new ModelServiceImpl();
				}
			}
		}
		return modelService;
	}
	public CartService getCartService() {

		if (cartService == null) {
			synchronized (INSTANCE) {
				if (cartService == null) {
					cartService = new CartServiceImpl();
				}
			}
		}
		return cartService;
	}


	public SignInService getSignInService() {

		if (signInService == null) {
			synchronized (INSTANCE) {
				if (signInService == null) {
					signInService = new SignInServiceImpl();
				}
			}
		}
		return signInService;
	}

	public SignUpService getSignUpService() {

		if (signUpService == null) {
			synchronized (INSTANCE) {
				if (signUpService == null) {
					signUpService = new SignUpServiceImpl();
				}
			}
		}
		return signUpService;
	}
}
