$(document).ready(function(){
    var onOff=true;
    getContent(1);
    function getContent(now){
        $("#page").attr({"pagelistcount":$("#number").val()||10});
        var size = $("#number").val()||10;
        $.ajax({//请求数据
            type:"get",
            url:"/app/teacher/pageNews?sort=sortNo,asc",
            data:{
                title:$("input[name=title]").val(),
                publishStartFrom:$("input[name=publishStartFrom]").val(),
                publishStartTo:$("input[name=publishStartTo]").val(),
                publisEndFrom:$("input[name=publisEndFrom]").val(),
                publishEndTo:$("input[name=publishEndTo]").val(),
                newsColumn:$("[name=newsColumn]").val(),
                page:now-1,//当前页
                size:$("#number").val()||10//一页显示几个数据
            },
            success:function(obj){
                console.log(obj)
                $("#checkAll").prop({"checked":false});//全选默认为否
                var inner="";
                $.each(obj.content, function(i,e) {
                    var isDisplay = "";
                    var auditor = $(e)[0].auditor;

                    if($(e)[0].isDisplay=="0"){
                        isDisplay = "否";
                    }else {
                        isDisplay = "是";
                    }

                    if(auditor==null || auditor ==""){
                        auditor = "无";
                    }
                    inner +='<tr class="text-center">' +
                        '<td><input type="checkbox" value="'+$(e)[0].id+'" class="checkbox" /></td>' +
                        '<td>'+ $(e)[0].sortNo+'</td>' +
                        '<td><a href="/app/teacher/news/'+ $(e)[0].newsNo+'">'+$(e)[0].newsNo+'</a></td>' +
                        '<td>'+$(e)[0].title+'</td>' +
                        '<td>'+isDisplay+'</td>' +
                        '<td>'+$(e)[0].status+'</td>' +
                        '<td>'+auditor+'</td>' +
                        '<td>'+$(e)[0].publishDate+'</td>' +
                        '<td>'+$(e)[0].endDate+'</td>' ;
                    /*  '<td><a href="/app/teacher/teaAbsent.html?vid='+$(e)[0].id+'" class="btn btn-default">详情</a></td>' +
                     '<td><a class="a btn btn-default" data-val="'+$(e)[0].id+'">审核通过</a></td></tr>';
                     */
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
//点击查询
    $("#btn").on("click",function(){
        onOff=true;
        getContent(1);
    })
//每页显示多少页
    $("#number").on("change",function(){
        onOff=true;
        getContent(1);
    })
//-------全选按钮-------------------
    var box=[];
    $("#checkAll").on("click",function(){//点击全选按钮
        var isTrue= $(this).prop("checked")//
        $(".checkbox").each(function(i,e){
            $(this).prop("checked",isTrue)//所有的跟全选按钮保持一致
        })
    })
    $("tbody").on("click",$(".checkbox"),function(){
        var j=0;
        $(".checkbox").each(function(i,e){
            if($(".checkbox").eq(i).prop("checked")==false){
                j++;
            }
        })
        if(j>0){
            $("#checkAll").prop({"checked":false})
        }else{
            $("#checkAll").prop({"checked":true})
        }
    })

//增加内容
    $("#add").on("click",function(){
        window.location.href="/app/teacher/tea_newsAdd.html";
    })
//删除
    $("#del").on("click",function(){
        box=[];
        $(".checkbox").each(function(i,e){
            if($(e).prop("checked")){
                box.push($(e).val())
            }
        })
        $.ajax({
            type:"get",
            url:"/app/teacher/deleteBatchNewsByIds",
            data:{
                "id":box
            },
            traditional: true,
            success:function(data){
                $("#tc").find("#label").html(data);
                $("#tc").modal("show")
                onOff=true;
                getContent($(".pageItemActive").html());
            },
            error:function(data){
                $("#tc").find("#label").html("失败！");
                $("#tc").modal("show")
            }
        })
    })
})


