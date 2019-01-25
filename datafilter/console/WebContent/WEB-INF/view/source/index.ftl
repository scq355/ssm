<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>来源设置</title>
    <link href="/css/bootstrap.min14ed.css" rel="stylesheet">
    <link href="/css/font-awesome.min93e3.css" rel="stylesheet">
    <link href="/css/animate.min.css" rel="stylesheet">
    <link href="/css/style.min862f.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/filter-base.css"/>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>来源列表</h5>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <table id="list" class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>来源名称</th>
                                    <th>来源关键字</th>
                                    <th>审核规则</th>
                                    <th>修改人</th>
                                    <th>修改时间</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                           <tbody>
                           </tbody>
                        </table>
                        	
                    </div>
            </div>
            </div>
        </div>
   </div>
    <script src="/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/jquery.dataTables.js" type="text/javascript" charset="utf-8"></script>
    <script src="/js/jquery.dataTables.bootstrap.js"></script>
</body>

</html>
<script type="text/javascript">
var listTable = $("#list").dataTable({
    bSort:false,
    oSearch : false,
    bFilter: false,
    "oLanguage" : { // 汉化
        "sProcessing" : "正在加载数据...",
        "sLengthMenu" : "显示_MENU_条",
        "sZeroRecords" : "没有您要搜索的内容",
        "sInfo" : "从_START_ 到 _END_ 条记录——总记录数为 _TOTAL_ 条",
        "sInfoEmpty" : "记录数为0",
        "sInfoFiltered" : "(全部记录数 _MAX_  条)",
        "sInfoPostFix" : "",
        "sSearch" : "搜索",
        "sUrl" : "",
        "oPaginate" : {
          "sFirst" : "首页",
          "sPrevious" : " 上一页 ",
          "sNext" : " 下一页 ",
          "sLast" : " 末页 "
        }
      },
   aoColumns: [
                { "mData": "id"},
                { "mData": "sourceName"},
                { "mData": "sourceKey"},
                {
                    "fnRender": function (a) {
                            if (a.aData["auditRule"] == "1")
                                return '先发后审';
                            else if (a.aData["auditRule"] == "2")
                                return '先审后发';
                            else if (a.aData["auditRule"] == "3")
                                return '动态审核';
                    }
                },
                { "mData": "modifier"},
                { "mData": "updateTime"},
                {
                    "sClass":"center",
                    "fnRender": function (a) {
                        var strOperate = '<div class="hidden-sm hidden-xs action-buttons">';
                        strOperate += '<a class="green" href="javascript:edit('+a.aData["id"]+')">修改</a>';
                        strOperate += '</div>';
                        return strOperate;
                    }
                }
              ],
   aoColumnDefs: [
                {
                 sDefaultContent: '',
                 aTargets: ['_all']
                  }
                ],
   bServerSide:true,
   sAjaxSource:"/source/list.do",
   fnServerData:function(source, data, fnCallBack){ 
       var pageStart = 0;
       var pageSize = 1;
       for (var d in data) {
           if (data[d].name == "iDisplayStart")
               pageStart = data[d].value;
           else if (data[d].name == "iDisplayLength")
               pageSize = data[d].value;
       }
       $.ajax({
           type:"GET",
           dataType:"json",
           url:source,
           data: "pageStart=" + pageStart + "&pageSize=" + pageSize,
           success:function(d){
               if(d.flag != true)
                   alert(d.msg);
               else{
                   $("#total").html(d.data.iTotalRecords);
                   fnCallBack(d.data);
               }
           },
           failure:function(d){
               alert("服务器无响应");
           }
       }); 
   }
});

window.edit = function(id) {
    if(id==""){
        alert("不能编辑不存在的资料！");
        return;
    }
    window.location = "/source/edit.do?id="+id;
}
</script>
