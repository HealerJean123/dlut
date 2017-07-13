$(document).ready(function(){
	var onOff=true;
	getContent(1);
	function getContent(now){//分页功能
		$("#page").attr({"pagelistcount":$("#number").val()||10});
			$.ajax({//请求数据
			type:"get",
			url:"/app/company/findOffer",
			data:{
				stuNo:$("[name=stuNo]").val(),
				stuName:$("[name=stuName]").val(),
				sTime:$("[name=sTime]").val(),
				eduDegree:$("[name=eduDegree]").val(),
				academy:$("[name=academy]").val(),
				major:$("[name=major]").val(),
				graduationTime:$("[name=graduationTime]").val(),
				offerStatus:"04",
				page:now-1,//当前页
				size:$("#number").val()||10//一页显示几个数据
			},
			success:function(obj){
				//$("#checkAll").prop({"checked":false});
				var inner="";
				if(obj.content==null || obj.content==""){
				    inner="暂无数据";
				}else{
				    $.each(obj.content, function(i,e) {
				        var date = "";
                        if($(e)[0].signTime!=null && $(e)[0].signTime !=""){
                            var time = $(e)[0].signTime;
                            var day = time.dayOfMonth;
                            var month = time.monthValue;
                            if(day<10){day="0"+day;}
                            if(month<10){month="0"+month;}
                            date = time.year+"-"+month+"-"+day;
                        }
                		inner +='<tr class="text-center"><td>'+$(e)[0].department+'</td><td>'+$(e)[0].eduName+'</td><td>'+$(e)[0].majorName +'</td><td>'+$(e)[0].stuNo+'</td><td>'+$(e)[0].realName+'</td><td>'+date+'</td><td>'+$(e)[0].stuReceivingStatusName+'</td><td><a href="/app/company/signDetail?id='+$(e)[0].id+'">查看</a></td></tr>';
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
})
