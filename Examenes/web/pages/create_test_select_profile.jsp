<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>

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
        		<s:form action="createTestModelAction" method="POST" theme="simple">
					<s:select labelposition="left"
					   requiredposition="left"
					   label="Select Profile"
					   name="profile"
					   headerKey="%{profileSelected}"
					   headerValue="%{profile}"
					   list="profiles"
					   listValue="description"
					   listKey="id"
					   disabled="true"
					   />

			        <s:select labelposition="left"
			                  requiredposition="left"
			                  label="Select Seniority"
			                  name="seniority"
			                  headerKey="%{senioritySelected}"
			                  headerValue="%{seniority}"
			                  list="seniorities"
			                  listValue="description"
			                  listKey="id"
			                  onchange="createTestModelAction"
			                  disabled="true"
			                  />
			        </br>
			        
			        <s:iterator value="modelohastaelmomento" >
				        </br><s:label value="%{t}"/><s:label value="%{s}"/><s:label value="%{c}"/></br>         
	        		</s:iterator>
			        
			        </br>
			                  
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
			        
			        Count: <s:text name="count"></s:text>
			        <s:submit value="Create Test Model" align="center" />			        
    			</s:form>
			</div>
    	</div>       
    </body>
</html>
