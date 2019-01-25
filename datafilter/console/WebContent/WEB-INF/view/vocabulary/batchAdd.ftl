<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>批量新增</title>
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
                        <h5><a href="index.ftl">过滤词库</a> >> <a href="javascript:;">批量新增</a></h5>
                    </div>
                    <div class="ibox-content">
                       
                    <a href="/excel/batchAddVocabularyTemplate.xls" class="download-click">点击下载模板</a>
                        <form id="fileForm" class="wizard-big" enctype="multipart/form-data">
                            <fieldset>
                                <div class="row">
                                    <div class="col-sm-8 source-newCon">
                                        <div class="form-group">
                                            
                                        </div>
                                        <div class="form-group">
                                            <label>词汇内容：</label>
                                            <input type="file" name="file" id="file" value="" />                    
                                        </div>
                                    </div>

                                </fieldset>
                            </form>
                        <div class="source-sure">
                            <button id="sure-btn" class="btn btn-info btn-sm" onclick="upFile()">确定</button>
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

function upFile() {
    $.ajax({
        url: '/vocabulary/upExcelBatchAddData.do',
        type: 'POST',
        cache: false,
        data: new FormData($('#fileForm')[0]),
        processData: false,
        contentType: false,
        success: function (json) {
            window.location.href = "/vocabulary/index.do";
        },
        error: function (json) {
            alert("失败:" + json.msg);
        }
    });
}

//【返回】按钮
$("#return").click(function(){
  window.location.href = "/vocabulary/index.do";
});
</script>
