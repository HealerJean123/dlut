$(document).ready(function(){
	 $('form').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
        },
        fields: {
            url: {
                validators: {
                    uri: {
                        allowLocal: true,
                        message: '请输入正确的网址格式'
                    }
                }
            },
        }
    });
 	$("#changeImg").click(function(){
 		$(".changeImg").show();
 	})
	$("input[type=file]").on("change",function(){
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
					message.text("文件格式不正确！请选择图片");
					$("#publish").addClass("disabled")
				}
			}
		}
	}); 
})
