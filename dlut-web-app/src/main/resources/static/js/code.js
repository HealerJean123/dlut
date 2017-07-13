$(document).ready(function(){
	/*var lx=document.getElementById("codeType");
	for(var i=0;i<codeData.length;i++){
		var option=document.createElement("option");
		option.value=codeData[i].lx;
		option.innerHTML=codeData[i].lx;
		lx.appendChild(option)
		console.log(option)
	}
	$("#btn").on("click",function(){
		var index=lx.options.selectedIndex-1;//得到索引
		var con=codeData[index].details;
		var leixing=$("#codeType").val();//得到内容
		var tr="";
		for(var i=0;i<con.length;i++){//循环里面的内容
		 tr +="<tr class='text-center'><td>"+leixing+"</td><td>"+con[i].id+"</td><td>"+con[i].name+"</td><td><a>删除</a></td></tr>";
		};
		$("#tbody").html(tr);
	})*/
	var lx=document.getElementById("codeType");
	sele();//填充选项
	getList();//获取内容
	function sele(){
	$("#codeType").html("")
	$.get(
    "/app/teacher/listTypeArray",
    {},
    function (data) {
    	lx.innerHTML="<option>类型</option>";
	       for(var i=0;i<data.length;i++){
			var option=document.createElement("option");
			option.value=data[i];
			option.innerHTML=data[i];
			lx.appendChild(option)
		}
    });
	}
     $("#btn").on("click",function(){
        getList();
    });
    function getList(listType) {
        $.get(
            "/app/teacher/listCodesByType?sort=code,asc",
            {
                codeType: listType!=null? listType:$('#codeType').val(),
                page:0,
            },
            function (data) {
                if(data.content.length!=0){
                    var totalElements = data.totalElements; //总数
                    var totalPages = data.totalPages;       //总页数
                    var number = data.number + 1;             //当前页
                    var content = data.content;             //数据
                    var contnetHtml ='';
                    for(var i=0; i< content.length; i++){
                        contnetHtml+=
                            '<tr  class="text-center">' +
                            '<td>'+ content[i].type+'</td>' +
                            '<td>'+ content[i].code+'</td>'+
                            '<td>'+ content[i].name+'</td>'+
                            '<td><a class="btn btn-default a" data-val="'+content[i].id+'">删除</a></td>' +
                            '</tr>';
                        $('tbody').html(contnetHtml);

                    }
                }else{
                    $('tbody').html("");
                }
            }
        );
    };
    /*把选中的数据放到表单中*/
    $("tbody").on("click",".a",function(){
    	$("#delete").modal("show");
    	$("#del").attr({"data-id":$(this).attr("data-val")})
    	var array=[];
    	$(this).parent().parent().children().each(function(i,e){
    		array.push($(e).html())
    	})
    	var input=$("#delete").find("input");
    	input.each(function(i,e){
    		input.eq(i)[0].value=array[i]
    	})
		
    })
  /*点击确认删除*/
   $("#del").on("click",function(){
	del($(this).attr("data-id"));
   })
    function del(id){
    	$.ajax({
    		type:"get",
    		url:"/app/teacher/deleteCodesById",
    		data:{
    			id:id
    		},
    		success:function(data){
    			if(data=="0"){
    				sele();
    			}
    			getList();
    			$("#codeDel").modal("show");
    		},
    		error:function(err){
    			alert(err)
    		}
    	});
    }
})
