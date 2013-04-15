<%-- 
    Document   : newjspshow_question_choices
    Created on : 05-ago-2010, 12:23:18
    Author     : Santiago.Arias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<div style="background-color: #DDDDDD;">
	<div style="width:700px; white-space:pre-wrap"><s:property value="#state.index+1+')'" /><s:property value="description"  /></div>
	<div style="float:right;">
		
		<s:url id="url" action="showModificarQuestionAction">
			<s:param name="id_q" value="id"/>
		</s:url>
        <s:a href="%{url}">Modificar</s:a>
        
        <s:url id="url" action="eliminarQuestionAction">
        	<s:param name="id_q" value="id"/>
        </s:url>
        <s:a href="%{url}">Eliminar</s:a>
            
		<!--		 
		<s:form id="formShowModificarQuestion%{id}" name="formShowModificarQuestion%{id}" method="POST" action="showModificarQuestionAction">
			<s:hidden name="id_q" value="%{id}" />
			<s:submit value="Modificar" />
		</s:form>
		
		<s:form id="formEliminarQuestion%{id}" name="formEliminarQuestion%{id}" method="POST" action="eliminarQuestionAction">
			<s:hidden name="id_q" value="%{id}" />
			<s:submit value="Eliminar" />
		</s:form>
		-->
	</div>
</div>
<br></br>
<script>
	index = 0;
	letters = ["a)", "b)", "c)", "d)", "e)", "f)", "g)", "h)", "i)", "j)"];
</script>

	<s:iterator value="options" >
			<script>document.write(letters[index]); index++;</script>
		    	
		    	<s:if test="correct">
		       		<s:checkbox labelposition="left" name="%{id}" value="true"  disabled="true" />
		    	</s:if>
		    	<s:else>
		        	<s:checkbox labelposition="left" name="%{id}"  value="false" disabled="true" />
		    	</s:else>
		    
		    <s:property value="%{description}" />
		    <br><br>
	</s:iterator>

<br>
