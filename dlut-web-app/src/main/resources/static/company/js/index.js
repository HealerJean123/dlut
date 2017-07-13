$(document).ready(function(){
	//点击单位信息按钮的时候
	$("#info").on("click",function(){
		window.on("load",function(){
			var input=$("[name]");//获取所有的有name的元素
			$.ajax({//请求数据
				type:"get",
				url:"/app/company/companyInfo",
				data:{
					id:$("#identity").attr("data-id")
				},
				success:function(data){
					for(var attr in data){
						$.each(input, function(i,e) {
							if($(e).attr("name")==attr){
								$(e).val(data[attr]);
							}
						})
					}
				}
			});
		})

	})
})
