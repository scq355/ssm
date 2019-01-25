<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>解盘审核</title>
		<link href="/css/bootstrap.min14ed.css" rel="stylesheet">
		<link href="/css/font-awesome.min93e3.css" rel="stylesheet">
		<link href="/css/animate.min.css" rel="stylesheet">
		<link href="/css/style.min862f.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="/css/filter-base.css"/>
		</head>

	<body class="gray-bg">
		<div class="wrapper wrapper-content animated fadeIn">
			<div class="row">
				<div class="col-sm-12">
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<h5>解盘审核</h5>
						</div>
						<div class="white-user-select">
                            <button value="0">待审核<span>(${checkCount!''})</span></button>
                            <button value="2">已通过<span>（${passCount!''}）</span></button>
                            <button value="3">已拒绝<span>（${rejectCount!''}）</span></button>
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
						<div class="select-keywords-comment col-lg-4">
							<label for="">关键词：</label>
							<select id="keyWordType" name="" style="width: 80px;height: 22px;">
								<option value="0">数据内容</option>
								<option value="1">用户信息</option>
								<option value="2">操作信息</option>
							</select>
							<input type="text" name="" id="input-keywords" value="" />
							<input type="checkbox" name="" id="imgchoose" value="" />
							<span>有图</span>
							<button id="search-shop">查询</button>
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
									<th>数据内容</th>
									<th>提问人</th>
									<th>过滤词</th>
									<th>操作信息</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						<tr>
							<button class="btn btn-info btn-sm" onclick="selectAll()">全选</button>
							<button class="btn btn-info btn-sm" onclick="cancelAll()">取消</button>
							<button class="btn btn-info btn-sm" onclick="adoptAll()">通过</button>
							<button class="btn btn-info btn-sm" onclick="freezeID()">冻ID</button>
							<button class="btn btn-info btn-sm" onclick="freezeIP()">冻IP</button>
						</tr>
					</div>
				</div>
			</div>
		</div>
		
		
		<div class="freeze-div">
			<div class="freeze-div-select freeze-reason">
				<p>请选择冻结理由</p>
				<span class="freeze-cancel">×</span>
				<form action="" method="get">
					<label><input name="reason" type="radio" value="广告营销" />广告营销 </label>
					<label><input name="reason" type="radio" value="淫秽色情" />淫秽色情</label>
					<label><input name="reason" type="radio" value="违法犯罪" />违法犯罪 </label>
					<label><input name="reason" type="radio" value="骚扰谩骂" />骚扰谩骂</label>
					<label>
						<input name="reason" type="radio" value="其它" />其它 
					</label>
					<input type="text" name="" id="please-details" value="请具体描述" />
				</form>
			</div>
			<div class="freeze-div-select freeze-special">
				<p>请选择冻结时长</p>
				<form action="" method="get">
					<label><input name="time" type="radio" value="1" />一天</label>
					<label><input name="time" type="radio" value="3" />三天</label>
					<label><input name="time" type="radio" value="7" />一周</label>
					<label><input name="time" type="radio" value="30" />一个月</label>
					<label><input name="time" type="radio" value="90" />三个月</label>
					<label><input name="time" type="radio" value="180" />六个月 </label>
					<label><input name="time" type="radio" value="360" />一年</label>
					<label><input name="time" type="radio" value="-1" />永久 </label>
				</form>
			</div>
			<div class="freeze-div-select freeze-yesno">
				<p>是否处理相关数据</p>
				<form action="" method="get">
					<label style="margin-left: 70px;"><input name="ok" type="radio" value="false" />否 </label>
					<label><input name="ok" type="radio" value="true" />是</label>
				</form>
			</div>
			<div>
				<input type="submit" name="" id="freeze-submit" value="提交" />
			</div>
		</div>
		<div class="freeze-divs">
			<div class="freeze-div-select freezes-div-select">
				<p>请选择冻结理由</p>
				<span class="freeze-cancel">×</span>
				<form action="">
					<label><input type="radio" name="refuse" id="" value="广告营销" />广告营销</label>
					<label><input type="radio" name="refuse" id="" value="淫秽色情" />淫秽色情</label>
					<label><input type="radio" name="refuse" id="" value="违法犯罪" />违法犯罪</label>
					<label><input type="radio" name="refuse" id="" value="骚扰谩骂" />骚扰谩骂</label>
					<label><input type="radio" name="refuse" id="" value="其他" />其他</label>
					<input style="height: 20px;width: 90px;display: inline-block;" type="text" name="" id="" value="请具体描述" />
				</form>
			</div>
			<div>
				<input type="submit" name="" id="refuse-details" value="提交" />
			</div>
		</div>
		<div class="auditLogs">
			<span>×</span>
			<table border="" cellspacing="" cellpadding="">
				<caption>操作日志</caption>
				<tr>
					<th style="width: 180px;">操作时间</th>
					<th style="width: 120px;">操作人</th>
					<th>操作信息</th>
					<th style="width: 150px;" class="operation-details">操作理由</th>
				</tr>
			</table>
		</div>
		<div class="showslide">
			<span class="cancel-show">x</span>
			<div class="swiper-container">
            	<div class="swiper-wrapper">
                	
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
	</body>

