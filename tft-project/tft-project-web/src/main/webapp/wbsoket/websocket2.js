var socket = new WebSocket("ws://172.16.91.20:18080/tft-project-web/actions");
socket.onmessage = onMessage;

var id=3000;
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

    	if(device.id == id){
    		document.getElementById('content').innerHTML = "";
    		printDeviceElement(device);
    	}

    }
    if (device.action === "refresh") {
    	if(device.id == id){
    		document.getElementById('content').innerHTML = "";
    		printDeviceElement(device);
    	}
    }
}

function updateLiveScore(id , score) {
    var id = id+"";
    var liveScore=score;
    var DeviceAction = {
        action: "updateLiveScore",
        id: id,
        liveScore: liveScore
    };
    socket.send(JSON.stringify(DeviceAction));
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
				'<td width="10%"><input type="button" value="+" id="add1" /></td>'+
			'</tr><tr>'+
				'<td width="50%"><span class="name">'+match.player2+'</span></td>'+
				td2+
				'<td width="10%">'+currentSet2+'</td>'+
				'<td width="10%">'+point2+'</td>'+
				'<td width="10%"><input type="button" value="+" id="add2" /></td>'+
			'</tr></table>';
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
	$(document).on('click','.match',function(){
		id = $(this).attr('data');
		var liveScore = match[id].liveScore;
		getData(liveScore);
		refresh();
	});
	function setData(){
			var liveScore = "";
			
			if(set1.length >= 1){
				liveScore += set1[0]+":"+set2[0];
			}
			
			for(var i=1 ; i < set1.length ; i++){
				liveScore += "S"+set1[i]+":"+set2[i];
			}

			liveScore += "T"+currentSet1+":"+currentSet2;
			liveScore += "T"+point1+":"+point2;
			liveScore += "T"+service1+":"+service2;
			console.log(id +"=>"+ liveScore);	
			updateLiveScore(id,liveScore);
		}
		
	$(document).on('click','#add1',function(){
		if(point1=="0") point1="15";
		else if(point1=="15") point1="30";
		else if(point1=="30") point1="40";
		else if(point1=="40" && point2!= "40" && point2 != "AV" ){point1="0"; point2="0"; currentSet1++; var aux  = service1;
		service1 = service2;
		service2 = aux;}
		else if(point1=="40" && point2 == "40" ) point1="AV";
		else if(point1=="40" && point2 == "AV" ) {point1="40"; point2="40"; }
		else if(point1=="AV" && point2 == "40" ) {point1="0"; point2="0"; currentSet1++; var aux  = service1;
		service1 = service2;
		service2 = aux;}
		if(currentSet1==6 || currentSet1==7)
		{	
			if(set1[0] == 0 && set2[0] == 0){
				set1[0] = currentSet1;
				set2[0] = currentSet2;
			}else{
				set1[set1.length] = currentSet1;
				set2[set2.length] = currentSet2;
			}
			point1=0; point2=0; currentSet1=0; currentSet2=0;
		}
		//refresh();
		setData();
	});

	$(document).on('click','#add2',function(){	
		if(point2=="0") point2="15";
		else if(point2=="15") point2="30";
		else if(point2=="30") point2="40";
		else if(point2=="40" && point1 != "40" && point1 != "AV"  ){point2="0"; point1="0"; currentSet2++; var aux  = service1;
		service1 = service2;
		service2 = aux;}
		else if(point2=="40" && point1 == "40" ) point2="AV";
		else if(point2=="40" && point1 == "AV" ) { point2="40"; point1="40"; }
		else if(point2=="AV" && point1 == "40" ) { point2="0"; point1="0"; currentSet2++; var aux  = service1;
		service1 = service2;
		service2 = aux;}
		if(currentSet2==6 || currentSet2==7)
		{	
			if(set1[0] == 0 && set2[0] == 0){
				alert("1er set");
				set1[0] = currentSet1;
				set2[0] = currentSet2;
			}else{
				set1[set1.length] = currentSet1;
				set2[set2.length] = currentSet2;
			}
			point1=0; point2=0; currentSet1=0; currentSet2=0;
		}
		setData();
	});
});

