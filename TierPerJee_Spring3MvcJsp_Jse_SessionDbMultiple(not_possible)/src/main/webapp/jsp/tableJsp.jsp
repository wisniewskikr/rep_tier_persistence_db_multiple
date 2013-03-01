<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="${pageContext.request.contextPath}/">
<title>Hello</title>

<script type="text/javascript">

	function send(action){
		document.form.action = action;
		document.form.submit();	
	}
	
</script>

</head>

<body>
<form name="form" method="post">
<input type="hidden" id="id" name="id"/>

<div style="width: 400px; font-family: Arial; font-size: 9pt;">
	<table>
		<tr>
			<td colspan="2" style="padding-bottom: 10px;">
				<h2>Hello World - Data Base Spring 3</h2>
				<h3>Site: <b>Table</b></h3>
			</td>
		</tr>		
		<tr>
			<td colspan="2">
			
				<table border="1px;" cellpadding="0px;" cellspacing="0px;" style="width: 300px;">
					<thead>
						<tr>
							<td>Id</td>
							<td>Name</td>
							<td>Surname</td>
							<td>Actions</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${command.users}" var="user">
							<tr>					 
								<td>${user.id}</td>
								<td>${user.nameEntity.name}</td>
								<td>${user.surnameEntity.surname}</td>
								<td> 
									<a href="table/view-link/${user.id}">View</a> &nbsp;
									<a href="table/edit-link/${user.id}">Edit</a> &nbsp;
									<a href="table/delete-link/${user.id}">Delete</a> 
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
			
			</td>
		</tr>		
		<tr>
			<td colspan="2">
				<input type="button" id="create" name="create" value="Create" onclick="send('table/create-button');"/>
			</td>
		</tr>		
	</table>
</div>
</form>
</body>

</html>