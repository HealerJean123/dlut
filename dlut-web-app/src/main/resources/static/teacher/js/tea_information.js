$(document).ready(function(){
    var principal = $("#principal").val();
    var schStatus = $("#schStatus").val();
    if(schStatus=="01"){
        $("#auditPass").css({"display":"none"});
        $("#auditNoPass").css({"display":"none"});
    }
    if(principal=="ACADEMY"|| principal=="ACADMIN"){
      if(schStatus!="00"&&schStatus!="02") {
          $("#roleName").css({"display":"none"});
          $("#rolegender").css({"display":"none"});
          $("#roleeduDegree").css({"display":"none"});
          $("#roleidCard").css({"display":"none"});
          $("#rolebirthdate").css({"display":"none"});
          $("#rolepolitical").css({"display":"none"});
          $("#roleethnic").css({"display":"none"});
          $("#rolehomeland").css({"display":"none"});
          $("#roledepartmentId").css({"display":"none"});
          $("#rolemajorCode").css({"display":"none"});
          $("#roletutorName").css({"display":"none"});
          $("#rolestartDate").css({"display":"none"});
          $("#roleeduYear").css({"display":"none"});
          $("#roleclassName").css({"display":"none"});
          $("#roleflangType").css({"display":"none"});
          $("#roleflangType2").css({"display":"none"});
          $("#rolecounselor").css({"display":"none"});
          $("#roleeduMode").css({"display":"none"});
          $("#roletrustee").css({"display":"none"});
          $("#rolehaveEduHukou").css({"display":"none"});
          $("#rolecounselor").css({"display":"none"});
          $("#rolecounselor").css({"display":"none"});
      }
    }


    var lastValue="";
    $(".val").each(function(i,e){
        $(".val").eq(i).html($(e).parent().parent().parent().find("input").val());
        $(".vals").eq(i).html($(".zhi").eq(i).text());
    })

    //编辑按钮
    $(".edit").on("click",function(){
        $(this).parent().css({"display":"none"})
        $(this).parent().parent().find(".show2").css({"display":"block"});
        lastValue=$(this).parent().next().find(".form-control").val();
    });

    //取消按钮
    $(".cancel").on("click",function(){
        $(this).parent().parent().css({"display":"none"})
        $(this).parent().parent().parent().find(".show1").css({"display":"block"});
        $(this).parent().prev().find(".form-control").val(lastValue);
        return false;
    })
    $("#auditPass").on("click",function(){
    	var haveReportStatus = $("#haveReportStatus").val();
        if (haveReportStatus=="1") {
      	    $("#myModal").modal("show");
        }else{
        	$("#tc").find("#label").html("学生上报才能审核");
     	    $("#tc").modal("show")
        }
    })
    $(".ok").on("click",function(){
        $.ajax({//请求数据
            type: "post",
            url: "/app/teacher/teaUpdateStuInfo",
            data: {
                id: $('#id').val(),
                allupdateStatus: "1",
                status: "01"
            },
            success: function (data) {
                window.location.href = "/app/teacher/editStuInformation?id=" + $('#id').val();
            },
            error: function (err) {
                console.log(err)
            }
        });
    })
    $(".false").on("click",function(){
        var haveReportStatus = $("#haveReportStatus").val();
        if (haveReportStatus=="1"){
            $.ajax({//请求数据
                type:"post",
                url:"/app/teacher/teaUpdateStuInfo",
                data:{
                    id: $('#id').val(),
                    allupdateStatus:"1",
                    status:"02"
                },
                success:function(data){
                    window.location.href="/app/teacher/editStuInformation?id="+$('#id').val();
                },
                error:function(err){
                    console.log(err)
                }
            });
        }else {
        $("#tc").find("#label").html("学生上报才能审核");
        $("#tc").modal("show")

        }

    })
    
    //模糊查询
	$("#homelandName").select2({
		placeholder:"请输入省市",
		ajax: {
	    url: "/app/common/dispatches",
	    dataType: 'json',
	    type:"get",
	    delay: 250,
	    data: function (params,size) {
	    	var str=params.term;
	    	var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");
	    	if(reg.test(str)){
	    		  return {
			        areaName:params.term,
					size:30
			      };
	    	}
	    },
	    processResults: function (data) {
	      return {
	        results:data
	      };
	    },
	    cache: true
	},
	minimumInputLength: 1,
//	language: "zh-CN",  
	})
	
	$("#homelandName").select2("val","1")
	
})
