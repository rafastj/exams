<%-- 
    Document   : show_question
    Created on : 18-ago-2010, 12:15:35
    Author     : Santiago.Arias
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exam</title>
        <s:include value="head.jsp" />
        <script type="text/javascript">
	    	function preSubmitForm(){
	    		document.forms["create_test_model_form"].submit();
	    	}
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
	        <s:form action="createTestModelAction" name="create_test_model_form" method="POST" labelposition="left" theme="simple" >
	        	
	   		<s:iterator value="actualtechno" >
	        	<br><s:property value="technology.description" />-<s:property value="seniority.description" /> - <s:property value="qtyQuestions"/> Questions
	        	
	        </s:iterator>
	        
	        <br>
	        <br>
	        		<table>
                    				<tr>
           							<td>Applicant Profile:</td>
           							<td>
           								<s:select labelposition="left"
					                     requiredposition="left"
					                     label="Select Profile"
					                     name="profile"
					                     headerKey="0"
					                     headerValue="-- Please Select --"
					                     list="profiles"
					                     listValue="description"
					                     listKey="id"/>
					               </td>
           							<td>
           								Applicant Seniority:
           							</td>
           							<td>
           								<s:select labelposition="left"
					                     requiredposition="left"
					                     label="Select Seniority"
					                     name="seniority"
					                     headerKey="0"
					                     headerValue="-- Please Select --"
					                     list="seniorities"
					                     listValue="description"
					                     listKey="id"/>
           							</td>
           						</tr>
	             				<%--
	             				<tr>
           							<td>Technology Profile:</td>
           							<td>
           								<s:select labelposition="left"
					                     requiredposition="left"
					                     label="Select Technology"
					                     name="technologyrow"
					                     headerKey="0"
					                     headerValue="-- Please Select --"
					                     list="technologiesrow"
					                     listValue="description"
					                     listKey="id"
					                     />
					               </td>
           							<td>
           								Seniority Technology:
           							</td>
           							<td>
           								<s:select labelposition="left"
					                     requiredposition="left"
					                     label="Select Seniority"
					                     name="seniorityrow"
					                     headerKey="0"
					                     headerValue="-- Please Select --"
					                     list="senioritiesrow"
					                     listValue="description"
					                     listKey="id"
					                     />
           							</td>
           						</tr>                     

				<tr>
					<td>Count:</td>
					<td><s:textfield name="qty" /></td>
				</tr>
	             				 --%>
	            <tr>
					<td>Minutes to complete the exam:</td>
					<td><s:textfield name="minutesToFinish" /></td>
				</tr>                     
	            </table>
	            
	            <%--
                <div id="createDiv" style="text-align: center;">
					<p style="text-align:center;"><a id="idCancel" href="homeAction.action">Cancel</a></p>	            
                	<s:submit type="submit" value="Create Test Model"/>
                </div>
	             --%>
                
	        </s:form>
	        <s:form name="homeForm" method="POST" action="homeAction">
	        </s:form>
	        
			<p style="text-align:center;"><input type="button" onclick="javascript:cancelForm();" value="Cancel"/></p>
			<p style="text-align:center;"><input type="button" onclick="javascript:preSubmitForm();" value="Submit"/></p>
	        
        </div>
    </div>
    </body>
</html>
