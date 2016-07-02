var channelName = "BLOODYDARKLAND";//"UCn6IliUYlAmxIeUAGvXTwig";
var vidWidth= 854;
var vidHeight= 480;
var vidResults= 1;
$(document).ready(function(){
	$.get(
	"https://www.googleapis.com/youtube/v3/channels",{
		part: 'contentDetails',
		forUsername: channelName,
		key: 'AIzaSyAX4uAdnfdUJeZxTMXlmmuuXmwborSRlwI'},
		function(data){
			$.each(data.items, function(i, item){
				console.log(item);
				pid = item.contentDetails.relatedPlaylists.uploads;
				getVids(pid);
			})
		}
		);
	function getVids(pid){
		$.get(
				"https://www.googleapis.com/youtube/v3/playlistItems",{
					part: 'snippet',
					maxResults: vidResults,
					playlistId: pid,
					key: 'AIzaSyAX4uAdnfdUJeZxTMXlmmuuXmwborSRlwI'},
					function(data){
						var output;
						$.each(data.items, function(i, item){
							console.log(item);
							videoTitle = item.snippet.title;
							videoId = item.snippet.resourceId.videoId;

							output = '<li>'+videoTitle+'<br><iframe height="'+vidHeight+'" width="'+vidWidth+'" src=\"//www.youtube.com/embed/'+videoId+'\"></iframe></li>';
							$('#results').append(output);
						})
					}
					);
	}
});
	