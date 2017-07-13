$(document).ready(function(){
	/*//按钮的状态
	if($("#status").val()!="00"){
		$("input[type=submit]").prop({"disabled":true})
	}else{
		$("input[type=submit]").prop({"disabled":false})
	};*/
	//有没有确认信息
	if($("#status").val()=="02"){
		$("[type=submit]").prop({"disabled":true});
		var onOff=$("#val").attr("value");
		console.log(onOff)
		/*为空，则就没有申请过，所以*/
		if(onOff){
			//如果申请过
			$(".look").css({"display":"block"})
			$(".apply").css({"display":"none"})
		}else{
			//没有申请过
			$(".look").css({"display":"none"})
			$(".apply").css({"display":"block"})
		}
	
		$(".look").on("click",function(){
			//点击查看信息
			window.location.href="/app/student/reassignApplyInfo";
		})
		$(".apply").on("click",function(){
			//点击查看信息
			window.location.href="/app/student/employmentApply.html";
		})
	}else{
		$("[type=submit]").prop({"disabled":false})
	}
	

})
