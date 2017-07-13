$(document).ready(function(){
	var onOff=true;
	getContent(1);
	function getContent(now){//分页功能
		$("#page").attr({"pagelistcount":$("#number").val()||10});
			$.ajax({//请求数据
			type:"get",
			url:"/app/teacher/RecOffers",
			data:{
				stuNo:$("[name=stuNo]").val(),
				realName:$("[name=realName]").val(),
				department:$("[name=department]").val(),
				eduDegree:$("[name=eduDegree]").val(),
				endDate:$("[name=endDate]").val(),
				stuReceivingStatus:$("[name=stuReceivingStatus]").val(),
				majorCode:$("[name=majorCode]").val(),
				recName:$("[name=recName]").val(),
				page:now-1,//当前页
				size:$("#number").val()||10//一页显示几个数据
			},
			success:function(obj){
				$("#checkAll").prop({"checked":false});
				var inner="";
				$.each(obj.content, function(i,e) {
					inner +='<tr class="text-center"><td><input type="checkbox" class="checkbox" value="'+$(e)[0].id+'" name="id"/></td><td>'+$(e)[0].stuNo+'</td><td>'+$(e)[0].realName +'</td><td>'+$(e)[0].recName+'</td><td>'+$(e)[0].department+'</td><td>'+$(e)[0].majorName+'</td><td>'+$(e)[0].eduName+'</td><td>'+$(e)[0].endDate+'</td><td>'+$(e)[0].stuReceivingStatusName+'</td><td>'+$(e)[0].auditStatusName+'</td><td><a href="/app/teacher/RecOfferById?id='+$(e)[0].id+'" class="btn btn-default">详情</a></td><td><a href="/app/teacher/agreerecOffer?id='+$(e)[0].id+'" class="btn btn-default">审核通过</a></td></tr>';

				});
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
//点击全选--------------------------------
var box=[];
$("#checkAll").on("click",function(){//点击全选按钮
	var isTrue= $(this).prop("checked")//
	$(".checkbox").each(function(i,e){
		$(this).prop("checked",isTrue)//所有的跟全选按钮保持一致
	})
})
$("tbody").on("click",$(".checkbox"),function(){
	var j=0;
	$(".checkbox").each(function(i,e){
		if($(".checkbox").eq(i).prop("checked")==false){
			j++;
		}
	})
	if(j>0){
		$("#checkAll").prop({"checked":false})
	}else{
		$("#checkAll").prop({"checked":true})
	}
})

$("#batch").on("click",function(){
	box=[];
	$(".checkbox").each(function(i,e){
		if($(this).prop("checked")){
			box.push($(this).val())
		}
	})
	$.ajax({
		type:"get",
		url:"/app/teacher/auditBatch",
		data:{
			"box":box
		},
		traditional: true,
		success:function(data){
			$("#tc").find("#label").html("批量通过");
    	    $("#tc").modal("show")
            onOff=true;
			getContent($(".pageItemActive").html());
		},
		error:function(){
		$("#tc").find("#label").html("失败！");
        $("#tc").modal("show")
		}
	})
})
})

