$(document).ready(function(){
	console.log("-----")
	var onOff=true;
	getContent(1);
	function getContent(now){//分页功能
		$("#page").attr({"pagelistcount":$("#number").val()||10});
			$.ajax({//请求数据
			type:"get",
			url:"/app/company/showTemplate",
			data:{
				stuType:$("[name=stuType]").val(),
				name:$("[name=name]").val(),
				page:now-1,//当前页
				size:$("#number").val()||10//一页显示几个数据
			},
			success:function(obj){
				console.log(obj)
				var inner="";
				if(obj==null || obj==""){
				    inner="暂无数据";
				}else{
				    $.each(obj, function(i,e) {
                		inner +='<tr class="text-center"><td>'+e.name+'</td><td>'+e.stuType+'</td><td>'+e.closingDate+'</td><td>'+e.remarks+'</td><td><a class="btn btn-default " href="/app/company/updateTemplate.html?id='+e.id+'" >修改</a><a class="btn btn-default dels" data-src="/app/company/deleteTemplate?id='+e.id+'">删除</a></td></tr>';
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

    $("tbody").on("click",".dels",function(){
    	$(".queding").attr({"href":$(this).attr("data-src")});
		$('#dels').modal('show');
    })
})
