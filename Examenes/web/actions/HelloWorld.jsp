<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <title><s:text name="HelloWorld.message"/></title>
    </head>

    <body>
        <s:include value="pages/header.jsp" />
        <h2><s:property value="message"/></h2>

        <h3>Languages</h3>
        <ul>
            <li>
                <s:url id="url" action="HelloWorld">
                    <s:param name="request_locale">en</s:param>
                </s:url>
                <s:a href="%{url}">English</s:a>
            </li>

            <li>
                <s:url id="url" action="HelloWorld">
                    <s:param name="request_locale">es</s:param>
                </s:url>

                <s:a href="%{url}">Espanol</s:a>               

                    <li>

                          <s:url id="url" action="populateQuestionFormAction">

                </s:url>

                       <s:a href="%{url}">Create Question</s:a>
                       </li>
                       <li>
                       <s:url id="url" action="getDataBaseInfo"> </s:url>
                       <s:a href="%{url}">Select Test</s:a>
                       </li>


            </li>
        </ul>
    </body>
</html>

