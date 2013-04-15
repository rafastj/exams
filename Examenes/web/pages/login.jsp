<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<% 

if (session.getAttribute("user") != null) {
	
	response.sendRedirect("index.jsp");
	
}


%>

<html>
	<head>
		<title>Login!</title>
		<s:include value="head.jsp" />
	</head>
	<body>
		<div class="container">
			<div class="head">
				<s:include value="header.jsp" />
			</div>
			<div class="contenido">
				<s:form action="doLogin" method="POST">
					<table>
						<tr>
							<td colspan="2">
								<h2>Login</h2>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<s:actionerror />
								<s:fielderror />
							</td>
						</tr>
						<s:textfield name="username" label="Login name"/><br>
						<s:password name="password" label="Password"/>
						<s:submit value="Login" align="center"/>
					</table>
				</s:form>
			</div>
		</div>
	</body>
</html>