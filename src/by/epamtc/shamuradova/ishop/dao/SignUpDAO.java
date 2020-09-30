package by.epamtc.shamuradova.ishop.dao;

import by.epamtc.shamuradova.ishop.bean.RegInfo;
import by.epamtc.shamuradova.ishop.dao.exception.DAOException;

public interface SignUpDAO {

	public void signUp(RegInfo regInfo) throws DAOException; 

}
