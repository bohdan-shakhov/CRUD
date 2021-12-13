<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isErrorPage="true" %>
<html lang="en">
<head>
	<title>Error</title>
</head>
<body>
	<div style="text-align: center;">
		<h1>Error</h1>
		<h2>
			<%=exception.getLocalizedMessage()%> <br>
		</h2>
	</div>
</body>
</html>
