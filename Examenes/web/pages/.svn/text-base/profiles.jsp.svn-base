<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

  		<s:if test="!#session.codeProfilesError.isEmpty()">
             <% out.print("<error>"); %>
             <% out.print("<code>"); %><s:property value="%{#session.codeProfilesError}"/><% out.print("</code>"); %>
             <% out.print("</error>"); %>
             <s:set name="error" scope="session" value="''"/>
        </s:if>
     	<s:else>
	      	<s:if test="!#session.profiles.isEmpty()">
	             <% out.print("<profiles>"); %>
	             <s:iterator value="#session.profiles">
	             	
	             	<% out.print("<profile>"); %>
	             	<% out.print("<id>"); %><s:property value="id"/><% out.print("</id>"); %>
	             	<% out.print("<descripcion>"); %><s:property value="description"/><% out.print("</descripcion>"); %>
	             	<% out.print("</profile>"); %>	
	             	
	             </s:iterator>
	             <% out.print("</profiles>"); %>
	             
	             <s:set name="error" scope="session" value="''"/>
	        </s:if>
      	</s:else>

