$(function(){
	fn();
	$("#year").on("change",fn);
	function fn(){
		$.ajax({
			type:"get",
			url:"/app/teacher/academicIndex",
			data:{
				endDate:$("#year").val()
			},
			success:function(data){
				var array=[], array2=[], inner="";
				//地图
				map("emploArea","就业地区分布",data.emploArea)
				//饼图
				cakes("emploDestination","就业去向",data.emploDestination);
				cakes("emploNature","就业单位性质分布",data.emploNature);
				cakes("emploCategory","就业单位行业分布",data.emploCategory);
				
				//就业率
				$(".emploRate").html(Math.round(data.emploRate*100)+"%");
				//毕业人数
				$(".countAll").html(data.countAll);
				
				//待办事件
				for(var list in data.todoList){
					$("."+list).html(data.todoList[list])
				}
			},
			error:function(err){
				console.log(err)
			}
		})
	}
	
})