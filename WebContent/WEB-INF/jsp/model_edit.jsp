<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.locale" var="loc" />

<fmt:message bundle="${loc}" key="message.editModel" var="editModelLoc" />
<fmt:message bundle="${loc}" key="attribute.id" var="idLoc" />
<fmt:message bundle="${loc}" key="attribute.name" var="nameLoc" />
<fmt:message bundle="${loc}" key="attribute.price" var="priceLoc" />
<fmt:message bundle="${loc}" key="attribute.producer" var="producerLoc" />
<fmt:message bundle="${loc}" key="attribute.category" var="categoryLoc" />
<fmt:message bundle="${loc}" key="attribute.count" var="countLoc" />
<fmt:message bundle="${loc}" key="attribute.description" var="descriptionLoc" />
<fmt:message bundle="${loc}" key="button.back" var="backLoc" />
<fmt:message bundle="${loc}" key="button.save" var="saveLoc" />

</head>
<body>

	<%@ include file="header.jsp"%>


	<div class="container-fluid">
		<div class="row">
			<%@ include file="admin_navbar.jsp"%>
			
			

			<div class="container">
					<h4><c:out value="${editModelLoc}"></c:out></h4>
					
					<c:choose>
						<c:when test="${not empty model}">
			       			<form action="controller" method="post">
								<input type="hidden" name="command" value="save_edit_model" />
								<input type="hidden" required name="modelId" value="${model.id}" />
								<div class="row">
									<div class="col">
										<label for="formGroupExampleInput"><c:out value="${nameLoc}"/></label>
										<input type="text" class="form-control" required name = "modelName" placeholder="Name" value="${model.name }">
									</div>
							
									<div class="col">
										<label for="formGroupExampleInput"><c:out value="${priceLoc}"/></label>
										<input type="number" required min="10" max="9999" class="form-control" name = "modelPrice" placeholder="Price" value="${model.price }">
									</div>
							
									<div class="col">
										<label for="formGroupExampleInput"><c:out value="${countLoc}"/></label>
										<input type="number" required min="0" max="100" class="form-control" name = "modelCount" placeholder="Count" value="${model.count }">
									</div>	
								</div>
						
								<div class="form-group">
		    						<label for="exampleFormControlSelect2"><c:out value="${descriptionLoc}"/></label>
		   							<input type="text" class="form-control" required name = "modelDescription" placeholder="Description" value="${model.description }">	
		  						</div>
  						
		  						<div class="row">
									<div class="col">
										<label for="formGroupExampleInput"><c:out value="${categoryLoc}"/></label>
										<input type="text" class="form-control" required name="modelCategory" placeholder="Category" value="${model.category }">
									</div>
									
									<div class="col">
										<label for="formGroupExampleInput"><c:out value="${producerLoc}"/></label>
										<input type="text" class="form-control" required name = "modelProducer" placeholder="Producer" value="${model.producer }">
									</div>
										
								</div>
						
								<div class="row mx-md-n5" style="margin-top: 20px;">
		  							<div class="col px-md-5"><a href="controller?command=ALL_MODELS_OR_BY_CATEGORY"><button type="button" class="btn btn-light"><c:out value="${backLoc}"/></button></a></div>
		  							<div class="col px-md-5"><button type="submit" class="btn btn-dark"><c:out value="${saveLoc}"/></button></div>
								</div>
						
							</form>       
			       		       
			    		</c:when>
			    
			    		<c:otherwise>
			        		
			        		<form action="controller" method="post">
								<input type="hidden" name="command" value="save_new_model" />
								
								<div class="row">
									<div class="col">
										<label for="formGroupExampleInput"><c:out value="${nameLoc}"/></label>
										<input type="text" class="form-control" required name = "modelName" placeholder="Name" value="">
									</div>
							
									<div class="col">
										<label for="formGroupExampleInput"><c:out value="${priceLoc}"/></label>
										<input type="number" required min="10" max="9999" class="form-control" name = "modelPrice" placeholder="Price" value="">
									</div>
							
									<div class="col">
										<label for="formGroupExampleInput"><c:out value="${countLoc}"/></label>
										<input type="number" required min="0" max="100" class="form-control" name = "modelCount" placeholder="Count" value="">
									</div>	
								</div>
						
								<div class="form-group">
		    						<label for="exampleFormControlSelect2"><c:out value="${descriptionLoc}"/></label>
		   							<input type="text" class="form-control" required name = "modelDescription" placeholder="Description" value="">	
		  						</div>
  						
		  						<div class="row">
									<div class="col">
										<label for="formGroupExampleInput"><c:out value="${categoryLoc}"/></label>
										<input type="text" class="form-control" required name="modelCategory" placeholder="Category" value="">
									</div>
									
									<div class="col">
										<label for="formGroupExampleInput"><c:out value="${producerLoc}"/></label>
										<input type="text" class="form-control" required name = "modelProducer" placeholder="Producer" value="">
									</div>
										
								</div>
						
								<div class="row mx-md-n5" style="margin-top: 20px;">
		  							<div class="col px-md-5"><a href="controller?command=ALL_MODELS_OR_BY_CATEGORY"><button type="button" class="btn btn-light"><c:out value="${backLoc}"/></button></a></div>
		  							<div class="col px-md-5"><button type="submit" class="btn btn-dark"><c:out value="${saveLoc}"/></button></div>
								</div>
						</form>       
				        		
			   			</c:otherwise>
					</c:choose>
				

			</div>			
		</div>
	</div>
</body>