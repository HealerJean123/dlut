    $(document).ready(function(){
	var onOff=true;
	getContent(1);
	function getContent(now){
		$("#page").attr({"pagelistcount":$("#number").val()||10});
		var size = $("#number").val()||10;
			$.ajax({//请求数据
			type:"get",
			url:"/app/teacher/companyList",
			data:{
				name:$("[name=name]").val(),
				nature:$("[name=nature]").val(),
				industry:$("[name=industry]").val(),
				registerTime:$("[name=registerTime]").val(),
				auditStatus:$("[name=auditStatus]").val(),
	            page:now-1,
	            size:$("#number").val()||10
            },
			success:function(obj){
				console.log(obj)
				$("#checkAll").prop({"checked":false});
				var inner="";
				$.each(obj.content, function(i,e) {
					inner +='<tr class="text-center"><td><input type="checkbox" value="'+e.id+'" class="checkbox" /></td><td>'+e.name+'</td><td>'+e.orgCode+'</td><td>'+e.natureName+'</td><td>'+e.industryName+'</td><td>'+e.registerTime+'</td><td>'+e.auditStatusName+'</td><td><a href="/app/teacher/companyOne?id='+e.id+'" class="btn btn-default">修改</a></td></tr>';
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
//-------全选按钮-------------------
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
//===批量通过===============
$("#batch").on("click",function(){
	box=[];
	$(".checkbox").each(function(i,e){
		if($(e).prop("checked")){
			box.push($(e).val())
		}
	})
	$.ajax({
		type:"get",
		url:"/app/teacher/updateList",
		data:{
			"ids":box
		},
		traditional: true,
		success:function(data){
			$("#tc").find("#label").html(data);
 			$("#tc").modal("show")
			onOff=true;
			getContent($(".pageItemActive").html());
		},
		error:function(data){
			$("#tc").find("#label").html("失败！");
			$("#tc").modal("show")
		}
	})
})
//--审核通过按钮----------------------------------------
$("tbody").on("click",".a",function(){
	var arr=$(this).attr("data-val");
	agree(arr);
})
function agree(id){
    $.ajax({
        type:"post",
        url:"",
        data:{id:id},
        success:function(data){
			$("#tc").find("#label").html(data);
			$("#tc").modal("show")
            onOff=true;
			getContent($(".pageItemActive").html());
        },
        error:function(err){
            $("#tc").find("#label").html("请求不成功！");
			$("#tc").modal("show")
        }
    });
}

})


