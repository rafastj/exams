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
    </head>
    <body>
    	<div class="container">
        <div class="head">
        <s:include value="header.jsp" />
        </div>
        <div class="contenido">
        <s:form action="showQuestionsByTechnologyAction" method="POST" labelposition="left" theme="simple" >
        
        
        	  	
              Select Question Technology: <s:select labelposition="left"
                                  requiredposition="left"
                                  label="Select Question Technology"
                                  name="technology"
                                  headerKey="0"
                                  headerValue="-- Please Select --"
                                  list="technologies"
                                  listValue="description"
                                  listKey="id"
                                  onchange="javascript:form.submit()"
                                  />
              
              Select Question Seniority: <s:select labelposition="left"
                                  requiredposition="left"
                                  label="Select Question Seniority"
                                  name="seniority"
                                  headerKey="0"
                                  headerValue="-- Please Select --"
                                  list="seniorities"
                                  listValue="description"
                                  listKey="id"
                                  onchange="javascript:form.submit()"
                                  />                              
              </s:form>
		<br>
        <s:iterator value="questionsRetreived" status="state">
        
        <br>
        <s:include value="show_correct_question_choices.jsp" />
        </s:iterator>
        </div>
    </div>
    </body>
</html>
