$(document).ready(function(){
	var onOff=true;
	getContent(1);
	function getContent(now){//分页功能
		$("#page").attr({"pagelistcount":$("#number").val()||10});
			$.ajax({//请求数据
			type:"get",
			url:"/app/student/stuFindJobFair",
			data:{
				fairType:$("[name=fairType]").val(),
                fairTime:$("[name=fairTime]").val(),
				page:now-1,//当前页
				size:$("#number").val()||10//一页显示几个数据
			},
			success:function(obj){
				var inner="";
				if(obj.content==null || obj.content==""){
				    inner="暂无数据";
				}else{
				    $.each(obj.content, function(i,e) {
				        var fairStartTime = "";
                        if($(e)[0].fairStartTime!=null && $(e)[0].fairStartTime !=""){
                            var time = $(e)[0].publishTime;
                            var day = time.dayOfMonth;
                            var month = time.monthValue;
                            if(day<10){day="0"+day;}
                            if(month<10){month="0"+month;}
                            fairStartTime = time.year+"-"+month+"-"+day;
                        }
                        var type = ""
                        if($(e)[0].type=="01"){
                            type = "大型招聘会";
                        }else{
                            type = "组团招聘会";
                        }
                        inner +='<tr class="text-center"><td>'+$(e)[0].name+'</td><td>'+type+'</td><td>'+$(e)[0].location+'</td><td>'+fairStartTime+'</td><td>'+$(e)[0].recNum+'</td><td>'+$(e)[0].jobNum+'</td><td><a class="btn btn-default" href="/app/portals/newspage.html?id='+$(e)[0].id+'">详情</a></td></tr>';
                	});
				}

				$("tbody").html(inner);
				if(onOff){
					$("#page").initPage(obj.totalElements,1,getContent);
				}
				onOff=false;

			},
			error:function(err) {
			    $("#tc").find("#label").html("请求不成功！");
      			$("#tc").modal("show")
			}
		});
	}

	$("#btn").on("click",function(){
    	onOff=true;
    	getContent(1);
    })

    $("#number").on("change",function(){
    	onOff=true;
    	getContent(1);
    })


   $(".list1").on("click",function(){
       	window.location.href="/app/student/stuCareerFair";
   })
   $(".list2").on("click",function(){
        window.location.href="/app/student/stuCareerFair2";
   })
})
