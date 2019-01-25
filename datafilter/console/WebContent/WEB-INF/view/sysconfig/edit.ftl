<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>系统配置</title>
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
                        <h5><a href="/sysconfig/index.do">系统配置</a> >> <a href="javascript:;">修改</a></h5>
                   </div>
                    <div class="ibox-content">
                        <form id="form" action="" class="wizard-big">
                            <input id="id" type="hidden" value="${systemConfig?if_exists.id?if_exists}">
                            <fieldset>
                                <div class="row">
                                    <div class="col-sm-8 source-newCon">
                                        <div class="form-group">
                                            <label style="text-indent: 1em;">配置名称：</label>
                                            <input id="showName" name="" type="text" class="form-control required" value="${systemConfig?if_exists.showName?if_exists}">
                                        </div>
                                        <div class="form-group">
                                            <label>配置关键字：</label>
                                            <input id="configKey" name="" type="text" class="form-control required" value="${systemConfig?if_exists.configKey?if_exists}">
                                        </div>
                                        <div class="form-group">
                                            <label style="text-indent: 2em;">配置值：</label>
                                            <input id="configValue" name="" type="text" class="form-control required" value="${systemConfig?if_exists.configValue?if_exists}">
                                        </div>
                                        <div class="form-group">
                                            <label style="text-indent: 3em;" >备注：</label>
                                            <input id="remark" name="" type="text" class="form-control " value="${systemConfig?if_exists.remark?if_exists}">
                                        </div>
                                    </div>
                                    </div>

                            </fieldset>
                          </form>
                    <div class="source-sure">
                        <button class="btn btn-info btn-sm" id="sure-btn">确定</button>
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
    $('#sure-btn').click(function () {
        var id = $("#id").val();
        var configName = $('#showName').val();
        var configKey = $('#configKey').val();
        var configValue = $('#configValue').val();
        var remark = $('#remark').val();
        $.ajax({
            type:"post",
            url:"/sysconfig/update.do",
            data:"id=" + id + "&configName=" + configName + "&configKey=" + configKey + "&configValue=" + configValue + "&remark=" + remark,
            dataType: "json",
            success:function(data){
                window.location.href = "/sysconfig/index.do";
            }
        });

    })
    
    $('#return').click(function () {
        history.go(-1);
    })
})
</script>
