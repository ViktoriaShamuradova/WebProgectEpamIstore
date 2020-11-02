package by.epamtc.shamuradova.ishop.service;

import by.epamtc.shamuradova.ishop.bean.RegInfo;
import by.epamtc.shamuradova.ishop.bean.entity.User;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;

public interface SignUpService {
	
	public User signUp(RegInfo regInfo)throws ServiceException;

}
