<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Applicant</title>
        <s:include value="head.jsp" />
        <s:head theme="ajax" />
        <script type="text/javascript">
	    	function preSubmitForm(){
	    		
        		var queryString = $('#reloadActionWithTestModel').formSerialize();
        		
        	    $(document).ready(function(){
        		    $.ajax({
        		        type: "POST",
        		        url: "reloadActionWithTestModel.action",
        		        dataType: "html",
        		        data: queryString,
        		        success: function(datos){
				        			$('#paragraph1').empty();
				        			$('#ajaxResult').remove();
				        			
	        		        		if(datos=='NO_TEST_MODEL_AVAILABLE'){
	        		        			$('#paragraph1').text("No Test Model Available");
	        		        		}else{
	        		        			$('#solapa2').append(datos);
	        		        		}
        		     			}	
        		    });
        		});
	    		
	    		
	    		//document.forms["create_new_applicant"].submit();
	    	}
	    	function submitForm(){
        		var queryString = $('#reloadActionWithTestModel').formSerialize();
        		document.forms["create_new_applicant"].action = document.forms["create_new_applicant"].action +"?"+queryString;
	    		document.forms["create_new_applicant"].submit();
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
        	
        		<table width="100%">
        			<tr>
        				<td id="solapa1" width="50%">
			        		<h2>New Applicant</h2>
			       			<s:if test="%{code != null}">
			          			<div style="padding: 5px; border: 1px solid maroon; display: inline;">
			          				Code :  <b><s:property value="%{code}"  /></b>
			          			</div>
			          			<br>
			       			</s:if>
			       			<s:else>
				        		<table>
							        <tr>
							            <td colspan="2" width="140px" align="right"></td>
							            <td>
							            </td>
							        </tr>
				        		</table>
				        		<%--
				        		 --%>
				        		<s:form name="selectForm" action="reloadActionWithTestModel" method="POST">
				        			
							        <s:select labelposition="left"
							                  requiredposition="left"
							                  label="Select Profile"
							                  name="profile"
							                  headerKey="0"
							                  headerValue="-- Please Select --"
							                  list="profiles"
							                  listValue="description"
							                  listKey="id"
                                  			  onchange="javascript:preSubmitForm()"
							                  />
							        <s:select labelposition="left"
							                  requiredposition="left"
							                  label="Select Seniority"
							                  name="seniority"
							                  headerKey="0"
							                  headerValue="-- Please Select --"
							                  list="seniorities"
							                  listValue="description"
							                  listKey="id"
                                  			  onchange="javascript:preSubmitForm()"
							                  />
							    </s:form>
				        		<s:form action="createApplicantAction" name="create_new_applicant" method="POST">         
							        <%--
									<s:hidden name="testModelRetreived" />
							         --%>
				        			<s:hidden name="profile" />
				        			<s:hidden name="seniority" />
							                  
							        <s:textfield name="firstName" label="First Name"/>
							        <s:textfield name="lastName" label="Last Name"/>
							        
							        <s:datetimepicker name="fecha" label="Suggested Date" displayFormat="yyyy/MM/dd" type="date" value="today" ></s:datetimepicker>
							        <s:datetimepicker name="horario" label="Suggested Time" type="time" value="18:00"></s:datetimepicker>
							             
							        <s:textfield name="dni" label="DNI (Optional)"/>
							        
									<%--
				        			<s:submit value="Create Applicant" align="center"/>
									 --%>									         
				    			</s:form>
						        <s:form name="homeForm" method="POST" action="homeAction">
						        </s:form>
				    			
								<p style="text-align:center;"><input type="button" onclick="javascript:cancelForm();" value="Cancel"/></p>
								<p style="text-align:center;"><input type="button" onclick="javascript:submitForm();" value="Submit"/></p>
				    			
			    			</s:else>
        				
        				</td>
        				<td id="solapa2" width="50%">
			       			<s:if test="%{testModelRetreived != null}">
        						<s:include value="show_1_test_model.jsp" />
        					</s:if>
			       			<s:else>
			       				<p id="paragraph1" align="center"></p>
        					</s:else>
        					
        				</td>
        			</tr>
        		</table>
        	
    		</div>
    	</div>
	</body>
</html>
