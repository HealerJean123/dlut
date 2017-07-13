function cakes(id,title,datas){
	var area=[];
	for( var i=0;i<datas.length;i++){
		area.push(datas[i].name);
		cake(id,title,area,datas);
	}
}
function cake(id,title,name,datas){
	var el = echarts.init(document.getElementById(id));
    elData = {
	    title : {
	        text: title,
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        orient : 'vertical',
	        x : 'left',
	        data:name
	    },
	    calculable : true,
	    series : [
	        {
	            name:title,
	            type:'pie',
	            radius : '55%',
	            center: ['50%', '60%'],
	            data:datas
	        }
	    ]
	};             
	el.setOption(elData);
}

function map(id,title,datas){
	  var el = echarts.init(document.getElementById(id));
       elData = {
    title : {
        text: title,
        x:'center'
    },
    tooltip : {
        trigger: 'item'
    },
    legend: {
        orient: 'vertical',
        x:'left',
        data:[title]
    },
    dataRange: {
        x: 'left',
        y: 'bottom',
        splitList: [
            {start: 1500},
            {start: 900, end: 1500},
            {start: 310, end: 1000},
            {start: 200, end: 300},
            {start: 10, end: 200, label: '10 到 200（自定义label）'},
            {start: 5, end: 5, label: '5（自定义特殊颜色）', color: 'black'},
            {end: 10}
        ],
        color: ['#E0022B', '#E09107', '#A3E00B']
    },
    series : [
        {
            name: title,
            type: 'map',
            mapType: 'china',
            roam: false,
            itemStyle:{
                normal:{
                    label:{
                        show:true,
                        textStyle: {
                           color: "rgb(249, 249, 249)"
                        }
                    }
                },
                emphasis:{label:{show:true}}
            },
            data:datas
        }
    ]
};
el.setOption(elData);
}
