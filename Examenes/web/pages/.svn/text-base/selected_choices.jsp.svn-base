<%@ taglib prefix="s" uri="/struts-tags" %>
<p>
	<s:property value="%{question.description}"  />
</p>
<div style="float: left;">
	<s:if test="%{correct}" >
		<span style="color: green;font-weight: bold; padding: 5px; border: solid 3px green;">correct</span>
	</s:if>
	<s:else>
		<span style="color: red;font-weight: bold; padding: 5px; border: solid 3px red;">wrong</span>
	</s:else>
</div>
<br><br>
<s:iterator value="%{optionChoseds}" >
    <s:if test="%{checked}">
        <s:checkbox   name="%{id}"  value="true" disabled="true"    />
    </s:if>
    <s:else>
        <s:checkbox   name="%{id}"  value="false" disabled="true"    />
    </s:else>
    <s:property value="%{option.description}" />
    <br>
</s:iterator>
<br><br>