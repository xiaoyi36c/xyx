

(function(){
	console.log(1)
	window.BMAP_PROTOCOL = "https";
	window.BMapGL_loadScriptTime = (new Date).getTime();
	document.write(
		'<script type="text/javascript" src="http://api.map.baidu.com/api?v=4.0&ak=hvv31Hzt069G4NGGeDbpobdTOoDQhI3r&services=&t=20230529114224&callback1=onBMapCallback"></script>'
		);
	document.write(
		'<link rel="stylesheet" type="text/css" href="https://api.map.baidu.com/res/webgl/10/bmap.css"/>');
})()

