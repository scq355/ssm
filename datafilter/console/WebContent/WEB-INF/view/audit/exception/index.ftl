<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>死信</title>
    	<link href="/css/bootstrap.min14ed.css" rel="stylesheet">
		<link href="/css/font-awesome.min93e3.css" rel="stylesheet">
		<link href="/css/animate.min.css" rel="stylesheet">
		<link href="/css/style.min862f.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="/css/filter-base.css" />
	</head>
<body>
<body class="gray-bg">
		<div class="wrapper wrapper-content animated fadeIn">
			<div class="row">
				<div class="col-sm-12">
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<h5>死信</h5>
						</div>
						<div class=" col-lg-6">
							<form class="form-horizontal m-t">
								<div class="form-group">
									<label class="col-sm-2 control-label">时间段：</label>
									<div class="col-sm-10" id="hello">
										<input placeholder="开始日期" class="form-control layer-date" id="start" readonly="readonly">
										<input placeholder="结束日期" class="form-control layer-date" id="end" readonly="readonly">
									</div>
								</div>
							</form>
						</div>
						<div class="select-keywords-comment col-lg-5">
							<select name="" id="projectType" style="width: 80px;height: 22px;">
								<option value="0">评论审核</option>
								<option value="1">投资圈审核</option>
								<option value="2">解盘审核</option>
								<option value="3">问答审核</option>
								<option value="4">商城审核</option>
							</select>
                            <input type="button" name="" id="button-keywords" value="查询" style="background: #21b9bb;"/>
						</div>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="ibox-content">
						<table id="list" class="table table-striped table-bordered table-hover dataTables-example">
							<thead>
								<tr>
									<th>选择</th>
									<th>ID</th>
									<th>时间</th>
									<th>项目</th>
									<th>错误信息</th>
									<th>对象信息</th>
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

		<script src="/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/js/bootstrap.min.js"></script>
		<script src="/layer/laydate/laydate.js"></script>
		<script src="/js/jquery.dataTables.js" type="text/javascript" charset="utf-8"></script>
 		<script src="/js/jquery.dataTables.bootstrap.js"></script>
		<script>
			laydate({
				elem: "#hello",
				event: "focus"
			});
			var start = {
				elem: "#start",
				format: "YYYY-MM-DD hh:mm:ss",
				min: laydate.now(-10000),
				max: "2099-06-16 23:59:59",
				istime: true,
				istoday: false,
				choose: function(datas) {
					end.min = datas;
					end.start = datas
				}
			};
			var end = {
				elem: "#end",
				format: "YYYY-MM-DD hh:mm:ss",
				min: laydate.now(-10000),
				max: "2099-06-16 23:59:59",
				istime: true,
				istoday: false,
				choose: function(datas) {
					start.max = datas
				}
			};
			laydate(start);
			laydate(end);
		</script>
		<script>
			//表格内容：
			var listTable;
			$(function () {
                	listTable = $("#list").dataTable({
                    bAutoWidth: false,
                    bSort: false,
                    oSearch: false,
                    bFilter: false,
                    "oLanguage": { // 汉化
                        "sProcessing": "正在加载数据...",
                        "sLengthMenu": "显示_MENU_条",
                        "sZeroRecords": "没有您要搜索的内容",
                        "sInfo": "从_START_ 到 _END_ 条记录——总记录数为 _TOTAL_ 条",
                        "sInfoEmpty": "记录数为0",
                        "sInfoFiltered": "(全部记录数 _MAX_  条)",
                        "sInfoPostFix": "",
                        "sSearch": "搜索",
                        "sUrl": "",
                        "oPaginate": {
                            "sFirst": "首页",
                            "sPrevious": " 上一页 ",
                            "sNext": " 下一页 ",
                            "sLast": " 末页 "
                        }
                    },
                    aoColumns: [
                        {
							"sWidth": "70px",
							"sClass": "center",
							"fnRender": function(a) {
								return '<label class="position-relative" onclick="addSelectedClass(this)"><input type="checkbox" class="ace" id="' + a.aData["id"] + '"  value="' + a.aData["id"] + '"/><span class="lbl"></span></label>';
							}
                    	},
                        {
                            "mData": "id"
                        },
						{
						    "sWidth": "200px",
                            "sClass": "center",
						  	"mData": "createTime"
						},
						{
                            "sWidth": "70px",
                            "sClass": "center",
                            "fnRender": function(a) {
						  	    var projectType = a.aData["projectType"];
                                if(projectType == 0){
									return "评论";
								}else if(projectType == 1){
                                    return "投资";
								}else if(projectType == 2){
                                    return "解盘";
                                }else if(projectType == 3){
                                    return "问答";
                                }else if(projectType == 4){
                                    return "商城";
                                }
                            }
						},
						{
						  	"mData": "msg"
						},
						{
						    "mData": "json"
						},
						{
                            "sWidth": "70px",
                            "sClass": "center",
                            "fnRender": function(a) {
                                var dealType = a.aData["dealType"];
                                if(dealType == 0){
									return '<a href="javascript:dealException('+a.aData["id"]+')"><span class="deal-a">发送</span></a>'
								}else if(dealType == 1){
									return "插入";
								}
							}
						}
                    ],
                    aoColumnDefs: [{
                        sDefaultContent: '',
                        aTargets: ['_all']
                    }],
                    bServerSide: true,
                    sAjaxSource: "/exception/list.do",
                    fnServerData: function(source, data, fnCallBack) {
                        var pageStart = 0;
                        var pageSize = 1;
                        for(var d in data) {
                            if(data[d].name == "iDisplayStart")
                                pageStart = data[d].value;
                            else if(data[d].name == "iDisplayLength")
                                pageSize = data[d].value;
                        }

						var data = {
						    "pageStart":pageStart,
                            "pageSize":pageSize};
                        //配置查询内容
						var queryBeginTime = $("#start").val();
                        var queryEndTime = $("#end").val();
                        var projectType = $("#projectType option:selected").val();

                        data["projectType"] = projectType;
                        if(queryBeginTime != ""){
                            queryBeginTime = new Date(queryBeginTime);
                            data["queryBeginTime"] = queryBeginTime;
						}
						if(queryEndTime != ""){
                            queryEndTime = new Date(queryEndTime);
                            data["queryEndTime"] = queryEndTime;
						}

                        $.ajax({
                            type: "GET",
                            dataType: "json",
                            url: source,
                            data: data,
                            success: function(d) {
                                if(d.flag == true){
                                    $("#total").html(d.data.iTotalRecords);
                                    fnCallBack(d.data);
								}
                                else {
                                    alert("数据处理异常！");
                                }
                            },
                            failure: function(d) {
                                alert("服务器无响应");
                            }
                        });
                    }
                });
                //关键词搜索
                $("#button-keywords").click(function(){
                    listTable.fnDraw();
                });
            });


			//数据处理
            function dealException(id) {
				//选中数据
                var trParent = $("#"+id).parents("tr");
				if(!trParent.hasClass("selected")){
                    $("#"+id).click();
				}
                var data = findTrs();
                var json = {"projectType":data["projectType"],"dealType":data["dealType"],"json":data["json"],
                    "status":1,"id":data["id"]};
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "/exception/updateStatus.do",
                    data: json,
                    success: function(d) {
                        if(d.flag == true)
                            alert(d.msg);
                        	trParent.remove();
                    },
                });
            }


            //添加选中单选框的class
            function addSelectedClass(eve){
                var trParent = $(eve).parent().parent();
                if(trParent.hasClass("selected")){
                    trParent.removeClass("selected");
                }else{
                    trParent.addClass("selected");
                }

            }

            //获取选中行数据，返回数据
            function findTrs () {
                var nTrs = listTable.fnGetNodes();//fnGetNodes获取表格所有行，nTrs[i]表示第i行tr对象
                for(var i = 0; i < nTrs.length; i++){
                    if($(nTrs[i]).hasClass('selected')){
                        var temp = listTable.fnGetData(nTrs[i]);//fnGetData获取一行的数据
                        return temp;
                    }
                }
            }
		</script>

</body>
</html>