</html>
<script type="text/javascript">
	var test = 0;
	//选择待审核、已通过、已拒绝选项
	$('.white-user-select>button').click(function() {
		$('.white-user-select>button').css({
			'background': '#21b9bb',
			'color': 'black'
		});
		$(this).css({
			'background': '#DA4453',
			'color': 'white'
		})
		test = $(this).val();
		//不同页面的底部按钮不同
		if(test == '2'){
			$('.btn-info').show();
			$('#dif-adopt').hide();
			$('#dif-back').hide();
		}else if(test == '3'){
			$('.btn-info').show();
			$('#dif-adopt').hide();
			$('#dif-refuse').hide();
		}else{
			$('.btn-info').show();
			$('#dif-back').hide();
		}
		listTable.fnDraw();
	})

	//待审核表
	var listTable = $("#list").dataTable({
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
		aoColumns: [{
				"sWidth": "70px",
				"sClass": "center",
				"fnRender": function(a) {
					return '<label class="position-relative" onclick="addSelectedClass(this)"><input type="checkbox" id="' + a.aData["id"] + '" class="ace"  value="' + a.aData["id"] + '"/><span class="lbl"></span></label>';
				}
			},
			{
				"mData": "id"
			},
			{
				"sWidth": "480px",
				"sClass": "center",
				"fnRender": function(a) {
					var filterData = a.aData["filterData"];
					var url = a.aData["url"];
					var title = a.aData["title"];
					var imgUrl = a.aData["imgUrls"];
					if(imgUrl != null | undefined){
					var str = '<a class="left" href=' + url + '>' + title + '</a>' +
						'<a class="right showimg" onclick=imgLook("'+imgUrl+'")>查看图片</a><br /> ' +
						'<p class="data-rules">' + filterData + '</p>';
						return str;
					}else{
						var str = '<a class="left" href=' + url + '>' + title + '</a>' +
						'<p class="data-rules">' + filterData + '</p>';
						return str;
					}
				}
			},
			{
				"sWidth": "240px",
				"sClass": "center",
				"fnRender": function(a) {
					var userName = a.aData["userName"];
					var userId = a.aData["userId"];
					var ip = a.aData["ip"];
					var dateTime = a.aData["createTime"];
					var str = '<span class=getName>' + userName + '</span><a onclick=searchUsers("' + userId + '") style="margin-left:1em">查看</a><br />' +
						'ID:&nbsp;' + '<span class=getId>' + userId + '</span>' + '<a href="javascript:freeId(' + a.aData["id"] + ')" class="freeze freezeID">冻</a><br />' +
						'IP:&nbsp;' + '<span class=getIp>' + ip + '</span>' + '<a href="javascript:freeIp(' + a.aData["id"] + ')" class="freeze freezeIP">冻</a><br />' +
						dateTime;
					return str;
				}
			},
			{
				"sWidth": "120px",
				"fnRender": function(a) {
		
					if(a.aData["type"] == 1) {
						return "敏感词";
					} else if(a.aData["type"] == 2) {
						return '非法词';
					} else {
						return "无";
					}

				}
			},
			{
				"sWidth": "240px",
				"fnRender": function(a) {
					var reason;
					var createdAt;
					var creator;
					if(a.aData["auditLogs"]!= undefined){
						if(test == '3'){
							reason = a.aData["auditLogs"][0].reason;
							createdAt = a.aData["auditLogs"][0].createTime;
							creator = a.aData["auditLogs"][0].creator;
							if(a.aData["auditLogs"].length>=2){
								var str ='<p class="get-auditLogs">日志</p>' + '<p>' + creator + '</p>' + '<p>' + reason + '</p>' + '<p>' + createdAt + '</p>';
									return str;
								}else{
									var str ='<p>' + creator + '</p>' + '<p>' + reason + '</p>' + '<p>' + createdAt + '</p>';
									return str;
								}
						}else{
							return "";
						}
					}else{
						return "";
					}
				}
			},
			{
				"sWidth": "170px",
				"sClass": "center",
				"fnRender": function(a) {
					var test = a.aData["status"];
					if(test == '0'){
					var strOperate = '<div class="hidden-sm hidden-xs action-buttons">';
					strOperate += '<a class="green pass-a" href="javascript:pass('+a.aData["id"]+')">通过</a>&nbsp;|&nbsp;';
					strOperate += '<a class="green refuse-a" href="javascript:jujue('+a.aData["id"]+')">拒绝</a>';
					strOperate += '</div>';
					return strOperate;
					}else if(test == '2'){
					var	strOperate = '<div class="hidden-sm hidden-xs action-buttons">';
						strOperate += '<a class="green refuse-a" href="javascript:jujue('+a.aData["id"]+')">拒绝</a>';
						strOperate += '</div>';
						return strOperate;
					}else if(test == '3'){
					var	strOperate = '<div class="hidden-sm hidden-xs action-buttons">';
						strOperate += '<a class="green refuse-back" onclick="back()" href="javascript:back('+a.aData["id"]+')">恢复</a>';
						strOperate += '</div>';
						return strOperate;
					}
				}
			}
		],
		aoColumnDefs: [{
			sDefaultContent: '',
			aTargets: ['_all']
		}],
		bServerSide: true,
		sAjaxSource: "/jiepan/list.do",
		fnServerData: function(source, data, fnCallBack) {
			var pageStart = 0;
			var pageSize = 1;
			for(var d in data) {
				if(data[d].name == "iDisplayStart")
					pageStart = data[d].value;
				else if(data[d].name == "iDisplayLength")
					pageSize = data[d].value;
			}
			// 查询内容
            var data = {
                "status": test,
                "pageStart":pageStart,
                "pageSize":pageSize};
            var queryBeginTime = $("#start").val();
            var queryEndTime = $("#end").val();
            var keyWordType = $("#keyWordType option:selected").val();
            var queryKeyword = $("#input-keywords").val();
            if($("#imgchoose").prop('checked')){
                data["hasImage"] = true;
			}
            if(queryBeginTime != ""){
                queryBeginTime = new Date(queryBeginTime);
                data["queryBeginTime"] = queryBeginTime;
            }
            if(queryEndTime != ""){
                queryEndTime = new Date(queryEndTime);
                data["queryEndTime"] = queryEndTime;
            }
            data["keyWordType"] = keyWordType;
            data["queryKeyword"] = queryKeyword;
			$.ajax({
				type: "GET",
				dataType: "json",
				url: source,
				data: data,
				success: function(d) {
					if(d.flag != true)
						alert(d.msg);
					else {
						$("#total").html(d.data.iTotalRecords);
						fnCallBack(d.data);
					}
				},
				failure: function(d) {
					alert("服务器无响应");
				}
			});
		}
	});

   //关键词搜索
    $("#search-shop").click(function(){
        listTable.fnDraw();
    });

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
function findTrs (blackWay,freezeTime,blackReason) {
    var userArr = [];
	var nTrs = listTable.fnGetNodes();//fnGetNodes获取表格所有行，nTrs[i]表示第i行tr对象
	for(var i = 0; i < nTrs.length; i++){
        if($(nTrs[i]).hasClass('selected')){
            var temp = listTable.fnGetData(nTrs[i]);//fnGetData获取一行的数据
            var user = {};
            user["userName"] = temp["userName"];
            user["userId"] = temp["userId"];
            user["ip"] = temp["ip"];
            user["blackWay"] = blackWay;
            user["freezeTime"] = freezeTime;
            user["blackReason"] = blackReason;
            userArr.push(user);
		}
	}
	return JSON.stringify(userArr);
}
//日志操作记录获取

