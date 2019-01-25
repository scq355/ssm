<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="renderer" content="webkit">
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<title>金投过滤后台管理系统</title>
		<link href="/css/bootstrap.min14ed.css" rel="stylesheet">
		<link href="/css/font-awesome.min93e3.css" rel="stylesheet">
		<link href="/css/animate.min.css" rel="stylesheet">
		<link href="/css/style.min862f.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="/css/filter-base.css"/>
	</head>

	<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
		<div id="wrapper">
			<!--头部-->
			<header>
				<div class="navbar-left">金投过滤后台管理系统</div>
				<ul class="nav navbar-top-links navbar-right">
					<li class="divider">${admin?if_exists.realName?if_exists} &nbsp;&nbsp;您好!</li>
					<li class="divider">
						<a href="/admin/doLogOut.do" target="_top">注销</a>
					</li>
					<li class="divider">版本号：V1.0.0</li>
				</ul>
			</header>
			<!--左侧导航开始-->
			<nav class="navbar-default navbar-static-side" role="navigation">
				<div class="sidebar-collapse">
					<ul class="nav" id="side-menu">
						<li>
							<a href="#">
								<i class="fa fa-home"></i>
								<span class="nav-label">审核管理</span>
								<span class="fa arrow"></span>
							</a>
							<ul class="nav nav-second-level">
								<li>
									<a class="J_menuItem" href="/data/index.do" data-index="0">数据总览</a>
								</li>
								<li>
									<a class="J_menuItem" href="/comment/index.do">评论审核</a>
								</li>
								<li>
									<a class="J_menuItem" href="/investment/index.do">投资圈审核</a>
								</li>
								<li>
									<a class="J_menuItem" href="/jiepan/index.do">解盘审核</a>
								</li>
								<li>
									<a class="J_menuItem" href="/quiz/index.do">问答审核</a>
								</li>
								<li>
									<a class="J_menuItem" href="/market/index.do">商城审核</a>
								</li>
							</ul>

						</li>
						<li>
							<a href="#"><i class="fa fa-desktop"></i> <span class="nav-label">黑白名单</span><span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li>
									<a class="J_menuItem" href="/blackuser/index.do">黑名单</a>
								</li>
								<li>
									<a class="J_menuItem" href="/whiteuser/index.do">白名单</a>
									<ul class="nav nav-third-level">
										<li>
											<a class="J_menuItem" href="/whiteuser/add.do">新增白名单</a>
										</li>
									</ul>
								</li>
							</ul>
						</li>
						<li>
							<a href="#"><i class="fa fa-cutlery"></i> <span class="nav-label">过滤设置</span><span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li>
									<a class="J_menuItem" href="/vocabulary/index.do">过滤词库 <span class="fa arrow"></span></a>
									<ul class="nav nav-third-level">
										<li>
											<a class="J_menuItem" href="/vocabulary/add.do">新增过滤词</a>
										</li>
										<li>
											<a class="J_menuItem" href="/vocabulary/batchAdd.do">EXCEL批量新增</a>
										</li>
									</ul>
								</li>
								<li>
									<a class="J_menuItem" href="/source/index.do">来源设置</a>
								</li>
								<li>
									<a class="J_menuItem" href="/sysconfig/index.do">系统配置</a>
									<ul class="nav nav-third-level">
										<li>
											<a class="J_menuItem" href="/sysconfig/add.do">新增系统配置</a>
										</li>
									</ul>
								</li>
							</ul>
						</li>
                        <li>
                            <a href="/exception/index.do" class="J_menuItem">
                                <i class="fa fa-home"></i>
                                <span class="nav-label">死信</span>
                                <span class="fa arrow"></span>
                            </a>
                        </li>
					</ul>

				</div>
			</nav>
			<!--左侧导航结束-->
			<!--右侧部分开始-->
			<div id="page-wrapper" class="gray-bg dashbard-1">
				<div class="row border-bottom">
					<nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
						<div class="navbar-header">
							<a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
						</div>
					</nav>
				</div>
				<div class="row content-tabs">
					<nav class="page-tabs J_menuTabs">
						<div class="page-tabs-content">
							<a href="javascript:;" class="active J_menuTab" data-id="index_v1.html">首页</a>
						</div>
					</nav>
					<div class="btn-group roll-nav roll-right">
						<button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span></button>
						<ul role="menu" class="dropdown-menu dropdown-menu-right">
							<li class="J_tabCloseAll">
								<a>关闭全部选项卡</a>
							</li>
							<li class="J_tabCloseOther">
								<a>关闭其他选项卡</a>
							</li>
						</ul>
					</div>
					<a href="\admin\login.do" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
				</div>
				<div class="row J_mainContent" id="content-main">
					<iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="/data/index.do" frameborder="0" data-id="index_v1.html" seamless></iframe>
				</div>
				<div class="footer">
					<div class="pull-right">&copy; 2017-4-24
						<a href="javascript:;" target="_blank">研发四组</a>
					</div>
				</div>
			</div>
			<!--右侧部分结束-->
		</div>
		<script src="/js/jquery.min.js"></script>
		<script src="/js/bootstrap.min.js"></script>
		<script src="/js/jquery.metisMenu.js"></script>
		<script src="/js/jquery.slimscroll.min.js"></script>
		<script src="/js/layer.min.js"></script>
		<script src="/js/hplus.min.js"></script>
		<script type="text/javascript" src="/js/contabs.min.js"></script>
	</body>
</html>