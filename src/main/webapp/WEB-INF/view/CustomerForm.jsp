<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/Common.css">
<div class="header">CRM - Customer Relationship Manager</div>

<h1 style="font-size: 22px">Save Customer</h1>

<form:form action="processForm?custId=${customer.id}" modelAttribute="customer">
	<table class="customerForm">
		<tr>
			<td>First name:</td>
			<td><form:input cssClass="customerFormInput" path="firstName" /></td>
		</tr>
		<tr>
			<td>Last name:</td>
			<td><form:input cssClass="customerFormInput" path="lastName" /></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><form:input cssClass="customerFormInput" path="email" /></td>
		</tr>
		<tr>
			<td></td>
			<td><input class="customerButton" type="submit" value="Save" /></td>
		</tr>
	</table>
</form:form>

<div>
 <a href="list">Back to List</a>
</div>
</html>