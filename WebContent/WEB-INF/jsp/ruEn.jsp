<form action="controller" method="post">
		<input type="hidden" name="command" value="set_locale" /> 
		<input type="hidden" name="locale" value="ru" /> 
		<input type="hidden" name="redirect_to" value="/WEB-INF/jsp/registration.jsp" /> 
		<input type="submit" value="${ru_button}" />
	</form>

	<form action="controller" method="post">
		<input type="hidden" name="command" value="set_locale" /> 
		<input type="hidden" name="locale" value="en" /> 
		<input type="hidden" name="redirect_to" value="/WEB-INF/jsp/registration.jsp" /> 
		<input type="submit" value="${en_button}"  />
	</form>