<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改过滤词</title>
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
                        <h5><a href="index.ftl">过滤词库</a> >> <a href="javascript:;">修改过滤词</a></h5>
                    </div>
                    <div class="ibox-content">
                        <form id="form" action="" class="wizard-big">
                            <input id="id" type="hidden" value="${vocabulary?if_exists.id?if_exists}">
                            <fieldset>
                                <div class="row">
                                    <div class="col-sm-8 source-newCon">
                                         <div class="form-group">
                                            <label>词汇类型：</label>
                                            <select id="type" name="type" class="source-examine">
                                                <option value="1" <#if vocabulary?? && vocabulary.type?? && vocabulary.type == 1>selected=selected</#if>>敏感词</option>
											    <option value="2" <#if vocabulary?? && vocabulary.type?? && vocabulary.type == 2>selected=selected</#if>>非法词</option>
											</select>
                                            
                                        </div>
                                        <div class="form-group">
                                            <label>来源名称：</label>
                                            <input id="content" name="content" type="text" class="form-control required" value="${vocabulary?if_exists.content?if_exists}">
                                        </div>
                                    </div>
                                    </div>

                            </fieldset>
      			          </form>
                    <div class="source-sure">
                    	<button id="update-sure" class="btn btn-info btn-sm">确定</button>
		              	<button id="return" class="btn btn-info btn-sm">返回</button>
                    </div>
                    </div>
                    
                </div>
            </div>

        </div>
    </div>
    <script src="/js/jquery.min.js?v=2.1.4"></script>
    <script src="/js/bootstrap.min.js?v=3.3.6"></script>
   
</body>
</html>
<script type="text/javascript">
$(document).ready(function(){
    //【确定】按钮
    $('#update-sure').click(function(){
        var id = $("#id").val();
        var type = $("#type").val();
        var content = $("#content").val();
        $.ajax({
            type: "get",
            url: "/vocabulary/update.do",
            data: "id=" + id +"&type=" + type + "&content=" + content,
            dataType: "json",
            success:function(data){
                window.location.href = "/vocabulary/index.do";
            }
        });
    })
    //【返回】按钮
    $('#return').click(function(){
        history.go(-1)
    })
    
})

</script>