$(document).ready(function(){
	 $('#form').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
        },
        fields: {
            newPwd: {
                validators: {
                    notEmpty: {
                        message: '新密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 18,
                        message: '密码长度为6-18'
                    }
                }
            },
            newPwd2: {
                validators: {
                    notEmpty: {
                        message: '确认密码不能为空'
                    },
                    identical: {
                        field: 'newPwd',
                        message: '新密码要和确认登录密码要一致'
                    }
                }
            }
        }
    });
    $("input[name=pwd]").on("input",function(){
    	var pass=$(this).val()
		$.ajax({
			type:"get",
			url:"/app/student/findPwd",
			async:true,
			data:{
				"pwd":pass
			},
			success:function(data){
				if(data.status==403){
					$(".small").css("display","block")
					$("input[type='pwd']").parent().parent().removeClass("has-feedback")
					$("input[type='pwd']").parent().parent().removeClass("has-success")
					$("input[type='pwd']").parent().parent().addClass("has-error")
					$(".amend").attr({"disabled":true})//设置密码不正确，不能点击确认信息按钮
				}else{
					$(".small").css("display","none")
					$(".amend").attr({"disabled":false})
				}
			}
		});
    })
    
    $("input[name=newPwd]").on("blur",function(){
    	var pass2=$(this).val()
		$.ajax({
			type:"get",
			url:"/app/student/findPwd",
			async:true,
			data:{
				"pwd":pass2
			},
			success:function(data){
				if(data.status==200){
					$(".small2").css("display","block")
					$("input[type='newPwd']").parent().parent().removeClass("has-feedback")
					$("input[type='newPwd']").parent().parent().removeClass("has-success")
					$("input[type='newPwd']").parent().parent().addClass("has-error")
				}else{
					$(".small2").css("display","none")
				}
			}
		});
    })
    
    $(".change").on("click",function(){
    	$("#myModal").find($("input")).val("");
    	$("small").css("display","none")
    })
})