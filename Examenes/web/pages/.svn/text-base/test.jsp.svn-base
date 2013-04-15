<%-- 
    Document   : test
    Created on : 03-ago-2010, 14:15:29
    Author     : Santiago.Arias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
    	<meta http-equiv="Expires" content="0">
		<meta http-equiv="Last-Modified" content="0">
		<meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">
		<meta http-equiv="Pragma" content="no-cache">
    	
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/test.css" rel="stylesheet" type="text/css">
        <title>Test</title>
        <script type="text/javascript" src="js/clock.js"></script>
 		     
    </head>
    <!-- Prueba para CSS, Eliminar comentario luego-->
    <body> 
	<s:hidden id="testTime" name="testTime" />
    
<!--		<form name="counter">-->
<!--			<INPUT TYPE="text" NAME="timerDisplay" VALUE="">		-->
<!--		</form> -->

		<script type="text/javascript">
	        var running = false;
	        var endTime = null;
	        var timerID = null;
	        var amountOfMinutes = 1;
			
			function start(){
				
				amountOfMinutes = document.getElementById("testTime").value;
				startTimer();
			}
        </script>
        
		
		
    	<div class="container">
        	<div class="header">
    			<div class="logo"></div>
    			<div class="tit-ex"></div>
    			
    			<div style="float :right; margin-top: 50px; font-family: fantasy; font-size: 14px; font-weight: bold; color: #162B6E;">
    			<div id="timerDisplay" style="width:50px; margin-left: 10px; margin-right: 40px;"></div> 
           			<script type="text/javascript">start();</script>
    			</div>
    			
                <div class="botonera-ex">
        			<div class="nombre"><s:property value="applicant.firstName"/> <s:property value="applicant.lastName"/></div>
           			<div class="categ"> <b><s:property value="applicant.profile.description"/></b> | <s:property value="applicant.seniority.description"/></div>
        		</div>
            </div>
           	<div class="content">
              <div class="ayuda">
					<iframe style="width:800px; height:700px; border: groove;" frameborder="1" id="eventinfo" src="nextQuestionAction.action" >
                    	<s:include value="question.jsp" />
					</iframe>                      
                              
              </div>                
   			</div>                               
    </body>
</html>
