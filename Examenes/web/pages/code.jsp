<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

  		<s:if test="!#session.codeApplicantError.isEmpty()">
             <% out.print("<error>"); %>
             <% out.print("<code>"); %><s:property value="%{#session.codeApplicantError}"/><% out.print("</code>"); %>
             <% out.print("</error>"); %>
             <s:set name="error" scope="session" value="''"/>
             
        </s:if>
        <s:else>
	        <s:if test="!#session.codeApplicant.isEmpty()">
	             <% out.print("<applicant>"); %>
	             <% out.print("<code>"); %><s:property value="%{#session.codeApplicant}"/><% out.print("</code>"); %>
	             <% out.print("</applicant>"); %>
	             <s:set name="sucess" scope="session" value="''"/>
	        </s:if>
        </s:else>
