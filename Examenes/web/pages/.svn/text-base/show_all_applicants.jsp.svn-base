<%-- 
    Document   : show_applicant
    Created on : 09-ago-2010, 16:29:32
    Author     : Santiago.Arias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>




<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Exam</title>
<s:include value="head.jsp" />
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){

	//how much items per page to show
	var show_per_page = 10;
	//getting the amount of elements inside content div
	var number_of_items = $('#content').children().size();
	//calculate the number of pages we are going to have
	var number_of_pages = Math.ceil(number_of_items/show_per_page);

	//set the value of our hidden input fields
	$('#current_page').val(0);
	$('#show_per_page').val(show_per_page);

	//now when we got all we need for the navigation let's make it '

	/*
	what are we going to have in the navigation?
		- link to previous page
		- links to specific pages
		- link to next page
	*/
	var navigation_html = '<a class="previous_link" href="javascript:previous();">Prev</a>';
	var current_link = 0;
	while(number_of_pages > current_link){
		navigation_html += '<a class="page_link" href="javascript:go_to_page(' + current_link +')" longdesc="' + current_link +'">'+ (current_link + 1) +'</a>';
		current_link++;
	}
	navigation_html += '<a class="next_link" href="javascript:next();">Next</a>';

	$('#page_navigation').html(navigation_html);

	//add active_page class to the first page link
	$('#page_navigation .page_link:first').addClass('active_page');

	//hide all the elements inside content div
	$('#content').children().css('display', 'none');

	//and show the first n (show_per_page) elements
	$('#content').children().slice(0, show_per_page).css('display', 'block');

});

function previous(){

	new_page = parseInt($('#current_page').val()) - 1;
	//if there is an item before the current active link run the function
	if($('.active_page').prev('.page_link').length==true){
		go_to_page(new_page);
	}

}

function next(){
	new_page = parseInt($('#current_page').val()) + 1;
	//if there is an item after the current active link run the function
	if($('.active_page').next('.page_link').length==true){
		go_to_page(new_page);
	}

}
function go_to_page(page_num){
	//get the number of items shown per page
	var show_per_page = parseInt($('#show_per_page').val());

	//get the element number where to start the slice from
	start_from = page_num * show_per_page;

	//get the element number where to end the slice
	end_on = start_from + show_per_page;

	//hide all children elements of content div, get specific items and show them
	$('#content').children().css('display', 'none').slice(start_from, end_on).css('display', 'block');

	/*get the page link that has longdesc attribute of the current page and add active_page class to it
	and remove that class from previously active page link*/
	$('.page_link[longdesc=' + page_num +']').addClass('active_page').siblings('.active_page').removeClass('active_page');

	//update the current page input field
	$('#current_page').val(page_num);
}



function go_to_exam(id)
{
       document.getElementById('applicant').value=id;
       document.forms(0).submit();
 }

</script>




</head>
<body>
	<s:form id="form1" action="showTestAction" method="POST" theme="simple">

		<div class="container">
			<div class="head">
				<s:include value="header.jsp" />

			</div>
			<div class="contenido">
				<h2>Applicants List</h2>
				<!-- the input fields that will hold the variables we will use -->
				<input type='hidden' id='current_page' /> <input type='hidden'
					id='show_per_page' />
				<s:hidden id="applicant" name="applicant" />


				<!-- Content div. The child elements will be used for paginating(they don't have to be all the same,
		you can use divs, paragraphs, spans, or whatever you like mixed together). '-->
				<table>

				</table>
				<div id='content'>




					<table align="left" border="1">
						<tr>
							<td>First Name</td>
							<td>Last Name</td>
							<td>DNI</td>
							<td>Code</td>
							<td style="width: 70px;">Score</td>
							<td style="width: 100px;">Techonology</td>
							<td style="width: 70px;">Correctas/Totales</td>

						</tr>


						<s:iterator value="%{applicants}">

							<s:if test="%{tests.size()>0}">


								<tr onclick="go_to_exam(this.id);" style="cursor: pointer"
									id=<s:property value="%{id}"/>>
									<td><s:property value="%{firstName}" /></td>

									<td><s:property value="%{lastName}" /></td>
									<td><s:if test="%{dni!=null}">
											<s:property value="%{dni}" />
										</s:if></td>
									<td><s:property value="%{code}" /></td>
								</tr>
								<tr>

									<td colspan="7" align="right">

										<table align="right" border="0">
											<s:iterator value="%{tests}">
												<s:iterator value="%{techScores}">

													<s:if test="%{score!=0}">
														<tr>

															<td style="width: 70px;"><s:property
																	value="%{score * 100}" /></td>
															<td style="width: 100px;"><s:property
																	value="%{technology.description}" /></td>
															<td style="width: 70px;"><s:property
																	value="%{questionsCorrect + '/' + questions}" /></td>
														</tr>
														<td style="width: 70px;">Resultado: <s:property value="%{score}" /></td>
													</s:if>
													
												</s:iterator>
										</table>
								<tr>
									<td colspan="7" align="center" bgcolor="Gray"><s:property
											value="%{'Average Score ' + score + '%'}" /></td>

								</tr>
						</s:iterator>



						</s:if>

						</s:iterator>

					</table>

				</div>

				<!-- An empty div which will be populated using jQuery -->
				<div style="clear: both;"></div>
				<div id='page_navigation'></div>
	</s:form>
	</div>
	</div>
</body>
</html>
