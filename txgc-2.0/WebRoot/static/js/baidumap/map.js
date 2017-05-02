
	// 百度地图API功能
	var map = new BMap.Map("mapDiv");    // 创建Map实例
	map.centerAndZoom(new BMap.Point(106.33, 29.35), 14);  // 初始化地图,设置中心点坐标和地图级别
	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
	map.setCurrentCity("重庆");          // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	
	//marker点击事件
	function attribute(e){
		var p = e.target;
		alert("marker的位置是" + p.getPosition().lng + "," + p.getPosition().lat);    
	}	
	
	// 编写自定义函数,创建标注
	function addMarker(point,label){
	  var marker = new BMap.Marker(point);
	  marker.addEventListener("click",attribute);
	  marker.setLabel(label);
	  map.addOverlay(marker);
	}
	
	
	/*
		// 随机向地图添加25个标注
	var bounds = map.getBounds();
	var sw = bounds.getSouthWest();
	var ne = bounds.getNorthEast();
	var lngSpan = Math.abs(sw.lng - ne.lng);
	var latSpan = Math.abs(ne.lat - sw.lat);
	for (var i = 0; i < 10; i ++) {
		var point = new BMap.Point(sw.lng + lngSpan * (Math.random() * 0.7), ne.lat - latSpan * (Math.random() * 0.7));
		var label = new BMap.Label("我是id="+i,{offset:new BMap.Size(20,-10)});
		addMarker(point,label);
	}
*/