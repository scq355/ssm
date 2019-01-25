<#import "../include/common-css.ftl" as css/>
<#import "../include/common-js.ftl" as js/>
<!DOCTYPE html>
<html lang="en">
<head>
<@css.description/>
<@css.ace/>
<@css.datePicker/>
</head>

<body>
<#include "../menu/top-menu.ftl"/>
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {}
    </script>

    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#">
            <span class="menu-text"></span>
        </a>

        <div class="sidebar" id="sidebar">
        <#include "../menu/left-menu.ftl"/>
        </div>

        <div class="main-content">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try {
                        ace.settings.check('breadcrumbs', 'fixed')
                    } catch (e) {}
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="icon-home home-icon"></i>
                        <a href="">主页</a>
                    </li>

                    <li>
                        <a href="/">上一级</a>
                    </li>
                    <li class="active">用户列表页</li>
                </ul>

                <div class="nav-search" id="nav-search">
                    <form class="form-search">
                        <span class="input-icon">
                          <input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off"/>
                          <i class="icon-search nav-search-icon"></i>
                        </span>
                    </form>
                </div>
            </div>

            <div class="page-content">
                <div class="page-content">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <h3 class="header smaller lighter blue">用户列表</h3>
                                            <div class="table-header"></div>
                                            <div class="row">
                                                <form id="myform" action="" method="post">
                                                    <input type="hidden" name="pageNumber" value="${pageNumber!}">
                                                    <div class="col-sm-3 form-group">
                                                        <div class="input-group">
                                                            <div class="input-group-addon">用户名：</div>
                                                            <input type="text" class="form-control" name="userName" value="${userName!}">
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-3 form-group">
                                                        <div class="input-group">
                                                            <div class="input-group-addon">手机号：</div>
                                                            <input type="text" class="form-control" name="mobile" value="${mobile!}">
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-3 form-group">
                                                        <div class="input-group">
                                                            <div class="input-group-addon">状态：</div>
                                                            <select class="form-control" name="status">
                                                                <option value="-1">全部</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-3 form-group">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                              <i class="icon-calendar">&nbsp;时间开始：</i>
                                                            </span>
                                                            <input class="form-control date-picker" data-date-format="yyyy-mm-dd" type="text" name="registBegin" value="${registBegin!}">
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-3 form-group">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                              <i class="icon-calendar">&nbsp;时间结束：</i>
                                                            </span>
                                                            <input class="form-control date-picker" data-date-format="yyyy-mm-dd" type="text" name="registEnd" value="${registEnd!}">
                                                        </div>
                                                    </div>
                                                </form>
                                                <div class="col-sm-3 form-group">
                                                    <button type="submit" class="btn btn-primary">查询</button>
                                                    <button type="submit" class="btn btn-export">导出 </button>
                                                </div>
                                            </div>

                                            <div class="space-6"></div>
                                            <div class="table-responsive">
                                                <div class="alert alert-block alert-success">
                                                    <i class="icon-bar-chart green"></i>总用户数：<strong class="red">${count!}</strong>
                                                </div>
                                                <table id="sample-table-2" class="table table-striped table-bordered table-hover">
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>用户名</th>
                                                        <th>电话号码</th>
                                                        <th>注册时间</th>
                                                        <th>注册邮箱</th>
                                                        <th>操作</th>
                                                    </tr>
                                                    <#list p.getThisPageElements() as user>
                                                    <tr>
                                                        <td>${user.id}</td>
                                                        <td>${user.userName!}</td>
                                                        <td>${user.phoneNumber!}</td>
                                                        <td>${user.registerTime?string("yyyy-MM-dd HH:mm:ss")!}</td>
                                                        <td>${user.email!}</td>
                                                        <td>
                                                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                                                                <a target="_blank" class="blue" href="/user/detail?id=${user.id?string}">
                                                                    <i class="icon-zoom-in bigger-110">查看</i>
                                                                </a>
                                                                <#if user.status == 0>
                                                                    <a class="green" href="javascript:qiyong(${user.id?string})">
                                                                        <i class="icon-ok bigger-110">启用</i>
                                                                    </a>
                                                                </#if>
                                                                <#if user.status == 1>
                                                                    <a class="red" href="javascript:jinyong(${user.id?string})">
                                                                        <i class="icon-remove bigger-110">禁用</i>
                                                                    </a>
                                                                </#if>
                                                                <a class="blue" href="javascript:resetPwd(${user.id?string})">
                                                                    <i class="icon-key bigger-110">重置密码</i>
                                                                </a>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    </#list>
                                                </table>
                                                <#include "../include/pagination.ftl"/>
                                                <@pagination p />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <#include "../menu/right-menu.ftl"/>
    </div>

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div>
<@js.ace/>
<@js.dataTables/>
<@js.datePicker/>
<script>
$(function(){
    $('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
        $(this).prev().focus();
    });

    $('#spinner1').ace_spinner({
        value: 0,
        min: 0,
        max: 200,
        step: 10,
        btn_up_class: 'btn-info',
        btn_down_class:'btn-info'
    }).on('change', function(){
        //alert(this.value)
    });

    /* 查询 */
    $('.btn-primary').click(function() {
        $('#myform').submit();
    });

    /* 导出 */
    $('.btn-export').click(function() {
        window.location.href = "/user/export?mobile=${mobile!}&userName=${userName!}&registBegin=${registBegin!}&registEnd=${registEnd!}";
    });
});

function resetPwd(id){
    bootbox.confirm("确定要重置密码吗?", function(result) {
        if(result) {
            $.ajax({
                url:"/user/resetPwd/",
                type:"POST",
                data:{"id":id,"status":0},
                success:function(a){
                    if(a == "success"){
                        bootbox.alert("密码重置成功！");
                    }else{
                        alert("失败:" + a);
                    }
                }
            });
        }
    });
}
</script>
</body>
</html>