$(document).on('click','.get-auditLogs',function(){
	addSelectedClass(this);
	var index = $('.get-auditLogs').index(this);
	var nTrs = listTable.fnGetNodes();//fnGetNodes获取表格所有行，nTrs[i]表示第i行tr对象
	var temp = listTable.fnGetData(nTrs[index]);//fnGetData获取一行的数据
	var captionVal = temp["originalData"] + '...操作日志';
	var str = "";
	for(var i = 0; i < temp['auditLogs'].length;i++){
		str = str + '<tr>'
			+'<td>' + temp["auditLogs"][i].createTime + '</td>'
			+'<td>' + temp["auditLogs"][i].creator + '</td>'
			+'<td>' + temp["auditLogs"][i].operation + '</td>'
			+'<td>' + temp["auditLogs"][i].reason + '</td>'
			+'</tr>';	
	}
	$('.auditLogs tbody').append(str);
	$('.auditLogs caption').html(captionVal);
})	
//点击提交冻单个ID/IP
	$('#freeze-submit').click(function() {
		var blackway = 1;
		var freezeTime;
		var blackReason;
		var dealDataFlag;
		var ids = getSelect();
		var userId = getUserId();
		var ip = getIp();
		var userName = getName();
		var getValFree = $('#' + ids).val();
		$('.freeze-special').find('input:radio').each(function() {
			if(this.checked == true) {
				freezeTime = $(this).val();
			}
		})
		$('.freeze-reason').find('input:radio').each(function() {
			if(this.checked == true) {
				blackReason = $(this).val();
			}
		})
		$('.freeze-yesno').find('input:radio').each(function() {
			if(this.checked == true) {
				dealDataFlag = $(this).val();
			}
		})
		var users = JSON.stringify([{
			"userName": userName,
			"userId": userId,
			"ip": ip,
			"blackWay": 1,
			"freezeTime": freezeTime,
			"blackReason": blackReason
		}]);
		var urlArr;
		if(getValFree == '1') {
			urlArr = "/jiepan/batchFreezeId.do"
		} else if(getValFree == '2') {
			urlArr = "/jiepan/batchFreezeIp.do"
		}
		$.ajax({
			type: "GET",
			dataType: "json",
			url: urlArr,
			data: {
				"users": users,
				"userIds": userId,
				"ids": ids,
				"operation": 1,
				"dealDataFlag": dealDataFlag
			},
			success: function(data) {
				if(data.flag == '1') {
					$('.freeze-div').hide();
					listTable.fnDraw();
				} else {
					alert(data.msg);
				}
			},
			failure: function(d) {
				alert("服务器无反应！");
			}
		});

	})
	//选中数据打√号设置value =1;
	function freeId(id) {
		var ids = id;
		$("#" + id).click();
		$("#" + id).attr('value', '1');
		$('.freeze-div-select').find('input:radio').each(function() {
			this.checked = false;
		})
		$('.freeze-div').show();
	}
	//选中数据打√号设置value =2;
	function freeIp(id) {
		var ids = id;
		$("#" + id).click();
		$("#" + id).attr('value', '2');
		$('.freeze-div-select').find('input:radio').each(function() {
			this.checked = false;
		})
		$('.freeze-div').show();
	}

