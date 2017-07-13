$(document).ready(function(){
	var onOff=true;
	getContent(1);
	function getContent(now){//分页功能
		$("#page").attr({"pagelistcount":$("#number").val()||10});
			$.ajax({//请求数据
			type:"get",
			url:"/app/",
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
//				        var publishTime = "";
                        /*if($(e)[0].publishTime!=null && $(e)[0].publishTime !=""){
                            var time = $(e)[0].publishTime;
                            var day = time.dayOfMonth;
                            var month = time.monthValue;
                            if(day<10){day="0"+day;}
                            if(month<10){month="0"+month;}
                            publishTime = time.year+"-"+month+"-"+day;
                        }*/
                        inner +='<tr class="text-center"><td>'+$(e)[0].recName+'</td><td>'+$(e)[0].name+'</td><td>'+$(e)[0].typeName+'</td><td>'+$(e)[0].degreeName+'</td><td>'+$(e)[0].city+'</td><td>'+publishTime+'</td><td><a class="btn btn-default" href="/app/student/stuJobDetail?id='+$(e)[0].id+'">详情</a></td></tr>';
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
