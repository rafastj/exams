<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<script language="JavaScript">
	function update(){
			var val = document.getElementById("code").value;
			var time = document.getElementById("testTime").value;
		  var url="showTestForApplicantPopUp.action?code="+val+"&testTime="+time;
		  var popup = window.open(url,"_blank","height=800,width=1100,status=no,location=no,toolbar=no,directories=no,menubar=no,scrollbars=1");
		}	
	</script>
</head>
<body>
	<s:hidden id="code" name="code" />
	<s:hidden id="testTime" name="testTime" />
	<script type="text/javascript">javascript:update();</script>
</body>
</html>