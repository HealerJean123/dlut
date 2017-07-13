$(document).ready(function(){
	var n=$("[type=hidden]").val(),arr=[];
	console.log(n)
	$(".lis").each(function(i,e){//列出来所有的内容
		arr.push($(".lis").eq(i)[0].innerText);
	})
	var news=arr.join("").split(">");
	//分页
	var onOff=true;
	getContent(1,n);
	$(".titles").html(news[n-1]);
	$(".lis").eq(n-1).addClass("active").parent().addClass("in");
	$(".lis").eq(n-1).find("span").css("display","block");
	console.log($(".page").attr("pagelistcount"))
	function getContent(now,num){
			$.ajax({//请求数据
			type:"get",
			url:"/app/portals/moreNews",
			data:{
				newsColumn:num,
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
					if($(e)[0].top){
						inner +='<li><a href="newspage.html?id='+$(e)[0].id+'"><span>[置顶] </span>'+$(e)[0].title+'<i>'+$(e)[0].publishDate+'</i></a></li>';
					}else{
						inner +='<li><a href="newspage.html?id='+$(e)[0].id+'">'+$(e)[0].title+'<i>'+$(e)[0].publishDate+'</i></a></li>';
					}

				});
				$(".cons").html(inner)
				if(onOff){
					$("#page").initPage(obj.totalElements,1,tt);
				}
				onOff=false;
				function tt(dd){
					getContent(dd,num)
				}
			},
			error:function(err) {
			    console.log("请求失败！")
			}
		});
	}

	$(".lis").each(function(i,e){
		$(e).on("click",function(){
			$(".lis").removeClass("active");
			$(".lis").find("span").css("display","none");
			$(".lis").eq(i).addClass("active").find("span").css("display","block");
			if(i<=10){
				$(".detailsR0").css("display","none")
				$(".detailsR").css("display","block")
				var j=-1;
				j=(i+1)>9?(i+1):"0"+(i+1);
				$(".titles").html(news[i])
				onOff=true;
				getContent(1,j);
			}else{
				$(".detailsR").css("display","none")
				$(".detailsR0").css("display","block")
			}

		})
	})
	
	/*-搜索新闻内容---------------------*/
		$("#search").on("click",function(){
			window.location.href="/app/portals/newsSearchList?param="+$("#searchContent").val();
		})

})
