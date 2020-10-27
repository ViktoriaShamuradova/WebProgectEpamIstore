package by.epamtc.shamuradova.ishop.service.exception;

//наследуется от IllegalArgumentException, потому что предены неправильные параметры для поиска объекта
//если не найдет объект, запрашиваемый пользователем, выбрасывается данное исклюение и устанавливается ошибка 404 "resource not found"

public class ResourceNotFoundServiceException extends IllegalArgumentException {

	public ResourceNotFoundServiceException(String message, Throwable e) {
		super(message, e);
	}

	public ResourceNotFoundServiceException(String message) {
		super(message);
	}

	public ResourceNotFoundServiceException(Throwable e) {
		super(e);
	}

}
