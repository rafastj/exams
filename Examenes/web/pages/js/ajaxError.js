function editTestModelSubmit(index){
	
    $(document).ready(function(){
	    $.ajax({
	        type: "POST",
	        url: "totalAmountOfQuestionsAction.action",
	        dataType: "html",
	        data: "technologyId="+technologyValue+"&seniorityId="+seniorityValue,
	        success: function(datos){
	        			document.getElementById("totalAmountOfQuestions_"+index).innerText = datos;
	     			}	
	    });
	});
}
