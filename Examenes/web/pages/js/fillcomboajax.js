function changeAmountOfTotalQuestions(index){
	var technologyValue = document.getElementById("technologySelect_"+index).value;
	var seniorityValue = document.getElementById("senioritySelect_"+index).value;
	
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
//function changeAmountOfTotalQuestionsNewLine(){
function changeAmountOfTotalQuestionsNewLine(technologySELECTId,senioritySELECTId,availableTDId,indiceEnMap){
	var technologyValue = technologySELECTId.value;
	var seniorityValue = senioritySELECTId.value;
	
    $(document).ready(function(){
	    $.ajax({
	        type: "POST",
	        url: "totalAmountOfQuestionsAction.action",
	        dataType: "html",
	        data: "technologyId="+technologyValue+"&seniorityId="+seniorityValue,
	        success: function(datos){
	        			//availableTDId.innerText = datos; 
	        			availableTDId.innerHTML = "<span>"+datos+"</span><input type='hidden' name='newQuestionQtyDTOMap["+ indiceEnMap + "].totalAmountOfQuestions' value='"+datos+"'/>";
	        			
	        			//"<input type='text' disabled='disabled' name='newQuestionQtyDTOMap["+ indiceEnMap + "].totalAmountOfQuestions' value='"+datos+"'/>" +
	        			//availableTDId.innerText = datos;
	     			}	
	    });
	});
    
    
}

