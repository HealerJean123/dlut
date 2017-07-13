$(document).ready(function(){
	var lastValue="";
	$(".val").each(function(i,e){
		$(".val").eq(i).html($(e).parent().parent().parent().find("input").val());
		$(".vals").eq(i).html($(".zhi").eq(i).text());
	})
	//编辑按钮
	$(".edit").on("click",function(){
		$(this).parent().css({"display":"none"})
		$(this).parent().parent().find(".show2").css({"display":"block"});
		lastValue=$(this).parent().next().find(".form-control").val();
	});
	//取消按钮
	$(".cancel").on("click",function(){
		$(this).parent().parent().css({"display":"none"})
		$(this).parent().parent().parent().find(".show1").css({"display":"block"});
		$(this).parent().prev().find(".form-control").val(lastValue);
		return false;
	})
	$(".change").on("click",function(){
		$(".tip").html("修改成功!")
		$('#mo').modal('show');
		
	})
	$(".ok").on("click",function(){
		$.ajax({//请求数据
		type:"post",
		url:"/app/student/updateInformation",
		data:{
			allupdateStatus:"1"
		},
		success:function(data){
            window.location.href="/app/student/stuInformation.html";
		},
		error:function(err){
			console.log(err)
		}
	});
	})
	//模糊查询
	$("#homelandName").select2({
	placeholder:"请输入省市",
	ajax: {
    url: "/app/common/dispatches",
    dataType: 'json',
    type:"get",
    delay: 250,
    data: function (params,size) {
    	var str=params.term;
    	var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");
    	if(reg.test(str)){
    		  return {
		        areaName:params.term,
				size:30
		      };
    	}
    },
    processResults: function (data) {
      return {
        results:data
      };
    },
    cache: true
},
minimumInputLength: 1,
})
$("#homelandName").select2("val","1")

})
