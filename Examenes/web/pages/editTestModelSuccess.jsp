<%@ taglib prefix="s" uri="/struts-tags" %>

<%--
<s:property value="%{#session.error}"/>
 --%>
<div id="sdiv" onload="javascript:hola('<s:property value="%{#session.error}"/>');"></div>

<s:iterator value="questionList" status="rownum">
          <tr id="tr_id_<s:property value="#rownum.index" />">	
       					<td>
       						<s:select id="technologySelect_%{#rownum.index}"
			                    name="testModelQuestionQtyDTOMap['%{id}'].technologyId"
			     				theme="simple"
			                    headerKey="%{testModelQuestionQtyDTOMap[id].technologyId}"
			                    headerValue="%{testModelQuestionQtyDTOMap[id].technologyDescription}"
			                    list="technologiesrow"
			                    listValue="description"
			                    listKey="id"
			                    onchange="changeAmountOfTotalQuestions('%{#rownum.index}');"
			                   	/>
    							 
             			</td>
     					<td>
	 						<s:select id="senioritySelect_%{#rownum.index}"
			                    name="testModelQuestionQtyDTOMap['%{id}'].seniorityId"
			     				theme="simple"
			                    headerKey="%{testModelQuestionQtyDTOMap[id].seniorityId}"
			                    headerValue="%{testModelQuestionQtyDTOMap[id].seniorityDescription}"
			                    list="senioritiesrow"
			                    listValue="description"
			                    listKey="id"
			                    onchange="changeAmountOfTotalQuestions('%{#rownum.index}');"
			                    />
     					</td>
                     <td id="totalAmountOfQuestions_<s:property value="#rownum.index" />">
                     	<s:property value="%{testModelQuestionQtyDTOMap[id].totalAmountOfQuestions}"/>
					</td>
					
                     <td>
						<s:textfield name="testModelQuestionQtyDTOMap['%{id}'].qtyQuestions"
						 theme="simple"
						 value="%{qtyQuestions}" />			                        
					</td>
			
                     <td>
						<s:hidden name="testModelRetreived" />
						<s:checkbox name="delete" id="delete_%{#rownum.index}" theme="simple" onclick="javascript:deleteRow('%{#rownum.index}')" label="Delete"/>
                     </td>
             </tr>
</s:iterator>
