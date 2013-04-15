<%-- 
    Document   : test
    Created on : 03-ago-2010, 14:15:29
    Author     : Santiago.Arias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div>
 	<script type="text/javascript" src="js/clock.js"></script>
	<script type="text/javascript">
		function SubmitForm(){
			//stopTimer();
			document.nextQuestionForm.submit();
			//window.close();
		}
	</script>
	<s:form name="nextQuestionForm" action="nextQuestionAction" method="post" labelposition="left" theme="simple"  >
		<%--
		<s:hidden id="startTime" name="testStartTime" />
		 --%>
		<s:if test="tecnologiaActual!=null">
			<s:label name="todasLasPreguntas.get(tecnologiaActual).size()-preguntaActual" /> questions to answer in <s:label name="tecnologiaActual.getDescription()" /> technology 
			
			<br></br>
			<br></br>	
			
			<s:include value="show_question_choices.jsp" />
			
			<s:submit value="Next Question" align="center"/>
		</s:if>
		<s:else>
			<script>
				SubmitForm();
			</script>
<!--			Please, press button "Finish" to finishing the evaluation.-->
<!---->
<!--			<s:submit value="Finish" />-->
<!--			<%---->
<!--			<s:submit onclick="SubmitForm();"/>-->
<!--			<input type="button" onclick="SubmitForm();"/>-->
<!---->
<!--			 --%>			-->
		</s:else>	
	</s:form>
</div>
		