//批量冻ID
function freezeID(){
	var ids = getSelect();
	if (ids == null || ids =="") return; 
		$('.freeze-div-select').find('input:radio').each(function(){
				this.checked = false;
		})
		$('.freeze-div').show();
		var userId = getUserId();
		var ip = getIp();
		var userName= getName();
			$(document).on('click','#freeze-submit',function(){
				var blackway = 1;
				var freezeTime;
				var blackReason;
				var dealDataFlag;
				$('.freeze-special').find('input:radio').each(function(){
					if(this.checked == true){
						freezeTime = $(this).val();
					}
					})
				$('.freeze-reason').find('input:radio').each(function(){
							if(this.checked == true){
								blackReason = $(this).val();
							}
					})
				$('.freeze-yesno').find('input:radio').each(function(){
							if(this.checked == true){
								dealDataFlag = $(this).val();
							}
				})
				var users = findTrs(blackway,freezeTime,blackReason);
					$.ajax({
						type: "GET",
						dataType: "json",
						url: "/jiepan/batchFreezeId.do",
						data: {"users":users,"userIds":userId,"ids":ids,"operation":1,"dealDataFlag":dealDataFlag},
						success:function(data) {
							if (data.flag == '1'){
								listTable.fnDraw();
								$('.freeze-div').hide();
								alert('success');
							}else{
								alert(data.msg);
							}
						},
						failure:function(d){
								alert("服务器无反应！");
						}
					});
			})
}

