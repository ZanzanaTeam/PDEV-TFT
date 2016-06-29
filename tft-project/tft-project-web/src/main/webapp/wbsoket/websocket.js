var socket = new WebSocket("ws://localhost:18080/tft-project-web/actions");
socket.onmessage = onMessage;

var id;
var liveScore;
var set1 = new Array();
var set2 = new Array();
var currentSet1=0;
var currentSet2=0;
var point1=0;
var point2=0;
var service1= 0;
var service2= 0;


function onMessage(event) {
    var device = JSON.parse(event.data);
    if (device.action === "add") {
        printDeviceElement(device);
    }
    if (device.action === "refresh") {
    	document.getElementById(device.id).remove();
        printDeviceElement(device);
    }
}

function printDeviceElement(device) {
    var content = document.getElementById("content");
    getData(device.score);
	
    var deviceDiv = document.createElement("div");
    deviceDiv.setAttribute("id", device.id);
    deviceDiv.setAttribute("class", "device " + device.id);
    deviceDiv.innerHTML = refresh(device);
    content.appendChild(deviceDiv);
}

function refresh(match){
	var td1 ="";
	var td2 ="";
	
	for(var i=0 ; i < set1.length ; i++){
		td1 += '<td>'+set1[i]+'</td>';
		td2 += '<td>'+set2[i]+'</td>';
	}
	html_service_1 = (service1 == 0 ) ? '<td width="18px"></td>' : '<td width="18px"><img src="img/ball.png" width="16" height="16"/></td>' ; 
	html_service_2 = (service2 == 0 ) ? '<td width="18px"></td>' : '<td width="18px"><img src="img/ball.png" width="16" height="16"/></td>' ; 

	html = 	'<div class="col-md-4"><div class="container-liveScore"><table class="liveScore">'+
			'<tr><th colspan="7">'+match.tour+' - '+match.competition+'</th></tr>'+
			'<tr>'+
				html_service_1+
				'<td width="60%">'+match.player1+'</td>'+
				td1+
				'<td>'+currentSet1+'</td>'+
				'<td class="current" >'+point1+'</td>'+
			'</tr><tr style="border-top: 1px #000 solid;">'+
				html_service_2+
				'<td width="60%"><span class="name">'+match.player2+'</span></td>'+
				td2+
				'<td>'+currentSet2+'</td>'+
				'<td class="current" >'+point2+'</td></tr>'+
				'<tr><th colspan="7" ><a href="http://localhost:18080/tft-project-web/location.jsf?id='+match.courtId+'">'+match.court+'</a></th></tr>'+
			'</table></div></div>';
	return html;
}

function getData(liveScore){
	set1 = new Array();
	set2 = new Array();
	var arrayLiveScore = liveScore.split('T');
	var arraySet = arrayLiveScore[0].split('S');
	for(var i=0 ; i < arraySet.length ; i++){
		set1[i] = arraySet[i].split(':')[0];
		set2[i] = arraySet[i].split(':')[1];
	}

	currentSet1 = arrayLiveScore[1].split(':')[0];
	currentSet2 = arrayLiveScore[1].split(':')[1];

	point1 = arrayLiveScore[2].split(':')[0];
	point2 = arrayLiveScore[2].split(':')[1];
	
	service1 = arrayLiveScore[3].split(':')[0];
	service2 = arrayLiveScore[3].split(':')[1];
}

$('document').ready(function(){

});

