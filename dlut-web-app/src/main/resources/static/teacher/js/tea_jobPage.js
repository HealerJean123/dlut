

   $(document).ready(function(){
	var onOff=true;
	getContent(1);
	function getContent(now){
		$("#page").attr({"pagelistcount":$("#number").val()||10});
			$.ajax({//请求数据
			type:"get",
			url:"/app/teacher/jobDestinationList",
			data:{
				stuNo:$("[name=stuNo]").val(),
				name:$("[name=name]").val(),
				departmentId:$("[name=departmentId]").val(),
				eduDegree:$("[name=eduDegree]").val(),
				endDate:$("[name=endDate]").val(),
				stuStatus:$("[name=stuStatus]").val(),
				majorCode:$("[name=majorCode]").val(),
				recName:$("[name=recName]").val(),
				page:now-1,//当前页
				size:$("#number").val()||10//一页显示几个数据
			},
			success:function(obj){
				var inner="";
				$.each(obj.content, function(i,e) {
				var recName = "暂无就业";
				var destinationTypeName = "无";
				if($(e)[0].recName!=null){
				    recName = $(e)[0].recName;
				}
				if($(e)[0].destinationType!=null){
				   destinationTypeName = $(e)[0].destinationTypeName;
				}
					inner +='<tr  class="text-center"><td>'+$(e)[0].studentInfo.stuNo+'</td><td>'+$(e)[0].studentInfo.name +'</td><td>'+$(e)[0].studentInfo.department+'</td><td>'+$(e)[0].studentInfo.majorName+'</td><td>'+$(e)[0].studentInfo.eduDegreeName+'</td><td>'+$(e)[0].studentInfo.endDate+'</td><td>'+destinationTypeName+'</td><td>'+recName+'</td><td><a class="btn btn-default"  href="/app/teacher/jobAdd/?id='+$(e)[0].id+'">修改</a></td></tr>';
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


$("#one").on("click",function(){
	$.ajax({
		type:"put",
		url:"/app/teacher/dispatchAll?graduateDate=2017",
		success:function(data){
			$("#tc").find("#label").html(data);
			$("#tc").modal("show")
		},
		error:function(err){
			$("#tc").find("#label").html("一键派遣请求不成功！");
			$("#tc").modal("show")
		}
	});

})
})
