$(function(){
	//校级
	$(".pass").on("click",function(){
		window.location.href="/app/teacher/agreerecOffer?id="+$("[name=id]").val()
	})
	$(".noPass").on("click",function(){
		window.location.href="/app/teacher/noPassOffer?id="+$("[name=id]").val()+"&auditStatus=02&noPassReason="+$("[name=noPassReason]").val()
	})
	//院系
	$(".passY").on("click",function(){
		window.location.href="/app/teacher/agreerecOffer?id="+$("[name=id]").val()
	})
	$(".noPassY").on("click",function(){
		window.location.href="/app/teacher/noPassOffer?id="+$("[name=id]").val()+"&auditStatus=04&noPassReason="+$("[name=noPassReason]").val()
	})
	$(".passOk").on("click",function(){
		window.location.href="/app/teacher/updateVioAudit1?id="+$("[name=id]").val()+"&auditStatus=01";
	})
	$(".noPassOk").on("click",function(){
		window.location.href="/app/teacher/updateVioAudit1?id="+$("[name=id]").val()+"&auditStatus=02&noPassReason="+$("[name=noPassReason]").val()
	})
	$(".passOkBlank").on("click",function(){
		window.location.href="/app/teacher/updateBlankAudit1?id="+$("[name=id]").val()+"&auditStatus=01";
	})
	$(".noPassOkBlank").on("click",function(){
		window.location.href="/app/teacher/updateBlankAudit1?id="+$("[name=id]").val()+"&auditStatus=02&noPassReason="+$("[name=noPassReason]").val()
	})
	$(".alertOK").on("click",function(){
		window.location.href="/app/teacher/updateReassAppAuditStatus?stuNo="+$("[name=stuNo]").val()+"&auditStatus=01";
	})
	$(".noAlertOK").on("click",function(){
		window.location.href="/app/teacher/updateReassAppAuditStatus?stuNo="+$("[name=stuNo]").val()+"&auditStatus=02&noPassReason="+$("[name=noPassReason]").val()
	})

	$(".bulletinPass").on("click",function(){
	    window.location.href="/app/teacher/teaBulletinAudit?id="+$("[name=bulletinId]").val()+"&auditStatus=01";
	})

	$(".bulletinNoPass").on("click",function(){
    	window.location.href="/app/teacher/teaBulletinAudit?id="+$("[name=bulletinId]").val()+"&auditStatus=02&noPassReason="+$("[name=noPassReason]").val();
    })

    $(".jobPass").on("click",function(){
        window.location.href="/app/teacher/teaJobAudit1?id="+$("[name=jobId]").val()+"&auditStatus=01";
    })

    $(".jobNoPass").on("click",function(){
        window.location.href="/app/teacher/teaJobAudit1?id="+$("[name=jobId]").val()+"&auditStatus=02&noPassReason="+$("[name=noPassReason]").val();
    })

})
