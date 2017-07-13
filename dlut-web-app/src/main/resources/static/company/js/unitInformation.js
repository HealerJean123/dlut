$(document).ready(function() {
	//保存一下原有的数值{name：value}
	var obj={};
	$.each($("[name]"), function(i,e) {
		obj[e.name]=$(e).val();
		$(e).on("input",function(){
			$(e).css("color","red")
		})
	});

//	//保存
//	$(".unitSave").on("click",function(){
//		var obj2={};
//		$.each($("[name]"), function(i,e) {
//			obj2[e.name]=$(e).val()
//		});
//		$.ajax({
//			type:"",
//			url:"",
//			data:obj2,
//			success:function(json){
//				//成功之后，让所有字体都不变成黑色
//				if(json){
//					$.each($("[name]"), function(i,e) {
//						$(e).css("color","#000")
//					});
//				}
//
//			}
//		})
//	})
	$(".unitCancel").on("click",function(){
		//取消
		for(var attr in obj){
			$.each($("[name]"), function(i,e) {
				if($(e).attr("name")==attr){
					$(e).val(obj[attr]);
					$(e).css("color","#000")
				}
			})
		}
	})
})
