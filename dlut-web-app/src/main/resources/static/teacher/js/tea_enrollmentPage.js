$(document).ready(function(){
	var onOff=true;
	getContent(1);
	function getContent(now){
		$("#page").attr({"pagelistcount":$("#number").val()||10});
			$.ajax({//请求数据
			type:"get",
			url:"/app/teacher/listDepartStuPage?sort=stuNo,asc",
			data:{
              stuNo: $('#stuNo').val(),
              name: $('#name').val(),
              gender: $('#gender').val(),
              ethnic: $('#ethnic').val(),
              departmentId: $('#departmentId').val(),
              majorCode: $('#majorCode').val(),
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
					inner +='<tr class="text-center"><td>'+haveReport+'</td><td>'+ $(e)[0].stuNo+'</td><td>'  + $(e)[0].name+'</td><td>'+ $(e)[0].genderName+'</td><td >'+ $(e)[0].eduYearName+' </td><td >'+ $(e)[0].ethnicName+'</td><td>'+ $(e)[0].endDate+'</td><td>'+ $(e)[0].majorName+'</td><td>'+ $(e)[0].tutorName +'</td><td>'+ $(e)[0].eduModeName  + '</td><td><a href="/app/teacher/stuInformation?id='+$(e)[0].id+'" class="btn btn-default">详情</a></td></tr>';
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
