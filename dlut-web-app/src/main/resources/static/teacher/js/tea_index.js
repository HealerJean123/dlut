$(function(){
	fn();
	$("#year").on("change",fn);
	function fn(){
		$.ajax({
			type:"get",
			url:"/app/stat/schTeacher",
			data:{
				graduateDate:$("#year").val()
			},
			success:function(data){
				var array=[], array2=[], inner="";
				//各个院系就业百分比
				for(var acade  in data.academyEmplo){
					data.academyEmplo[acade]/data.academyTotal[acade]
					array.push(acade);
					array2.push(Math.round(data.academyEmplo[acade]/data.academyTotal[acade]*100)+"%");
				}
				for(var i=0;i<array.length;i++){
					inner+=`<span>${array[i]}</span>
						<div class="progress">
						  <div class="progress-bar progress-bar-info progress-bar-striped" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: ${array2[i]}">
						    <span>${array2[i]}</span>
						  </div>
						</div>`;
				}
				$('.percentage').html(inner);
				//地图
				map("emploArea","就业地区分布",data.emploArea)
				//饼图
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