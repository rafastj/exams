function deleteTR(tr_id){
	$('#'+tr_id.id).remove();
}

function createRow(){
	var indice = $('#mainTable tr').length;
	var tr_id ="tr_id_"+indice;
	var technologyTDId = "technologyTD_" + indice;
	var seniorityTDId = "seniorityTD_" + indice;
	var availableTDId = "availableTD_" + indice;
	var inputTDId = "inputTD_" + indice;
	var deleteTDId = "deleteTD_" + indice;
	
	var trHTML = "<tr id='"+tr_id+"'><td align='left' id='" + technologyTDId + "'/><td id='" + seniorityTDId + "'/><td id='" + availableTDId + "' /><td id='" + inputTDId + "' /><td id='" + deleteTDId + "' /></tr>";
	
	$("#mainTable").append(trHTML);
	
	var technologySELECTId = "technologySELECT_" + indice;
	var senioritySELECTId = "senioritySELECT_" + indice;
	var deleteId = "delete_" + indice;
	
	$('#' + technologyTDId).append('<select id="' + technologySELECTId + '" name="newQuestionQtyDTOMap['+ indiceEnMap + '].technologyId" onchange="changeAmountOfTotalQuestionsNewLine('+ technologySELECTId + ','+ senioritySELECTId + ','+ availableTDId + ','+indiceEnMap+');"></select>');
	$('#' + seniorityTDId).append('<select id="' + senioritySELECTId + '" name="newQuestionQtyDTOMap['+ indiceEnMap + '].seniorityId" onchange="changeAmountOfTotalQuestionsNewLine('+ technologySELECTId + ','+ senioritySELECTId + ','+ availableTDId +','+indiceEnMap+');"></select>');
	$('#' + deleteTDId).append('<input id="'+deleteId+'" type="checkbox" onclick="javascript:deleteRow('+indice+')" value="Delete"/>');
	//$('#' + availableTDId).append('<input type="hidden" name="newQuestionQtyDTOMap['+ indiceEnMap + '].totalAmountOfQuestions" value="77"/>');
	//$('#' + availableTDId).append('<p>matias</p>');
	$('#' + inputTDId).append('<input type="text" name="newQuestionQtyDTOMap['+ indiceEnMap + '].qtyQuestions"/>');
	
	$.each(tecnologias, function(key, value) {   
	     $("#" + technologySELECTId)
	          .append($('<option>', { value : value[0] })
	          .text(value[1])); 
	});
	
	$.each(seniorities, function(key, value) {   
	     $("#" + senioritySELECTId)
	          .append($('<option>', { value : value[0] })
	          .text(value[1])); 
	});
	
	// TODO: Cargar las preguntas disponibles segun el technology/seniority seleccionado!
	//$('#' + availableTDId).append('<input type="text" name="newQuestionQtyDTOMap['+ indiceEnMap + '].totalAmountOfQuestions"/>');
	
	
	
	document.getElementById('idNewTechnology').value = true;
	indiceEnMap++;
}

function update(val){
	  var url="addTechnologyToTestModel.action?testModel="+val; //tendria que pasarle por parametro el test model
	  var vReturn = window.showModalDialog(url, self, "_blank","height=400,width=600,status=no,location=no,toolbar=no,directories=no,menubar=no,scrollbars=1");			  
}
function deleteRow(index){
	var delete_class = document.getElementById('tr_id_'+index);
	if(delete_class.className == 'delete'){
		$('#tr_id_'+index).css("background-color","white");
		$('#tr_id_'+index).removeClass("delete");	
	}else{
		$('#tr_id_'+index).css("background-color","red");
		$('#tr_id_'+index).addClass("delete");
	}
}
