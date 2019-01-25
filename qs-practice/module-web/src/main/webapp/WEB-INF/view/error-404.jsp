<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>404错误页面 - Bootstrap后台管理系统模版Ace下载</title>
    <meta name="keywords" content="Bootstrap模版,Bootstrap模版下载,Bootstrap教程,Bootstrap中文" />
    <meta name="description" content="站长素材提供Bootstrap模版,Bootstrap教程,Bootstrap中文翻译等相关Bootstrap插件下载" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <jsp:include page="common-js/header.jsp"/>
</head>

<body>
<div class="navbar navbar-default" id="navbar">
    <script type="text/javascript">
        try{ace.settings.check('navbar' , 'fixed')}catch(e){}
    </script>

    <div class="navbar-container" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="#" class="navbar-brand">
                <small>
                    <i class="icon-leaf"></i>
                    Ace Admin
                </small>
            </a>
        </div>
    </div>
</div>

<div class="main-container" id="main-container">
    <script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>

    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#">
            <span class="menu-text"></span>
        </a>

        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">

                    <div class="error-container">
                        <div class="well">
                            <h1 class="grey lighter smaller">
                                <span class="blue bigger-125">
                                    <i class="icon-sitemap"></i>
                                    404
                                </span>
                                Page Not Found
                            </h1>

                            <hr />
                            <h3 class="lighter smaller">We looked everywhere but we couldn't find it!</h3>

                            <div>
                                <form class="form-search">
                                    <span class="input-icon align-middle">
                                        <i class="icon-search"></i>
                                        <input type="text" class="search-query" placeholder="Give it a search..." />
                                    </span>
                                    <button class="btn btn-sm" onclick="return false;">Go!</button>
                                </form>

                                <div class="space"></div>
                                <h4 class="smaller">Try one of the following:</h4>

                                <ul class="list-unstyled spaced inline bigger-110 margin-15">
                                    <li>
                                        <i class="icon-hand-right blue"></i>
                                        Re-check the url for typos
                                    </li>
                                    <li>
                                        <i class="icon-hand-right blue"></i>
                                        Read the faq
                                    </li>
                                    <li>
                                        <i class="icon-hand-right blue"></i>
                                        Tell us about it
                                    </li>
                                </ul>
                            </div>

                            <hr/>
                            <div class="space"></div>

                            <div class="center">
                                <a href="javascript:window.history.back();" class="btn btn-grey">
                                    <i class="icon-arrow-left"></i>
                                    返回上一页
                                </a>
                                <a href="/" class="btn btn-primary">
                                    <i class="icon-dashboard"></i>
                                    控制台
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div>

<jsp:include page="common-js/footer.jsp"/>
</body>
</html>
