
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <title>Create Question</title>        
        <s:include value="head.jsp" />
        <script type="text/javascript">
	    	function preSubmitForm(){
	    		document.forms["create_question_form"].submit();
	    	}
	    	function cancelForm(){
	    		document.forms["homeForm"].submit();
	    	}
        </script>
    </head>
    <body>
		<div class="container">
        <div class="head">
        <s:include value="header.jsp" />             
        <div class="contenido">
        <h2>New Question</h2>
        
        <s:form name="homeForm" method="POST" action="homeAction">
        </s:form>
        
            <s:form action="CreateQuestionAction" name="create_question_form" method="POST" labelposition="left" theme="simple" >
            <table>
                <tr>
                    <td>
                        Select  Technology
                    </td>
                    <td>

                        <s:select labelposition="left"
                                  requiredposition="left"
                                  label="Select Technology"
                                  name="technology"
                                  headerKey="0"
                                  headerValue="-- Please Select --"
                                  list="technologies"
                                  listValue="description"
                                  listKey="id"
                                  />
                    </td>
                </tr>
                
                <tr>
                    <td>
                        Select  Seniority:
                    </td>
                    <td>

                        <s:select labelposition="left"
                                  requiredposition="left"
                                  label="Select Seniority"
                                  name="seniority"
                                  headerKey="0"
                                  headerValue="-- Please Select --"
                                  list="seniorities"
                                  listValue="description"
                                  listKey="id"
                                  />
                    </td>
                </tr>
                
                <tr>
                    <td>
                        Question Description:
                    </td>
                    <td>
                        <s:textarea name="questionDescription" label="Question Description" cols="60" rows="10"/>
                    </td>
                </tr>

                <s:include value="auxiliary/question_options.jsp" />
            </table>

			<%--
			<div style="text-align: center;"><a id="idCancel" href="homeAction.action">Cancel</a></div>	            

            <div style="text-align: center;"><s:submit value="Create Question" align="center"/></div>
			 --%>

        </s:form>
        <%--
		<s:form name="homeForm" action="homeAction.action" method="post">
	        <div style="text-align: center;">
				<s:submit type="submit" onclick="homeForm.submit()" value="Cancel"></s:submit>                	
	        </div>
		</s:form>        				 
         --%>
		<p style="text-align:center;"><input type="button" onclick="javascript:cancelForm();" value="Cancel"/></p>
		<p style="text-align:center;"><input type="button" onclick="javascript:preSubmitForm();" value="Submit"/></p>

</div>
</div>
</body>
</html>