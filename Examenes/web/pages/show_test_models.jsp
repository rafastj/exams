<%-- 
    Document   : show_test_models
    Created on : 05-ago-2010, 15:07:14
    Author     : Santiago.Arias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Test Model</title>
        <s:include value="head.jsp" />
        <script type="text/javascript">
        	// definir variables globales de tecnologia y seniority
        	var tecnologias = new Array(); // se genera del lado del servidor
        	<s:iterator value="technologiesrow" status="stat">
        		tecnologias[<s:property value="#stat.index" />]= new Array('<s:property value="%{id}"/>','<s:property value="%{description}"/>');
        	</s:iterator>
        	
        	var seniorities = new Array();
        	
        	<s:iterator value="senioritiesrow" status="stat">
    			seniorities[<s:property value="#stat.index" />]= new Array('<s:property value="%{id}"/>','<s:property value="%{description}"/>');
    		</s:iterator>
        
    		var indiceEnMap = 0;
    		
        	function submitForm(){
        		document.form.submit;
        	}
        	
        	function preSubmitForm(){
        		var parametros = new Array();
        		var count = 0;
        		$("tr.delete").each(function(index) {
        			  $(this).remove();
        			});
        		$("#lista_preguntas tr").each(function (){
        			parametros[count] = $(this);
        		});

        		
				/*        		
        		var queryString = $('#edit_test_model_form').formSerialize();
        		
        	    $(document).ready(function(){
        		    $.ajax({
        		        type: "POST",
        		        url: "editTestModel.action",
        		        dataType: "html",
        		        data: queryString,
        		        success: function(datos){
		        					document.getElementById("lista_preguntas").innerHTML = datos;
		        					//document.getElementById("errormsg").innerText = '<s:property value="%{#session.error}"/>';
        		     			}	
        		    });
        		});
				*/
        		
        		document.forms["edit_test_model_form"].submit();
        	}
        	function hola(data){
        		document.getElementById("errormsg").innerText = data; 
        	}
        	
        	function cancelForm(){
        		document.forms["homeForm"].submit();
        	}
        </script>
		<script type="text/javascript" src="js/addTechnology.js"></script>
		<script type="text/javascript" src="js/fillcomboajax.js"></script>		
    </head>
    <body>
    	<div class="container">
        <div class="head">
        <s:include value="header.jsp" />
        </div>
        <div class="contenido">
        <h2>Test Models</h2>
        
		<s:hidden id="testModelQuestionQtyDTOMap" name="testModelQuestionQtyDTOMap" />
		<s:hidden name="editTestModel" />
		<s:hidden name="editMode" />
		
		<s:hidden name="testModelRetreived" />
        <s:url action="showTestModel" id="showUrl" escapeAmp="false">
            <s:param name="editTestModel" value="false"/>
            <s:param name="testModel" value="testModel"/>
        </s:url>
        <s:url action="showCreateTestModelAction" id="addTechnology" escapeAmp="false">
            <s:param name="testModel" value="testModel"/>
        </s:url>
        
        <s:form name="homeForm" method="POST" action="homeAction">
        </s:form>
        
        <s:form id="createTestModelQuestionQtyForm" name="createTestModelQuestionQtyForm" action="createTestModelQuestionQty" method="POST">
			<s:hidden name="testModel" />
			<input type="hidden" name="technologyrow"/>
			<input type="hidden" name="seniorityrow"/>
			<input type="hidden" name="qty"/>
			
		</s:form>        
		
        <s:url action="showTestModel" id="editUrl" escapeAmp="false">
            <s:param name="editTestModel" value="true"/>
            <s:param name="testModel" value="testModel"/>
        </s:url>
        <s:if test="testModel!=null">
			<%--
	        	<a href="<s:property value="#editUrl"/>">Edit</a>
			<s:if test="editTestModel==true">
        		<a href="<s:property value="#showUrl"/>">Show</a>
        	</s:if>
			 --%>	        
        </s:if>
        &nbsp;&nbsp;&nbsp;
        <s:form action="showTestModel" method="POST">
        	<s:hidden name="editMode" />
            <s:select labelposition="left"
                  requiredposition="left"
                  label="Select Test Model"
                  name="testModel"
                  headerKey="0"
                  headerValue="-- Please Select --"
                  list="testModels"
                  listValue="%{profile.description + ' - ' + seniority.description}"
                  listKey="id"
                  onchange="javascript:form.submit()"
                  onkeyup="javascript:form.submit()"
                  />
		 </s:form>
                  
            <br>
            
            
		<s:if test="editTestModel">
		
		<input type="button" value="Add Technology" onClick="javascript:createRow();">
			
			            
            <s:form action="editTestModel" id="edit_test_model_form" name="editTestModelForm" method="POST">
				<input id="idNewTechnology" type="hidden" name="newTechnology"/>
            
	            <table>
	            	<tr>
	            		<td valign="top">
				            <table id="mainTable" border="1px" cellpadding="5px">
				                <tr>
				                    <th>  Technology  </th>
				                    <th>  Seniority   </th>
				                    <th>  Available questions  </th>
				                    <th>  # of questions  </th>
				                    <th>  Delete  </th>
				                    <%--
				                     --%>
				                </tr>
				                <div id="lista_preguntas">
        							<s:include value="editTestModelSuccess.jsp" />
				                </div>
				            </table> 
	            		</td>
	            	</tr>
	            </table>
	           <table>
	            	<tr>
	            		<td valign="top">
	            			<table border="1px" cellpadding="5px">
				                <tr>
				                	<th>  Minutes to solve the exam:  </th>
				                </tr>
				                <tr>
			                       <td>
		                       			<s:textfield  name="minutesToFinish" theme="simple" value="%{testModelRetreived.minutesToFinish}" />
		                       			<s:hidden name="idTestModel" value="%{testModelRetreived.id}"/>
			                       </td>        
				                </tr>
	         				</table>      
	            		</td>
	            	</tr>
	            </table>
			<p style="text-align:center;"><input type="button" onclick="javascript:cancelForm();" value="Cancel"/></p>
			<p style="text-align:center;"><input type="button" onclick="javascript:preSubmitForm();" value="Submit"/></p>
      		</s:form>
      		
      </s:if>
      
      <s:else>
        	<s:include value="show_1_test_model.jsp" />
      </s:else>
      
 </div>
 </div>
    </body>
</html>
