$(document).ready(function(){
    var onOff=true;
    getContent(1);
    function getContent(now){
        $("#page").attr({"pagelistcount":$("#number").val()||10});
        $.ajax({//请求数据
            type:"get",
            url:"/app/teacher/logo/list",
            data:{
                page:now-1,//当前页
                size:$("#number").val()||10//一页显示几个数据
            },
            success:function(obj){
                console.log(obj,"--------")
                var inner="";
                $.each(obj, function(i,e) {
                    inner +='<tr class="text-center">' +
                        '<td>'+e.sortNo+'</td>' +
                        '<td>'+e.name+'</td>' +
                        '<td><img src="'+e.logo+'"></td>' +
                        '<td>'+e.url+'</td>' +
                      '<td><a href="/app/teacher/logo/'+e.id+'" class="btn btn-default">修改</a>' +
                     '<a class="btn btn-default del" href="/app/teacher/delete/logo/'+e.id+'">删除</a></td></tr>';

                });
                $("tbody").html(inner);
                if(onOff){
                    $("#page").initPage(obj.totalElements,1,getContent);
                }
                onOff=false;
            },
            error:function(err) {
                $("#tc").find("#label").html("请求不成功！");
                $("#tc").modal("show")
            }
        });
    }
//每页显示多少页
    $("#number").on("change",function(){
        onOff=true;
        getContent(1);
    })

})


