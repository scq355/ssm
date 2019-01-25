<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>过滤词库</title>
    <link href="/css/bootstrap.min14ed.css" rel="stylesheet">
    <link href="/css/font-awesome.min93e3.css" rel="stylesheet">
    <link href="/css/animate.min.css" rel="stylesheet">
    <link href="/css/style.min862f.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/filter-base.css"/>
	<style type="text/css">
        .btn{
        	margin-right: 20px;
        }
	</style>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>过滤词库</h5>
                        </div>
                    </div>
                    <div class="select-keywords-filter">
                    	<div>
                    	<label>词汇类型：</label>
                        	<select id="type">
                        		<option value="">全部</option>
                        		<option value="1">敏感词</option>
								<option value="2">非法词</option>
							</select>
                    	</div>
                    	<div>
                    		<span>词汇内容：</span>
                    		<input type="text" name="" id="input-keywords" value="" />
                    		<input type="button" name="" id="button-keywords" value="查询" />
                    	</div>
                    	<div class="add-newRight">
                    		<a href="/vocabulary/add.do"><input type="button" name="" class="add-newSource" value="新增过滤词" /></a>
                    		<a href="/vocabulary/batchAdd.do"><input type="button" name="" class="add-newSource" value="EXCEL批量新增" /></a>
                    	</div>
                    	
                    	
                    
                    </div>
                    <div class="ibox-content">
                        <table id="list" class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                    <th>选择</th>
                                    <th>ID</th>
                                    <th>词汇内容</th>
                                    <th>词汇类型</th>
                                    <th>更改人</th>
                                    <th>更新时间</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                           <tbody>
                           </tbody>
                        </table>
                        	<tr> 
                        	<button class="btn btn-info btn-sm" onclick="selectAll()">全选</button>
		              		<button class="btn btn-info btn-sm" onclick="cancelAll()">取消</button>
		              		<button class="btn btn-info btn-sm" onclick="batchDelete()">批量删除</button>
		              		<button class="btn btn-info btn-sm" onclick="batchUpdate(2)">改为非法词</button>
		              		<button class="btn btn-info btn-sm" onclick="batchUpdate(1)">改为敏感词</button>
                        	</tr>
                    </div>
 					
            </div>
            </div>
        </div>
   </div>
    <script src="/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="/js/bootstrap.min.js?v=3.3.6"></script>
 	<script src="/js/jquery.dataTables.js" type="text/javascript" charset="utf-8"></script>
 	<script src="/js/jquery.dataTables.bootstrap.js"></script>
</body>

</html>
<script type="text/javascript">
var listTable = $("#list").dataTable({
	bAutoWidth:false,
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
				{
					"sWidth":"70px",
					"sClass":"center",
				    "fnRender": function (a) {
				        return '<label class="position-relative"><input type="checkbox" class="ace" value="'+a.aData["id"]+'"/><span class="lbl"></span></label>';
				    }
				},
				{ "mData": "id"},
				{ "mData": "content"},
				{
					"sWidth":"120px",
				    "fnRender": function (a) {
					    	if(a.aData["type"] == "1")
					    		return '敏感词';
					    	else
					    		return '非法词';
				    }
				},
				{ "mData": "modifier","sWidth":"170px" },
				{ "mData": "updateTime","sWidth":"170px" },
				{
					"sWidth":"170px",
					"sClass":"center",
					"fnRender": function (a) {
				    	var strOperate = '<div class="hidden-sm hidden-xs action-buttons">';
				    	strOperate += '<a class="green" href="javascript:edit('+a.aData["id"]+')">修改</a>&nbsp;|&nbsp;';
				    	strOperate += '<a class="green" href="javascript:del('+a.aData["id"]+')">删除</a>';
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
   sAjaxSource:"/vocabulary/list.do",
   fnServerData:function(source, data, fnCallBack){ 
	   var type = $("#type").val();
	   var contentLike = $("#input-keywords").val();
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
		   data: "type=" + type + "&contentLike=" + contentLike + "&pageStart=" + pageStart + "&pageSize=" + pageSize,
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
	window.location = "/vocabulary/edit.do?id="+id;
}

window.del = function(id) {
	if(id==""){
		alert("不能删除不存在的资料！");
		return;
	}
	var chosse = confirm("确认要删除此数据吗？");
	if (chosse == true) {
		$.ajax({
			type: "GET",
			dataType: "json",
			url: "/vocabulary/delete.do",
			data: "id=" + id,
			success:function(data) {
				if (data.flag == 1)
					listTable.fnDraw();
				else
					alert(data.msg);
			},
			failure:function(data){
				alert("服务器无反应！");
			}
		});
	}
}




//过滤词类型下拉框
$("#type").change(function(){
	listTable.fnDraw();
});
//关键词搜索
$("#button-keywords").click(function(){
	listTable.fnDraw();
});

//批量删除
function batchDelete() {
	var ids = getSelect();
	if (ids == null || ids =="") return;
	var chosse = confirm("确认进行批量删除吗？");
	if (chosse == true) {
		$.ajax({
			type: "GET",
			dataType: "json",
			url: "/vocabulary/batchDelete.do",
			data: "ids=" + ids,
			success:function(data) {
				if (data.flag == 1)
					listTable.fnDraw();
				else
					alert(data.msg);
			}
		});
	}
};
//批量更改类型，非法词/敏感词
function batchUpdate(type) {
	var ids = getSelect();
	if (ids == null || ids =="") return;
	$.ajax({
		type: "GET",
		dataType: "json",
		url: "/vocabulary/batchUpdate.do",
		data: "ids=" + ids + "&type=" + type,
		success:function(data) {
			if (data.flag == 1)
				listTable.fnDraw();
			else
				alert(data.msg);
		}
	});
};

//获取选中的数据ID
function getSelect() {
	var ids = "";
	$("#list").find('tr > td:first-child input:checkbox')
	.each(function(){
		if (this.checked==true)
			ids += $(this).val() + ",";
	});
	if (ids == "") {
		alert("请至少选中一个对象！");
		return "";
	}
	return ids.substring(0, ids.length-1);
}	
//全选
function selectAll() {
	$("#list").find('tr > td:first-child input:checkbox')
	.each(function(){
		this.checked = true;
	});
}

//取消全选
function cancelAll() {
	$("#list").find('tr > td:first-child input:checkbox')
	.each(function(){
		this.checked = false;
	});
}


</script>
