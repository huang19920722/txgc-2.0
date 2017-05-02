
	var maxPointLength=4;//描点最大数量0为无穷
	// 百度地图API功能
	var map = new BMap.Map("mapDiv");    // 创建Map实例
	map.centerAndZoom(new BMap.Point(106.33, 29.35), 14);  // 初始化地图,设置中心点坐标和地图级别
	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
	map.setCurrentCity("重庆");          // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	

	var allArray=getVisiableArray();
	
	var allVisiableArray=getAllVisiableArray(new Array(),allArray);//可用序列

	//map的点击事件
	map.addEventListener("click", function(e){   //点击事件    
		//alert(e.point.lng + ", " + e.point.lat);  
		    if(!e.overlay){  
		    	var markerList=map.getOverlays();
		    	console.info(map.getOverlays());

		       if(markerList != undefined &&markerList!=null && markerList.length>=4){
		    	   
		       }else{
		    	   var sttr=0;
		    	   for(var y=0;y<allVisiableArray.length;y++){
		    		   if(allVisiableArray[y]!=null && allVisiableArray[y]!=0){
		    			   sttr=allVisiableArray[y];
		    			   allVisiableArray[y]=0;
		    		   }
		    	   }
		    	   var label = new BMap.Label(sttr,{offset:new BMap.Size(20,-10)});
		    	   addMarker(e.target.getPosition(),label)
		       }  
		    }  
		});
	
	//返回完整的序列
	function getVisiableArray(){
		var visiableArray=new Array(); 
		for(var i=0;i<maxPointLength;i++){
			visiableArray[i]=i+1;
		}
		return visiableArray;
	}
	//初始化可用序列
	function getAllVisiableArray(preArray,afterArray){
		if(preArray!=null&&preArray.length>0){
			for(var u=0;u<=preArray.length;u++){
				afterArray[u]=0;
			}
		}
		return  afterArray;
	}

	
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