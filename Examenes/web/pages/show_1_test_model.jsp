<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="ajaxResult">
         <table>
         	<tr>
         		<td valign="top">
	            <table border="1px" cellpadding="5px">
	                <tr>
	                    <th>  Technology:  </th>
	                    <th>  Seniority:   </th>
	                    <th>  Number of questions:  </th>
	                </tr>
					<s:iterator value="questionList" >
		                <tr>
	                        <td>
	                        	<s:property value="%{technology.description}" />
	                        </td>
	                        <td>
	                        	<s:property value="%{seniority.description}" />
	                        </td>
	                        <td>
	                        	<s:property value="%{qtyQuestions}" />
	                        </td>
		                </tr>
					</s:iterator>
	            </table> 
         		</td>
         	</tr>
         </table>
           <table>
            	<tr>
            		<td valign="top">
            			<table border="1px" cellpadding="5px">
			                <tr>
			                	<th>  Minutes to solve the exam:  </th>
			                </tr>
			                <s:if test="testModelRetreived.minutesToFinish!=null">
				                <tr>
			                       <td>
			                       		<label name="minutesToFinish"><s:property value="%{testModelRetreived.minutesToFinish}" /></label>
			                       </td>        
				                </tr>
				            </s:if>
         				</table>      
            		</td>
            	</tr>
            </table>
</div>
