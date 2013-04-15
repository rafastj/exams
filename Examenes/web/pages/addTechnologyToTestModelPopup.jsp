<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<script type="text/javascript">
		function updateWindow(){
			
			var t = document.getElementById('technologySelect');
			var s = document.getElementById('senioritySelect');
			var q = document.getElementById('qty');
			if(!isNaN(q)){
				alert("qty tiene q ser un numero");
				return false;
			}
			
			var opener = window.dialogArguments;
			opener.document.getElementById('createTestModelQuestionQtyForm').technologyrow.value=t.value;
			opener.document.getElementById('createTestModelQuestionQtyForm').seniorityrow.value=s.value;
			opener.document.getElementById('createTestModelQuestionQtyForm').qty.value = q.value; 
			opener.document.getElementById('createTestModelQuestionQtyForm').submit();
			self.close();
		}
	</script>
</head>
<body>
	<s:hidden id="testModel" name="testModel" />
	<%--
	<s:hidden id="testTime" name="testTime" />
	<script type="text/javascript">javascript:update();</script>
	 --%>
    	<div class="container">
        <div class="head">
        <div class="contenido">
        <%--
        <s:form action="createTestModelQuestionQty" target="_parent" method="POST" labelposition="left" theme="simple" >
         --%>
         <form action="createTestModelQuestionQty.action" target="_parent" method="POST"> 
	 			<s:hidden name="testModel"/>
		   		<s:iterator value="actualtechno" >
		        	<br><s:property value="technology.description" />-<s:property value="seniority.description" /> - <s:property value="qtyQuestions"/> Questions
		        	
		        </s:iterator>
		        
		        <br>
		        <br>
        		<table>
             
       				<tr>
						<td>Technology Profile:</td>
						<td>
    						<s:select id="technologySelect" 
    						 labelposition="left"
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
      						<s:select id="senioritySelect" 
      						 labelposition="left"
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
            </table>
            
                  <div style="text-align: center;">
                  	
                  	<%--
					<input type="submit" name="mysubmit" value="Click!" />
                  	<s:submit type="submit" value="Create Test Model"></s:submit>
        			
        			<a href="javascript:updateWindow();">Add Technology</a>
                  	 --%>
                  	 <input type="button" value="Add Technology" onclick="javascript:updateWindow();" />
                  </div>             
         </form>
         <%--
        </s:form>
          --%>
        
        </div>
	 	</div>
	 	</div>
</body>
</html>