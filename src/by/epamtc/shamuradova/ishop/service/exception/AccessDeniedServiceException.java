package by.epamtc.shamuradova.ishop.service.exception;

//наследуется от IllegalArgumentException, потому что предены неправильные параметры для поиска объекта
//если не имеет доступ к данному объекту, то выбрасываем данное исключение и устанавливаем 403 
public class AccessDeniedServiceException extends IllegalArgumentException {

	public AccessDeniedServiceException(String message, Throwable e) {
		super(message, e);
	}

	public AccessDeniedServiceException(String message) {
		super(message);
	}

	public AccessDeniedServiceException(Throwable e) {
		super(e);
	}

}
