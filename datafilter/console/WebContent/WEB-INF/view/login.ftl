<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>登录</title>
		<link href="/css/bootstrap.min14ed.css" rel="stylesheet">
		<link href="/css/font-awesome.min93e3.css" rel="stylesheet">
		<link href="/css/animate.min.css" rel="stylesheet">
		<link href="/css/style.min862f.css" rel="stylesheet">
		<style type="text/css">
			.middle-box {
				position: fixed;
				top: 0;
				right: 0;
				bottom: 0;
				left: 0;
				margin: auto;
			}
		</style>
	</head>

	<body class="gray-bg">

		<div class="middle-box text-center loginscreen  animated fadeInDown" style="height: 400px;">
			<div>
				<form class="m-t">
					<div class="form-group">
						<input type="text" class="form-control" name="adminName" placeholder="用户名" required="">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" name="password" placeholder="密码" required="">
					</div>
					<div id='snPwd' class="form-group" style='display:none'>
						<input type="text" id="snPassword" class="form-control" name="snPassword"    placeholder="动态密码"/>
        			</div>
        			<div class="form-group">
						<span style="color:red;" id="msg"></span>
					</div>
					<input id="ok" type="button" class="btn btn-primary block full-width m-b" value="登 录" />

				</form>
			</div>
		</div>
		<script src="/js/jquery.min.js"></script>
		<script src="/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="/js/jquery.form.js"></script>
	</body>

</html>
<script type="text/javascript">
	function formSubmit() {
		if($('#adminName').val() == '') {
			$('#adminName').focus();
			$('#msg').text('用户名不能为空');
			return false;
		}
		if($('#password').val() == '') {
			$('#password').focus();
			$('#msg').text('密码不能为空');
			return false;
		}
		var tempSnPwd = $("#snPwd").is(":hidden");//是否隐藏
		if(tempSnPwd == false) {
			if($('#snPassword').val() == '') {
				$('#snPassword').focus();
				$('#msg').text('动态密码不能为空');
				return false;
			}
		}
		
		$('#msg').text('正在登录...');
		
		$('form').ajaxSubmit({
			url: '/admin/doLogin.do',
			type: 'post',
			dataType: 'json',
			success: function(data) {
			    $('#msg').text(data.msg);
				if(data.flag) {
					document.location.href = '/index.do';
				} else {
					return false;
				}
			}
		});
	}
	
	$(document).ready(function() {

		$.ajax({
            type:"GET",
            url:"/admin/openSnCheck.do",
            dataType: "json",
            success:function(data){
                if (data.flag != null && data.flag == true
                	&& data.data != null && data.data == 1) {
                	$("#snPwd").show();
                }
            }
        });

		if(top.location != self.location) {
			top.location = self.location;
		}
		$('#adminName').focus();
		$('#ok').click(formSubmit);
		
		$('#adminName').keydown(function (evt){
			if(evt.keyCode == 13) {
				$('#password').focus();
			}
		});
		
		$('#password').keydown(function (evt){
			var temp= $("#authcd").is(":hidden");
			if(evt.keyCode == 13) {
				if(temp == false){
					$('#authCode').focus();
				}
				if(temp == true){
					formSubmit();
				}
			}
		});
		
		$('#snPassword').keydown(function (evt){
			if(evt.keyCode == 13) {
				formSubmit();
			}
		});
		
	});
	
</script>