<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改黑名单用户</title>
    <link href="/css/bootstrap.min14ed.css" rel="stylesheet">
    <link href="/css/animate.min.css" rel="stylesheet">
    <link href="/css/style.min862f.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/filter-base.css"/>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox">
                    <div class="ibox-title">
                        <h5><a href="/blackuser/index.do">黑名单</a> >> <a href="javascript:;">用户修改</a></h5>
                    </div>
                    <div class="ibox-content">
                        <form id="form" action="" class="wizard-big">
                        <input id="id" type="hidden" value="${blackUser?if_exists.id?if_exists}">
                            <fieldset>
                                <div class="row">
                                    <div class="col-sm-8 source-newCon">
                                        <div class="form-group">
                                            <label style="text-indent: 1em;">用户ID：</label>
                                            <input name="userName" type="text" id="userId" class="form-control required" value="${blackUser?if_exists.userId?if_exists}" disabled="true">
                                        </div>
                                        <div class="form-group">
                                            <label>用户昵称：</label>
                                            <input id="userName" name="userName" type="text" class="form-control required" value="${blackUser?if_exists.userName?if_exists}" disabled="true">
                                        </div>
                                        <div class="form-group">
                                            <label style="text-indent: 2em;">时效：</label>
                                             <select id="blackEndTime">
											  <option value="1">一天</option>
											  <option value="3">三天</option>
											  <option value="7">一周</option>
											  <option value="30">一个月</option>
											  <option value="90">三个月</option>
											  <option value="180">六个月</option>
											  <option value="365">一年</option>
											  <option value="-1">永久</option>
											</select>
                                        </div>
                                    </div>
                                    </div>

                            </fieldset>
      			          </form>
                    <div class="source-sure" style="transform: translateX(69px);">
                    	<button class="btn btn-info btn-sm" id="add-sure">确定</button>
		              	<button class="btn btn-info btn-sm" id="return">返回</button>
                    </div>
                    </div>
                    
                </div>
            </div>

        </div>
    </div>
    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</body>
</html>
<script type="text/javascript">
$(document).ready(function () {
    $('#add-sure').click(function () {
    	var id = $('#id').val();
		var userId = $('#userId').val();
		var userName = $('#userName').val();
		var blackEndTime = $('#blackEndTime').val();
		$.ajax({
            type:"post",
            url:"/blackuser/update.do",
            data:"id=" + id + "&userId=" + userId + "&userName=" + userName + '&timeLimit=' + blackEndTime,
            dataType: "json",
            success:function(data){
                window.location.href = "/blackuser/index.do";
            }
        });

    })
    
    $('#return').click(function () {
        history.go(-1);
    })
})
</script>

