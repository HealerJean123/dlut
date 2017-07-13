 $(document).ready(function(){
 	$(".iconfont").html('&nbsp;&nbsp;&nbsp;&nbsp;')
 	$(".logout").on("click",function(){
 		var inner=`<div class="modal fade " id="out" tabindex="-1">
			<div class="modal-dialog"> 
				<div class="modal-content"> 
					<div class="modal-header">
						<button class="close" data-dismiss="modal"><span>&times;</span></button>
					</div>
					<div class="modal-body clearfix">
							<div class="col-lg-12" style="margin-bottom: 20px;">
								<label id="label" class="col-lg-12">确认要退出吗？</label>
							</div>
							<a  data-dismiss="modal" class="btn btn-default pull-right" >取消</a>
							<a  class="btn btn-primary pull-right outOk" >确认</a>
					</div>
					<div class="modal-footer">
					</div>
				</div>
			</div>
		</div>`;
		$(".main").append(inner);
		$("#out").modal("show");
		$(".outOk").on("click",function(){
			window.location.href="/app/logout.html";
		})
 	})
   	
 })