//批量冻IP
function freezeIP(){
	var ids = getSelect();
	if (ids == null || ids =="") return; 
		$('.freeze-div-select').find('input:radio').each(function(){
				this.checked = false;
		})
		$('.freeze-div').show();
		var userId = getUserId();
		var ip = getIp();
		var userName= getName();
			$(document).on('click','#freeze-submit',function(){
				var blackway = 1;
				var freezeTime;
				var blackReason;
				var dealDataFlag;
				$('.freeze-special').find('input:radio').each(function(){
					if(this.checked == true){
						freezeTime = $(this).val();
					}
					})
				$('.freeze-reason').find('input:radio').each(function(){
							if(this.checked == true){
								blackReason = $(this).val();
							}
					})
				$('.freeze-yesno').find('input:radio').each(function(){
							if(this.checked == true){
								dealDataFlag = $(this).val();
							}
				})
				var users = findTrs(blackway,freezeTime,blackReason);
					$.ajax({
						type: "GET",
						dataType: "json",
						url: "/jiepan/batchFreezeIp.do",
						data: {"users":users,"userIds":userId,"ids":ids,"operation":1,"dealDataFlag":dealDataFlag},
						success:function(data) {
							if (data.flag == '1'){
								listTable.fnDraw();
								$('.freeze-div').hide();
								alert('success');
							}else{
								alert(data.msg);
							}
						},
						failure:function(d){
								alert("服务器无反应！");
						}
					});
			})
}

	
	//点击通过
	function pass(id){
			var ids = id;
			if (ids == null || ids =="") return;
				var chosse = confirm("就这么轻易地通过吗？");
				if(chosse == true){
					$.ajax({
						type: "GET",
						dataType: "json",
						url: "/jiepan/updateStatus.do",
						data: {"status":2,"auditId":ids,"operation":0},
						success:function(data) {
							if (data.flag == '1'){
								listTable.fnDraw();
								alert('已经通过');
							}else{
								alert(data.msg);
							}
						},
						failure:function(d){
								alert("服务器无反应！");
						}
					});
				}
	}
//点击拒绝时提交	
	$('#refuse-details').click(function() {
		var ids = getSelect();
		var reason;
		$('.freezes-div-select').find('input:radio').each(function() {
			if(this.checked == true) {
				reason = $(this).val();
			}
		})
		var chosse = confirm("真的要拒绝人家吗？");
		if(chosse == true) {
			$.ajax({
				type: "GET",
				dataType: "json",
				url: "/jiepan/updateStatus.do",
				data: {
					"status": 3,
					"auditId": ids,
					"operation": 1,
					"reason": reason
				},
				success: function(data) {
					if(data.flag == '1') {
						listTable.fnDraw();
						$('.freeze-divs').hide();
					} else {
						alert(data.msg);
					}
				},
				failure: function(d) {
					alert("服务器无反应！");
				}
			});
		}

	})

	function jujue(id) {
		var ids = id;
		$("#" + id).click();
		if(ids == null || ids == "") return;
		$('.freeze-divs').show();
		$('.freezes-div-select').find('input:radio').each(function() {
			this.checked = false;
		})

	}

//批量通过
	function adoptAll(){
		var ids = getSelect();
		if(ids == null || ids == "") return;
		var chosse = confirm("就这么轻易地通过吗？");
		if(chosse == true) {
			$.ajax({
				type: "GET",
				dataType: "json",
				url: "/jiepan/batchUpdateStatus.do",
				data: {
					"status": 2,
					"ids": ids,
					"operation": 0
				},
				success: function(data) {
					if(data.flag == '1') {
						listTable.fnDraw();
						alert('已经通过');
					} else {
						alert(data.msg);
					}
				},
				failure: function(d) {
					alert("服务器无反应！");
				}
			});
		}
	}
