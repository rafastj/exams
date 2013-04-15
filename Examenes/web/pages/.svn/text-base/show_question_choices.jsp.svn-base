<%-- 
    Document   : newjspshow_question_choices
    Created on : 05-ago-2010, 12:23:18
    Author     : Santiago.Arias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>

<div style="background-color: #DDDDDD; font-family:'Courier New', Courier, monospace; font-size:14px;padding:10px;border-radius:10px">
<div style="width:700px; white-space:pre-wrap"><s:label name="todasLasPreguntas.get(tecnologiaActual).get(preguntaActual).getDescription()" /></div>
</div>
<div style="width:700px; white-space:pre-wrap">
<script>
	index = 0;
	letters = ["a)", "b)", "c)", "d)", "e)", "f)", "g)", "h)", "i)", "j)"];
</script>
<table border="0">
<s:iterator value="todasLasPreguntas.get(tecnologiaActual).get(preguntaActual).options">
	<tr>
		<td><script>document.write(letters[index]); index++;</script></td>
		<td><s:checkbox name="%{id}"  value="false" /></td>
		<td><s:property value="%{description}" /></td>
	</tr>
</s:iterator>
</table>
</div>
<br>
