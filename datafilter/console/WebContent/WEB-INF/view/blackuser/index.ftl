<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>黑名单</title>
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
						<h5>黑名单</h5>
					</div>
				</div>
				<div class="select-keywords">
					<select id="type">
						<option value="1">冻结用户</option>
						<option value="2">冻结IP</option>
					</select>
					<label for="" style="margin-left:1em;">操作人：</label>
					<select id="modifier-select" style="width: 80px;">
						<option value="">全部</option>
						<option value="系统">系统</option>
						<option value="其它">其它</option>
					</select>
					<span>关键字：</span>
					<input type="text" name="" id="input-keywords" value="" />
					<input type="button" name="" id="button-keywords" value="查询" />
				</div>
				<div class="ibox-content">
					<table id="list" class="table table-striped table-bordered table-hover dataTables-example">
						<thead>
							<tr>
								<th>选择</th>
								<th>ID</th>
								<th>用户ID</th>
								<th>用户昵称</th>
								<th>IP</th>
								<th>操作人</th>
								<th>冻结时间</th>
								<th>冻结理由</th>
								<th>冻结时效</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<tr>
						<button class="btn btn-info btn-sm" onclick="selectAll()">全选</button>
						<button class="btn btn-info btn-sm" onclick="cancelAll()">取消</button>
						<button class="btn btn-info btn-sm" onclick="unfreeze()">解冻</button>
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
   	aoColumns: [{
					"sWidth":"70px",
					"sClass":"center",
				    "fnRender": function (a) {
				        return '<label class="position-relative"><input type="checkbox" class="ace" value="'+a.aData["id"]+'"/><span class="lbl"></span></label>';
				    }
				},
				{ "mData": "id"},
				{ "mData": "userId"},
				{ "mData": "userName"},
				{ "mData": "ip","bVisible": false},
				{ "mData": "modifier","sWidth":"170px" },
				{ "mData": "updateTime","sWidth":"170px" },
				{ "mData": "blackReason"},
				{ "mData": "blackEndTimeStr"},
				{
					"sWidth":"170px",
					"sClass":"center",
					"fnRender": function (a) {
				    	var strOperate = '<div class="hidden-sm hidden-xs action-buttons">';
				    	strOperate += '<a class="green" href="javascript:edit('+a.aData["id"]+')">修改</a>&nbsp;|&nbsp;';
				    	strOperate += '<a class="green" href="javascript:unfreeze('+a.aData["id"]+')">解冻</a>';
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
   	sAjaxSource:"/blackuser/list.do",
   	fnServerData:function(source, data, fnCallBack){ 
	   	var blackWay = $("#type").val();
	   	var userNameLike = "";
	   	var ip = "";
	   	if (blackWay == 1)
	   		userNameLike = $("#input-keywords").val();
	   	else if (blackWay == 2)
	   		ip = $("#input-keywords").val();
	   	var modifier = $('#modifier-select').val();
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
		   data: "blackWay=" + blackWay + "&userNameLike=" + userNameLike + "&ip=" + ip + "&modifier=" + modifier + "&pageStart=" + pageStart + "&pageSize=" + pageSize,
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
var type = $('#type').val();
if (type == 1) {
	listTable.fnSetColumnVis(2,true,false);
	listTable.fnSetColumnVis(3,true,false);
	listTable.fnSetColumnVis(4,false,false);
} else {
	listTable.fnSetColumnVis(2,false,false);
	listTable.fnSetColumnVis(3,false,false);
	listTable.fnSetColumnVis(4,true,false);
}
listTable.fnDraw();

//修改黑名单
window.edit = function(id) {
	if(id==""){
		alert("不能编辑不存在的资料！");
		return;
	}
	window.location = "/blackuser/edit.do?id="+id;	
}

//解冻
window.unfreeze = function(id) {
	var ids = [];
	if (id != null && id != "")
		ids.push(id);
	else
		ids = getSelect();
	var chosse = confirm("确认要解冻此数据吗？");
	if (chosse == true) {
		$.ajax({
			type: "POST",
			dataType: "json",
			url: "/blackuser/batch/id/unfreeze.do",
			data: "ids=" + ids,
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

//获取选中的数据ID
function getSelect() {
	var ids = [];
	$("#list").find('tr > td:first-child input:checkbox')
	.each(function(){
		if (this.checked==true)
			ids.push($(this).val());
	});
	if (ids.length < 1) {
		alert("请至少选中一个对象！");
		return "";
	}
	return ids;
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


//关键词搜索
$("#button-keywords").click(function(){
	listTable.fnDraw();
});
//修改人下拉框改变
$('#modifier-select').change(function(){
	var modifier = $(this).val();
	listTable.fnDraw();
})
//user或IP选择的下拉框改变
$('#type').change(function(){
	$("#input-keywords").val('');
	var type = $('#type').val();
	if (type == 1) {
		listTable.fnSetColumnVis(2,true,false);
		listTable.fnSetColumnVis(3,true,false);
		listTable.fnSetColumnVis(4,false,false);
	} else {
		listTable.fnSetColumnVis(2,false,false);
		listTable.fnSetColumnVis(3,false,false);
		listTable.fnSetColumnVis(4,true,false);
	}
	listTable.fnDraw();
})
</script>
