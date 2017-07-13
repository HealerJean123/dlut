$(function(){
	$('form').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
          /*  valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'*/
        },
        fields: {
//      	username: {
//              validators: {
//                  notEmpty: {
//                      message: '电子邮箱不能为空'
//                  },
//                  emailAddress: {
//                      message: '请输入正确的邮箱格式'
//                  }
//              }
//          },
            password: {
                validators: {
                    notEmpty: {
                        message: '登录密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 18,
                        message: '密码长度为6-18'
                    }
                }
            },
            type: {
                 validators: {
                    notEmpty: {
                        message: '请选择您的身份'
                    }
                }
            }
        }
    });
     //登录框中的位置
    	$(".login").css({
			"margin-top":-($(".login").height())/2+"px",
			"margin-left":-($(".login").width())/2+"px"
		})
		$(".bg").height($(window).height()*(2/5))
		$(".img").css({"margin-left":($(".login").width()/2-$(".img").width()/2)+"px"})
		$(window).resize(function(){
			$(".login").css({
				"margin-top":-($(".login").height())/2+"px",
				"margin-left":-($(".login").width())/2+"px"
			})
			$(".bg").height($(window).height()*(2/5))
			$(".img").css({"margin-left":($(".login").width()/2-$(".img").width()/2)+"px"})
		})
})
