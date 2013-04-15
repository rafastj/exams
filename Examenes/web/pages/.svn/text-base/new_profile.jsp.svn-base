<%-- 
    Document   : new_Technology
    Created on : 05-ago-2010, 18:59:42
    Author     : Santiago.Arias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exam</title>
        <s:include value="head.jsp" />
        <script type="text/javascript">
	    	function cancelForm(){
	    		document.forms["homeForm"].submit();
	    	}
        </script>
    </head>
    <body>
    	<div class="container">
   			<div class="head">
   				<s:include value="header.jsp" />
   			</div>
        
      		<div class="contenido">
      			<h2>New Profile</h2>
      			<s:if test="%{profileExists}">
         				<p style="color: red;font-size:12">
             						Attention Profile Already Exists
						</p>
		        </s:if>  

		        	<s:form action="createProfileAction" method="POST" labelposition="left" theme="simple">
				        <s:textfield name="profileName" label="Profile Name" requiredposition="left"/>
				        
				        <s:submit value="Create Profile" requiredposition="left"/>
						<input type="button" onclick="javascript:cancelForm();" value="Cancel"/>
	      			</s:form>
			        <s:form name="homeForm" method="POST" action="homeAction">
			        </s:form>
				<br>
				<br>
           		<div>
	           		<s:if test="%{profiles!=null}">
	       			<table border="1" align="center" width="100%" >
	           			<tr><td bgcolor="#A4A4A4" >EXISTENT PROFILES</td></tr>
			        <s:iterator value="profiles">
			            <tr>
			            	<td>
			                    <s:property value="%{description}"/>
			                </td>
			            </tr>
			        </s:iterator>
	
	           		</table>
	       			</s:if>
				</div>
			</div>
        </div>
    </body>
</html>
