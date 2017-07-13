$(document).ready(function(){
	var onOff=true;
	getContent(1);
	function getContent(now){//分页功能
		$("#page").attr({"pagelistcount":$("#number").val()||10});
			$.ajax({//请求数据
			type:"get",
			url:"/app/teacher/teaQueryByCondition",
			data:{
				name:$("[name=name]").val(),
				recName:$("[name=recName]").val(),
				auditStatus:$("[name=auditStatus]").val(),
				positionType:$("[name=positionType]").val(),
				eduDegree:$("[name=eduDegree]").val(),
				major:$("[name=major]").val(),
                publishTime:$("[name=publishTime]").val(),
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
				        var publishTime = "";
                        if($(e)[0].publishTime!=null && $(e)[0].publishTime !=""){
                            var time = $(e)[0].publishTime;
                            var day = time.dayOfMonth;
                            var month = time.monthValue;
                            if(day<10){day="0"+day;}
                            if(month<10){month="0"+month;}
                            publishTime = time.year+"-"+month+"-"+day;
                        }
                        var startTime = "";
                        if($(e)[0].startTime!=null && $(e)[0].startTime !=""){
                            var time = $(e)[0].startTime;
                            var day = time.dayOfMonth;
                            var month = time.monthValue;
                            if(day<10){day="0"+day;}
                            if(month<10){month="0"+month;}
                            startTime = time.year+"-"+month+"-"+day;
                        }
                        var endTime = "";
                        if($(e)[0].endTime!=null && $(e)[0].endTime !=""){
                            var time = $(e)[0].endTime;
                            var day = time.dayOfMonth;
                            var month = time.monthValue;
                            if(day<10){day="0"+day;}
                            if(month<10){month="0"+month;}
                            endTime = time.year+"-"+month+"-"+day;
                        }
                        inner +='<tr class="text-center"><td><input type="checkbox" value="'+$(e)[0].id+'" class="checkbox" /></td><td>'+$(e)[0].recName+'</td><td>'+$(e)[0].name+'</td><td>'+$(e)[0].typeName+'</td><td>'+$(e)[0].city+'</td><td>'+startTime+'</td><td>'+endTime+'</td><td>'+$(e)[0].auditStatusName+'</td><td><a class="btn btn-default" href="/app/teacher/teaJobDetail?id='+$(e)[0].id+'">详情</a></td><td><a class="a btn btn-default" data-val="'+$(e)[0].id+'">审核通过</a></td></tr>';
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


    $(".list1").on("click",function(){
    	window.location.href="/app/teacher/teaRecruitment";
    })
    $(".list2").on("click",function(){
    	window.location.href="/app/teacher/teaRecruitment2";
    })

    /*--全选-----------------*/
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
        	console.log(box)
        	$.ajax({
        		type:"post",
        		url:"/app/teacher/teaMoreJobAudit",
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
                url:"/app/teacher/teaJobAudit2",
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
