	$(document).ready(function() {
    $('form').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
           /* valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'*/
        },
        fields: {
        	name: {
                validators: {
                    notEmpty: {
                        message: '单位名称不能为空'
                    }
                }
            },
            orgCode:{
                validators: {
                    notEmpty: {
                        message: '组织机构代码不能为空'
                    }
                }
            },
            department: {
                validators: {
                    notEmpty: {
                        message: '主管部门不能为空'
                    }
                }
            },
            industry: {
                validators: {
                    notEmpty: {
                        message: '单位行业不能为空'
                    }
                }
            },
            nature: {
                validators: {
                    notEmpty: {
                        message: '单位类型不能为空'
                    }
                }
            },
            size: {
                validators: {
                    notEmpty: {
                        message: '单位规模不能为空'
                    }
                }
            },
            capital: {
                validators: {
                    notEmpty: {
                        message: '注册资金不能为空'
                    }
                }
            },
            city: {
                validators: {
                    notEmpty: {
                        message: '单位地址不能为空'
                    }
                }
            },
            address: {
                validators: {
                    notEmpty: {
                        message: '详细地址不能为空'
                    }
                }
            },
            describe: {
                validators: {
                    notEmpty: {
                        message: '单位简介不能为空'
                    }
                }
            },
            contacts: {
                validators: {
                    notEmpty: {
                        message: '联系人不能为空'
                    }
                }
            },
            zipCode: {
                validators: {
                    notEmpty: {
                        message: '单位邮编不能为空'
                    }
                }
            },
            acceptTerms: {
                validators: {
                    notEmpty: {
                        message: '请接受条款和政策'
                    }
                }
            },
			telphone: {
                validators: {
                    notEmpty: {
                        message: '手机号码不能为空'
                    },
                    regexp: {
                        regexp: /1[34578]\d{9}/,
                        message: '请输入正确的格式'
                    }
                }
            },
 			fixedTelphone: {
                validators: {
                    notEmpty: {
                        message: '固定电话不能为空'
                    },
                    regexp: {
                        regexp: /\d{3}-\d{8}|\d{4}-\d{7}/,
                        message: '请输入正确的格式'
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: '电子邮箱不能为空'
                    },
                    emailAddress: {
                        message: '请输入正确的邮箱格式'
                    }
                }
            },
            website: {
                validators: {
                    uri: {
                        allowLocal: true,
                        message: '请输入正确的网址格式'
                    }
                }
            },
            pwd: {
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
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: '确认密码不能为空'
                    },
                    identical: {
                        field: 'pwd',
                        message: '登录密码要和确认登录密码要一致'
                    }
                }
            },
            file:{
            	 validators: {
                    notEmpty: {
                        message: '请上传组织机构代码证或社会新红图片'
                    }
                }
            }
        }
    });
 
$("input[type=file]").on("change",function(){
	console.log("--")
	$(".preview img").remove();
	//判断一下上传文件的类型-----------
	var fileDir = $.trim($(this).val()),//文件
		fileName="",
		arr=[],
		that=$(this);
	var message = $(this).siblings('span');
	if(fileDir != ""){//选择文件处不为空
		if(fileDir.match(/\\/g)){//有匹配的\\
			arr = fileDir.split('\\');//\\分割
			fileName = arr[arr.length-1] || "";//名字就是截取的数组中最后面的一组数据
		}else{//没有有匹配的\\
			arr = fileDir.split('/');//用/分割
			fileName = arr[arr.length-1] || "";//名字就是截取的数组中最后面的一组数据
		}
		if(fileName != ""){//取到的名字不为空
			var arr = fileName.split('.');
			var add = arr[arr.length-1];//格式
			if(add.match(/gif|jpg|png/gi)){//属于表格的格式
				message.text("");
				$("#btn").removeClass("disabled")
				//图片选择成功，展示图片开始
				var file=this.files[0];
			    var reader=new FileReader();
			    reader.onload=function(){
			        // 通过 reader.result 来访问生成的 DataURL
			        var url=reader.result;
			        setImageURL(url);
			    };
				    reader.readAsDataURL(file);
					var image=new Image();
					function setImageURL(url){
						image.src=url;
					}
					$(".preview").append(image)
				//图片选择成功，展示图片结束
			}else{
				$("input[type=file]").val("");
				message.text("文件格式不正确！请选择图片");
				$("#btn").addClass("disabled")
			}
		}
	}
}); 

	//验证邮箱
	$("input[type='email']").eq(0).on("blur",function(){
		var emailVal=$("input[type='email']").eq(0).val();
		console.log(1);
		$.ajax({
			type:"get",
			url:"/register/company/findByEmail",
			async:true,
			dataType:"json",
			data:{
				"email":emailVal
			},
			success:function(data){
				if(data.status==403){
					$(".small").css("display","block")
					$("input[type='email']").eq(0).parent().parent().removeClass("has-feedback")
					$("input[type='email']").eq(0).parent().parent().removeClass("has-success")
					$("input[type='email']").eq(0).parent().parent().addClass("has-error")
				}else{
					$(".small").css("display","none")
				}
			}
		});
	})

});
