<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- 
<script type="text/javascript" src="js/superfish/jquery-1.2.6.min.js"></script>
 -->

<div class="contenedor">

	<div class="header">
    <div class="logo"></div>
    </div>
	
    <s:if test="#session.user != null">
	    <div class="user">
			<s:property value="%{'Logged in as : ' + #session.user}"/>
		</div>
        <s:include value="menu.jsp" />             
	</s:if>
	<s:else>
        <s:include value="menu_login.jsp" />             
	</s:else>
    <br>
    <br>

    <div class="errormsg" id="errormsg"><s:property value="%{#session.error}"/></div>
    <s:set name="error" scope="session" value="''"/>
    <s:if test="#session.error != ''">
    </s:if>
    <s:if test="#session.sucess != ''">
             <div class="scsmsg"><s:property value="%{#session.sucess}"/></div>
             <s:set name="sucess" scope="session" value="''"/>
    </s:if>

</div>
