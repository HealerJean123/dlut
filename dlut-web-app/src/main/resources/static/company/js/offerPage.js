$(document).ready(function(){
	var onOff=true;
	getContent(1);
	function getContent(now){//分页功能
		$("#page").attr({"pagelistcount":$("#number").val()||10});
			$.ajax({//请求数据
			type:"get",
			url:"/app/company/findOffer",
			data:{
				stuNo:$("[name=stuNo]").val(),
				stuName:$("[name=stuName]").val(),
				sTime:$("[name=sTime]").val(),
				eduDegree:$("[name=eduDegree]").val(),
				academy:$("[name=academy]").val(),
				major:$("[name=major]").val(),
				graduationTime:$("[name=graduationTime]").val(),
				offerStatus:$("[name=offerStatus]").val(),
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
				        var date = "";
				        if($(e)[0].createOn!=null && $(e)[0].createOn !=""){
				            var time = $(e)[0].createOn;
                            var day = time.dayOfMonth;
                            var month = time.monthValue;
                            if(day<10){day="0"+day;}
                            if(month<10){month="0"+month;}
                            date = time.year+"-"+month+"-"+day;
				        }
				        var send1 = '';
				        var send2 = '';
				        var status = $(e)[0].stuReceivingStatus;
				        if(status=='00'){
				            send1 = '<a class="btn btn-default cancel" data-href="/app/company/deleteOffer?id='+$(e)[0].id+'">取消发送</a>';
				        }else{
				            send1 = '-';
				        }

				        if(status=='00' || status=='02'){
				            send2 = '<a class="btn btn-default fs" data-id="'+$(e)[0].stuId+'">再次发送</a>';
				        }else{
				            send2 = '-';
				        }
                		inner +='<tr class="text-center"><td>'+$(e)[0].realName+'</td><td>'+$(e)[0].department+'</td><td>'+$(e)[0].majorName +'</td><td>'+$(e)[0].stuReceivingStatusName+'</td><td>'+date+'</td><td>'+$(e)[0].eduName+'</td><td>'+send1+'</td><td>'+send2+'</td></tr>';
                	});
				}

				$("#tbody").html(inner);
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
    /*--取消发送offer----------*/
   $("#tbody").on("click",".cancel",function(){
   		$(".queding").attr({"href":$(this).attr("data-href")});
   		$("#cancel").modal("show");
   })
    /*-再次发送-------------*/
   $("#tbody").on("click",".fs",function(){//点击发送的时候
 		stu_id=$(this).attr("data-id");
   		$("#cadre").modal("show");
 		$("#cadre").modal({
   			show:false,
 			backdrop:false
 		});
 	})
   $("#btn0").on("click",function(){//查询模板类型
 		$.ajax({
	 		type:"get",
	 		url:"/app/company/showTemplate",
	 		data:{
	 			stuType:$("select[name=stuType]").val(),
	 			name:$("#template").val(),
	 		},
	 		success:function(data){
	 			var inner="";
	 			$.each(data,function(i,e){
	 				inner+='<tr><td>'+e.name+'</td><td>'+e.stuType+'</td><td>'+e.remarks+'</td><td><button class="goes btn btn-default" data-id="'+e.id+'">发送</button></td></tr>'
	 			})
	 			$("#tbody0").html(inner);
	 		},error:function(err){
	 			console.log("失败！")
	 		}
	 	})
   	})
   $("#tbody0").on("click",".goes",function(){//给学生发offer
   	$("#cadre").modal("hide");
   	template_id=$(this).attr("data-id");
   	console.log(stu_id,template_id)
   		$.ajax({
   			type:"post",
   			url:"/app/company/sendOfferAgain",
   			data:{
   				stuId:stu_id,
   				templateId:template_id
   			},
   			success:function(data){
   				console.log(data)
   				if(data=="ok"){
   				    $("#tc").find("#label").html("发送成功！");
					$("#tc").modal("show")
					$("#tc").on("hidden.bs.modal",function(){
						window.location.reload();
					})
   				}else if(data=="error"){
   				    $("#tc").find("#label").html("发送失败！");
					$("#tc").modal("show")
   				}
   			},
   			error:function(err){
   				console.log("失败！")
   			}
   		})
   })


})
