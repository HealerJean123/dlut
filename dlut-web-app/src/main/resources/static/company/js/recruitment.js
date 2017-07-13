$(document).ready(function() {
	$('form.wrap1').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
        },
        fields: {
        	title: {
                validators: {
                    notEmpty: {
                        message: '招聘简章的标题不能为空'
                    }
                }
            },
            content:{
                validators: {
                    notEmpty: {
                        message: '招聘简章的内容不能为空'
                    }
                }
            },
            startTime:{
                validators: {
                    notEmpty: {
                        message: '开始时间不能为空'
                    }
                }
            },
            endTime:{
                validators: {
                    notEmpty: {
                        message: '结束时间不能为空'
                    }
                }
            }
        }
    });

	var li=$(".publishNav").find("li");
	//发布简章
    $("#release").on("click",function(){
		window.location.href="/app/company/recruitment-step1.html";
    })
    $("#addJob").on("click",function(){
        window.location.href="/app/company/recruitmentJob"
    })
     $(".jump").on("click",function(){
   		window.location.href="/app/company/recruitment-step3.html";
    })
     $(".look").on("click",function(){
       	window.location.href="/app/company/recruitment.html";
     })
var div =$(".plus").parents('.form1').clone(true);

	 $(".plus").click(function(){
		 var html=$(this).parents(".form-content").clone(true);
		 html.find('input').val('');
		 html.find('select').val('');
		 html.find('checkbox').prop('checked',false);
		 html.insertAfter($(this).parents(".form-content"));
		 //$(this).parents(".form1").append();
	 })
	 $(".minus").click(function(){
		 if($(this).parents(".form1").find(".form-content").length==1)return;
		 $(this).parents(".form-content").remove();
	 })
     //收缩按钮
     $(".upDown").click(function(){


		 $(this).parents(".form-content").find(".div").toggle("showOrHide")
	 })
     //删除
     $(".list1").on("click",function(){
	window.location.href="./recruitment.html";
    })
$(".list2").on("click",function(){
	window.location.href="./recruitment2.html";
})
$(".close").on("click",function(){
//	$("#pane").css("display","none");
//	$("#pane").find("[name]").val("");
})
/*-发布与招聘简章相关的招聘职位-------------------------------------------------------------------------------*/
$("#next").on("click",function(){
	var recJobPositionDTOS=[];
	$(".form-content").each(function(i,e){
		var obj={};
		obj["bulletinId"]=$("[name=bulletinId]").val();
		$(e).find(".form-control").each(function(i,e){
			obj[e.name]=e.value
		});
		recJobPositionDTOS.push(obj)
	})
	var data = JSON.stringify(recJobPositionDTOS);
	console.log(data)
	$.ajax({
		type:"post",
		url:"/app/company/saveJob",
		data: data,
		contentType: "application/json; charset=utf-8",
		success:function(data){
			window.location.href="/app/company/recruitment-step3.html";
		},error:function(err){
			console.log(err)
		}
	})
})


/*-发布招聘职位-------------------------------------------------------------------------------*/
$("#btn").on("click",function(){
	var recJobPositionDTOS=[];
	$(".form-content").each(function(i,e){
		var obj={};
		$(e).find(".form-control").each(function(i,e){
			obj[e.name]=e.value
		});
		recJobPositionDTOS.push(obj)
	})
	console.log(recJobPositionDTOS)
	$.ajax({
		type:"post",
		url:"/app/company/RecJob",
		data: JSON.stringify(recJobPositionDTOS),
		contentType: "application/json; charset=utf-8",
		success:function(data){
		    alert(data)
			window.location.href="/app/company/recruitment2.html";
		},
		error:function(err){
			console.log(err)
		}
	})
})
})
