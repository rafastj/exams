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
        <title>Show Test Model</title>
        <s:include value="head.jsp" />
        <s:head theme="ajax" />
    </head>
    <body>
		<div class="container">
        <div class="head">
        <s:include value="header.jsp" />
		</div>
        <div class="contenido">
        <h2>Test Results</h2>



		<s:form action="showTestAction" method="POST" theme="simple">
			
				<table border="0">
					<tr>
						<td><label>First name</label></td>
						<td><s:textfield name="applicantName" label="First name"></s:textfield></td>
					
						<td><label>Last name</label></td>
						<td><s:textfield name="applicantLastName" label="Last name"></s:textfield></td>
					
						<td><label>Begin date</label></td>
						<td><s:datetimepicker name="beginDate" ></s:datetimepicker></td>
					
						<td><label>End date</label></td>
						<td><s:datetimepicker name="endDate"></s:datetimepicker></td>
					</tr>
					<tr>
						<td>
							<label>Show Test</label>
						</td>
						<td>
							<s:select name="testTaked" list="#{'all':'ALL', 'true':'Made!', 'false':'Not made'}" />
						</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>			
					</tr>
					<tr>
						<td><button onclick="javascript:form.submit()">Search</button></td>			
					</tr>
				</table>
			
        </s:form>
        	<s:if test="onlyResult!=null">
	   	     	<s:if test="!onlyResult">
		        		<table border="1">
		        			<thead>
		        				<th width="110px">First name</th>
		        				<th width="110px">Last Name</th>
		        				<th width="110px">Profile</th>
		        				<th width="60px">Seniority</th>
		        				<th width="170px">Result</th>
		        				<th width="180px">Suggested Date</th>
		        				<th width="80px">View Test</th>		        				
		        			</thead>
			           	<s:iterator value="applicants" >
			           		<tr>
			           			<td><s:property value="firstName"/></td>
			           			<td><s:property value="lastName"/></td>
			           			<td><s:property value="getProfile().getDescription()"/></td>
			           			<td><s:property value="getSeniority().getDescription()"/></td>
			           			<td align="center">
				           			<s:property value="%{'Average Score ' + score + '%'}"/>
				           			<table align="center" border="1">
				           				<thead>
				           					<th width="70px">Score</th>
				           					<th width="100px">Technology</th>
				           					<th width="70px">Correct/Total</th>
				           				</thead>
		           						<s:iterator value="%{tests}">
											<s:iterator value="%{techScores}">
												<%--
												<s:if test="%{score!=0}">
												 --%>
												<s:if test="%{questions !=0}">
													<tr>
														<td style="width: 70px;"><s:property value="%{getFormattedScore(score)}" /></td>
														<td style="width: 100px;"><s:property value="%{technology.description}" /></td>
														<td style="width: 70px;"><s:property value="%{questionsCorrect + '/' + questions}" /></td>
													</tr>
												</s:if>
											</s:iterator>
										</s:iterator>
									</table>
			           			</td>
			           			<td align="center"><s:property value="testDate" /></td>
			           			<td align="center">
				        			<s:form action="showTestDetailAction" method="POST" theme="simple">
					           			<s:hidden name="applicant" value="%{id}" />
					           			<s:submit value="View Exam"></s:submit>
					        		</s:form>
				        		</td>
			        		<tr>	
			        	</s:iterator>
			        	</table>
				</s:if>
				<s:else>
					<h1 style="color: red">Only result</h1>                
			        <h2 style="color: blue"><s:property  value="%{test.getApplicant().getFirstName()+' '+test.getApplicant().getLastName()}" /></h2> 
			        <table>
						<s:iterator value="testScores" >
			                <s:if test="%{questions !=0}">
			                <tr>
			                	<td>
			                        <span style="color: black;font-size: large;"><s:property  value="%{technology.description + '  '}" /> </span>
			                    </td>
			                    <td>
			                        <span style="color: green;font-size: large;">
			                            <s:property value="%{questionsCorrect }" />
			                        </span>
			                        <s:property value="%{  ' / ' + questions}" />
			                       
			                        <span style="color: red;font-size: large;">
										<s:property value="%{'Promedio: ' + formattedScore}" />
									</span>   
									                
			                   	</td>                        
			                </tr>
			                </s:if>
			            </s:iterator>
				
			        </table>
			        <h4 style="color: black "><s:property value="%{'Average Score : ' + test.score +' %'}"/></h4>       
			        <s:iterator value="technologies" >
			            <s:iterator value="questionsAnswered" >
				            <s:if test="%{question.technology.description == description}"  >
				                   <s:set name="showTechTitle" scope="session" value="description"/>
				            </s:if>
				             </s:iterator>
				            <s:if test="#session.showTechTitle != ''">
				                <h1> <s:property value="%{#session.showTechTitle}"/></h1>
				            </s:if>
				                <s:set name="showTechTitle" scope="session" value=""/>
				            <s:iterator value="questionsAnswered" >
				            <s:if test="%{question.technology.description == description}"  >
				                <s:include value="selected_choices.jsp" />
				            </s:if>
			     	   </s:iterator>
			        </s:iterator>
				</s:else>
			</s:if>
</div>    
	</div>

    </body>
</html>
