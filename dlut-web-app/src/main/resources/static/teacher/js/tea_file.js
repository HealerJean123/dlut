 $(document).ready(function(){
var myFile=document.getElementById("myFile");//选择文件的按钮

$("#myFile").on("change",function(){//选中的文件有所改变
	//判断一下上传文件的类型-----------
	var fileDir = $.trim($(this).val()),//文件
		fileName="",
		arr=[],
		that=$(this);
//	var uploadBtn = $('#fileUpload');
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
			if(add.match(/xls|xlsx/gi)){//属于表格的格式
				message.text("支持xls,xlsx格式");
				//获取信息
				var xhr=new XMLHttpRequest();
				xhr.open("POST","/app/teacher/intoStuInfoFinal");
				xhr.setRequestHeader("X-Request-With","XMLHttpRequest");
				var oFormDate=new FormData();//通过formData来获取二进制对象
				oFormDate.append("file",myFile.files[0])
				xhr.send(oFormDate);
				xhr.onload=function(){
					var array=this.responseText;
					console.log(array)
					$("[data-name]").each(function(i,e){
						if(array.indexOf($(e).attr("data-name"))!=-1){
							$(e).html($(e).attr("data-name"))
						}else{
							$(e).html("请把字段改为"+$(e).attr("data-name")).css({"color":"red"});
							$("#tj").prop({"disabled":"true"});
							message.text("请修改字段");
						}
					})
					$(".fileCon").css({"display":"block"})
				}
				$("#tj").removeClass('disabled').unbind('click').on('click',function(){//上传文件的按钮可以点击

					$.ajax({
						type:"get",
						url:"/app/teacher/insertSuccess",
						success:function(data){
							if(data=="success"){
								var setI=null;
								 setI=setInterval(function(){
								 	$.ajax({
										type:"get",
										url:"/app/teacher/insertNumStatus",
										success:function(data){
											var rate=Math.round((data.dealNum/data.totalNum)*100)+"%";
                                            $(".progress").css({"display":"block"});
                                            $(".progress").find(".progress-bar").css({"width":rate}).html(rate);
                                            $(".successNum").html("成功条数："+data.successNum).css({"display":"inline-block"});
                                            $(".totalNum").html("总条数："+data.totalNum).css({"display":"inline-block"});
											if((data.totalNum===data.dealNum)&&(data.totalNum>data.successNum)){
												clearInterval(setI);
												var errorStuInfosSize = $("#errorStuInfosSize").val();
												if(errorStuInfosSize!=0){
										       	    $("#download").show()
											    }else {
											        $("#download").hide()
											    }
											}
										},
										error:function(err){
											console.log("读取进度失败！")
										}
									})
								 },200);
							};
							/**/
//
						},
						error:function(err){
							console.log("上传失败！")
						}
					})
				})
			}else{
				message.text("文件格式不正确！只支持xls,xlsx格式");
				$("#tj").addClass("disabled")
			}


		}
	}

})

 })
