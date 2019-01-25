<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>白名单</title>
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
                        <h5><a href="/whiteuser/index.do">白名单</a> >> <a href="javascript:;">新增白名单</a></h5>
                    </div>
                    <div class="ibox-content">
                        <form id="form" action="" class="wizard-big">
                            <fieldset>
                                <div class="row">
                                    <div class="col-sm-8 source-newCon">
                                         <div class="form-group">
                                            <label>白名单类型：</label>
                                            <select id="type" class="source-examine">
											  <option value="1">用户</option>
											  <option value="2">IP</option>
											</select>
                                            
                                        </div>
                                        <div class="form-group special">
                                            <label style="text-indent: 1em;">添加理由：</label>
                                            <div class="add-content-word">
                                            	<input id="white-reason" type="text" class="form-control required">
                                            </div>
                                        </div>
                                        <div class="form-group user-div">
                                            <label style="text-indent: 1em;">用户信息：</label>
                                            <span>用户ID：</span><input type="text" id="white-userId"/>
                                        	<span>用户昵称：</span><input type="text" id="white-userName"/>
                                        	<span class="add-content-words">+</span>
                                        </div>
                                        <div class="form-group ip-div" style="display:none">
                                        	<label style="text-indent: 4em;">IP：</label>
                                        	<input id="white-ip" type="text"/>
                                        	<span class="add-content-words">+</span>
                                        </div>
                                    </div>
                                    </div>

                            </fieldset>
      			          </form>
                    <div class="source-sure" style="margin-left: 5%; transform: translateX(82px);">
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
$(document).ready(function(){
	// $('.source-examine').change(function(){
	// 	var value = $('.source-examine').val();
	// 	alert(value);
	// })
	
    //【确定】按钮
    $('#add-sure').click(function(){
        var type = $("#type").val();
        var whiteReason = $("#white-reason").val();
        var users = [];
        if (type == 1) {
            $(".user-div").each(function(){
                var user = {};
                user.userId = $(this).find("#white-userId").val();
                user.userName = $(this).find("#white-userName").val();
                user.whiteReason = whiteReason;
                user.whiteType = 1;
                user.deleteFlag = 0;
                users.push(user);
            })
        } else if (type == 2) {
            $(".ip-div").each(function(){
                var user = {};
                user.ip = $(this).find("#white-ip").val();
                user.whiteReason = whiteReason;
                user.whiteType = 2;
                user.deleteFlag = 0;
                users.push(user);
            })
        }
        $.ajax({
            type:"POST",
            url:"/whiteuser/insert.do",
            data:"users=" + JSON.stringify(users),
            dataType: "json",
            success:function(data){
                document.location.href = '/whiteuser/index.do';
            }
        });
    })
    //【返回】按钮
    $('#return').click(function(){
        history.go(-1);
    })
    
})

//user或IP选择的下拉框改变
$('#type').change(function(){
    var type = $(this).val();
    if (type == 1) {
        $(".user-div").show();
        $(".ip-div").hide();
    } else if (type == 2) {
        $(".user-div").hide();
        $(".ip-div").show();
    }
});

//+新增
$(document).on("click",'.add-content-words',function(){
    var node = $(this).parents(".form-group").prop("outerHTML");
    $(this).parents(".form-group").after(node);
 });
 
 
 
//X删除
 $(document).on("click",'.delete-content-words',function(){
    $(this).parents(".form-group").remove();
 })
</script>


