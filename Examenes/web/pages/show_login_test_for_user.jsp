<%--
    Document   : show_test
    Created on : 04-ago-2010, 11:59:05
    Author     : Santiago.Arias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Test</title>
        <s:include value="head.jsp" />
    </head>
    <body>
    	<div class="container">
			<div class="head">
				
				
				<s:if test="#session.user != null">
					<s:include value="header.jsp" />
				</s:if>
				<s:else>
					<img src="img/bg-nav.png" width="896" height="114" alt="nav"> 
				</s:else>
			</div>
			<div class="contenido">
			
				  <s:if test="#session.error != ''">
             <div class="errormsg"><s:property value="%{#session.error}"/></div>
             <s:set name="error" scope="session" value="''"/>
        </s:if>
             			
				<s:form action="showTestForApplicant" method="POST"  theme="simple">
					<s:textfield name="code" label="Insert your Code" value="Insert your Code"/>
					<s:submit value="Show Test" align="center"/>
				</s:form>
			</div>
   		</div>     
	</body>
</html>
