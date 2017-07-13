    $(document).ready(function(){
	var onOff=true;
	getContent(1);
	function getContent(now){
		$("#page").attr({"pagelistcount":$("#number").val()||10});
			$.ajax({//请求数据
			type:"get",
			url:"/app/company/recCareerList",
			data:{
				fairName:$("[name=fairName]").val(),
				applicationDate:$("[name=applicationDate]").val(),
				startDate:$("[name=startDate]").val(),
				auditsStatus:$("[name=auditsStatus]").val(),
				page:now-1,
				size:$("#number").val()||10
            },
			success:function(obj){
				console.log(obj)
				var inner="";
				$.each(obj.content, function(i,e) {
					inner +='<tr class="text-center"><td>'+e.fairName+'</td><td>'+e.startDate+'</td><td>'+e.applicationDate+'</td><td>'+e.auditsStatusName+'</td><td>'+e.fieldAuditsStatusName+'</td><td><a href="/app/company/findOne?id='+e.id+'" class="btn btn-default">详情</a></td></tr>';
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


