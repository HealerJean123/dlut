 $(document).ready(function(){
 	$(function(){
		$("#myCarousel").carousel({
			interval:2000,
		})
	})
	/*----毕业生----------------------------------------------------------------------------*/
	$(".student").hover(function(){
		$("#myNav").css({"background":"#fff"});
		$(this).find(".bys").css({"color":"#a51c30","border-color":"#a51c30"})
		$(".tip").find("a").css({"color":"#000"})
	},function(){
		$("#myNav").css("background","#8C1829");
		$(this).find(".bys").css({"color":"#dd2727","border-color":"rgba(255,255,255,0)"});
		$(".tip").find("a").css({"color":"#fff"})
	})
	/*-搜索新闻内容---------------------*/
		$("#search").on("click",function(){
			window.location.href="/app/portals/newsSearchList?param="+$("#searchContent").val();
		})
	/*-招聘日历----------------------------*/	
			$("#my-calendar").zabuto_calendar({
				language: "en",//语言
				today: true,//显示今天
				cell_border: true, //单元格
				ajax: {//请求数据，对日期进行标注
		            url:"/app/portals/recruiterDate",
		            modal: true,
		       },
				action: function() {//事件
					return myDateFunction(this.id, false);
				},
				action_nav: function() {//变换月份事件
					return myNavFunction(this.id);
				},
				legend: [{//解释含义
					type: "text",
					label: "今天",
					badge: "00"
				}, {
					type: "block",
					label: "有招聘信息"
				}]
			});
		function myDateFunction(id, fromModal) {
			if(fromModal) {
				$("#" + id + "_modal").modal("hide");
			}
			var date = $("#" + id).data("date");
			
			var arr=["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"];
			var month= arr[Number(date.split("-")[1])-1];
			var day=date.split("-")[2];
			$("#day").html(day);
			$("#month").html(month)
			$.ajax({//请求某一日的数据进行页面渲染
				type:"get",
				url:"/app/portals/recruiterNews",
				data:{
					date:date
				},
				success:function(array){
					var inner="";
					$.each(array,function(i,e){
						console.log($(e)[0])
						inner+=`<li><a href="/app/portals/newspage.html?id=${$(e)[0].id}">${$(e)[0].title}</a></li>`
					})
					$(".conList").html(inner)
				},
				error:function(err){
					console.log("失败")
				}
			})
			var hasEvent = $("#" + id).data("hasEvent");
			if (hasEvent && !fromModal) {
	            return false;
	        }
				return true;
			}

		function myNavFunction(id) {}
	/*-请求新闻列----------------------------------*/
	$("a[data-toggle=tab]").each(function(i,e){
		$(e).click(function(){
			var j=-1;
			j=(i+1)<=9?"0"+(i+1):(i+1)
			console.log(j);
			$.ajax({
				type:"get",
				url:"/app/portals/columnList",
				data:{
					newsColumn:j
				},
				success:function(data){
					console.log(data)
					var inner="";
					for(var i=0;i<data.length;i++){
						inner+='<li class="spill"><a href="/app/portals/newspage.html?id='+data[i].id+'">'+data[i].title+'</a><span class="pull-right">'+data[i].publishDate+'</span></li>';
					}
					inner+='<li class="more"><a href="/app/portals/newslist.html?newsColumn='+j+'" class="pull-right">更多<span class="glyphicon glyphicon-circle-arrow-right"></span></a></li>'
					$(".uls").eq(j-1).html("")
					$(".uls").eq(j-1).append(inner)
					
				},
				error:function(err){
					console.log("请求失败！")
				}
			});
		})
		
	})
	
	
 })