<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.locale" var="loc" />


<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />

<div class="btn-group pl-30" role="group" aria-label="Basic example">

	<form action="controller" method="post">
		<input type="hidden" name="command" value="set_locale" /> 
		<input type="hidden" name="locale" value="ru" /> 
		<input type="hidden" name="redirect_to" value="${redirectTo}" /> 
		<button type="submit" class="btn btn-secondary">${ru_button}</button>
	</form>

	<form action="controller" method="post">
		<input type="hidden" name="command" value="set_locale" /> 
		<input type="hidden" name="locale" value="en" /> 
		<input type="hidden" name="redirect_to" value="${redirectTo}" /> 
		<button type="submit" class="btn btn-secondary">${en_button}</button>
		
	</form>
	
</div>