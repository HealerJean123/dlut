$(document).ready(function(){
	var onOff=true;
	getContent(1);
	function getContent(now){
		$("#page").attr({"pagelistcount":$("#number").val()||10});
			$.ajax({//请求数据
			type:"get",
			url:"/app/teacher/listMyDepartStuPage?sort=stuNo,asc",
			data:{
              stuNo: $('#stuNo').val(),
              name: $('#name').val(),
              gender: $('#gender').val(),
              ethnic: $('#ethnic').val(),
              haveReport: $('#haveReport').val(),
              majorCode: $('#majorCode').val(),
              departmentId: $('#departmentId').val(),
              eduYear: $('#eduYear').val(),
              eduMode: $('#eduMode').val(),
              endDate: $('#endDate').val(),
              status: $('#status').val(),
              tutorName: $('#tutorName').val(),
              eduDegree: $('#eduDegree').val(),
              page:now-1,
              size:$("#number").val()||10,
            },
			success:function(obj){
				var inner="";
				var haveReport ="";
				$.each(obj.content, function(i,e) {

				    if($(e)[0].haveReport=="0"){
                        haveReport = "否";
                    }else {
                        haveReport = "是";
                    }
					inner +='' +
                        '<tr  class="text-center">' +
                        '<td><input type="checkbox" class="checkbox" value="'+$(e)[0].id+'" name = id"/></td>' +
                        '<td>'+haveReport+'</td>' +
                        '<td>'+$(e)[0].stuNo+'</td>' +
                        '<td>'+$(e)[0].name+'</td>' +
                        '<td>'+$(e)[0].departmentIdName+'</td>' +
                        '<td>'+$(e)[0].majorCodeName+'</td>' +
                        '<td>'+$(e)[0].endDate+'</td>' +
                        '<td>'+ $(e)[0].statusName+'</td>' +
                        '<td><a href="/app/teacher/editStuInformation?id='+$(e)[0].id+'" class="btn btn-default">修改</a></td>' +
                        '<td><a class="a btn btn-default" data-val="'+$(e)[0].id+'">审核通过</a></td></tr>';
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
		if($(this).prop("checked")){
			box.push($(this).val())
		}
	})
	$.ajax({
		type:"get",
		url:"/app/teacher/stuInfoBatchAudit",
		data:{
            "id":box
		},
        traditional: true,
		success:function(data){
            getContent(1);
            $("#tc").find("#label").html(data);
            $("#tc").modal("show")
            onOff=true;
			getContent($(".pageItemActive").html());
		},
		error:function(err){
            $("#tc").find("#label").html("失败！");
            $("#tc").modal("show")
		}
	})
})
//--审核通过按钮----------------------------------------
$("tbody").on("click",".a",function(){
//	var arr=$(this).attr("data-val").split(",");
	agree($(this).attr("data-val"))

})
function agree(id){
    $.ajax({
        type:"get",
        url:"/app/teacher/stuInfoAuditPass",
        data:{id:id},
        traditional: true,
        success:function(data){
            getContent(1);
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