//批量拒绝
	function refuseAll(){
		var ids = getSelect();
		if (ids == null || ids =="") return; 
		$('.freezes-div-select').find('input:radio').each(function(){
				this.checked = false;
		})
		$('.freeze-divs').show();
			$(document).on('click','#refuse-details',function(){
				var reason;
				$('.freezes-div-select').find('input:radio').each(function(){
					if(this.checked == true){
						reason = $(this).val();
					}
				})
				var chosse = confirm("真的要拒绝人家吗？");
				if(chosse == true){
					$.ajax({
						type: "GET",
						dataType: "json",
						url: "/jiepan/batchUpdateStatus.do",
						data: {"status":3,"ids":ids,"operation":1,"reason":reason},
						success:function(data) {
							if (data.flag == '1'){
								$('.freeze-divs').hide();
								listTable.fnDraw();
							}else{
								alert(data.msg);
							}
						},
						failure:function(d){
								alert("服务器无反应！");
						}
					});
				}
			})
	}
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
//批量获取选中数据的userId
function getUserId(){
	var userId ="";
	$("#list").find('tr > td:first-child input:checkbox')
	.each(function(){
		if (this.checked==true)
			userId += $(this).parent().parent().parent().find('.getId').html() + ',';
	});
	return userId;
}

//批量获取选中数据的ip
function getIp(){
	var ips = '';
	$("#list").find('tr > td:first-child input:checkbox')
	.each(function(){
		if (this.checked==true)
			ips += $(this).parent().parent().parent().find('.getIp').html()+ ',';
	});
	return ips;
}
//批量获取选中数据的userName
function getName(){
	var userName = '';
	$("#list").find('tr > td:first-child input:checkbox')
	.each(function(){
		if (this.checked==true)
			userName += $(this).parent().parent().parent().find('.getName').html()+ ',';
	});
	return userName;
}
//全选
function selectAll() {
	$("#list").find('tr > td:first-child input:checkbox')
		.each(function() {
			this.checked = true;
		});
}
//取消全选
function cancelAll() {
	$("#list").find('tr > td:first-child input:checkbox')
		.each(function() {
			this.checked = false;
		});
}
	//操作日志
	$(document).on('click','.get-auditLogs',function(){
		$('.auditLogs').show();
		$('.auditLogs>span').click(function(){
			$(this).parent().hide();
		})
	})
//恢复
function back(id){
	var ids = id;
	if (ids == null || ids =="") return;
	var chosse = confirm("确认要恢复此数据么？");
				if(chosse == true){
					$.ajax({
						type: "GET",
						dataType: "json",
						url: "/jiepan/updateStatus.do",
						data: {"status":2,"auditId":ids,"operation":2},
						success:function(data) {
							if (data.flag == '1'){
								listTable.fnDraw();
								alert("恢复成功");
							}else{
								alert(data.msg);
							}
						},
						failure:function(d){
								alert("服务器无反应！");
						}
					});
		}

}
//批量恢复
function backAll(){
	var ids = getSelect();
	if (ids == null || ids =="") return;
	var chosse = confirm("确认要恢复这些数据么？");
				if(chosse == true){
					$.ajax({
						type: "GET",
						dataType: "json",
						url: "/jiepan/batchUpdateStatus.do",
						data: {"status":2,"ids":ids,"operation":2},
						success:function(data) {
							if (data.flag == '1'){
								listTable.fnDraw();
							}else{
								alert(data.msg);
							}
						},
						failure:function(d){
								alert("服务器无反应！");
						}
					});
		}

}
//点击查看
function searchUsers(userId){
    window.location = "/jiepan/look.do?userId="+userId;
}
	//查看图片
function imgLook(imgUrl) {
	$('.showslide').show();
	var imgUrlArr = imgUrl.split(",");
			var imgStr=''
			for(var i = 0; i< imgUrlArr.length;i++){
				 imgStr +='<div class="swiper-slide"><img src="'+ imgUrlArr[i] + '"/></div>'
				}
//			console.log(imgStr);
	$('.swiper-wrapper').append(imgStr);
}
$(document).on('click','.cancel-show',function(){
	$('.showslide').hide();
})
</script>
<script type="text/javascript">
	//点击隐藏冻结、拒绝选区
	$('.freeze-cancel').click(function(){
		$(this).parent().parent().hide();
	})

	
</script>