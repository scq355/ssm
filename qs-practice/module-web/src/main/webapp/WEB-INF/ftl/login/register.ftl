<#import "../include/common-css.ftl" as css/>
<#import "../include/common-js.ftl" as js/>
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
                    <#--用户注册-->
                    <div id="signup-box" class="signup-box visible widget-box no-border" style="width:101%!important;">
                        <div class="widget-body">
                            <div class="widget-main">
                                <h4 class="header green lighter bigger">
                                    <i class="icon-group blue"></i>
                                    新用户注册
                                </h4>
                                <div class="space-6"></div>
                                <p> 输入注册信息: </p>

                                <form id="register-form">
                                    <fieldset>
                                        <label id="email" class="block clearfix">
                                            <span class="block input-icon input-icon-right">
                                              <input type="email" class="form-control" placeholder="邮箱" />
                                              <i class="icon-envelope"></i>
                                            </span>
                                        </label>
                                        <label id="username" class="block clearfix">
                                            <span class="block input-icon input-icon-right">
                                              <input type="text" class="form-control" placeholder="用户名" />
                                              <i class="icon-user"></i>
                                            </span>
                                        </label>
                                        <label id="password" class="block clearfix">
                                            <span class="block input-icon input-icon-right">
                                              <input type="password" class="form-control" placeholder="密码" />
                                              <i class="icon-lock"></i>
                                            </span>
                                        </label>
                                        <label id="repassword" class="block clearfix">
                                            <span class="block input-icon input-icon-right">
                                              <input type="password" class="form-control" placeholder="重复密码" />
                                              <i class="icon-retweet"></i>
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
                                        <label class="block">
                                            <input type="checkbox" class="ace" />
                                            <span class="lbl">
                                              我接受
                                              <a href="#">用户注册协议</a>
                                            </span>
                                        </label>
                                        <div class="space-10"></div>
                                        <div class="clearfix">
                                            <button type="reset" class="width-30 pull-left btn btn-sm">
                                                <i class="icon-refresh"></i>
                                                重置
                                            </button>
                                            <button type="submit" class="width-65 pull-right btn btn-sm btn-success">
                                                确认注册
                                                <i class="icon-arrow-right icon-on-right"></i>
                                            </button>
                                        </div>
                                    </fieldset>
                                </form>
                            </div>
                            <div class="toolbar center">
                                <a href="/login" class="back-to-login-link">
                                    <i class="icon-arrow-left"></i>
                                    返回登录
                                </a>
                            </div>
                        </div>
                    </div>
                    <#--用户注册-->
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
            , $e = $('#email')
            , $form = $('#register-form');

    $form.submit(function(e){
        e.preventDefault();
        var username = $u.find('input').val()
                , password = $p.find('input').val()
                , verify = $v.find('input').val()
                , repassword = $p.find('input').val()
                , email = $e.find('input').val();

        if (!username) {
            $.warningControl($u, '用户名不能为空', true);
            return;
        } else {
            $.warningControl($u, '', false);
        }

        if (!password) {
            $.warningControl($p, '密码不能为空', true);
            return;
        } else {
            $.warningControl($p, '', false);
        }

        if (repassword != password) {
            $.warningControl($p, '俩次输入密码不一致', true);
        } else {
            $.warningControl($p, '', false);
        }

        if (!verify) {
            $.warningControl($v, '验证码不能为空', true);
            return;
        } else {
            $.warningControl($v, '', false);
        }

        $.ajax({
            type: 'POST',
            url: '/register/confirm',
            data: {
                "email":email, "userName":username, "password":password, "verifyCode":verify
            },
            success: function(d){
                if (d.code == 0) {
                    alert(d.msg);
                    location.href = '/login';
                }
            },
            error: function(d){
                alert(d.msg);
            }
        });
    });
</script>
</body>
</html>
