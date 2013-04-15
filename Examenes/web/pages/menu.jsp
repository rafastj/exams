<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
    
    
	<div class="menu">

		<ul class="sf-menu">
			<%--
            <li>
	            <s:a href="login.jsp">User Login</s:a>
	        </li>
			 --%>
            <li>
	            <s:a href="#">Profile</s:a>
				<ul>
		            <li>
			            <s:url id="url"  action="showCreateProfile"> </s:url>
			            <s:a href="%{url}">Create Profile</s:a>
			        </li>
	            </ul>
	        </li>
            <li>
	            <s:a href="#">Technology</s:a>
				<ul>
		            <li>
			            <s:url id="url"  action="showCreateTechnology"> </s:url>
			            <s:a href="%{url}">Create Technology</s:a>
			        </li>
	            </ul>
	        </li>
            <li>
	            <s:a href="#">Applicant</s:a>
				<ul>
		            <li>
			            <s:url id="url" action="createNewApplicantForm"> </s:url>
			            <s:a href="%{url}">Create Applicant</s:a>
			        </li>
					<li>
			            <s:url id="url" action="populasteShowTestAction"> </s:url>
			            <s:a href="%{url}">Show Applicants Tests</s:a>
					</li>
					<li>
	            		<s:url id="url" action="showApplicantLogin">
	            		</s:url>
						<s:a href="%{url}">Test an applicant</s:a>
					</li>
			        
	            </ul>
	        </li>
			<li class="current">
				<a href="#a">Test</a>
				<ul>
					<li>
			            <s:url id="url" action="showCreateTestModelAction"> </s:url>
			            <s:a href="%{url}">Create Test Model</s:a>
					</li>
					<li>
						<s:a href="#" onclick="showTestModelForm.submit()">Show Test Models</s:a>
						<s:form name="showTestModelForm" action="showTestModels.action" method="post">
						        <s:hidden name="editMode" value="false"/>
						</s:form>        				 
					</li>
					<li>
						<s:a href="#" onclick="editTestModelForm.submit()">Edit Test Models</s:a>
						<s:form name="editTestModelForm" action="showTestModels.action" method="post">
						        <s:hidden  name="editMode" value="true"/>
						</s:form>        				 
						 
					</li>
					<li>
	            		<s:url id="url" action="showAllQuestions"> </s:url>
						<s:a href="%{url}">Show Questions</s:a>
					</li>
					<li>
			            <s:url id="url" action="populateQuestionFormAction"></s:url>
			            <s:a href="%{url}">Create Question</s:a>
					</li>
					
				</ul>
			</li>
            <li>
	            <s:url id="url" action="logOut"> </s:url>
	            <s:a href="%{url}">Log Out</s:a>
	        </li>
		</ul>
	    
	</div>
