<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/Common.css">
<div class="header">CRM - Customer Relationship Manager</div>
<input type="button" class="customerButton"
	style="width: 120px; height: 24px; margin: 10 0;" value="Add Customer"
	onCLick="location='showFormForAdd'" />
<table class="customerListForm">
	<tr>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Email Name</th>
		<th>Action</th>
	</tr>
	<c:forEach items="${customers}" var="customer">
		<tr>
			<td>${customer.firstName}</td>
			<td>${customer.lastName}</td>
			<td>${customer.email}</td>
			<td><a href="showFormForUpdate?custId=${customer.id}">Update</a>
				| <a href="javascript:void(0);" onClick="deleteCust(${customer.id})">Delete</a></td>
		</tr>
	</c:forEach>
</table>
</html>

<script>
	function deleteCust(custId) {
		var ans = confirm("Are you sure that you want to delete this customer?");
		console.log(ans);
		console.log(custId);
		if(ans){
			window.location = "deleteCustomer?custId=" + custId;
		}else{
		}
	}
</script>