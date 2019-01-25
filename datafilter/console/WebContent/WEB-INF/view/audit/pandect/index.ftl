<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>数据总览</title>
    <link href="/css/bootstrap.min14ed.css" rel="stylesheet"> 
   	<link href="/css/font-awesome.min93e3.css" rel="stylesheet">
    <link href="/css/animate.min.css" rel="stylesheet">
    <link href="/css/style.min862f.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/filter-base.css"/>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">

        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
						<table id="list" class="table table-striped table-bordered table-hover dataTables-example">
							<thead>
								<tr>
									<th>名称</th>
									<th>待审核数</th>
									<th>今日通过人工/系统</th>
									<th>今日参与人数</th>
									<th>总数据量</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
                      
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title general-canvas">
                    	<p class="general-drawing">概况图</p>
                    	<p class="pending-book" style="left: 0;">待审核数</p>
                       <canvas id="line" width="900" height="300"></canvas>
                    </div>
                    <div class="ibox-title">
                    	<select name="" style="margin-left: 64px;" class="select-kinds">
                    		<option value="-1">总览</option>
                    		<option value="0">评论</option>
                    		<option value="1">投资圈</option>
                    		<option value="2">解盘</option>
                    		<option value="3">问答</option>
                    		<option value="4">商城</option>
                    	</select>
                    	<button type="button" class="btn btn-info today" value="0">当日待审核数</button>
                    	<button type="button" class="btn btn-info today" value="1">今日通过</button>
                    	<button type="button" class="btn btn-info today" value="-1">当日今日参与人数</button>
                    </div>
                </div>
            </div>
       </div>
    </div>
    <script src="/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/Chart.js" type="text/javascript" charset="utf-8"></script>
    <script src="/js/jquery.dataTables.js" type="text/javascript" charset="utf-8"></script>
    <script src="/js/jquery.dataTables.bootstrap.js"></script>
</body>
</html>
<script>
    //表格内容：
    var listTable;
    $(function () {
        listTable = $("#list").dataTable({
            bAutoWidth: false,
            bSort: false,
            oSearch: false,
            bFilter: false,
            bInfo: false,//页脚信息
            bLengthChange: false, //改变每页显示数据数量
            bPaginate: false, //翻页功能
            aoColumns: [
                {
                    "mData":"projectName"
                },
                {
                    "mData":"checkCount"
                },
                {
                    "sWidth": "200px",
                    "sClass": "center",
                    "fnRender": function(a) {
                        return a.aData["passManualCount"]+'/'+a.aData["passSysCount"];
                    }
                },
                {
                    "mData":"participationCount"
                },
                {
                    "mData":"totalCount"
                }
            ],
            aoColumnDefs: [{
                sDefaultContent: '',
                aTargets: ['_all']
            }],
            bServerSide: true,
            sAjaxSource: "/data/listDataCount.do",
            fnServerData: function(source, data, fnCallBack) {

                $.ajax({
                    type: "GET",
                    dataType: "json",
                    url: source,
                    data: {},
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
    });


</script>


<script type="text/javascript">
	var projectType;
	var statusData;
	$(function(){
		projectType = -1;
		statusData = 0 ;
		getAjax();
		//获取参数值
		$('.select-kinds').change(function(){
			projectType = $(this).val();
			getAjax();
		})
		$('.today').click(function(){
			statusData = $(this).val();
			getAjax();
		})
	});
	
function getAjax(){
		//请求数据
		$.ajax({
			type:"get",
			url:"/data/listDataCountStatistic.do",
			data:"projectType=" + projectType + "&statusData=" + statusData,
			success: function(d) {
					draw(d);
                    },
                    failure: function(d) {
                        alert("服务器无响应");
                    }
		});		
}
function draw(d){
	var dataT = [];
	var dataNum = [];
	for(var i = 0; i< d.data.length;i++){
		dataT.push(d.data[i].dateTime);
		dataNum.push(d.data[i].count);
		}

	var line = document.getElementById('line').getContext('2d');
	//创建chart对象
	var lineChart = new Chart(line);
	//绘制图表使用的数据
	var data = {
		    // x轴的标识
//			labels : ["20170329","20170330","20170331","20170401","20170402","20170403","20170404"],
			labels : dataT,
// 数据，数组中的每一个object代表一条线
			datasets : [
				{   
					// 填充颜色
					fillColor : "rgba(220,220,220,0)",
					// 线的颜色
					strokeColor : "rgba(22,158,250,1)",
					// 点的填充颜色
					pointColor : "rgba(255,255,255,1)",
					// 点的边线颜色
					pointStrokeColor : "#90C9FA",
					// 与x轴标签对应的数据
//					data : [42,768,304,48,10,5,1]
					data :  dataNum
					
				},
			]
}
	//调用line方法,画出折线图
	lineChart.Line(data, {
		// y轴的起始值
        scaleStartValue : null,
		// 启动y轴修改
		   scaleOverride: true,
		// y轴刻度数
            scaleSteps: 6,
        // y轴刻度间距/宽度
            scaleStepWidth: 200,
		//背景显示内容(只显示Y轴内容)
		tooltipTemplate: "<%= value %>",
		//** Required if scaleOverride is true **
		tooltipFillColor: "rgba(22,158,250,1)",
		//String - 设置y轴x轴边框颜色
		scaleLineColor:'rgba(241, 241, 241, 1)',
		//Number - 设置边框宽度
		scaleLineWidth:2,
		//Boolean - 是否显示y轴数据: false为不显示,true为显示
		scaleShowLabels:true,
		// 标签显示值<%=value%> 是一个整体不能破坏,可以在前面或后面写
		scaleLabel:"<%=value%>",
		// 是否显示背景网格线
		scaleShowGridLines :true,
		// 是否要曲线显示 false为折线,true为曲线
		bezierCurve: false,
		// 是否要显示小圆点
		pointDot : true,
		
		//是否要显示 数据连接线
		datasetStroke : false,
		// 数据连接线的宽度
		datasetStrokeWidth : 2,
		// 是否显示加载完成的动画
		animation : true,
		// 动画加载的时间,步数越多加载的时间越长
		animationSteps : 60,
		// 动画加载的函数
		animationEasing : "easeOutQuart",
		// 动画加载完是否要执行函数,null不执行
		onAnimationComplete : function(){
					this.showTooltip(this.datasets[0].points, true);//
				},
		tooltipEvents: [],

		showTooltips: true
	});
}
</script>
