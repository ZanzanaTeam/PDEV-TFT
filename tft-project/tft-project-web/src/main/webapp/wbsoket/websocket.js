var socket = new WebSocket("ws://172.16.91.20:18080/tft-project-web/actions");
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
		td1 += '<td width="10%">'+set1[i]+'</td>';
		td2 += '<td width="10%">'+set2[i]+'</td>';
	}

	html = 	'<table><tr>'+
				'<td width="50%"><span class="name">'+match.player1+'</span></td>'+
				td1+
				'<td width="10%">'+currentSet1+'</td>'+
				'<td width="10%">'+point1+'</td>'+
			'</tr><tr>'+
				'<td width="50%"><span class="name">'+match.player2+'</span></td>'+
				td2+
				'<td width="10%">'+currentSet2+'</td>'+
				'<td width="10%">'+point2+'</td>'+
			'</tr></table>';
	return html;
}

function getData(liveScore){
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

