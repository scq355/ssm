<#import "../include/common-css.ftl" as css/>
<#import "../include/common-js.ftl" as js/>
<#escape x as x?html>
<!DOCTYPE html>
<html lang="en">
<head>
<@css.description/>
<@css.ace/>
</head>

<body class="login-layout">
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <div class="center">
                        <h1>
                            <i class="icon-leaf green"></i>
                            <span class="red">Ace</span>
                            <span class="white">后台管理系统</span>
                        </h1>
                        <h4 class="blue">&copy; 中国科技集团</h4>
                    </div>
                    <div class="space-6"></div>
                    <div class="position-relative">
                        <#--用户登录-->
                        <div id="login-box" class="login-box visible widget-box no-border" style="width:101%!important;">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header blue lighter bigger">
                                        <i class="icon-coffee green"></i>
                                        用户登录
                                    </h4>
                                    <div class="space-6"></div>
                                    <form id="login-form">
                                        <fieldset>
                                            <label id="username" class="block clearfix">
                                                <span class="block input-icon input-icon-right">
                                                  <input type="text" name="userName" class="form-control" placeholder="Username"/>
                                                  <i class="icon-user"></i>
                                                </span>
                                            </label>
                                            <label id="password" class="block clearfix">
                                                <span class="block input-icon input-icon-right">
                                                  <input type="password" name="password" class="form-control" placeholder="Password"/>
                                                  <i class="icon-lock"></i>
                                                </span>
                                            </label>
                                            <label id="verify" class="block">
                                                <span class="clearfix">
                                                    <span class="pull-left">
                                                        <input type="text" name="verify" class="form-control" placeholder="验证码" />
                                                    </span>
                                                    <span class="pull-right">
                                                        <img id="verify-img" class="verify-img" src="/imageServlet" height="34" style="cursor:pointer;" alt="验证码"/>
                                                    </span>
                                                </span>
                                            </label>
                                            <div class="clearfix">
                                                <label class="inline">
                                                    <input type="checkbox" class="ace"/>
                                                    <span class="lbl"> 记住我</span>
                                                </label>
                                                <button type="submit" class="width-35 pull-right btn btn-sm btn-primary">
                                                    <i class="icon-key"></i>
                                                    登 录
                                                </button>
                                            </div>
                                            <div class="space-4"></div>
                                        </fieldset>
                                    </form>
                                </div>
                                <div class="toolbar clearfix">
                                    <div>
                                        <a href="/forget" class="forgot-password-link">
                                            <i class="icon-arrow-left"></i>
                                            忘记密码?
                                        </a>
                                    </div>
                                    <div>
                                        <a href="/register" class="user-signup-link">
                                            还未注册?
                                            <i class="icon-arrow-right"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <#--用户登录-->
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<@js.ace/>
<script src="/js/app.js"></script>
<script type="text/javascript">
var changeVerifyImg = function () {
    $('#verify-img').attr('src', '/imageServlet?date='+ new Date().getSeconds());
};

$('#verify-img').click(function(){
    changeVerifyImg();
});

var $u = $('#username')
        , $p = $('#password')
        , $v = $('#verify')
        , $form = $('#login-form');

var warning = [
    '用户名不能为空',
    '用户名不存在',
    '密码不能为空',
    '密码错误',
    '验证码不能为空',
    '验证码错误',
    '提交的信息不完整',
    '服务器错误',
    '网络错误',
    '未知错误,请重试'
];

$form.submit(function(e){
    e.preventDefault();
    var username = $u.find('input').val()
            , password = $p.find('input').val()
            , verify = $v.find('input').val();

    if (!username) {
        $.warningControl($u, warning[0], true);
        return;
    } else {
        $.warningControl($u, '', false);
    }

    if (!password) {
        $.warningControl($p, warning[2], true);
        return;
    } else {
        $.warningControl($p, '', false);
    }

    if (!verify) {
        $.warningControl($v, warning[4], true);
        return;
    } else {
        $.warningControl($v, '', false);
    }

    $.ajax({
        async: true,
        data: $form.serialize(),
        method: 'POST',
        url: '/login/check'
    }).done(function(data){
        switch (data.code) {
            case '0':
                window.location.href = '/';
                break;
            case '1':
                // 用户名不存在
                $.warningControl($u, warning[1], true);
                changeVerifyImg();
                break;
            case '2':
                // 密码错误
                $.warningControl($p, warning[3], true);
                changeVerifyImg();
                break;
            case '3':
                // 填写信息不完整
                $.warningControl($u, warning[6], true);
                changeVerifyImg();
                break;
            case '4':
                // 验证码不正确
                $.warningControl($v, warning[5], true);
                changeVerifyImg();
                break;
            case '5':
                // 服务器错误
                $.warningControl($u, warning[7], true);
                changeVerifyImg();
                break;
            default:
                alert(warning[9]);
        }
    }).fail(function(){
        alert(warning[8]);
    });
});
</script>
</body>
</html>
</#escape>