(function() {
	window.BMAP_PROTOCOL = "https";
	window.BMapGL_loadScriptTime = (new Date).getTime();
	document.write(
		'<script type="text/javascript" src="https://api.map.baidu.com/getscript?type=webgl&v=1.0&ak=hvv31Hzt069G4NGGeDbpobdTOoDQhI3r&services=&t=20230529114224"></script>'
		);
	document.write(
		'<link rel="stylesheet" type="text/css" href="https://api.map.baidu.com/res/webgl/10/bmap.css"/>');
	console.log(window.BMapGL_loadScriptTime)
})();