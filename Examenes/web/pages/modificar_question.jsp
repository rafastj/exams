
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <title>Create Question</title>        
        <s:include value="head.jsp" />
    </head>
    <body>
		<div class="container">
        <div class="head">
        <s:include value="header.jsp" />             
        <div class="contenido">
        <h2>Modificar Question</h2>
            <s:form action="updateQuestionAction" method="POST" labelposition="left" theme="simple" >
            <s:hidden name="id_q" />
            <s:hidden name="firstOptionId" />
            <s:hidden name="secondOptionId" />
            <s:hidden name="thirdOptionId" />
            <s:hidden name="fourthOptionId" />
            <s:hidden name="fifthOptionId" />
            <s:hidden name="sixthOptionId" />
            <s:hidden name="seventhOptionId" />
            <s:hidden name="eightOptionId" />
            <s:hidden name="ninethOptionId" />
            <s:hidden name="tenthOptionId" />
            
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
                                  value="idTechnologyAnterior"
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
                                  value="idSeniorityAnterior"
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

            <div style="text-align: center;"><s:submit value="Update Question" align="center"/></div>

        </s:form>

</div>
</div>
</body>
</html>