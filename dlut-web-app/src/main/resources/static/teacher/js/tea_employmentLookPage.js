$(document).ready(function(){
	var onOff=true;
	getContent(1);
	function getContent(now){
		$("#page").attr({"pagelistcount":$("#number").val()||10});
			$.ajax({//请求数据
			type:"get",
			url:"/app/teacher/dispatchLookPage",
			data:{
                stuNo:$("[name=stuNo]").val(),
                stuName:$("[name=stuName]").val(),
                majorCode:$("[name=majorCode]").val(),
                eduDegree:$("[name=eduDegree]").val(),
                eduMode:$("[name=eduMode]").val(),
                endStatus:$("[name=endStatus]").val(),
				page:now-1,//当前页
				size:$("#number").val()||10//一页显示几个数据
			},
			success:function(obj){
                var stuStatus="" ;
				var inner="";
				$.each(obj.content, function(i,e) {
                    if ($(e)[0].stuStatus=="0"){
                        stuStatus = "否";
                    }else {
                        stuStatus = "是";
                    }
                    var recName = "无";
                    var recAddress = "无";
                    if($(e)[0].recName!=null){
                        recName = $(e)[0].recName;
                    }
                    if($(e)[0].recAddress!=null){
                        recAddress = $(e)[0].recAddress;
                    }
					inner +=
                        '<tr class="text-center">' +
                        '<td>'+stuStatus+'</td>' +
                        '<td>'+$(e)[0].statusName +'</td>' +
                        '<td>'+$(e)[0].studentInfo.stuNo+'</td>' +
                        '<td>'+$(e)[0].studentInfo.name+'</td>' +
                        '<td>'+$(e)[0].studentInfo.majorCodeName+'</td>' +
                        '<td>'+$(e)[0].studentInfo.eduModeName+'</td>' +
                        '<td>'+recName+'</td>' +
                        '<td>'+recAddress+'</td>' +
                        '<td><a class="btn btn-default">详情</a></td>' +
                        '</tr>';

				});
				$("tbody").html(inner);
				if(onOff){
					$("#page").initPage(obj.totalElements,1,getContent);
				}
				onOff=false;

			},
			error:function(err) {
			    alert("请求不成功！")
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



