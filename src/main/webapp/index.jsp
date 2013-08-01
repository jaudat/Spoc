<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<body>
	<h2>Welcome to Spring Greetings!</h2>

	<p>
		<a href="/Spoc/home/addgreeting.html">Add greeting</a> <br />
		<a href="/Spoc/home/greetings.html">Show all Greetings</a><br />
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<a href="/Spoc/restrictedarea.html">Restricted area...</a>
			<br />
		</sec:authorize>
		<a href="/Spoc/logout">Logout</a>
	</p>

</body>
</html>
