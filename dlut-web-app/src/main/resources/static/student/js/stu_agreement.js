$(document).ready(function(){
	
	var onOff=$("#val1").attr("value");
	/*为空，则就没有申请过，所以*/
	if(onOff){
		//如果申请过
		$(".location a").css({"display":"block"})
		$(".location button").css({"display":"none"})
	}else{
		//没有申请过
		$(".location a").css({"display":"none"})
		$(".location button").css({"display":"block"})
	}
	
	$(".location a").on("click",function(){
		//点击查看信息
		window.location.href="/app/student/violateApplicationInfo";
	})
})