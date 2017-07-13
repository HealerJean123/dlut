$(document).ready(function(){
	var onOff=true;
	getContent(1);
	function getContent(now){
		$("#page").attr({"pagelistcount":$("#number").val()||10});
		var size = $("#number").val()||10;
			$.ajax({//请求数据
			type:"get",
			url:"/app/company/fieldList",
			data:{
				fieldSize:$("[name=fieldSize]").val(),
				fieldRequire:$("[name=fieldRequire]").val(),
				fieldUsing:$("[name=fieldUsing]").val(),
				auditStatus:$("[name=auditStatus]").val(),
				startDate:$("[name=startDate]").val(),
				page:now-1,//当前页
				size:$("#number").val()||10//一页显示几个数据
            },
			success:function(obj){
				console.log(obj)
				var inner="";
				$.each(obj, function(i,e) {
					inner +='<tr class="text-center"><td>'+e.fieldUsingName+'</td><td>'+e.startDate+'</td><td>'+e.startTime+'</td><td>'+e.endTime+'</td><td>'+(e.fieldUsingName?e.fieldUsingName:'')+'</td><td>'+e.fieldRequireName+'</td><td>'+e.fieldSizeName+'</td><td>'+e.auditStatusName+'</td><td><a class="btn btn-default" href="/app/company/findFieldOne?id='+e.id+'">详情</a></td></tr>';
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

})


