<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>

	<%@ include file="header.jsp"%>


	<div class="container-fluid">
		<div class="row">
			<%@ include file="admin_navbar.jsp"%>
			
			

			<div class="container">
					<h4>Edit Model</h4>
					
					<c:choose>
						<c:when test="${not empty model}">
			       			<form action="controller" method="post">
								<input type="hidden" name="command" value="save_edit_model" />
								<input type="hidden" required name="modelId" value="${model.id}" />
								<div class="row">
									<div class="col">
										<label for="formGroupExampleInput">Name</label>
										<input type="text" class="form-control" required name = "modelName" placeholder="Name" value="${model.name }">
									</div>
							
									<div class="col">
										<label for="formGroupExampleInput">Price</label>
										<input type="number" required min="10" max="9999" class="form-control" name = "modelPrice" placeholder="Price" value="${model.price }">
									</div>
							
									<div class="col">
										<label for="formGroupExampleInput">Count</label>
										<input type="number" required min="0" max="100" class="form-control" name = "modelCount" placeholder="Count" value="${model.count }">
									</div>	
								</div>
						
								<div class="form-group">
		    						<label for="exampleFormControlSelect2">Description</label>
		   							<input type="text" class="form-control" required name = "modelDescription" placeholder="Description" value="${model.description }">	
		  						</div>
  						
		  						<div class="row">
									<div class="col">
										<label for="formGroupExampleInput">Category</label>
										<input type="text" class="form-control" required name="modelCategory" placeholder="Category" value="${model.category }">
									</div>
									
									<div class="col">
										<label for="formGroupExampleInput">Producer</label>
										<input type="text" class="form-control" required name = "modelProducer" placeholder="Producer" value="${model.producer }">
									</div>
										
								</div>
						
								<div class="row mx-md-n5" style="margin-top: 20px;">
		  							<div class="col px-md-5"><a href="controller?command=ALL_MODELS_FOR_ADMIN"><button type="button" class="btn btn-light">Back</button></a></div>
		  							<div class="col px-md-5"><button type="submit" class="btn btn-dark">Save</button></div>
								</div>
						
							</form>       
			       		       
			    		</c:when>
			    
			    		<c:otherwise>
			        		
			        		<form action="controller" method="post">
								<input type="hidden" name="command" value="save_new_model" />
								
								<div class="row">
									<div class="col">
										<label for="formGroupExampleInput">Name</label>
										<input type="text" class="form-control" required name = "modelName" placeholder="Name" value="">
									</div>
							
									<div class="col">
										<label for="formGroupExampleInput">Price</label>
										<input type="number" required min="10" max="9999" class="form-control" name = "modelPrice" placeholder="Price" value="">
									</div>
							
									<div class="col">
										<label for="formGroupExampleInput">Count</label>
										<input type="number" required min="0" max="100" class="form-control" name = "modelCount" placeholder="Count" value="">
									</div>	
								</div>
						
								<div class="form-group">
		    						<label for="exampleFormControlSelect2">Description</label>
		   							<input type="text" class="form-control" required name = "modelDescription" placeholder="Description" value="">	
		  						</div>
  						
		  						<div class="row">
									<div class="col">
										<label for="formGroupExampleInput">Category</label>
										<input type="text" class="form-control" required name="modelCategory" placeholder="Category" value="">
									</div>
									
									<div class="col">
										<label for="formGroupExampleInput">Producer</label>
										<input type="text" class="form-control" required name = "modelProducer" placeholder="Producer" value="">
									</div>
										
								</div>
						
								<div class="row mx-md-n5" style="margin-top: 20px;">
		  							<div class="col px-md-5"><a href="controller?command=ALL_MODELS_FOR_ADMIN"><button type="button" class="btn btn-light">Back</button></a></div>
		  							<div class="col px-md-5"><button type="submit" class="btn btn-dark">Save</button></div>
								</div>
						
							</form>       
			       		       
			        		
			   			</c:otherwise>
					</c:choose>
					
					


			</div>			
		</div>
	</div>
</body>