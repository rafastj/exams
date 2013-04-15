function startTimer() {
	//endTime = document.getElementById("timer").value;					
    running = true;
    now = new Date();
    now = now.getTime();
    // change last multiple for the number of minutes
    endTime = now + (1000 * 60 * amountOfMinutes);
    showCountDown();
}
function showCountDown() {
    var now = new Date();
    now = now.getTime();
    if (endTime - now <= 0) {
        if(running==true){
        	/*
        	 * aca javascript tendria que ejecutar en la misma pantalla sin abrir una nueva el siguiente action de struts
        	 */
        }
		
		//alert("Time is up.  Put down your pencils.")
    } else {
        var delta = new Date(endTime - now);
        
        var miliseconds = endTime - now;
        var seconds = miliseconds/1000;
        var minutes = seconds/60;
        
        //var theHour = delta.getHours();
        //var theMin = delta.getMinutes();
        var theMin = Math.floor(minutes);
        var theSec = delta.getSeconds();
        
        
        //cuanto excede 60 minutos vuelve a cero entonces agregue theHour
        var theTime = theMin;
        theTime += ((theSec < 10) ? ":0" : ":") + theSec;
        document.getElementById("timerDisplay").innerHTML = theTime;
        if (running) {
            timerID = setTimeout("showCountDown()",1000);
        }
    }
}
function stopTimer() {
    clearTimeout(timerID);
    running = false;
    //document.forms[0].timerDisplay.value = "0:00";
}
