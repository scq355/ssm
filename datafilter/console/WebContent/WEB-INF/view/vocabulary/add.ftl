<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新增过滤词</title>
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
                        <h5><a href="index.ftl">过滤词库</a> >> <a href="javascript:;">新增过滤词</a></h5>
                    </div>
                    <div class="ibox-content">
                        <form id="form" action="" class="wizard-big">
                            <fieldset> 
                                <div class="row">
                                    <div class="col-sm-8 source-newCon">
                                         <div class="form-group">
                                            <label>词汇类型：</label>
                                            <select id="type" class="source-examine">
                                              <option value="1">敏感词</option>
                                              <option value="2">非法词</option>
                                            </select>  
                                        </div>
                                        <div class="form-group special">
                                            <label>词汇内容：</label>
                                            <div class="add-content-word">
                                                <input type="text" class="form-control required">
                                                <span class="add-content-words">+</span>
                                            </div> 
                                        </div>
                                    </div>
                                    </div>

                            </fieldset>
                          </form>
                    <div class="source-sure">
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
    //【确定】按钮
    $('#add-sure').click(function(){
        var type = $("#type").val();
        var content = [];
        $(".add-content-word .form-control").each(function(){
            content.push($(this).val());
        })
        $.ajax({
            type:"get",
            url:"/vocabulary/create.do",
            data:"type=" + type + "&content=" + content,
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

//+新增
$(document).on("click",'.add-content-words',function(){
    $(this).parents(".form-group").after("<div class=\"form-group\"><div class=\"add-content-word\" style=\"margin-left: 69px;\"><input type=\"text\" class=\"form-control required\"><span class=\"add-content-words\" style=\"margin-left:4px\">+</span><span class=\"delete-content-words\" style=\"margin-left:4px\">×</span></div></div>");
 });
//删除
 $(document).on("click",'.delete-content-words',function(){
    $(this).parents(".form-group").remove();
 })
</script>
