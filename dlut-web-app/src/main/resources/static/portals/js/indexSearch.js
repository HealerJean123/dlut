$(document).ready(function(){
	//分页
	var onOff=true;
	getContent(1);
	function getContent(now){
			$.ajax({//请求数据
			type:"get",
			url:"/app/portals/searchBox",
			data:{
				param:$("[type=hidden]").val(),
            	page:now-1,
            	size:$(".page").attr("pagelistcount")
            },
			success:function(obj){
				console.log(obj)
				if(obj.totalPages<=1){
					$(".page").css("display","none")
				}else{
					$(".page").css("display","block");
				}
				var inner="";
				$.each(obj.content, function(i,e) {
					inner +='<li><a href="newspage.html?id='+$(e)[0].id+'">'+$(e)[0].title+'<i>'+$(e)[0].publishDate+'</i></a></li>';
				});
				$(".cons").html(inner)
				if(onOff){
					$("#page").initPage(obj.totalElements,1,getContent);
				}
				onOff=false;
			},
			error:function(err) {
			    console.log("请求失败！")
			}
		});
	}
})
